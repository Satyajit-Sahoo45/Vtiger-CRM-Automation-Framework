package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChildWindowPomPage {
	
//	Declare
	@FindBy(id = "search_txt")
	private WebElement searchBox;
	
	@FindBy(xpath = "//input[@name = 'search']")
	private WebElement searchNowBtn;
	
	
//	Initialize
	public ChildWindowPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
//	Utility	
	
	public WebElement getSearchBox() {
		return searchBox;
	}
	
	public WebElement getSearchNowBtn() {
		return searchNowBtn;
	}
	
	public void searchOrgName(String orgName) {
		getSearchBox().sendKeys(orgName);
		getSearchNowBtn().click();
	}
}
