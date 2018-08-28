package drivers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import data_access_objects.CustomerDAO;
import models.Customer;
import models.Transaction;



public class CustomerDriver {
	public static Customer custDetails;
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
		System.out.println("enter your customers SSN");
		Scanner keyboard = new Scanner(System.in);
		int ssn = keyboard.nextInt();
		
		CustomerDAO cDAO = new CustomerDAO();
		custDetails = cDAO.getCustomerDetails(ssn);
		System.out.println("here are the account details of your customer (ssn:" + ssn + ")");
		printCustDetails();
		System.out.println("\n\n\n");
		System.out.println("__________________________________________________________________________________________________");
		System.out.println("------------------------------------------ WELCOME BACK ------------------------------------------\n");
	}
	
	// functional requirement 2
	static String[] fields = {"first_name", "middle_name", "last_name", "credit_card_no", "street_name", "apt_no", "cust_city", "cust_state", "cust_zip", "cust_country", "cust_phone", "cust_email"};		
	public static void printFieldOptions() {
		System.out.println("\nYOU CAN UPDATE ANY OF THE FOLLOWING FIELDS.  PLEASE CHOOSE ONE:\n");
		for (String field :fields) {
			System.out.println("> " + field);
		}
		// add this to every program (get ESC key code => if (keyboard.next() == ESCkeyCode) { MainDriver.main() } )
		System.out.println("\npress ESC to go back  ");
	}
	
	public static void updateCustomerDetails() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException {
		System.out.println("enter your customers SSN");
		Scanner keyboard = new Scanner(System.in);
		int ssn = keyboard.nextInt();
		
		CustomerDAO customer = new CustomerDAO();
		custDetails = customer.getCustomerDetails(ssn);
		System.out.println("\nHERE ARE THE current ACCOUNT DETAILS OF YOUR CUSTOMER (ssn:" + ssn + ")");
		printCustDetails();
		
		printFieldOptions();
		String field = keyboard.next();
		
		// user input validation
		List<String> fieldList = Arrays.asList(fields);
		while (!fieldList.contains(field)) {	
			System.out.println("\n\n**************\nplease enter a valid choice... (all lowercase)\n**************\n");
			printFieldOptions();
			field = keyboard.next();
		}
		
		
		switch (field) {
			case "cust_phone":
				System.out.println("enter the updated value");
				int valueInt = keyboard.nextInt();
				CustomerDAO cDAO = new CustomerDAO();
				Customer updatedDetails = cDAO.updateCustomerPhone(field, valueInt, ssn);
				// updatedDetails.setLastUpdated(now);
				custDetails = updatedDetails;
				System.out.println("\nHERE ARE THE updated ACCOUNT DETAILS OF YOUR CUSTOMER (ssn:" + ssn + ")");
				printCustDetails();
				break;
			default:
				System.out.println("enter the updated value");
				String valueStr = keyboard.next();
				CustomerDAO custDAO = new CustomerDAO();
				Customer detailsUpdate = custDAO.updateCustomerDetails(field, valueStr, ssn);
				// detailsUpdate.setLastUpdated(now);
				custDetails = detailsUpdate;
				System.out.println("\nHERE ARE THE updated ACCOUNT DETAILS OF YOUR CUSTOMER (ssn:" + ssn + ")");
				printCustDetails();
				break;
		}
		// customer.setTimestamp(now)
		System.out.println("\n\n\n");
		System.out.println("__________________________________________________________________________________________________");
		System.out.println("------------------------------------------ WELCOME BACK ------------------------------------------\n");
	}
	
	// functional requirement 3
	public static void getMonthlyBill() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("enter a two digit month");
		int month = keyboard.nextInt();
		System.out.println("enter a four digit year");
		int year = keyboard.nextInt();
		System.out.println("enter the credit card number (no spaces or dashes)");
		String cc = keyboard.next();
		
		CustomerDAO cDAO = new CustomerDAO();
		ArrayList<Transaction> transactions;
		try {
			transactions = cDAO.getMonthlyBill(month, year, cc);
			// getCustDetails()?
			// printCustDetails()?
			System.out.println("\tCOUNT\t DAY/MONTH/YEAR\t\t BRANCH CODE\t TRANSACTION TYPE\t TRANSACTION VALUE ($USD)\t CUSTUMER NAME\t ");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
			
			int count = 1;
			double total = 0;
			for (Transaction transaction :transactions) {
				System.out.println("\t" + count + "\t|\t" + transaction.getDay() + "/" + transaction.getMonth() + "/" + transaction.getYear() + "\t|\t" + transaction.getBranchCode() + "\t|\t" + transaction.getType() + "\t|\t" + "$" + transaction.getValue() + "\t|\t" + transaction.getCustName() + "\t");  
				System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
				count++;
				total+=transaction.getValue();
			}
			if (count == 1) {
				System.out.println("there are no recorded transactions during the month of " + month + "/" + year + " by credit card no. " + cc);
			} else {
				System.out.println("\nthe total bill for the month of " + month + "/" + year + " for credit card no." + cc + " is " + total);
			}
			count = 1;
			System.out.println("\n\n\n");
			System.out.println("__________________________________________________________________________________________________");
			System.out.println("------------------------------------------ WELCOME BACK ------------------------------------------\n");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	// functional requirement 4
	public static void getCustTransByDateRange() throws IOException {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("enter your customer's SSN");
		int ssn = keyboard.nextInt();
		// date validation => startDate[0] cannot be more than 3, startDate[3] can only be 1 or 2, if (startDate[4] == 2) startDate[0] cannot be more than 2
		System.out.println("enter beginning date (DD/MM/YYYY)");
		String startDate = keyboard.next();
		System.out.println("enter ending date (DD/MM/YYYY)");
		String endDate = keyboard.next();
		
		CustomerDAO cDAO = new CustomerDAO();
		ArrayList<Transaction> transactions;
		
		transactions = cDAO.getCustTransByDateRange(ssn, startDate, endDate);
		
		System.out.println("\tCOUNT\t DAY/MONTH/YEAR\t\t BRANCH CODE\t CREDIT_CARD_NO\t\t TRANSACTION TYPE\t TRANSACTION VALUE ($USD)\t CUSTUMER NAME\t ");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
		
		String customer; 
		int count = 1;
		double total = 0;
		for (Transaction transaction :transactions) {
			String date = transaction.getDay() + "/" + transaction.getMonth() + "/" + transaction.getYear();
			// System.out.format("%10d%15s%20d%20s%30s10d20s", count, date, transaction.getBranchCode(), transaction.getCardNo(), transaction.getType(), transaction.getValue(), transaction.getCustName());
			System.out.println("\t" + count + "\t|\t" + transaction.getDay() + "/" + transaction.getMonth() + "/" + transaction.getYear() + "\t|\t" + transaction.getBranchCode() + "\t|\t" + transaction.getCardNo() + "\t|\t" + transaction.getType() + "\t|\t" + "$" + transaction.getValue() + "\t|\t" + transaction.getCustName() + "\t");  
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
			count++;
			total+=transaction.getValue();
		}
		if (count == 1) {
			customer = "your customer";
			System.out.println("there are no recorded transactions during the period of " + startDate + " and " + endDate + " by " + customer + " (SSN: " + ssn + ")");
		} else {
			customer = transactions.get(0).getCustName(); 
			DecimalFormat df = new DecimalFormat("#.##");      
			total = Double.valueOf(df.format(total));
			System.out.println("\nthe total charges by " + customer + " (SSN: " + ssn + ") for the period between " + startDate + " and " + endDate + " amount to " + total);
			
			System.out.println("\n\n\nwould you like to output these transaction details to a csv file? (Y/N)");
			String answer = keyboard.next();
			if (answer == "Y") {
				System.out.println("generating file...");
				try {
					// File file = new File("C:\\Users\\Students\\Desktop\\transactionDetails.csv");
					File file = new File("/users/frankie/desktop/transactionDetails.csv");
					file.createNewFile();
						System.out.println("YOooooooo! the file was created");
					FileWriter writer = new FileWriter(file); 
					
					for (Transaction transaction :transactions) {
						int trCount = 0;
						String date = transaction.getDay() + "/" + transaction.getMonth() + "/" + transaction.getYear();
						writer.write(trCount + "," + date + "," + transaction.getBranchCode() + "," + transaction.getCardNo() + "," + transaction.getType() + "," + "$" + transaction.getValue() + "," + transaction.getCustName() + "\n");  
						trCount++;
					}
					
					writer.write("\nthe total charges by " + customer + " (SSN: " + ssn + ") for the period between " + startDate + " and " + endDate + " amount to " + total + "\n\n\n");
					writer.flush();
					writer.close();
						System.out.println("yuuuurrrr we just wrote some stuff to the file");
					System.out.println("the file (\"transactionDetails.csv\") was outputted to your desktop");
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
	
}








