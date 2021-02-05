package com.app.base;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.api.reports.ReportUtil;
import com.api.support.Xls_Reader;
import com.api.utilities.Constants;
import com.api.utilities.Util;
import com.api.utilities.Zip;
import com.relevantcodes.extentreports.ExtentTest;

public class DriverScript{

	public static String TEST_DATA_PATH = System.getProperty("user.dir")+"/src/main/java/com/api/data";
	public static Xls_Reader xls = null, xlsController = new Xls_Reader(TEST_DATA_PATH + File.separator + "controller.xlsx");
	public static int rowNum = 2, rowNumController = 2;
	public static int rowNumExecutableTC = 2;
	public static int count= 0;
	public static String testCaseName;
	public static String testCaseId, testType;
	public static boolean continueRun = false;
	public static String baseURI = null, baseURI_POST = null;
	
	public static boolean isTestCaseRunnable(String tcId){
		boolean isTestCaseRunnable = false;
		continueRun = false;
		rowNumController = xlsController.getCellRowNum(Constants.TEST_DATA, Constants.TEST_CASE_ID, tcId);
		rowNum = rowNumController;
		testCaseId = tcId;
		testCaseName = xlsController.getCellData(Constants.TEST_DATA, Constants.TEST_CASE_NAME, rowNum);
		testType = xlsController.getCellData(Constants.TEST_DATA, "TestType", rowNum);
		if(xlsController.getCellData(Constants.TEST_DATA, Constants.TEST_CASE_RUNMODE, rowNum).equalsIgnoreCase(Constants.TEST_CASE_RUNMODE_YES)) {	
			continueRun = true;
			isTestCaseRunnable = true;
		}else{
			System.out.println("Please check the runmode of TestCaseID '" + testCaseId + "'");
			isTestCaseRunnable = false;
		}
		return isTestCaseRunnable;
	}
		
	public static String getTestDataSheetName(){
		String testDataSheet=xlsController.getCellData(Constants.TEST_DATA, Constants.TEST_DATA_SHEET_NAME, rowNum);
		return testDataSheet;
	}
		
		
	@BeforeMethod
	public void beforeMethod() {

	}

	@AfterMethod
	public void afterMethod() { 
		ReportUtil.test = null;
	}
    
		
	@AfterClass()
	public void afterClass() throws IOException{
		Util.openHTMLReport();
		Zip.zipFile();
	}
	
	
	@BeforeClass()
	public void init() throws IOException {
		
	}
    	
}
