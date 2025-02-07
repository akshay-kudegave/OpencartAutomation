package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{

	
	
	@Test(groups={"regression","master"})
	public void verify_account_registration() {
		
		logger.info("********* Statrting TC001_AccountRegistrationTest ****************");
		
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account Link");
		hp.clickRegister();
		logger.info("Clicked on My Register Link");
		
		AccountRegistrationPage accRegPage= new AccountRegistrationPage(driver);
		
		logger.info("Providing Customer Details");
		accRegPage.setFirstName(randomString().toUpperCase());
		//accRegPage.setFirstName("Rajat");
		accRegPage.setLastName(randomString().toUpperCase());
		//accRegPage.setLastName("Tata");
		accRegPage.setEmail(randomString()+"@gmail.com"); //random generation
		//accRegPage.setEmail("rajattata@gmail.com");
		accRegPage.setTelephone(randomNumbers());
		//accRegPage.setTelephone("9876543212");
		
		String password=randomAlphaNumeric();
		//String password=randomAlphaNumeric();
		accRegPage.setPassword(password);
		//accRegPage.setPassword("abc@1234");
		accRegPage.setConfirmPassword(password);
		//accRegPage.setConfirmPassword("abc@1234");
		
		accRegPage.setPrivacyPolicy();
		accRegPage.clickContinue();
		
		logger.info("Validating expected message...");
		String confmsg=accRegPage.getConfirmationMessage();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		//accRegPage.getErrorMessage();
	    catch(Exception e) {
		logger.error("Test Failed...");
		logger.debug("Debug logs....");
		Assert.fail();
	    }
		
		logger.info("************ Finished TC001_AccountRegistrationTest ***********************");
	}
}