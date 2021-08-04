package webdriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_08_Custom_Dropdown_II {
	WebDriver driver;
	//Wait
	WebDriverWait explicitWait;
	// JavascriptExecutor ;
	JavascriptExecutor jsExecutor;
	//lấy đường dẫn của project hiện tại;
	
	String projectPath=System.getProperty("user.dir");

	String[] firstmonth= {"January", "May"};
	String[] secondmonth= {"January", "May","June","July"};
	String[] thirdmonth= {"January","February","March","April","May","June","July","August","September","October","November","December"};
@BeforeClass
public void beforeClass() {
	
	System.setProperty("webdriver.chrome.driver",projectPath+"\\browserDriver\\chromedriver.exe");
	//System.setProperty("webdriver.chrome.driver","C:\\Users\\Nguyen Dinh\\Downloads\\Automation FC\\02-WebDriver\\browserDriver\\chromedriver.exe");
	driver = new ChromeDriver();
	
	explicitWait=new WebDriverWait(driver, 15);
	jsExecutor=(JavascriptExecutor) driver;
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
}

//@Test
public void TC_01_Custom_DropDown_Angular() {
	//open page
	driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
	sleepInSecond(5);
	driver.navigate().refresh();
	
	//SelectItemInCustomDropdown("//span[@aria-owns='games_options']", "//li[@class='e-list-item ']", "Badminton");
	//sleepInSecond(3);
}

//@Test
public void TC_01_2_Custom_DropDown_Angular() {
	//open page
	driver.get("https://valor-software.com/ng2-select/");
	sleepInSecond(3);
	//select
	SelectItemInCustomDropdown("//tab[@heading='Single']//i[@class='caret pull-right']", "//tab[@heading='Single']//li[@role='menuitem']//a//div", "Barcelona");
	//verify
	Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Select a single city']/following-sibling::ng-select//span[contains(@class,'ui-select-match-text')]")).getText(), "Barcelona");
	
	//select item
	SelectItemInCustomDropdown("//tab[@heading='Single']//i[@class='caret pull-right']", "//tab[@heading='Single']//li[@role='menuitem']//a//div", "Cologne");
	
	//verify
	Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Select a single city']/following-sibling::ng-select//span[contains(@class,'ui-select-match-text')]")).getText(), "Cologne");
	
	//select item
	SelectItemInCustomDropdown("//tab[@heading='Single']//i[@class='caret pull-right']", "//tab[@heading='Single']//li[@role='menuitem']//a//div", "Amsterdam");
	
	//verify
	Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Select a single city']/following-sibling::ng-select//span[contains(@class,'ui-select-match-text')]")).getText(), "Amsterdam");
	
}
//@Test
public void TC_02_1_Custom_DropDown_Editable() {
driver.get("https://valor-software.com/ng2-select/");
sleepInSecond(3);
//slect
enterAndSelectItemInCustomDropDownEditable("//tab[@heading='Single']//i[@class='caret pull-right']","//tab[@heading='Single']//input[@placeholder='No city selected']","//tab[@heading='Single']//li[@role='menuitem']//a//div", "Bradford");
//sleepInSecond(3);
Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Select a single city']/following-sibling::ng-select//span[contains(@class,'ui-select-match-text')]")).getText(), "Bradford");
//slect
enterAndSelectItemInCustomDropDownEditable("//tab[@heading='Single']//i[@class='caret pull-right']","//tab[@heading='Single']//input","//tab[@heading='Single']//li[@role='menuitem']//a//div", "Brussels");
sleepInSecond(3);
Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Select a single city']/following-sibling::ng-select//span[contains(@class,'ui-select-match-text')]")).getText(), "Brussels");
//select
enterAndSelectItemInCustomDropDownEditable("//tab[@heading='Single']//i[@class='caret pull-right']","//tab[@heading='Single']//input","//tab[@heading='Single']//li[@role='menuitem']//a//div", "Copenhagen");
//sleepInSecond(3);
Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Select a single city']/following-sibling::ng-select//span[contains(@class,'ui-select-match-text')]")).getText(), "Copenhagen");
	
}
//@Test
public void TC_02_2_Custom_Dropdown_Editable(){
	driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
	sleepInSecond(3);
	enterAndTabItemInCustomDropDownEditable("//input[@class='search']", "Albania");
	Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Albania");
}
@Test
public void TC_03_Multiple_Select() {
	driver.get("http://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
    sleepInSecond(3);
    selectItemInMultipleDropDown("(//div[@class='icon-caret'])[1]", "//div[@class='form-group row'][2]//div[@class='ms-drop bottom']//span", firstmonth);
    areItemSelected(firstmonth);
    sleepInSecond(3);
    selectItemInMultipleDropDown("(//button[@class='ms-choice'])[1]", "//div[@class='form-group row'][2]//div[@class='ms-drop bottom']//span", secondmonth);
    areItemSelected(secondmonth);
    sleepInSecond(3);
    selectItemInMultipleDropDown("(//button[@class='ms-choice'])[1]", "//div[@class='form-group row'][2]//div[@class='ms-drop bottom']//span", thirdmonth);
    areItemSelected(thirdmonth);
}
@AfterClass
public void afterClass() {
	driver.quit();
}
public void selectItemInMultipleDropDown(String xPathParent, String xPathChild, String[] listexpectedItem) {
	driver.findElement(By.xpath(xPathParent)).click();
	sleepInSecond(1);
	List<WebElement> listItems= explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xPathChild)));
	//System.out.println(listItems.size()+"");
	//Duyet qua het cac phan cho den khi thoa man dieu kien
 for (WebElement item : listItems) {
	for (String expectedItem : listexpectedItem) {
		if(item.getText().trim().equals(expectedItem)) {
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
			sleepInSecond(1);
			item.click();
			//break;
			List<WebElement> listSelected=driver.findElements(By.xpath("//li[@class='selected']//input"));
			if(listSelected.size()==listexpectedItem.length) {
				break;
			}
		}
		
	}
}
}
public boolean areItemSelected(String[] months) {
	boolean status=false;
	List<WebElement> listItemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
	int size=listItemSelected.size();
	String allSelectedText=driver.findElement(By.xpath("(//button[@class='ms-choice']/span)[1]")).getText();
	if(size<=3 && size>0) {
		System.out.println("TH1");
		for (WebElement webElement : listItemSelected) {
			if(!allSelectedText.contains(webElement.getText())) {
				status=false;
				break;
			}
		}
	}else if(size>3 && size<12) {
		System.out.println("TH2");
		status= driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='"+size+" of 12 selected']")).isDisplayed();
	}else {
		System.out.println("TH3");
		status=driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()= 'All selected']")).isDisplayed();
	}
	return status;
	
}
public void enterAndTabItemInCustomDropDownEditable(String textbox,  String expected) {
	
	//send key
	driver.findElement(By.xpath(textbox)).clear();
	driver.findElement(By.xpath(textbox)).sendKeys(expected);
	
	sleepInSecond(3);
	driver.findElement(By.xpath(textbox)).sendKeys(Keys.TAB);
}
public void enterAndSelectItemInCustomDropDownEditable(String xPathParent,String textbox, String xPathChild, String expected) {
	driver.findElement(By.xpath(xPathParent)).click();
	sleepInSecond(3);
	//send key
	driver.findElement(By.xpath(textbox)).clear();
	driver.findElement(By.xpath(textbox)).sendKeys(expected);
	sleepInSecond(3);

	List<WebElement> listItems= explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xPathChild)));
	for (WebElement item : listItems) {
		if(item.getText().trim().equals(expected)) {
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
			sleepInSecond(1);
			item.click();
			break;
		}
	}
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
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
			sleepInSecond(1);
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