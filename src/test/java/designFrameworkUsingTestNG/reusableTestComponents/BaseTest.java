package designFrameworkUsingTestNG.reusableTestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		String browser = prop.getProperty("Browser").trim().toLowerCase();
		;

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

	@BeforeMethod(alwaysRun = true)
	public void launchApp() throws IOException {
		this.driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		this.driver.quit();
	}

// convert jasonData TO Map
	public static List<HashMap<String, String>> getJsonDataToMap(String filePath)
			throws JsonMappingException, JsonProcessingException {

		String jsonFilePath = filePath;
		String jsonContent;
		// convert jason to string (inbuilt)
		try {
			jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
		} catch (Exception e) {
			throw new RuntimeException("Error reading JSON file", e);
		}

		// convert string into list of Hashmaps (Must use jackson dataBind dependency)
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

// Take screenshot on failure
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		String rootDir = System.getProperty("user.dir");
		String screenShotsPath = rootDir + "/reports/"+ testCaseName + ".png";
		TakesScreenshot ts = (TakesScreenshot) driver;  // driver is initialized inside initialization method. So at this point driver has no life. Hence life is coming from listeners
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(screenShotsPath);
		FileUtils.copyFile(src, dest);
		return screenShotsPath;
	}

}
