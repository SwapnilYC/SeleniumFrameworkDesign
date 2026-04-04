package designFrameworkUsingTestNG.tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import designFrameworkUsingTestNG.data.DataProviderClass1;
import designFrameworkUsingTestNG.data.DataProviderClass2;
import designFrameworkUsingTestNG.pageObjects.CartPage;
import designFrameworkUsingTestNG.pageObjects.CheckOutPage;
import designFrameworkUsingTestNG.pageObjects.MyOrdersPage;
import designFrameworkUsingTestNG.pageObjects.OrderSummaryPage;
import designFrameworkUsingTestNG.pageObjects.PoductCataloguePage;
import designFrameworkUsingTestNG.reusableTestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	// Test end to end happy flow
	@Test(dataProviderClass=DataProviderClass2.class, dataProvider="getDataFromJasonFile", groups= {"purchaseOrder"})
	public void submitOrderTest(HashMap<String,String> input) throws InterruptedException, IOException {
		String userName1 = input.get("email"); 
		String password1 = input.get("password");
		String productName1 = input.get("productName");
		String cvv= input.get("cvv");
		String countryInitials = input.get("countryInitials");
		String targetCountryName = input.get("countryName");
		System.out.println("1) SubmitOrderTest -> submitOrderTest"); // this is to check order of execution in parallel execution
		productName1 = productName1.toUpperCase();
		// 1. landing page → moved to base test

		// 2. Products catalog page-----------------------------------------------------------------------------------------------------------
		PoductCataloguePage productCatPage = landingPage.loginApp(userName1, password1);
		productCatPage.addTocart(productName1);

		// 3. Go to cart page---------------------------------------------------------------------------------------------------------------
		CartPage cartPage = productCatPage.goToCartPage();
		Assert.assertTrue(cartPage.isProductPresent(productName1));

		// 4. Go to checkOut page-----------------------------------------------------------------------------------------------------------
		CheckOutPage checkOutpage = cartPage.goToCheckOutPage();
		checkOutpage.fillCVV(cvv);
		checkOutpage.selectCountry(countryInitials, targetCountryName);

		// 5. Order summary page------------------------------------------------------------------------------------------------------------
		OrderSummaryPage summaryPage = checkOutpage.goToOrderSummaryPage();
		Assert.assertTrue(summaryPage.isOrderPlaced());

		// 6. Quit browser  → moved to base test
//		System.out.println(userName1 + "\n" + password1 + "\n" + productName1 + "\n" + cvv + "\n" + targetCountryName + "\n"); // this is to check order of execution in parallel execution
	}
	
	// Test orders history page for recently placed order
	@Test(dependsOnMethods= {"submitOrderTest"}, dataProviderClass=DataProviderClass1.class, dataProvider="getLoginData")
	public void checkOrederHistoryPage(String userName1, String password1, String productName1) {
		productName1 = productName1.toUpperCase();
		System.out.println("2) SubmitOrderTest -> checkOrederHistoryPage");
		landingPage.loginApp(userName1, password1);  // If logged in with same user this test will pass
//		landingPage.loginApp("vinayak.sheth2@gmail.com", "Vin@yakS26");  // this is valid user but havent added any product yet: Test will fail
		MyOrdersPage ordersHistoryPage = new MyOrdersPage(driver);
		Boolean isOrderPresent = ordersHistoryPage.getProductByName(productName1);
		Assert.assertTrue(isOrderPresent);
	}
	
	

}
