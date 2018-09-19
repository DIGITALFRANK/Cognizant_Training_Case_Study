
-- 1) Find the top 20 zip codes (hint: branch_zip) by total transaction value

SELECT sum(transaction_value) total_value, branch_zip
FROM CDW_SAPP_F_CREDIT_CARD c join CDW_SAPP_D_BRANCH b 
ON  c.branch_code = b.branch_code
GROUP BY branch_zip
ORDER BY total_value desc
LIMIT 20;










