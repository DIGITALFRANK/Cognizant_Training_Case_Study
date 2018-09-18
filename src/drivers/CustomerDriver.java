package drivers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;


import data_access_objects.CustomerDAO;
import models.Customer;
import models.Transaction;
import resources.inputValidators;



public class CustomerDriver {
	
	
	
	public static Customer custDetails;
	
	// print customer details function
	public static void printCustDetails() {
		System.out.println("\n--------------------------------------------------------------------" +
							"\n\n" + custDetails.getFirstName() + " "  + custDetails.getLastName() +
							"\n\n" + custDetails.getStreetName() + ", APT " + custDetails.getAptNo() + 
							"\n" + custDetails.getCustCity() + ", " + custDetails.getCustState() + " " + custDetails.getCustZip() + 
							"\n" + custDetails.getCustCountry() +
							"\n\ncredit card on file: " + custDetails.getCreditCardNo() +
							"\nemail: " + custDetails.getCustEmail() +
							"\nphone: " + custDetails.getCustPhone() +
							"\n\nlast updated: " + custDetails.getLastUpdated() +
							"\n\n--------------------------------------------------------------------" );
	}
	
	
	
	
	
	
	

	// functional requirement 1
	public static void getCustomerDetails() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException {		
		System.out.println("=> enter your customer's social security numver");
		Scanner keyboard = new Scanner(System.in);
		String ssnString = keyboard.next();
				
		inputValidators.ssnInputValidator(ssnString, keyboard);
		int ssn = Integer.parseInt(ssnString);
		
		CustomerDAO cDAO = new CustomerDAO();
		custDetails = cDAO.getCustomerDetails(ssn);
		System.out.println("here are the account details of your customer (ssn:" + ssn + ")");
		printCustDetails();
		System.out.println("\n\n\n__________________________________________________________________________________________________");
		System.out.println("------------------------------------------ WELCOME BACK ------------------------------------------\n");
	}
	
	
	
	
	
	
	
	
	// functional requirement 2
	public static String[] fields = {"first_name", "middle_name", "last_name", "credit_card_no", "street_name", "apt_no", "cust_city", "cust_state", "cust_zip", "cust_country", "cust_phone", "cust_email"};		
	public static void printFieldOptions() {
		System.out.println("\nYOU CAN UPDATE ANY OF THE FOLLOWING FIELDS.  PLEASE CHOOSE ONE:\n");
		for (String field :fields) {
			System.out.println("=> " + field);
		}
		// add this to every program (get ESC key code => if (keyboard.next() == ESCkeyCode) { MainDriver.main() } )
		System.out.println("\npress ESC to go back  ");
	}
	
