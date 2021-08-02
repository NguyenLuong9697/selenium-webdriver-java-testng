package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_10_Alert {
	WebDriver driver;
	String projectPath=System.getProperty("user.dir");
	//Wait
	WebDriverWait explicitWait;
	//Alert
	Alert alert;
@BeforeClass
public void beforeClass() {
	System.setProperty("webdriver.chrome.driver",projectPath+"\\browserDriver\\chromedriver.exe");
	//System.setProperty("webdriver.chrome.driver","C:\\Users\\Nguyen Dinh\\Downloads\\Automation FC\\02-WebDriver\\browserDriver\\chromedriver.exe");
	//driver=new FirefoxDriver();
	driver = new ChromeDriver();
	explicitWait=new WebDriverWait(driver, 15);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}

@Test
public void TC_01_AcceptAlert() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	sleepInSecond(3);
	// click button Click for JS Alert
	driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
	// wait cho alert xuất hiện
	alert = explicitWait.until(ExpectedConditions.alertIsPresent());
	sleepInSecond(5);
	//verify message của alert
	Assert.assertEquals(alert.getText(), "I am a JS Alert");
	//accept alert
	alert.accept();
	//verify message của result sau khi accept alert
	Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You clicked an alert successfully");
}

@Test
public void TC_02_ConfirmAlert() {
	//open page
	driver.get("https://automationfc.github.io/basic-form/index.html");
	sleepInSecond(3);
	//clik button Click for JS Confirm
	driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
	//chờ alert xuất hiện
	alert=explicitWait.until(ExpectedConditions.alertIsPresent());
	sleepInSecond(1);
	//verify text của alert là I am a JS Confirm
	Assert.assertEquals(alert.getText(),"I am a JS Confirm");
	//cancel alert và verify kết quả
	alert.dismiss();
	Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You clicked: Cancel");
}

@Test
public void TC_03_PromptAlert() {
	//open page
	driver.get("https://automationfc.github.io/basic-form/index.html");
	sleepInSecond(3);
	//click button Click for JS Prompt
	driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
	//chờ alert xuất hiện
	alert=explicitWait.until(ExpectedConditions.alertIsPresent());
	sleepInSecond(1);
	//verify text của alert là I am a JS prompt
	Assert.assertEquals(alert.getText(),"I am a JS prompt");
	alert.sendKeys("Automation Test");
	sleepInSecond(3);
	alert.accept();
	sleepInSecond(3);
	Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You entered: Automation Test");
}
@Test
public void TC_04_AuthenAlert_01() {
	driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
	sleepInSecond(3);
	//verify
	Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Basic Auth']/following-sibling::p")).getText(), "Congratulations! You must have the proper credentials.");
}
@Test
public void TC_04_AuthenAlert_02() {
	// mô phỏng trường hợp chuyển từ page này sang page kia hiện alert
	driver.get("http://the-internet.herokuapp.com/");
	sleepInSecond(3);
	String url=driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
	//System.out.println(url);
	url=ConvertUrlWithUserPassToUseAuthenAlert(url,"admin","admin");
	driver.get(url);
	sleepInSecond(3);
	//verify
	Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Basic Auth']/following-sibling::p")).getText(), "Congratulations! You must have the proper credentials.");
}

@AfterClass
public void afterClass() {
	driver.quit();
}
public String ConvertUrlWithUserPassToUseAuthenAlert(String url, String user, String pass) {
	//http://the-internet.herokuapp.com/basic_auth
	//http://the-internet.herokuapp.com/basic_auth
	String[] s=url.split("//");
	System.out.println("s1:"+s[0]);
	System.out.println("s2:"+s[1]);
	String new_url;
	new_url=s[0]+"//"+user+":"+pass+"@"+s[1];
	return new_url;
	
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