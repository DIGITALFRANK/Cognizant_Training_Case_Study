package drivers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


import data_access_objects.TransactionDAO;
import models.Transaction;
import resources.inputValidators;






public class TransactionDriver {
	
	// functional requirement 1
	public static void getTransactionsByZipCode() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("=> enter a five digit zipcode:");
		String zipString = String.valueOf(keyboard.next()); // for validation purposes 
		int zip = Integer.parseInt(inputValidators.zipCodeInputValidator(zipString, keyboard));
		
		System.out.println("=> enter the month and year (MM/YYYY)"); 
		String mmyyyy = keyboard.next();
		String[] monthYear = inputValidators.monthYearInputValidator(mmyyyy, keyboard).split("/");

		int month = Integer.parseInt(monthYear[0]);
		int year = Integer.parseInt(monthYear[1]);
		
		TransactionDAO tDAO = new TransactionDAO();
		ArrayList<Transaction> transactions = tDAO.getTransactionsByZipCode(zip, year, month);
		System.out.format("%-5s | %-20s | %-12s | %-20s | %-20s | %-12s | %-20s\n", "COUNT", "TRANSACTION TYPE", "VALUE ($USD)", "DATE (mm/dd/yyyy)", "CREDIT CARD NO.", "CUSTOMER SSN", "BRANCH CODE");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		int count = 1;
		double total = 0;
		for (Transaction transaction :transactions) {
			String date = transaction.getMonth() + "/" + transaction.getDay() + "/" + transaction.getYear();
			System.out.format("%-5s | %-20s | %-12s | %-20s | %-20s | %-12s | %-20s\n", count, transaction.getType(), "$" + transaction.getValue(), date, transaction.getCardNo(), transaction.getSSN(), transaction.getBranchCode());  
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			count++;
			total+=transaction.getValue();
		}
		if (count == 1) {
			System.out.println("there are no recorded transactions by customers living in zipcode " + zip + " during " + month + "/" + year);
		} else {
			System.out.println("\nthe total value of transactions by customers living in zipcoe " + zip + " during " + month + "/" + year + "amounts to $" + total);
			
			
			System.out.println("\n\n\n=> would you like to output these transaction details to a csv file? (y/N)");
			String answer = inputValidators.yesNoAnswerValidator(keyboard.next().toLowerCase(), keyboard);
			
			
			// output file do Desktop if user wants
			if (answer.equals("y")) {
				System.out.println("\n\ngenerating file...");
				try {
					// File file = new File("C:\\Users\\Students\\Desktop\\transactionDetails.csv"); // for Platform's desktops
					File file = new File("/users/frankie/desktop/CDW_SAPP_transaction_details.csv"); // for my laptop
					file.createNewFile();
					FileWriter writer = new FileWriter(file); 
					
					int trCount = 1;
					for (Transaction transaction :transactions) {
						String date = transaction.getMonth() + "/" + transaction.getDay() + "/" + transaction.getYear();
						writer.write(trCount + "," + transaction.getType() + "," + "$" + transaction.getValue() + date + "," + transaction.getCardNo() + "," + transaction.getSSN() + "," + transaction.getBranchCode() + "\n");  
						trCount+=1 ;
					}
					
					writer.write("\nthe total value of transactions by customers living in zipcoe " + zip + " during " + month + "/" + year + "amounts to $" + total);
					writer.flush();
					writer.close();
					System.out.println("the file (\"CDW_SAPP_transaction_details.csv\") is now located at your desktop");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		count = 1;
		System.out.println("\n\n\n");
		System.out.println("__________________________________________________________________________________________________");
		System.out.println("------------------------------------------ WELCOME BACK ------------------------------------------\n");
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






