# Lab 2 Documentation

- Date: April 16, 2025
- Platform: Oracle Quick VM

## Setup
One directory is computer and one in Desktop, which to update?

## Configuration

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

## Mapreduce
The code for mapreduce is not working after the change.