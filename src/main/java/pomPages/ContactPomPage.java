package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPomPage {
	
//	Declare
	@FindBy(xpath = "//a//img[@title = 'Create Contact...']/..")
	private WebElement createContactBtn;
	
	
	@FindBy(xpath = "//a[@class=\"hdrLink\" and text() = 'Contacts']")
	private WebElement contactPageHeader;
		
//	initialize
	public ContactPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
//	utilize
	public void getCreateContactBtn() {
		createContactBtn.click();
	}	
	
	public String getContactHeader() {
		return contactPageHeader.getText();
	}
}
