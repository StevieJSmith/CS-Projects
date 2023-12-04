package dev.sjs;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountTestParameterized {
	private BankAccount account;

	@BeforeEach
	public void setup () {
		System.out.println("-----");
		account = new BankAccount("Steven", "Smith", 5000.00, BankAccount.accountType.CHECKING);
		System.out.println("Bank Account created!");

	}

	@AfterAll
	public static void closing() {
		System.out.println("-----");
		System.out.println("All tests are completed!");

	}

		public static Stream<testParameters> testConditionsDeposit () {
			return Stream.of(
					new testParameters(100.00, true, 5100.00),
					new testParameters(200.00, true, 5200.00),
					new testParameters(325.14, true, 5325.14),
					new testParameters(489.33, true, 5489.33),
					new testParameters(1000.00, true, 6000.00)
			);
		}

	public static Stream<testParameters> testConditionsWithdraw_atBranch () {
		return Stream.of(
				new testParameters(3000.00, true, 2000.00),
				new testParameters(200.00, true, 4800.00),
				new testParameters(1789.00, true, 3211.00),
				new testParameters(489.33, true, 4510.67),
				new testParameters(1000.00, true, 4000.00)
		);
	}

	public static Stream<testParameters> testConditionsWithdraw_atNotBranchFail () {
		return Stream.of(
				new testParameters(501.00, false, 0), // null = 0
				new testParameters(1000.00, false, 0),
				new testParameters(2000.00, false, 0),
				new testParameters(500.01, false, 0),
				new testParameters(4999.99, false, 0)
		);
	}

	public static Stream<testParameters> testConditionsWithdraw_atNotBranchSuccess () {
		return Stream.of(
				new testParameters(500.00, false, 4500.00), // null = 0
				new testParameters(1.00, false, 4999.00),
				new testParameters(250.00, false, 4750.00),
				new testParameters(101.19, false, 4898.81),
				new testParameters(99.99, false, 4900.01)
		);
	}

	void executeFunction(testParameters parameters, String type) {
		if (type.toLowerCase().equals("deposit")) {
			account.deposit(parameters.getAmount(), parameters.isBranch());
			assertEquals(parameters.getExpected(), account.getBalance(), 0.1);
		}
		else if (type.toLowerCase().equals("withdraw")) {
			account.withdraw(parameters.getAmount(), parameters.isBranch());
			assertEquals(parameters.getExpected(), account.getBalance(), 0.1);
		}
		else if (type.toLowerCase().equals("exception")) {
			Assertions.assertThrows(IllegalArgumentException.class, () -> account.withdraw(parameters.getAmount(), parameters.isBranch()));
		} else {
			System.out.println("Invalid type input!");
		}
	}

	@ParameterizedTest
	@MethodSource("testConditionsDeposit")
	void deposit (testParameters parameters) {
		executeFunction(parameters, "deposit");
		System.out.println("Test deposit successful!");
	}

	@ParameterizedTest
	@MethodSource("testConditionsWithdraw_atBranch")
	void withdraw_atBranch(testParameters parameters) { // at branch
		executeFunction(parameters, "withdraw");
		System.out.println("Test withdraw_atBranch successful!");
	}

	@ParameterizedTest
	@MethodSource("testConditionsWithdraw_atNotBranchFail")
	void withdraw_atNotBranchFail(testParameters parameters) {
		executeFunction(parameters, "exception");
		System.out.println("Test withdraw_notAtBranchFail successful!");
	}

	@ParameterizedTest
	@MethodSource("testConditionsWithdraw_atNotBranchSuccess")
	void withdraw_atNotBranchSuccess(testParameters parameters) {
		executeFunction(parameters, "withdraw");
		System.out.println("Test withdraw_notAtBranchSuccess successful!");
	}

	static class testParameters {
			private double amount;
			private boolean branch;
			private double expected;

		public testParameters(double amount, boolean branch, double expected) {
			this.amount = amount;
			this.branch = branch;
			this.expected = expected;
		}

		public double getAmount() {
			return amount;
		}

		public boolean isBranch() {
			return branch;
		}

		public double getExpected() {
			return expected;
		}
	}
}
