package designFrameworkUsingTestNG.pageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// 1st page
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import designFrameworkUsingTestNG.Utils.AbstractComponents;

public class LandingPage extends AbstractComponents {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// Initializes @FindBy proxy elements of the current class instance/PAGE OBJECTS
		// with the driver (lazy initialization)
		// Actual elements are located when they are used
	}

// Locators/Elements------------------------------------------------------------------------------------
	// PageFactory Annotation
	@FindBy(id = "userEmail")
	WebElement userNamefield;

	@FindBy(id = "userPassword")
	WebElement passwordfield;

	@FindBy(id = "login")
	WebElement loginBtn;

// Methods/Actions On Elements----------------------------------------------------------------------------

	public void goTo() throws IOException {
		String rootDir = System.getProperty("user.dir");
		String fileDir = rootDir + "/src/main/java/designFrameworkUsingTestNG/resources/";
		Properties prop = new Properties();
		FileInputStream fisObj1 = new FileInputStream(fileDir+"qa.properties");
		prop.load(fisObj1);
		String url = prop.getProperty("url");
		driver.get(url);
	}

	public PoductCataloguePage loginApp(String username, String password) {
		userNamefield.sendKeys(username);
		passwordfield.sendKeys(password);
		loginBtn.click();
		return new PoductCataloguePage(driver);
	}
}
