package designFrameworkUsingTestNG.reusableTestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends BaseTest implements ITestListener {

	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {  // this result object holds the driver of test case failing
		// Giving life to driver
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			// fields are associated with class not the methods
		} catch (Exception e) {
			e.getMessage();
		}
		
		// logging the method name & error message
		test.log(Status.FAIL, result.getMethod().getMethodName() + " is failed");
		test.fail(result.getThrowable()); // result.getThrowable() -> Gives the error msg
		
		// Take & attach screenshot
		String methodName = result.getMethod().getMethodName();
		String screenshotPath = null;
		try {
			screenshotPath = getScreenShot(methodName, driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(screenshotPath, methodName);
	}
	

	@Override
	public void onTestSuccess(ITestResult result) {
		ITestListener.super.onTestSuccess(result);
		test.log(Status.PASS, result.getMethod().getMethodName() + " is passed");
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
