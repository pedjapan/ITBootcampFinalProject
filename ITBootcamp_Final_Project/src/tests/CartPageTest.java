package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.xml.ISuiteParser;

import pages.CartPage;
import pages.StoreItemPage;
import utils.ExcelUtils;

public class CartPageTest {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	@BeforeClass
	@Parameters("browser")
	public void setup(String browser) throws FileNotFoundException, IOException {
		if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "driver-lib\\geckodriver.exe");
			this.driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
			this.driver = new ChromeDriver();			
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "driver-lib\\msedgedriver.exe");
			this.driver = new EdgeDriver();
		}
		this.locators = new Properties();
		locators.load(new FileInputStream("config/petstore.properties"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.navigate().to(this.locators.getProperty("store_menu_page_url"));

	}

	@Test(priority = 1)
	public void isInCart() {
		StoreItemPage storeItem = new StoreItemPage(driver, locators, waiter);
		CartPage cart = new CartPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();
		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		ExcelUtils.setWorkSheet(0);

		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			driver.navigate().to(ExcelUtils.getDataAt(i, 1));
			String itemId = ExcelUtils.getDataAt(i, 0);
			storeItem.setAddToCart();
			sa.assertTrue(cart.isProductAddedToCart(itemId));
		}
		sa.assertAll();
	}

	@Test(priority = 2)
	public void compareTotalPrices() {
		CartPage cart = new CartPage(driver, locators, waiter);

		Assert.assertTrue(cart.isTotalEquals());
	}

	@Test(priority = 3)
	public void deleteCookiesTest() {
		StoreItemPage storeItem = new StoreItemPage(driver, locators, waiter);
		CartPage cart = new CartPage(driver, locators, waiter);
		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		ExcelUtils.setWorkSheet(0);

		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			driver.navigate().to(ExcelUtils.getDataAt(i, 1));
			storeItem.setAddToCart();
		}
		cart.deleteCookiesFromCartPage();
		Assert.assertTrue(cart.cartIsEmpty());
	}

	@AfterClass
	public void afterClass() {
		ExcelUtils.closeExcell();
		this.driver.close();
	}

}
