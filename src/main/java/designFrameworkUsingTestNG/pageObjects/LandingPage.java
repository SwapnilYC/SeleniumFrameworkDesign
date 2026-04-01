package designFrameworkUsingTestNG.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		this.driver = driver;
	}
	WebElement userName = driver.findElement(By.id("userEmail"));
}
