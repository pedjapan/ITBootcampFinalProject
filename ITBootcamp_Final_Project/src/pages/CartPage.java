package pages;

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
	
	public WebElement getQuantity() {
		return this.driver.findElement(By.xpath(locators.getProperty("product_quantity")));
	}

	public void setQuantity(int quantity) {
		this.getQuantity().toString();
	}
	
	public boolean isInCart() {
		boolean isAdded = false;
		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		ExcelUtils.setWorkSheet(0);
		for (int i = 0; i < ExcelUtils.getRowNumber(); i++) {
			ExcelUtils.getDataAt(i, 1);
		}
		return isAdded;
		
	}
}