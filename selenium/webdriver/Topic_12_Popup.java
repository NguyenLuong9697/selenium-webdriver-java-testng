package webdriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_12_Popup {
	WebDriver driver;
	String projectPath=System.getProperty("user.dir");
@BeforeClass
public void beforeClass() {
	System.setProperty("webdriver.chrome.driver",projectPath+"\\browserDriver\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
}

@Test
public void TC_01_Fixed_Popup() {
	driver.get("https://www.zingpoll.com/");
	sleepInSecond(3);
	//click button Login
	driver.findElement(By.cssSelector("#Loginform")).click();
	sleepInSecond(3);
	//Assert.assertTrue(driver.findElement(By.cssSelector(".modal_dialog_custom")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.className("modal_dialog_custom")).isDisplayed());
	//close popup
	driver.findElement(By.cssSelector(".modal_dialog_custom .close")).click();
	sleepInSecond(1);
	//verify popup k con xuất hiện
	Assert.assertFalse(driver.findElement(By.className("modal_dialog_custom")).isDisplayed());
}
@Test
public void TC_02_Shoppe() {
	driver.get("https://shopee.vn/");
	sleepInSecond(3);
	//verify popup
	Assert.assertTrue(driver.findElement(By.cssSelector("img[alt='home_popup_banner']")).isDisplayed());
	//close popup
    driver.findElement(By.cssSelector(".shopee-popup__close-btn")).click();
    sleepInSecond(1);
    //verify popup k xuất hiện
    //Assert.assertFalse(isElementDisplayed(By.cssSelector("img[alt='home_popup_banner']")));
    //or
    Assert.assertFalse(isElementDisplay(By.cssSelector("img[alt='home_popup_banner']")));
}
@Test
public void TC_03_Random_Popup_InDOM() {
	driver.get("https://blog.testproject.io/");
	sleepInSecond(10);
	//nếu tồn tại popup thì tắt popup đi
	if(isElementDisplay(By.className("mailch-wrap"))) {
		driver.findElement(By.className("close-mailch")).click();
	}
	//search từ khó Selenium
	driver.findElement(By.cssSelector("#secondary .search-field")).sendKeys("Selenium");
	sleepInSecond(3);	
	//driver.findElement(By.cssSelector("#secondary .glass")).click();
	driver.findElement(By.cssSelector("#secondary .search-field")).sendKeys(Keys.ENTER);
	List<WebElement> items=driver.findElements(By.cssSelector(".post-on-archive-page .post-title>a"));
	for (WebElement element : items) {
		
		Assert.assertTrue(element.getText().contains("Selenium"));
	}
}

@Test
public void TC_04_RandomPopup_NotInDOM() {
	driver.get("https://shopee.vn/");
	String searchKey="Macbook Pro";
	sleepInSecond(3);
	if(isElementDisplay(By.cssSelector("img[alt='home_popup_banner']"))) {
		driver.findElement(By.cssSelector(".shopee-popup__close-btn")).click();
	}
	driver.findElement(By.cssSelector(".shopee-searchbar-input__input")).sendKeys(searchKey);
	driver.findElement(By.cssSelector(".btn-solid-primary")).click();
	//or
	//driver.findElement(By.cssSelector(".shopee-searchbar-input__input")).sendKeys(Keys.ENTER);
	sleepInSecond(3);
	List<WebElement> items=driver.findElements(By.cssSelector(".shopee-search-item-result__item .PFM7lj>div"));
	for (WebElement element : items) {
		String[] s=searchKey.split(" ");
		
		Assert.assertTrue(element.getText().toLowerCase().contains(s[0].toLowerCase())|| element.getText().toLowerCase().contains(s[1].toLowerCase()));
	}
}

@AfterClass
public void afterClass() {
	driver.quit();
}
public boolean isElementDisplayed(By by) {
	try {
		return driver.findElement(by).isDisplayed();
	}catch(Exception e) {
		return false;
	}
	
}
public boolean isElementDisplay(By by) {
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	List<WebElement> Items=driver.findElements(by);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	if(Items.size()==0) {
		return false;
		
	}else if (Items.size()>0 && !Items.get(0).isDisplayed()) {
		return false;
	}else return true;
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