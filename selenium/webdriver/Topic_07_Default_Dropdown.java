package webdriver;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_07_Default_Dropdown {
	WebDriver driver;
	String firstName, lastName, day, month, year, email,company, pass, confirm_pass;
	Select select;
	JavascriptExecutor jsExecutor;
@BeforeClass
public void beforeClass() {
	driver = new FirefoxDriver();
	jsExecutor=(JavascriptExecutor) driver;
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	firstName="Automation";
	lastName="FC";
	day="9";
	month="July";
	year="1997";
	email=randoEmail();
	company="ABC Company";
	pass="123456";
	confirm_pass="123456";
	
}

@Test
public void TC_01_Register() {
	//open page
	driver.get("https://demo.nopcommerce.com/");
	//click Register trên header
	driver.findElement(By.className("ico-register")).click();
	sleepInSecond(3);
	//nhap cac thong tin
	//gender
	driver.findElement(By.id("gender-male")).click();
	//First Name
	driver.findElement(By.id("FirstName")).sendKeys(firstName);
	//Last Name
	driver.findElement(By.id("LastName")).sendKeys(lastName);
	// khoi tao select chon ngay
	 select=new Select(driver.findElement(By.name("DateOfBirthDay")));
	 //chon gia tri trong dropdown
	 select.selectByVisibleText(day);
	 // verify gia tri đc chọn
	 Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
	 // select chon thang
	 select=new Select(driver.findElement(By.name("DateOfBirthMonth")));
	 select.selectByVisibleText(month);
	 Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
	 //select chon nam
	 select=new Select(driver.findElement(By.name("DateOfBirthYear")));
	 select.selectByVisibleText(year);
	 Assert.assertEquals(select.getFirstSelectedOption().getText(),year);
	 //nhap Email
	 driver.findElement(By.id("Email")).sendKeys(email);
	 //nhap Company
	 driver.findElement(By.id("Company")).sendKeys(company);
	 //nhap pass
	 driver.findElement(By.id("Password")).sendKeys(pass);
	 //confirm pass
	 driver.findElement(By.id("ConfirmPassword")).sendKeys(confirm_pass);
	 //nhan button Register
	// driver.findElement(By.id("register-button")).click();
	 clickByJS(By.id("register-button"));
	 sleepInSecond(3);
	 // Verify text
	 Assert.assertEquals(driver.findElement(By.className("result")).getText(), "Your registration completed");
	 // click My Account
	 driver.findElement(By.className("ico-account")).click();
	 sleepInSecond(3);
	 
	 //verify gia tri
	 //gender
	 Assert.assertTrue(driver.findElement(By.id("gender-male")).isSelected());
	 //first name
	 Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
    //last name
	 Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
	 //day
	 select=new Select(driver.findElement(By.name("DateOfBirthDay")));
	 Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
	 //month
	 select=new Select(driver.findElement(By.name("DateOfBirthMonth")));
	 Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
	 //year
	 select=new Select(driver.findElement(By.name("DateOfBirthYear")));
	 Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
	//email
	 Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), email);
	//company
	 Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"),company);
	 
}



@AfterClass
public void afterClass() {
	driver.quit();
}
public void clickByJS(By by) {
	jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
}
public void sleepInSecond(long timeout) {
	try {
		Thread.sleep(timeout*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public String randoEmail() {
 Random rd=new Random();
 return "automationfc"+rd.nextInt(1000)+"@mail.net";
}
}