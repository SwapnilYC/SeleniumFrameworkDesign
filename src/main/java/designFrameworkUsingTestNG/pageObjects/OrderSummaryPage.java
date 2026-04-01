package designFrameworkUsingTestNG.pageObjects;

// 5th page

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import designFrameworkUsingTestNG.Utils.AbstractComponents;

public class OrderSummaryPage extends AbstractComponents {
	WebDriver driver;

	public OrderSummaryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// Initializes @FindBy proxy elements of the current class instance/PAGE OBJECTS
		// with the driver (lazy initialization)
		// Actual elements are located when they are used
	}

// Locators/Elements------------------------------------------------------------------------------------
	// PageFactory Annotation
	@FindBy(tagName = "h1")
	WebElement confirmationMessageEl;

	// By locators
	By confirmationMessageBy = By.tagName("h1");

// Methods/Actions On Elements----------------------------------------------------------------------------
//	check isOrderPlaced
	public Boolean isOrderPlaced() {
		waitForWebElementLocatedByToAppear(confirmationMessageBy);
		String actualConfirmMessage = confirmationMessageEl.getText();
		System.out.println(actualConfirmMessage);
		String requiredConfirmationMessage = "Thankyou for the order.";
		Boolean areMessageSame = actualConfirmMessage.equalsIgnoreCase(requiredConfirmationMessage);
		return areMessageSame;
	}

}
