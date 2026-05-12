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

public class CreateOrgWithIndustryAndType extends BaseClass {
	
	/**
	 * 
	 * @param data
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 * This TestNG class is used to create an organization with industry and it's type
	 */
	@Test(groups = {"organization", "Master"}, dataProvider = "organizationDataProvider", 
			dataProviderClass = OrgDataProvider.class, 
			description = "Create Org With Industry and Type",
			retryAnalyzer = listeners.RetryAnalyzer.class)
	public void CreateOrgWithIndustryAndTypeTest(Map<String, String> data) throws IOException, InterruptedException {
	
		WebDriverUtilities wutil = new WebDriverUtilities();
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
		
//		select industry option
		
		WebElement industryDropdown = ocp.getIndustryDropdown();
//		ocp.selectByValue(industryDropdown, data.get("INDUSTRY"));
		String industry = data.get("INDUSTRY");
		wutil.SelectDDbyValue(industryDropdown, industry);
		
//		select type option
		WebElement typeDropdown = ocp.getTypeDropdown();
//		ocp.selectByValue(typeDropdown, data.get("TYPE"));
		String industryType = data.get("TYPE");
		wutil.SelectDDbyValue(typeDropdown, industryType);
		
//		click on save button
		ocp.getSaveBtn();
		
		OrganizationCreatedListPomPage ocl = new OrganizationCreatedListPomPage(driver);

		Assert.assertEquals(true, ocl.getOrgInfoHeader().contains(orgName));
		
//		click on organization tab
		hpp.getOrganizationTab();
		
		Thread.sleep(2000);
		
		String actualHeader = opp.getOrgHeader();

		try {
		    Assert.assertEquals(actualHeader, "Organizations");
		    ExcelFileUtil.writeData("Organization Data", 4, 6, "Pass");
		    Reporter.log("Completed Crate Org with industry and it's type", true);
		} catch (AssertionError e) {
		    ExcelFileUtil.writeData("Organization Data", 4, 6, "Fail");
		    throw e;
		}
	}
}
