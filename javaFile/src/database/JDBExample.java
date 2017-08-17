package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBExample {
	public static void main(String[] args) {
		
		Connection connection = null;
		Statement  stml = null; 
		Scanner sc = new Scanner(System.in);
		
		try {
			
			Class.forName("org.mariadb.jdbc.Driver"); //자바와 마리아 DB를 연결하기위해서 사용
			//
			connection = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3306/management", "root", "1234"); 
			stml = connection.createStatement(); // sql 구문
			//입력 삭제 업데이트 
			ResultSet rs = stml.executeQuery("select cloum_ID, cloum_PW, cloum_Name, cloum_Phone from members");
			
				System.out.println(" ID      비밀번호          이름         전화번호");
				System.out.println("==================================");
			
				while(rs.next()){
					String b_ID    = rs.getString("cloum_ID");
					String b_Pw    = rs.getString("cloum_PW");
					String b_Name  = rs.getString("cloum_Name");
					String b_phone = rs.getString("cloum_Phone");
				
					System.out.printf("%5s  %5s  %5s  %5s \n", b_ID, b_Pw, b_Name, b_phone);
	
				}
			    System.out.print("아이디를 입력하세요 : ");
			    String ID   = sc.nextLine();
			    
			    System.out.print("비밀번호를 입력하세요 : ");
			    String Pw   = sc.nextLine();
			    
			    System.out.print("이름을 입력하세요 : ");
			    String name = sc.nextLine();
			    
			    System.out.print("핸드폰 번호를 입력하세요  : ");
			    String phone = sc.nextLine();
			    
			    stml = (Statement)connection.createStatement(); 
	            
			    
		        int r = stml.executeUpdate("insert into members " +
	                      "(cloum_ID,cloum_PW,cloum_Name,cloum_Phone) value ('" +
	                      ID + "','" + Pw + "','" + name + "'," + phone + ")" );
			    
			    if(r == 1){
			    	System.out.println("입력완료");
			    }else {
			      System.out.println("실패");
			    }

			    sc.close();
		}catch(ClassNotFoundException | SQLException e){
			System.err.println("Connection Failed. ");
		}
	}
}