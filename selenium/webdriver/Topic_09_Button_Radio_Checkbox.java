package webdriver;
import java.util.concurrent.TimeUnit;

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
public class Topic_09_Button_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath=System.getProperty("user.dir");
@BeforeClass
public void beforeClass() {
	//driver = new FirefoxDriver();

	System.setProperty("webdriver.chrome.driver",projectPath+"\\browserDriver\\chromedriver.exe");
	//System.setProperty("webdriver.chrome.driver","C:\\Users\\Nguyen Dinh\\Downloads\\Automation FC\\02-WebDriver\\browserDriver\\chromedriver.exe");
	driver = new ChromeDriver();
	jsExecutor=(JavascriptExecutor)driver;
	
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}

@Test
public void TC_01_Button() {
	driver.get("https://www.fahasa.com/customer/account/create?attempt=1");
	sleepInSecond(3);
	//click tab Đăng nhập
	driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
	sleepInSecond(3);
	
	//check button Đăng nhập là disable
	System.out.println("Button is: "+ driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());
	Assert.assertFalse(driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());
	
	//nhap cac thông tin sđt va pass
	driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("0375460399");
	driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("123456");
	//check button Đăng nhập là enable
	System.out.println("Button is: "+ driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());
	Assert.assertTrue(driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());
	//refresh lai trang
	driver.navigate().refresh();
//click tab Đăng nhập
	driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
	sleepInSecond(3);
	//remove attribute disable
	RemoveDisableAttributeByJS(By.xpath("//button[@class='fhs-btn-login']"));
   //check button Đăng nhập là enable
	System.out.println("Button is: "+ driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());
	Assert.assertTrue(driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());
	//click vao button Đăng nhập
	driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).click();
	sleepInSecond(3);
	//verify
	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
	
//	refresh lai trang
	driver.navigate().refresh();
	RemoveDisableAttributeByJS(By.xpath("//button[@class='fhs-btn-login']"));
	clickByJS(By.xpath("//button[@class='fhs-btn-login']"));
	
}

//defaut checbox, default radio button: thẻ input đc visible
//nc lai nó là custom checkbox , custom default radio button
@Test
public void TC_02_DefaultCheckbox() {
	//open page
	driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
	sleepInSecond(5);
	Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());

	By checkbox_Dual=By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
	//scroll đến check box Dual-zone air conditioning
	ScrollToItem(checkbox_Dual);
	
	//click vao check box Dual-zone air conditioning
	driver.findElement(checkbox_Dual).click();
	sleepInSecond(1);
	//verify
	Assert.assertTrue(driver.findElement(checkbox_Dual).isSelected());
	//uncheck
	driver.findElement(checkbox_Dual).click();
	sleepInSecond(1);
	Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());

	//click check box Heated front and rear seats
	Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Heated front and rear seats']/preceding-sibling::input")).isSelected());

	By headfrontandrearseats_checkbox=By.xpath("//label[text()='Heated front and rear seats']/preceding-sibling::input");
	//scroll đến item
	ScrollToItem(headfrontandrearseats_checkbox);
	//click
	driver.findElement(headfrontandrearseats_checkbox).click();
	sleepInSecond(1);
	//verify
	Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Heated front and rear seats']/preceding-sibling::input")).isSelected());

	
	
}

