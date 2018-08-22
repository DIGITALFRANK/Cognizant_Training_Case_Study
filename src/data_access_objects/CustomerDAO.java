package data_access_objects;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.conf.ConnectionUrl.Type;

import models.Customer;
import models.Transaction;
import resources.myQueries;

public class CustomerDAO extends dbconnection_abstract {

	// functional requirement 1
	public Customer getCustomerDetails(int ssn) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {		
		try {
			System.out.println("\n\nconnecting to database...");
			myconnection();
			System.out.println("connection successful.\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		prepStmt = connection.prepareStatement(myQueries.customerDetails);
		prepStmt.setInt(1, ssn);
		resultSet = prepStmt.executeQuery();
		Customer customer = new Customer();
		
		if(resultSet.next()) {
			customer.setFirstName(resultSet.getString(1));
			customer.setLastName(resultSet.getString(2));
			customer.setCreditCardNo(resultSet.getString(3));
			customer.setStreetName(resultSet.getString(4));
			customer.setAptNo(resultSet.getString(5));
			customer.setCustCity(resultSet.getString(6));
			customer.setCustState(resultSet.getString(7));
			customer.setCustZip(resultSet.getString(8));
			customer.setCustCountry(resultSet.getString(9));
			customer.setCustPhone(resultSet.getInt(10));
			customer.setCustEmail(resultSet.getString(11));
			customer.setLastUpdated(resultSet.getTimestamp(12));
			
			return customer;
		}
		return null;
	}
	
	// functional requirement 2
		public Customer updateCustomerDetails(String field, String value, int ssn) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {		
			try {
				System.out.println("\n\nconnecting to database...");
				myconnection();
				System.out.println("connection successful.\n\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
			prepStmt = connection.prepareStatement(myQueries.updateCustomerDetailsP1 + field + myQueries.updateCustomerDetailsP2);
			// prepStmt.setString(1, field);
			prepStmt.setString(1, value);
			prepStmt.setInt(2, ssn);
			prepStmt.executeUpdate();
			Customer customer = new Customer();
			
			return getCustomerDetails(ssn);
		}
		
		public Customer updateCustomerPhone(String field, int value, int ssn) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {		
			try {
				System.out.println("\n\nconnecting to database...");
				myconnection();
				System.out.println("connection successful.\n\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
			prepStmt = connection.prepareStatement(myQueries.updateCustomerDetailsP1 + field + myQueries.updateCustomerDetailsP2);
			// prepStmt.setString(1, field);
			prepStmt.setInt(1, value);
			prepStmt.setInt(2, ssn);
			prepStmt.executeUpdate();
			Customer customer = new Customer();
			
			return getCustomerDetails(ssn);
		}

		// functional requirement 3
		public ArrayList<Transaction> getMonthlyBill(int month, int year, String cc) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {		
			ArrayList<Transaction> transactionsList = new ArrayList<Transaction>();
			
			try {
				System.out.println("\n\nconnecting to database...");
				myconnection();
				System.out.println("connection successful.\n\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
			prepStmt = connection.prepareStatement(myQueries.monthlyBill);
			prepStmt.setInt(1, month);
			prepStmt.setInt(2, year);
			prepStmt.setString(3, cc);
			resultSet = prepStmt.executeQuery();
						
			while (resultSet.next()) {
				Transaction transaction = new Transaction();
				transaction.setDay(resultSet.getInt(1));
				transaction.setMonth(resultSet.getInt(2));
				transaction.setYear(resultSet.getInt(3));
				transaction.setBranchCode(resultSet.getInt(4));
				transaction.setType(resultSet.getString(5));
				transaction.setValue(resultSet.getDouble(6));
				transaction.setCustName(resultSet.getString(7));
				transactionsList.add(transaction);
			}	
			return transactionsList;
		}
		
		// functional requirement 4
		public ArrayList<Transaction> getCustTransByDateRange(int ssn, String startDate, String endDate) {
			ArrayList<Transaction> transactionsList = new ArrayList<Transaction>();
			
			try {
				System.out.println("\n\nconnecting to database...");
				myconnection();
				System.out.println("connection successful.\n\n");
				
				String[] splitStartDate = startDate.split("/");
				String[] splitEndDate = endDate.split("/");
				
				prepStmt = connection.prepareStatement(myQueries.custTransactionsBetween);
				prepStmt.setInt(1, Integer.parseInt(splitStartDate[2])); 
				prepStmt.setInt(2, Integer.parseInt(splitStartDate[1]));
				prepStmt.setInt(3, Integer.parseInt(splitStartDate[0])); 
				prepStmt.setInt(4, Integer.parseInt(splitEndDate[2])); 
				prepStmt.setInt(5, Integer.parseInt(splitEndDate[1]));
				prepStmt.setInt(6, Integer.parseInt(splitEndDate[0]));
				prepStmt.setInt(7, ssn);
				resultSet = prepStmt.executeQuery();
				
				while (resultSet.next()) {
					Transaction transaction = new Transaction();
					transaction.setDay(resultSet.getInt(1));
					transaction.setMonth(resultSet.getInt(2));
					transaction.setYear(resultSet.getInt(3));
					transaction.setCardNo(resultSet.getString(4));
					transaction.setBranchCode(resultSet.getInt(5));
					transaction.setType(resultSet.getString(6));
					transaction.setValue(resultSet.getDouble(7));
					transaction.setCustName(resultSet.getString(8));
					transactionsList.add(transaction);
				} 
			} catch (Exception e) {
					e.printStackTrace();
			}
			return transactionsList;
		}

}
