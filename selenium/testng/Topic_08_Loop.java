package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
	
public class Topic_08_Loop {
	WebDriver driver;
	String projectPath=System.getProperty("user.dir");
@BeforeClass
public void beforeClass() {
	
	System.setProperty("webdriver.gecko.driver",projectPath+"\\browserDriver\\geckodriver.exe");
	driver = new FirefoxDriver();	
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
}

 @Test(dataProvider = "register", invocationCount = 2)
 public void TC_01(String firstname, String lastname, String email, String pass) {
	 driver.get("http://live.demoguru99.com/demo.guru99.com/v4/");
	 //click vao My Account
	 driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	 //click vao Create an Account
	 driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
	 
	 driver.findElement(By.id("firstname")).sendKeys(firstname);
	 driver.findElement(By.id("lastname")).sendKeys(lastname);
	 driver.findElement(By.id("email_address")).sendKeys(email);
	 driver.findElement(By.id("password")).sendKeys(pass);
	 driver.findElement(By.id("confirmation")).sendKeys(pass);
	 driver.findElement(By.xpath("//button[@title='Register']")).click();
	 //verify
	 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
	 driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
	 driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	 
 }
 @DataProvider(name="register")
 public String[][] Register(){
	 return new String[][] {
		 {"John", "Thomas","john_thomas"+randomEmail(),"111111"}
		
	 };
 }
 
 public String randomEmail() {
		Random rd=new Random();
		return rd.nextInt(1000)+"@mail.vn";
		
	}
 @AfterClass
 public void afterClass() {
 	driver.quit();
 }
  
}
