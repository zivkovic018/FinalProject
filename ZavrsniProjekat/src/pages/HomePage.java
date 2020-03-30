package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	private WebDriver driver;
	private Properties locators;

	public HomePage(WebDriver driver, Properties locators) {
		this.driver = driver;
		this.locators = locators;
	}

	public WebElement getEnterTheStore() {
		return this.driver.findElement(By.xpath(locators.getProperty("enter_the_store")));
	}

	public void clickEnterTheStore() {
		this.getEnterTheStore().click();
	}

}
