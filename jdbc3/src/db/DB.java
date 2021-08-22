package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	
	public static Connection conn = null;
	
	public static Connection getConnection() {
		try {
			if(conn == null) {
				Properties properties = loadProperties();
				String url = properties.getProperty("dburl");
				conn = DriverManager.getConnection(url,properties);
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		return conn;
	}
	
	public static void closeConnection() {
		try {
			conn.close();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	private static Properties loadProperties() {
		try(FileInputStream fis = new FileInputStream("db.properties")){
			Properties properties = new Properties();
			properties.load(fis);
			return properties;
		}
		catch(IOException e) {
			throw new DbException(e.getMessage());
		}		
	}
	
	public static void closeStatement(Statement st) {
		try {
			st.close();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		try {
			rs.close();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
}
