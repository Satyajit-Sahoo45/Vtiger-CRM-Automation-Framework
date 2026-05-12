package base;

import java.io.IOException;
import java.sql.SQLException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import pomPages.HomePomPage;
import pomPages.LoginPomPage;
import utils.DatabaseUtility;
import utils.PropertyFileUtil;
import utils.WebDriverUtilities;

public class BaseClass {
	
	public WebDriver driver;
	PropertyFileUtil pfu = new PropertyFileUtil();
	DatabaseUtility dbutil;
	WebDriverUtilities wutil = new WebDriverUtilities();
	
	@BeforeSuite
	public void connectDB() throws SQLException {
		dbutil = new DatabaseUtility();
		dbutil.getConnectionWithDB();
		Reporter.log("Connected with DB", true);
	}
	
	@BeforeTest
	public void configParallelExe() {
		Reporter.log("Configured the parallel Exe", true);
	}
	
	@BeforeClass(alwaysRun = true)
//	@Parameters("browser")
	public void setUp() throws IOException {
		String browserName = pfu.getPropertyValue("browser");
		Reporter.log("Launching the browser");
		
		if(browserName.equals("chrome")) {
			driver = new ChromeDriver();
		} else if(browserName.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public void Login() throws NumberFormatException, IOException {		
		
		String timeouts = pfu.getPropertyValue("timeouts");
		String username = pfu.getPropertyValue("username");
		String password = pfu.getPropertyValue("password");
		String url = pfu.getPropertyValue("url");
		
//		maximize the window
		wutil.Maximize_Browser(driver);
		wutil.WaitForElement_implictly(driver, timeouts);
		wutil.NavigateToApplication(driver, url);
		
		LoginPomPage login = new LoginPomPage(driver);
		login.Login(username, password);
	}
	
	@AfterMethod(alwaysRun = true)
	public void Logout() {
		HomePomPage hp = new HomePomPage(driver);
		hp.logout(driver);
	}
	
	@AfterClass(alwaysRun = true)
	public void quitBrowser() {
		if(driver != null) {
			driver.quit();
		}
	}
	
	@AfterSuite
	public void disconnectDB() throws SQLException {
		dbutil.disconnectWithDB();
	}
	
	public WebDriver getDriver() {
	    return driver;
	}
	
}
