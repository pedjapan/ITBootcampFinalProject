package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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

import pages.HomePage;
import pages.PetStoreMenuPage;

public class PetStoreMenuTest {
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
	}

	@Test
	public void allLinksVerifyTest() {
		driver.navigate().to(this.locators.getProperty("store_menu_page_url"));
		PetStoreMenuPage petStoreMenuPageAllLinks = new PetStoreMenuPage(driver, locators, waiter);
		Assert.assertTrue(petStoreMenuPageAllLinks.allLinksVerify());
	}

	@Test
	public void leftNavLinksTakesToCorrectPageTest() {
		PetStoreMenuPage PetStoreLeftLinks = new PetStoreMenuPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();

		driver.navigate().to(this.locators.getProperty("store_menu_page_url"));
		List<WebElement> leftNavLinks = PetStoreLeftLinks.getLeftNavLinks();
		for (int i = 0; i < leftNavLinks.size(); i++) {
			leftNavLinks.get(i).click();
			String currentUrl = driver.getCurrentUrl().toLowerCase();
			driver.navigate().back();
			leftNavLinks = PetStoreLeftLinks.getLeftNavLinks();
			sa.assertTrue(currentUrl.contains(leftNavLinks.get(i).getAttribute("href").toLowerCase()));
		}
		sa.assertAll();
	}

	@Test
	public void upperNavLinksCorrectPageTest() {
		PetStoreMenuPage PetStoreUpperLinks = new PetStoreMenuPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();

		driver.navigate().to(this.locators.getProperty("store_menu_page_url"));
		List<WebElement> upperNavLinks = PetStoreUpperLinks.getUpperNavLinks();
		for (int i = 0; i < upperNavLinks.size(); i++) {
			upperNavLinks.get(i).click();
			String currentUrl = driver.getCurrentUrl().toLowerCase();
			driver.navigate().back();
			upperNavLinks = PetStoreUpperLinks.getUpperNavLinks();
			sa.assertTrue(currentUrl.contains(upperNavLinks.get(i).getAttribute("href").toLowerCase()));
		}
		sa.assertAll();
	}

	@Test
	public void centerNavLinksWork() {
		PetStoreMenuPage PetStoreCenterLinks = new PetStoreMenuPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();

		driver.navigate().to(this.locators.getProperty("store_menu_page_url"));
		List<WebElement> centerNavLinks = PetStoreCenterLinks.getCenterNavLinks();
		for (int i = 0; i < centerNavLinks.size(); i++) {
			centerNavLinks.get(i).click();
			String currentUrl = driver.getCurrentUrl().toLowerCase();
			driver.navigate().back();
			centerNavLinks = PetStoreCenterLinks.getCenterNavLinks();
			sa.assertTrue(currentUrl.contains(centerNavLinks.get(i).getAttribute("href").toLowerCase()));
		}
		sa.assertAll();
	}

	@Test
	public void logInBtnTest() {
		driver.navigate().to(this.locators.getProperty("store_menu_page_url"));
		PetStoreMenuPage petStoreMenuPage = new PetStoreMenuPage(driver, locators, waiter);
		Assert.assertTrue(petStoreMenuPage.toLogInPage());
	}

	@AfterClass
	public void afterClass() {
		 this.driver.close();
	}

}

