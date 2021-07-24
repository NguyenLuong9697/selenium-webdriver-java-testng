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
public class Topic_06_Textbox_TextArea {
	WebDriver driver;
	//Data Test
	String emailAddress, userID,customerID, password, customerName,gender,dateofbirth, address,city, state, pin,mobile_number;
	String loginPage;
	//UI ( new Customer & Edit Customer)
	By username=By.name("name");
	By gender_male=By.xpath("//td[text()='Gender']/following-sibling::td//input[1]");
	By gender_femal=By.xpath("//td[text()='Gender']/following-sibling::td//input[last()]");
	By dob=By.name("dob");
	By add=By.name("addr");
	By city_ui=By.name("city");
	By state_ui=By.name("state");
	By pinno=By.name("pinno");
	By telephoneno=By.name("telephoneno");
	By email_ui=By.name("emailid");
	By pass_ui=By.name("password");

@BeforeClass
public void beforeClass() {
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	emailAddress="johnthomas"+randomEmail();
	driver.get("http://demo.guru99.com/v4/");
	customerName="Thomas";
	dateofbirth="1986-01-02";
	address="123 Po Boxing";
	city="California";
	state="Hawai";
	pin="987889";
	mobile_number="087579293";
	
}

@Test
public void TC_01_Register() {
	loginPage=driver.getCurrentUrl();
	driver.findElement(By.xpath("//a[text()='here']")).click();
	//nhap email
	driver.findElement(By.name("emailid")).sendKeys(emailAddress);
	//click button submit
	driver.findElement(By.name("btnLogin")).click();
	//get username & password
	userID=driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
	password=driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
}

@Test
public void TC_02_Login() {
	//open page login
	driver.get(loginPage);
	//nhap username & password
	driver.findElement(By.name("uid")).sendKeys(userID);
	driver.findElement(By.name("password")).sendKeys(password);
	//click login
	driver.findElement(By.name("btnLogin")).click();
	sleepInSecond(3);
	Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(),"Welcome To Manager's Page of Guru99 Bank");

	
}

@Test
public void TC_03_LoginFormDisplayed() {
	//click New Customer
	driver.findElement(By.xpath("//a[text()='New Customer']")).click();
	//nhap cac thong tin
	driver.findElement(username).sendKeys(customerName);
	//chon female
	driver.findElement(gender_femal).click();
	//date of birth
	driver.findElement(dob).sendKeys(dateofbirth);
	//address
	driver.findElement(add).sendKeys(address);
	//city
	driver.findElement(city_ui).sendKeys(city);
	//state
	driver.findElement(state_ui).sendKeys(state);
	//pin
	driver.findElement(pinno).sendKeys(pin);
	//mobile
	driver.findElement(telephoneno).sendKeys(mobile_number);
	//email
	driver.findElement(email_ui).sendKeys(emailAddress);
	//pass
	driver.findElement(pass_ui).sendKeys(password);
	//submit
	driver.findElement(By.name("sub")).click();
	//verify
	Assert.assertEquals(driver.findElement(By.cssSelector(".heading3")).getText(), "Customer Registered Successfully!!!");
	customerID=driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),customerName );
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),"female");
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dateofbirth );
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),address );
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),city );
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),state );
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),pin );
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),mobile_number );
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),emailAddress );
}
@Test
public void TC_04_UpdateUser() {
	//click edit customer
	driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
	//nhap user name
	driver.findElement(By.name("cusid")).sendKeys(customerID);
	//click button submit
	driver.findElement(By.name("AccSubmit")).click();
	//verify before edit
    Assert.assertFalse(driver.findElement(By.name("name")).isEnabled());
    Assert.assertEquals(driver.findElement(username).getAttribute("value"), customerName);
    Assert.assertFalse(driver.findElement(By.name("gender")).isEnabled());
    Assert.assertEquals(driver.findElement(By.name("gender")).getAttribute("value"), "female");
    Assert.assertFalse(driver.findElement(By.name("dob")).isEnabled());
    Assert.assertEquals(driver.findElement(By.name("dob")).getAttribute("value"), dateofbirth);
    Assert.assertEquals(driver.findElement(add).getText(),address);
    Assert.assertEquals(driver.findElement(city_ui).getAttribute("value"),city);
    Assert.assertEquals(driver.findElement(state_ui).getAttribute("value"),state);
    Assert.assertEquals(driver.findElement(pinno).getAttribute("value"),pin);
    Assert.assertEquals(driver.findElement(telephoneno).getAttribute("value"),mobile_number);
    Assert.assertEquals(driver.findElement(email_ui).getAttribute("value"),emailAddress);
	//edit
    String new_address="Quang Yen";
    String new_city="Ha Noi";
    String new_state="Ha Noi";
    
    edit(add, new_address);
    edit(city_ui,new_city);
    edit(state_ui, new_state);
    //submit
    driver.findElement(By.name("sub")).click();
    sleepInSecond(3);
    // verify after edit
    Assert.assertEquals(driver.findElement(By.cssSelector(".heading3")).getText(), "Customer details updated Successfully!!!");
    Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText(),customerID );
    Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),customerName );
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),"female");
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dateofbirth );
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),new_address );
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),new_city );
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),new_state );
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),pin );
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),mobile_number );
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),emailAddress );
}
@AfterClass
public void afterClass() {
	driver.quit();
}
public String randomEmail() {
	Random rd=new Random();
	return rd.nextInt(1000)+"@mail.vn";
	
}
public void edit(By by, String edit) {
	driver.findElement(by).clear();
	driver.findElement(by).sendKeys(edit);
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