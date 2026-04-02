package designFrameworkUsingTestNG.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import designFrameworkUsingTestNG.pageObjects.CartPage;
import designFrameworkUsingTestNG.pageObjects.CheckOutPage;
import designFrameworkUsingTestNG.pageObjects.MyOrdersPage;
import designFrameworkUsingTestNG.pageObjects.OrderSummaryPage;
import designFrameworkUsingTestNG.pageObjects.PoductCataloguePage;
import designFrameworkUsingTestNG.reusableTestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	// Data required for all test methods of this class
	String userName = "prashantShethManus1@gmail.com";
	String password = "IamKing@123";
	String productName = "iphone 13 pro".toUpperCase();
	
	// Test end to end happy flow
	@Test
	public void submitOrderTest() throws InterruptedException, IOException {
		System.out.println("1) SubmitOrderTest -> submitOrderTest"); // this is to check order of execution in parallel execution
		// Data required for this specific method
		String cvv = "123";
		String countryInitials = "ind";
		String targetCountryName = "India";

		// 1. landing page → moved to base test

		// 2. Products catalog page-----------------------------------------------------------------------------------------------------------
		PoductCataloguePage productCatPage = landingPage.loginApp(userName, password);
		productCatPage.addTocart(productName);

		// 3. Go to cart page---------------------------------------------------------------------------------------------------------------
		CartPage cartPage = productCatPage.goToCartPage();
		Assert.assertTrue(cartPage.isProductPresent(productName));

		// 4. Go to checkOut page-----------------------------------------------------------------------------------------------------------
		CheckOutPage checkOutpage = cartPage.goToCheckOutPage();
		checkOutpage.fillCVV(cvv);
		checkOutpage.selectCountry(countryInitials, targetCountryName);

		// 5. Order summary page------------------------------------------------------------------------------------------------------------
		OrderSummaryPage summaryPage = checkOutpage.goToOrderSummaryPage();
		Assert.assertTrue(summaryPage.isOrderPlaced());

		// 6. Quit browser  → moved to base test
	}
	
	// Test orders history page for recently placed order
	@Test(dependsOnMethods= {"submitOrderTest"})
	public void checkOrederHistoryPage() {
		System.out.println("2) SubmitOrderTest -> checkOrederHistoryPage");
		landingPage.loginApp(userName, password);  // If logged in with same user this test will pass
//		landingPage.loginApp("vinayak.sheth2@gmail.com", "Vin@yakS26");  // this is valid user but havent added any product yet: Test will fail
		MyOrdersPage ordersHistoryPage = new MyOrdersPage(driver);
		Boolean isOrderPresent = ordersHistoryPage.getProductByName(productName);
		Assert.assertTrue(isOrderPresent);
	}

}
