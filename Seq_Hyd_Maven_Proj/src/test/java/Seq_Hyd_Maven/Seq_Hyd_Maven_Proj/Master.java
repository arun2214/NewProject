package Seq_Hyd_Maven.Seq_Hyd_Maven_Proj;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
@Listeners(Seq_Hyd_Maven.Seq_Hyd_Maven_Proj.Listners.class)

public class Master extends TestBase {	
	private String Path;	
  
    @Test(dataProvider="DriveTest") 
	public void MainScript(String TestCase) throws Exception {
		
    	String str = TestCase;
        
		String[] arrOfStr = str.split("_", 0);
		
        System.out.println(arrOfStr[1]);
		
		WebDriver driver=TLDriverFactory.getTLDriver();
		
		int n=Integer.parseInt(arrOfStr[1]);  
	    
		ExcelUtils.getExcelFileScenario(Constant.Path_Test_Scenario + Constant.File_Test_Scenario, n);	  
		
	    Thread.sleep(2000);
		
		TLDriverFactory.getTLDriver().navigate().to(Constant.Url);
		
		Thread.sleep(5000);
		
		ArrayList<String> ListOfKeyWord = (ArrayList<String>) ExcelUtils.getList();
		
		int TotalKeyWordCount = ListOfKeyWord.size(); 
		
		 String TCfromTestScenario = ExcelUtils.GetTestCase();
		
		 for (int i=0; i<=TotalKeyWordCount-1; i++)
			{  
			  ExcelUtils.TestDataInteraction1(Constant.Path_TestData + Constant.File_TestData,ListOfKeyWord.get(i),TCfromTestScenario);
				
			  switch (ListOfKeyWord.get(i))
				{
				
					case "Login": 
						
						KeyWordLib.login(driver,TestCase);
						
						System.out.println("Pass");
						
						break;
						
					case "PageAction": 
						
						KeyWordLib.login(driver,TestCase);
						
						System.out.println("Pass");
						
						break;
						
					case "LogOut":
						
						KeyWordLib.login(driver,TestCase);
						
						System.out.println("Pass");
						
						break;	
						
					case "BankDetails":	
						
						System.out.println("Bank Details pass");
						
						break;
						
					default: 
								
				}
				
			} 
		 
  }
    
}

	    

	 
		

	

