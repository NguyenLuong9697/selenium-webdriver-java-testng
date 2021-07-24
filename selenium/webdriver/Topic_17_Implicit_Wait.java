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
public class Topic_17_Implicit_Wait {
	WebDriver driver;
	String projectPath=System.getProperty("user.dir");
	By startButton=By.cssSelector("#start>button");
	By loading = By.cssSelector("div#loading");
	By helloWord=By.cssSelector("div#finish>h4");
@BeforeClass
public void beforeClass() {
	System.setProperty("webdriver.gecko.driver",projectPath+"\\browserDriver\\geckodriver.exe");
	driver = new FirefoxDriver();	
	driver.manage().window().maximize();
}

@Test
public void TC_01_ImplicitWait_Unset() {
	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	
	driver.findElement(startButton).click();
	
	
	Assert.assertTrue(driver.findElement(helloWord).isDisplayed());
}

@Test
public void TC_02_Implict_Set_3s() {
	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	driver.findElement(startButton).click();
	
	Assert.assertTrue(driver.findElement(helloWord).isDisplayed());
}
@Test
public void TC_02_Implict_Set_6s() {
	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	driver.manage().timeouts().implicitlyWait(6,TimeUnit.SECONDS);
	driver.findElement(startButton).click();
	
	Assert.assertTrue(driver.findElement(helloWord).isDisplayed());
}

@AfterClass
public void afterClass() {
	driver.quit();
}

}