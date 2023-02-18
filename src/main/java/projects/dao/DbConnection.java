package projects.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import projects.exception.DbException;

public class DbConnection {
	private static String HOST = "localhost";
	private static String PASSWORD = "password";
	private static int PORT = 3306;
	private static String SCHEMA = "projects";
	private static String USER = "projects";
	
	public static Connection getConnection() {
		
		String uri = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s&useSSL=false", HOST, PORT, SCHEMA, USER, PASSWORD);
		Connection dbConnection;
		
		try {
			
			dbConnection = DriverManager.getConnection(uri);
			
			System.out.println("Succesfully connected to the databse");
		
		} catch (SQLException e) {
			
			System.out.println("Faild to Connect to data base"); 

			throw new DbException("Unable to get connection at \" + uri", e); 
		
		}
		
		return dbConnection;
	}
}
