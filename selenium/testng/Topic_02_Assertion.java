package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_02_Assertion {
  
	@BeforeMethod
	  public void beforeMethod() {
		  System.out.println("Assertion:Before Method");
	  }

	  @AfterMethod
	  public void afterMethod() {
		  System.out.println("Assertion:After Method");
	  }


	 @Test
	 public void TC_01() {
		 System.out.println("Assertion:TC_01");
	 }
	 @Test
	 public void TC_02() {
		 System.out.println("Assertion:TC_02");
	 }
	 @Test
	 public void TC_03() {
		 System.out.println("Assertion:TC_03");
	 }
	  @BeforeClass
	  public void beforeClass() {
		  System.out.println("Assertion::Before Class");
	  }

	  @AfterClass
	  public void afterClass() {
		  System.out.println("Assertion:After Class");
	  }

	  @BeforeTest
	  public void beforeTest() {
		  System.out.println("Assertion:Before Test");
	  }

	  @AfterTest
	  public void afterTest() {
		  System.out.println("Assertion:After Test");
	  }

	  @BeforeSuite
	  public void beforeSuite() {
		  System.out.println("Assertion:Before Suite");
	  }

	  @AfterSuite
	  public void afterSuite() {
		  System.out.println("Assertion:After Suite");
	  }

}
