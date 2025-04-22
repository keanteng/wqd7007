# Lab 3 Part 2 Documentation

- Date: April 22, 2025
- Platform: Oracle Quick VM

## Setup
Follow the slide, part 2 will work with Sqoop. Remember username is `redi` and password is `1234`.

## Configuration

Download the `tar.gz` file from here https://archive.apache.org/dist/sqoop/1.4.7/. Select the `hadoop-2.6.0` one.

Then unzip the file. Can use the code or do in file explorer:
```bash
# Better use file explorer
tar -xzf sqoop-1.4.7.bin__hadoop-2.6.0.tar.gz
```

Then create `sqoop` folder at `home` directory and paster everything inside. Then update the environment variables by inserting the variables at the last line:

```bash
nano ~/.bashrc

# version 1
export PATH=$PATH:/home/redi/sqoop/bin
# version 2
export SQOOP_HOME=/home/redi/hadoop # redi is the username
export PATH=$PATH:$SQOOP_HOME/bin

source ~/.bashrc
```

Then update the template file. First make a copy of `/home/redi/sqoop/conf/sqoop-env-template.sh` and rename to `/home/redi/sqoop/conf/sqoop-env.sh`. Then update the content:

```bash
# remember to remove the comment tag
export HADOOP_COMMON_HOME=/home/student/hadoop
export HADOOP_MAPRED_HOME=/home/student/hadoop
```

Then copy the MySQL connector from Spectrum (can be downloaded at the `/jar` of this repo) and paste to `home/redi/sqoop/lib`. Then, run the command:

```bash
# THIS CODE
sqoop import -connect 
jdbc:mysql://localhost/WQD7007 -username 
root -password 1234 -table churn -m 1
```

## Issues

Currently, the MapReduce process cannot be completed and will stuck at 0%, although the nodes are working at `localhost:8088`. Further investigation is needed.

**Attempt**
- Increase memory of `yarn`, still not working

## Bug Fixes

- Typo `locahost` should be modified to `localhost` at `yarn-site.xml` as it will result in an empty and accesible node to be called
- `namenode` not starting or `resource-manager` not appearing in `sudo jps` is detected. To resolve we need to restart everything via:
```bash
hadoop/sbin/start-all.sh
hadoop/sbin/stop-all.sh
# check localhost to see if hadoop is running
```
- When booting the VM the screen is also detected to be freeze, this is resolved by powering down the machine. Powering down the machine will not delete the machine data and it can be a useful approach in certain scenario.

**MySQL Issue**
```bash
# THIS CODE
sqoop import -connect 
jdbc:mysql://localhost/WQD7007 -username 
root -password 1234 -table churn -m 1 # change password
```
- When the code is run a `/churn` folder will be created at hadoop, and running it for a second time will result in an error where a folder exists, we can remove the created folder with:
```bash
# delete folder
hdfs dfs -rm -r /home/redi/churn

# check folder note that /home/redi is created by default
# like we create /abc
# then if will be put at /home/redi/abc
hdfs dfs -ls /home/redi
```