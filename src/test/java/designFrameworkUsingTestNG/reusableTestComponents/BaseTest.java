package designFrameworkUsingTestNG.reusableTestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;

import designFrameworkUsingTestNG.pageObjects.LandingPage;

public class BaseTest {
	public WebDriver driver;

	public WebDriver initializeDriver() throws IOException {

		// Properties from properties class
		Properties prop = new Properties();
		String rootDir = System.getProperty("user.dir");
		String fileDir = rootDir + "/src/main/java/designFrameworkUsingTestNG/resources/";
		FileInputStream fisObj1 = new FileInputStream(fileDir + "GlobalData.properties");
		prop.load(fisObj1);

		// Extract browser value
		String browser = prop.getProperty("Browser");
		System.out.println("Browser is: " + browser);

		// Compare browser value & initialize webdriver accordingly
		if (browser.contains("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.contains("edge")) {
			driver = new EdgeDriver();
		} else if (browser.contains("firefox")) {
			driver = new FirefoxDriver();
		}
		System.out.println("Browser is: " + browser);
		driver.manage().window().setSize(new Dimension(1920, 1080));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		return driver;
	}

	public LandingPage launchApp() throws IOException {
		driver = initializeDriver();
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

}
