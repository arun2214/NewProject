package Seq_Hyd_Maven.Seq_Hyd_Maven_Proj;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.internal.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listners extends TestBase implements ITestListener {
	
	static ExtentReports extent = ExtentReportNG.extentReportsGenerator();
	static ExtentTest test;
	static ExtentReports report;
	
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	 
	@Override
	public  void onTestStart(ITestResult result) {
		
		StringBuilder testName = new StringBuilder("");

		Object[] parameters = result.getParameters();
		           
		test = extent.createTest(testName.append(parameters[0]).toString());
		
	    try {
			
	    	extentTest.set(test);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSuccess(result);
		extentTest.get().log(Status.PASS, "This test pass");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {	
		//WebDriver driver = TLDriverFactory.getTLDriver();
		WebDriver driver=TLDriverFactory.getTLDriver();
		 
		extentTest.get().log(Status.FAIL, "Test Case Failed");
		
		Object testObject = result.getInstance();
		Class cls= result.getTestClass().getRealClass();
		
			try {
				driver = (WebDriver)cls.getDeclaredField("driver").get(testObject);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
		
		
		try {
			extentTest.get().addScreenCaptureFromPath(getScreenShotpath(result.getParameters().toString(), driver),result.getMethod().getMethodName());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	//@Override
	/*
	 * public void onTestFailedWithTimeout(ITestResult result) { // TODO
	 * Auto-generated method stub
	 * //ITestListener.super.onTestFailedWithTimeout(result); }
	 */

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onFinish(context);
		
		extent.flush();
	}
	

}
