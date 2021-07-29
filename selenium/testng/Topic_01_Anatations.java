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

public class Topic_01_Anatations {
  
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("Anatation:Before Method");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("Anatation:After Method");
  }


 @Test
 public void TC_01() {
	 System.out.println("Anatation:TC_01");
 }
 @Test
 public void TC_02() {
	 System.out.println("Anatation:TC_02");
 }
 @Test
 public void TC_03() {
	 System.out.println("Anatation:TC_03");
 }
  @BeforeClass
  public void beforeClass() {
	  System.out.println("Anatation:Before Class");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("Anatation:After Class");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("Anatation:Before Test");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("Anatation:After Test");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("Anatation:Before Suite");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("Anatation:After Suite");
  }

}
