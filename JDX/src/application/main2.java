package application;

public class main2 {
	
	public static void main(String[] args)
	{
		JDBC connection = new JDBC();
		System.out.println("TEST : " + connection.isMember("1234", "1234"));
	}
}
