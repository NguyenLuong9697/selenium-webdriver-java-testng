package webdriver;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;
public class Topic_17_Fluent_Wait {
	WebDriver driver;
	String projectPath=System.getProperty("user.dir");
	By startButton=By.cssSelector("#start>button");
	By loading = By.cssSelector("div#loading");
	By helloWord=By.cssSelector("div#finish>h4");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
@BeforeClass
public void beforeClass() {
	//System.setProperty("webdriver.gecko.driver",projectPath+"\\browserDriver\\geckodriver.exe");
	//driver = new FirefoxDriver();	
	System.setProperty("webdriver.chrome.driver",projectPath+"\\browserDriver\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	
	
}

@Test
public void TC_01() {
	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	waitElementAndClick(startButton);
	isElementDisplayed(helloWord);
	Assert.assertTrue(driver.findElement(helloWord).isDisplayed());
	//Assert.assertTrue();
	//waitElement(By.xpath("//input[@id='tao_khong_co']"));
}
@Test
public void TC_02() {
	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	waitElementAndClick(startButton);
	isElementNotDisplayed(loading);
	Assert.assertTrue(driver.findElement(helloWord).isDisplayed());
	//Assert.assertTrue();
	//waitElement(By.xpath("//input[@id='tao_khong_co']"));
}
@Test
public void TC_03(){
	driver.get("https://www.facebook.com/");
	waitElementAndClick(By.xpath("//a[text()='Tạo tài khoản mới']"));
	
	//Assert.assertTrue(isElementDisplayed(By.xpath("//input[@name='reg_email_confirmation__']")));
	Assert.assertTrue(isElementNotDisplayed(By.xpath("//input[@name='reg_email_confirmation__']")));
	//waitElement(By.xpath("//input[@name='reg_email_confirmation__']"));
}
@Test
public void TC_04() {
	explicitWait=new WebDriverWait(driver,30);
	jsExecutor=(JavascriptExecutor)driver;
	driver.get("https://opensource-demo.orangehrmlive.com");
	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	driver.findElement(By.cssSelector("#txtUsername")).sendKeys("Admin");
	driver.findElement(By.cssSelector("#txtPassword")).sendKeys("admin123");
	driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
	//cho trang dc load
	Assert.assertTrue(isJQueryLoadSuccess(/*driver*/));
	Assert.assertTrue(driver.findElement(By.xpath("//tr[@class='total']//span[text()='3 month(s)']")).isDisplayed());
	driver.findElement(By.cssSelector("a#menu_pim_viewPimModule")).click();
	//chờ cho trang đc load
	Assert.assertTrue(isJQueryLoadSuccess(/*driver*/));
	driver.findElement(By.cssSelector("input#empsearch_employee_name_empName")).sendKeys("Peter Mac");
	//click vao button Search
	driver.findElement(By.cssSelector("input#searchBtn")).click();
	isJQueryLoadSuccess(/*driver*/);
	Assert.assertEquals(driver.findElement(By.xpath("//table[@id='resultTable']//tr[@class='odd']//td[3]/a")).getText(),"Peter Mac");
	Assert.assertEquals(driver.findElement(By.xpath("//table[@id='resultTable']//tr[@class='odd']//td[4]/a")).getText(),"Anderson");
	
	
	
	
	
}
public boolean isJQueryLoadSuccess(/*WebDriver driver*/) {
	//explicitWait=new WebDriverWait(driver,30);
	//jsExecutor=(JavascriptExecutor)driver;
	ExpectedCondition<Boolean> jQueryLoad= new ExpectedCondition<Boolean>() {

		@Override
		public Boolean apply(WebDriver driver) {
			// TODO Auto-generated method stub
			return (Boolean) jsExecutor.executeScript("return (window.jQuery!=null) && (jQuery.active===0);");
		}
		
	};
	return explicitWait.until(jQueryLoad);
}
public boolean isjQueryAndAjaxLoadSuccess(WebDriver driver) {
	explicitWait=new WebDriverWait(driver,30);
	jsExecutor=(JavascriptExecutor)driver;
	ExpectedCondition<Boolean> jQueryLoad= new ExpectedCondition<Boolean>() {

		@Override
		public Boolean apply(WebDriver driver) {
			// TODO Auto-generated method stub
			return  (Boolean) jsExecutor.executeScript("return jQuery.active===0;");
		}
		
	};
	ExpectedCondition<Boolean> AjaxLoad= new ExpectedCondition<Boolean>() {

		@Override
		public Boolean apply(WebDriver driver) {
			// TODO Auto-generated method stub
			return  (Boolean) jsExecutor.executeScript("return $('.raDiv').is(':visible')").toString().equals("false");
		}
		
	};
	return explicitWait.until(jQueryLoad) && explicitWait.until(AjaxLoad);
}
public boolean isjQueryAndPageLoadSuccess(WebDriver driver) {
	explicitWait=new WebDriverWait(driver,30);
	jsExecutor=(JavascriptExecutor)driver;
	ExpectedCondition<Boolean> jQueryLoad= new ExpectedCondition<Boolean>() {

		@Override
		public Boolean apply(WebDriver driver) {
			// TODO Auto-generated method stub
			return  (Boolean) jsExecutor.executeScript("return jQuery.active===0;");
		}
		
	};
	ExpectedCondition<Boolean> PageLoadSuccess= new ExpectedCondition<Boolean>() {

		@Override
		public Boolean apply(WebDriver driver) {
			// TODO Auto-generated method stub
			return  (Boolean) jsExecutor.executeScript("return document.readyState").toString().equals("complete");
		}
		
	};
	return explicitWait.until(jQueryLoad) && explicitWait.until(PageLoadSuccess);
}
public WebElement waitElement(By locator) {
	FluentWait<WebDriver> fluentWait= new FluentWait<WebDriver>(driver)
		//.withTimeout(14,TimeUnit.MILLISECONDS)
		.withTimeout(Duration.ofSeconds(15))
		.pollingEvery(Duration.ofMillis(1))
		.ignoring(NoSuchElementException.class);
	WebElement element=fluentWait.until(new Function<WebDriver, WebElement>() {

		@Override
		public WebElement apply(WebDriver webdriver) {
			// TODO Auto-generated method stub
			
			return webdriver.findElement(locator);
		}
	});
	
	return element;
}
public void waitElementAndClick(By locator) {
	FluentWait<WebDriver> fluentWait= new FluentWait<WebDriver>(driver)
		//.withTimeout(14,TimeUnit.MILLISECONDS)
		.withTimeout(Duration.ofSeconds(15))
		.pollingEvery(Duration.ofMillis(1))
		.ignoring(NoSuchElementException.class);
	WebElement element=fluentWait.until(new Function<WebDriver, WebElement>() {

		@Override
		public WebElement apply(WebDriver webdriver) {
			// TODO Auto-generated method stub
			
			return webdriver.findElement(locator);
		}
	});
	element.click();
	
}

public boolean isElementDisplayed(By locator) {
	FluentWait<WebDriver> fluentWait= new FluentWait<WebDriver>(driver)
		//.withTimeout(14,TimeUnit.MILLISECONDS)
		.withTimeout(Duration.ofSeconds(15))
		.pollingEvery(Duration.ofMillis(1))
		.ignoring(NoSuchElementException.class);
	Boolean status=fluentWait.until(new Function<WebDriver, Boolean>() {

		@Override
		public Boolean apply(WebDriver webdriver) {
			// TODO Auto-generated method stub
			//System.out.println("Display:"+webdriver.findElement(locator).isDisplayed());
			return webdriver.findElement(locator).isDisplayed();
		}
	});
	//System.out.println("status:"+status);
	return status;
	
}
public boolean isElementNotDisplayed(By locator) {
	FluentWait<WebDriver> fluentWait= new FluentWait<WebDriver>(driver)
		//.withTimeout(14,TimeUnit.MILLISECONDS)
		.withTimeout(Duration.ofSeconds(15))
		.pollingEvery(Duration.ofMillis(1))
		.ignoring(NoSuchElementException.class);
	Boolean status=fluentWait.until(new Function<WebDriver, Boolean>() {

		@Override
		public Boolean apply(WebDriver webdriver) {
			// TODO Auto-generated method stub
			System.out.println("Display:"+webdriver.findElement(locator).isDisplayed());
			return !webdriver.findElement(locator).isDisplayed();
		}
	});
	//System.out.println("status:"+status);
	return status;
	
}

@AfterClass
public void afterClass() {
	driver.quit();
}

}