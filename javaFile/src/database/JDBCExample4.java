//JDBC Print 
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample4 {
	public static void main(String[] args) {
		
		Connection connection = null;
		Statement  stml = null; 
		int r;
		Scanner sc = new Scanner(System.in);
		
		try {
			
			Class.forName("org.mariadb.jdbc.Driver"); // load drive jDBC connection DB 

			//DB Connect
			connection = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3306/management", "root", "1234"); 
			//How to connect with DB : jdbc: DBName://ip address port number , rootID , pw; 
			
			//select syntax execute
			stml = connection.createStatement();  
			
			
			 System.out.print("(1/2) 수정 ID : ");
             String ei = sc.nextLine();
              
             System.out.print("(2/2) 수정내용(예 : PW='1234', " +
                     "Name ='홍바꿈', Address='2') : ");
             String ev = sc.nextLine();
              
         
             //r = stml.executeUpdate("UPDATE members SET C_PW ='1234'"); 전체를 바꾸는 것은 쉽게 된다.
			//r = stml.executeUpdate("UPDATE members SET C_PW ='1234' WHERE C_ID = 'Park'"); 하나를 바꾸는것   
			
			r = stml.executeUpdate("UPDATE members SET C_"+ ev +" WHERE C_ID = '"+ei+"'");  
			
             if( r == 0 ){
                 System.out.println("수정 할  내용을 찾을 수 없습니다.");
             }else{
                 System.out.println("수정 되었습니다.");
             }

             sc.close();
		
		}catch(ClassNotFoundException | SQLException e){
			System.err.println("Connection Failed. ");
			
		}
	}
}