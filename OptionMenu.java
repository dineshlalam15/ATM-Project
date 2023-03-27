package ATM_Project;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class OptionMenu {
	Scanner menuIpnut = new Scanner(System.in);
	DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
	HashMap<Integer, Account> data = new HashMap<Integer, Account>();
	
	public void mainMenu() throws IOException {
		data.put(952141, new Account(952141, 191904, 1000, 5000));
		data.put(123, new Account(123, 123, 20000, 50000));
		boolean end = false;
		while(!end) {
			try {
				System.out.println("\nType 1 to Login");
				System.out.println("\nType 2 Create a new Account");
				int type = menuIpnut.nextInt();
				switch(type) {
				case 1:
					getLogin();
					end = true;
					break;
				case 2:
					createAccount();
					end = true;
					break;
				default:
					System.out.println("\nInvalid Choice");
				}
			} catch(InputMismatchException e) {
				System.out.println("\nInvalid Choice");
				menuIpnut.next();
			}
		}
		System.out.println("\nThank You for using the ATM");
		menuIpnut.close();
		System.exit(0);
	}
	
	public void createAccount() throws IOException {
		int customerNumber = 0;
		boolean end = false;
		while(!end) {
			try {
				System.out.println("\nEnter your Customer Number: ");
				customerNumber = menuIpnut.nextInt();
				Iterator it = data.entrySet().iterator();
				while(it.hasNext()) {
					Map.Entry pair = (Map.Entry)it.next();
					if(!data.containsKey(customerNumber)) {
						end = true;
					} 
				} if(!end){
					System.out.println("\nThe Account Number you entered already exists.");
				}
			} catch(InputMismatchException e) {
				System.out.println("\nInvalid choice.");
				menuIpnut.next();
			}
		}
		System.out.println("\nEnter PIN to be Registered");
		int pinNumber = menuIpnut.nextInt();
		data.put(customerNumber, new Account(customerNumber,pinNumber));
		System.out.println("\nYour Account is Successfully Created");
		System.out.println("\nRedirecting to login page... ");
		getLogin();
	}
	
	public void getLogin() throws IOException {
		int customerNumber = 0;
		int pinNumber = 0;
		boolean end = false;
		while(!end) {
			try {
				System.out.println("\nEnter Your Account Number: ");
				customerNumber = menuIpnut.nextInt();
				System.out.println("\nEnter Your Pin Number: ");
				pinNumber = menuIpnut.nextInt();
				Iterator iterator = data.entrySet().iterator();
				while(iterator.hasNext()) {
					Map.Entry pair = (Map.Entry)iterator.next();
					Account account = (Account) pair.getValue();
					if(data.containsKey(customerNumber) && pinNumber == account.getPinNumber()) {
						getAccountType(account);
						end = true;
						break;
					}
				} 
				if(!end) {
					System.out.println("\nEntered Pin Number or Customer Number is incorrect.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Character(s). Only Numbers");
			}
		}
	}
	
	public void getAccountType(Account account){
		boolean end = false;
		while(!end) {
			try {
				System.out.println("\nSelect the Account you want to access: ");
				System.out.println("Type 1 for Checking's Account");
				System.out.println("Type 2 for Saving's Account");
				System.out.println("Type 3 to Exit");
				System.out.println("\nChoice: ");
				int choice = menuIpnut.nextInt();
				switch (choice) {
				case 1: 
					getChecking(account);
					break;
				case 2:
					getSaving(account);
					break;
				case 3:
					end = true;
					break;
				default:
					System.out.println("\nInvalid Choice.");
				}
			} catch(InputMismatchException e) {
				System.out.println("\nInvalid Choice. ");
				menuIpnut.next();
			}
		}
	}
	
	public void getChecking(Account account) {
		boolean end = false;
		while(!end) {
			try {
			    System.out.println("\nChecking's Account: ");
			    System.out.println("Type 1 to View Balance");
			    System.out.println("Type 2 to Withdraw Funds");
			    System.out.println("Type 3 to Deposit Funds");
			    System.out.println("Type 4 to Transfer FUnds");
			    System.out.println("Type 5 to Exit");
			    System.out.println("\nChoice: ");
			    int type = menuIpnut.nextInt();
			    switch(type) {
			    case 1:
				    System.out.println("\nChecking's Account Balanace: " +moneyFormat.format(account.getCheckingBalance()));
				    break;
			    case 2:
				    account.getCheckingWithdrawInput();
				    break;
			    case 3:
				    account.getCheckingDepositInput();
				    break;
			    case 4:
				    account.getTransferInput("Checkings");
				    break;
			    case 5:
				    end = true;
				    break;
			    default:
				    System.out.println("\nInvalid Choice");
			    }
			} catch(InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				menuIpnut.next();
			}
		}
	}
	
	public void getSaving(Account account) {
		boolean end = false;
		while(!end) {
			try {
			    System.out.println("\nSaving's Account: ");
			    System.out.println("Type 1 to View Balance");
			    System.out.println("Type 2 to Withdraw Funds");
			    System.out.println("Type 3 to Deposit Funds");
			    System.out.println("Type 4 to Transfer FUnds");
			    System.out.println("Type 5 to Exit");
			    System.out.println("\nChoice: ");
			    int type = menuIpnut.nextInt();
			    switch(type) {
			    case 1:
				    System.out.println("\nSaving's Account Balanace: " +moneyFormat.format(account.getSavingBalance()));
				    break;
			    case 2:
				    account.getSavingWithdrawInput();
				    break;
			    case 3:
				    account.getSavingDepositInput();
				    break;
			    case 4:
				    account.getTransferInput("Savings");
				    break;
			    case 5:
				    end = true;
				    break;
			    default:
				    System.out.println("\nInvalid Choice");
			    }
			} catch(InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				menuIpnut.next();
			}
		}
	}
}
