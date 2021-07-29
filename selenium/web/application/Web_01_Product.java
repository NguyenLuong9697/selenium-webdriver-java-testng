package web.application;

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

public class Web_01_Product {
  
 @Test(groups="web")
 public void TC_01_Create_New_Product() {
	 System.out.println("Anatation:TC_01");
 }
 @Test(groups="web")
 public void TC_02_View_Product() {
	 System.out.println("Anatation:TC_02");
 }
 @Test(groups="web")
 public void TC_03_Delete_Product() {
	 System.out.println("Anatation:TC_03");
 }

 

}
