package dev.sjs;

public class BankAccount {

	enum accountType {CHECKING, SAVINGS};

	private String firstName;
	private String lastName;
	private boolean branch;
	private double balance;
	private accountType accountType;

	public BankAccount(String firstName, String lastName, double balance, accountType type) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance;
		this.accountType = type;
	}

	public double deposit(double amount, boolean branch) {
		if(amount > 0) {
			balance += amount;
		} else {
			System.out.println("You can not deposit a negative amount!");
		}
		return balance;
	}

	public double withdraw(double amount, boolean branch) {
		if (!branch && amount > 500) {
			throw new IllegalArgumentException();
		} else {
			if(amount > 0 && (balance - amount) >= 0) {
				balance -= amount;
			} else {
				String response = amount < 0 ? "You can not withdraw a negative amount!" :
						"You do not have enough money for this transaction!";
				System.out.println(response);
			}
		}
		return balance;
	}

	public double getBalance() {
		return balance;
	}

	public BankAccount.accountType getAccountType() {
		return accountType;
	}
}
