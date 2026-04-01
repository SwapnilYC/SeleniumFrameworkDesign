package designFrameworkUsingTestNG.pageObjects;

// 3rd page
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import designFrameworkUsingTestNG.Utils.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// Initializes @FindBy proxy elements of the current class instance/PAGE OBJECTS
		// with the driver (lazy initialization)
		// Actual elements are located when they are used
	}

// Locators/Elements------------------------------------------------------------------------------------
	// PageFactory Annotation
	@FindBy(css = ".ng-animating")
	WebElement loaderOverlay;

	@FindBy(id = "toast-container")
	WebElement toasterPopUp;

	@FindBy(xpath = "//button[contains(@routerlink,'cart')]")
	WebElement goToCartBtn;

	@FindBy(xpath = "//div[@class='cart']//li")
	List<WebElement> cartProducts;

	// By locators
	By tosterPopUpBy = By.id("toast-container");
	By loaderOverlayBy = By.cssSelector(".ng-animating");
	By productsInCart = By.className("cart");

// Methods/Actions On Elements----------------------------------------------------------------------------
	// Wait to appear toaster & disappear loader then click go to cart btn
	public void goToCartPage() throws InterruptedException {
		waitForWebElementToAppear(toasterPopUp);
		waitForWebElementToDisppear(loaderOverlay);
		// Watch lecture 164
		Thread.sleep(1500);
		waitForWebElementToBeClickable(goToCartBtn);
		goToCartBtn.click();
	}

	// Wait for Elements to load in cart page & check that newly added product is
	// present in cart
	public Boolean isProductPresent(String productName) {
		waitForWebElementLocatedByToAppear(productsInCart);
		Boolean isItemPresent = cartProducts.stream()
				.anyMatch(e -> e.findElement(By.tagName("h3")).getText().equalsIgnoreCase(productName));
		return isItemPresent;
	}

}
