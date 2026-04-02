package designFrameworkUsingTestNG.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import designFrameworkUsingTestNG.pageObjects.CartPage;
import designFrameworkUsingTestNG.pageObjects.PoductCataloguePage;
import designFrameworkUsingTestNG.reusableTestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest {
	
	// Login with Wrong credentials & validate do we get correct error message
	@Test(groups={"ErrorHandling"})
	public void wrongCredsLoginValidation() {
		System.out.println("3) ErrorValidationsTest -> wrongCredsLoginValidation");
		// Data required to feed
		String wrongUserName = "ShethManus1@gmail.com";
		String wrongPassword = "IamKing";
		landingPage.loginApp(wrongUserName, wrongPassword);
		Assert.assertTrue(landingPage.getErrorMsgForWrongCreds());  // this msg will give are the error msgs same(expected & actual)
		
		// Following is written to deliberately fails the test
//		Assert.assertEquals(" Incorrect email or password. ", "Incorrect email or password.");
	}
	
	// Wrong product validation
	@Test
	public void productErrorValidation() throws InterruptedException, IOException {
		System.out.println("4) ErrorValidationsTest -> productErrorValidation");
		// Data required to feed
		String userName = "prashantShethManus1@gmail.com";
		String password = "IamKing@123";
		String productName = "iphone 13 pro".toUpperCase();

		// 1. landing page → moved to base test

		// 2. Products catalog page-----------------------------------------------------------------------------------------------------------
		PoductCataloguePage productCatPage = landingPage.loginApp(userName, password);
		productCatPage.addTocart(productName);

		// 3. Go to cart page---------------------------------------------------------------------------------------------------------------
		CartPage cartPage = productCatPage.goToCartPage();
		Assert.assertTrue(cartPage.isProductPresent(productName));  // correct one
//		Assert.assertTrue(cartPage.isProductPresent("iphone 14 pro".toUpperCase())); //deliberately failing
	}

}


/*
 👉 How to club various test together?
---------------->
	- Suppose there are 1000's are test cases
		- It is not advisable to create a separate class for each test (like wrong login creds, product error test, simple end to end flow)
		- In such case we need to club tests according to functionality
			- Like 
				- Login related test cases : Suppose there are 5 test cases related to login functionality club them together(Including -ve & +ve) in LoginValidationTests.java
				- ProductCart related testCases : Similarly do this for this page
	- All these go with sprint by sprint
		- For each sprints this changes(clubbing test cases)
  
 */
