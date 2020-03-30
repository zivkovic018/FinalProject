package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignInPage {

	private WebDriver driver;
	private Properties locators;
	
	public SignInPage(WebDriver driver, Properties locators) {
		this.driver = driver;
		this.locators = locators;
	}
	
	public WebElement getUserName() {
		return this.driver.findElement(By.xpath(locators.getProperty("user_name_input")));
	}
	
	public WebElement getPassword() {
		return this.driver.findElement(By.xpath(locators.getProperty("password_input")));
	}

	public WebElement getLogInBtn() {
		return this.driver.findElement(By.xpath(locators.getProperty("login_btn")));
	}
	
	public WebElement getRegisterNow() {
		return this.driver.findElement(By.xpath(locators.getProperty("registerNow")));
	}
	
	public void setUserName(String username) {
		this.getUserName().clear();
		this.getUserName().sendKeys(username);
	}
	
	public void setPassword(String password) {
		this.getPassword().clear();
		this.getPassword().sendKeys(password);
	}
	
	public void clickLogInBtn() {
		this.getLogInBtn().click();
	}
	
	public void clickRegisterNow() {
		this.getRegisterNow().click();
	}
	
	public void signIn(String username, String password) {
		this.setUserName(username);
		this.setPassword(password);
		this.clickLogInBtn();
	}
	
}
