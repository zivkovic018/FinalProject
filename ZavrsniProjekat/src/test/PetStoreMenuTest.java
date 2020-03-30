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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.PetStoreMenuPage;

public class PetStoreMenuTest {

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
	public void petStoreMenuStatusTest() {
		driver.navigate().to(locators.getProperty("jpet_store_home_page_url"));
		PetStoreMenuPage psmp = new PetStoreMenuPage(driver, locators);
		SoftAssert sa = new SoftAssert();

		sa.assertTrue(psmp.checkMenusItemsStatus(psmp.getSidebarMenu()));
		sa.assertTrue(psmp.checkMenusItemsStatus(psmp.getQuickMenu()));
		sa.assertTrue(psmp.checkMenusItemsStatus(psmp.getImageMenu()));

		sa.assertAll();
	}

	@Test 
	public void petStoreMenuPathTest() {
		driver.navigate().to(locators.getProperty("jpet_store_home_page_url"));
		PetStoreMenuPage psmp = new PetStoreMenuPage(driver, locators);
		SoftAssert sa = new SoftAssert();

		sa.assertTrue(psmp.checkSidebarMenuItemsPaths());
		sa.assertTrue(psmp.checkQuickMenuItemsPaths());
		sa.assertTrue(psmp.checkImageMenuItemsPaths());

		sa.assertAll();
	}

	@Test 
	public void signInBtnTest() {
		driver.navigate().to(locators.getProperty("jpet_store_home_page_url"));
		PetStoreMenuPage psmp = new PetStoreMenuPage(driver, locators);

		psmp.clickSignInBtn();;

		WebElement loginbtn = this.driver.findElement(By.xpath(locators.getProperty("login_btn")));

		Assert.assertTrue(loginbtn.isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
