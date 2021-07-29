package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_17_Wait_PartI {
	WebDriver driver;
	String projectPath=System.getProperty("user.dir");
	//wait
	WebDriverWait explicitWait;
@BeforeClass
public void beforeClass() {
	//driver = new ChromeDriver();
	//System.setProperty("webdriver.chrome.driver",projectPath+"\\browserDriver\\chromedriver.exe");
	System.setProperty("webdriver.gecko.driver",projectPath+"\\browserDriver\\geckodriver.exe");
	//driver = new ChromeDriver();
	driver=new FirefoxDriver();
	explicitWait=new WebDriverWait(driver,15);
	//jsExecutor=(JavascriptExecutor)driver;
	driver.manage().window().maximize();
}

//@Test
public void TC_01_visible() {
	//Điều kiện bắt buộc: phải có trong DOM & hiển thị trên UI
	
	driver.get("https://www.facebook.com/");
	//wait cho 1 element được hiển thị trong 15s
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
	//verify element được hiển thị
	Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());
}

//@Test
public void TC_02_Invisible() {
	//Điều kiện bắt buộc: không hiển thị trên UI
	//Điều kiện k bắt buộc :có trong DOM or k có trong DOM
	
	driver.get("https://www.facebook.com/");
	//wait cho button Tạo tài khoản có thể click được
	explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Tạo tài khoản mới']")));
	//click vao button Tạo tài khoản mới
	driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
	//div[@id='reg_box']
	//có trong DOM va k hiển thị trên UI
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	//wait cho button Close có thể click được
	explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")));
	//close form
	driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
	//ko co trong DOM va k hiển thị trên UI
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='reg_box']")));
	
}

//@Test
public void TC_03_Presence() {
	//Điều kiện bắt buộc: phai có trong DOM
	//Điều kiện k bắt buộc: hiển thị trên UI or k hiển thị trên UI
	
	driver.get("https://www.facebook.com/");
	//wait cho button Tạo tài khoản có thể click được
	explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Tạo tài khoản mới']")));
	//click vao button Tạo tài khoản mới
	driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
	//Có hiển thị trên UI và có trong DOM
	explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email__']")));
	// Không hiển thị trên UI nhưng có trong DOM
	
	explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
    //wait cho button Close có thể click được
	explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")));
	//close form
	driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
}
@Test
public void TC_04_Staleness() {
	//Điều kiện bắt buộc : Không có trong DOM
	
	driver.get("https://www.facebook.com/");
	//wait cho button Tạo tài khoản có thể click được
	explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Tạo tài khoản mới']")));
	//click vao button Tạo tài khoản mới
	driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='reg_box']")));
	WebElement form=driver.findElement(By.xpath("//div[@id='reg_box']"));
	WebElement email=driver.findElement(By.xpath("//input[@name='reg_email__']"));
	//wait cho button Close có thể click được
	explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")));
	//close form
	driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
	explicitWait.until(ExpectedConditions.stalenessOf(form));
	explicitWait.until(ExpectedConditions.stalenessOf(email));
}
@AfterClass
public void afterClass() {
	driver.quit();
}

}