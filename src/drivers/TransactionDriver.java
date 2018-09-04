package drivers;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// import com.mysql.cj.conf.ConnectionUrl.Type;

import data_access_objects.TransactionDAO;
// import data_access_objects.dbconnection_abstract;
import models.Transaction;




public class TransactionDriver {
	
	// functional requirement 1
	public static void getTransactionsByZipCode() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("=> enter a five digit zipcode:");
		String zipString = String.valueOf(keyboard.next()); // for validation purposes 
		int zip = Integer.parseInt(zipCodeInputValidation(zipString, keyboard));
		
		System.out.println("=> enter the month and year (MM/YYYY)"); 
		String mmyyyy = keyboard.next();
		String[] monthYear = validateMonthYear(mmyyyy, keyboard).split("/");
		int month = Integer.parseInt(monthYear[0]);
		int year = Integer.parseInt(monthYear[1]);
		
		TransactionDAO tDAO = new TransactionDAO();
		ArrayList<Transaction> transactions = tDAO.getTransactionsByZipCode(zip, year, month);
		System.out.println("\tCOUNT\t TRANSACTION TYPE\t TRANSACTION VALUE ($USD)\t DAY/MONTH/YEAR\t\t CARD NO.\t CUSTOMER SSN\t BRANCH CODE\t ");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
		
		int count = 1;
		for (Transaction transaction :transactions) {
			System.out.println("\t" + count + "\t|\t" + transaction.getType() + "\t|\t" + "$" + transaction.getValue() + "\t|\t" + transaction.getDay() + "/" + transaction.getMonth() + "/" + transaction.getYear() + "\t|\t" + transaction.getCardNo() + "\t|\t" + transaction.getSSN() + "\t|\t" + transaction.getBranchCode() + "\t");  
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
			count++;
		}
		if (count == 1) {
			System.out.println("there are no recorded transactions by customers living in zipcode " + zip + " during " + month + "/" + year);
		}
		count = 1;
		System.out.println("\n\n\n");
		System.out.println("__________________________________________________________________________________________________");
		System.out.println("------------------------------------------ WELCOME BACK ------------------------------------------\n");
	}
	
	// zipcode input validation function
	public static String zipCodeInputValidation(String zipString, Scanner keyboard) {
		boolean validZip = false;
		while (validZip == false) {
			//no letters
			for (int i = 0; i < zipString.length(); i++) {
				if (Character.getNumericValue(zipString.charAt(i)) > 9 ) {		// integer input validation using numeric character code
					System.out.println("=> please enter a valid 5-digit zipcode");
					zipString = String.valueOf(keyboard.next());
				}
			}
			
			while (zipString.length() != 5) {
				System.out.println("=> please enter a valid 5-digit zipcode:");
				zipString = String.valueOf(keyboard.next());
				//no letters
				for (int i = 0; i < zipString.length(); i++) {
					if (Character.getNumericValue(zipString.charAt(i)) > 9 ) {		// integer input validation using numeric character code
						System.out.println("=> please enter a valid 5-digit zipcode");
						zipString = String.valueOf(keyboard.next());
					}
				}
			}
			validZip = true;
		}
		return zipString;
	}
	
	// month and year input validation
	public static String validateMonthYear(String mmyyyy, Scanner keyboard) {
		boolean validMonthYear = false;
		while (validMonthYear == false) {
			// no letters
			for (int i = 0; i < mmyyyy.length(); i++) {
				if (i==2) {
					continue;
				}
				if (Character.getNumericValue(mmyyyy.charAt(i)) > 9 ) {
					System.out.println("please enter a valid month and year (MM/YYYY)");
					mmyyyy = keyboard.next();
				}
			}
			
			while (mmyyyy.length() != 7 || mmyyyy.charAt(2) != '/' || Character.getNumericValue(mmyyyy.charAt(0)) > 1 || Character.getNumericValue(mmyyyy.charAt(0)) == 1 && Character.getNumericValue(mmyyyy.charAt(1)) > 2) {
				System.out.println("enter a valid Month and Year, strictly adhere to the format MM/YYYY");
				mmyyyy = keyboard.next();
				
				// no letters
				for (int i = 0; i < mmyyyy.length(); i++) {
					if (i==2) {
						continue;
					}
					if (Character.getNumericValue(mmyyyy.charAt(i)) > 9 ) {
						System.out.println("please enter a valid month and year (MM/YYYY)");
						mmyyyy = keyboard.next();
					}
				}
			}
			validMonthYear = true;
		}
		return mmyyyy;	
	}
	
	
	
	
	
	
	// functional requirement 2
	static String[] transTypes = {"Education", "Entertainment", "Grocery", "Gas", "Bills", "Test", "Healthcare"};
	public static void printTransTypeOptions() {
		System.out.println("CHOOSE ONE OF THE FOLLOWING TRANSACTION TYPES:\n");
		for (String type :transTypes) {
			System.out.println("> " + type);
		}
		
		// add this to every program (get ESC key code => if (keyboard.next() == ESCkeyCode) { MainDriver.main() } )
		System.out.println("\npress ESC to go back  ");
	}
	
	public static void getTotalByType() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException {
		
		printTransTypeOptions();
		
		Scanner keyboard = new Scanner(System.in);
		String type = keyboard.nextLine(); // find a way to run this w/o being case-sensitive   // .toLowerCase() duh!!
		
		List<String> choiceList = Arrays.asList(transTypes);
		while (!choiceList.contains(type)) {	// make sure you can check regardless of capitalization or case
			System.out.println("\n\n**************\nplease enter a valid choice... (capitalization matters)\n**************\n");
			printTransTypeOptions();
			type = keyboard.next();
		}
				
		TransactionDAO tDAO = new TransactionDAO();
		Transaction mytransactions = tDAO.getTotalByType(type);
		System.out.println("\n\nTHE TOTAL # OF TRANSACTIONS OF TYPE \"" + type + "\" IS: " + mytransactions.getCount());
		System.out.println("THE TOTAL $(USD) VALUE OF THESE TRANSACTIONS IS: $" + mytransactions.getValue() + "\n\n\n");
		System.out.println("__________________________________________________________________________________________________");
		System.out.println("------------------------------------------ WELCOME BACK ------------------------------------------\n");
	}
	
	
	
	
	
	
	
	// functional requirement 3
	public static void getTotalByState() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("enter a two letter state");
		String state = keyboard.nextLine();
		
		TransactionDAO tDAO = new TransactionDAO();
		try {
			Transaction mytransactions = tDAO.getTotalByState(state);
			System.out.println("\n\nTHE TOTAL # OF TRANSACTIONS FROM BRANCHES IN " + state + " IS: " + mytransactions.getCount());
			System.out.println("THE TOTAL $(USD) VALUE OF THESE TRANSACTIONS IS: $" + mytransactions.getValue() + "\n\n\n");
			System.out.println("__________________________________________________________________________________________________");
			System.out.println("------------------------------------------ WELCOME BACK ------------------------------------------\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}






