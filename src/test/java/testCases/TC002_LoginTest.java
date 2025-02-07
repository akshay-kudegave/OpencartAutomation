package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	@Test(groups={"sanity","master"})
	public void verify_login() throws InterruptedException {
		
		logger.info("***** Starting TC002_LoginTest *******");
		
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		Thread.sleep(3000);
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(prop.getProperty("email1"));
		lp.setPassword(prop.getProperty("password1"));
		lp.clickLogin();
		Thread.sleep(3000);
		
		MyAccountPage myAccPage=new MyAccountPage(driver);
		Assert.assertEquals(myAccPage.isMyAccountPageExists(), true, "Login failed");
		
		}
		
		catch(Exception e) {
		logger.info("***** Finished TC002_LoginTest *******");
		}
	}
	
}
