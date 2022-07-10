package validation;

/*Java does not have a built-in Regular Expression class, but we can import the java.util.
 * regex package to work with regular expressions. */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validate {
	
	private Pattern name;
	private Pattern email;
	private Pattern phone;
	private Pattern pwd;
	private Matcher matcher;
	
	public static final String name_pattern="^([A-Z][a-z]*((\\s)))+[A-Z][a-z]*$";
	public static final String email_pattern= "^(.+)@(\\S+)$";
	public static final String phone_pattern="(0/91)?[7-9][0-9]{9}";
	public static final String pwd_pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
	
	public validate()
	{
		/*the pattern is created using the Pattern.compile() method */
		name=Pattern.compile(name_pattern);
		email=Pattern.compile(email_pattern);
		phone=Pattern.compile(phone_pattern);
		pwd=Pattern.compile(pwd_pattern);
	}
	
	public boolean Namevalidate(final String name1)
	{
		matcher=name.matcher(name1); /*The matcher() method is used to search for the pattern in a string*/
		return matcher.matches();
	}
	public boolean Emailvalidate(final String email1)
	{
		matcher=email.matcher(email1);
		return matcher.matches();
	}
	public boolean PhoneNovalidate(final String phoneNo1)
	{
		matcher=phone.matcher(phoneNo1);
		return matcher.matches();
	}
	public boolean Passwordvalidate(final String s4)
	{
		matcher=pwd.matcher(s4);
		return matcher.matches();
	}
	
	public static void main(String[] args) {
		new validate();
	}
	
}
