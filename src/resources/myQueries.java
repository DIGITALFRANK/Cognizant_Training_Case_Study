package resources;

public class myQueries {
	public static String field;
	// functional requirement 1
	public final static String transactionsByZipCode = "select distinct TRANSACTION_TYPE, round(TRANSACTION_VALUE, 2) '$ Amount', day, month, year, CREDIT_CARD_NO, CUST_SSN, BRANCH_CODE " +
			"from CDW_SAPP_CREDITCARD join CDW_SAPP_CUSTOMER using(Credit_Card_No)" +
			"where CUST_ZIP = ? and YEAR = ? and MONTH = ? " +
			"order by day";
	
	// functional requirement 2
	public final static String totalByType = "select sum(transaction_value), count(*)" +
			"from CDW_SAPP_CREDITCARD " +
			"where TRANSACTION_TYPE = ? " +
			"group by TRANSACTION_TYPE";
	
	// functional requirement 3
	public final static String totalByState = "select round(sum(transaction_value), 2), count(transaction_type) '# of Transactions' " + 
			"from CDW_SAPP_CREDITCARD join CDW_SAPP_BRANCH using(branch_code)" + 
			"where branch_state = ? " + 
			"group by branch_name";
	
	// functional requirement 4
	public final static String customerDetails = "select first_name, last_name, CREDIT_CARD_NO, STREET_NAME, APT_NO, CUST_CITY, CUST_STATE, CUST_ZIP, CUST_COUNTRY, CUST_PHONE, CUST_EMAIL, LAST_UPDATED " +		 
			"from CDW_SAPP_CUSTOMER "
			+ "where SSN = ? ";
		
	// functional requirement 5
	public final static String updateCustomerDetailsP1 = " update CDW_SAPP_CUSTOMER set ";
	public final static String updateCustomerDetailsP2 = " = ? where SSN = ? ";
	
	// functional requirement 6
	public final static String monthlyBill = " select day, month, year, branch_code, transaction_type, round(transaction_value, 2), concat(first_name, ' ', last_name) 'customer_name' " + 
			"from (select * " + 
			"		from CDW_SAPP_CREDITCARD join CDW_SAPP_CUSTOMER using(credit_card_no) " + 
			"        where month = ? and year = ?) t_ID " + 
			"where t_ID.CREDIT_CARD_NO = ? " + 
			"order by day";
	
	// functional requirement 7
		public final static String custTransactionsBetween = "select day, month, year, t_range.credit_card_no, branch_code, transaction_type, round(transaction_value, 2), concat(first_name, ' ', last_name) 'customer_name' " + 
			"from (select * " + 
			"		from CDW_SAPP_CREDITCARD " + 
			"       where CAST(STR_TO_DATE(CONCAT(year,'-',month,'-',day), '%Y-%m-%d') as date) between CAST(STR_TO_DATE(CONCAT(?, '-', ?, '-', ?), '%Y-%m-%d') as date) and CAST(STR_TO_DATE(CONCAT(?, '-', ?, '-', ?), '%Y-%m-%d') as date)) t_range " +		 
			"join CDW_SAPP_CUSTOMER c on ssn = cust_ssn " + 
			"where t_range.cust_ssn = ? " + 
			"order by year desc, month desc, day desc";
	
}

