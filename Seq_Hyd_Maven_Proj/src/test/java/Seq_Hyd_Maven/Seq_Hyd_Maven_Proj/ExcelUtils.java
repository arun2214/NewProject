package Seq_Hyd_Maven.Seq_Hyd_Maven_Proj;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils {
	
	//public static final String KeywordList1 = null;
	public static List<String> KeywordList1;
	private static XSSFSheet ExcelWSheet;
	private static XSSFSheet KeywordScenario;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFCell ExecutionFlag;
	private static XSSFRow Row;
	public static int TotalRowsNo;
	public static int TotalRowsTestScenario;
	public static int count;
	private static XSSFCell Keyword_Ref;
	private static XSSFCell ModuleRef;
	private static XSSFCell KeyWordData;
	private static XSSFCell TestCase;
	public static List<String> KeywordList;
	private static String testCaseName;
	private static String TCName;
	private static Hashtable<String, String> TestData_dict;
	
 
//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method

public static void setExcelFile(String Path,String SheetName) throws Exception {

		try {

			// Open the Excel file

		FileInputStream ExcelFile = new FileInputStream(Path);

		// Access the required test data sheet

		ExcelWBook = new XSSFWorkbook(ExcelFile);

		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		

		} catch (Exception e){

			throw (e);

		}

}

//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

public static String getCellData(int RowNum, int ColNum) throws Exception{

		try{
			
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			String CellData = Cell.getStringCellValue();

			return CellData;

			}catch (Exception e){

			return"";

			}

}

//This method is to write in the Excel cell, Row num and Col num are the parameters

public static void setCellData(String Result,  int RowNum, int ColNum) throws Exception	{

		try{

			Row  = ExcelWSheet.getRow(RowNum);
			
			Cell = Row.getCell(ColNum);


		if (Cell == null) {

			Cell = Row.createCell(ColNum);

			Cell.setCellValue(Result);

			} else {

				Cell.setCellValue(Result);

			}

// Constant variables Test Data path and Test Data file name

			FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);

				ExcelWBook.write(fileOut);

				fileOut.flush();

				fileOut.close();

			}catch(Exception e){

				throw (e);

		}

	}


//This method is to set & get Data from Test Scenario Sheet, fetching test case , execution flag and keyword reference

public static void getExcelFileScenario(String Path, int TCCount) throws Exception {
		
		try {
	
		FileInputStream ExcelFile = new FileInputStream(Path);
		// Access the required test data sheet
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheet("Test_Scenarios");
		TotalRowsNo = ExcelWSheet.getLastRowNum() +1;
		
			for (int i=TCCount; i<=TCCount; i++)
			{
				 ExecutionFlag = ExcelWSheet.getRow(i).getCell(3);
				 TestCase = ExcelWSheet.getRow(i).getCell(2);
				 String TestCaseNameData =  ExcelWSheet.getRow(i).getCell(2).toString();
				 ExcelUtils.setTestCase(TestCaseNameData);
				 Keyword_Ref = ExcelWSheet.getRow(i).getCell(1);
				 //Method
				 if (ExecutionFlag.toString().equals("Yes") ){//&& (TestCaseNameData.equals(TestCaseName)) ) {
					 System.out.println(ExecutionFlag);
					 System.out.println(TestCase);
					 ExcelUtils.AddKeywords(Keyword_Ref, Path);
					 Constant.TestCaseActive =true;
					 
				 }
		    }	
		
		} catch (Exception e){
	
			throw (e);
	
		}

	}


