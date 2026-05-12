package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactCreatedListPomPage {
	
//	Declare
	@FindBy(xpath = "//a[text() = 'del']")
	private WebElement delBtn;
	
	
//	initialize
	public ContactCreatedListPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
//	utilize
	public void getDeleteBtn() {
		delBtn.click();
	}
}
