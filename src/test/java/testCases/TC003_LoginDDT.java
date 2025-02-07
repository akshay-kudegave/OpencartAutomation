package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups="datadriven") // Getting data provider form different package
	public void verify_LoginDDT(String email, String pass, String exp) throws InterruptedException {
		
		
		logger.info("******************** Starting TC003_LoginDDT ***********************");
		
		try {
		//Home Page
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		Thread.sleep(3000);
		
		//Login Page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pass);
		lp.clickLogin();
		Thread.sleep(3000);
		
		
		// Data is valid - Login Success - Test Pass - Logout
		// Data is valid - Login Failed - Test Fail
		// Invalid Data - Login Success - Test Fail - Logout
		// Invalid Data - Login Failed - Test Pass
		
		// My account 
		MyAccountPage myAccPage=new MyAccountPage(driver);
		boolean targetPage=myAccPage.isMyAccountPageExists();
		
		if(exp.equalsIgnoreCase("Valid")) {
			
			if(targetPage==true) {
				myAccPage.logout();
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid")) {
			
			if(targetPage==true) {
				myAccPage.logout();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}
		
		}
		
		catch(Exception e){
			Assert.fail();
		}
		Thread.sleep(3000);
		logger.info("******************** Starting TC003_LoginDDT ***********************");
	}
}
