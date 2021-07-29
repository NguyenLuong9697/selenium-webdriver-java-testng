package api.application;

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

public class API_01_FechData {
  
 @Test(groups="api")
 public void TC_01_Create_User() {
	 System.out.println("Anatation:TC_01");
 }
 @Test(groups="api")
 public void TC_02_View_User() {
	 System.out.println("Anatation:TC_02");
 }
 @Test(groups="api")
 public void TC_03_Update_User() {
	 System.out.println("Anatation:TC_03");
 }
 @Test(groups="api")
 public void TC_04_Delete_User() {
	 System.out.println("Anatation:TC_02");
 }
 

}
