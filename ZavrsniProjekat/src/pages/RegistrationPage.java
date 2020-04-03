package pages;

import java.util.Properties;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage {

	private WebDriver driver;
	private Properties locators;

	public RegistrationPage(WebDriver driver, Properties locators) {
		this.driver = driver;
		this.locators = locators;
	}

	public WebElement getUserId() {
		return this.driver.findElement(By.xpath(locators.getProperty("user_id_input")));
	}

	public WebElement getNewPassword() {
		return this.driver.findElement(By.xpath(locators.getProperty("new_password_input")));
	}

	public WebElement getRepeatPassword() {
		return this.driver.findElement(By.xpath(locators.getProperty("repeat_password_input")));
	}

	public WebElement getFirstName() {
		return this.driver.findElement(By.xpath(locators.getProperty("first_name_input")));
	}

	public WebElement getLastName() {
		return this.driver.findElement(By.xpath(locators.getProperty("last_name_input")));
	}

	public WebElement getEmail() {
		return this.driver.findElement(By.xpath(locators.getProperty("email_input")));
	}

	public WebElement getPhone() {
		return this.driver.findElement(By.xpath(locators.getProperty("phone_input")));
	}

	public WebElement getAddress1() {
		return this.driver.findElement(By.xpath(locators.getProperty("address_1_input")));
	}

	public WebElement getAddress2() {
		return this.driver.findElement(By.xpath(locators.getProperty("address_2_input")));
	}

	public WebElement getCity() {
		return this.driver.findElement(By.xpath(locators.getProperty("city_input")));
	}

	public WebElement getState() {
		return this.driver.findElement(By.xpath(locators.getProperty("state_input")));
	}

	public WebElement getZip() {
		return this.driver.findElement(By.xpath(locators.getProperty("zip_input")));
	}

	public WebElement getCountry() {
		return this.driver.findElement(By.xpath(locators.getProperty("country_input")));
	}

	public WebElement getLanguagePreference() {
		return this.driver.findElement(By.xpath(locators.getProperty("language_preference_select")));
	}

	public WebElement getFavouriteCategory() {
		return this.driver.findElement(By.xpath(locators.getProperty("favourite_category_select")));
	}

	public WebElement getMyList() {
		return this.driver.findElement(By.xpath(locators.getProperty("enable_my_list_checkbox")));
	}

	public WebElement getMyBanner() {
		return this.driver.findElement(By.xpath(locators.getProperty("enable_my_banner_checkbox")));
	}

	public WebElement getSaveAccountInformationBtn() {
		return this.driver.findElement(By.xpath(locators.getProperty("save_account_information_btn")));
	}

	public void setUserId(String userid) {
		this.getUserId().clear();
		this.getUserId().sendKeys(userid);
	}

	public void setNewPassword(String password) {
		this.getNewPassword().clear();
		this.getNewPassword().sendKeys(password);
	}

	public void setRepeatPassword(String password) {
		this.getRepeatPassword().clear();
		this.getRepeatPassword().sendKeys(password);
		;
	}

	public void setFirstName(String firstname) {
		this.getFirstName().clear();
		this.getFirstName().sendKeys(firstname);
	}

	public void setLastName(String lastname) {
		this.getLastName().clear();
		this.getLastName().sendKeys(lastname);
	}

	public void setEmail(String email) {
		this.getEmail().clear();
		this.getEmail().sendKeys(email);
	}

	public void setPhone(String phone) {
		this.getPhone().clear();
		this.getPhone().sendKeys(phone);
	}

	public void setAddress1(String address1) {
		this.getAddress1().clear();
		this.getAddress1().sendKeys(address1);
	}

	public void setAddress2(String address2) {
		this.getAddress2().clear();
		this.getAddress2().sendKeys(address2);
	}

	public void setCity(String city) {
		this.getCity().clear();
		this.getCity().sendKeys(city);
	}

	public void setState(String state) {
		this.getState().clear();
		this.getState().sendKeys(state);
	}

	public void setZip(String zip) {
		this.getZip().clear();
		this.getZip().sendKeys(zip);
	}

	public void setCountry(String country) {
		this.getCountry().clear();
		this.getCountry().sendKeys(country);
		;
	}

	public void setLanguagePreference(String language) {
		Select select = new Select(this.getLanguagePreference());
		select.selectByValue(language);
	}

	public void setFavouriteCategory(String category) {
		Select select = new Select(this.getFavouriteCategory());
		select.selectByValue(category);
	}

	public void setMyList(String mylist) {
		if (this.getMyList().isSelected() && mylist.equals("n")) {
			this.getMyList().click();
		} else if (mylist.equals("y")) {
			this.getMyList().click();
		} 
	}

	public void setMyBanner(String mybanner) {
		if (this.getMyBanner().isSelected() && mybanner.equals("n")) {
			this.getMyBanner().click();
		} else if (mybanner.equals("y")) {
			this.getMyBanner().click();
		} 
	}

	public void clickSaveAccountInformationBtn() {
		this.getSaveAccountInformationBtn().click();
	}
	
	public String generateUniqueId() {
		String uniqueID = UUID.randomUUID().toString();
		String userID = uniqueID.substring(0, 7);
		return userID;
	}
	
	public void register(String userID, String password, String firstName, 
									 String lastName, String email, String phone, 
									 String address1, String address2, String city, 
									 String state, String zip, String country, 
									 String language, String category, 
									 String myList, String myBanner) { 
		this.setUserId(userID);
		this.setNewPassword(password);
		this.setRepeatPassword(password);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPhone(phone);
		this.setAddress1(address1);
		this.setAddress2(address2);
		this.setCity(city);
		this.setState(state);
		this.setZip(zip);
		this.setCountry(country);
		this.setLanguagePreference(language);
		this.setFavouriteCategory(category);
		this.setMyList(myList);
		this.setMyBanner(myBanner);
		this.clickSaveAccountInformationBtn();
		
	}
	
}