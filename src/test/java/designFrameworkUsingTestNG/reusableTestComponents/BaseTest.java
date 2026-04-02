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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import designFrameworkUsingTestNG.pageObjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException {

		// Properties from properties class
		Properties prop = new Properties();
		String rootDir = System.getProperty("user.dir");
		String fileDir = rootDir + "/src/main/java/designFrameworkUsingTestNG/resources/";
		FileInputStream fisObj1 = new FileInputStream(fileDir + "GlobalData.properties");
		prop.load(fisObj1);

		// Extract browser value
		String browser = prop.getProperty("Browser").trim().toLowerCase();;

		// Compare browser value & initialize webdriver accordingly
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.manage().window().setSize(new Dimension(1920, 1080));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		return driver;
	}

	@BeforeMethod(alwaysRun=true)
	public void launchApp() throws IOException {
		this.driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		this.driver.quit();
	}

}
