package designFrameworkUsingTestNG.standAloneTests;

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


public class StandAloneTest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
//		driver.manage().window().setSize(new Dimension(1366, 768));

		driver.manage().window().setSize(new Dimension(1920, 1080)); // for CI/CD this dimension is recommended
//		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// 1. Go to landing page
		landingPage(driver, "https://www.rahulshettyacademy.com/client");

		// 2. Logged in with valid credentials
		loginWithValidCredentials(driver, "vinayak.sheth@gmail.com", "Vin@yakS26");

		// 3. Wait till all products are loaded
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement productsContainer = driver.findElement(By.className("container"));
		wait1.until(ExpectedConditions.visibilityOf(productsContainer));

		// 4. Once all products are loaded please store all products in one list
		List<WebElement> productsList = driver.findElements(By.className("card-body"));

		// 5. Capture the required product
		String productName = "iphone 13 pro".toUpperCase();
		WebElement selectedProduct = searchAndStore(driver, productsList, productName);

		// 6. Now add this captured product in cart
		addTocart(driver, selectedProduct, wait1);

		// 7. Wait for toast message to appear & loader to get disappear
		toasterAndLoaderWait(wait1, driver);

		// 8. Click on cart button & go to cart page
		goTocartPage(driver, wait1);

		// 9. Wait for all elements to be loaded in cart section
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart")));

		// 10. Save all those items in a list & verify that our added product is present
		isProductPresentIncart(driver, productName);

		// 11. Go to checkOut page
		goToCheckoutPage(driver, wait1);

		// 12. select country
		String countryInitials = "ind";
		String countryName = "India";
		selectCountry(driver, wait1, countryInitials, countryName);

		// 13. go to Order summary page
		goToOrderConfirmationPage(driver, wait1);
		
		//14. Quit the browser
		driver.quit();

	}

// Go to URL
	public static void landingPage(WebDriver driver, String url) {
		driver.get(url);
	}

// Login
	public static void loginWithValidCredentials(WebDriver driver, String userName, String password) {
		driver.findElement(By.id("userEmail")).sendKeys(userName);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();
	}

// Search for desired product & store it an web element
	public static WebElement searchAndStore(WebDriver driver, List<WebElement> productsList, String productName) {
		return productsList.stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
	}

// Add the desired product to cart
	public static void addTocart(WebDriver driver, WebElement selectedProduct, WebDriverWait wait1) {
		WebElement addToCartBtn = selectedProduct.findElement(By.xpath(".//button[2]"));
		wait1.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
		addToCartBtn.click();
	}

// Wait for toast message to appear & loader to get disappear
	public static void toasterAndLoaderWait(WebDriverWait wait1, WebDriver driver) {
		// wait to load toaster success message
		WebElement loaderOverlay = driver.findElement(By.cssSelector(".ng-animating"));
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container"))); 															
		wait1.until(ExpectedConditions.invisibilityOf(loaderOverlay));
		// These waits run sequentially: First wait completes Then second wait starts
	}

// Click on cart button & go to cart page
	public static void goTocartPage(WebDriver driver, WebDriverWait wait1) {
		WebElement goToCart = driver.findElement(By.xpath("//button[contains(@routerlink,'cart')]"));
//		wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@routerlink,'cart')]")));
		wait1.until(ExpectedConditions.elementToBeClickable(goToCart));
		goToCart.click();
	}

// Save all those items in a list & verify that our added product is present
	public static void isProductPresentIncart(WebDriver driver, String productName) {
		List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cart']//li"));
		Assert.assertTrue(cartProducts.stream()
				.anyMatch(e -> e.findElement(By.tagName("h3")).getText().equalsIgnoreCase(productName)));
	}

// Go to checkout page
	public static void goToCheckoutPage(WebDriver driver, WebDriverWait wait1) {
		driver.findElement(By.xpath("//div[contains(@class,'subtotal')]//button")).click(); // click on checkout btn
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input [@class='input txt'])[1]")));
		driver.findElement(By.xpath("(//input [@class='input txt'])[1]")).sendKeys("123"); // csv
	}

// Select country
	public static void selectCountry(WebDriver driver, WebDriverWait wait1, String countryInitials,
			String countryName) {
		WebElement countrydropdown = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
//		countrydropdown.sendKeys(countryInitials);
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), countryInitials).build().perform();
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
