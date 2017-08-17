//JDBC Print 
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample1 {
	public static void main(String[] args) {
		
		Connection connection = null;
		Statement  stml = null; 
		
		try {
			
			Class.forName("org.mariadb.jdbc.Driver"); // load drive jDBC connection DB 

			//DB Connect
			connection = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3306/management", "root", "*****"); 
			//How to connect with DB : jdbc: DBName://ip address port number , rootID , pw; 
			
			//select syntax execute
			stml = connection.createStatement();  

			ResultSet rs = stml.executeQuery("select cloum_ID, cloum_PW, cloum_Name, cloum_Phone from members");
			
				System.out.println("ID      PW          Name         Phone");
				System.out.println("================================================");
				//Execute the result of select syntax 
				while(rs.next()){
					String b_ID    = rs.getString("cloum_ID");
					String b_Pw    = rs.getString("cloum_PW");
					String b_Name  = rs.getString("cloum_Name");
					String b_phone = rs.getString("cloum_Phone");
				
					System.out.printf("%5s  %5s  %5s  %5s \n", b_ID, b_Pw, b_Name, b_phone);	
				}
		
		}catch(ClassNotFoundException | SQLException e){
			System.err.println("Connection Failed. ");
			
		}
	}
}