package dev.sjs;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		boolean menuOpen = true;
		University university = new University();

		while(menuOpen) {
			displayMenu();
			System.out.println("-----");
			String option = scan.nextLine();
			System.out.println("-----");
			if (option.equals("1")) { // add a Professor

				try {
					Professor professor = createProfessor(scan);
					university.addProfessor(professor);
					System.out.println("-----");
					System.out.println(professor.getName() + " was successfully added!");
					System.out.println("-----");

				} catch (Exception e) {
					System.out.println("Invalid Input :" + e.getMessage());
				}
			}
			else if (option.equals("2")) { // add a Student

				try {
					Student student = createStudent(scan);
					university.addStudent(student);
					System.out.println("-----");
					System.out.println(student.getName() + " was successfully added!");
					System.out.println("-----");

				} catch(Exception e) {
					System.out.println("Invalid Input :" + e.getMessage());
				}

			}
			else if (option.equals("3")) { // find a Professor

				try {
					System.out.println("Please choose a valid option:");
					System.out.println("All - all Professors | First Initial (S) - all Professor's starting with the letter | Full-Name - all Professor's with the entered name");
					System.out.println("*****");
					searchForProfessor(university, scan.nextLine());
					System.out.println("*****");
				} catch (Exception e) {
					System.out.println("Invalid Input :" + e.getMessage());
				}
			}
			else if (option.equals("4")) { // find a Student

				try {
					System.out.println("Please choose a valid option:");
					System.out.println("All - all Students | First Initial (S) - all Student's starting with the letter | Full-Name - all Student's with the entered name");
					System.out.println("*****");
					searchForStudent(university, scan.nextLine());
					System.out.println("*****");
				} catch (Exception e) {
					System.out.println("Invalid Input :" + e.getMessage());
				}
			}
			else if (option.equals("5")) { // update Professor (salary)

				try {
					Professor professor = findProfessorById(university, scan);
					if (professor != null) {
						boolean professorInfoOpen = true;
						System.out.println("-----");
						System.out.println("Please choose a valid option:");
						System.out.println("get - get salary | update - update salary | pay - pay salary | earned - total salary paid | exit - return to the main menu");
						System.out.println("-----");

						while (professorInfoOpen) {
							professorInfoOpen = salaryOptions(scan, professor);
						}
					}else{
						System.out.println("Could not find a matching Professor!");
					}
				} catch (Exception e) {
					System.out.println("Invalid Input :" + e.getMessage());
				}
			}

			else if (option.equals("6")) { // update Student (fees)

				try {
					Student student = findStudentById(university, scan);
					if (student != null) {
						boolean studentInfoOpen = true;
						System.out.println("-----");
						System.out.println("Please choose a valid option:");
						System.out.println("total - total fees |  paid - paid fees | pay - pay fees | remaining - remaining fees | exit - return to the main menu");
						System.out.println("-----");

						while (studentInfoOpen) {
							studentInfoOpen = feeOptions(scan, student);
						}
					}else {
						System.out.println("Could not find a matching Student!");
					}
				} catch (Exception e) {
					System.out.println("Invalid Input :" + e.getMessage());
				}
			}
			else if (option.equals("7")) { // update Professor (department)

				try {
					Professor professor = findProfessorById(university, scan);
					if (professor != null) {
						System.out.println("-----");
						System.out.println("Department name: ");
						Department dept = findDepartment(scan.nextLine().toUpperCase());
						professor.updateDepartment(dept);
						System.out.println("Department was updated successfully!");
						System.out.println("-----");

					}else{
						System.out.println("Could not find a matching Professor!");
					}
				} catch (Exception e) {
					System.out.println("Invalid Input :" + e.getMessage());
				}
			}
			else if (option.equals("8")) { // update Student (grade/course)

				try {
					Student student = findStudentById(university, scan);
					if (student != null) {
						System.out.println("-----");
						System.out.println("Please choose a valid option:");
						System.out.println("Grade - update grade | Course - update course");
						System.out.println("-----");

						String choice = scan.nextLine();
						if (choice.equalsIgnoreCase("Grade")) {
							System.out.println("-----");
							System.out.println("Grade: ");
							String newGrade = scan.nextLine();
							student.updateGrade(Integer.parseInt(newGrade));
							System.out.println("Grade was updated successfully!");
							System.out.println("-----");
						}
						else if (choice.equalsIgnoreCase("course")) {
							System.out.println("-----");
							System.out.println("Course: ");
							Department newCourse = findDepartment(scan.nextLine().toUpperCase());
							student.updateCourse(newCourse);
							System.out.println("Course was updated successfully!");
							System.out.println("-----");

						}else{
							System.out.println("Invalid option chosen!");
						}
					}else {
						System.out.println("Could not find a matching Student!");
					}
				} catch (Exception e) {
					System.out.println("Invalid Input :" + e.getMessage());
				}

			}
			else if (option.equals("9")) { // university costs
				System.out.println("The university has Spent a total of £" + university.getTotalMoneySpent());
				System.out.println("-----");

			}
			else if (option.equals("10")) { // university earnings
				System.out.println("The University has earned a total of £" + university.getTotalMoneyEarned());
				System.out.println("-----");
			}
			else if (option.equals("11")) { // university funds
				System.out.println("The university has the current funds of £" + university.getTotalMoney());
				System.out.println("-----");
			}
			else if(option.equalsIgnoreCase("x")) { // Exit
				menuOpen = false;
			}
			else { // Invalid input
				System.out.println("Please enter a valid option (1,2,3,4,5,6,7,8,9,10,11 or x)");
			}
		}

	}

	public static void displayMenu() {
		System.out.println("""
					======================================================================
					                     University Management System
					======================================================================
					 1 - Add A Professor               | 2 - Add A student
					 3 - Display Professors            | 4 - Display Students
					 5 - Professor Salary Options      | 6 - Student Fees Options
					 7 - update Professor dept         | 8 - update Student course/grade
					 9 - Total Money Spent             | 10 - Total Money Earned
					 11 - Available Money              | x - Exit
					 ---------------------------------------------------------------------
					                     Please choose a valid option
					                    (1,2,3,4,5,6,7,8,9,10,11 or x):
					 ---------------------------------------------------------------------
					 """);
	}

	public static Department findDepartment(String department) {
		Department dept = switch (department) {
			case "COMPUTER SCIENCE" -> Department.COMPUTER_SCIENCE;
			case "BUSINESS" -> Department.BUSINESS;
			case "CHEMISTRY" -> Department.CHEMISTRY;
			case "PHYSICS" -> Department.PHYSICS;
			case "LAW" -> Department.LAW;
			default -> Department.NA;
		};

		return dept;

	}

	public static void searchForProfessor(University university, String choice) {
		int count = 0;

		if (choice.equalsIgnoreCase("all")) {
			for (Professor professor : university.getProfessors()) {
				System.out.println(professor);
				count++;
			}
		} else if (choice.length() == 1) {
			for (Professor professor : university.getProfessors()) {
				if (choice.toUpperCase().equals("" + professor.getName().charAt(0))) {
					System.out.println(professor);
					count++;
				}
			}
		} else if (choice.length() > 1) {
			for (Professor professor : university.getProfessors()) {
				if (choice.equalsIgnoreCase(professor.getName())) {
					System.out.println(professor);
					count++;
				}
			}
		}
		if (count == 0) System.out.println("Could not find a matching Professor!");
	}

	public static void searchForStudent(University university, String choice) {
		int count = 0;
		if (choice.equalsIgnoreCase("All")) {
			for (Student student : university.getStudents()) {
				System.out.println(student);
				count++;
			}
		} else if (choice.length() == 1) {
			for (Student student : university.getStudents()) {
				if (choice.toUpperCase().equals("" + student.getName().charAt(0))) {
					System.out.println(student);
					count++;

				}
			}
		} else if (choice.length() > 1) {
			for (Student student : university.getStudents()) {
				if (choice.equalsIgnoreCase(student.getName())) {
					System.out.println(student);
					count++;
				}
			}
		}
		if (count == 0) System.out.println("Could not find a matching student!");
	}

	public static Professor createProfessor(Scanner scan) {
		System.out.println("Please enter the Professor's first name: ");
		String firstName = scan.nextLine();
		System.out.println("Please enter the Professor's last name: ");
		String lastName = scan.nextLine();
		System.out.println("Please enter the Professor's department (COMPUTER_SCIENCE, BUSINESS, CHEMISTRY, PHYSICS or LAW): ");
		Department dept = findDepartment(scan.nextLine().toUpperCase());
		System.out.println("Please enter the Professor's salary: ");
		String salary = scan.nextLine();

		Professor professor = new Professor(firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase(),
				lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase(), dept, Double.parseDouble(salary));

		return professor;
	}

	public static Student createStudent(Scanner scan) {
		System.out.println("Please enter the Student's first name: ");
		String firstName = scan.nextLine();
		System.out.println("Please enter the Student's last name: ");
		String lastName = scan.nextLine();
		System.out.println("Please enter the Student's course title (COMPUTER SCIENCE, BUSINESS, CHEMISTRY, PHYSICS or LAW): ");
		Department dept = findDepartment(scan.nextLine().toUpperCase());
		System.out.println("Please enter the Student's current grade: ");
		String grade = scan.nextLine();

		Student student = new Student(firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase(),
				lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase(), Integer.parseInt(grade), dept);

		return student;
	}
	public static Professor findProfessorById(University university, Scanner scan) {
		System.out.println("Please enter the Professor's ID:");
		String idChoice = scan.nextLine();
		for (Professor professor : university.getProfessors()) {
			if (Integer.parseInt(idChoice) == professor.getId()) {
				System.out.println(professor);
				return professor;

			}
		}
		return null;
	}

	public static Student findStudentById(University university, Scanner scan) {
		System.out.println("Please enter the Student's ID:");
		String idChoice = scan.nextLine();
		for (Student student : university.getStudents()) {
			if (Integer.parseInt(idChoice) == student.getId()) {
				System.out.println(student);
				return student;
			}
		}
		return null;
	}

	public static boolean salaryOptions(Scanner scan, Professor professor) {
		boolean open = true;
		String salaryChoice = scan.nextLine();
		switch (salaryChoice.toLowerCase()) {
			case "get" -> System.out.println("Salary: £" + professor.getSalary());
			case "update" -> {
				System.out.println("Updated Salary amount: ");
				professor.updateSalary(Double.parseDouble(scan.nextLine()));
			}
			case "pay" -> {
				professor.receiveSalary();
				System.out.println("Professor successfully paid!");
			}
			case "earned" -> System.out.println("Salary earned: £" + professor.getSalaryEarned());
			case "exit" -> open = false;
			default -> System.out.println("Invalid option chosen!");
		}
		return open;
	}

	public static boolean feeOptions(Scanner scan, Student student) {
		boolean open = true;
		String feeChoice = scan.nextLine();
		switch (feeChoice.toLowerCase()) {
			case "total" -> System.out.println("Total fees owed: £" + student.getTotalFees());
			case "paid" -> System.out.println("Total fees paid: £" + student.getFeesPaid());
			case "pay" -> {
				System.out.println("Payment amount: ");
				student.payFees(Double.parseDouble(scan.nextLine()));
			}
			case "remaining" -> System.out.println("Remaining fees : £" + student.getRemainingFees());
			case "exit" -> open = false;
			default -> System.out.println("Invalid option chosen!");
		}
		return open;
	}

}
