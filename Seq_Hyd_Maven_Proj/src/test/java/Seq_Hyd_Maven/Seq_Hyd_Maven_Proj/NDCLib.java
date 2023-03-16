package Seq_Hyd_Maven.Seq_Hyd_Maven_Proj;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Seq_Hyd_Maven.Seq_Hyd_Maven_Proj.Listners.class)
public class NDCLib extends PageObjects {
	
  @Test  

public static void LoginFn(WebDriver driver, String sUserName, String sPassword, String sChannel,
		double sConfig_UserName) throws InterruptedException {
	 
	//  if ((Integer.parseInt(sConfig_UserName))==1) {
	  if ((Math.round(sConfig_UserName))==1) {
			Listners.test.info("started G1...");
			driver.findElement(userName1).sendKeys(sUserName);
			Listners.test.info("Login.."+sUserName);
		} 
		    Thread.sleep(4000);
		    driver.findElement(Next1).click();
		    Thread.sleep(4000);
		//To locate input field for password and enter value
		driver.findElement(passWord).sendKeys(sPassword);
		//To locate and click on login button
		driver.findElement(cLickLogin).click();	
		Thread.sleep(4000);
		System.out.println("Test thread id is for Test1 :"+Thread.currentThread().getId());
		System.out.println("pass");
	

	
}
	  
	 
  }
  
  

