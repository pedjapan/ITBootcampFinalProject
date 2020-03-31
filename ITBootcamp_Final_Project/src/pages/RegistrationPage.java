package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public RegistrationPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	public WebElement getUserID() {
		return this.driver.findElement(By.xpath(locators.getProperty("user_id_input")));
	}

	public void setUserID(String userid) {
		this.getUserID().clear();
		this.getUserID().sendKeys(userid);
	}

	public WebElement getNewPassword() {
		return this.driver.findElement(By.xpath(locators.getProperty("new_password_input")));
	}

	public void setNewPassword(String password) {
		this.getNewPassword().clear();
		this.getNewPassword().sendKeys(password);
	}

	public WebElement getRepeatPassword() {
		return this.driver.findElement(By.xpath(locators.getProperty("repeat_password_input")));
	}

	public void setRepeatPassword(String password) {
		this.getRepeatPassword().clear();
		this.getRepeatPassword().sendKeys(password);
	}

	public WebElement getFirstName() {
		return this.driver.findElement(By.xpath(locators.getProperty("first_name_input")));
	}

	public void setFirstName(String name) {
		this.getFirstName().clear();
		this.getFirstName().sendKeys(name);
	}

	public WebElement getLastName() {
		return this.driver.findElement(By.xpath(locators.getProperty("last_name_input")));
	}

	public void setLastName(String lastName) {
		this.getLastName().clear();
		this.getLastName().sendKeys(lastName);
	}

	public WebElement getEmail() {
		return this.driver.findElement(By.xpath(locators.getProperty("email_input")));
	}

	public void setEmail(String email) {
		this.getEmail().clear();
		this.getEmail().sendKeys(email);
	}

	public WebElement getPhone() {
		return this.driver.findElement(By.xpath(locators.getProperty("phone_input")));
	}

	public void setPhone(String phone) {
		this.getPhone().clear();
		this.getPhone().sendKeys(phone);
	}

	public WebElement getAddress1() {
		return this.driver.findElement(By.xpath(locators.getProperty("address1_input")));
	}

	public void setAddress1(String address1) {
		this.getAddress1().clear();
		this.getAddress1().sendKeys(address1);
	}

	public WebElement getAddress2() {
		return this.driver.findElement(By.xpath(locators.getProperty("address2_input")));
	}

	public void setAddress2(String address2) {
		this.getAddress2().clear();
		this.getAddress2().sendKeys(address2);
	}

	public WebElement getCity() {
		return this.driver.findElement(By.xpath(locators.getProperty("city_input")));
	}

	public void setCity(String city) {
		this.getCity().clear();
		this.getCity().sendKeys(city);
	}

	public WebElement getState() {
		return this.driver.findElement(By.xpath(locators.getProperty("state_input")));
	}

	public void setState(String state) {
		this.getState().clear();
		this.getState().sendKeys(state);
	}

	public WebElement getZip() {
		return this.driver.findElement(By.xpath(locators.getProperty("zip_input")));
	}

	public void setZip(String zip) {
		this.getZip().clear();
		this.getZip().sendKeys(zip);
	}

	public WebElement getCountry() {
		return this.driver.findElement(By.xpath(locators.getProperty("country_input")));
	}

	public void setCountry(String country) {
		this.getCountry().clear();
		this.getCountry().sendKeys(country);
	}

	public WebElement getSaveAccountInfo() {
		return this.driver.findElement(By.xpath(locators.getProperty("save_account_info")));
	}

	public void setSaveAccountInfo() {
		this.getSaveAccountInfo().click();
	}

	public void fillForm(String userid, String password, String pass, String name, String lastName, String email,
			String phone, String address1, String address2, String city, String state, String zip, String country) {
		this.setUserID(userid);
		this.setNewPassword(password);
		this.setRepeatPassword(pass);
		this.setFirstName(name);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPhone(phone);
		this.setAddress1(address1);
		this.setAddress2(address2);
		this.setCity(city);
		this.setState(state);
		this.setZip(zip);
		this.setCountry(country);
		this.setSaveAccountInfo();
	}

	public boolean registeredOK() {
		boolean registrationOK = false;
		if (this.driver.getCurrentUrl().contains(locators.getProperty("store_menu_page_url"))) {
			registrationOK = true;
		}
		return registrationOK;
	}

}
