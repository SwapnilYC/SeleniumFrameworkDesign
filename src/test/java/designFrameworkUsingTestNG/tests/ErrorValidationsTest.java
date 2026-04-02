package designFrameworkUsingTestNG.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import designFrameworkUsingTestNG.reusableTestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest {
	@Test
	public void loginWithWrongCreds() {
		// Data required to feed
		String wrongUserName = "ShethManus1@gmail.com";
		String wrongPassword = "IamKing";
		landingPage.loginApp(wrongUserName, wrongPassword);
//		Assert.assertTrue(landingPage.getErrorMsgForWrongCreds());  // this msg will give are the error msgs same(expected & actual)
		
		// Following is written to deliberately fails the test
		Assert.assertEquals(" Incorrect email or password. ", "Incorrect email or password.");
	}

}
