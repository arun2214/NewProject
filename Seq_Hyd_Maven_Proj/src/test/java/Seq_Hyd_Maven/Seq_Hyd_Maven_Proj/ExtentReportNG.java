package Seq_Hyd_Maven.Seq_Hyd_Maven_Proj;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	static ExtentReports extent;
	public static ExtentTest logger;
	public static ExtentReports extentReportsGenerator() {
		String Path = System.getProperty("user.dir", "/reports/index.html");
		ExtentSparkReporter reporter = new ExtentSparkReporter(Path);
		try {
			reporter.config().setReportName("NDC Portal Automation Results : Hostname -"+InetAddress.getLocalHost().getHostName()+" : User - "+System.getProperty("user.name"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reporter.config().setDocumentTitle("Test Execution Analysis");
	    extent = new ExtentReports();
		extent.attachReporter(reporter);
	    extent.setSystemInfo("Tester", "Arun Roy");
	    return extent;
	}

}
