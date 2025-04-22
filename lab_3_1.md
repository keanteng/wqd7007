# Lab 3 Part 1 Documentation

- Date: April 22, 2025
- Platform: Oracle Quick VM

## Setup
Follow the slide, part 1 will work with MySQL. Remember username is `redi` and password is `1234`.

## Configuration

Installing Mysql using terminal:

```bash
sudo apt-get install mysql-server
systemctl start mysql
```

To open the mysql shell, use the following command. We need to add `sudo` as the lecture slide is not working:

```bash
sudo mysql -u root -p
```

Then create a database and table using the mysql shell:

```sql
CREATE DATABASE WQD7007;
Use WQD7007;
create table churn (customerID varchar(20), 
PaperlessBilling varchar(3), PaymentMethod varchar(30), MonthlyCharges numeric(8,2), Churn varchar(3));
exit
```

To view the create table:

```sql
SHOW DATABASES;
USE WQD7007;
SHOW TABLES;
```

Load the CSV file into the table using the following command. There is some issue to load straight from local device to the virtual machine. So we need to get a copy of the file in our directory first. Then we also need to put in the mysql-files directory as it will show `--secure-file-priv` erro message:

```bash
# Copy the CSV file to the mysql-files directory
sudo cp /path/to/churn_reduced.csv /var/lib/mysql-files/
# write in terminal
mysql -u root -p WQD7007 --execute="
LOAD DATA INFILE '/var/lib/mysql-files/churn_reduced.csv'
INTO TABLE churn
FIELDS TERMINATED BY ','
IGNORE 1 LINES;"
```

To check the loaded data:

```sql
Use WQD7007;
SELECT * FROM churn LIMIT 10;
```

## Extra

Save the command history

```bash
history > ~/Desktop/history.txt
```

How to get the CSV file from the virtual machine to your local machine. We will use `github` to upload the file. Then we can download from github to our local machine. This is the link: https://github.com/keanteng/wqd7007