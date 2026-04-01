package DesignPrinciplesOfSeleniumFrameWork.UsingTestNG_TestingFramework;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;

import designFrameworkUsingTestNG.pageObjects.CartPage;
import designFrameworkUsingTestNG.pageObjects.CheckOutPage;
import designFrameworkUsingTestNG.pageObjects.LandingPage;
import designFrameworkUsingTestNG.pageObjects.OrderSummaryPage;
import designFrameworkUsingTestNG.pageObjects.PoductCataloguePage;

public class SubmitOrderTest {

	public static void main(String[] args) throws InterruptedException {
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

		// 1. Go to landing page & Logged in with valid
		// credentials---------------------------------------------------------------------------
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		landingPage.loginApp(userName, password);

		// 2. Products catalog page -> wait to load elements -> store elements in list
		// -> iterate & search for desired element -> store & add to
		// cart--------------------------
		PoductCataloguePage productCatPage = new PoductCataloguePage(driver);
		productCatPage.addTocart(productName);

		// 3. Go to cart page (Wait for toaster to appear & loader to disappear)
		CartPage cartPage = new CartPage(driver);
		cartPage.goToCartPage();

		// 4. Wait -> all elements to be loaded in cart section -> save in list ->
		// verify that our added product is present
		Assert.assertTrue(cartPage.isProductPresent(productName));

		// 5. Go to checkOut page (Wait to appear cvv field -> enter cvv -> select
		// country)
		CheckOutPage checkOutpage = new CheckOutPage(driver);
		checkOutpage.goToCheckOutPage(cvv);
		checkOutpage.selectCountry(countryInitials, countryName);

		// 6. go to Order summary page & check is order placed successfully
		OrderSummaryPage summaryPage = new OrderSummaryPage(driver);
		Assert.assertTrue(summaryPage.isOrderPlaced());

		// 14. Quit the browser
		driver.quit();

	}

}
