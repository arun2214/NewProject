package Seq_Hyd_Maven.Seq_Hyd_Maven_Proj;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Seq_Hyd_Maven.Seq_Hyd_Maven_Proj.Listners.class)
public class KeyWordLib {
	
@Test
  public static void login(WebDriver driver, String TestCase) throws  Exception
  {
		ExcelUtils.TestDataInteraction1(Constant.Path_TestData + Constant.File_TestData,"Login",TestCase);
		
		String sUserName = ExcelUtils.GetObjDict().get("UserName"); 
		String sPassword = ExcelUtils.GetObjDict().get("Pass"); 
		String sChannel  = ExcelUtils.GetObjDict().get("Channel");
		String sTransaction  = ExcelUtils.GetObjDict().get("Transaction");
		double sConfig_UserName = Double.valueOf(ExcelUtils.GetObjDict().get("Config_UserName"));
	
								
		//call application object class
		NDCLib.LoginFn(driver,sUserName,sPassword,sChannel,sConfig_UserName);
  }
}
