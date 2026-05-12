package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationCreatePomPage {
	
//	Declare
	@FindBy(xpath = "//span[text() = 'Creating New Organization']")
	private WebElement createOrgPageHeading;
	
	@FindBy(xpath = "//input[@name = 'accountname']")
	private WebElement orgNameTf;
	
	@FindBy(xpath = "//input[@title = 'Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name = "industry")
	private WebElement industryDropdown;
	
	@FindBy(name = "accounttype")
	private WebElement typeDropdown;
	
	@FindBy(id = "phone")
	private WebElement phoneTF;
	
//	initialize
	public OrganizationCreatePomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
//	utility
	public String getCreateOrgPageHeading() {
		return createOrgPageHeading.getText();
	}
	
	public void getSaveBtn() {
		saveBtn.click();
	}
	
	public void getOrgNameTextField(String orgName) {
		orgNameTf.sendKeys(orgName);
	}
	
	public WebElement getIndustryDropdown() {
		return industryDropdown;
	}
	
	public WebElement getTypeDropdown() {
		return typeDropdown;
	}
	
	public void getPhoneTF(String phno) {
		phoneTF.sendKeys(phno);
	}

}
