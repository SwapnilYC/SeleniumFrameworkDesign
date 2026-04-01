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
		String targetCountryName = "India";

		// 0. Initial setup
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1920, 1080));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// 1. landing page -> Logged in with valid credentials---------------------------------------------------------------------------
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		
		// 2. Products catalog page -> Add desired product to cart-----------------------------------------------------------------------
		PoductCataloguePage productCatPage = landingPage.loginApp(userName, password); 
		productCatPage.addTocart(productName);
		

		// 3. Go to cart page & verify that our added product is present
		CartPage cartPage = productCatPage.goToCartPage();
		Assert.assertTrue(cartPage.isProductPresent(productName));

		// 4. Go to checkOut page (enter cvv -> select country)
		CheckOutPage checkOutpage = cartPage.goToCheckOutPage();
		checkOutpage.fillCVV(cvv);
		checkOutpage.selectCountry(countryInitials, targetCountryName);

		// 5. go to Order summary page & check is order placed successfully
		OrderSummaryPage summaryPage = checkOutpage.goToOrderSummaryPage();
		Assert.assertTrue(summaryPage.isOrderPlaced());

		// 6. Quit the browser
		driver.quit();

	}

}
