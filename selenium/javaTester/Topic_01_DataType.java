package javaTester;

import java.util.Date;
import java.util.Random;

import org.openqa.selenium.support.Color;

public class Topic_01_DataType {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("Selenium Java");
		//int x=RandomNumber();
		//System.out.println(x);
		//System.out.println(ConvertUrlWithUserPassToUseAuthenAlert("http://the-internet.herokuapp.com/basic_auth","admin","admin"));
		//String rgba = "rgba(3,169,244,1)";
		//Color color = Color.fromString(rgba);
		//System.out.println("hex value = " + color.asHex());
		
//		String projectPath=System.getProperty("user.dir");
//		System.out.println(projectPath); 
//		System.out.println(projectPath+"\\browserDriver\\chromedriver.exe");
		String separator = System.getProperty("file.separator");
		System.out.println(separator);
		//PrintTime();
	}
	public static int RandomNumber() {
		Random rd=new Random();
		return rd.nextInt(1000);
		
		
	}
	public static String ConvertUrlWithUserPassToUseAuthenAlert(String url, String user, String pass) {
		//http://the-internet.herokuapp.com/basic_auth
		String[] s=url.split("//");
		System.out.println("s1:"+s[0]);
		System.out.println("s2:"+s[1]);
		String new_url;
		new_url=s[0]+"//"+user+":"+pass+"@"+s[1];
		return new_url;
	}
	public static void PrintTime() {
		Date time=new Date();
		
		System.out.println(time.toString()+"");
	}
	

}
