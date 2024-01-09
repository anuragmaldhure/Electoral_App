package com.election.utils;

import java.sql.*;

public class DBUtil {
	
	public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/election_webjava";
	public static final String DB_USER = "D3_80316_Anurag";
	public static final String DB_PASSWORD = "80316";
	
	public static Connection connection;

	static {
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	//open cn
		public static Connection openConnection() throws SQLException {
			System.out.println("db cn established....");
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			return connection;
		}

	//close cn
		public static void closeConnection() throws SQLException{
			if(connection != null)
				connection.close();
			System.out.println("db cn closed....");
		}
}
