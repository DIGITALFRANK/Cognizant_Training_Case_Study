package models;

import java.sql.Timestamp;
import data_access_objects.dbconnection_abstract;

public class Customer extends dbconnection_abstract {

	protected String firstName, middleName, lastName, creditCardNo, streetName, aptNo, custCity, custState, custZip, custCountry, custEmail;
	protected int custSSN, custPhone;
	protected Timestamp lastUpdated;
	
	// getter / setter FristName
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	// getter / setter MiddleName
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	// getter / setter LastName
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	// getter / setter CreditCardNo
	public String getCreditCardNo() {
		return creditCardNo;
	}
	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}
	// getter / setter StreetName
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String steetName) {
		this.streetName = steetName;
	}
	// getter / setter AptNo
	public String getAptNo() {
		return aptNo;
	}
	public void setAptNo(String aptNo) {
		this.aptNo = aptNo;
	}
	// getter / setter CustCity
	public String getCustCity() {
		return custCity;
	}
	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}
	// getter / setter CustState
	public String getCustState() {
		return custState;
	}
	public void setCustState(String custState) {
		this.custState = custState;
	}
	// getter / setter CustZip
	public String getCustZip() {
		return custZip;
	}
	public void setCustZip(String custZip) {
		this.custZip = custZip;
	}
	// getter / setter CustCountry
	public String getCustCountry() {
		return custCountry;
	}
	public void setCustCountry(String custCountry) {
		this.custCountry = custCountry;
	}
	// getter / setter SSN
	public int getCustSSN() {
		return custSSN;
	}
	public void setCustSSN(int custSSN) {
		this.custSSN = custSSN;
	}
	// getter / setter Phone
	public int getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(int custPhone) {
		this.custPhone = custPhone;
	}
	// getter / setter Email
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	// getter / setter lastUpdated
	public Timestamp getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Timestamp timestamp) {
		this.lastUpdated = timestamp;
	}

}











