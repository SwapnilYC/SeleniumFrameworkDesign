package designFrameworkUsingTestNG.reusableTestComponents;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
	public void initializeDriver() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1920, 1080));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
}
