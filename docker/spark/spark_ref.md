# Spark Reference

## Configuration


Load the Spark image to Docker and start the container with the following command:

```bash
# Load the Spark image
docker pull apache/spark

# Start the Spark container
docker run -it --name spark-container -p 4040:4040 -p 8080:8080 -v "//c/Users/Khor Kean Teng/Downloads/MDS Git Sem 2/wqd7007/docker/spark:/data" apache/spark bash
```

## Work with Spark

First, we will experiment with a Word-Count program using Spark:

```bash
// Read the input file
val input = sc.textFile("../../../data/input/input.txt")

// Perform word count
val count = input.flatMap(line => line.split(" "))
  .map(word => (word, 1))
  .reduceByKey(_ + _)

// Save the results
count.saveAsTextFile("../../../data/outfile")
println("OK")
```

We can then check the result in our local directory. 

```bash
# exit the spark shell
ctrl + c

# go to root by typing 3 times
cd ..

# view the data
cat data/outfile/part-00000
```

## Machine Learning with Spark

Let's experiment with some simple machine learning tasks using Spark. We will use it to predict the wine price. The program is `job.scala` and the data is in `data/input/wine_data.csv`. The program will read the data, train a model, and save the model to a file. The program is as follows:

```bash
# first load the program at spark shell
:load ../../../data/job.scala

# run the program
Main.main(Array())
```