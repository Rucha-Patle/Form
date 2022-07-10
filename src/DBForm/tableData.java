package DBForm;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class tableData
{
	static JTable jtable;
	 static JScrollPane scroll;
	 static JFrame f;
	 static JPanel jp;
	
	static Color color1=new Color(95,158,160);
	static Color color2=new Color(188,143,143);
	
	  public static void main(String[] args) 
	  {
	    try 
	  {
	    	/* 3 arguments of getConnection method*/
//	      String url = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false";
//	      String user = "root";
//	      String password = "root";
	     Class.forName("com.mysql.jdbc.Driver");
	     Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_form","root","root");
	    
	      String query = "select * from studentform";
	    
	      Statement stm = (Statement) con.createStatement();
	      ResultSet res = (ResultSet) stm.executeQuery(query);
	    
	      String columns[] = { "ID", "Name", "Email","Phone_No","password" };
	      String data[][] = new String[100][5];
	    
	      int i = 0;
	      while (res.next()) {
	        String id = res.getString("ID");
	        String name = res.getString("name");
	        String email = res.getString("email");
	        String Phone_No=res.getString("Phone_No");
	        String password=res.getString("password");
	        data[i][0] = id ;
	        data[i][1] = name;
	        data[i][2] = email;
	        data[i][3]=Phone_No;
	        data[i][4]=password;
	        i++;
	      }
	    
	      DefaultTableModel model = new DefaultTableModel(data, columns);
	      
	      f = new JFrame("Show Table Data");
	      f.getContentPane().setBackground(color1);
	      f.setSize(600, 550);
	      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	      jtable = new JTable(model);
	      jtable.setShowGrid(true);
	      jtable.setShowVerticalLines(true);
	      jtable.setBackground(color2);
	      
	      scroll = new JScrollPane(jtable);
	      jp = new JPanel();
	      jp.add(scroll);
	      f.add(jp);
	      
	      f.setVisible(true);
	    }
	    catch(Exception e) {
	      e.printStackTrace();
	    }
	  }

}
