package user.management.system.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

	private Connection conn;
	private static DatabaseConnector connector;
	
	private DatabaseConnector() {
		try {
			String url = "jdbc:mariadb://localhost:3306/user_db?user=root";
			conn = DriverManager.getConnection(url);
			System.out.println("Connected to user_db!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static DatabaseConnector getInstance() {
		if(connector == null) {
			connector = new DatabaseConnector();
		}
		return connector;
	}	

	public Connection getConnection() {
		return conn;
	}
	
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
