package webdriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_11_UserInteractions_PartI {
	WebDriver driver;
	Actions action;
	String projectPath=System.getProperty("user.dir");
@BeforeClass
public void beforeClass() {
//driver = new FirefoxDriver();
	System.setProperty("webdriver.chrome.driver",projectPath+"\\browserDriver\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	action=new Actions(driver);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
}
@Test
public void TC_01_HoverToElement_1() {
	driver.get("https://automationfc.github.io/jquery-tooltip/");
	sleepInSecond(3);
	action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
	sleepInSecond(5);
	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");
}

//@Test
public void TC_02_HoverToElement_2() {
	
}

@Test
public void TC_03_HoverToElement_3() {
	driver.get("http://www.myntra.com/");
	sleepInSecond(3);
	//hover vao Kids
	action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
	sleepInSecond(5);
	driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']")).click();
	sleepInSecond(5);
	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Kids Home Bath']")).isDisplayed());
	Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='title-title']")).getText(), "Kids Home Bath");
}

@Test
public void TC_04_HoverToElement_4() {
	driver.get("https://hn.telio.vn/");
	sleepInSecond(3);
	action.moveToElement(driver.findElement(By.xpath("//nav[@class='navigation cdz-fix-left']//span[text()='B??nh k???o']"))).perform();
	sleepInSecond(5);
	Assert.assertTrue(driver.findElement(By.xpath("//nav[@class='navigation cdz-fix-left']//a[text()='L????ng kh??']")).isDisplayed());

	Assert.assertTrue(driver.findElement(By.xpath("//nav[@class='navigation cdz-fix-left']//a[text()='B??nh g???o']")).isDisplayed());

	Assert.assertTrue(driver.findElement(By.xpath("//nav[@class='navigation cdz-fix-left']//a[text()='B??nh quy - B??nh x???p']")).isDisplayed());

	Assert.assertTrue(driver.findElement(By.xpath("//nav[@class='navigation cdz-fix-left']//a[text()='K???o']")).isDisplayed());

	Assert.assertTrue(driver.findElement(By.xpath("//nav[@class='navigation cdz-fix-left']//a[text()='B??nh b??ng lan']")).isDisplayed());

	Assert.assertTrue(driver.findElement(By.xpath("//nav[@class='navigation cdz-fix-left']//a[text()='B??nh h???p t???t']")).isDisplayed());
	
	Assert.assertTrue(driver.findElement(By.xpath("//nav[@class='navigation cdz-fix-left']//a[text()='H???t']")).isDisplayed());
	
}
@Test
public void TC_05_ClickAnHold_Block() {
	driver.get("https://automationfc.github.io/jquery-selectable/");
	sleepInSecond(3);
	List<WebElement> listItem=driver.findElements(By.xpath("//ol[@id='selectable']/li"));
	
	//Click and hold element d???u ti???n-> move to element cu???i c??ng -> nh??? chu???t
	//ch???n t??? 1-> 7
	action.clickAndHold(listItem.get(0)).moveToElement(listItem.get(6)).release().perform();
	sleepInSecond(3);
	Assert.assertEquals(driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]")).size(), 6);
		
}
@Test
public void TC_06_ClickAndHold_Random() {
	driver.get("https://automationfc.github.io/jquery-selectable/");
	sleepInSecond(3);
	List<WebElement> listItem=driver.findElements(By.xpath("//ol[@id='selectable']/li"));
	
	//> nh???n ctrol->Click and hold element d???u ti???n -> ch???n c??c elemnt -> nh??? ph??m
	//ch???n t??? 1, 3,6, 7, 12
	//cach 1:
	/*listItem.get(0).click();
	action.keyDown(Keys.CONTROL).perform();
	listItem.get(2).click();
	listItem.get(5).click();
	listItem.get(6).click();
	listItem.get(11).click();
	action.keyUp(Keys.CONTROL).perform();
	sleepInSecond(3);*/
	//c??ch 2
	/*action.keyDown(Keys.CONTROL).click(listItem.get(0))
	.click(listItem.get(2))
	.click(listItem.get(5))
	.click(listItem.get(6))
	.click(listItem.get(11))
	.keyUp(Keys.CONTROL).perform();
	sleepInSecond(3);*/
	//c??ch 3
	//Nh???n Control
	
	action.keyDown(Keys.CONTROL).perform();
	//ch???n elemnt 1, 3, 6, 7, 12
	action.click(listItem.get(0))
	.click(listItem.get(2))
	.click(listItem.get(5))
	.click(listItem.get(6))
	.click(listItem.get(11))
	.perform();
	//nh??? control
	action.keyUp(Keys.CONTROL).perform();
	/*action.clickAndHold(listItem.get(0)).moveToElement(listItem.get(6)).release().perform();
	sleepInSecond(3);
	Assert.assertEquals(driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]")).size(), 6);
*/
	Assert.assertEquals(driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]")).size(), 5);
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