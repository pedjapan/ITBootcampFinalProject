package pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PetStoreMenuPage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public PetStoreMenuPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	public WebElement getLogInBtn() {
		return this.driver.findElement(By.xpath(locators.getProperty("sign_in_from_menu_page")));
	}

	public void setLogInBtn() {
		this.getLogInBtn().click();
	}

	public WebElement getLogInText() {
		return this.driver.findElement(By.xpath(locators.getProperty("enter_user_name_text")));
	}

	public WebElement getCartBtn() {
		return this.driver.findElement(By.xpath(locators.getProperty("view_cart_from_menu_page")));
	}

	public void setCartBtn() {
		this.getCartBtn().click();
	}

	public WebElement getHelpBtn() {
		return this.driver.findElement(By.xpath(locators.getProperty("to_help_from_menu_page")));
	}

	public void setHelpBtn() {
		this.getHelpBtn().click();
	}
	
	public List<WebElement> getLeftNavLinks() {
		return this.driver.findElements(By.xpath(locators.getProperty("left_navigaton_links")));				
	}
	
	public List<WebElement> getUpperNavLinks() {
		return this.driver.findElements(By.xpath(locators.getProperty("upper_navigation_links")));
	}
	
	public List<WebElement> getCenterNavLinks() {
		return this.driver.findElements(By.xpath(locators.getProperty("center_navigation_links")));
	}


	public boolean leftNavLinksVerify() {
		List<WebElement> leftNavLinks = driver.findElements(By.xpath(locators.getProperty("left_navigaton_links")));
		boolean verifiedLeftLinks = false;
		for (int i = 0; i < leftNavLinks.size(); i++) {
			WebElement links = leftNavLinks.get(i);
			int status = verifyURLStatus(links.getAttribute("href"));
			if (status < 400) {
				verifiedLeftLinks = true;
			}
		}
		return verifiedLeftLinks;
	}

	public boolean upperNavLinksVerify() {
		List<WebElement> upperNavLinks = driver.findElements(By.xpath(locators.getProperty("upper_navigation_links")));
		boolean verifiedUpperLinks = false;
		for (int i = 0; i < upperNavLinks.size(); i++) {
			WebElement links = upperNavLinks.get(i);
			int status = verifyURLStatus(links.getAttribute("href"));
			if (status < 400) {
				verifiedUpperLinks = true;
			}
		}
		return verifiedUpperLinks;
	}

	public boolean centerNavLinksVerify() {
		List<WebElement> centerNavLinks = driver
				.findElements(By.xpath(locators.getProperty("center_navigation_links")));
		boolean verifiedCenterLinks = false;
		for (int i = 0; i < centerNavLinks.size(); i++) {
			WebElement links = centerNavLinks.get(i);
			int status = verifyURLStatus(links.getAttribute("href"));
			if (status < 400) {
				verifiedCenterLinks = true;
			}
		}
		return verifiedCenterLinks;
	}

	public boolean topLinksVerify() {
		List<WebElement> topLinks = driver.findElements(By.xpath(locators.getProperty("top_navigation_links")));
		boolean verifiedTopLinks = false;
		for (int i = 0; i < topLinks.size(); i++) {
			WebElement links = topLinks.get(i);
			int status = verifyURLStatus(links.getAttribute("href"));
			if (status < 400) {
				verifiedTopLinks = true;
			}

		}
		return verifiedTopLinks;
	}

	public boolean allLinksVerify() {
		boolean allVerified = false;
		if (leftNavLinksVerify() && upperNavLinksVerify() && centerNavLinksVerify() && topLinksVerify()) {
			allVerified = true;
		}
		return allVerified;
	}
	// for allLinksWorks() method if we want to test links without SoftAssert
	public boolean leftNavLinksWorks() {
		List<WebElement> leftNavLinks = driver.findElements(By.xpath(locators.getProperty("left_navigaton_links")));
		boolean workingLeftLinks = false;
		for (int i = 0; i < leftNavLinks.size(); i++) {
			leftNavLinks.get(i).click();
			String currentUrl = driver.getCurrentUrl().toLowerCase();
			driver.navigate().back();

			leftNavLinks = driver.findElements(By.xpath(locators.getProperty("left_navigaton_links")));
			if (currentUrl.contains(leftNavLinks.get(i).getAttribute("href").toLowerCase())) {
				workingLeftLinks = true;
			}
		}
		return workingLeftLinks;
	}
	// for allLinksWorks() method if we want to test links without SoftAssert
	public boolean upperNavLinksWork() {
		List<WebElement> upperNavLinks = driver.findElements(By.xpath(locators.getProperty("upper_navigation_links")));
		boolean workingUpperLinks = false;
		for (int i = 0; i < upperNavLinks.size(); i++) {
			upperNavLinks.get(i).click();
			String currentUrl = driver.getCurrentUrl().toLowerCase();
			driver.navigate().back();

			upperNavLinks = driver.findElements(By.xpath(locators.getProperty("upper_navigation_links")));
			if (currentUrl.contains(upperNavLinks.get(i).getAttribute("href").toLowerCase())) {
				workingUpperLinks = true;
			}
		}
		return workingUpperLinks;
	}
	// for allLinksWorks() method if we want to test links without SoftAssert
	public boolean centerNavLinksWork() {
		List<WebElement> centerNavLinks = driver
				.findElements(By.xpath(locators.getProperty("center_navigation_links")));
		boolean workingCenterLinks = false;
		for (int i = 0; i < centerNavLinks.size(); i++) {
			centerNavLinks.get(i).click();
			String currentUrl = driver.getCurrentUrl().toLowerCase();
			driver.navigate().back();

			centerNavLinks = driver.findElements(By.xpath(locators.getProperty("center_navigation_links")));
			if (currentUrl.contains(centerNavLinks.get(i).getAttribute("href").toLowerCase())) {
				workingCenterLinks = true;
			}
		}
		return workingCenterLinks;
	}
	// for testing all working links without SoftAssert
	public boolean allLinksWorks() {
		boolean allWorking = false;
		if (leftNavLinksWorks() && upperNavLinksWork() && centerNavLinksWork()) {
			allWorking = true;
		}
		return allWorking;
	}

	public boolean toLogInPage() {
		boolean logInText = false;
		this.setLogInBtn();
		this.getLogInText();
		if (this.getLogInText().isDisplayed()) {
			logInText = true;
		}
		return logInText;
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
			e.printStackTrace();
		}
		return status;
	}

}
