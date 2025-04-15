# Lab 1 Documentation

- Date: April 15, 2025
- Platform: Oracle Quick VM

## Setup
Follow exactly the video steps and perform a `clean install` which will delete the OS. Following this, user sign in will be enabled can set username and password.

## Installing Packages 1

- Skip whatever update and upgrade using `sudo` to avoid error
- Only add openssh and java

```bash
sudo apt-get install openssh-server
sudo apt-get install openjdk-8-jdk
java -version
```

## Installing Packages 2

- Then download `hadoop-2.7.7` using Mozilla, download is very slow on my device
- After downloading, unzip the file:

```bash
tar -xzvf hadoop-2.7.7.tar.gz
```

- After unzipping go to `/home/username/` create a file called `hadoop`
- Then copy all the files in `hadoop-2.7.7` and paste there
- Change the `JAVA_HOME` path in `/home/username/hadoop/etc/hadoop/hadoop-env.sh`
- Also go to root `~` path by performing `cd ..` twice and edit `/hadoop/etc/hadoop/hadoop-env.sh`

## Using Hadoop

- Check if hadoop is working, go to root `~`

```bash
/home/username/hadoop/bin/hadoop
```

If hadoop is working, we can perform a mapreduce operation with it:

```bash
mkdir ~/input # create a /username/ level
cp /home/username/hadoop/etc/hadoop/*.xml ~/input

# note the following should be one line
/home/{yourname}/hadoop/bin/hadoop jar /home/{yourname}/hadoop/share/hadoop/mapreduce hadoop mapreduce-examples-2.7.7.jar grep ~/input ~/grep_example 'principal[.]*'
```

If the above code executed successfully, then the output can be viewed by using `cat ~/grep_example/*`