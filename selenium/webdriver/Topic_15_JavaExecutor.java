package webdriver;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_15_JavaExecutor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath=System.getProperty("user.dir");
	Select select;
@BeforeClass
public void beforeClass() {
	//driver = new FirefoxDriver();
	System.setProperty("webdriver.chrome.driver",projectPath+"\\browserDriver\\chromedriver.exe");
	driver = new ChromeDriver();
	jsExecutor=(JavascriptExecutor)driver;
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	//driver.get("");
}

@Test
public void TC_01_JavaScriptExecutor() {
	//truy cập trang http://live.demoguru99.com/ = JE
	navigateToUrlByJS("http://live.demoguru99.com/");
	sleepInSecond(2);
	//Lấy domain của page = JS
	Assert.assertEquals("live.demoguru99.com", (String)executeForBrowser("return document.domain;"));
	// Lấy URL của page =JS
	Assert.assertEquals("http://live.demoguru99.com/",(String)executeForBrowser("return document.URL;"));
	//click vao tab Mobile = JS
	clickToElementByJS("//a[text()='Mobile']");
	//thêm Galaxy vao gio hang = JS
	clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button[contains(@class,'btn-cart')]");
	//verify text Samsung Galaxy was added to your shopping cart. đc hiển thị = JS
	Assert.assertTrue(isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
	//click vao Customer Service
	clickToElementByJS("//a[text()='Customer Service']");
	sleepInSecond(2);
	//verify title của page
	Assert.assertEquals("Customer Service", (String)executeForBrowser("return document.title;"));
	//scroll đến element NEWSLETTER
	scrollToElement("//input[@id='newsletter']");
	String email="johnthomas"+randomEmail();
	//send key vao NEWSLETTER
	sendkeyToElementByJS("//input[@id='newsletter']", email);
	//click vao button Subscribe
	clickToElementByJS("//button[@title='Subscribe']");
	//verify text Thank you for your subscription.
	Assert.assertTrue(isExpectedTextInInnerText("Thank you for your subscription."));
	//navigative đến http://demo.guru99.com/v4/
	navigateToUrlByJS("http://demo.guru99.com/v4/");
	sleepInSecond(2);
	Assert.assertEquals("demo.guru99.com", (String)executeForBrowser("return document.domain;"));
}

@Test
public void TC_02_ValidationMessage() {
	navigateToUrlByJS("https://automationfc.github.io/html5/index.html");
	sleepInSecond(2);
	//click vao button Submit
	clickToElementByJS("//input[@name='submit-btn']");
	sleepInSecond(2);
	Assert.assertEquals("Please fill out this field.", getElementValidationMessage("//input[@id='fname']"));
	//input vao name
	sendkeyToElementByJS("//input[@id='fname']", "John Thomas");
	clickToElementByJS("//input[@name='submit-btn']");
	sleepInSecond(2);
	Assert.assertEquals("Please fill out this field.", getElementValidationMessage("//input[@id='pass']"));
	//send key vao password
	sendkeyToElementByJS("//input[@id='pass']", "123456");
	clickToElementByJS("//input[@name='submit-btn']");
	sleepInSecond(2);
	Assert.assertEquals("Please fill out this field.", getElementValidationMessage("//input[@id='em']"));
	//send mail ko hợp lệ
	/*sendkeyToElementByJS("//input[@id='em']", "123");
	clickToElementByJS("//input[@name='submit-btn']");
	sleepInSecond(2);
	Assert.assertEquals("Please included.", getElementValidationMessage("//input[@id='em']"));
	// send mail không hợp lệ
	sendkeyToElementByJS("//input[@id='em']", "123!@#");
	clickToElementByJS("//input[@name='submit-btn']");
	sleepInSecond(2);
	Assert.assertEquals("Please enter an email address.", getElementValidationMessage("//input[@id='em']"));
	// send mail không hợp lệ
	sendkeyToElementByJS("//input[@id='em']", "123@456");
	clickToElementByJS("//input[@name='submit-btn']");
	sleepInSecond(2);
	Assert.assertEquals("Please match the requested format.", getElementValidationMessage("//input[@id='em']"));
*/
	String email="johnthomas"+randomEmail();
	sendkeyToElementByJS("//input[@id='em']",email);
	clickToElementByJS("//input[@name='submit-btn']");
	sleepInSecond(2);
	Assert.assertEquals("Please select an item in the list.", getElementValidationMessage("//select"));
	select=new Select(driver.findElement(By.xpath("//select")));
	select.selectByVisibleText("DA NANG");
	Assert.assertEquals("DA NANG",select.getFirstSelectedOption().getText());
	clickToElementByJS("//input[@name='submit-btn']");
}

@Test
public void TC_03_HTML5_ValidationMessage() {
	navigateToUrlByJS("https://login.ubuntu.com/");
	sleepInSecond(2);
	if(isElementDisplay(By.xpath("//div[@role='dialog']"))) {
		driver.findElement(By.xpath("//button[@id='cookie-policy-button-accept']")).click();
	}
	sendkeyToElementByJS("//form[@id='login-form']//input[@id='id_email']","a");
	sleepInSecond(2);
	clickToElementByJS("//span[text()='Log in']");
	Assert.assertEquals("Please include an '@' in the email address. 'a' is missing an '@'.", getElementValidationMessage("//form[@id='login-form']//input[@id='id_email']"));
	navigateToUrlByJS("https://sieuthimaymocthietbi.com/account/register");
	sleepInSecond(2);
	clickToElementByJS("//button[text()='Đăng ký']");
	sleepInSecond(5);
	Assert.assertEquals("Please fill out this field.", getElementValidationMessage("//input[@id='lastName']"));
	//Assert.assertEquals(driver.findElement(By.xpath(".//span[@class='help-block form-error']")).getText(), "Không được để trống");
	//Assert.assertTrue(isExpectedTextInInnerText("Không được để trống"));
	//
	navigateToUrlByJS("https://warranty.rode.com/");
	sleepInSecond(2);
	clickToElementByJS("//button[contains(text(),'Register')]");
	Assert.assertEquals("Please fill out this field.", getElementValidationMessage("//input[@id='firstname']"));
}

@Test
public void TC_04_RemoveAttribute() {
	navigateToUrlByJS("http://demo.guru99.com/v4");
	sleepInSecond(2);
	driver.findElement(By.xpath("//a[text()='here']")).click();
	//nhap email
	String emailAddress="john"+randomEmail();
	driver.findElement(By.name("emailid")).sendKeys(emailAddress);
	//click button submit
	driver.findElement(By.name("btnLogin")).click();
	//get username & password
	String userID=driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
	String password=driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
	//
	navigateToUrlByJS("http://demo.guru99.com/v4");
	sleepInSecond(2);
	driver.findElement(By.name("uid")).sendKeys(userID);
	driver.findElement(By.name("password")).sendKeys(password);
	//click login
	driver.findElement(By.name("btnLogin")).click();
	sleepInSecond(3);
	Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(),"Welcome To Manager's Page of Guru99 Bank");
	//
	By username=By.name("name");
	By gender_male=By.xpath("//td[text()='Gender']/following-sibling::td//input[1]");
	By gender_femal=By.xpath("//td[text()='Gender']/following-sibling::td//input[last()]");
	By dob=By.name("dob");
	By add=By.name("addr");
	By city_ui=By.name("city");
	By state_ui=By.name("state");
	By pinno=By.name("pinno");
	By telephoneno=By.name("telephoneno");
	By email_ui=By.name("emailid");
	By pass_ui=By.name("password");
	
	//click New Customer
	driver.findElement(By.xpath("//a[text()='New Customer']")).click();
	//nhap cac thong tin
	driver.findElement(username).sendKeys("Thomas");
	//chon female
	driver.findElement(gender_femal).click();
	//date of birth
	//remove attribute  type='date'
	removeAttributeInDOM("//input[@name='dob']", "type");
	driver.findElement(dob).sendKeys("1986-01-02");
	//address
	driver.findElement(add).sendKeys("123 Po Boxing");
	//city
	driver.findElement(city_ui).sendKeys("California");
	//state
	driver.findElement(state_ui).sendKeys("Hawai");
	//pin
	driver.findElement(pinno).sendKeys("987889");
	//mobile
	driver.findElement(telephoneno).sendKeys("087579293");
	//email
	driver.findElement(email_ui).sendKeys(emailAddress);
	//pass
	driver.findElement(pass_ui).sendKeys(password);
	//submit
	driver.findElement(By.name("sub")).click();
	//verify
	Assert.assertEquals(driver.findElement(By.cssSelector(".heading3")).getText(), "Customer Registered Successfully!!!");
	String customerID=driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),"Thomas" );
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),"female");
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),"1986-01-02" );
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),"123 Po Boxing" );
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),"California");
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),"Hawai");
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),"987889");
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),"087579293" );
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),emailAddress );

}

