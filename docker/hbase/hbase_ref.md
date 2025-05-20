# HBASE Reference

## Configuration

Set up Docker, and pull the images to load it to the terminal for access:

```bash
docker pull harisekhon/hbase

# run the container
docker run -d --name hbase -p 2181:2181 -p 8080:8080 -p 8085:8085 -p 9090:9090 -p 9095:9095 -p 16000:16000 -p 16010:16010 -p 16201:16201 -p 16301:16301 harisekhon/hbase

# verify the container is running
docker ps

# open the HBase shell, need some time to start
docker exec -it hbase bash
```

## Working with HBase

```bash
version
list
```

Creating a table:

```bash
create 'customer', 'address', 'order'
list
```

Further Operations:
```bash
# insert data
put 'customer', 'john', 'address:city', 'New York'
put 'customer', 'john', 'address:state', 'NY'
put 'customer', 'john', 'order:order_id', '12345'
put 'customer', 'john', 'order:amount', '100.00'

put 'customer', 'harry', 'address:city', 'Los Angeles'
put 'customer', 'harry', 'address:state', 'CA'
put 'customer', 'harry', 'order:order_id', '67890'
put 'customer', 'harry', 'order:amount', '200.00'

# get data
get 'customer', 'john'
get 'customer', 'harry'

# get the address of john
get 'customer', 'john', 'address'

# get the city of john
get 'customer', 'john', 'address:city'

# scan records
scan 'customer'

# update john order:amount to 9999
put 'customer', 'john', 'order:amount', '9999'

# deleteall harry
deleteall 'customer', 'harry'

# delete john order:amount
delete 'customer', 'john', 'order:amount'

# count records
count 'customer'

# drop table
disable 'customer'

# enable table
enable 'customer'

# remove table
drop 'customer'
```