package dev.sjs;

public class Professor {

	private static int numberOfProfessors = 0;
	private int id;
	private String name = "%s %s";
	private String firstName;
	private String lastName;
	private Department department;
	private double salary;
	private double salaryEarned;


	public Professor(String firstName, String lastName, Department department, double salary) {
		id = numberOfProfessors++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.salary = salary;
		salaryEarned = 0;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		name = name.formatted(firstName, lastName);
		return name;
	}

	public Department getDepartment() {
		return department;
	}

	public double getSalary() {
		return salary;
	}

	public double getSalaryEarned() {
		return salaryEarned;
	}

	public void updateSalary(double salary) {
		this.salary = salary;
	}

	public void updateDepartment(Department department) {
		this.department = department;
	}

	public void receiveSalary() {
		salaryEarned += salary;
		University.updateTotalMoneySpent(salary);
	}

	@Override
	public String toString() {
		return "Professor's ID: " + getId() + ", " +
				"Professor's Name: " + getName() + ", " +
				"Department: " + getDepartment();
	}
}
