import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.feature.{StringIndexer, VectorAssembler}
import org.apache.spark.ml.regression.GBTRegressor
import org.apache.spark.sql.types.{DoubleType, StringType, StructField, StructType}
import org.apache.spark.sql.{Encoders, SparkSession}
object Main {
    def main(args: Array[String]) = {
        val file = new java.io.File("../../../data/input/wine_data.csv")
        println(s"File exists: ${file.exists()}")

        // Get absolute path to help with debugging
        println(s"Absolute path: ${file.getAbsolutePath}")
        
        val spark = SparkSession.builder
            .appName("Wine Price Regression")
            .master("local")
            .getOrCreate()
            
        //We'll define a partial schema with the values we are interested in
        val schemaStruct = StructType(
            StructField("country", StringType) ::
            StructField("description", StringType) :: 
            StructField("designation", StringType) ::
            StructField("points", DoubleType) ::
            StructField("price", DoubleType) :: Nil
        )
        
        //We read the data from the file
        val df = spark.read
            .option("header", true)
            .schema(schemaStruct)
            .csv("../../../data/input/wine_data.csv")
            
        println(s"Raw DataFrame count: ${df.count()}")
        if (df.count() > 0) df.show(5)
        
        //Filter nulls
        // After reading and before processing the data
        val filteredDf = df.na.drop()
            .filter($"country".isNotNull && $"country" =!= "")

        println("Country value counts:")
        filteredDf.groupBy("country").count().orderBy($"count".desc).show(10)
        println(s"After na.drop() count: ${filteredDf.count()}")
        if (filteredDf.count() > 0) filteredDf.show(5)
        
        //Use seed for reproducible split
        val Array(trainingData, testData) = filteredDf.randomSplit(Array(0.8, 0.2), seed = 42)
        
        println(s"Training data count: ${trainingData.count()}")
        println(s"Test data count: ${testData.count()}")

        // Skip the rest if data is empty
        if (trainingData.count() == 0 || testData.count() == 0) {
            println("ERROR: Training or test data is empty! Cannot continue.")
            System.exit(1)
        }
        val labelColumn = "price"

        //We define two StringIndexers for the categorical variables
        val countryIndexer = new StringIndexer()
            .setInputCol("country")
            .setOutputCol("countryIndex")
            .setHandleInvalid("skip")  // Add this to skip invalid entries

        //We define the assembler to collect the columns into a new column with a single vector - "features"
        val assembler = new VectorAssembler()
            .setInputCols(Array("points", "countryIndex"))
            .setOutputCol("features")

        //For the regression we'll use the Gradient-boosted tree estimator
        val gbt = new GBTRegressor()
            .setLabelCol(labelColumn)
            .setFeaturesCol("features")
            .setPredictionCol("Predicted " + labelColumn)
            .setMaxIter(50)
            .setMaxBins(50)

        //We define the Array with the stages of the pipeline
        val stages = Array(
            countryIndexer,
            assembler,
            gbt
        )
        //Construct the pipeline
        val pipeline = new Pipeline().setStages(stages)

        //We fit our DataFrame into the pipeline to generate a model
        val model = pipeline.fit(trainingData)

        //We'll make predictions using the model and the test data
        val predictions = model.transform(testData)

        //This will evaluate the error/deviation of the regression using the Root Mean Squared deviation
        val evaluator = new RegressionEvaluator()
            .setLabelCol(labelColumn)
            .setPredictionCol("Predicted " + labelColumn)
            .setMetricName("rmse")

        //We compute the error using the evaluator
        val error = evaluator.evaluate(predictions)
        println(error)

        spark.stop()
    }
}