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
	
public class Topic_09_Dependency {
	WebDriver driver;
	String projectPath=System.getProperty("user.dir");
@BeforeClass
public void beforeClass() {
	
	System.setProperty("webdriver.gecko.driver",projectPath+"\\browserDriver\\geckodriver.exe");
	driver = new FirefoxDriver();	
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
}

@Test
public void TC_01_CreateUser() {
	//Assert.assertTrue(true);
	Assert.assertTrue(false);
}
@Test(dependsOnMethods = "TC_01_CreateUser")
public void TC_02_ViewUser() {
	
}
@Test(dependsOnMethods = "TC_01_CreateUser")
public void TC_03_MoveUser() {
	
}
@Test(dependsOnMethods = "TC_01_CreateUser")
public void TC_04_EditUser() {
	
}
@Test(dependsOnMethods = "TC_01_CreateUser")
public void TC_05_DeleteUser() {
	
}
 @AfterClass
 public void afterClass() {
 	driver.quit();
 }
  
}
