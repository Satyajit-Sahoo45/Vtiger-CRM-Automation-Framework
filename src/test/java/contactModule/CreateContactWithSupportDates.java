package contactModule;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClass;
import dataProviders.ContactDataProvider;
import pomPages.ChildWindowPomPage;
import pomPages.ContactCreatePomPage;
import pomPages.ContactPomPage;
import pomPages.HomePomPage;
import pomPages.LoginPomPage;
import utils.ExcelFileUtil;
import utils.JavaUtility;
import utils.PropertyFileUtil;
import utils.WebDriverUtilities;

public class CreateContactWithSupportDates extends BaseClass {
	
	@Test(groups = {"contact", "Master"}, dataProvider = "contactDataProvider", 
			dataProviderClass = ContactDataProvider.class, 
			description = "Create a Contact With Support Dates",
			retryAnalyzer = listeners.RetryAnalyzer.class)
	public void CreateContactWithSupportDatesTest(Map<String, String> data) throws IOException, InterruptedException {
		
		JavaUtility jutil = new JavaUtility();
		WebDriverUtilities wutil = new WebDriverUtilities();
		
		String orgName = data.get("ORG_NAME");
		String contactName = data.get("CONTACT_NAME");
		
		HomePomPage hpp = new HomePomPage(getDriver());
		
		Assert.assertEquals(hpp.getHomePageHeading(), "Home", "Home page not validated!");
		
//		navigate to contact page
		hpp.getContactTab();
		
//		identify the "Contact" tab and click on it
		ContactPomPage cpp = new ContactPomPage(getDriver());
		
//		create new contact
		cpp.getCreateContactBtn();
		
		ContactCreatePomPage ccpp = new ContactCreatePomPage(getDriver());
		
		Assert.assertEquals(ccpp.getCreateContactPageHeading(), "Creating New Contact", "New Contact Page not validated!");
		
//		pass input to lastName TextField
		ccpp.getLastNameTf(contactName);
		
//		click on organization button
		ccpp.getAddOrgNameBtn();
		
		ChildWindowPomPage cwpp = new ChildWindowPomPage(getDriver());
		
//		fetch the parent window id
		String parentWinId = wutil.Fetchwindowid(getDriver());
		
//		fetch all the windows
		Set<String> allWinIds = wutil.FetchAllwindowid(getDriver());
		
		Thread.sleep(3000);
		
		wutil.SwitchChildwindow_URL(getDriver(), "module=Accounts&action=Popup");
		
		cwpp.searchOrgName(orgName);
		
		Thread.sleep(3000);
		
		getDriver().findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
//		switch back the getDriver() controller to the parent window
		wutil.SwitchToParentWindow(getDriver(), parentWinId);		
		
//		identify support start date TextField and pass the value 
		String startDate = jutil.fetchCurrentDate();
		ccpp.getSupportStateDateTf(startDate);
		
//		identify support end date TextField and pass the value
		String endDate = jutil.fetchDateAfterGivenDays(30);
		ccpp.getSupportEndDateTf(endDate);
		
//		save the contact
		ccpp.getSaveBtn();
		
//		navigate back to the contact page
		hpp.getContactTab();
		
		Thread.sleep(2000);
		
		String actualHeader = cpp.getContactHeader();
		
		try {
		    Assert.assertEquals(actualHeader, "Contacts");
		    ExcelFileUtil.writeData("Contact Data", 7, 7, "Pass");
		    Reporter.log("Completed Create Contact with support dates", true);
		} catch (AssertionError e) {
		    ExcelFileUtil.writeData("Contact Data", 7, 7, "Fail");
		    throw e;
		}
	}
}
