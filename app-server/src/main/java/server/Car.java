package server;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="car")

@XmlType(propOrder = {"id", "make", "model", "year", "transmission", "horsePower"})
public class Car {
	private int id;
	private String make;
	private String model;
	private int year;
	private String transmission;
	private int horsePower;
	
	public Car() {
		this.id = 0;
		this.make = "default-make";
		this.model = "default-model";
		this.year = 0;
		this.transmission = "default-transmission";
		this.horsePower = 0;
	}
	
	public Car(int id, String make, String model, int year, String transmission, int horsePower) {
		this.id = id;
		this.make = make;
		this.model = model;
		this.year = year;
		this.transmission = transmission;
		this.horsePower = horsePower;
	}
	
	public int getId() { return id; }
	public String getMake() { return make; }
	public String getModel() { return model; }
	public int getYear() { return year; }
	public String getTransmission() { return transmission; }
	public int getHorsePower() { return horsePower; }
	
	public void setId(int id) { this.id = id; }
	public void setMake(String make) { this.make = make; }
	public void setModel(String model) { this.model = model; }
	public void setYear(int year) { this.year = year; }
	public void setTransmission(String transmission) { this.transmission = transmission; }
	public void setHorsePower(int horsePower) { this.horsePower = horsePower; }
}