@Test
public void TC_03_DefaultRadio() {
	//open page
	driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
	sleepInSecond(3);
	//check Radio button: 1.4 Petrol, 92kW đã được chọn chưa
	By radio_92kW=By.xpath("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::input");
	Assert.assertTrue(driver.findElement(radio_92kW).isSelected());
	//By radio_2.0 Diesel, 103kW
	By radio_103kW=By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input");
	Assert.assertFalse(driver.findElement(radio_103kW).isSelected());
	ScrollToItem(radio_103kW);
	driver.findElement(radio_103kW).click();
	sleepInSecond(1);
	Assert.assertFalse(driver.findElement(radio_92kW).isSelected());
	Assert.assertTrue(driver.findElement(radio_103kW).isSelected());
	//By radio	1.8 Petrol, 118kW
	By radio_118kW=By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::input");
	Assert.assertFalse(driver.findElement(radio_118kW).isSelected());
	ScrollToItem(radio_118kW);
	driver.findElement(radio_118kW).click();
	sleepInSecond(1);
	Assert.assertFalse(driver.findElement(radio_103kW).isSelected());
	Assert.assertTrue(driver.findElement(radio_118kW).isSelected());
}
//chỉ thẻ input ms check isSelected hay chưa nên khi là custom radio dùng javaScript để click
@Test
public void TC_04_CustomRadio() {
	//open page
	driver.get("https://material.angular.io/components/radio/examples");
	sleepInSecond(3);
	//Radio Button Spring
	By spring_radio=By.xpath("//input[@value='Spring']");
	Assert.assertFalse(driver.findElement(spring_radio).isSelected());
	//ScrollToItem(spring_radio);
	clickByJS(spring_radio);
	sleepInSecond(3);
	//verify
	Assert.assertTrue(driver.findElement(spring_radio).isSelected());
	
	//click Autumn 
	
	By autumn_radio=By.xpath("//input[@value='Autumn']");
	Assert.assertFalse(driver.findElement(autumn_radio).isSelected());
	//ScrollToItem(spring_radio);
	clickByJS(autumn_radio);
	sleepInSecond(3);
	//verify
	Assert.assertTrue(driver.findElement(autumn_radio).isSelected());
	Assert.assertFalse(driver.findElement(spring_radio).isSelected());
}
@Test
public void TC_05_CustomCheckbox() {
	//open page
	driver.get("https://material.angular.io/components/checkbox/examples");
	sleepInSecond(3);
	By checked_checkbox=By.xpath("//span[contains(text(),'Checked')]/preceding-sibling::span/input");
	Assert.assertFalse(driver.findElement(checked_checkbox).isSelected());
	clickByJS(checked_checkbox);
	sleepInSecond(1);
	Assert.assertTrue(driver.findElement(checked_checkbox).isSelected());
	clickByJS(checked_checkbox);
	sleepInSecond(1);
	Assert.assertFalse(driver.findElement(checked_checkbox).isSelected());
	By indeterminate_checkbox=By.xpath("//span[contains(text(),'Indeterminate')]/preceding-sibling::span/input");
	Assert.assertFalse(driver.findElement(indeterminate_checkbox).isSelected());
	clickByJS(indeterminate_checkbox);
	sleepInSecond(1);
	Assert.assertTrue(driver.findElement(indeterminate_checkbox).isSelected());
	clickByJS(indeterminate_checkbox);
	sleepInSecond(1);
	Assert.assertFalse(driver.findElement(indeterminate_checkbox).isSelected());
}
@Test
public void TC_06_Custom_Checkbox_2() {
	//open page
	driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
	sleepInSecond(3);
    //Custom radio button
	//By HN_radio=By.xpath("//div[@aria-label='Hà Nội' and @aria-checked='false']//div[contains(@class,'exportInnerCircle')]");
	
	Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Hà Nội' and @aria-checked='false']")).isDisplayed());
	ScrollToItem(By.xpath("//div[@aria-label='Hà Nội' and @aria-checked='false']"));
	//clickByJS(By.xpath("//div[@aria-label='Hà Nội' and @aria-checked='false']"));
	driver.findElement(By.xpath("//div[@aria-label='Hà Nội' and @aria-checked='false']")).click();
	sleepInSecond(1);
	Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Hà Nội' and @aria-checked='true']")).isDisplayed());
	//Check box button
	Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Nam' and @aria-checked='false']//div[contains(@class,'exportInnerBox')]")).isDisplayed());
	ScrollToItem(By.xpath("//div[@aria-label='Quảng Nam' and @aria-checked='false']//div[contains(@class,'exportInnerBox')]"));
	driver.findElement(By.xpath("//div[@aria-label='Quảng Nam' and @aria-checked='false']//div[contains(@class,'exportInnerBox')]")).click();

	Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Nam' and @aria-checked='true']")).isDisplayed());
	
}
@AfterClass
public void afterClass() {
	driver.quit();
}
public void CheckToCheckBox(By by) {
	driver.findElement(by).click();
}
public void ScrollToItem(By by) {
	WebElement item=driver.findElement(by);
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
	sleepInSecond(3);
}
public void sleepInSecond(long timeout) {
	try {
		Thread.sleep(timeout*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void clickByJS(By by) {
	jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
}
public void RemoveDisableAttributeByJS(By by) {
	jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(by));
}
}