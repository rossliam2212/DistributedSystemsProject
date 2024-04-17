package server;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarDao implements AutoCloseable {
    /**
	 * Retrieves all cars from the database.
	 * @return A list of all cars.
	 */
	public List<Car> getCars() {
		List<Car> cars = new ArrayList<>();
		
		try (Connection connection = DatabaseManager.getConnection()) {
			String sql = "SELECT c.id, m.manufacturer, c.model, c.year, c.transmission, c.horsepower FROM cars c JOIN manufacturers m ON c.manufacturer_id = m.id;";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String make = resultSet.getString("manufacturer");
				String model = resultSet.getString("model");
				int year = resultSet.getInt("year");
				String transmission = resultSet.getString("transmission");
				int horsePower = resultSet.getInt("horsepower");
				
				var c = new Car(id, make, model, year, transmission, horsePower);
				cars.add(c);
			}
		} catch (final Exception e) {
			System.err.println("[ERROR] Failed to execute SQL query.");
			e.printStackTrace();
		} 
		return cars;
	}
	
	/**
	 * Retrieves the car with the given ID from the database.
	 * @param id
	 * 			The ID of the car to retrieve.
	 * @return The car with the given ID.
	 */
	public Car getCarByID(int id) {
		Car car = new Car();
		
		try (Connection connection = DatabaseManager.getConnection()) {
			String idStr = Integer.toString(id);
			String sql = "SELECT c.id, m.manufacturer, c.model, c.year, c.transmission, c.horsepower FROM cars c JOIN manufacturers m ON c.manufacturer_id = m.id WHERE c.id = " + idStr + ";";
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			if (resultSet.next()) {
				int carID = resultSet.getInt("id");
				String make = resultSet.getString("manufacturer");
				String model = resultSet.getString("model");
				int year = resultSet.getInt("year");
				String transmission = resultSet.getString("transmission");
				int horsePower = resultSet.getInt("horsepower");
				
				car.setId(carID);
				car.setMake(make);
				car.setModel(model);
				car.setYear(year);
				car.setTransmission(transmission);
				car.setHorsePower(horsePower);
			}
		} catch (final Exception e) {
			System.err.println("[ERROR] Failed to execute SQL query.");
			e.printStackTrace();
		} 
		return car;
	}

	
	@Override
	public void close() throws Exception { }
	
	/**
	 * Retrieves all cars with the given make from the database.
	 * @param make
	 * 			The make of the cars to retrieve.
	 * @return A list of all cars with the given make.
	 */
//	public List<Car> getCarsByMake(String make) {
//		List<Car> cars = new ArrayList<>();
//		
//		try {
//			String sql = "SELECT c.id, m.manufacturer, c.model, c.year, c.transmission, c.horsepower FROM cars c JOIN manufacturers m ON c.manufacturer_id = m.id WHERE m.manufacturer = '" + make + "';";
//			resultSet = statement.executeQuery(sql);
//			
//			while (resultSet.next()) {
//				int id = resultSet.getInt("id");
//				String carMake = resultSet.getString("manufacturer");
//				String model = resultSet.getString("model");
//				int year = resultSet.getInt("year");
//				String transmission = resultSet.getString("transmission");
//				int horsePower = resultSet.getInt("horsepower");
//				
//				var c = new Car(id, carMake, model, year, transmission, horsePower);
//				cars.add(c);
//			}
//		} catch (final SQLException e) {
//			System.err.println("[ERROR] Failed to initialize connection to HSQL database.");
//			e.printStackTrace();
//		} finally {
//			try {
//				resultSet.close();
//			} catch (final Exception e) {
//				System.err.println("[ERROR] Failed to close resultSet.");
//				e.printStackTrace();
//			}
//		}
//		return cars;
//		return Collections.emptyList();
//	}
	
