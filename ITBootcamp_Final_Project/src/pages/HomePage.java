package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public HomePage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	public WebElement getEnterStoreLink() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("enter_store")));
	}

	public void setEnterStoreLink() {
		this.getEnterStoreLink().click();
	}

	public WebElement getSignIn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("sign_in_from_menu_page")));
	}

	public boolean isInShop() {
		boolean inShop = false;
		if (getSignIn().getText().contains("Sign In")) {
			inShop = true;
		}
		return inShop;
	}

}