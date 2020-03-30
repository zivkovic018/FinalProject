package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreItemPage {

	private WebDriver driver;
	private Properties locators;
	
	public StoreItemPage(WebDriver driver, Properties locators) {
		this.driver = driver;
		this.locators = locators;
	}
	
	public WebElement getAddToCart() {
		return this.driver.findElement(By.xpath(locators.getProperty("add_to_cart_btn")));
	}
	
	public void clickAddToCart() {
		this.getAddToCart().click();
	}	
}
