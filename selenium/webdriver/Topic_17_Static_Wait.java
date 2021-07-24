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
public class Topic_17_Static_Wait {
	WebDriver driver;
	
	String projectPath=System.getProperty("user.dir");
	By startButton=By.cssSelector("#start>button");
	By loading = By.cssSelector("div#loading");
	By helloWord=By.cssSelector("div#finish>h4");
	
@BeforeClass
public void beforeClass() {
	
	System.setProperty("webdriver.gecko.driver",projectPath+"\\browserDriver\\geckodriver.exe");
	//driver = new ChromeDriver();
	driver=new FirefoxDriver();
	driver.manage().window().maximize();
//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
}

@Test
public void TC_01_Static_Wait_Less() {
	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	driver.findElement(startButton).click();
	sleepInSecond(3);
	Assert.assertTrue(driver.findElement(helloWord).isDisplayed());
	
}
@Test
public void TC_02_Static_Wait_Enough() {
	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	driver.findElement(startButton).click();
	sleepInSecond(6);
	Assert.assertTrue(driver.findElement(helloWord).isDisplayed());
}
@Test
public void TC_03_Static_Wait_More() {
	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	driver.findElement(startButton).click();
	sleepInSecond(10);
	Assert.assertTrue(driver.findElement(helloWord).isDisplayed());
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