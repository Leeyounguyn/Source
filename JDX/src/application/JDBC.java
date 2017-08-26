package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC {
	
		private Connection connection = null;
		private Statement  stml = null; 
		private ResultSet  rs = null;
		
		public JDBC()
		{
			try {
			
				Class.forName("org.mariadb.jdbc.Driver"); // load drive jDBC connection DB 

				connection = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3306/management", "root", "1234"); 

			//How to connect with DB : jdbc: DBName://ip address port number , rootID , pw; 
			
			//select syntax execute
				stml = connection.createStatement();  

				//ResultSet rs = stml.executeQuery("select C_ID, C_PW, C_Name, C_Address from members");
			
			}catch(Exception e){
				System.out.println("데이터 베이스 연결 오류 "+ e.getMessage());
			
			}
		}
		
		public boolean isMember(String memberID, String memberPw)
		{
			try {
				
				//rs = stml.executeQuery("select C_ID, C_PW, C_Name, C_Address from members");
				rs = stml.executeQuery("select * from test where c_ID = '"+ memberID + "'and c_Pw = '"+ memberPw+"'");
				if(rs.next()) {
					return true;
				}
			}
			catch(Exception e) {
				System.out.println("Database Select Error : "+e.getMessage());
			}
			return false;
		}
		
		public int inset(String memberID, String memberPw)
		{
			try {
				
		        //int r = stml.executeUpdate("insert into test (c_ID, c_Pw) value('kim', '3456')");		        
		        int r = stml.executeUpdate("insert into test (c_ID, c_Pw) value('"+memberID+"', '"+memberPw+"')");
				return r;
			}
			catch(Exception e) {
				System.out.println("Database insert Error : "+e.getMessage());
			}
			return 0;
			}
		
}	