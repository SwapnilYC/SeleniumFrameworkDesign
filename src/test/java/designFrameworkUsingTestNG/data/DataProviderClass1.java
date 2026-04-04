package designFrameworkUsingTestNG.data;

import java.util.HashMap;

import org.testng.annotations.DataProvider;

public class DataProviderClass1 {
	@DataProvider
	public Object[][] getFullData() {
		return new Object[][] { { "vinayak.sheth@gmail.com", "Vin@yakS26", "ADIDAS ORIGINAL", "123", "u", "Anguilla" },
				{ "vinayak.sheth2@gmail.com", "Vin@yakS26", "ZARA COAT 3", "890", "s", "Afghanistan" },
				{ "vinayak.sheth3@gmail.com", "Vin@yakS26", "iphone 13 pro", "727", "ind", "India" },
				{ "prashantShethManus1@gmail.com", "IamKing@123", "ADIDAS ORIGINAL", "567", "us", "Mauritius" },
				{ "prashantShethManus2@gmail.com", "IamKing@123", "ZARA COAT 3", "427", "y", "Cayman Islands" },
				{ "prashantShethManus3@gmail.com", "IamKing@123", "iphone 13 pro", "558", "rr", "Montserrat" } };

		// Limitations: Data still hardcoded in dataprovider class. It is not EXERNALIZE
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

	@DataProvider
	public Object[][] getDataFromHashMap() {
		HashMap<Object, Object> map = new HashMap<>();
		map.put("email", "vinayak.sheth@gmail.com");
		map.put("password", "Vin@yakS26");
		map.put("productName", "ADIDAS ORIGINAL");
		map.put("cvv", "123");
		map.put("countryInitials", "u");
		map.put("countryName", "Anguilla");

		// limitations for each dataset need to create new map
		return new Object[][] { { map } };
	}
	
	
}
