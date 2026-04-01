package designFrameworkUsingTestNG.Utils;

// 0th page
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import designFrameworkUsingTestNG.pageObjects.CartPage;

public class AbstractComponents {
	WebDriver driver;

// Locators/Elements------------------------------------------------------------------------------------
	// PageFactory Annotation
	@FindBy(id = "toast-container")
	WebElement toasterPopUp;

	@FindBy(css = ".ng-animating")
	WebElement loaderOverlay;

	@FindBy(xpath = "//button[contains(@routerlink,'cart')]")
	WebElement goToCartBtn;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
	}

	// Wait to Appear WebElement
	public void waitForWebElementToAppear(WebElement el) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(el));
	}

	// Wait to Appear WebElement located By
	public void waitForWebElementLocatedByToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	// Wait to Disappear WebElement
	public void waitForWebElementToDisppear(WebElement el) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(el));
	}

	// Wait to Disappear WebElement located By
	public void waitForWebElementLocatedByToDisppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}

	// Wait an element to be clickable
	public void waitForWebElementToBeClickable(WebElement el) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(el));
	}

// Methods/Actions On Common Elements----------------------------------------------------------------------------
	// 1. Header: goToCart Btn -> common for all pages -> can be clicked from any
	// page
	// Wait to appear toaster & disappear loader then click go to cart btn
	public CartPage goToCartPage() throws InterruptedException {
		waitForWebElementToAppear(toasterPopUp);
		waitForWebElementToDisppear(loaderOverlay);
		// Watch lecture 164
		Thread.sleep(1500);
//			waitForWebElementToBeClickable(goToCartBtn);
		goToCartBtn.click();
		return new CartPage(driver);
	}

}
