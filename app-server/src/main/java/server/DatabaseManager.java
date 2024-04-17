package server;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {
	private static final String JDBC_DRIVER = "org.hsqldb.jdbcDriver";
    private static final String DATABASE_URL = "jdbc:hsqldb:hsql://localhost/oneDB"; 
    private static final String DATABASE_USER = "SA"; 
    private static final String DATABASE_PASSWORD = "Passw0rd";
    
    private static Connection connection = null;

	private DatabaseManager() { }
	
	public static Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName(JDBC_DRIVER);
				Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
}
