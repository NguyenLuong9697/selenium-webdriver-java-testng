package webdriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_08_Custom_Dropdown_I {
	WebDriver driver;
	//Wait
	WebDriverWait explicitWait;
	// JavascriptExecutor ;
	JavascriptExecutor jsExecutor;

@BeforeClass
public void beforeClass() {
	driver = new FirefoxDriver();
	explicitWait=new WebDriverWait(driver, 15);
	jsExecutor=(JavascriptExecutor) driver;
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
}

@Test
public void TC_01_Custom_DropDown_JQuery() {
	//open page
	driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
	sleepInSecond(3);
	////select item
	SelectItemInCustomDropdown("//span[@id='number-button']/span[contains(@class,'ui-selectmenu-icon')]","//ul[@id='number-menu']//div", "5");
    sleepInSecond(1);
    //verify
    Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "5");
    
    //select item
    SelectItemInCustomDropdown("//span[@id='number-button']/span[contains(@class,'ui-selectmenu-icon')]","//ul[@id='number-menu']//div", "19");
    sleepInSecond(1);
    //verify
    Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "19");
   //select item
    SelectItemInCustomDropdown("//span[@id='number-button']/span[contains(@class,'ui-selectmenu-icon')]","//ul[@id='number-menu']//div", "1");
    sleepInSecond(1);
    //verify
    Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "1");
}

@Test
public void TC_02_Custom_DropDown_ReactJS() {
	//open page
	driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
	sleepInSecond(3);
	//select item
	SelectItemInCustomDropdown("//i[@class='dropdown icon']", "//div[@role='option']//span", "Christian");
	sleepInSecond(1);
	//verify
	Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Christian");
// or
	Assert.assertTrue(driver.findElement(By.xpath("//div[@role='alert' and text()='Christian']")).isDisplayed());
//select item
	SelectItemInCustomDropdown("//i[@class='dropdown icon']", "//div[@role='option']//span", "Jenny Hess");
	sleepInSecond(1);
	//verify
	Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Jenny Hess");
	//select item
	SelectItemInCustomDropdown("//i[@class='dropdown icon']", "//div[@role='option']//span", "Justen Kitsune");
	sleepInSecond(1);
	//verify
	Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Justen Kitsune");
}

@Test
public void TC_03_Custom_DropDown_VueJS() {
	//open page
	driver.get("https://mikerodham.github.io/vue-dropdowns/");
	//select item
	SelectItemInCustomDropdown("//span[@class='caret']", "//ul[@class='dropdown-menu']//a","First Option");
	sleepInSecond(1);
	//verify
	Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText().trim(), "First Option");
	//or

	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'First Option')]")).isDisplayed());
	
}

@AfterClass
public void afterClass() {
	driver.quit();
}
public void SelectItemInCustomDropdown(String xPathParent, String xPathChild, String expected) {
	//click vao 1 element để nó sổ hết các item trong drop down ra -> parent 
	driver.findElement(By.xpath(xPathParent)).click();
	sleepInSecond(3);
	//chờ tất cả các item của drop down đc load ra thành công
	List<WebElement> listItems= explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xPathChild)));
	//-Tim item cần chọn
	// + Nếu item đc hiển thị -> click
	// + Nếu item bị ẩn ( ở dưới )-> Scroll đến item -> click
	for (WebElement item : listItems) {
		if(item.getText().trim().equals(expected)) {
			if(!item.isDisplayed()) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
			}
			item.click();
			break;
		}
		
	}
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