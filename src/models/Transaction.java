package models;

import data_access_objects.dbconnection_abstract;

public class Transaction extends dbconnection_abstract {
	
	protected int transactionId, day, month, year, ssn, branchCode, count;
	protected double transactionValue;
	protected String cardNo, transactionType, custName;
		
	
	// getter / setter Day
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	// getter / setter Month
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	// getter / setter Year
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	// getter / setter Value
	public double getValue() {
		return transactionValue;
	}
	public void setValue(double transactionValue) {
		this.transactionValue = transactionValue;
	}
	// getter / setter Card Number
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	// getter / setter SSN
	public int getSSN() {
		return ssn;
	}
	public void setSSN(int ssn) {
		this.ssn = ssn;
	}
	// getter / setter BranchCode
	public int getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(int branchCode) {
		this.branchCode = branchCode;
	}
	// getter / setter Transaction Type
	public String getType() {
		return transactionType;
	}
	public void setType(String transactionType) {
		this.transactionType = transactionType;
	}
	// getter / setter Transaction Count
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	// getter / setter Customer Name
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
}





















