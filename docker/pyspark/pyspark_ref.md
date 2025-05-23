# Running Pyspark

This code will make use of `pyspark` in a Jupyter Notebook.

```bash
# first of all get the images (1.6GB)
# takes a few minutes to download
docker pull quay.io/jupyter/pyspark-notebook

# run it
docker run -p 8888:8888 quay.io/jupyter/pyspark-notebook

# if want to use local file
docker run -p 8888:8888 -v "c:/Users/Khor Kean Teng/Downloads/MDS Git Sem 2/wqd7007/docker/pypark:/home/jovyan/work" quay.io/jupyter/pyspark-notebook
# then a localhost link will be provided in the terminal to access the notebook
```