package designFrameworkUsingTestNG.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
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

	public void goTo() {
		driver.get("https://www.rahulshettyacademy.com/client");
	}

	public void loginApp(String username, String password) {
		userNamefield.sendKeys(username);
		passwordfield.sendKeys(password);
		loginBtn.click();
	}
}
