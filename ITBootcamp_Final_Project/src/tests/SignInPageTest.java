package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.SignInPage;
import utils.ExcelUtils;

public class SignInPageTest {
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
		this.locators =  new Properties();
		locators.load(new FileInputStream("config/petstore.properties"));
		driver.navigate().to(this.locators.getProperty("sign_in_url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void signIn() throws InterruptedException {	
		SignInPage signInPage = new SignInPage(driver, locators, waiter);	
		SoftAssert sa = new SoftAssert();
		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		ExcelUtils.setWorkSheet(1);		
		
		for (int i = 1; i <ExcelUtils.getRowNumber() ; i++) {
			driver.navigate().to(this.locators.getProperty("sign_in_url"));
			String userName = ExcelUtils.getDataAt(i, 0);
			String pass = ExcelUtils.getDataAt(i, 1);
			
			signInPage.signIn(userName, pass);
			sa.assertTrue(signInPage.signInOK());
			signInPage.getSignOut().click();
		}
		sa.assertAll();		
	}

	@AfterClass
	public void afterClass() {
		ExcelUtils.closeExcell();
		this.driver.close();
	}
}