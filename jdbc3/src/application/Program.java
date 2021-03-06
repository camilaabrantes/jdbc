package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Connection connection = null;
			PreparedStatement st = null;
			
			try {
				connection = DB.getConnection();
				
				st = connection.prepareStatement(
						"INSERT INTO SELLER"
						+ "(NAME, EMAIL, BIRTHDATE, BASESALARY, DEPARTMENTID)"
						+ "VALUES (?,?,?,?,?)", 
						Statement.RETURN_GENERATED_KEYS
						);
				
				st.setString(1, "Carl Purple");
				st.setString(2, "carl@gmal.com");
				st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
				st.setDouble(4, 3000.00);
				st.setInt(5, 4);
				
				/*
				st = connection.prepareStatement(
						"INSERT INTO DEPARTMENT"
						+ "(NAME)"
						+ "VALUES ('D1'), ('D2')", 
						Statement.RETURN_GENERATED_KEYS
						);
				*/
				int rows = st.executeUpdate();
				
				
				if(rows > 0) {
					ResultSet rs = st.getGeneratedKeys();
					System.out.println("Done! Rows Affeted: " + rows);
					while(rs.next()) {
						System.out.println("Id: " + rs.getInt(1));
					}					
				}else {
					System.out.println("No rows Affeted");
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			catch(ParseException e) {
				e.printStackTrace();
			}
			finally {
				DB.closeStatement(st);
				DB.closeConnection();
			}
	}
}
