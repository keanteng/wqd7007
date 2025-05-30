# Lab 2 Documentation

- Date: April 16, 2025
- Platform: Oracle Quick VM

## Setup
Basically can follow the guides from this video: https://www.youtube.com/watch?v=R_kid1CHuOw

## Configuration

Refers to the configuration codes from lecture slides. All the updates make at root directory of `hadoop`. Not the one at home/redi/hadoop.

- Update `hdfs-site.xml`

```html
 <configuration>
    <property>
        <name>dfs.namenode.name.dir</name>
        <value>/home/student/hadoop/data/nameNode</value>
    </property>
    <property>
        <name>dfs.datanode.data.dir</name>
        <value>/home/student/hadoop/data/dataNode</value>
    </property>
    <property>
        <name>dfs.replication</name>
        <value>1</value>
    </property>
 </configuration>
```

- Update `core-site.xml`

```html
 <configuration>
    <property>
        <name>fs.default.name</name>
        <value>hdfs://localhost:9000</value>
    </property>
 </configuration>
```

- update `mapred-site.xml`

```html
 <configuration>
    <property>
        <name>mapreduce.framework.name</name>
        <value>yarn</value>
    </property>
    <property>
        <name>yarn.app.mapreduce.am.resource.mb</name>
        <value>512</value>
    </property>
    <property>
        <name>mapreduce.map.memory.mb</name>
        <value>256</value>
    </property>
    <property>
        <name>mapreduce.reduce.memory.mb</name>
        <value>256</value>
    </property>
 </configuration>
```

- Update `yarn-site.xml`

```html
 <configuration>
    <property>
        <name>yarn.acl.enable</name>
        <value>0</value>
    </property>
    <property>
        <name>yarn.resourcemanager.hostname</name>
        <value>localhost</value>
    </property>
    <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
    </property>
    <property>
        <name>yarn.nodemanager.resource.memory-mb</name>
        <value>1536</value>
    </property>
    <property>
        <name>yarn.scheduler.maximum-allocation-mb</name>
        <value>1536</value>
    </property>
    <property>
        <name>yarn.scheduler.minimum-allocation-mb</name>
        <value>128</value>
    </property>
    <property>
        <name>yarn.nodemanager.vmem-check-enabled</name>
        <value>false</value>
    </property>
 </configuration>
```

## Testing The Setup

### Launching The Nodes

- Need to figure out how to add `hdfs`s to the path, so that it can be run from any directory. Othersie use `hadoop/bin/hdfs` or `hadoop/bin/hadoop` to run the commands.
- To start and stop the services, use the following commands:

```bash
# Start the services
hadoop/sbin/start-dfs.sh
hadoop/sbin/start-yarn.sh

# Stop the services
hadoop/sbin/stop-dfs.sh
hadoop/sbin/stop-yarn.sh

# Stop all
# hadoop/sbin/stop-all.sh
# Start all
# hadoop/sbin/start-all.sh
```

We can check the status of the services through browser by visitng `http://localhost:9870` for HDFS and `http://localhost:50070`. We can also verify the status of the services through the command line by using the following commands:

```bash
sudo jps
```

### Adding Environment Variables

To access `hadoop` and `hdfs` commands from terminal, we need to add the following environment variables to the `~/.bashrc` file:

Open the `~/.bashrc` file in a text editor by typing:

```bash
nano ~/.bashrc
```

Then add the following lines at the end of the file:
```bash
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export HADOOP_HOME=/home/redi/hadoop # redi is the username
export HADOOP_INSTALL=$HADOOP_HOME
export HADOOP_MAPRED_HOME=$HADOOP_HOME
export HADOOP_COMMON_HOME=$HADOOP_HOME
export HADOOP_HDFS_HOME=$HADOOP_HOME
export YARN_HOME=$HADOOP_HOME
export HADOOP_COMMON_LIB_NATIVE=$HADOOP_HOME/lib/native
export PATH=$PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin
export HADOOP_OPTS="-Djava.library.path=$HADOOP_HOME/lib/native"
```

After exiting editor, run the following command to apply the changes:

```bash
source ~/.bashrc
```

## Mapreduce
The code for mapreduce is not working after the change since the file system is changed.

### Working With hdfs File System

```bash
hadoop fs -mkdir /user
hadoop fs -mkdir /user/hdfs

touch home/redi/Desktop/sample.txt
cat >> home/redi/Desktop/sample.txt
# This is a sample text file.
# This is a sample text file.
# This is a sample text file. 
cat home/redi/Desktop/sample.txt # to check the content of the file

hdfs dfs -put home/redi/Desktop/sample.txt /user/hdfs/sample.txt # to put the file into hdfs
hdfs dfs -ls /user/hdfs/sample.txt # to check the file in hdfs
hdfs dfs -appendToFile /home/redi/Desktop/sample.txt /user/hdfs/sample.txt # to append the file in hdfs
hdfs dfs -get /user/hdfs/sample.txt
hdfs dfs -rm /user/hdfs/sample.txt # to remove the file from hdfs
```