package Seq_Hyd_Maven.Seq_Hyd_Maven_Proj;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class ExecutionReports {	
	public static ExtentTest test;
	static ExtentReports report;
	
	
public static void ExtentReport() {
		
		 report = new ExtentReports(System.getProperty("user.dir")+"/Reports/ExtentReportResults.html");	 
		 test = report.startTest("Extent Report Demo");	 
			
	}
	//pass cases
public static void  pass(String testcase,WebDriver driver) {
	
		 test.log(LogStatus.PASS, testcase);
		  
	 }
	//Failed cases
public static void  fail(String testcase,WebDriver driver) throws IOException {
			 
		ExecutionReports.test.log(LogStatus.FAIL, testcase);
		//calling method for screenshots  r
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExecutionReports.test.log(LogStatus.FAIL,test.addScreenCapture(captureBase64(driver))+ "Test Failed");		 			
	}
	
	//Capturing Screenshots method	
public static String captureBase64(WebDriver driver) throws IOException {
     String encodedBase64 = null;
     FileInputStream fileInputStream = null;
     File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
     File Dest = new File("C:\\Users\\Admin\\eclipse-workspace\\Sequential_Hybrid_Project\\Failed Screenshot\\Screenshots" + System.currentTimeMillis()
     + ".png");
     String errflpath = Dest.getAbsolutePath();
     FileUtils.copyFile(scrFile, Dest);
      
     try {
         
         fileInputStream =new FileInputStream(Dest);
         byte[] bytes =new byte[(int)Dest.length()];
         fileInputStream.read(bytes);
         encodedBase64 = new String(Base64.encodeBase64(bytes));

     }catch (FileNotFoundException e){
         e.printStackTrace();
     }
     return "data:image/png;base64,"+encodedBase64;    
     
     }
	
//report flush
public static void endTest()
	  {
		  report.endTest(test);	  
		  report.flush();
	  }

}
