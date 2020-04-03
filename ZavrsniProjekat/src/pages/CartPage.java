package pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {

	private WebDriver driver;
	private Properties locators;

	public CartPage(WebDriver driver, Properties locators) {
		this.driver = driver;
		this.locators = locators;
	}

	public WebElement getUpdateCart() {
		return this.driver.findElement(By.xpath(locators.getProperty("update_cart_btn")));
	}

	public WebElement getProcedToCheckout() {
		return this.driver.findElement(By.xpath(locators.getProperty("proced_to_checkout_btn")));
	}

	public WebElement getCartIsEmptyMessage() {
		return this.driver.findElement(By.xpath(locators.getProperty("cart_is_empty_message")));
	}

	public List<WebElement> getItems() {
		List<WebElement> items = this.driver.findElements(By.xpath(locators.getProperty("items")));
		items.remove(0);
		items.remove(items.size() - 1);
		return items;
	}

	public WebElement getSubTotal() {
		return this.driver.findElement(By.xpath(locators.getProperty("sub_total")));
	}

	public void clickUpdateCart() {
		this.getUpdateCart().click();
	}

	public void clickProcedToCheckout() {
		this.getProcedToCheckout().click();
	}

	public boolean checkItemsId(String item_id) {
		List<WebElement> elements = this.getItems();
		boolean itemIdIsOk = true;
		for (int i = 0; i < elements.size(); i++) {
			try {
				WebElement itemid = elements.get(i).findElement(By.xpath(locators.getProperty("item_id")));
				itemid.getText().contains(item_id);
			} catch (Exception e) {
				itemIdIsOk = false;
			}
		}
		return itemIdIsOk;
	}

	public int getSubTotalPrice() {
		String subtotalstr = this.getSubTotal().getText().substring(12);
		double subtotaldouble = Double.parseDouble(subtotalstr);
		int subtotalint = (int) (subtotaldouble * 100);
		return subtotalint;
	}

	public int getSumTotalCost() {
		List<WebElement> elements = this.getItems();
		int totalCost = 0;
		for (int i = 0; i < elements.size(); i++) {
			WebElement total_cost = elements.get(i).findElement(By.xpath(locators.getProperty("total_cost")));
			String txtPrice = total_cost.getText().substring(1);
			double price = Double.parseDouble(txtPrice);
			int sumPrice = (int) (price * 100);
			totalCost += sumPrice;
		}
		return totalCost;
	}

}
