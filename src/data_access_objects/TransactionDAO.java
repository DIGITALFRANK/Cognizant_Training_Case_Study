package data_access_objects;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.conf.ConnectionUrl.Type;

import drivers.TransactionDriver;
import models.Transaction;
import resources.myQueries;

public class TransactionDAO extends dbconnection_abstract {
	// functional requirement 1
	public ArrayList<Transaction> getTransactionsByZipCode(int zip, int year, int month) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {		
		ArrayList<Transaction> transactionsList = new ArrayList<Transaction>();
		
		try {
			System.out.println("\n\nconnecting to database...");
			myconnection();
			System.out.println("connection successful.\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		prepStmt = connection.prepareStatement(myQueries.transactionsByZipCode);
		prepStmt.setInt(1, zip);
		prepStmt.setInt(2, year);
		prepStmt.setInt(3, month);
		resultSet = prepStmt.executeQuery();
		
		while (resultSet.next()) {
			Transaction transaction = new Transaction();
			transaction.setType(resultSet.getString(1));
			transaction.setValue(resultSet.getDouble(2));
			transaction.setDay(resultSet.getInt(3));
			transaction.setMonth(resultSet.getInt(4));
			transaction.setYear(resultSet.getInt(5));
			transaction.setCardNo(resultSet.getString(6));
			transaction.setSSN(resultSet.getInt(7));
			transaction.setBranchCode(resultSet.getInt(8));
			transactionsList.add(transaction);
		}	
		return transactionsList;
	}
	
	
	// functional requirement 2
	public Transaction getTotalByType(String type) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {

		try {
			System.out.println("\n\nconnecting to database...");
			myconnection();
			System.out.println("connection successful.\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		prepStmt = connection.prepareStatement(myQueries.totalByType);
		prepStmt.setString(1, type);
		resultSet = prepStmt.executeQuery();
		Transaction transaction = new Transaction();
		
		if(resultSet.next()) {
			transaction.setValue(resultSet.getDouble(1));
			transaction.setCount(resultSet.getInt(2));
			return transaction;
		}
		return null;
	}
	
	
	// functional requirement 3
	public Transaction getTotalByState(String state) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		try {
			System.out.println("\n\nconnecting to database...");
			myconnection();
			System.out.println("connection successful.\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		prepStmt = connection.prepareStatement(myQueries.totalByState);
		prepStmt.setString(1, state);
		resultSet = prepStmt.executeQuery();
		Transaction transaction = new Transaction();
		
		if(resultSet.next()) {
			transaction.setValue(resultSet.getDouble(1));
			transaction.setCount(resultSet.getInt(2));
			return transaction;
		}
		return null;
	}

}






