package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactCreatePomPage {
	
//	Declare
	@FindBy(xpath = "//span[text() = 'Creating New Contact']")
	private WebElement createContactPageHeading;
	
	@FindBy(xpath = "//input[@name = 'lastname']")
	private WebElement lastNameTf;
	
	@FindBy(xpath = "(//img[@title = 'Select'])[1]")
	private WebElement addOrgNameBtn;
	
	@FindBy(xpath = "//input[@title = 'Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(id = "jscal_field_support_start_date")
	private WebElement supportStartDateTf;
	
	@FindBy(id = "jscal_field_support_end_date")
	private WebElement supportEndDateTf;
	
	
//	initialize
	public ContactCreatePomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
//	utilize
	public String getCreateContactPageHeading() {
		return createContactPageHeading.getText();
	}
	
	public void getLastNameTf(String lastname) {
		lastNameTf.sendKeys(lastname);
	}
	
	public void getAddOrgNameBtn() {
		addOrgNameBtn.click();
	}
	
	public void getSaveBtn() {
		saveBtn.click();
	}
	
	public void getSupportStateDateTf(String startdate) {
		supportStartDateTf.clear();
		supportStartDateTf.sendKeys(startdate);
	}
	
	public void getSupportEndDateTf(String enddate) {
		supportEndDateTf.clear();
		supportEndDateTf.sendKeys(enddate);
	}	
}
