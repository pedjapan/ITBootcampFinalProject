package pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public SignInPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	public WebElement getUsernameInput() {
		return this.driver.findElement(By.xpath(locators.getProperty("input_user_name")));
	}

	public void setUsernameInput(String username) {
		this.getUsernameInput().clear();
		this.getUsernameInput().sendKeys(username);
	}

	public WebElement getPasswordInput() {
		return this.driver.findElement(By.xpath(locators.getProperty("input_password")));
	}

	public void setPasswordInput(String password) {
		this.getPasswordInput().clear();
		this.getPasswordInput().sendKeys(password);
	}

	public WebElement getLoginBtn() {
		return this.driver.findElement(By.xpath(locators.getProperty("login_btn")));
	}

	public void setLoginBtn() {
		this.getLoginBtn().click();
	}

	public WebElement getRegisterNowBtn() {
		return this.driver.findElement(By.xpath(locators.getProperty("register_now_btn")));
	}

	public void setRegisterNowBtn() {
		this.getRegisterNowBtn().click();
	}

	public WebElement signinFailed() {
		return this.driver.findElement(By.xpath(locators.getProperty("signin_failed_msg")));
	}

	public WebElement getSignOut() {
		return this.driver.findElement(By.xpath(locators.getProperty("sign_out")));
	}

	public void signIn(String userName, String password) {
		this.setUsernameInput(userName);
		this.setPasswordInput(password);
		this.setLoginBtn();
	}

	public boolean signInOK() {
		boolean signInSuccsess = false;
		try {
			if (this.getSignOut().getText().toLowerCase().contains("sign out")) {
				signInSuccsess = true;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return signInSuccsess;
	}

}