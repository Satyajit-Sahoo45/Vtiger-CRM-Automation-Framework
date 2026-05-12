package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

/**
 * @author Satyajit Sahoo
 */
public class DatabaseUtility {
	
	Connection conn;
	
	/**
	 * This method is used to connect with the DB by passing the url, username and password
	 * 
	 * @param url
	 * @param un
	 * @param password
	 * @throws SQLException
	 */	
	public void getConnectionWithDB(String url, String un, String password) throws SQLException {
		Driver driverObj = new Driver();
		DriverManager.registerDriver(driverObj);
		conn = DriverManager.getConnection(url, un, password);
	}
	
	/**
	 * This method is used to connect to database with credentials
	 * 
	 * @throws SQLException
	 */
	public void getConnectionWithDB() throws SQLException {
		Driver driverObj = new Driver();
		DriverManager.registerDriver(driverObj);
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/adv_project", "root", "root");
	}
	
	/**
	 * This method is used to fetch data from database
	 * 
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public ResultSet fetchDataFromDatabase(String query) throws SQLException {
		Statement stat = conn.createStatement();
		ResultSet result = stat.executeQuery(query);
		return result;
	}
	
	/**
	 * This method used to update the data to database
	 * 
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public int updateDataToDB(String query) throws SQLException {
		Statement stat = conn.createStatement();
		int res = stat.executeUpdate(query);
		return res;
	}
	
	/**
	 * This method is used to disconnect with the database
	 * 
	 * @throws SQLException
	 */
	public void disconnectWithDB() throws SQLException {
		conn.close();
	}

}
