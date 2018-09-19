
-- 4.A create staging table for incremental data upload CDW_SAPP_D_TIME_INCREMENTAL, at the location of the incremental files 

DROP TABLE IF EXISTS CDW_SAPP_D_TIME_INCREMENTAL;

CREATE EXTERNAL TABLE IF NOT EXISTS CDW_SAPP_D_TIME_INCREMENTAL (
    TIMEID DATE,
    TRANSACTION_ID INT,
    DAY INT,
    MONTH INT,
    QUARTER STRING,
    YEAR INT
)
ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n'
LOCATION '/user/maria_dev/Credit_Card_System/CDW_SAPP_TIME/incremental';





-- 4.B insert new incremental data into the warehouse

SET hive.exec.dynamic.partition=true;
SET hive.exec.dynamic.partition.mode=nonstrict;
SET hive.support.sql11.reserved.keywords=false;

INSERT INTO TABLE CDW_SAPP_D_TIME_PARTITIONED_QUARTERLY
PARTITION (QUARTER)
SELECT 
    TIMEID DATE,
    TRANSACTION_ID INT,
    DAY,
    MONTH,
    YEAR,
    QUARTER
FROM CDW_SAPP_D_TIME_INCREMENTAL;
















