package designFrameworkUsingTestNG.pageObjects;

// 6th page
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import designFrameworkUsingTestNG.Utils.AbstractComponents;

public class MyOrdersPage extends AbstractComponents {
	WebDriver driver;

	public MyOrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// Initializes @FindBy proxy elements of the current class instance/PAGE OBJECTS
		// with the driver (lazy initialization)
		// Actual elements are located when they are used
	}

// Locators/Elements------------------------------------------------------------------------------------
	// PageFactory Annotation
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement OrdersBtn;
	
	@FindBy(css = "table[class*='ng-star-inserted']")
	WebElement allOrdersContainer;

	@FindBy(xpath = "//tbody//tr")
	List<WebElement> ordersList;


// Methods/Actions On Elements----------------------------------------------------------------------------
	// Wait to load all products then add them in a list
	public List<WebElement> goToOrdersListPage() {
		waitForWebElementToBeClickable(OrdersBtn);
		OrdersBtn.click();
		waitForWebElementToAppear(allOrdersContainer); // first wait to Orders to get loaded
		return ordersList; // then return the orders list
	}

	// Search for order by name
	public boolean getProductByName(String productName) {
		List<WebElement> ordersList = goToOrdersListPage();
		WebElement targetedOrder =  ordersList.stream()
				.filter(orderEl -> orderEl.findElement(By.xpath(".//td[2]")).getText().trim().equalsIgnoreCase(productName.trim()))
				.findFirst().orElse(null);
		
		if(targetedOrder == null){
			System.out.println("Your product '"+productName+"' is not present in Order list");
			return false;
		}
		else {
			String orderID = targetedOrder.findElement(By.xpath(".//th")).getText();
			System.out.println("Product name is: "+productName+" & OrderId is: "+ orderID);
			return true;
		}
	}

}
