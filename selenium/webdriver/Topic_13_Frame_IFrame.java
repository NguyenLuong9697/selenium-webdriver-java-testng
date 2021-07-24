package webdriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_13_Frame_IFrame {
	WebDriver driver;
	String projectPath=System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	Select select;
	
@BeforeClass
public void beforeClass() {
	//driver = new FirefoxDriver();
	System.setProperty("webdriver.chrome.driver",projectPath+"\\browserDriver\\chromedriver.exe");
	driver = new ChromeDriver();
	jsExecutor=(JavascriptExecutor)driver;
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
}

@Test
public void TC_01_IFrame() {
	driver.get("https://kyna.vn/");
	sleepInSecond(3);
	driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'//www.facebook.com')]")));
	//verify frame Face book hiển thị
	//Assert.assertTrue(driver.findElement(By.xpath("//div[text()='169K lượt thích']")).isDisplayed());
	Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(),"169K lượt thích");
	driver.switchTo().defaultContent();
	
	driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']")));

	driver.findElement(By.xpath("//div[@class='border_overlay meshim_widget_widgets_BorderOverlay']")).click();
	driver.findElement(By.xpath("//input[contains(@class,'input_name')]")).sendKeys("Automation Testing");
	driver.findElement(By.xpath("//input[contains(@class,'input_phone')]")).sendKeys("0546353252");
	select=new Select(driver.findElement(By.xpath("//select[@id='serviceSelect']")));
	select.selectByVisibleText("TƯ VẤN TUYỂN SINH");
	sleepInSecond(3);
	Assert.assertEquals(select.getFirstSelectedOption().getText(), "TƯ VẤN TUYỂN SINH");
	driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Java Script");
	driver.findElement(By.xpath("//input[contains(@class,'submit')]")).click();
	sleepInSecond(1);
	Assert.assertEquals(driver.findElement(By.xpath("//label[contains(@class,'logged_in_phone')]")).getText(),"0546353252");
	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='floater']//label[contains(@class,'logged_in_name')]")).getText(),"Automation Testing");
	Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='message']")).getText(),"Java Script");

	driver.switchTo().defaultContent();
	driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Excel");
	sleepInSecond(1);
	driver.findElement(By.xpath("//form[@id='search-form']/div/button")).click();
	sleepInSecond(3);
	List<WebElement> listItems=driver.findElements(By.xpath("//div[@class='content']/h4"));
	for (WebElement webElement : listItems) {
		Assert.assertTrue(webElement.getText().contains("Excel"));
		//Assert.assertEquals(webElement.getText(), expected);
	}
	
}

@Test
public void TC_02_Frame() {
	driver.get("https://v1.hdfcbank.com/assets/popuppages/netbanking.htm");
	sleepInSecond(3);
	driver.findElement(By.xpath("(//div[@class='container']/div[contains(@class,'pdtb25')]/a)[1]")).click();
	sleepInSecond(3);
	driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='login_page']")));
	driver.findElement(By.xpath("//input[@class='input_password']")).sendKeys("aaa");
	driver.findElement(By.xpath("//td[text()='User ID / Customer ID']/parent::tr/following-sibling::tr[4]//img[@alt='continue']")).click();
	Assert.assertTrue(driver.findElement(By.xpath("//p[text()='IPIN (Password)']/parent::td/following-sibling::td/span/input")).isDisplayed());
////a[text()='Terms and Conditions']
	driver.switchTo().defaultContent();
	driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='footer']")));
	driver.findElement(By.xpath("//a[text()='Terms and Conditions']")).click();
	sleepInSecond(1);
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
}