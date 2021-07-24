package webdriver;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_14_Window_Tab {
	WebDriver driver;
	String projectPath=System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	Alert alert;
@BeforeClass
public void beforeClass() {
	//driver = new FirefoxDriver();
	System.setProperty("webdriver.chrome.driver",projectPath+"\\browserDriver\\chromedriver.exe");
	driver = new ChromeDriver();
	jsExecutor=(JavascriptExecutor)driver;
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	//driver.get("");
}

@Test
public void TC_Window_Tab_Git() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	sleepInSecond(3);
	String parentID=driver.getWindowHandle();
	//click vao link Google
	driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
	//switch về tab Google
	switchToWindowByID(parentID);
	sleepInSecond(3);
	Assert.assertEquals(driver.getTitle(), "Google");
	driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium");
	String googleID=driver.getWindowHandle();
	//switch về tab ban đầu
	switchToWindowByID(googleID);
	//click vao link face book
	driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
	switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
	Assert.assertTrue(driver.findElement(By.xpath("//form[@class='_9vtf']")).isDisplayed());
	//Assert.assertTrue(driver.findElement(By.xpath("//input[@name='email']")).isDisplayed());
	//Assert.assertTrue(driver.findElement(By.xpath("//input[@name='pass']")).isDisplayed());
	//Assert.assertTrue(driver.findElement(By.xpath("@name='email']")).isDisplayed());
	//switch về tab ban đầu
	switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
	//click vao link Tiki
	driver.findElement(By.xpath("//a[text()='TIKI']")).click();
	//switch qua Tiki
	switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
	driver.findElement(By.xpath("//input[ @data-view-id='main_search_form_input']")).sendKeys("Toner");
	closeTabWithoutParent(parentID);
	sleepInSecond(3);
	Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
}

@Test
public void TC_02_Window_Tab_Kyna() {
	driver.get("https://kyna.vn/");
	sleepInSecond(3);
	String parentID=driver.getWindowHandle();
	ScrollToItem(By.xpath("//div[@id='k-footer']//img[@alt='facebook']"));
	//click vao icon facebook
	driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();
	//switch qua tab facbook
	switchToWindowByID(parentID);
	sleepInSecond(3);
	Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");
	Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");
	String KynaID=driver.getWindowHandle();
	//quay lai tab parent
	switchToWindowByID(KynaID);
	//click vao icon youtube
	driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='youtube']")).click();
	//switch qua tab Youtube của Kyna
	switchToWindowByTitle("Kyna.vn - YouTube");
	sleepInSecond(3);
	Assert.assertEquals(driver.getCurrentUrl(),"https://www.youtube.com/user/kynavn");
	//switch về parent tab
	switchToWindowByID("Kyna.vn - Học online cùng chuyên gia");
	//click vao icon zalo
	driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='zalo']")).click();
	//switch vao tab Zalo
	switchToWindowByID("Zalo Official Account");
	
	//close all tab != trang chủ của  Kyna
	closeTabWithoutParent(parentID);
	
}
@Test
public void TC_03_Window_Tab_LiveGuru() {
	driver.get("http://live.demoguru99.com/index.php/");
	sleepInSecond(3);
	driver.findElement(By.xpath("//nav[@id='nav']//a[text()='Mobile']")).click();
	sleepInSecond(3);
	String mobileID=driver.getWindowHandle();
	//Add To Compare Iphone 
	driver.findElement(By.xpath("//a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product IPhone has been added to comparison list.']")).isDisplayed());
	//Add To Compare Samsung
	driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
	//click button Compare
	driver.findElement(By.xpath("//button[@title='Compare']")).click();
	//switch qua window Compare
	switchToWindowByID(mobileID);
	//verify title của window
	Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
	String compareID=driver.getWindowHandle();
	driver.close();
	switchToWindowByID(compareID);
	//click Clear All
	driver.findElement(By.xpath("//a[text()='Clear All']")).click();
	alert=driver.switchTo().alert();
	sleepInSecond(2);
	alert.accept();
	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());
	
}
public void ScrollToItem(By by) {
	WebElement item=driver.findElement(by);
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
	sleepInSecond(3);
}
//ap dung khi co nhieu hơn 2 tab
public void switchToWindowByTitle( String title) {
	Set<String> allWindows=driver.getWindowHandles();
	for (String id : allWindows) {
		driver.switchTo().window(id);
		if(driver.getTitle().equals(title)) {
			break;
		}
	}
}
public void closeTabWithoutParent(String parentID) {
	Set<String> allWindows=driver.getWindowHandles();
	for (String id : allWindows) {
		if(!id.equals(parentID)) {
			driver.switchTo().window(id);
			driver.close();
		}
	}
	driver.switchTo().window(parentID);
	
}

//ap dung khi cos 2 tab/window
public void switchToWindowByID(String parentID) {
	Set<String> allWindows=driver.getWindowHandles();
	for (String id : allWindows) {
		if(!id.equals(parentID)) {
			driver.switchTo().window(id);
			break;
		}
	}
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