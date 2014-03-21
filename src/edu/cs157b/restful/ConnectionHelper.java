package edu.cs157b.restful;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionHelper
{
	private String url;
	private static ConnectionHelper instance;

	private ConnectionHelper()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost/hw2?user=root";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			instance = new ConnectionHelper();
		}
		try {
			return DriverManager.getConnection(instance.url);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public static void close(Connection connection)
	{
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}