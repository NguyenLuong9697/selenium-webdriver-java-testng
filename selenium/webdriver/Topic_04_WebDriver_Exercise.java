package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_04_WebDriver_Exercise {
	WebDriver driver;

@BeforeClass
public void beforeClass() {
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
}

@Test
public void TC_01_VerifyUrl() {
	driver.get("http://live.demoguru99.com");
	// click My Account ở footer
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	//wait
	sleepInSecond(3);
	// Verify url của Login Page
	Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/login/");
	// Click Create An Account button
	driver.findElement(By.xpath("//a[@class='button' and @title='Create an Account']")).click();
	//wait
	sleepInSecond(3);
	// Verify url của register account

	Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/create/");
	
}

@Test
public void TC_02_VerifyTitle() {
	//open page
	driver.get("http://live.demoguru99.com");
  // click My Account ở footer
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	//wait
	sleepInSecond(3); 
	// verify Title
	Assert.assertEquals(driver.getTitle(),"Customer Login");
// Click Create An Account button
	driver.findElement(By.xpath("//a[@class='button' and @title='Create an Account']")).click();
	//wait
	sleepInSecond(3);
	// Verify url của register account

	Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
		
	
}

@Test
public void TC_03_Navigate_function() {
	// open page
	driver.get("http://live.demoguru99.com");
  // click My Account ở footer
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	//wait
   sleepInSecond(3); 
// Click Create An Account button
	driver.findElement(By.xpath("//a[@class='button' and @title='Create an Account']")).click();
	//wait
	sleepInSecond(3);
	// Verify url

	Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/create/");
	// back lai trang
	driver.navigate().back();
	//wait
	sleepInSecond(3);
	// Verify url
	Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/login/");
	// forward
	driver.navigate().forward();
	// wait
	sleepInSecond(3);
	//verify title
	Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
	
}
@Test
public void TC_04_GetPageSource() {
	//open page
	driver.get("http://live.demoguru99.com");
   // click My Account ở footer
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	//wait
   sleepInSecond(3); 
   // get page Source
   String pageSource=driver.getPageSource();
   //wait
   sleepInSecond(3);
   // verify login page chứa text Login or Create an Account
   Assert.assertTrue(pageSource.contains("Login or Create an Account"));
   // Click Create An Account button
	driver.findElement(By.xpath("//a[@class='button' and @title='Create an Account']")).click();
	//wait
	sleepInSecond(3);
	// get Page Source
	pageSource=driver.getPageSource();
	//wait
	sleepInSecond(3);
	// verify login page chứa text Create an Account
	Assert.assertTrue(pageSource.contains("Create an Account"));
	
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
}