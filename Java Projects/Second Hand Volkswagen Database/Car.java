package dev.sjs;

public class Car {

	private String model;
	private int year;
	private int price;
	private String transmission;
	private int mileage;
	private String fuelType;
	private int tax;
	private double mpg;
	private double engineSize;

	public Car(String model, int year, int price, String transmission, int mileage,
			   String fuelType, int tax, double mpg, double engineSize) {
		this.model = model;
		this.year = year;
		this.price = price;
		this.transmission = transmission;
		this.mileage = mileage;
		this.fuelType = fuelType;
		this.tax = tax;
		this.mpg = mpg;
		this.engineSize = engineSize;
	}

	public String getModel() {
		return model;
	}

	public int getYear() {
		return year;
	}

	public int getPrice() {
		return price;
	}

	public String getTransmission() {
		return transmission;
	}

	public int getMileage() {
		return mileage;
	}

	public String getFuelType() {
		return fuelType;
	}

	public int getTax() {
		return tax;
	}

	public double getMpg() {
		return mpg;
	}

	public double getEngineSize() {
		return engineSize;
	}

	@Override
	public String toString() {
		return "[" + model + ", " + year + ", " + price + ", " + transmission + ", " +
				mileage + ", " + fuelType + ", " + tax + ", " + mpg + ", " + engineSize + "]";
	}
}
