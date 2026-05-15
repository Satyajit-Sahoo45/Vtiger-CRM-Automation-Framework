package base;

import java.io.IOException;
import java.sql.SQLException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
	
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();
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
	@Parameters("browser")
	public void setUp(String browserName) throws IOException {
//		String browserName = pfu.getPropertyValue("browser");
		Reporter.log("Launching the browser");
		
		if(browserName.equals("chrome")) {
			tdriver.set(new ChromeDriver());
		} else if(browserName.equals("edge")) {
			tdriver.set(new EdgeDriver());
		}else if(browserName.equals("firefox")) {
			tdriver.set(new FirefoxDriver());
		} else {
			tdriver.set(new ChromeDriver());
		}
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public void Login() throws NumberFormatException, IOException {		
		
		String timeouts = pfu.getPropertyValue("timeouts");
		String username = pfu.getPropertyValue("username");
		String password = pfu.getPropertyValue("password");
		String url = pfu.getPropertyValue("url");
		
//		maximize the window
		wutil.Maximize_Browser(getDriver());
		wutil.WaitForElement_implictly(getDriver(), timeouts);
		wutil.NavigateToApplication(getDriver(), url);
		
		LoginPomPage login = new LoginPomPage(getDriver());
		login.Login(username, password);
	}
	
	@AfterMethod(alwaysRun = true)
	public void Logout() {
		HomePomPage hp = new HomePomPage(getDriver());
		hp.logout(getDriver());
	}
	
	@AfterClass(alwaysRun = true)
	public void quitBrowser() {
		if(getDriver() != null) {
			getDriver().quit();
			tdriver.remove();
		}
	}
	
	@AfterSuite
	public void disconnectDB() throws SQLException {
		dbutil.disconnectWithDB();
	}
	
	public WebDriver getDriver() {
	    return tdriver.get();
	}
	
}
