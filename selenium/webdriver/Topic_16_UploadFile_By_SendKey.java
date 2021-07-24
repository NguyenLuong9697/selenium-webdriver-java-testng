package webdriver;
import java.util.List;
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
public class Topic_16_UploadFile_By_SendKey {
	WebDriver driver;
	
	String projectPath=System.getProperty("user.dir");
	String capture1_Path=projectPath+"\\Image\\Capture1.JPG";
	String capture2_Path=projectPath+"\\Image\\Capture2.JPG";
	String capture3_Path=projectPath+"\\Image\\Capture3.JPG";
	//String brower=projectPath+"\\browserDriver\\chromedriver.exe";
	String capture1_Name="Capture1.JPG";
	String capture2_Name="Capture2.JPG";
	String capture3_Name="Capture3.JPG";
@BeforeClass
public void beforeClass() {
	//System.setProperty("webdriver.chrome.driver",projectPath+"\\browserDriver\\chromedriver.exe");
	//driver = new ChromeDriver();
	//jsExecutor=(JavascriptExecutor)driver;
	//driver = new ChromeDriver();
	//System.setProperty("webdriver.chrome.driver",projectPath+"\\browserDriver\\chromedriver.exe");
	System.setProperty("webdriver.gecko.driver",projectPath+"\\browserDriver\\geckodriver.exe");
	//driver = new ChromeDriver();
	driver=new FirefoxDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
}

@Test
public void TC_01_Upload_SingleFile() {
	driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	sleepInSecond(3);
	
	//selenium sendkey upload file ko quan tâm đến element có hiển thị hay k
	//Nó chỉ quan tâm thẻ input=type  là được
	
	WebElement fileInput=driver.findElement(By.xpath("//input[@type='file']"));
	fileInput.sendKeys(capture1_Path);
	sleepInSecond(3);
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='Capture1.JPG']")).isDisplayed());
	//click vao start
	//driver.findElement(By.xpath("//tbody[@class='files']//button[contains(@class,'start')]"))
	driver.findElement(By.cssSelector(".files .start")).click();
	sleepInSecond(2);
	
	Assert.assertEquals(driver.findElement(By.cssSelector(".name>a")).getText(), capture1_Name);
}

@Test
public void TC_02_Upload_MultipleFile() {
	driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	sleepInSecond(3);
	
	//selenium sendkey upload file ko quan tâm đến element có hiển thị hay k
	//Nó chỉ quan tâm thẻ input=type  là được
	
	WebElement fileInput=driver.findElement(By.xpath("//input[@type='file']"));
	fileInput.sendKeys(capture1_Path+"\n"+capture2_Path+"\n"+capture3_Path);
	sleepInSecond(3);
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