package dev.sjs;

public class Student {
	private static int numberOfStudents = 0;
	private static int id;
	private String name = "%s %s";
	private String firstName;
	private String lastName;
	private Department course;

	private int grade;
	private double feesPaid; // default is set to £0
	private double totalFees; // each year of study equals £9000

	public Student(String firstName, String lastName, int grade, Department course) {
		id = (numberOfStudents++) + 1000000;
		this.firstName = firstName;
		this.lastName = lastName;
		this.grade = grade;
		this.course = course;
		feesPaid = 0;
		totalFees = 9000.00;
	}

	public void updateGrade(int grade) {
		this.grade = grade;
	}

	public void payFees(double payment) {
		if (payment > 0) {
			feesPaid += payment;
			University.updateTotalMoneyEarned(payment);
			System.out.println("FeesPaid updated successfully!");
		} else {
			System.out.println("Invalid value entered!");
		}
	}

	public void updateCourse(Department course) {
		this.course = course;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		name = name.formatted(firstName, lastName);
		return name;
	}

	public int getGrade() {
		return grade;
	}

	public Department getCourse() {
		return course;
	}

	public double getFeesPaid() {
		return feesPaid;
	}

	public double getRemainingFees() {
		return totalFees - feesPaid;
	}

	public double getTotalFees() {
		return totalFees;
	}

	@Override
	public String toString() {
		return "Student's ID: " + getId() + ", " +
				"Student's Name: " + getName() + ", " +
				"course: " + getCourse() + ", " +
				"Grade: " + getGrade();
	}
}
