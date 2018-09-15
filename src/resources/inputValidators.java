package resources;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import drivers.CustomerDriver;

public class inputValidators {
	
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
	
	
	
	// ssn input validator
	public static String ssnInputValidator(String ssnString, Scanner keyboard) {
		boolean validSSN = false;
		while (validSSN == false) {
			integersValidator(ssnString, keyboard);
			ssnLengthValidator(ssnString, keyboard);
			validSSN = true;
		}
		return ssnString;
	}
	
	// SSN integers validator
	public static void integersValidator(String ssnString, Scanner keyboard) {
		for (int i = 0; i < ssnString.length(); i++) {
			if (Character.getNumericValue(ssnString.charAt(i)) > 9 ) {		// integer input validation using numeric character code
				System.out.println("=> the length of ssnString is " + ssnString.length() + " but this is at the letter validation level"); // * I'm checking
				System.out.println("=> please enter a valid 9-digit social security number");
				ssnString = String.valueOf(keyboard.next());
				ssnInputValidator(ssnString, keyboard);
			} 
		}
	}
	
	// SSN length validator
	public static void ssnLengthValidator(String ssnString, Scanner keyboard) {
		if (ssnString.length() < 9) {
			System.out.println("=> the length of ssnString is " + ssnString.length()); // * I'm checking
			System.out.println("=> your ssn number is too short\n=> enter a 9-digit social security number");	
			ssnString = String.valueOf(keyboard.next());
			ssnInputValidator(ssnString, keyboard);
		} else if (ssnString.length() > 9) {
			System.out.println("=> the length of ssnString is " + ssnString.length()); // * I'm checking
			System.out.println("=> your ssn number is too long\n=> enter a 9-digit social security number");	
			ssnString = String.valueOf(keyboard.next());
			ssnInputValidator(ssnString, keyboard);
		} 
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
	
	
	
	// credit card number input validator
	public static void ccInputValidator(String cc, Scanner keyboard) {
		// code similar to ssn validator
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













