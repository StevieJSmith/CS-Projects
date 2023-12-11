package dev.sjs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CarMain {


	public static void main(String[] args) {


		Scanner scan = new Scanner(System.in);
		List<Car> cars = new ArrayList<>();
		String[] header;


		try (BufferedReader br = new BufferedReader(new FileReader("src\\dev\\sjs\\vw.csv"))) {
			String line;
			header = br.readLine().split(",");

			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");

				String model = values[0];
				int year = Integer.parseInt(values[1]);
				int price = Integer.parseInt(values[2]);
				String transmission = values[3];
				int mileage = Integer.parseInt(values[4]);
				String fuelType = values[5];
				int tax = Integer.parseInt(values[6]);
				double mpg = Double.parseDouble(values[7]);
				double engineSize = Double.parseDouble(values[8]);

				Car car = new Car(model, year, price, transmission, mileage, fuelType, tax, mpg, engineSize);
				cars.add(car);

			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		boolean isOn = true;

		while(isOn) {

			displayMenu();
			String option = scan.nextLine();

			if (option.equals("1")) {
				System.out.println(Arrays.toString(header));
				System.out.println("-----");
				cars.forEach(System.out::println);
				System.out.println("-----");
			}

			else if (option.equals("2")) {
				System.out.println("Please enter the name of the model:");
				String chosenModel = scan.nextLine();
				System.out.println(chosenModel);
				System.out.println(Arrays.toString(header));
				System.out.println("-----");
				cars.stream()
						.filter(s -> s.getModel().equalsIgnoreCase(" " + chosenModel))
						.map(s -> " -> " + s)
						.forEach(System.out::println);

				System.out.println("-----");


			}

			else if (option.equals("3")) {
				System.out.println(Arrays.toString(header));
				System.out.println("-----");
				advancedSearch(scan, cars);
				System.out.println("-----");
			}

			else if (option.equals("4")) {
				System.out.println(Arrays.toString(header));
				Car cheapestCar = cars.stream()
						.min(Comparator.comparing(Car::getPrice))
						.get();

				System.out.println("-----");
				System.out.println(cheapestCar);
				System.out.println("-----");
			}

			else if (option.equals("5")) {
				System.out.println(Arrays.toString(header));
				Car expensiveCar = cars.stream()
						.max(Comparator.comparing(Car::getPrice))
						.get();

				System.out.println("-----");
				System.out.println(expensiveCar);
				System.out.println("-----");
			}

			else if (option.equalsIgnoreCase("x")) {
				System.out.println("Database shutting down...");
				isOn = false;
			}

			else {
				System.out.println("Please choose a valid option (1,2,3,4 or x)!!!");
			}
		}
	}

	public static void displayMenu() {
		System.out.println("""
					===================================
					  Second Hand Volkswagen Database
					===================================
					1 - All Cars
					2 - Simple Search Using Model
					3 - Advanced Search
					4 - Cheapest car
					5 - Most Expensive car
					x - exit
					-----------------------------------
					     Please choose an option
					         (1,2,3,4,5 or x):
					-----------------------------------
					""");
	}
	public static void advancedSearch(Scanner scan, List<Car> cars) {

		System.out.println("Please enter the name of the model:");
		String chosenModel = scan.nextLine();
		System.out.println("Please enter the Year of the car:");
		String chosenYear = scan.nextLine();
		System.out.println("Please enter the lower price boundary:");
		String chosenLowerPrice = scan.nextLine();
		System.out.println("Please enter the upper price boundary:");
		String chosenUpperPrice = scan.nextLine();
		System.out.println("Please enter the name of the transmission:");
		String chosenTransmission = scan.nextLine();
		System.out.println("Please enter the fuel type:");
		String chosenFuelType = scan.nextLine();
		System.out.println("Please enter the engine size:");
		String chosenEngineSize = scan.nextLine();

		cars.stream()
				.filter(s -> s.getModel().equalsIgnoreCase(" " + chosenModel) &&
						s.getYear() == Integer.parseInt(chosenYear) &&
						s.getPrice() >= Integer.parseInt(chosenLowerPrice) &&
						s.getPrice() <= Integer.parseInt(chosenUpperPrice) &&
						s.getTransmission().equalsIgnoreCase(chosenTransmission) &&
						s.getFuelType().equalsIgnoreCase(chosenFuelType) &&
						s.getEngineSize() == Double.parseDouble(chosenEngineSize))
				.map(s -> " -> " + s)
				.forEach(System.out::println);
	}


}
