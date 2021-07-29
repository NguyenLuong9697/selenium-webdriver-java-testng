package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
	
public class Topic_06_Optional {
	//Optionalr: vd quên k set gia tri của brower or url , hoặc truyền sai k tìm thấy
	WebDriver driver;
	String projectPath=System.getProperty("user.dir");
/*	@Parameters("browser")
@BeforeClass
public void beforeClass(String browser_name) {
	if(browser_name.equals("Firefox")) {
		System.setProperty("webdriver.gecko.driver",projectPath+"\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();	
	}else if(browser_name.equals("Chrome")) {
		System.setProperty("webdriver.chrome.driver",projectPath+"\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();	
	}else if(browser_name.equals("Opera")) {
		System.setProperty("webdriver.opera.driver",projectPath+"\\browserDriver\\operadriver.exe");
		driver = new OperaDriver();	
	} else {
		throw new RuntimeException("Please check the browser name again");
	}
	
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
}*/
/*@Parameters({ "browser", "url" })
@BeforeClass
public void beforeClass(String browser_name, String urlValue) {
	if (browser_name.equals("Firefox")) {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
	} else if (browser_name.equals("Chrome")) {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
	} else if (browser_name.equals("Opera")) {
		System.setProperty("webdriver.opera.driver", projectPath + "\\browserDriver\\operadriver.exe");
		driver = new OperaDriver();
	} else {
		throw new RuntimeException("Please check the browser name again");
	}

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.get(urlValue);

}*/

@Parameters("url")
@BeforeClass
public void beforeClass(@Optional("http://live.demoguru99.com/") String urlValue) {
	
	System.setProperty("webdriver.gecko.driver",projectPath+"\\browserDriver\\geckodriver.exe");
	driver = new FirefoxDriver();	
	
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.get(urlValue);
//	System.out.println(urlValue);
	}
 @Test(dataProvider = "register")
 public void TC_01(String firstname, String lastname, String email, String pass) {
	 //driver.get("http://live.demoguru99.com/");
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
		 {"John", "Thomas","john_thomas"+randomEmail(),"111111"},
		 {"Auto", "Test","auto_test"+randomEmail(),"222222"},
		 {"Anna", "Nguyen","annna"+randomEmail(),"333333"},
		 {"Mic", "Peter","peter"+randomEmail(),"444444"}
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