public static List<String> AddKeywords(XSSFCell keyword_Ref2, String Path) throws Exception {
	
	try {
		TotalRowsNo=0;
		FileInputStream ExcelFile = new FileInputStream(Path);
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		KeywordScenario =ExcelWBook.getSheet("Keyword_Scenario");
		TotalRowsTestScenario = KeywordScenario.getLastRowNum() +1;
		String KeyWordRef = keyword_Ref2.toString();
		ArrayList<String> KeywordList1 = new ArrayList<String>();
		
		for (int i=1; i<=TotalRowsTestScenario-1; i++)
		{
			 ModuleRef = KeywordScenario.getRow(i).getCell(2);
			 String ModuleRefData = ModuleRef.toString();
			if((KeyWordRef).equals(ModuleRefData))  {
				 KeyWordData= KeywordScenario.getRow(i).getCell(3);
				 String ListofKeyword = KeyWordData.toString();
				 //Method for keyword collection
				 KeywordList1.add(ListofKeyword);	
				
			}	
			  
	     }	
		 ExcelUtils.setList(KeywordList1);
		count = KeywordList1.size();
		System.out.println(KeywordList1.get(0));
	
		return KeywordList1;

	} catch (Exception e){

		throw (e);

	}
	  
}

///Test Data interaction

public static int TotalActiveRows( String Path, String FileName) throws Exception {
	
	try {
		
			FileInputStream ExcelFile = new FileInputStream(Path);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(FileName);
			TotalRowsNo = ExcelWSheet.getLastRowNum() +1;
			ExcelUtils.setList(TotalRowsNo);
			
	} catch (Exception e){

			throw (e);
	}
			return TotalRowsNo;
	}


//Setter
public static void setList(ArrayList KeywordList1) {
    ExcelUtils.KeywordList1 = KeywordList1;
}

//getter
public static List<String> getList() {
	    return KeywordList1;
}	
//Setter
public static void setList(int TotalRowsNo) {
    ExcelUtils.TotalRowsNo = TotalRowsNo;
}

//getter
public static int GetTestCaseCount() {
	// TODO Auto-generated method stub
	return TotalRowsNo;
}

//Setter --get test case name from test data sheet
public static void setTestCase(String testCaseName) {
  ExcelUtils.testCaseName = testCaseName;
}

//getter
public static String GetTestCase() {
	// TODO Auto-generated method stub
	return testCaseName;
}


public static Hashtable<String, String> TestDataInteraction1(String Path,String SheetName, String TestCaseName) throws Exception {

	try {	
		
		// Access the required test data sheet
		FileInputStream ExcelFile = new FileInputStream(Path);
		ExcelWBook = new XSSFWorkbook(ExcelFile);

		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		
		 Hashtable<String, String> TestData_dict = new Hashtable<String, String>();
		 //int noOfColumns = ExcelWSheet.getRow(0).getLastCellNum();
		 
		 int rows = ExcelWSheet.getLastRowNum();
	        int cols= ExcelWSheet.getRow(1).getLastCellNum();
	        
	        for(int r=0; r<rows; r++) {
	            
	            XSSFRow row=ExcelWSheet.getRow(r);
	            XSSFRow FirstRow=ExcelWSheet.getRow(0);
	            String TCfromTD = ExcelUtils.getCellData(r, 1).toString();
	            if(TestCaseName.equals(TCfromTD))
		            for(int c=0; c< cols; c++) {
		                
		                XSSFCell cell=row.getCell(c);
		                
		                XSSFCell ColName=FirstRow.getCell(c);
		              
		                
		                TestData_dict.put(ColName.toString(),cell.toString());
		                
		                switch(cell.getCellType())
		                {
		                case STRING: System.out.println(cell.getStringCellValue()); break;
		                case NUMERIC: System.out.println(cell.getNumericCellValue()); break;
		                case BOOLEAN: System.out.println(cell.getBooleanCellValue()); break;
		                
		                }
	            }
	                
	            }
		 
		 
	        ExcelUtils.setObjDict(TestData_dict);
		 return TestData_dict;	
	
	} catch (Exception e){

		throw (e);

	}

}

//carrying test data thru object dictionary from one class to another class
	public static void setObjDict(Hashtable<String, String> TestData_dict) {
		ExcelUtils.TestData_dict = TestData_dict;
	}

	//getter
	public static Hashtable<String, String> GetObjDict() {
		// TODO Auto-generated method stub
		return TestData_dict;
	}
		
	}

 	







