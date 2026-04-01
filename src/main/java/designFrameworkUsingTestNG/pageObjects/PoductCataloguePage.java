package designFrameworkUsingTestNG.pageObjects;
// 2nd page
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import designFrameworkUsingTestNG.Utils.AbstractComponents;

public class PoductCataloguePage extends AbstractComponents {
	WebDriver driver;

	public PoductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// Initializes @FindBy proxy elements of the current class instance/PAGE OBJECTS
		// with the driver (lazy initialization)
		// Actual elements are located when they are used
	}

// Locators/Elements------------------------------------------------------------------------------------
	// PageFactory Annotation
	@FindBy(className = "container")
	WebElement productsContainer;

	@FindBy(className = "card-body")
	List<WebElement> productsList;
	
	@FindBy(xpath="")
	WebElement addToCartBtn;
	
	// By locators
	By addToCartBtnBy = By.xpath(".//button[2]");


// Methods/Actions On Elements----------------------------------------------------------------------------
	// Wait to load all products then add them in a list
	public List<WebElement> getProductList() {
		waitForWebElementToAppear(productsContainer);  // first wait to products get loaded
		return productsList; // then return the products list
	}
	
	//Search for product by name
	public WebElement getProductByName(String productName) {
		List<WebElement> productsList = getProductList();
		return productsList.stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
	}
	
	// Add the selected product to cart
	public void addTocart(String productName) {
		WebElement selectedProduct = getProductByName(productName);
		WebElement addToCartBtn = selectedProduct.findElement(addToCartBtnBy);  // can't use page factory for relative element search
		waitForWebElementToBeClickable(addToCartBtn);
		addToCartBtn.click();
	}
	
}
