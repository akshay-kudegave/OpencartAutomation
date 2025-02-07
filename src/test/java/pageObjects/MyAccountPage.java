package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h2[text()='My Account']") // MyAccount Page heading
	WebElement msgHeading;
	
	@FindBy(xpath="//div[@class='list-group']//a[contains(text(),'Logout')]") // LogoutLink
	WebElement lnkLogout;
	
	public boolean isMyAccountPageExists() {
		try {
		return msgHeading.isDisplayed();
		}
		catch (Exception e){
			return false;
		}
	}
	
	public void logout() {
		lnkLogout.click();
	}
}
