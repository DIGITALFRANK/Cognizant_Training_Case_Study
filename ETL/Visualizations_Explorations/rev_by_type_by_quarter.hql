
-- 2) Find total transaction value for each transaction type by Quarter in 2018
-- Hint: Find quarter from 'creditcard' table using month or use 'time' table if you already added transaction_id column there.

SELECT transaction_type, sum(transaction_value) total_value, quarter
FROM CDW_SAPP_F_CREDIT_CARD c join CDW_SAPP_D_TIME t
ON c.transaction_id = t.transactino_id
GROUP BY transaction_type, quarter
ORDER BY quarter;










