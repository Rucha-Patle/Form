package DBconnection;
import java.sql.Connection;

import java.sql.DriverManager;

public class dbconnection {
	public static Connection con;
	static public Connection getConnect()
	{
		try{
			/*The forName() method of Class class is used to register the driver class. This method is used to dynamically load the driver class.*/
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student_form","root","root");  /*The getConnection() method of DriverManager class is used to establish connection with the database.*/
			
			//System.out.println(con.isClosed());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}

	public static void main(String[] args) {
		dbconnection db=new dbconnection();
		
	}
}
