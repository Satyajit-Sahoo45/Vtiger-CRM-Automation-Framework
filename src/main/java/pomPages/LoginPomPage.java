package pomPages;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPomPage {

//	Declare
	@FindBy(xpath = "//input[@name = 'user_name']")
	private WebElement userNameTf;
	
	@FindBy(xpath = "//input[@name = 'user_password']")
	private WebElement passwordTf;
	
	@FindBy(xpath = "//input[@id = 'submitButton']")
	private WebElement loginBtn;
	
	
//	initialize
	public LoginPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
//	utilize
	public WebElement getUsernameTf() {
		return userNameTf;
	}
	
	public WebElement getPasswordTf() {
		return passwordTf;
	}
	
	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	
//	action
	public void Login(String username, String password) throws IOException {
		
		getUsernameTf().sendKeys(username);
		getPasswordTf().sendKeys(password);
		
		getLoginBtn().submit();
	}
	
}
