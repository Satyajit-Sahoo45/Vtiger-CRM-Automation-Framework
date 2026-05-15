package contactModule;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.BaseClass;
import dataProviders.ContactDataProvider;
import pomPages.ContactCreatePomPage;
import pomPages.ContactPomPage;
import pomPages.HomePomPage;
import pomPages.LoginPomPage;
import utils.ExcelFileUtil;
import utils.PropertyFileUtil;
import utils.UtilityObjectClass;

public class CreateContactTest extends BaseClass {
	
	/**
	 * 
	 * @param data
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 * This TestNG class is used to create a new contact
	 */
	@Test(groups = {"contact", "Master"}, dataProvider = "contactDataProvider", 
			dataProviderClass = ContactDataProvider.class, 
			description = "Create Contact",
			retryAnalyzer = listeners.RetryAnalyzer.class)
	public void createTest(Map<String, String> data) throws IOException, InterruptedException {
		
//		String orgName = data.get("ORG_NAME");
		String contactName = data.get("CONTACT_NAME");
		
		HomePomPage hpp = new HomePomPage(getDriver());
		
		//verify home page
		UtilityObjectClass.getTest().log(Status.INFO, "Verifyting Home Page");
		Assert.assertEquals(hpp.getHomePageHeading(), "Home");
		
//		navigate to contact page
		hpp.getContactTab();
		
//		identify the "Contact" tab and click on it
		ContactPomPage cpp = new ContactPomPage(getDriver());
		
//		create new contact
		cpp.getCreateContactBtn();
		
		ContactCreatePomPage ccpp = new ContactCreatePomPage(getDriver());
		
		Assert.assertEquals(ccpp.getCreateContactPageHeading(), "Creating New Contact");
		
//		pass input to lastName TextField
		ccpp.getLastNameTf(contactName);
		
//		save the contact
		ccpp.getSaveBtn();
		
//		navigate back to the contact page
		hpp.getContactTab();
		
		Thread.sleep(2000);
		
		String actualHeader = cpp.getContactHeader();

		try {
		    Assert.assertEquals(actualHeader, "Contacts");
		    ExcelFileUtil.writeData("Contact Data", 1, 4, "Pass");
		    Reporter.log("Completed with Create Contact", true);
		} catch (AssertionError e) {
		    ExcelFileUtil.writeData("Contact Data", 1, 4, "Fail");
		    throw e;
		}
		
//		ContactCreatedListPomPage cclp = new ContactCreatedListPomPage(getDriver()); 
		
//		click on delete button
//		cclp.clickDelete();
//		
//		Thread.sleep(3000);
//		
////		handle popup
//		getDriver().switchTo().alert().accept();
		

	}

}