//	/**
//	 * Retrieves all cars with a given model from the database.
//	 * @param model
//	 * 			The model of the cars to retrieve.
//	 * @return A list of all cars with the given model.
//	 */
//	public List<Car> getCarsByModel(String model) {
//		Connection connection = null;
//		Statement statement = null;
//		ResultSet resultSet = null;
//		List<Car> cars = new ArrayList<>();
//		
//		try {
//			Class.forName(JDBC_DRIVER);
//			connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
//			statement = connection.createStatement();
//			String sql = "SELECT c.id, m.manufacturer, c.model, c.year, c.transmission, c.horsepower FROM cars c JOIN manufacturers m ON c.manufacturer_id = m.id WHERE c.model = '" + model + "';";
//			resultSet = statement.executeQuery(sql);
//			
//			while (resultSet.next()) {
//				int id = resultSet.getInt("id");
//				String make = resultSet.getString("manufacturer");
//				String carModel = resultSet.getString("model");
//				int year = resultSet.getInt("year");
//				String transmission = resultSet.getString("transmission");
//				int horsePower = resultSet.getInt("horsepower");
//				
//				var c = new Car(id, make, carModel, year, transmission, horsePower);
//				cars.add(c);
//			}
//		} catch (final Exception e) {
//			System.err.println("[ERROR] Failed to initialize connection to HSQL database.");
//			e.printStackTrace();
//		} finally {
//			try {
//				resultSet.close();
//				statement.close();
//				connection.close();
//			} catch (final Exception e) {
//				System.err.println("[ERROR] Failed to close HSQL database connections.");
//				e.printStackTrace();
//			}
//		}
//		return cars;
//	}
//	
//	/**
//	 * Retrieves all cars with a given year from the database.
//	 * @param year
//	 * 			The year of the cars to retrieve.
//	 * @return A list of all cars with the given year.
//	 */
//	public List<Car> getCarsByYear(int year) {
//		Connection connection = null;
//		Statement statement = null;
//		ResultSet resultSet = null;
//		List<Car> cars = new ArrayList<>();
//		
//		try {
//			Class.forName(JDBC_DRIVER);
//			connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
//			statement = connection.createStatement();
//			String sql = "SELECT c.id, m.manufacturer, c.model, c.year, c.transmission, c.horsepower FROM cars c JOIN manufacturers m ON c.manufacturer_id = m.id WHERE c.year = " + Integer.toString(year) + ";";
//			resultSet = statement.executeQuery(sql);
//			
//			while (resultSet.next()) {
//				int id = resultSet.getInt("id");
//				String make = resultSet.getString("manufacturer");
//				String model = resultSet.getString("model");
//				int carYear = resultSet.getInt("year");
//				String transmission = resultSet.getString("transmission");
//				int horsePower = resultSet.getInt("horsepower");
//				
//				var c = new Car(id, make, model, carYear, transmission, horsePower);
//				cars.add(c);
//			}
//		} catch (final Exception e) {
//			System.err.println("[ERROR] Failed to initialize connection to HSQL database.");
//			e.printStackTrace();
//		} finally {
//			try {
//				resultSet.close();
//				statement.close();
//				connection.close();
//			} catch (final Exception e) {
//				System.err.println("[ERROR] Failed to close HSQL database connections.");
//				e.printStackTrace();
//			}
//		}
//		
//		return cars;
//	}
//	
//	/**
//	 * Retrieves all cars with a given transmission from the database.
//	 * @param transmission
//	 * 			The transmission of the cars to retrieve.
//	 * @return A list of all cars with the given transmission.
//	 */
//	public List<Car> getCarsByTransmission(String transmission) {
//		Connection connection = null;
//		Statement statement = null;
//		ResultSet resultSet = null;
//		List<Car> cars = new ArrayList<>();
//		
//		try {
//			Class.forName(JDBC_DRIVER);
//			connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
//			statement = connection.createStatement();
//			String sql = "SELECT c.id, m.manufacturer, c.model, c.year, c.transmission, c.horsepower FROM cars c JOIN manufacturers m ON c.manufacturer_id = m.id WHERE c.transmission = '" + transmission + "';";
//			resultSet = statement.executeQuery(sql);
//			
//			while (resultSet.next()) {
//				int id = resultSet.getInt("id");
//				String make = resultSet.getString("manufacturer");
//				String model = resultSet.getString("model");
//				int year = resultSet.getInt("year");
//				String carTransmission = resultSet.getString("transmission");
//				int horsePower = resultSet.getInt("horsepower");
//				
//				var c = new Car(id, make, model, year, carTransmission, horsePower);
//				cars.add(c);
//			}
//		} catch (final Exception e) {
//			System.err.println("[ERROR] Failed to initialize connection to HSQL database.");
//			e.printStackTrace();
//		} finally {
//			try {
//				resultSet.close();
//				statement.close();
//				connection.close();
//			} catch (final Exception e) {
//				System.err.println("[ERROR] Failed to close HSQL database connections.");
//				e.printStackTrace();
//			}
//		}
//		return cars;
//	}
}