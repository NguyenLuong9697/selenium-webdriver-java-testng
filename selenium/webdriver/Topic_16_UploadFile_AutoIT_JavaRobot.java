package webdriver;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Set;
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
public class Topic_16_UploadFile_AutoIT_JavaRobot {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath=System.getProperty("user.dir");
	String capture1_Path=projectPath+"\\Image\\Capture1.JPG";
	String capture2_Path=projectPath+"\\Image\\Capture2.JPG";
	String capture3_Path=projectPath+"\\Image\\Capture3.JPG";
	String autoITUploadOneFile= projectPath+"\\autoIT\\chromeUploadOneTime.exe";
	String autoITUploadMultipleFile= projectPath+"\\autoIT\\chromeUploadMultiple.exe";
	String capture1_Name="Capture1.JPG";
	String capture2_Name="Capture2.JPG";
	String capture3_Name="Capture3.JPG";
	//Wait
	WebDriverWait explicitWait;
@BeforeClass
public void beforeClass() {
	System.setProperty("webdriver.chrome.driver",projectPath+"\\browserDriver\\chromedriver.exe");
	driver = new ChromeDriver();
	explicitWait=new WebDriverWait(driver,15);
	//jsExecutor=(JavascriptExecutor)driver;
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
}

@Test
public void TC_01_Upload_AutoIT() throws IOException {
	driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	//click vao button Add Files
	driver.findElement(By.cssSelector(".fileinput-button")).click();
	Runtime.getRuntime().exec(new String[] {autoITUploadOneFile, capture1_Path});
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='Capture1.JPG']")).isDisplayed());
	//click vao start
	//driver.findElement(By.xpath("//tbody[@class='files']//button[contains(@class,'start')]"))
	driver.findElement(By.cssSelector(".files .start")).click();
	sleepInSecond(2);
	
	Assert.assertEquals(driver.findElement(By.cssSelector(".name>a")).getText(), capture1_Name);
}

@Test
public void TC_02_Upload_AutoIT_Multiple() throws IOException {
	driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	//click vao button Add Files
	driver.findElement(By.cssSelector(".fileinput-button")).click();
	Runtime.getRuntime().exec(new String[] {autoITUploadMultipleFile, capture1_Path, capture2_Path, capture3_Path});
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+capture1_Name+"']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+capture2_Name+"']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+capture3_Name+"']")).isDisplayed());
	//click vao start
	List<WebElement> allStartButtons=driver.findElements(By.cssSelector(".files .start"));
	for (WebElement button: allStartButtons) {
		button.click();
		sleepInSecond(1);
	}
	////p[@class='name']/a[@title='Capture2.JPG' and text()='Capture2.JPG']
	//System.out.println("//p[@class='name']/a[@title='"+capture1_Name+"' and text()='"+capture1_Name+"']");
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+capture1_Name+"' and text()='"+capture1_Name+"']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+capture2_Name+"' and text()='"+capture2_Name+"']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+capture3_Name+"' and text()='"+capture3_Name+"']")).isDisplayed());
}

@Test
public void TC_03_UploadFileByRobot() {
	driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	//click vao button Add Files
	driver.findElement(By.cssSelector(".fileinput-button")).click();
	uploadFileByRobot(capture3_Path);
	driver.findElement(By.cssSelector(".files .start")).click();
	sleepInSecond(2);
	
	Assert.assertEquals(driver.findElement(By.cssSelector(".name>a")).getText(), capture3_Name);
}
@Test
public void TC_04_Summary() {
	driver.get("https://gofile.io/?t=uploadFiles");
	driver.findElement(By.xpath("//input[@type='file']")).sendKeys(capture1_Path+"\n"+capture2_Path+"\n"+capture3_Path);
	//verify
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
	Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']")).isDisplayed());
	String parentID=driver.getWindowHandle();
	driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage']")).click();
	switchToWindowByID(parentID);
	//verify Capture1.JPG
	driver.findElement(By.xpath("//span[text()='Capture1.JPG']/parent::a/parent::div/following-sibling::div/button")).click();
	//span[text()='Capture1.JPG']/parent::a/parent::div/following-sibling::div//a[contains(@class,'contentPlay')]
	//WebElement e=driver.findElement(By.xpath("//span[text()='Capture1.JPG']/parent::a/parent::div/following-sibling::div//a[contains(@class,'contentPlay')]"));
	//System.out.println(e.isDisplayed()+"");
	
	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Capture1.JPG']/parent::a/parent::div/following-sibling::div//a[contains(@class,'contentPlay')]")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Capture1.JPG']/parent::a/parent::div/following-sibling::div//a[contains(@class,'contentRename')]")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Capture1.JPG']/parent::a/parent::div/following-sibling::div//a[contains(@class,'contentCopy')]")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Capture1.JPG']/parent::a/parent::div/following-sibling::div//a[contains(@class,'contentInfo')]")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Capture1.JPG']/parent::a/parent::div/following-sibling::div//a[contains(@class,'contentDelete')]")).isDisplayed());
	driver.findElement(By.xpath("//span[text()='Capture1.JPG']/parent::a/parent::div/following-sibling::div/button")).click();
	//verify Capture2.JPG
	driver.findElement(By.xpath("//span[text()='Capture2.JPG']/parent::a/parent::div/following-sibling::div/button")).click();
	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Capture2.JPG']/parent::a/parent::div/following-sibling::div//a[contains(@class,'contentPlay')]")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Capture2.JPG']/parent::a/parent::div/following-sibling::div//a[contains(@class,'contentRename')]")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Capture2.JPG']/parent::a/parent::div/following-sibling::div//a[contains(@class,'contentCopy')]")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Capture2.JPG']/parent::a/parent::div/following-sibling::div//a[contains(@class,'contentInfo')]")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Capture2.JPG']/parent::a/parent::div/following-sibling::div//a[contains(@class,'contentDelete')]")).isDisplayed());
	driver.findElement(By.xpath("//span[text()='Capture2.JPG']/parent::a/parent::div/following-sibling::div/button")).click();
	//verify Capture3.JPG
	driver.findElement(By.xpath("//span[text()='Capture3.JPG']/parent::a/parent::div/following-sibling::div/button")).click();
	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Capture3.JPG']/parent::a/parent::div/following-sibling::div//a[contains(@class,'contentPlay')]")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Capture3.JPG']/parent::a/parent::div/following-sibling::div//a[contains(@class,'contentRename')]")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Capture3.JPG']/parent::a/parent::div/following-sibling::div//a[contains(@class,'contentCopy')]")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Capture3.JPG']/parent::a/parent::div/following-sibling::div//a[contains(@class,'contentInfo')]")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Capture3.JPG']/parent::a/parent::div/following-sibling::div//a[contains(@class,'contentDelete')]")).isDisplayed());
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

public void uploadFileByRobot(String filePath) {
	try {
		//Specify the file location with extension
		StringSelection select =new StringSelection(filePath);
		//copy the clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		Robot robot=new Robot();
		sleepInSecond(1);
		//nhan phim Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		//nhan xuong Ctrl V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		//nha Ctrl V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		//nhan enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	catch (AWTException e) {
		// TODO: handle exception
		e.printStackTrace();
	}

	
	
	
}
public void ScrollToItem(By by) {
	WebElement item=driver.findElement(by);
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
	sleepInSecond(3);
}
public void clickByJS(By by) {
	jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
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