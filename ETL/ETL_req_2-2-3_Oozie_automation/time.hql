
-- 4.A create staging table CDW_SAPP_D_TIME, at the location of the files 

DROP TABLE IF EXISTS CDW_SAPP_D_TIME;

CREATE EXTERNAL TABLE IF NOT EXISTS CDW_SAPP_D_TIME (
    TIMEID DATE,
    TRANSACTION_ID INT,
    DAY INT,
    MONTH INT,
    QUARTER STRING,
    YEAR INT
)
ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n'
LOCATION '/user/maria_dev/Credit_Card_System/CDW_SAPP_TIME';



-- 4.B create Hive ORC table, to be partitioned quarterly

DROP TABLE IF EXISTS CDW_SAPP_D_TIME_PARTITIONED_QUARTERLY;

SET hive.exec.dynamic.partition=true;
SET hive.exec.dynamic.partition.mode=nonstrict;

CREATE TABLE IF NOT EXISTS CDW_SAPP_D_TIME_PARTITIONED_QUARTERLY (
    TIMEID DATE,
    TRANSACTION_ID INT,
    DAY INT,
    MONTH INT,
    YEAR INT
)
PARTITIONED BY (QUARTER STRING)
ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n'
STORED AS ORC;



-- 4.C insert Time data into Hive partitioned ORC table

SET hive.exec.dynamic.partition=true;
SET hive.exec.dynamic.partition.mode=nonstrict;
SET hive.support.sql11.reserved.keywords=false;

INSERT OVERWRITE TABLE CDW_SAPP_D_TIME_PARTITIONED_QUARTERLY
PARTITION (QUARTER)
SELECT 
    TIMEID DATE,
    TRANSACTION_ID INT,
    DAY,
    MONTH,
    YEAR,
    QUARTER
FROM CDW_SAPP_D_TIME;
















