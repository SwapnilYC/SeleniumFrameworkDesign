package designFrameworkUsingTestNG.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		this.driver = driver;
	}

	WebElement userNamefield = driver.findElement(By.id("userEmail"));
	WebElement passwordfield = driver.findElement(By.id("userPassword"));
	WebElement loginBtn = driver.findElement(By.id("login"));
}
