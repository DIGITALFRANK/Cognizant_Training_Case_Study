package resources;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import drivers.CustomerDriver;

public class inputValidators {
	
	// program option choice validator 
	public static String programOptionValidator(String userInput, Scanner keyboard) {
		while (userInput.length() > 1 || Character.getNumericValue(userInput.charAt(0)) > 9) {
			System.out.println("=> please select a valid option");
			userInput = keyboard.next();
		}
		return userInput;
	}
	
	
	
	// y/N answer input validator 
	public static void yesNoAnswerValidator(String answer, Scanner keyboard) {
		boolean validAnswer = false;
		if (answer.equals("y") || answer.equals("n")) {
			validAnswer = true;
		}
		while (validAnswer == false) {
			System.out.println("please enter \"y\" for Yes or \"N\" for No");
			answer = keyboard.next().toLowerCase();
		}
	}
	

		
	// ssn input validator (calls ssnIntegersValidator)
	public static String ssnInputValidator(String ssnString, Scanner keyboard) {
		while (ssnString.length() != 9) {
			System.out.println("=> please enter a valid social security number");
			ssnString = String.valueOf(keyboard.next());
		}
		if (ssnString.length() == 9 && !ssnIntegersValidator(ssnString)) {
			System.out.println("=> please enter a valid social security number");
			ssnString = String.valueOf(keyboard.next());
			ssnInputValidator(ssnString, keyboard);
		}
		return ssnString;
	}
	
	// SSN integers validator
	public static boolean ssnIntegersValidator(String ssnString) {
		for (int i = 0; i < ssnString.length(); i++) {
			if (Character.getNumericValue(ssnString.charAt(i)) > 9 ) {		// integer input validation using numeric character code
				return false;
			} 
		}
		return true;
	}
	
	
	

	// field input validator		
	public static void fieldInputValidator(String field, Scanner keyboard) {
		List<String> fieldList = Arrays.asList(CustomerDriver.fields);
		while (!fieldList.contains(field)) {	
			System.out.println("\n\n**************\nplease enter a valid choice... (all lowercase)\n**************\n");
			CustomerDriver.printFieldOptions();
			field = keyboard.next();
		}
	}
	
	

	// credit card number input validator (calls ccIntegersValidator)
	public static String ccInputValidator(String ccString, Scanner keyboard) {
		while (ccString.length() != 16) {
			System.out.println("=> please enter a valid credit card number");
			ccString = String.valueOf(keyboard.next());
		}
		if (ccString.length() == 16 && !ccIntegersValidator(ccString)) {
			System.out.println("=> please enter a valid credit card number");
			ccString = String.valueOf(keyboard.next());
			ccInputValidator(ccString, keyboard);
		}
		return ccString;
	}

	// credit card integers validator
	public static boolean ccIntegersValidator(String ccString) {
		for (int i = 0; i < ccString.length(); i++) {
			if (Character.getNumericValue(ccString.charAt(i)) > 9 ) {		// integer input validation using numeric character code
				return false;
			} 
		}
		return true;
	}
	
	
	
	// full date input validator
	public static void fullDateInputValidator(String mmddyyyy, Scanner keyboard) {
		// code similar to ssn validator
	}
	
		
	
	// month and year input validator
	public static String monthYearInputValidator(String mmyyyy, Scanner keyboard) {
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
				System.out.println("=> enter a valid Month and Year, strictly adhere to the format MM/YYYY");
				mmyyyy = keyboard.next();
				
				// no letters
				for (int i = 0; i < mmyyyy.length(); i++) {
					if (i==2) {
						continue;
					}
					if (Character.getNumericValue(mmyyyy.charAt(i)) > 9 ) {
						System.out.println("=> please enter a valid month and year (MM/YYYY)");
						mmyyyy = keyboard.next();
					}
				}
			}
			validMonthYear = true;
		}
		return mmyyyy;	
	}
	
	
	
	
//	public static void monthInputValidator(String month, Scanner keyboard) {
//		// code similar to ssn validator
//	}
//	
//	
//	
//	public static void yearInputValidator(String year, Scanner keyboard) {
//		// code similar to ssn validator
//	}
	
	
	

	// zipcode input validation function
	public static String zipCodeInputValidator(String zipString, Scanner keyboard) {
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
	

	
	
	
	
}













