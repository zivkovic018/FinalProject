package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.PetStoreMenuPage;
import pages.RegistrationPage;
import pages.SignInPage;
import utils.ExcelUtils;

public class SignInTest {

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
	public void signInTest() {
		this.driver.navigate().to(locators.getProperty("sign_In_page_url"));
		SignInPage sip = new SignInPage(driver, locators);
		PetStoreMenuPage psmp = new PetStoreMenuPage(driver, locators);
		SoftAssert sa = new SoftAssert();

		ExcelUtils.setExcell(this.locators.getProperty("data_source"));
		ExcelUtils.setWorkSheet(1);
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {

			String username = ExcelUtils.getDataAt(i, 0);
			String password = ExcelUtils.getDataAt(i, 1);

			sip.signIn(username, password);

			sa.assertTrue(psmp.getWelcomeMessage().isDisplayed());

			this.driver.navigate().to(locators.getProperty("sign_In_page_url"));
		}
		sa.assertAll();
	}

	@Test 
	public void registerNowTest() {
		this.driver.navigate().to(locators.getProperty("sign_In_page_url"));
		SignInPage sip = new SignInPage(driver, locators);
		RegistrationPage rp = new RegistrationPage(driver, locators);

		sip.clickRegisterNow();;

		Assert.assertTrue(rp.getSaveAccountInformationBtn().isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
