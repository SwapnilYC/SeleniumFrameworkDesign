package designFrameworkUsingTestNG.reusableTestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	public static ExtentReports getReportObject() {
		String rootDir = System.getProperty("user.dir");
		String path = rootDir + "/reports/ExtentHTMLReport/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Ecommerce Website Demo reports");
		reporter.config().setReportName("Main website's extent report");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Swapnil");
		return extent;
	}

}