	public static void updateCustomerDetails() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException {
		System.out.println("enter your customers SSN");
		Scanner keyboard = new Scanner(System.in);
		String ssnString = inputValidators.ssnInputValidator(keyboard.next(), keyboard);  // for validation purposes
		int ssn = Integer.parseInt(ssnString);
		
		CustomerDAO customer = new CustomerDAO();
		custDetails = customer.getCustomerDetails(ssn);
		System.out.println("\nHERE ARE THE current ACCOUNT DETAILS OF YOUR CUSTOMER (ssn:" + ssn + ")");
		printCustDetails();
		
		printFieldOptions();
		String field = keyboard.next();
		inputValidators.fieldInputValidator(field, keyboard);
		
		
		switch (field) {
			case "cust_phone":
				System.out.println("=> enter the updated value");
				int valueInt = keyboard.nextInt();
				CustomerDAO cDAO = new CustomerDAO();
				Customer updatedDetails = cDAO.updateCustomerPhone(field, valueInt, ssn);
				custDetails = updatedDetails;
				System.out.println("\nHERE ARE THE updated ACCOUNT DETAILS OF YOUR CUSTOMER (ssn:" + ssn + ")");
				printCustDetails();
				break;
			default:
				System.out.println("=> enter the updated value");
				String valueStr = keyboard.next();
				CustomerDAO custDAO = new CustomerDAO();
				Customer detailsUpdate = custDAO.updateCustomerDetails(field, valueStr, ssn);
				custDetails = detailsUpdate;
				System.out.println("\nHERE ARE THE updated ACCOUNT DETAILS OF YOUR CUSTOMER (ssn:" + ssn + ")");
				printCustDetails();
				break;
		}
		System.out.println("\n\n\n__________________________________________________________________________________________________");
		System.out.println("------------------------------------------ WELCOME BACK ------------------------------------------\n");
	}
	
	
	
	
	
	
	
	
	// functional requirement 3
	public static void getMonthlyBill() {
		Scanner keyboard = new Scanner(System.in);
//		System.out.println("enter a two digit month");
//		int month = keyboard.nextInt();
//		System.out.println("enter a four digit year");
//		int year = keyboard.nextInt();
		
		
		
		System.out.println("=> please enter the month and year (MM/YYYY)"); 
		String mmyyyy = keyboard.next();
		// validate month and year input 
		String[] monthYear = inputValidators.monthYearInputValidator(mmyyyy, keyboard).split("/");
		int month = Integer.parseInt(monthYear[0]);
		int year = Integer.parseInt(monthYear[1]);
			
		
		System.out.println("enter the credit card number (no spaces or dashes)");
		// validate credit card number input
		String cc = inputValidators.ccInputValidator(keyboard.next(), keyboard);
	
		
		CustomerDAO cDAO = new CustomerDAO();
		ArrayList<Transaction> transactions;
		try {
			transactions = cDAO.getMonthlyBill(month, year, cc);
			// getCustDetails()?
			// printCustDetails()?
			System.out.format("%-5s | %-20s | %-12s | %-20s | %-20s | %-20s\n", "COUNT", "DATE (mm/dd/yyyy)", "BRANCH CODE", "TRANSACTION TYPE", "VALUE ($USD)", "CUSTUMER NAME");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
			
			int count = 1;
			double total = 0;
			for (Transaction transaction :transactions) {
				String date = transaction.getMonth() + "/" + transaction.getDay() + "/" + transaction.getYear();
				System.out.format("%-5s | %-20s | %-12s | %-20s | %-20s | %-20s\n", count, date, transaction.getBranchCode(), transaction.getType(), "$" + transaction.getValue(), transaction.getCustName());  
				System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
				count++;
				total+=transaction.getValue();
			}
			if (count == 1) {
				System.out.println("there are no recorded transactions during the month of " + month + "/" + year + " by credit card no. " + cc);
			} else {
				System.out.println("\nthe total bill for the month of " + month + "/" + year + " on credit card no. " + cc + " is $" + total);
				
				
				
				System.out.println("\n\n\n=> would you like to output these transaction details to a csv file? (y/N)");
				String answer = inputValidators.yesNoAnswerValidator(keyboard.next().toLowerCase(), keyboard);
				
				
				// output file do Desktop if user wants
				if (answer.equals("y")) {
					System.out.println("\n\ngenerating file...");
					try {
						// File file = new File("C:\\Users\\Students\\Desktop\\transactionDetails.csv"); // for Platform's desktop
						File file = new File("/users/frankie/desktop/CDW_SAPP_transaction_details.csv"); // for my laptop
						file.createNewFile();
						FileWriter writer = new FileWriter(file); 
						
						int trCount = 1;
						for (Transaction transaction :transactions) {
							String date = transaction.getMonth() + "/" + transaction.getDay() + "/" + transaction.getYear();
							writer.write(trCount + "," + date + "," + transaction.getBranchCode() + "," + transaction.getType() + "," + "$" + transaction.getValue() + "," + transaction.getCustName() + "\n");  
							trCount+=1 ;
						}
						
						writer.write("\nthe total bill for the month of " + month + "/" + year + " on credit card no. " + cc + " is $" + total);
						writer.flush();
						writer.close();
						System.out.println("the file (\"CDW_SAPP_transaction_details.csv\") is now located at your desktop");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			count = 1;
			System.out.println("\n\n\n__________________________________________________________________________________________________");
			System.out.println("------------------------------------------ WELCOME BACK ------------------------------------------\n");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	
	

	
	
	
	// functional requirement 4
	public static void getCustTransByDateRange() throws IOException {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("=> enter your customer's SSN");		
		String ssnString = String.valueOf(keyboard.next()); // for validation purposes 
		int ssn = Integer.parseInt(inputValidators.ssnInputValidator(ssnString, keyboard));
		System.out.println("=> enter beginning date (MM/DD/YYYY)");
		String mmddyyyyStart = inputValidators.fullDateInputValidator(keyboard.next(), keyboard);
		System.out.println("=> enter ending date (MM/DD/YYYY)");
		String mmddyyyyEnd = inputValidators.fullDateInputValidator(keyboard.next(), keyboard);
		
				
		
		CustomerDAO cDAO = new CustomerDAO();
		ArrayList<Transaction> transactions;
		
		transactions = cDAO.getCustTransByDateRange(ssn, mmddyyyyStart, mmddyyyyEnd);
		
		// System.out.println("\tCOUNT\t DAY/MONTH/YEAR\t\t BRANCH CODE\t CREDIT_CARD_NO\t\t TRANSACTION TYPE\t TRANSACTION VALUE ($USD)\t CUSTUMER NAME\t ");
		System.out.format("%-5s | %-20s | %-12s | %-20s | %-20s | %-12s | %-20s\n", "COUNT", "DATE (mm/dd/yyyy)", "BRANCH CODE", "CREDIT_CARD_NO", "TRANSACTION TYPE", "VALUE ($USD)", "CUSTUMER NAME");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		String customer; 
		int count = 1;
		double total = 0;
		for (Transaction transaction :transactions) {
			String date = transaction.getMonth() + "/" + transaction.getDay() + "/" + transaction.getYear();
			System.out.format("%-5s | %-20s | %-12s | %-20s | %-20s | %-12s | %-20s\n", count, date, transaction.getBranchCode(), transaction.getCardNo(), transaction.getType(), transaction.getValue(), transaction.getCustName());
			// System.out.println("\t" + count + "\t|\t" + transaction.getMonth() + "/" + transaction.getDay() + "/" + transaction.getYear() + "\t|\t" + transaction.getBranchCode() + "\t|\t" + transaction.getCardNo() + "\t|\t" + transaction.getType() + "\t|\t" + "$" + transaction.getValue() + "\t|\t" + transaction.getCustName() + "\t");  
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			count++;
			total+=transaction.getValue();
		}
		if (count == 1) {
			customer = "your customer";
			System.out.println("there are no recorded transactions during the period of " + mmddyyyyStart + " and " + mmddyyyyEnd + " by " + customer + " (SSN: " + ssn + ")");
		} else {
			customer = transactions.get(0).getCustName(); 
			DecimalFormat df = new DecimalFormat("#.##");      
			total = Double.valueOf(df.format(total));
			System.out.println("\nthe total charges by " + customer + " (SSN: " + ssn + ") for the period between " + mmddyyyyStart + " and " + mmddyyyyEnd + " amount to $" + total);
			
			System.out.println("\n\n\n=> would you like to output these transaction details to a csv file? (y/N)");
			String answer = inputValidators.yesNoAnswerValidator(keyboard.next().toLowerCase(), keyboard);
	
			
			
			// output file do Desktop if user wants
			if (answer.equals("y")) {
				System.out.println("\n\ngenerating file...");
				try {
					// File file = new File("C:\\Users\\Students\\Desktop\\transactionDetails.csv");
					File file = new File("/users/frankie/desktop/CDW_SAPP_transaction_details.csv");
					file.createNewFile();
					FileWriter writer = new FileWriter(file); 
					
					int trCount = 1;
					for (Transaction transaction :transactions) {
						String date = transaction.getMonth() + "/" + transaction.getDay() + "/" + transaction.getYear();
						writer.write(trCount + "," + date + "," + transaction.getBranchCode() + "," + transaction.getCardNo() + "," + transaction.getType() + "," + "$" + transaction.getValue() + "," + transaction.getCustName() + "\n");  
						trCount+=1 ;
					}
					
					writer.write("\nthe total charges by " + customer + " (SSN: " + ssn + ") for the period between " + mmddyyyyStart + " and " + mmddyyyyEnd + " amount to " + total + "\n\n\n");
					writer.flush();
					writer.close();
					System.out.println("the file (\"CDW_SAPP_transaction_details.csv\") is now located at your desktop");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		count = 1;
		total = 0;
		System.out.println("\n\n\n__________________________________________________________________________________________________");
		System.out.println("------------------------------------------ WELCOME BACK ------------------------------------------\n");
	}
	
	
}
	
	
	
	










