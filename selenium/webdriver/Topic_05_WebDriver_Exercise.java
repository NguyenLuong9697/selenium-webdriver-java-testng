package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_05_WebDriver_Exercise {
	WebDriver driver;

@BeforeClass
public void beforeClass() {
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
}

//@Test
public void TC_01_Check_Display() {
	//step 1: open page
	driver.get("https://automationfc.github.io/basic-form/index.html");
	//step 2: check email is displayed or not
	if(isElementDisplay(By.id("mail"))) {
		//send key
		enterValue(By.id("mail"),"Automation Testing");
	}
	//step 3: check edu is displayed or not
	if(isElementDisplay(By.id("edu"))) {
		//send key
		enterValue(By.id("edu"),"Automation Testing");
	}
	//step 4: check under 18 is displayed or not
	if(isElementDisplay(By.id("under_18"))) {
		//send key
		checkRadio(By.id("under_18"));
	}
	//step 5: check Name: user 5 is display or not
	isElementDisplay(By.xpath("//h5[text()='Name: User5']"));
}

//@Test
public void TC_02_Check_Enabled() {
	//step 1: open page
	driver.get("https://automationfc.github.io/basic-form/index.html");
	//step 2:Check email, age (under 18), education, job role 1, job role 2, interest ( development) checkbox, slider 01 is enable
	Assert.assertTrue(isElementEnable(By.id("mail")));
	Assert.assertTrue(isElementEnable(By.id("under_18")));
	Assert.assertTrue(isElementEnable(By.id("edu")));
	Assert.assertTrue(isElementEnable(By.id("job1")));
	Assert.assertTrue(isElementEnable(By.id("job2")));
	Assert.assertTrue(isElementEnable(By.id("development")));
	Assert.assertTrue(isElementEnable(By.id("slider-1")));
	//step 3:Check password, age (radio button is disable), biography, job role 3,  interest (check box is disable),slider 02 is disable
	Assert.assertFalse(isElementEnable(By.id("password")));
	Assert.assertFalse(isElementEnable(By.id("radio-disabled")));
	Assert.assertFalse(isElementEnable(By.id("bio")));
	Assert.assertFalse(isElementEnable(By.id("job3")));
	Assert.assertFalse(isElementEnable(By.id("check-disbaled")));
	Assert.assertFalse(isElementEnable(By.id("slider-2")));
}

//@Test
public void TC_03_Check_Selected() {
	//step 1: open page
	driver.get("https://automationfc.github.io/basic-form/index.html");
	//step 2: click Age (under 18) & Language: Java
	checkRadio(By.id("under_18"));
	checkRadio(By.id("java"));
	//step 3: kiểm tra các phần tử ở step 2 đã được chọn hay chưa
	Assert.assertTrue(isElementSelected(By.id("under_18")));
	Assert.assertTrue(isElementSelected(By.id("java")));
	//step 4: bo chon Language :Java
	checkRadio(By.id("java"));
	//step 5: kiểm tra Language : Java bỏ chọn còn Age (under 18 ) được chọn
	Assert.assertTrue(isElementSelected(By.id("under_18")));
	Assert.assertFalse(isElementSelected(By.id("java")));
}
@Test
public void TC_04_Register_At_MailChimp() {
	//step 1: open page
	driver.get("https://login.mailchimp.com/signup/");
	//step 2: Nhap email & username
	enterValue(By.id("email"), "johnthomas@mail.net");
	enterValue(By.id("new_username"), "John Thomas");
	
	//step 3: nhap pass word la chư thuong, 
	enterValue(By.id("new_password"), "a");
	sleepInSecond(3);
	Assert.assertTrue(isElementDisplay(By.cssSelector(".lowercase-char.completed")));
	Assert.assertFalse(isElementEnable(By.id("create-account")));
	// nhap pass la chư hoa
	enterValue(By.id("new_password"), "A");
	sleepInSecond(3);
	Assert.assertTrue(isElementDisplay(By.cssSelector(".uppercase-char.completed")));
	Assert.assertFalse(isElementEnable(By.id("create-account")));
	//pass la số
	enterValue(By.id("new_password"), "1");
	sleepInSecond(3);
	Assert.assertTrue(isElementDisplay(By.cssSelector(".number-char.completed")));
	Assert.assertFalse(isElementEnable(By.id("create-account")));
	//pass la 1 ki tu dac biet
	enterValue(By.id("new_password"), "*");
	sleepInSecond(3);
	Assert.assertTrue(isElementDisplay(By.cssSelector(".special-char.completed")));
	Assert.assertFalse(isElementEnable(By.id("create-account")));
	//pass co dộ dai > 8
	enterValue(By.id("new_password"), "abcdefghi");
	sleepInSecond(3);
	Assert.assertTrue(isElementDisplay(By.cssSelector(".lowercase-char.completed")));
	Assert.assertTrue(isElementDisplay(By.cssSelector("li[class='8-char completed']")));
	Assert.assertFalse(isElementEnable(By.id("create-account")));
	// step 4: pass đu tất cả các đk
	enterValue(By.id("new_password"), "Jun@2019");
	sleepInSecond(3);
	Assert.assertTrue(isElementEnable(By.id("create-account")));
	Assert.assertFalse(isElementDisplay(By.cssSelector(".lowercase-char.completed")));
	Assert.assertFalse(isElementDisplay(By.cssSelector(".uppercase-char.completed")));
	Assert.assertFalse(isElementDisplay(By.cssSelector(".number-char.completed")));
	Assert.assertFalse(isElementDisplay(By.cssSelector(".special-char.completed")));
	Assert.assertFalse(isElementDisplay(By.cssSelector("li[class='8-char completed']")));
	
	//step 5: check vao check box
	checkRadio(By.id("marketing_newsletter"));
	Assert.assertTrue(isElementSelected(By.id("marketing_newsletter")));
}
@AfterClass
public void afterClass() {
	driver.quit();
}
public boolean isElementDisplay(By by) {
	if(driver.findElement(by).isDisplayed()) {
		System.out.println(by+" is display");
		return true;
	}else {
		System.out.println(by+" is not display");
	}
	return false;
}
public boolean isElementEnable(By by) {
	if(driver.findElement(by).isEnabled()) {
		System.out.println(by+" is enabled");
		return true;
	}else {
		System.out.println(by+" is not enabled");
	}
	return false;
}
public boolean isElementSelected(By by) {
	if(driver.findElement(by).isSelected()) {
		System.out.println(by+" is selected");
		return true;
	}else {
		System.out.println(by+" is not selected");
	}
	return false;
}
public void enterValue(By by, String value) {
	driver.findElement(by).clear();
	driver.findElement(by).sendKeys(value);
}
public void checkRadio(By by) {
	driver.findElement(by).click();
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