package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPomPage {
	
//	Declare
	@FindBy(xpath = "//a//img[@title = 'Create Organization...']/..")
	private WebElement createOrgBtn;
	
	@FindBy(xpath = "//a[@class=\"hdrLink\" and text() = 'Organizations']")
	private WebElement orgPageHeader;
	
//	initialize
	public OrganizationPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
//	utilize
	public void getCreateOrgBtn() {
		createOrgBtn.click();
	}
	
	public String getOrgHeader() {
		return orgPageHeader.getText();
	}

}
