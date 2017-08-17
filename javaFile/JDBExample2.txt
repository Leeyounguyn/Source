//input
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample2{
	public static void main(String[] args) {
		
		Connection connection = null;
		Statement  stml = null; 
		
		Scanner sc = new Scanner(System.in);
		
		try {
			
			Class.forName("org.mariadb.jdbc.Driver"); // load drive jDBC connection DB 

			//DB Connect
			connection = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3306/management", "root", "****"); 
						
		    System.out.print("ID : ");
		    String ID   = sc.nextLine();
		    
		    System.out.print("PW : ");
		    String Pw   = sc.nextLine();
		    
		    System.out.print("Name : ");
		    String name = sc.nextLine();
		    
		    System.out.print("Phone : ");
		    String phone = sc.nextLine();
		    
		    stml = (Statement)connection.createStatement(); 
            
		    // if insert Success = 1 return 
	        int r = stml.executeUpdate("insert into members " +
                      "(cloum_ID,cloum_PW,cloum_Name,cloum_Phone) value ('" +
                      ID + "','" + Pw + "','" + name + "'," + phone + ")" );
		    
		    if(r == 1){
		    	System.out.println("success");
		    }else {
		      System.out.println("failure");
		    }
            
			sc.close();
		}catch(ClassNotFoundException | SQLException e){
			System.err.println("Connection Failed. ");
		}
	}
}