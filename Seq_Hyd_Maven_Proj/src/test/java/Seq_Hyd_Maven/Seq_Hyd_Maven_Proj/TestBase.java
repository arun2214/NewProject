package Seq_Hyd_Maven.Seq_Hyd_Maven_Proj;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;

public class TestBase {
	private static XSSFWorkbook ExcelWBook;
	private static XSSFSheet ExcelWSheet;
	public static int TotalRowsNo;
	private static XSSFCell ExecutionFlag;	
	DataFormatter formatter= new DataFormatter();
    protected WebDriverWait wait;
    //Do the test setup//
  
    @BeforeMethod
    @Parameters(value={"browser"})
    public void setupTest (@Optional String browser) throws MalformedURLException, InterruptedException {
        //Set & Get ThreadLocal Driver with Browser
        TLDriverFactory.setTLDriver(browser);
       // wait = new WebDriverWait(TLDriverFactory.getTLDriver(), 15);       
        System.setProperty("webdriver.chrome.driver","C:\\Selenium Installation process\\chrome\\s\\chromedriver.exe");
       Thread.sleep(5000);
      // OptionsManager.getChromeOptions();
   
    }
    
    public String getScreenShotpath(String TestCaseName,WebDriver driver) throws IOException {
		 TakesScreenshot ts =(TakesScreenshot)driver;
	     File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	     String Dest = System.getProperty("user.dir")+"\\Reports\\"+TestCaseName+".png";     
	    // String errflpath = Dest.getAbsolutePath();
	     File file = new File(Dest);
	     FileUtils.copyFile(scrFile, file);
	     return Dest;

	     }

    @AfterMethod
    public synchronized void tearDown() throws Exception {
        TLDriverFactory.getTLDriver().quit();
    }
	
    @DataProvider(name="DriveTest")
	public Object[] getdata() throws IOException {
		FileInputStream ExcelFile = new FileInputStream("C:\\Users\\Admin\\git\\repository11\\Seq_Hyd_Maven_Proj\\src\\test\\java\\TestData\\Test_Scenario.xlsx");
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheet("Test_Scenarios");
		int rowCount = ExcelWSheet.getPhysicalNumberOfRows();
		XSSFRow row1 = ExcelWSheet.getRow(0);
		int d= 0;
		int k =0;
		int s= 0;
		for (int i =0; i<rowCount-1;i++) {
			 ExecutionFlag = ExcelWSheet.getRow(i).getCell(3);
			row1 =ExcelWSheet.getRow(i+1);
			XSSFCell cell1=row1.getCell(3);
					//for (int j=0;j<coulcount;j++) {
					if (cell1.toString().equals("Yes")) {
						 s = s +1;
					}
					k= s;
		}
		int datas = k;
		
		XSSFRow row = ExcelWSheet.getRow(0);
		
		////Object Data[][]= new Object[rowCount-1][coulcount];
		Object Data[]= new Object[datas];
		int x=0;
		for (int m =0; m<rowCount-1;m++) {
			// ExecutionFlag = ExcelWSheet.getRow(m).getCell(3);
			row =ExcelWSheet.getRow(m+1);
			XSSFCell cell1=row.getCell(3);
					//for (int j=0;j<coulcount;j++) {
					if (cell1.toString().equals("Yes")) {
						XSSFCell cell = row.getCell(2);
						
							Data[x]= formatter.formatCellValue(cell);
							x = x +1;
					}		
					// x = x+1;
		}
		return Data;
		
	}
}