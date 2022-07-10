package DBForm;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Random;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.jdbc.PreparedStatement;

import validation.validate;


/*To determine where the user clicked on the screen, Java provides an interface called "ActionListener" */
public  class SimpleForm implements ActionListener
{
	JFrame jf;
	Font ft;
	JButton bt;
	JPasswordField jpass;
	JPasswordField jcpass;
	JLabel jb1;
	JLabel jb2;
	JLabel jb3;
	JLabel jb4;
	JLabel jb5;
	JLabel jb6;
	JTextField name;
    JTextField email;
	JTextField password;
	JLabel jb7;
	
	public SimpleForm() {
		jf=new JFrame("Generate_ID");
		
		jb1=new JLabel("Form");
		jb1.setFont(new Font("Times New Roman",Font.BOLD,50));
		jb1.setForeground(Color.pink);
		jb1.setBounds(350, 20, 350, 60);
		jf.add(jb1);
		
		jb2=new JLabel("Name:");
		jb2.setFont(new Font("Times New Roman",Font.BOLD,20));
		jb2.setForeground(Color.PINK);
		jb2.setBounds(30, 80, 100, 50);
		jf.add(jb2);
		
		name=new JTextField();
		name.setFont(new Font("Times New Roman",Font.PLAIN, 20));
		name.setBounds(230, 90, 200, 30);
		name.setColumns(1);
		jf.add(name);
		
		jb3=new JLabel("Email:");
		jb3.setFont(new Font("Times New Roman",Font.BOLD,20));
		jb3.setForeground(Color.pink);
		jb3.setBounds(30, 140, 100, 50);
		jf.add(jb3);
		
		email=new JTextField();
		email.setFont(new Font("Times New Roman",Font.PLAIN, 20));
		email.setBounds(230, 150, 200, 30);
		email.setColumns(1);
		jf.add(email);
		
		jb4=new JLabel("PhoneNo:");
		jb4.setFont(new Font("Times New Roman",Font.BOLD,20));
		jb4.setForeground(Color.pink);
		jb4.setBounds(30, 210, 100, 50);
		jf.add(jb4);
		
		password=new JTextField();
		password.setFont(new Font("Times New Roman",Font.PLAIN, 20));
		password.setBounds(230, 220, 200, 30);
		password.setColumns(1);
		jf.add(password);
	
		jb5=new JLabel("Password:");
		jb5.setFont(new Font("Times New Roman",Font.BOLD,20));
		jb5.setForeground(Color.pink);
		jb5.setBounds(30, 280, 100, 50);
		jf.add(jb5);
		
		jpass=new JPasswordField();
		jpass.setBounds(230, 290, 200, 30);
		jf.add(jpass);
		
		jb6=new JLabel("ConfirmPassword:");
		jb6.setFont(new Font("Times New Roman",Font.BOLD,20));
		jb6.setForeground(Color.pink);
		jb6.setBounds(30, 350, 220, 50);
		jf.add(jb6);
	     
		jcpass=new JPasswordField();
		jcpass.setBounds(230, 360, 200, 30);
		jf.add(jcpass);
		
		jb7=new JLabel("Password and confirm password should be same.");
		jb7.setFont(new Font("Times New Roman",Font.BOLD,19));
		jb7.setForeground(Color.red);
		jb7.setBounds(450, 360, 400, 30);
		jf.add(jb7);
		
		bt=new JButton("Submit");
		bt.addActionListener(this);
		bt.setFont(new Font("Times New Roman",Font.BOLD,18));
		bt.setForeground(Color.white);
		bt.setBackground(Color.black);
		bt.setBounds(350, 420, 100, 50);
		jf.add(bt);
		
		jf.setSize(900,900);
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.getContentPane().setBackground(Color.darkGray);
		jf.setLayout(null);
		
		jf.setVisible(true);
	}

	public static void main(String[] args) {
		new SimpleForm();
		
	}
	
	static int max=199;
	static int min=100;
	
	static String createid()
	{
		/*Random class generates a stream of pseudorandom numbers. */
		Random random = new Random();   
		// Generates random integers 0 to 100  
		
		return "RUC00"+String.valueOf(min++);
	}

	/*The method actionPerformed handles all the actions of a component, and inside this method, 
	 * you will define or write your own codes that will be executed when an action occurred.*/
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String id=createid();
		String s1=name.getText();
		String s2=email.getText();
		String s3=password.getText();
		char[] pass=jpass.getPassword();
		String s4=new String(pass);
		char[] conpass=jcpass.getPassword();
		String s5=new String(conpass);
		
		
		String query="insert into studentform values(?,?,?,?,?)";
		validate val=new validate();
	
		if(s1.equals("") ||(!val.Namevalidate(s1)))
		{
		   JOptionPane.showMessageDialog(bt,"Name not valid, enter valid name.");
		}
		else if(s2.equals("")||(!val.Emailvalidate(s2)))
		{
		   JOptionPane.showMessageDialog(bt,"Email not valid, enter valid email.");
		}
		else if(s3.equals("")||(!val.PhoneNovalidate(s3)))
		{
		   JOptionPane.showMessageDialog(bt,"PhoneNumber not valid,enter valid phoneNo.");
		}
		else if(s4.equals("")||(!val.Passwordvalidate(s4)))
		{
		   JOptionPane.showMessageDialog(bt,"Password Can't be null.");
		}
		else if(s5.equals(""))
		{
		   JOptionPane.showMessageDialog(bt,"Enter confirm password.");
		}
		else if(!s4.equals(s5))
		{
			JOptionPane.showMessageDialog(bt,"Password and confirm password should be same.");
		}
		else {
			try(Connection con=DBconnection.dbconnection.getConnect();
					PreparedStatement ps=(PreparedStatement) con.prepareStatement(query);
					)
			{
				ps.setString(1, id);
				ps.setString(2, s1);
				ps.setString(3, s2);
				ps.setString(4, s3);
				ps.setString(5, s4);
				
				int i=ps.executeUpdate();
				if(i>0)
				{
					JOptionPane.showMessageDialog(jf, "submitted data successfully");
				}
				else
				{
					JOptionPane.showMessageDialog(jf, "Not submitted due to some error","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			catch(Exception e1)
			{
				System.out.println(e1);
			}
			}
	}
}
