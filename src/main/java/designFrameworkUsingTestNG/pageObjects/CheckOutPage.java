package designFrameworkUsingTestNG.pageObjects;

// 4th page
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import designFrameworkUsingTestNG.Utils.AbstractComponents;

public class CheckOutPage extends AbstractComponents {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// Initializes @FindBy proxy elements of the current class instance/PAGE OBJECTS
		// with the driver (lazy initialization)
		// Actual elements are located when they are used
	}

// Locators/Elements------------------------------------------------------------------------------------
	// PageFactory Annotation
	@FindBy(xpath = "//div[contains(@class,'subtotal')]//button")
	WebElement checkOutBtn;

	@FindBy(xpath = "(//input [@class='input txt'])[1]")
	WebElement cvvField;
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement enterCountryField;
	
	@FindBy(css="section [class*=list-group] button")
	List<WebElement> countryList;

	// By locators
	By cvvFieldBy = By.xpath("(//input [@class='input txt'])[1]");
	By countryOptionsBy = By.cssSelector("section [class*=list-group]");

// Methods/Actions On Elements----------------------------------------------------------------------------
	// Go to checkout page
	public void goToCheckOutPage(String cvv) {
		checkOutBtn.click();
		waitForWebElementLocatedByToAppear(cvvFieldBy);
		cvvField.sendKeys(cvv);
	}
	
	// Select country
	public void selectCountry(String countryInitials, String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(enterCountryField, countryInitials).build().perform();
		waitForWebElementLocatedByToAppear(countryOptionsBy);
		WebElement desiredCountry = countryList.stream()
				.filter(country -> country.findElement(By.cssSelector("span")).getText().equalsIgnoreCase(countryName))
				.findFirst().orElse(null);
		if (desiredCountry != null) {
			desiredCountry.click();
		} else {
			System.out.println("Country not found. Please try again later");
			driver.quit();
		}
	}

}
