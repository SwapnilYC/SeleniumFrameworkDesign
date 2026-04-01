package designFrameworkUsingTestNG.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import designFrameworkUsingTestNG.pageObjects.CartPage;
import designFrameworkUsingTestNG.pageObjects.CheckOutPage;

import designFrameworkUsingTestNG.pageObjects.OrderSummaryPage;
import designFrameworkUsingTestNG.pageObjects.PoductCataloguePage;
import designFrameworkUsingTestNG.reusableTestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	@Test
	public void submitOrderTest() throws InterruptedException, IOException {
		// Data required to feed
		String userName = "prashantShethManus1@gmail.com";
		String password = "IamKing@123";
		String productName = "iphone 13 pro".toUpperCase();
		String cvv = "123";
		String countryInitials = "ind";
		String targetCountryName = "India";

		// 1. landing page
		// ------------------------------------------------------------------------------------------------
		// moved to base test

		// 2. Products catalog page -> Add desired product to
		// cart-----------------------------------------------------------------------------------------------------------
		PoductCataloguePage productCatPage = landingPage.loginApp(userName, password);
		productCatPage.addTocart(productName);

		// 3. Go to cart page & verify that our added product is
		// present-----------------------------------------------------
		CartPage cartPage = productCatPage.goToCartPage();
		Assert.assertTrue(cartPage.isProductPresent(productName));

		// 4. Go to checkOut page (enter cvv -> select
		// country)--------------------------------------------------------------
		CheckOutPage checkOutpage = cartPage.goToCheckOutPage();
		checkOutpage.fillCVV(cvv);
		checkOutpage.selectCountry(countryInitials, targetCountryName);

		// 5. go to Order summary page & check is order placed
		// successfully-------------------------------------------------
		OrderSummaryPage summaryPage = checkOutpage.goToOrderSummaryPage();
		Assert.assertTrue(summaryPage.isOrderPlaced());

		// 6. Quit the
		// browser----------------------------------------------------------------------------------------------
		// moved to base test

	}

}
