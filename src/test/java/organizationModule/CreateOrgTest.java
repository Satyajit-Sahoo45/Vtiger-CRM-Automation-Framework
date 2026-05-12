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
import utils.WebDriverUtilities;

public class CreateOrgTest extends BaseClass{
	
	@Test(groups = {"organization", "Master"}, dataProvider = "organizationDataProvider", 
			dataProviderClass = OrgDataProvider.class, 
			description = "Create Org",
			retryAnalyzer = listeners.RetryAnalyzer.class)
	public void CreateTest(Map<String, String> data) throws IOException, InterruptedException {
		
		JavaUtility ju = new JavaUtility();
		
		String orgName = data.get("ORG_NAME") + ju.fetchRandomInteger();
		
		HomePomPage hpp = new HomePomPage(driver);
		
		Assert.assertEquals(hpp.getHomePageHeading(), "Home");
		
		
//		identify the "Organization" tab and click on it
		OrganizationPomPage opp = new OrganizationPomPage(driver);
//		hpp.clickOnOrganizationTab();
		hpp.getOrganizationTab();
		
//		create new organization
		opp.getCreateOrgBtn();
		
		OrganizationCreatePomPage ocp = new OrganizationCreatePomPage(driver);

		Assert.assertEquals(ocp.getCreateOrgPageHeading(), "Creating New Organization");
		
//		pass input to Organization Name
		ocp.getOrgNameTextField(orgName);
		
//		click on save button
		ocp.getSaveBtn();
		
		Thread.sleep(5000);
		
		OrganizationCreatedListPomPage ocl = new OrganizationCreatedListPomPage(driver);
		
		Assert.assertEquals(true, ocl.getOrgInfoHeader().contains(orgName));
		
//		click on organization tab
		hpp.getOrganizationTab();
		
		Thread.sleep(2000);
		
		String actualHeader = opp.getOrgHeader();

		try {
		    Assert.assertEquals(actualHeader, "Organizations");
		    ExcelFileUtil.writeData("Organization Data", 1, 4, "Pass");
		    Reporter.log("Completed Create Org", true);
		} catch (AssertionError e) {
		    ExcelFileUtil.writeData("Organization Data", 1, 4, "Fail");
		    throw e;
		}
		
//		click on delete button
//		ocl.clickDelete();
		
//		Thread.sleep(3000);
		
//		handle popup
//		driver.switchTo().alert().accept();	
		
		
	}
	
}
