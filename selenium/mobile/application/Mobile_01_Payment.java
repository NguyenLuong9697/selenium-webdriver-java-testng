package mobile.application;

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

public class Mobile_01_Payment {
  
 @Test(groups="mobile")
 public void TC_01_Visa() {
	 System.out.println("Anatation:TC_01");
 }
 @Test(groups="mobile")
 public void TC_02_Cash() {
	 System.out.println("Anatation:TC_02");
 }
 @Test(groups="mobile")
 public void TC_03_Credit() {
	 System.out.println("Anatation:TC_03");
 }

 

}
