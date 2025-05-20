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
batting = load ‘/user/hdfs/batting.csv’ using PigStorage(‘,’);
raw_runs = FILTER batting BY $1>0;

# view the raw_runs
DUMP raw_runs;

# filter data
Runs = FOREACH raw_runs GENERATE $0 as playerID, $1 as year, $8 as runs;

# view the Runs
DUMP Runs;

# aggregate data
grp_data = GROUP runs by (year);
max_runs = FOREACH grp_data GENERATE group as 
grp,MAX(run.runs) as max_runs;
DUMP max_runs
```