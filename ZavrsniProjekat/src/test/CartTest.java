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

import pages.CartPage;
import pages.StoreItemPage;
import utils.ExcelUtils;

public class CartTest {

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

	@Test (priority = 1)
	public void addingItemsToCartTest() {
		this.driver.navigate().to(locators.getProperty("cart_page_url"));
		CartPage cp = new CartPage(driver, locators);
		StoreItemPage sip = new StoreItemPage(driver, locators);
		SoftAssert sa = new SoftAssert();

		ExcelUtils.setExcell(this.locators.getProperty("data_source"));
		ExcelUtils.setWorkSheet(0);

		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {

			String item_id = ExcelUtils.getDataAt(i, 0);
			String item_link = ExcelUtils.getDataAt(i, 1);

			this.driver.navigate().to(item_link);
			sip.clickAddToCart();

			sa.assertTrue(cp.verifyItemExistsByID(item_id));
		}
		
		sa.assertAll();
	}

	@Test (priority = 2)
	public void TotalPriceTest() {
		this.driver.navigate().to(locators.getProperty("cart_page_url"));
		CartPage cp = new CartPage(driver, locators);
		StoreItemPage sip = new StoreItemPage(driver, locators);

		ExcelUtils.setExcell(this.locators.getProperty("data_source"));
		ExcelUtils.setWorkSheet(0);

		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {

			String item_link = ExcelUtils.getDataAt(i, 1);

			this.driver.navigate().to(item_link);
			sip.clickAddToCart();;
		}
		
		Assert.assertTrue(cp.getSumTotalCost() == cp.getSubTotalPrice());
	}

	@Test (priority = 3)
	public void deleteCookiesCartTest() {
		this.driver.navigate().to(locators.getProperty("cart_page_url"));
		StoreItemPage sip = new StoreItemPage(driver, locators);
		CartPage cp = new CartPage(driver, locators);
		
		ExcelUtils.setExcell(this.locators.getProperty("data_source"));
		ExcelUtils.setWorkSheet(0);

		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {

			String item_link = ExcelUtils.getDataAt(i, 1);

			this.driver.navigate().to(item_link);
			sip.clickAddToCart();;
		}
		
		this.driver.navigate().to(locators.getProperty("cart_page_url"));
		this.driver.manage().deleteCookieNamed(this.locators.getProperty("cookie_name"));
		this.driver.navigate().refresh();

		Assert.assertTrue(cp.getCartIsEmptyMessage().isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
