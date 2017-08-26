//JDBC Print 
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample3 {
	public static void main(String[] args) {
		
		Connection connection = null;
		Statement  stml = null; 
		Scanner sc = new Scanner(System.in);
		int r = 0;
		
		try {
			
			Class.forName("org.mariadb.jdbc.Driver"); // load drive jDBC connection DB 

			//DB Connect
			connection = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3306/management", "root", "1234"); 
			//How to connect with DB : jdbc: DBName://ip address port number , rootID , pw; 
			
			//select syntax execute
			stml = connection.createStatement();  
			
			System.out.println("삭제할  ID을 입력하세요 : ex KIM ");
			String cn = sc.nextLine();
			r = stml.executeUpdate("delete from members where C_ID IN ('" +cn +"')");
			
			if(r == 0) {
				System.out.println("삭제할 내용을 찾을수 없습니다.");
			}else {
				System.out.println("삭제되었습니다.");
			}
			
			sc.close();
		}catch(ClassNotFoundException | SQLException e){
			System.err.println("Connection Failed. ");
			
		}
	}
}