what is distributed computing
- a collection of independent computers that appear to its users as a single coherent system

why do we need it
- scaling in processing and data
- diverse application domains
- collaboratoin
- cost
- storage, computation and messaging

why distributed computing
- big data continues to grow
- applications becoming data-intensive
- lack resournces on individual computers
- processor memory speed gap

how to do that
- express a problem as parallel processes to execute of different machines >>> programming modesl and concurrency
- let different machine exchange information >>> communication
- let the processes synchronized and agree on shared value >>> synchronization
- enhance reliability >>> consistency and replication
- recover from partial failure >>> fault tolerance
- secure communication, rights to access >>> security
- extend interfaces to mimic behavior of another system, reduce platforms diversity >>> virtualization

what is hadoop
- open source framework for distributed storage and processing of large sets of data on commodity hardware

hadoop pillars
- data management
- data acces
- data governance and integration
- security
- operations

challenges in cluster computing
- store data persistently and availability even nodes fail
- deal with node failures during long running computation
- netowork bottleneck delays time of processing data

what is mapreduce
- splits large dataset into independent chunks and organized into key and values pairts for parallel processing
- map divided input into ranges by the inputformat and creates a map tasks for each range in the input
- reduce collects the various results and combines them to answer the larger problme that the moster nodes needs to solve