package ATM_Project;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Account {
	// Important Variables 
	private int customerNumber;
	private int pinNumber;
	private double checkingBalance = 0;
	private double savingBalance = 0;
	
	public Account(int customerNumber, int pinNumber) {
		this.customerNumber = customerNumber;
		this.pinNumber = pinNumber;
	}
	
	public Account(int customerNumber, int pinNumber, int checkingBalance, int savingBalance) {
		this.customerNumber = customerNumber;
		this.pinNumber = pinNumber;
		this.checkingBalance = checkingBalance;
		this.savingBalance = savingBalance;
	}
	
	Scanner inputScanner = new Scanner(System.in);
	DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
	
	public int setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
		return customerNumber;
	}
	
	public int getCustomerNumber() {
		return customerNumber;
	}
	
	public int setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
		return pinNumber;
	}

	public int getPinNumber() {
		return pinNumber;
	}
	
	public double getCheckingBalance() {
		return checkingBalance;
	}
	
	public double getSavingBalance() {
		return savingBalance;
	}
	
	public double calculateCheckingWithdraw(double amount) {
		checkingBalance = checkingBalance - amount;
		return checkingBalance;
	}
	
	public double calculateSavingWithdraw(double amount) {
		savingBalance = savingBalance - amount;
		return savingBalance;
	}
	
	public double calculateCheckingDeposit(double amount) {
		checkingBalance = checkingBalance + amount;
		return checkingBalance;
	}
	
	public double calculateSavingDeposit(double amount) {
		savingBalance = savingBalance + amount;
		return savingBalance;
	}
	
	public void calculateCheckTransfer(double amount) {
		checkingBalance = checkingBalance - amount;
		savingBalance = savingBalance + amount;
	}
	
	public void calculateSavingTransfer(double amount) {
		savingBalance = savingBalance - amount;
		checkingBalance = checkingBalance + amount;
	}
	
	public void getCheckingWithdrawInput() {
		boolean end = false;
		while(!end) {
			try {
				System.out.println("\nCurrent Checking's Account Balance: " +moneyFormat.format(checkingBalance));
				System.out.println("\nEnter the amount to withdraw: ");
				double amount = inputScanner.nextDouble();
				if(checkingBalance - amount >= 0 && amount >= 0) {
					calculateCheckingWithdraw(amount);
					System.out.println("\nChecking Checkings Account Balance: " +moneyFormat.format(checkingBalance));
					end = true;
				} else {
					System.out.println("\nBalance cannot be negative.");
				}
			} catch(InputMismatchException e){
				System.out.println("\nInvalid Choice");
				inputScanner.next();
			}
		}
	}
	
	public void getSavingWithdrawInput() {
		boolean end = false;
		while(!end) {
			try {
				System.out.println("\nCurrent Savings Account Balance: " +moneyFormat.format(savingBalance));
				System.out.println("\nEnter the amount to withdraw: ");
				double amount = inputScanner.nextDouble();
				if(savingBalance - amount >= 0 && amount >= 0) {
					calculateSavingWithdraw(amount);
					System.out.println("\nChecking Saving's Account Balance: " +moneyFormat.format(savingBalance));
					end = true;
				} else {
					System.out.println("\nBalance cannot be negative.");
				}
			} catch(InputMismatchException e){
				System.out.println("\nInvalid Choice");
				inputScanner.next();
			}
		}
	}
	
	public void getCheckingDepositInput() {
		boolean end = false;
		while(!end) {
			try {
				System.out.println("\nBalanace in Checking Account: " +moneyFormat.format(checkingBalance));
				System.out.println("\nEnter the amount to Deposit: ");
				double amount = inputScanner.nextDouble();
				if(checkingBalance + amount >= 0 && amount >= 0) {
					calculateCheckingDeposit(amount);
					System.out.println("\nChecking Checkings Account Balance: " +moneyFormat.format(checkingBalance));
					end = true;
				} else {
					System.out.println("\nBalance cannot be negative.");
				}
			} catch(InputMismatchException e){
				System.out.println("\nInvalid Choice");
				inputScanner.next();
			}
		}
	}
	
	public void getSavingDepositInput() {
		boolean end = false;
		while(!end) {
			try {
				System.out.println("\nBalanace in Saving Account: " +moneyFormat.format(savingBalance));
				System.out.println("\nEnter the amount to Deposit: ");
				double amount = inputScanner.nextDouble();
				if(savingBalance + amount >= 0 && amount >= 0) {
					calculateSavingDeposit(amount);
					System.out.println("\nChecking Checkings Account Balance: " +moneyFormat.format(savingBalance));
					end = true;
				} else {
					System.out.println("\nBalance cannot be negative.");
				}
			} catch(InputMismatchException e){
				System.out.println("\nInvalid Choice");
				inputScanner.next();
			}
		}
		
	}
	
	public void getTransferInput(String AccountType) {
		boolean end = false;
		while(!end) {
			try {
				if(AccountType.equals("Checkings")) {
					System.out.println("Select and account to transfer funds: ");
					System.out.println("1. Savings");
					System.out.println("2. Exit");
					System.out.println("Choice: ");
					int choice = inputScanner.nextInt();
					switch(choice) {
					case 1:
						System.out.println("\nCurrent Checkings Account's Balance: ");
						System.out.println("\nAmount to be deposited in Savings Account: ");
						double amount = inputScanner.nextDouble();
						if((savingBalance + amount) >= 0 && (checkingBalance - amount) <= 0 && amount >= 0) {
							calculateCheckTransfer(amount);
							System.out.println("\nCurrent Saving's Account Balance: " +moneyFormat.format(savingBalance));
							System.out.println("\nCurrent Checking's Account Balance: " +moneyFormat.format(checkingBalance));
							end = true;
						} else {
							System.out.println("\nBalance cannot be negative.");
						}
						break;
					case 2:
						return;
					default:
						System.out.println("\nInvalid Choice.");
						break;
					}
				} else if(AccountType.equals("Savings")){
					System.out.println("Select and account to transfer funds: ");
					System.out.println("1. Checkings");
					System.out.println("2. Exit");
					System.out.println("Choice: ");
					int choice = inputScanner.nextInt();
					switch(choice) {
					case 1:
						System.out.println("\nCurrent Savings Account's Balance: ");
						System.out.println("\nAmount to be deposited in Checkings Account: ");
						double amount = inputScanner.nextDouble();
						if((checkingBalance + amount) >= 0 && (savingBalance - amount) <= 0 && amount >= 0) {
							calculateSavingTransfer(amount);
							System.out.println("\nCurrent Checking's Account Balance: " +moneyFormat.format(savingBalance));
							System.out.println("\nCurrent Saving's Account Balance: " +moneyFormat.format(checkingBalance));
							end = true;
						} else {
							System.out.println("\nBalance cannot be negative.");
						}
						break;
					case 2:
						return;
					default:
						System.out.println("\nInvalid Choice.");
						break;
				}		
			}
		} catch (InputMismatchException e) {
			System.out.println("\nInvalid Choice.");
			inputScanner.next();
		}
	}
		
	}
}
