package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			connection = DB.getConnection();
			st = connection.createStatement();
			rs = st.executeQuery("Select * from department");
			
			while(rs.next()) {
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();			
		}		

	}

}
