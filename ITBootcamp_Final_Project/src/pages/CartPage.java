package pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExcelUtils;

public class CartPage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public CartPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	public WebElement getProceedToCheckout() {
		return this.driver.findElement(By.xpath(locators.getProperty("proceed_to_checkout")));
	}

	public void setProceedToCheckout() {
		this.getProceedToCheckout().click();
	}

	public WebElement getSubTotalPrice() {
		return this.driver.findElement(By.xpath(locators.getProperty("sub_total_price")));
	}

	public List<WebElement> getListOfItemsPrice() {
		return this.driver.findElements(By.xpath(locators.getProperty("item_price_in_list")));
	}

	public List<WebElement> getProductsIdFromCart() {
		return this.driver.findElements(By.xpath(locators.getProperty("productId_in_cart")));
	}

	public WebElement getQuantity() {
		return this.driver.findElement(By.xpath(locators.getProperty("product_quantity")));
	}

	public void setQuantity(int quantity) {
		this.getQuantity().toString();
	}

	public WebElement getEmptyCartMsg() {
		return this.driver.findElement(By.xpath(locators.getProperty("cart_is_empty_msg")));
	}

	public void deleteCookiesFromCartPage() {
		driver.navigate().to(locators.getProperty("cart_url"));
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
	}

	public boolean isProductAddedToCart(String idFromFile) {
		List<WebElement> productsId = this.getProductsIdFromCart();
		for (int i = 0; i < productsId.size(); i++) {
			String productId = productsId.get(i).getText();
			if (productId.contentEquals(idFromFile)) {
				return true;
			}
		}
		return false;
	}

	public int getPriceConvertedToInt() {
		String subTotalString = this.getSubTotalPrice().getText().substring(12);
		double doublePrice = Double.parseDouble(subTotalString);
		// Because double is not working well on summing, and we need this
		// for comparing purpose, I'm converting sum into int type
		int totalPrice = (int) (doublePrice * 100);
		return totalPrice;
	}

	public int calculateTotalSumOfPriceFromList() {
		List<WebElement> totalPrice = this.getListOfItemsPrice();
		double totalSum = 0;
		for (int i = 0; i < totalPrice.size(); i++) {
			String itemPrice = totalPrice.get(i).getText().substring(1, 5);
			double price = Double.parseDouble(itemPrice);
			totalSum += price;
		}
		// Because double is not working well on summing, and we need this
		// for comparing purpose, I'm converting sum into int type
		int sum = (int) (totalSum * 100);
		return sum;
	}

	public boolean isTotalEquals() {
		return this.getPriceConvertedToInt() == this.calculateTotalSumOfPriceFromList();
	}

	public boolean cartIsEmpty() {
		try {
			getEmptyCartMsg().isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}