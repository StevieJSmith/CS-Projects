package dev.sjs;

import java.util.ArrayList;
import java.util.List;

public class University {

	private List<Professor> professors;
	private List<Student> students;
	private static double totalMoneyEarned;
	private static double totalMoneySpent;
	private static double totalMoney;

	public University() {
		this(new ArrayList<>(), new ArrayList<>());
	}

	public University(List<Professor> professors, List<Student> students) {
		this.professors = professors;
		this.students = students;
		totalMoneyEarned = 0;
		totalMoneySpent = 0;
		totalMoney = 0;
	}

	public List<Professor> getProfessors() {
		return professors;
	}

	public List<Student> getStudents() {
		return students;
	}

	public double getTotalMoneyEarned() {
		return totalMoneyEarned;
	}

	public double getTotalMoneySpent() {
		return totalMoneySpent;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void addProfessor(Professor newProfessor) {
		professors.add(newProfessor);
	}

	public void addStudent(Student newStudent) {
		students.add(newStudent);
	}

	public static void updateTotalMoneyEarned(double earnings) {
		if(earnings > 0) {
			totalMoneyEarned += earnings;
			totalMoney += earnings;
		} else {
			System.out.println("Invalid value entered!");
		}
	}

	public static void updateTotalMoneySpent(double expenses) {
		if (expenses > 0) {
			totalMoneySpent += expenses;
			totalMoney -= expenses;
		} else {
			System.out.println("Invalid value entered!");
		}
	}
}
