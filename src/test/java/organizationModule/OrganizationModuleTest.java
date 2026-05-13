package organizationModule;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.BaseClass;
import dataProviders.OrgDataProvider;
import pomPages.HomePomPage;
import pomPages.OrganizationCreatePomPage;
import pomPages.OrganizationCreatedListPomPage;
import pomPages.OrganizationPomPage;
import utils.ExcelFileUtil;
import utils.JavaUtility;
import utils.UtilityObjectClass;
import utils.WebDriverUtilities;

public class OrganizationModuleTest extends BaseClass {
	
	@Test(groups = {"organization", "Master"}, dataProvider = "organizationDataProvider", 
			dataProviderClass = OrgDataProvider.class, 
			description = "Create Org",
			retryAnalyzer = listeners.RetryAnalyzer.class)
	public void CreateTest(Map<String, String> data) throws IOException, InterruptedException {
		
		JavaUtility ju = new JavaUtility();
		
		String orgName = data.get("ORG_NAME") + ju.fetchRandomInteger();
		
		HomePomPage hpp = new HomePomPage(driver);
		
		UtilityObjectClass.getTest().log(Status.INFO, "Verifyting Home Page Of Organization");
		Assert.assertEquals(hpp.getHomePageHeading(), "Home");
		
		
//		identify the "Organization" tab and click on it
		OrganizationPomPage opp = new OrganizationPomPage(driver);
//		hpp.clickOnOrganizationTab();
		UtilityObjectClass.getTest().log(Status.INFO, "Identify Organization tab and click on it");
		hpp.getOrganizationTab();
		
//		create new organization
		UtilityObjectClass.getTest().log(Status.INFO, "Identify Plus icon to create a new Organization and click on it");
		opp.getCreateOrgBtn();
		
		OrganizationCreatePomPage ocp = new OrganizationCreatePomPage(driver);

		Assert.assertEquals(ocp.getCreateOrgPageHeading(), "Creating New Organization");
		UtilityObjectClass.getTest().log(Status.INFO, "Validating Create New Organization Page");
		
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
	
	
	
	@Test(groups = {"organization", "Master"}, dataProvider = "organizationDataProvider", 
			dataProviderClass = OrgDataProvider.class, 
			description = "Create Org With Industry and Type",
			retryAnalyzer = listeners.RetryAnalyzer.class)
	public void CreateOrgWithIndustryAndTypeTest(Map<String, String> data) throws IOException, InterruptedException {
	
		WebDriverUtilities wutil = new WebDriverUtilities();
		JavaUtility ju = new JavaUtility();
		
		String orgName = data.get("ORG_NAME") + ju.fetchRandomInteger();
		
		UtilityObjectClass.getTest().log(Status.INFO, "Verifyting Home Page dor Org with Industry and type");
		HomePomPage hpp = new HomePomPage(driver);
		
		Assert.assertEquals(hpp.getHomePageHeading(), "Home");
		
//		identify the "Organization" tab and click on it
		OrganizationPomPage opp = new OrganizationPomPage(driver);
//		hpp.clickOnOrganizationTab();
		UtilityObjectClass.getTest().log(Status.INFO, "Identify Organization tab and click on it");
		hpp.getOrganizationTab();
		
		UtilityObjectClass.getTest().log(Status.INFO, "Identify Plus icon to create a new Organization and click on it");
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

		UtilityObjectClass.getTest().log(Status.INFO, "Verifyting Home Page for Org with phone number");
		
		Assert.assertEquals(hpp.getHomePageHeading(), "Home");		
		
//		identify the "Organization" tab and click on it
		OrganizationPomPage opp = new OrganizationPomPage(driver);
		UtilityObjectClass.getTest().log(Status.INFO, "Identify Organization tab and click on it");
		hpp.getOrganizationTab();
		
//		create new organization
		UtilityObjectClass.getTest().log(Status.INFO, "Identify Plus icon to create a new Organization and click on it");
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
