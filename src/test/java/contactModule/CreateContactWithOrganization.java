package contactModule;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.BaseClass;
import dataProviders.ContactDataProvider;
import pomPages.ChildWindowPomPage;
import pomPages.ContactCreatePomPage;
import pomPages.ContactPomPage;
import pomPages.HomePomPage;
import pomPages.LoginPomPage;
import utils.ExcelFileUtil;
import utils.PropertyFileUtil;
import utils.WebDriverUtilities;

public class CreateContactWithOrganization extends BaseClass {
	
	/**
	 * 
	 * @param data
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 * This T
	 */
	@Test(groups = {"contact", "Master"}, dataProvider = "contactDataProvider", 
			dataProviderClass = ContactDataProvider.class, 
			description = "Create a Contact With Organization",
			retryAnalyzer = listeners.RetryAnalyzer.class)
	public void CreateContactWithOrganizationTest(Map<String, String> data) throws IOException, InterruptedException {
		
		WebDriverUtilities wutil = new WebDriverUtilities();
		
		String orgName = data.get("ORG_NAME");
		String contactName = data.get("CONTACT_NAME");
		
		HomePomPage hpp = new HomePomPage(driver);
		
		listeners.TestListener.test.log(Status.INFO, "Verifyting Home Page");
		Assert.assertEquals(hpp.getHomePageHeading(), "Home", "Home Page not validated!");
		listeners.TestListener.test.log(Status.INFO, "Verified Home Page");
		
//		navigate to contact page
		listeners.TestListener.test.log(Status.INFO, "Identify Contact tab and click on it");
		hpp.getContactTab();
		
//		identify the "Contact" tab and click on it
		ContactPomPage cpp = new ContactPomPage(driver);
		
//		create new contact
		listeners.TestListener.test.log(Status.INFO, "Identify Plus icon to create a new contact and click on it");
		cpp.getCreateContactBtn();
		
		ContactCreatePomPage ccpp = new ContactCreatePomPage(driver);
		
		Assert.assertEquals(ccpp.getCreateContactPageHeading(), "Creating New Contact", "New Contact Page not validated!");
		
//		pass input to lastName TextField
		ccpp.getLastNameTf(contactName);
		
//		click on organization button
		ccpp.getAddOrgNameBtn();
		
		ChildWindowPomPage cwpp = new ChildWindowPomPage(driver);
		
//		fetch the parent window id
		String parentWinId = wutil.Fetchwindowid(driver);
		
//		fetch all the windows
		Set<String> allWinIds = wutil.FetchAllwindowid(driver);
		
		Thread.sleep(3000);
		
		wutil.SwitchChildwindow_URL(driver, "module=Accounts&action=Popup");
		
		cwpp.searchOrgName(orgName);
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
//		switch back the driver controller to the parent window
		wutil.SwitchToParentWindow(driver, parentWinId);
		
//		save the contact
		ccpp.getSaveBtn();
		
//		navigate back to the contact page
		hpp.getContactTab();
		
		Thread.sleep(2000);
		
		String actualHeader = cpp.getContactHeader();
		
		try {
		    Assert.assertEquals(actualHeader, "Contacts");
		    ExcelFileUtil.writeData("Contact Data", 4, 5, "Pass");
		    Reporter.log("Completed Create Contact with org", true);
		} catch (AssertionError e) {
		    ExcelFileUtil.writeData("Contact Data", 4, 5, "Fail");
		    throw e;
		}
	}

}
