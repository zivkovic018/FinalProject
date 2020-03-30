package pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PetStoreMenuPage {

	private WebDriver driver;
	private Properties locators;

	public PetStoreMenuPage(WebDriver driver, Properties locators) {
		this.driver = driver;
		this.locators = locators;
	}

	public List<WebElement> getSidebarMenu() {
		return this.driver.findElements(By.xpath(locators.getProperty("sidebar_menu")));
	}

	public List<WebElement> getQuickMenu() {
		return this.driver.findElements(By.xpath(locators.getProperty("quick_menu")));
	}

	public List<WebElement> getImageMenu() {
		return this.driver.findElements(By.xpath(locators.getProperty("image_menu")));
	}

	public WebElement getSignInBtn() {
		return this.driver.findElement(By.xpath(locators.getProperty("sign_In")));
	}

	public void clickSignInBtn() {
		this.getSignInBtn().click();
	}

	public boolean checkMenusItemsStatus(List<WebElement> menu) {
		List<WebElement> elements = menu;
		boolean MenuItemsStatusIsOk = true;
		for (int i = 0; i < elements.size(); i++) {
			int status = this.verifyURLStatus(elements.get(i).getAttribute("href"));
			if (status >= 400) {
				MenuItemsStatusIsOk = false;
			}
		}
		return MenuItemsStatusIsOk;
	}

	public boolean checkSidebarMenuItemsPaths() {
		List<WebElement> elements = this.getSidebarMenu();
		boolean sidebarMenuItemsPathIsOk = false;
		for (int i = 0; i < elements.size(); i++) {
			elements.get(i).click();
			if (this.driver.findElement(By.xpath(locators.getProperty("catalog_title"))).isDisplayed()) {
				sidebarMenuItemsPathIsOk = true;
			}
			this.driver.navigate().to(locators.getProperty("jpet_store_home_page_url"));
			elements = this.getSidebarMenu();
		}
		return sidebarMenuItemsPathIsOk;
	}

	public boolean checkQuickMenuItemsPaths() {
		List<WebElement> elements = this.getQuickMenu();
		boolean quickMenuItemsPathIsOk = false;
		for (int i = 0; i < elements.size(); i++) {
			elements.get(i).click();
			if (this.driver.findElement(By.xpath(locators.getProperty("catalog_title"))).isDisplayed()) {
				quickMenuItemsPathIsOk = true;
			}
			this.driver.navigate().to(locators.getProperty("jpet_store_home_page_url"));
			elements = this.getQuickMenu();
		}
		return quickMenuItemsPathIsOk;
	}

	public boolean checkImageMenuItemsPaths() {
		List<WebElement> elements = this.getImageMenu();
		boolean imageMenuItemsPathIsOk = false;
		for (int i = 0; i < elements.size(); i++) {
			elements.get(i).click();
			if (this.driver.findElement(By.xpath(locators.getProperty("catalog_title"))).isDisplayed()) {
				imageMenuItemsPathIsOk = true;
			}
			this.driver.navigate().to(locators.getProperty("jpet_store_home_page_url"));
			elements = this.getImageMenu();
		}
		return imageMenuItemsPathIsOk;
	}

	public int verifyURLStatus(String urlString) {
		int status = 404;
		try {
			URL link = new URL(urlString);
			HttpURLConnection hConn = null;
			hConn = (HttpURLConnection) link.openConnection();
			hConn.setRequestMethod("GET");
			hConn.connect();
			status = hConn.getResponseCode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
}
