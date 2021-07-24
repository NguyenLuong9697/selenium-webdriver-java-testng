package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_00_Template {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
@BeforeClass
public void beforeClass() {
	driver = new FirefoxDriver();
	jsExecutor=(JavascriptExecutor)driver;
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.get("");
}

@Test
public void TC_01_ValidateCurrentUrl() {
	
}

@Test
public void TC_02_ValidatePageTitle() {
	
}

@Test
public void TC_03_LoginFormDisplayed() {
	
}
public void ScrollToItem(By by) {
	WebElement item=driver.findElement(by);
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
	sleepInSecond(3);
}
public void clickByJS(By by) {
	jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
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