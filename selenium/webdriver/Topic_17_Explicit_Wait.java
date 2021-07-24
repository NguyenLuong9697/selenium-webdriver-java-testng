package webdriver;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_17_Explicit_Wait {
	WebDriver driver;
	String projectPath=System.getProperty("user.dir");
	WebDriverWait explicitWait;
	By startButton=By.cssSelector("#start>button");
	By loading = By.cssSelector("div#loading");
	By helloWord=By.cssSelector("div#finish>h4");
	JavascriptExecutor jsExecutor;
	String capture1_Path=projectPath+"\\Image\\Capture1.JPG";
	String capture2_Path=projectPath+"\\Image\\Capture2.JPG";
	String capture3_Path=projectPath+"\\Image\\Capture3.JPG";
	String capture1_Name="Capture1.JPG";
	String capture2_Name="Capture2.JPG";
	String capture3_Name="Capture3.JPG";
@BeforeClass
public void beforeClass() {
	System.setProperty("webdriver.gecko.driver",projectPath+"\\browserDriver\\geckodriver.exe");
	driver = new FirefoxDriver();	
	//System.setProperty("webdriver.chrome.driver",projectPath+"\\browserDriver\\chromedriver.exe");
	//System.setProperty("webdriver.chrome.driver","C:\\Users\\Nguyen Dinh\\Downloads\\Automation FC\\02-WebDriver\\browserDriver\\chromedriver.exe");
	//driver = new ChromeDriver();
	driver.manage().window().maximize();
	jsExecutor=(JavascriptExecutor)driver;
}

@Test
public void TC_01_Explicit_3s_Invisible() {
	explicitWait=new WebDriverWait(driver,3);
	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
	driver.findElement(startButton).click();
	//wait cho đến khi loading icon biến mất
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loading));
	Assert.assertEquals(driver.findElement(helloWord).getText(),"Hello World!");
	
}

@Test
public void TC_02_Explicit_6s_Invisible() {
	explicitWait=new WebDriverWait(driver,6);
	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
	driver.findElement(startButton).click();
	//wait cho đến khi loading icon biến mất
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loading));
	Assert.assertEquals(driver.findElement(helloWord).getText(),"Hello World!");
}

@Test
public void TC_03_3s_Visible() {
	explicitWait=new WebDriverWait(driver,3);
	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
	driver.findElement(startButton).click();
	//wait cho đến khi hello word xuất hiện
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloWord));
	Assert.assertEquals(driver.findElement(helloWord).getText(),"Hello World!");
}
@Test
public void TC_04_6s_Visible() {
	explicitWait=new WebDriverWait(driver,6);
	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
	driver.findElement(startButton).click();
	//wait cho đến khi hello word xuất hiện
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloWord));
	Assert.assertEquals(driver.findElement(helloWord).getText(),"Hello World!");
}
@Test
public void TC_05_Explicit_Ajax() {
	explicitWait=new WebDriverWait(driver,15);
	driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
	//chờ cho đến khi form được hiển thị
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("contentWrapper")));
	//explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='dialog']")));
	if(isElementDisplay(By.xpath("//div[@role='dialog']"))) {
		driver.findElement(By.xpath("//button[text()='Accept Cookies']")).click();
	}
	jsExecutor.executeScript("window.scrollBy(0,150);");
	
	Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(),"No Selected Dates to display.");
	//chọn ngay 25
	
	driver.findElement(By.xpath("//td[@class='rcWeekend']/a[text()='25']")).click();
	//verify ngay 25 được chọn
	Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']/a[text()='25']")).isDisplayed());
	//wait cho ajax loading biến mất
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
	explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1' and text()='Sunday, July 25, 2021']")));
	Assert.assertTrue(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1' and text()='Sunday, July 25, 2021']")).isDisplayed());
	//Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(),"Sunday, July 25, 2021");
	
}
@Test
public void TC_06_Upload() {
	explicitWait=new WebDriverWait(driver,30);
	driver.get("https://gofile.io/uploadFiles");
	explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[contains(@class,'uploadButton') and not(contains(@class,'hideNotOwner'))]")));
	WebElement fileInput=driver.findElement(By.xpath("//input[@type='file']"));
	fileInput.sendKeys(capture1_Path+"\n"+capture2_Path+"\n"+capture3_Path);
	//wait cho ajax Selecting best server bien mat
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='rowUploadProgress-selectServer']")));
	
	//cho den khi lable Capture1.JPG, Capture2.JPG, Capture3.JPG xuat hien
	explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.progress")));
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.text-center>i.fa-spinner")));
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
	Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']")).isDisplayed());
	explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#rowUploadSuccess-showFiles")));
	scrollToElement("#rowUploadSuccess-showFiles");
	sleepInSecond(1);
	driver.findElement(By.cssSelector("#rowUploadSuccess-showFiles")).click();
	explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("span.contentName")));
	Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='"+capture1_Name+"']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='"+capture2_Name+"']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='"+capture3_Name+"']")).isDisplayed());
}
//@Test
public void TC_06() {
	explicitWait=new WebDriverWait(driver,15);
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.get("https://www.facebook.com/");
	try {
		getDateTimeNow("Start explicit By:");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='tao_khong_co']")));
	}catch(Exception e) {
		e.getStackTrace();
	}
	getDateTimeNow("End explicit By:");
}
//@Test
public void TC_07() {
	explicitWait=new WebDriverWait(driver,15);
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.get("https://www.facebook.com/");
	try {
		//getDateTimeNow("Start implicit By:");
		//WebElement e= driver.findElement(By.xpath("//input[@id='tao_khong_co']"));
		//getDateTimeNow("End implicit By:");
		getDateTimeNow("Start explicit WebElement:");
		
		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='tao_khong_co']"))));
		
	}catch(Exception e) {
		e.getStackTrace();
	}
	getDateTimeNow("End explicit WebElement:");
}
public void getDateTimeNow(String status) {
	Date time=new Date();
	System.out.println(status +":"+time.toString()+"---------");
}
public void scrollToElement(String locator) {
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
}
public WebElement getElement(String locator) {
	return driver.findElement(By.cssSelector(locator));
}
public void sleepInSecond(long timeout) {
	try {
		Thread.sleep(timeout*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public boolean isElementDisplay(By by) {
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	List<WebElement> Items=driver.findElements(by);
	
	if(Items.size()==0) {
		return false;
		
	}else if (Items.size()>0 && !Items.get(0).isDisplayed()) {
		return false;
	}else return true;
}
@AfterClass
public void afterClass() {
	driver.quit();
}

}