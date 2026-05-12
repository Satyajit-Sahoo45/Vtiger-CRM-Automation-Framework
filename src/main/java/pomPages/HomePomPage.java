package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WebDriverUtilities;

public class HomePomPage {
	
// Declare
	@FindBy(xpath = "//a[@class = 'hdrLink']")
	private WebElement homePageHeading;
	
	@FindBy(linkText = "Organizations")
	private WebElement organizationTab;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactsTab;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement userIcon;
	
	@FindBy(linkText = "Sign Out")
	private WebElement SignOutBtn;
	
	
//	initialize
	public HomePomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
//	utilize
	public String getHomePageHeading() {
		return homePageHeading.getText();
	}
	
	public void getOrganizationTab() {
		organizationTab.click();
	}
	
	public void getContactTab() {
		contactsTab.click();
	}
	
	public void logout(WebDriver driver) {
		new WebDriverUtilities().MouserOverToElement(driver, userIcon);
		new WebDriverUtilities().ClickOnElement(driver, SignOutBtn);
	}

}
