package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.RegistrationPage;
import utils.ExcelUtils;

public class RegistrationPageTest {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	@BeforeClass
	public void setup() throws FileNotFoundException, IOException {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.locators = new Properties();
		locators.load(new FileInputStream("config/petstore.properties"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void registeringUsers() throws InterruptedException {
		RegistrationPage newUser = new RegistrationPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();
		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		ExcelUtils.setWorkSheet(1);
		// Write user ID in pet-store-data.xlsx file for later use
		// in SignInPageTest as username when testing user login
		ExcelUtils.setUniqueRandomId();
		ExcelUtils.setLanguagePreference();
		ExcelUtils.setFavorite();

		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			driver.navigate().to(this.locators.getProperty("registration_url"));
			String userID = ExcelUtils.getDataAt(i, 0);
			String password = ExcelUtils.getDataAt(i, 1);
			String name = ExcelUtils.getDataAt(i, 2);
			String lastName = ExcelUtils.getDataAt(i, 3);
			String email = ExcelUtils.getDataAt(i, 4);
			String phone = ExcelUtils.getDataAt(i, 5);
			String address1 = ExcelUtils.getDataAt(i, 6);
			String address2 = ExcelUtils.getDataAt(i, 7);
			String city = ExcelUtils.getDataAt(i, 8);
			String state = ExcelUtils.getDataAt(i, 9);
			String zip = ExcelUtils.getDataAt(i, 10);
			String country = ExcelUtils.getDataAt(i, 11);
			String language = ExcelUtils.getDataAt(i, 12);
			String category =  ExcelUtils.getDataAt(i, 13);
			
			newUser.fillForm(userID, password, password, name, lastName, email, phone, address1, address2, city, state,
					zip, country,language, category);

			sa.assertTrue(newUser.registeredOK());

		}
		sa.assertAll();
	}

	@AfterClass
	public void afterClass() {
		ExcelUtils.closeExcell();
		this.driver.close();
	}

}
