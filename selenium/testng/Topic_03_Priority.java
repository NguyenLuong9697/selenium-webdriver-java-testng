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

public class Topic_03_Priority {
  
  //Oder theo thứ tự sắp xếp theo alphabet (0-0 , A-Z)
 // Tên Chức năng-Số thứ tự- Tên TC
	//VD : User_01_Create_new_user
	//User_02_View_User
	//User_03_Move_User
	//User_04_Edit_User
	//User_05_Delete_User
 @Test(priority = 2)
 public void TC_01() {
	 System.out.println("Anatation:TC_01");
 }
 @Test(priority = 3)
 public void TC_02() {
	 System.out.println("Anatation:TC_02");
 }
 @Test(priority = 1)
 public void TC_03() {
	 System.out.println("Anatation:TC_03");
 }
  
}
