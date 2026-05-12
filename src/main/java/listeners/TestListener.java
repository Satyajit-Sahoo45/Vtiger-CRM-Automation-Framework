package listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.BaseClass;
import utils.ScreenShotUtil;
import utils.UtilityObjectClass;

public class TestListener implements ISuiteListener, ITestListener{
	public ExtentReports report;
	public static ExtentTest test;
	
	@Override
	public void onStart(ISuite suite) {
		Reporter.log("Suite Execution Started-Adv report configuration", true);
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "Test-Report-" + timeStamp + ".html";
		
//		Configure the Report
		ExtentSparkReporter spark = new ExtentSparkReporter("./extentReports/"+repName);
		spark.config().setDocumentTitle("Reports of CRM Vtiger");
		spark.config().setReportName("Vtiger Contact_Org Reports");
		spark.config().setTheme(Theme.DARK);
		
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Browser", "Chrome-147.0.7727.138");
		report.setSystemInfo("OS", "Windows - 11th Gen");
	}

	
	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		test = report.createTest(testName);
		UtilityObjectClass.setTest(test);
		test.log(Status.INFO, testName + " : Test execution started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		test.log(Status.PASS, testName + " : Test execution success");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		test.log(Status.SKIP, testName + " : Test execution skipped");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		
		Object testClass = result.getInstance(); //Get Current Test Class Object
		
		/*
		 * Currently testClass is Object type. Object class does not have getDriver() method.
		 * so we cast it into BaseClass.
		 */
		WebDriver driver = ((BaseClass) testClass).getDriver();
		
		String testName = result.getName();
		test.log(Status.FAIL, "Test Execution Failed for " + testName);
		String imgPath = ScreenShotUtil.captureScreenShot(driver, testName);
		test.addScreenCaptureFromPath(imgPath);
		test.log(Status.FAIL, "Test case FAILED cause is: " + result.getThrowable());
		test.log(Status.FAIL, "Screenshot has been taken....check in screenshot folder");
	}

	
	@Override
	public void onFinish(ISuite suite) {
		test.log(Status.INFO, "Suite Execution Ended-Adv report configuration");
		report.flush();
	}

	

}
