package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBExample {
	Connection connection = null;
	Statement  stml = null;  //or PreparedStatement

	//String ID, Pw, name, address = null;

	public JDBExample(){
		try {
			
			Class.forName("org.mariadb.jdbc.Driver"); // load drive jDBC connection DB 

			//DB Connect / How to connect with DB : jdbc: DBName://ip address port number , rootID , pw; 
			connection = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3306/management", "root", "1234"); 
			if(connection != null) {System.out.println("데이터 베이스 접속 성공");}		  
			}catch(ClassNotFoundException e){System.err.println("Connection Failed. ");	}
			 catch(SQLException e) {System.out.println("데이터 베이스 접속 실패");}
	}
	
	public void select() {
		try {
			stml = connection.createStatement();
			
			ResultSet rs = stml.executeQuery("select C_ID, C_PW, C_Name, C_Address from members");
			
			System.out.println("ID           PW        Name   Address");
			System.out.println("========================================");

			while(rs.next()) {
				String b_ID    = rs.getString("C_ID");
				String b_Pw    = rs.getString("C_PW");
				String b_Name  = rs.getString("C_Name");
				String b_Address = rs.getString("C_Address");
				
				System.out.printf("%5s     %5s  %5s  %5s \n", b_ID, b_Pw, b_Name, b_Address);
	
			}
		}catch(SQLException e) {System.out.println("Connection Failed. ");}
	}
	
	public void insert(String ID, String Pw, String name, String address)
	{
		try {
			
			stml  = (Statement)connection.createStatement();

			//System.out.printf("%s     %s      %s     %s", ID, Pw, name, address);
			int r = stml.executeUpdate("insert into members " +
                    "(C_ID,C_PW,C_Name,C_Address) value ('" +
                    ID + "','" + Pw + "','" + name + "'," + address + ")" );			
			
			if(r == 1){
				System.out.println("success");
			}else {
				System.out.println("failure");
			}
	
		}catch(SQLException e) {System.out.println("Connection Failed. ");}
	}
	public void delete(String ID)
	{
		try {
			
			stml  = (Statement)connection.createStatement();

			//System.out.printf("%s ", ID);
			int r = stml.executeUpdate("delete from members where C_ID IN ('" +ID +"')");
			
			if(r == 1){
				System.out.println("success");
			}else {
				System.out.println("failure");
			}
	
		}catch(SQLException e) {System.out.println("Connection Failed. ");}
	}

	public void update(String ID, String e_Value)
	{
		try {
			
			stml  = (Statement)connection.createStatement();

			//System.out.printf("%s ", ID);
			int r = stml.executeUpdate("UPDATE members SET C_"+ e_Value +" WHERE C_ID = '"+ID+"'");			
			
			if(r == 1){
				System.out.println("success");
			}else {
				System.out.println("failure");
			}
	
		}catch(SQLException e) {System.out.println("Connection Failed. ");}
	}
	public void search(String s_n, String s_v) {
		try {
			stml = connection.createStatement();
			
			ResultSet rs = stml.executeQuery("select * from members where"+ s_n+ "like '%"+s_v+"%'" );
			
			System.out.println("ID           PW        Name   Address");
			System.out.println("========================================");
			
			if(rs ==null)
				System.out.println("해당 내용을 찾을수 없습니다.");
			
			while(rs.next()) {
				String b_ID    = rs.getString("C_ID");
				String b_Pw    = rs.getString("C_PW");
				String b_Name  = rs.getString("C_Name");
				String b_Address = rs.getString("C_Address");
				
				System.out.printf("%5s     %5s  %5s  %5s \n", b_ID, b_Pw, b_Name, b_Address);
	
			}
		}catch(SQLException e) {System.out.println("Connection Failed. ");}
	}

	
	public static void main(String[] args) {
	  JDBExample jdb = new JDBExample();
	  Scanner sc = new Scanner(System.in);
	  int check_Num = 0;
	  boolean run = true;
	  String ID, Pw, name, address = null;
	  

	  while(run)
	  {
		  System.out.println("1. 출력    2.  삽입   3. 삭제    4. 갱신   5. 찾기   6.종료");
		  check_Num = sc.nextInt();
		  sc.nextLine();
	  
		  if(check_Num == 1){
			  System.out.println("데이터 베이스 정보 출력");
			  jdb.select();			  
		  }else if(check_Num == 2) {
			  System.out.println("데이터 베이스 정보 삽입");
		  	  System.out.print("ID :  ");
		  	  ID = sc.nextLine();
		  	  
		  	  System.out.print("Pw :  ");
		  	  Pw = sc.nextLine();

		  	  System.out.print("Name :  ");
		  	  name = sc.nextLine();
		  	  
		  	  System.out.print("Address :  ");
		  	  address = sc.nextLine();
		  	  
			  jdb.insert(ID, Pw, name, address);
		  }else if(check_Num == 3){
			  System.out.println("데이터 베이스 정보 삭제");
			  System.out.println("삭제할  ID을 입력하세요 : ex KIM ");
			  String d_ID = sc.nextLine();
			  
			  jdb.delete(d_ID);
		  }else if(check_Num ==4){
			  System.out.println("데이터 베이스 정보 갱신");
			  
			  System.out.print("(1/2) 수정 ID : ");
	          String ei = sc.nextLine();
	              
	          System.out.print("(2/2) 수정내용(예 : PW='1234', " +
	                     "Name ='홍바꿈', Address='2') : ");
	          String ev = sc.nextLine();
			
			  jdb.update(ei , ev);
		  }else if(check_Num == 5) {
			  //System.out.println("데이터 베이스 정보 찾기");
			  //jdb.search("id", "");
		  }else if(check_Num == 6){
			  System.out.println("종료 되었습니다.");
			  break;
		  }else {
			  System.out.println("다른 번호를 입력하세요 ");
		  }
	  
	  }
	  sc.close();
	}
}