@Test
public void TC_05_CreateAnAccout() {
	navigateToUrlByJS("http://live.demoguru99.com/");
	sleepInSecond(2);
	//click vao My Account o header
	clickToElementByJS("//div[@id='header-account']//a[@title='My Account']");
	sleepInSecond(2);
	//click vao Create An Account
	clickToElementByJS("//a[@title='Create an Account']");
	sleepInSecond(2);
	//send key vao cac truong
	sendkeyToElementByJS("//input[@id='firstname']", "Hana");
	sendkeyToElementByJS("//input[@id='lastname']", "Nguyen");
	String email="hana"+randomEmail();
	sendkeyToElementByJS("//input[@id='email_address']", email);
	sendkeyToElementByJS("//input[@id='password']", "123456");
	sendkeyToElementByJS("//input[@id='confirmation']", "123456");
	clickToElementByJS("//button[@title='Register']");
	String fullName="Hana "+"Nguyen";
	//verify
	Assert.assertTrue(isExpectedTextInInnerText("Thank you for registering with Main Website Store."));
	
	String user_info=driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p")).getText();
	Assert.assertTrue(user_info.contains(fullName));
	Assert.assertTrue(user_info.contains(email));
	//Log out
	clickToElementByJS("//div[@id='header-account']//a[@title='Log Out']");
	sleepInSecond(5);
	Assert.assertEquals("http://live.demoguru99.com/index.php/",(String) executeForBrowser("return document.URL;"));
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
public String randomEmail() {
	Random rd=new Random();
	return rd.nextInt(1000)+"@mail.vn";
	
}
public Object executeForBrowser(String javaScript) {
	return jsExecutor.executeScript(javaScript);
}

public String getInnerText() {
	return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
}

public boolean isExpectedTextInInnerText(String textExpected) {
	String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
	return textActual.equals(textExpected);
}

public void scrollToBottomPage() {
	jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
}

public void navigateToUrlByJS(String url) {
	jsExecutor.executeScript("window.location = '" + url + "'");
}

public void highlightElement(String locator) {
	WebElement element = getElement(locator);
	String originalStyle = element.getAttribute("style");
	jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
	sleepInSecond(1);
	jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
}

public void clickToElementByJS(String locator) {
	jsExecutor.executeScript("arguments[0].click();", getElement(locator));
}

public void scrollToElement(String locator) {
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
}

public void sendkeyToElementByJS(String locator, String value) {
	jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
}

public void removeAttributeInDOM(String locator, String attributeRemove) {
	jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
}

public String getElementValidationMessage(String locator) {
	return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
}

public boolean isImageLoaded(String locator) {
	boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(locator));
	if (status) {
		return true;
	} else {
		return false;
	}
}

public WebElement getElement(String locator) {
	return driver.findElement(By.xpath(locator));
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