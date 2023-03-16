package Seq_Hyd_Maven.Seq_Hyd_Maven_Proj;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class test {
	private static XSSFWorkbook ExcelWBook;
	private static XSSFSheet ExcelWSheet;
	public static int TotalRowsNo;
	private static XSSFCell ExecutionFlag;
	
	DataFormatter formatter= new DataFormatter();
	
	
//	  @Test(dataProvider="DriveTest") 
//	  public void testdata(String data1) throws
//	  Exception {
//	  
//	  String str = data1; String[] arrOfStr = str.split("_", 0);
//	  System.out.println(arrOfStr[1]);
//	  
//	  }
	 
	
	/*
	 * @Test(dataProvider="DriveTest") public void MainScript(String data1) throws
	 * Exception { //WebDriver driver = null; String str = data1; String[] arrOfStr
	 * = str.split("_", 0); System.out.println(arrOfStr[1]); }
	 */
	
	//@SuppressWarnings("unlikely-arg-type")
	@DataProvider(name="DriveTest")
	public Object[] getdata() throws IOException {
		FileInputStream ExcelFile = new FileInputStream("C:\\Users\\Admin\\eclipse-workspace\\Seq_Hyd_Maven_Proj\\TestData\\Test_Scenario.xlsx");
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheet("Test_Scenarios");
		int rowCount = ExcelWSheet.getPhysicalNumberOfRows();
		
		XSSFRow row1 = ExcelWSheet.getRow(0);
		int d= 0;
		int k =0;
		for (int i =0; i<rowCount-1;i++) {
			 ExecutionFlag = ExcelWSheet.getRow(i).getCell(3);
			row1 =ExcelWSheet.getRow(i+1);
			XSSFCell cell1=row1.getCell(3);
					//for (int j=0;j<coulcount;j++) {
					if (cell1.toString().equalsIgnoreCase("Yes")) {
						 k = d+i;
					}
					k= k+1;
		}
		int datas = k;
		
		XSSFRow row = ExcelWSheet.getRow(0);
		//int coulcount =row.getLastCellNum();
		
		//Object Data[][]= new Object[rowCount-1][coulcount];
		//Object Data[][]= new Object[rowCount-1][coulcount];
		Object Data[]= new Object[datas-1];
		
		for (int m =0; m<datas-1;m++) {
			 ExecutionFlag = ExcelWSheet.getRow(m).getCell(3);
			row =ExcelWSheet.getRow(m+1);
			//XSSFCell cell1=row.getCell(3);
					//for (int j=0;j<coulcount;j++) {
					//if (cell1.toString().equalsIgnoreCase("Yes")) {
						XSSFCell cell = row.getCell(2);

							Data[m]= formatter.formatCellValue(cell);
					//}
					
		}
		return Data;
	}

}
