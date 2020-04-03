package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.RegistrationPage;
import utils.ExcelUtils;

public class RegistrationTest {

	private WebDriver driver;
	private Properties locators;

	@BeforeClass
	public void setup() throws FileNotFoundException, IOException {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.locators = new Properties();
		locators.load(new FileInputStream("config/config.properties"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void userRegistrationTest() {
		this.driver.navigate().to(locators.getProperty("register_url"));
		RegistrationPage rp = new RegistrationPage(driver, locators);
		SoftAssert sa = new SoftAssert();

		ExcelUtils.setExcell(this.locators.getProperty("data_source"));
		ExcelUtils.setWorkSheet(1);
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {

			String userID = rp.generateUniqueId();
			ExcelUtils.setDataAt(i, 0, userID);

			String password = ExcelUtils.getDataAt(i, 1);
			String firstName = ExcelUtils.getDataAt(i, 2);
			String lastName = ExcelUtils.getDataAt(i, 3);
			String email = ExcelUtils.getDataAt(i, 4);
			String phone = ExcelUtils.getDataAt(i, 5);
			String address1 = ExcelUtils.getDataAt(i, 6);
			String address2 = ExcelUtils.getDataAt(i, 7);
			String city = ExcelUtils.getDataAt(i, 8);
			String state = ExcelUtils.getDataAt(i, 9);
			String zip = ExcelUtils.getDataAt(i, 10);
			String country = ExcelUtils.getDataAt(i, 11);
			String language = ExcelUtils.getDataAt(i, 12);
			String category = ExcelUtils.getDataAt(i, 13);
			String myList = ExcelUtils.getDataAt(i, 14);
			String myBanner = ExcelUtils.getDataAt(i, 15);

			rp.register(userID, password, firstName, lastName, email, 
								phone, address1, address2, city, state,
								zip, country, language, category, myList, myBanner);

			WebElement siderbarMenu = this.driver.findElement(By.xpath(locators.getProperty("sidebar_menu")));

			sa.assertTrue(siderbarMenu.isDisplayed());

			this.driver.navigate().to(locators.getProperty("register_url"));
		}
		sa.assertAll();
	}

	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
