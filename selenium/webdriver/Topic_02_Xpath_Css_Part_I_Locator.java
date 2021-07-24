package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_02_Xpath_Css_Part_I_Locator {
	// Biến driver đại diện cho Selenium WebDriver
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Mở trình diện firefox
		driver = new FirefoxDriver();
		//Tg để chờ cho element đc tìm thấy trong 1 khoảng time (30s)
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Mở AUT ( Application Under testing )/ SUT ( System Under Testing) ứng dụng đang cần test
		// mở trang dăng ký
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
	}

	@Test
	public void TC_01_ID() {
		// Nhập giá trị vào Firstname text box
		driver.findElement(By.id("FirstName")).sendKeys("Automation");
		sleepInSecond(3);
		//click vao male radio button
		driver.findElement(By.id("gender-male")).click();
		sleepInSecond(3);
		
	}

	@Test
	public void TC_02_Class() {
		// refesh trang
		driver.navigate().refresh();
		//truyen 1 phần class
		driver.findElement(By.className("search-box-text")).sendKeys("Macbook");
		sleepInSecond(3);
		driver.findElement(By.className("search-box-button")).click();
		sleepInSecond(3);
	}

	@Test
	public void TC_03_Name() {
		// mở trang dăng ký
	driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
	driver.findElement(By.name("Email")).sendKeys("nguyenluong09061997@gmail.com");
	sleepInSecond(3);
	driver.findElement(By.name("Newsletter")).click();
	sleepInSecond(3);
				
	}
	@Test
	public void TC_04_Tagname() {
		System.out.println("Sum Input:"+ driver.findElements(By.tagName("input")).size());
		System.out.println("Sum Link:"+ driver.findElements(By.tagName("a")).size());

	}

	@Test
	public void TC_05_LinkText() {
		driver.findElement(By.linkText("Log in")).click();
		sleepInSecond(3);
	}
	@Test
	public void TC_06_Partial_LinkText() {
		driver.findElement(By.partialLinkText("Recently viewed products")).click();
		sleepInSecond(3);
		
		driver.findElement(By.partialLinkText("viewed products")).click();
		sleepInSecond(3);
		
		driver.findElement(By.partialLinkText("Recently viewed")).click();
		sleepInSecond(3);
	}

	@Test
	public void TC_07_Css() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		//id
		
		driver.findElement(By.cssSelector("input[id='FirstName']")).sendKeys("Automation");
		sleepInSecond(3);
		//class
		
		driver.findElement(By.cssSelector("input[class='search-box-text ui-autocomplete-input']")).sendKeys("Macbook");
		sleepInSecond(3);
		//name
		
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("nguyenluong@gmail.com");
		sleepInSecond(3);
		// link text
		
		driver.findElement(By.cssSelector("a[href*='login']")).click();;
		sleepInSecond(3);
		
		
	}
	@Test
	public void TC_08_XPath() {

		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		//XPath voi id
		
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Auto Test");
		sleepInSecond(3);
		
		//XPath voi class
		
		driver.findElement(By.xpath("//input[contains(@class,'search-box-text')]")).sendKeys("Mac book");
		sleepInSecond(3);
		
		//XPath voi name
		
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys("luong.ngthi@samsung.com");
		sleepInSecond(3);
		
		//XPath voi link text
		driver.findElement(By.xpath("//a[contains(@href,'login')]")).click();
		sleepInSecond(3);
	//	or
		//driver.findElement(By.xpath("//a[text()='Log in']")).click();
		//sleepInSecond(3);
		
		//XPath voi partial link text
		driver.findElement(By.xpath("//a[contains(text(),'Recently viewed')]")).click();
		sleepInSecond(3);
		
		
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
