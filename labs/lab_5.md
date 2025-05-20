# Lab 5 Documentation

- Date: May 20, 2025
- Platform: Oracle Quick VM

## Setup
Refer to lecture slides for setting up the environment. The following steps are a summary of the setup process.

## Configuration

Download the `pig-.16.0.tar.gz` file from [here](https://downloads.apache.org/pig/pig-0.16.0/). Unzip the file and move it to `home/redi/pig`:

```bash
cd ~
mkdir pig
move ~/Downloads/pig-0.16.0/* ~/pig
```
Update the `PATH` variable to include the Pig directory:

```bash
export PIG_HOME=/home/redi/pig
export PATH=$PATH:$PIG_HOME/bin

# update the .bashrc file
source ~/.bashrc
```

Check if Pig is installed correctly by running:

```bash
pig -version

# if it works, open pig cli
pig
```

## Working with Pig

```bash
batting = load '/user/hdfs/batting.csv' using PigStorage(',');
raw_runs = FILTER batting BY $1>0;

# view the raw_runs
DUMP raw_runs;

# filter data
Runs = FOREACH raw_runs GENERATE $0 as playerID, $1 as year, $8 as runs;

# view the Runs
DUMP Runs;

# aggregate data
grp_data = GROUP Runs by (year);
max_runs = FOREACH grp_data GENERATE group as 
grp,MAX(Runs.runs) as max_runs;
DUMP max_runs

# join data
join_max_run = JOIN max_runs by ($0, max_runs), 
runs by (year, runs);
join_data = FOREACH join_max_run GENERATE $0 as 
year, $2 as playerID, $1 as run.
DUMP join_data
```

## 💡 Issues Encountered

There is an unknown error being faced when running DUMP for two consecutive times, where the second map-reduce job will get stuck indefinitely. We need to restart the entire Hadoop cluster to resolve this issue. Furthermore, after complex transformation, the DUMP command will be get stuck, further examination using Docker will be implemented. We also notice a `maxretries` message being sent continuously during the `DUMP` process. Further investigation is needed to understand the cause of this issue.

## Solution
To resolve the above isseu we need to start the `historyserver` in Hadoop cluster.

```bash
# find the shell script to start the history server
find / -name "mr-jobhistory-daemon.sh" 2>/dev/null

# start the history server
/usr/local/hadoop-2.9.2/sbin/mr-jobhistory-daemon.sh start historyserver
```