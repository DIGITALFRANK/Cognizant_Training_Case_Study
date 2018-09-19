# Cognizant Training [Case_Study]
## CDW_SAPP Credit Card Management Application, ETL/ELT, & Data Warehousing

- The software aggregates nearly 50K rows of transaction/bank/customer data for optimal Analysis and BI. 
- Data is processed through an ETL/ELT pipeline and warehoused in a distributed file system (HDFS).
- Automated & optimized incremental workflow is coordinated for per-minute ETL/ELT and warehousing of future data




### Java Database Connectivity (JDBC) for Data Analysis

### 2.1 - Operational Database (MySQL RDBMS)

Download and extract all files (uncompress the downloaded zip folder) and import that entire folder into an IDE such as Eclipse that supports Java. Open the *CDW\_SAPP.sql* file in MySQL Workbench and run the entire file.  Be sure to match your database credentials with the credentials in the db.properties file located at */src/resources/db.properties*.  In your Integrated Development Environment, run the **_MainDriver.java_** class, which will start the program in your console.  In the IDE's console, you may use the application for Data Analysis as you wish and exit the application at any moment.


### Functional Requirement 2.1.1 - Transaction Details Module
- options 1-3 of the Java application address transaction details analysis 

## Functional Requirement 2.1.2 - Customer Details Module
- options 4-7 of the Java application address customer details analysis 




### 2.2.1 - DATA EXTRACTION & TRANSPORTATION MODULE - Apache Sqoop

**Functional Requirement 2.2.1 - Importing data from RDBMS to Hadoop HDFS using Sqoop**
- the `sqoop-jobs-execute.txt` file, located at `/ETL/ETL_req_2-2-1_Sqoop/sqoop-jobs-execute.txt`, contains instructions and Sqoop commands to create Metastore jobs that when executed will extract, transform, and import data to the Hadoop cluster.
- From Putty or HortonWorks Sandbox CLI, create and execute the jobs, please be sure that the Sqoop Metastore server is running. 


### 2.2.2 - DATA LOADING MODULE - Apache Hive

**Functional Requirement 2.2.2 - Loading data into the Hive warehouse**
- the `hive-table-partitioning.hql` file, located at `/ETL/ETL_req_2-2-2_Hive/hive-table-partitioning.hql`, contains instructions and hive scripts that load and transform data into partitioned Hive tables in the data warehouse.
- From the Ambari GUI, select Hive view and run the scripts.  Once completed, the data will be in partitioned as ORC files in the Hive warehouse.



### 2.2.3 - PROCESS AUTOMATION MODULE - Apache Oozie
**Functional Requirement 2.2.3 - Automating the process with Oozie**
- the folder `ETL_req_2-2-3_Oozie_automation` contains a number of files that will allow us to set up process automation using Oozie.
- create a directory `credit_card_system_workflow` under user `maria_dev` in HDFS using Ambari Files View.
- load all the hive scripts (.hql files) from `ETL_req_2-2-3_Oozie_automation`, as well as the `workflow.xml` and `coordinator.xml` files to `/user/maria_dev/credit_card_system_workflow`.
- using WinSCP or similar software, upload the `job.properties` file inside the `ETL_req_2-2-3_Oozie_automation` folder to your Linux VM's Document folder.
- Login to Putty or HortonWorks Sandbox CLI as 'root', navigate to the Documents folder and run the following command to launch the process:
`oozie job   -oozie  http://localhost:11000/oozie  -config  job.properties  -run`



### 2.2.4 - PROCESS OPTIMIZATION MODULE (incremental data ETL) - Apache Oozie Coordinator
**Functional Requirement 2.2.4 - Scheduling and Optimizing the incremental ETL process**



### 2.2.5 - VISUALIZATION OF DATASET
**Functional Requirement 2.2.5 - Exploration and visualization using Hive Query & Hive Visualization tool**





