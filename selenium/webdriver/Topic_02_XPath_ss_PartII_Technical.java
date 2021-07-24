package webdriver;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_02_XPath_ss_PartII_Technical {
	WebDriver driver;
	String firstname, lastname, emailAdress, pass, confirm_pass, fullName;
	
//Bai Tập [Topic 04/05] - [Exercise] Xpath and CSS
@BeforeClass
public void beforeClass() {
	driver = new FirefoxDriver();
	//thoi gian de tim element
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	//khoi tao cac bien
	firstname="John";
	lastname="Thomas";
	emailAdress="johnthomas"+randomEmail();
	pass="abc@13579";
	confirm_pass="abc@13579";
	fullName=firstname +" "+lastname;
}

//@Test
public void TC_01_Login_Empty_Password() {
	//step 1: open home page
	driver.get("http://live.demoguru99.com/");
	//step 2:click My Account o footer
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	//step 3
	//step 4:Click Login button
	driver.findElement(By.id("email")).sendKeys("");
	driver.findElement(By.id("pass")).sendKeys("");
	driver.findElement(By.xpath("//button[@id='send2']")).click();
	//driver.findElement(By.name("send")).click();
	sleepInSecond(3);
	//step 5: verify result
	Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(),"This is a required field.");
	Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(),"This is a required field.");
	
	
}

//@Test
public void TC_02_Login_Invalid_Email() {
	//step 1: open home page
	driver.get("http://live.demoguru99.com/");
	//step 2:click My Account o footer
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	//step 3: Enter email
	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("12341234@12345");
	//step 4: Enter email
	driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
	//step 5: Login
	driver.findElement(By.xpath("//button[@id='send2']")).click();
	//step 6: Verify result
	Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
}

//@Test
public void TC_03_LoginWithPassword_Under_6_character() {
	//step 1: open home page
	driver.get("http://live.demoguru99.com/");
	//step 2:click My Account o footer
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	//step 3: Enter email
	driver.findElement(By.id("email")).sendKeys("nguyenluon@gmail.com");
	//step 4: Enter pass
	driver.findElement(By.id("pass")).sendKeys("123");
	//step 5: Login
	driver.findElement(By.name("send")).click();
	//step 6: Verify result
	Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");

}
//@Test
public void TC_04_Login_With_Correct_Email_Incorrect_Password() {
	//step 1: open home page
	driver.get("http://live.demoguru99.com/");
	//step 2:click My Account o footer
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	//step 3: Enter email
	driver.findElement(By.id("email")).sendKeys("nguyenluon@gmail.com");
	//step 4: Enter pass
	driver.findElement(By.id("pass")).sendKeys("12345678");
	//step 5: Login
	driver.findElement(By.name("send")).click();
	//step 6: Verify result
	
	Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),"Invalid login or password.");
	
}
@Test
public void TC_05_Create_new_account() {
   //step 1: open home page
	driver.get("http://live.demoguru99.com/");
	//step 2:click My Account o footer
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	sleepInSecond(3);
	//step 3:Click Button Create a Account
	driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	
	//step 4: Nhap First Name
	driver.findElement(By.id("firstname")).sendKeys(firstname);
	//step 5: Nhat Last Name
	driver.findElement(By.id("lastname")).sendKeys(lastname);
	//step 6: nhap email
	
	driver.findElement(By.id("email_address")).sendKeys(emailAdress);
	//
	//step 7: nhap pass
	driver.findElement(By.id("password")).sendKeys(pass);
	//step 8: confirm pass
	driver.findElement(By.id("confirmation")).sendKeys(confirm_pass);
	// step 9: click button register
	driver.findElement(By.xpath("//button[@title='Register']")).click();
	sleepInSecond(3);
	// step 10: Verify text Thank you for registering with Main Website Store.
	Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
    //or
	
	//Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
	//step 11: Verify user information
	//Lấy thông tin user
	String user_info=driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
	System.out.println("User info:"+user_info);
	// verify
	Assert.assertTrue(user_info.contains(fullName));
	Assert.assertTrue(user_info.contains(emailAdress));
	//or
	//Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p[contains(text(),'"+fullName+"')]")).isDisplayed());
	//Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p[contains(string(),'"+emailAdress+"')]")).isDisplayed());
	//step 12: logout
	driver.findElement(By.cssSelector(".skip-account")).click();
	driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	sleepInSecond(8);
	Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/");
}
@Test
public void TC_06_Login_with_Valid_user_password() {
	//step 1:open page
	driver.get("http://live.demoguru99.com/");
	//step 2:click My Account o footer
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	sleepInSecond(3);
	//step 3: enter email
	driver.findElement(By.id("email")).sendKeys(emailAdress);
	//step 4: enter pass
	driver.findElement(By.id("pass")).sendKeys(pass);
	//step 5: click button Login
	driver.findElement(By.id("send2")).click();
	sleepInSecond(5);
	//step 6: verify
	Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
	
	Assert.assertEquals(driver.findElement(By.xpath("//p[@class='hello']//strong")).getText(), "Hello, "+fullName+"!");
	Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p[contains(text(),'"+fullName+"')]")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p[contains(string(),'"+emailAdress+"')]")).isDisplayed());
}

@AfterClass
public void afterClass() {
	driver.quit();
}
public void sleepInSecond(long timeout) {
	try {
		Thread.sleep(timeout*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public String randomEmail() {
	Random rd=new Random();
	return rd.nextInt(1000)+"@mail.vn";
	
}
}