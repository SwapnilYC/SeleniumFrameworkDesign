package designFrameworkUsingTestNG.reusableTestComponents;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	@DataProvider
	public Object[][] getFullData() {
		return new Object[][] { { "vinayak.sheth@gmail.com", "Vin@yakS26", "ADIDAS ORIGINAL", "123", "u", "Anguilla" },
				{ "vinayak.sheth2@gmail.com", "Vin@yakS26", "ZARA COAT 3", "890", "s", "Afghanistan" },
				{ "vinayak.sheth3@gmail.com", "Vin@yakS26", "iphone 13 pro", "727", "ind", "India" },
				{ "prashantShethManus1@gmail.com", "IamKing@123", "ADIDAS ORIGINAL", "567", "us", "Mauritius" },
				{ "prashantShethManus2@gmail.com", "IamKing@123", "ZARA COAT 3", "427", "y", "Cayman Islands" },
				{ "prashantShethManus3@gmail.com", "IamKing@123", "iphone 13 pro", "558", "rr", "Montserrat" } };
	}

	@DataProvider
	public Object[][] getLoginData() {
		return new Object[][] { { "vinayak.sheth@gmail.com", "Vin@yakS26", "ADIDAS ORIGINAL" },
				{ "vinayak.sheth2@gmail.com", "Vin@yakS26", "ZARA COAT 3" },
				{ "vinayak.sheth3@gmail.com", "Vin@yakS26", "iphone 13 pro" },
//				{ "prashantShethManus1@gmail.com", "IamKing@123", "ADIDAS ORIGINAL" },
//				{ "prashantShethManus2@gmail.com", "IamKing@123", "ZARA COAT 3" },
//				{ "prashantShethManus3@gmail.com", "IamKing@123", "iphone 13 pro"} 
		};
	}
}
