package DesignPrinciplesOfSeleniumFrameWork.UsingTestNG_TestingFramework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import designFrameworkUsingTestNG.pageObjects.CartPage;
import designFrameworkUsingTestNG.pageObjects.CheckOutPage;
import designFrameworkUsingTestNG.pageObjects.LandingPage;
import designFrameworkUsingTestNG.pageObjects.PoductCataloguePage;

public class SubmitOrderTest {

	public static void main(String[] args) {
		// Data required to feed
		String userName = "prashantShethManus1@gmail.com";
		String password = "IamKing@123";
		String productName = "iphone 13 pro".toUpperCase();
		String cvv = "123";
		String countryInitials = "ind";
		String countryName = "India";
		
		// 0. Initial setup
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1920, 1080)); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// 1. Go to landing page & Logged in with valid credentials---------------------------------------------------------------------------
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		landingPage.loginApp(userName, password);

		// 2. Products catalog page -> wait to load elements -> store elements in list -> iterate & search for desired element -> store & add to cart--------------------------
		PoductCataloguePage productCatPage = new PoductCataloguePage(driver);
		productCatPage.addTocart(productName);

		// 3. Go to cart page (Wait for toaster to appear & loader to disappear)
		CartPage cartPage = new CartPage(driver);
		cartPage.goToCartPage();

		// 4. Wait -> all elements to be loaded in cart section -> save in list -> verify that our added product is present
		Assert.assertTrue(cartPage.isProductPresent(productName));

		// 5. Go to checkOut page (Wait to appear cvv field -> enter cvv -> select country)
		CheckOutPage checkOutpage = new CheckOutPage(driver);
		checkOutpage.goToCheckOutPage(cvv);
		checkOutpage.selectCountry( countryInitials,  countryName);
//
//		// 13. go to Order summary page
//		goToOrderConfirmationPage(driver, wait1);
//
//		// 14. Quit the browser
//		driver.quit();

	}


// Select country
	public static void selectCountry(WebDriver driver, WebDriverWait wait1, String countryInitials,
			String countryName) {
		WebElement countrydropdown = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
//		countrydropdown.sendKeys(countryInitials);
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), countryInitials).build()
				.perform();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section [class*=list-group]")));
		List<WebElement> countryList = driver.findElements(By.cssSelector("section [class*=list-group] button"));
		WebElement selectedCountry = countryList.stream()
				.filter(country -> country.findElement(By.cssSelector("span")).getText().equalsIgnoreCase(countryName))
				.findFirst().orElse(null);
		if (selectedCountry != null) {
			selectedCountry.click();
		} else {
			System.out.println("Country not found. Please try again later");
			driver.quit();
		}
	}

// Go to order summary page
	public static void goToOrderConfirmationPage(WebDriver driver, WebDriverWait wait1) {
		driver.findElement(By.xpath("// div[@class='actions']//a")).click();
		wait1.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("h1")));
		String actualConfirmMessage = driver.findElement(By.tagName("h1")).getText();
		Assert.assertTrue(actualConfirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}

}
