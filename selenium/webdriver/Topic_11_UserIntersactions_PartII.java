package webdriver;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_11_UserIntersactions_PartII {
	WebDriver driver;
	Actions action;
	String projectPath=System.getProperty("user.dir");
	Alert alert;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExcutor;
	String jsDragDropPath=projectPath+"/DragAndDrop/drag_and_drop_helper.js";
	String jQueryPath=projectPath+"/DragAndDrop/jquery_load_helper.js";
	
@BeforeClass
public void beforeClass() {
//driver = new FirefoxDriver();
	System.setProperty("webdriver.chrome.driver",projectPath+"\\browserDriver\\chromedriver.exe");
	driver = new ChromeDriver();
	jsExcutor=(JavascriptExecutor)driver;
	//driver=new FirefoxDriver();
	driver.manage().window().maximize();
	action=new Actions(driver);
	explicitWait=new WebDriverWait(driver, 15);
	
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
}
@Test
public void TC_01_HoverToElement_1() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	sleepInSecond(3);
	action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
	sleepInSecond(3);
	//verfiy
	Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
    
}
@Test
public void TC_02_RightClick() {
	driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	sleepInSecond(3);
	action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
	//dung x path
	//:not([attribute='value']
	//:not(#id)
	//:not(.class)
	//before hover
	Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-quit:not(.context-menu-hover):not(.context-menu-visible)")).isDisplayed());
	//hover vao Quit
	
	action.moveToElement(driver.findElement(By.cssSelector(".context-menu-icon-quit"))).perform();
	sleepInSecond(3);
	//verify

	Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-quit.context-menu-hover.context-menu-visible")).isDisplayed());
	//click button quit
	driver.findElement(By.cssSelector(".context-menu-icon-quit")).click();
	alert=explicitWait.until(ExpectedConditions.alertIsPresent());
	//or
	//alert=driver.switchTo().alert();
	sleepInSecond(5);
	alert.accept();
	sleepInSecond(5);

}
@Test
public void TC_03_DragAndDrop_HTML4() {
	driver.get("https://automationfc.github.io/kendo-drag-drop/");
	sleepInSecond(3);
	WebElement sourceCircle=driver.findElement(By.id("draggable"));
	WebElement targetCircle=driver.findElement(By.id("droptarget"));
	
	//chưa kéo
	Assert.assertEquals(targetCircle.getText(), "Drag the small circle here.");
	//keo tu sourceCircle đến targetCircle
	action.dragAndDrop(sourceCircle, targetCircle).perform();
	sleepInSecond(3);
	//verify
	Assert.assertEquals(targetCircle.getText(), "You did great!");
	//kiểm tra background color của targetCircle là #03a9f4
	String background_color=convertRGBA_To_Hex(targetCircle.getCssValue("background-color"));
	Assert.assertEquals(background_color,"#03a9f4");
}
//dung với jQuery va JavaScript chi work voi xPath
@Test
public void TC_04_DragAndDrop_HTML5_OnlyWorkCss() throws IOException {
	driver.get("https://automationfc.github.io/drag-drop-html5/");
	sleepInSecond(5);
	String sourceCss = "#column-a";
	String targetCss = "#column-b";
	String java_script = readFile(jsDragDropPath);

	// Inject Jquery lib to site
	// String jqueryscript = readFile(jqueryPath);
	// javascriptExecutor.executeScript(jqueryscript);

	// A to B
	java_script = java_script + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
	//System.out.println(java_script);
	jsExcutor.executeScript(java_script);
	sleepInSecond(3);
	Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-a']/header")).getText(), "B");
	Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-b']/header")).getText(), "A");
	// B to A
	jsExcutor.executeScript(java_script);
	//Thread.sleep(3000);
	sleepInSecond(3);
	Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-a']/header")).getText(), "A");
	Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-b']/header")).getText(), "B");
	//Assert.assertTrue(isElementDisplayed("//div[@id='column-b']/header[text()='B']"));
}
@Test
public void TC_05_DragAndDrop_XPath_Offset() throws AWTException {
	driver.get("https://automationfc.github.io/drag-drop-html5/");
	sleepInSecond(5);
	drag_the_and_drop_html5_by_xpath("//div[@id='column-a']", "//div[@id='column-b']");
	sleepInSecond(3);
	Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-a']/header")).getText(), "B");
	Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-b']/header")).getText(), "A");
	
	drag_the_and_drop_html5_by_xpath("//div[@id='column-b']", "//div[@id='column-a']");
	sleepInSecond(3);
	Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-a']/header")).getText(), "A");
	Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-b']/header")).getText(), "B");
}
public String readFile(String file) throws IOException {
	Charset cs = Charset.forName("UTF-8");
	FileInputStream stream = new FileInputStream(file);
	try {
		Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
		StringBuilder builder = new StringBuilder();
		char[] buffer = new char[8192];
		int read;
		while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
			builder.append(buffer, 0, read);
		}
		return builder.toString();
	} finally {
		stream.close();
	}
}
@AfterClass 
public void afterClass() {
	driver.quit();
}
public String convertRGBA_To_Hex(String rgba) {
	String color = Color.fromString(rgba).asHex();
	return color;
}
public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

	WebElement source = driver.findElement(By.xpath(sourceLocator));
	WebElement target = driver.findElement(By.xpath(targetLocator));

	// Setup robot
	Robot robot = new Robot();
	robot.setAutoDelay(500);

	// Get size of elements
	Dimension sourceSize = source.getSize();
	Dimension targetSize = target.getSize();

	// Get center distance
	int xCentreSource = sourceSize.width / 2;
	int yCentreSource = sourceSize.height / 2;
	int xCentreTarget = targetSize.width / 2;
	int yCentreTarget = targetSize.height / 2;

	Point sourceLocation = source.getLocation();
	Point targetLocation = target.getLocation();
	System.out.println(sourceLocation.toString());
	System.out.println(targetLocation.toString());

	// Make Mouse coordinate center of element
	sourceLocation.x += 20 + xCentreSource;
	sourceLocation.y += 110 + yCentreSource;
	targetLocation.x += 20 + xCentreTarget;
	targetLocation.y += 110 + yCentreTarget;

	System.out.println(sourceLocation.toString());
	System.out.println(targetLocation.toString());

	// Move mouse to drag from location
	robot.mouseMove(sourceLocation.x, sourceLocation.y);

	// Click and drag
	robot.mousePress(InputEvent.BUTTON1_MASK);
	robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

	// Move to final position
	robot.mouseMove(targetLocation.x, targetLocation.y);

	// Drop
	robot.mouseRelease(InputEvent.BUTTON1_MASK);
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
