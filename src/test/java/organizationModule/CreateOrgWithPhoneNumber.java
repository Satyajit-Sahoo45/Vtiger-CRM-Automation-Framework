package organizationModule;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClass;
import dataProviders.OrgDataProvider;
import pomPages.HomePomPage;
import pomPages.LoginPomPage;
import pomPages.OrganizationCreatePomPage;
import pomPages.OrganizationCreatedListPomPage;
import pomPages.OrganizationPomPage;
import utils.ExcelFileUtil;
import utils.JavaUtility;
import utils.PropertyFileUtil;

public class CreateOrgWithPhoneNumber extends BaseClass {
	
	@Test(groups = {"organization", "Master"}, dataProvider = "organizationDataProvider", 
			dataProviderClass = OrgDataProvider.class, 
			description = "Create Org With Phone no",
			retryAnalyzer = listeners.RetryAnalyzer.class)
	public void CreateOrgWithPhoneNumberTest(Map<String, String> data) throws IOException, InterruptedException {

		JavaUtility ju = new JavaUtility();
		Thread.sleep(5000);
		
		String orgName = data.get("ORG_NAME") + ju.fetchRandomInteger();
		String phno = data.get("PHNO");
		
		HomePomPage hpp = new HomePomPage(driver);

		Assert.assertEquals(hpp.getHomePageHeading(), "Home");		
		
//		identify the "Organization" tab and click on it
		OrganizationPomPage opp = new OrganizationPomPage(driver);
		hpp.getOrganizationTab();
		
//		create new organization
		opp.getCreateOrgBtn();
		
		OrganizationCreatePomPage ocp = new OrganizationCreatePomPage(driver);

		Assert.assertEquals(ocp.getCreateOrgPageHeading(), "Creating New Organization");
		
//		pass input to Organization Name
		ocp.getOrgNameTextField(orgName);
		
//		identify phone no text field and pass the input
		ocp.getPhoneTF(phno);
		
		
//		click on save button
		ocp.getSaveBtn();
		
		OrganizationCreatedListPomPage ocl = new OrganizationCreatedListPomPage(driver);

		Assert.assertEquals(true,ocl.getOrgInfoHeader().contains(orgName));
		
//		click on organization tab
		hpp.getOrganizationTab();
		
		Thread.sleep(2000);
		
		String actualHeader = opp.getOrgHeader();

		try {
		    Assert.assertEquals(actualHeader, "Organizations");
		    ExcelFileUtil.writeData("Organization Data", 7, 5, "Pass");
		    Reporter.log("Completed Crate Org with phone number", true);
		} catch (AssertionError e) {
		    ExcelFileUtil.writeData("Organization Data", 7, 5, "Fail");
		    throw e;
		}
	}

}
