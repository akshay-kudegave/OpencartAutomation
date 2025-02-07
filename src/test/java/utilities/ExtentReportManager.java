package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{

	public ExtentSparkReporter sparkReporter; //UI of the Report
	public ExtentReports extent; //populate common info on the report
	public ExtentTest test; //creating test case entries in the report and update status of the test methods
	
	String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    String reportName = "Test-Report-" + timestamp + ".html";
	
	public  void onStart(ITestContext context) {
	   
		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/"+reportName);
		
		sparkReporter.config().setDocumentTitle("Automation Report"); //Title of the report
		sparkReporter.config().setReportName("Functional Testing"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter); // Attaching spark reporter and extent
		
		extent.setSystemInfo("Computer Name", "localhost");
		
		String os = context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Environment", os);
		
		
		extent.setSystemInfo("Tester Name", "Akshay");
		extent.setSystemInfo("OS", "Windows 11");
		
		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser Name", browser);
		
		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
		
	  }
	
	public void onTestSuccess(ITestResult result) {
	   test=extent.createTest(result.getClass().getName());
	   test.assignCategory(result.getMethod().getGroups()); //to display groups in report
	   test.log(Status.PASS,result.getName()+" got successfully executed");
	  }
	
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName()); //create a new entry in the report
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL,result.getName()+" got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	  }
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName()); //create a new entry in the report
		test.assignCategory(result.getMethod().getGroups());
	    test.log(Status.SKIP,result.getName()+" got skipped"); // update status p/f/s
	    test.log(Status.INFO, result.getThrowable().getMessage());
	  }
	
	public void onFinish(ITestContext context) {
	    extent.flush();
	    
	    String pathOfExtentReport = System.getProperty("user.dir")+"/reports/"+reportName;
	    File extentReport = new File(pathOfExtentReport);
	    
	    try {
	    	Desktop.getDesktop().browse(extentReport.toURI());
	    }catch(IOException e) {
	    	e.printStackTrace();
	    }
	  }
}
