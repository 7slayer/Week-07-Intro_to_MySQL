package projects.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import projects.exception.DbException;
//Need to show this class for the video and then run the code to show that a connection has been succesfuly made to the DB
public class DbConnection {
	//Creates all the values used to build my connection string
	private static String HOST = "localhost";
	private static String PASSWORD = "password";
	private static int PORT = 3306;
	private static String SCHEMA = "projects";
	private static String USER = "projects";
	
	public static Connection getConnection() {
		
		//formats a connection string using set variables to connect to the database
		String uri = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s&useSSL=false", HOST, PORT, SCHEMA, USER, PASSWORD);
		Connection dbConnection;
		
		try {
			//Will attempt a connection but if it fails will pass the exception to our DbException Class
			dbConnection = DriverManager.getConnection(uri);
			
			System.out.println("Succesfully connected to the databse");
		
		} catch (SQLException e) {
			
			System.out.println("Faild to Connect to data base"); 

			throw new DbException("Unable to get connection at " + uri, e); 
		
		}
		
		return dbConnection;
	}
}
