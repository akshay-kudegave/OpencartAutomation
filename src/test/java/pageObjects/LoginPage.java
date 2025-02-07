package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@placeholder='E-Mail Address']")
	WebElement emailAdd;
	
	@FindBy(xpath="//input[@placeholder='Password']")
	WebElement password;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnLogin;
	
	public void setEmail(String email) {
		emailAdd.sendKeys(email);
	}
	
	public void setPassword(String pass) {
		password.sendKeys(pass);
		
	}
	
	public void clickLogin() {
		btnLogin.click();
	}
	
}
