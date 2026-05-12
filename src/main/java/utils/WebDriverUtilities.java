package utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtilities {
	
	// Maximize The Browser
		public void Maximize_Browser(WebDriver driver) {
			driver.manage().window().maximize();
		}

		// Minimize The Browser
		public void Minimize_Browser(WebDriver driver) {
			driver.manage().window().minimize();
		}

		// full Screen On Browser
		public void full_Screen(WebDriver driver) {
			driver.manage().window().fullscreen();
		}

		// Setposition To The Browser
		public void setpositionOfBrowser(WebDriver driver, int x, int y) {
			driver.manage().window().setPosition(new Point(x, y));
		}

		// SetSize Of The Browser
		public void setsizeofBrowser(WebDriver driver, int width, int height) {
			driver.manage().window().setSize(new Dimension(width, height));
		}

		// getposition Of The Browser
		public Point getpositionOfBrowser(WebDriver driver) {
			Point siz = driver.manage().window().getPosition();
			return siz;
		}

		public Dimension getSizeOfBrowser(WebDriver driver) {
			Dimension dim = driver.manage().window().getSize();
			return dim;
		}

		// implicitly Wait
		public void WaitForElement_implictly(WebDriver driver, String time) {
			long t = Long.parseLong(time);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(t));

		}

		// WaitUntilElementIsVisible using explictly Wait
		public void WaitUntilElementIsVisible(WebDriver driver, String time, WebElement ele) {
			long t = Long.parseLong(time);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(t));
			wait.until(ExpectedConditions.visibilityOf(ele));
		}

		// WaitUntilElementIsClickable using explictly Wait
		public void WaitUntilElementToBeClickable(WebDriver driver, String time, WebElement ele) {
			long t = Long.parseLong(time);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(t));
			wait.until(ExpectedConditions.elementToBeClickable(ele));
		}

		// WaitUntilTitleVisible using explictly Wait
		public void WaitUntilTitleIsVisible(WebDriver driver, String time, String title) {
			long t = Long.parseLong(time);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(t));
			wait.until(ExpectedConditions.titleContains(title));
		}

		// Navigate to the application using url
		public void NavigateToApplication(WebDriver driver, String url) {
			driver.get(url);

		}

		// Navigate To The Application Via String Url
		public void NavigateToApplicationStringURL(WebDriver driver, String url) {
			driver.navigate().to(url);

		}

		// Navigate to the application using tourl
		public void NavigateToApplicationURL(WebDriver driver, String url) throws MalformedURLException {
			driver.navigate().to(new URL(url));

		}

		// Reload The Page
		public void Reloadthepage(WebDriver driver) {
			driver.navigate().refresh();

		}

		// Navigate To The Priveous Page
		public void NavigatetoPrivouspage(WebDriver driver) {
			driver.navigate().back();
			;

		}

		// Navigate To Nextpage
		public void NavigatetoNextpage(WebDriver driver) {
			driver.navigate().forward();
			;

		}

		// Identify Current Page URL
		public String currentpageurl(WebDriver driver) {
			String currenturl = driver.getCurrentUrl();
			return currenturl;
		}

		// Identify Current Page Title
		public String currentpagetitle(WebDriver driver) {
			String currentTitle = driver.getTitle();
			return currentTitle;
		}

		// Get Page Source
		public String getpagesource(WebDriver driver) {
			String data = driver.getPageSource();
			return data;
		}

		// Get Parent window id
		public String Fetchwindowid(WebDriver driver) {
			String wid = driver.getWindowHandle();
			return wid;
		}

		// Get All window id
		public Set<String> FetchAllwindowid(WebDriver driver) {
			Set<String> allwid = driver.getWindowHandles();
			return allwid;
		}

		// Switch Parent Window To Child And Validate URL
		public void SwitchChildwindow_URL(WebDriver driver, String expurl) {
			Set<String> allwid = driver.getWindowHandles();
			for (String s : allwid) {
				driver.switchTo().window(s);
				if (driver.getCurrentUrl().contains(expurl)) {
					break;
				}
			}
		}

		// Switch Parent Window To Child And Validate Title
		public void SwitchChildwindow_Title(WebDriver driver, String exptitle) {
			Set<String> allwid = driver.getWindowHandles();
			for (String s : allwid) {
				driver.switchTo().window(s);
				if (driver.getTitle().contains(exptitle)) {
					break;
				}
			}
		}

		// Switch Back To Parent Window
		public void SwitchToParentWindow(WebDriver driver, String id) {
			driver.switchTo().window(id);
		}

		// Select Dropdown By Index
		public void SelectDDbyIndex(WebElement dropdown, int index) {
			Select sobj = new Select(dropdown);
			sobj.selectByIndex(index);
		}

		// Select Dropdown By value
		public void SelectDDbyValue(WebElement dropdown, String value) {
			Select sobj = new Select(dropdown);
			sobj.selectByValue(value);
		}

		// Select Dropdown By visible Text
		public void SelectDDbyVisibletext(WebElement dropdown, String text) {
			Select sobj = new Select(dropdown);
			sobj.selectByVisibleText(text);
		}

		// Select Dropdown By contains visible Text
		public void SelectDDbyContainsVisibletext(WebElement dropdown, String text) {
			Select sobj = new Select(dropdown);
			sobj.selectByContainsVisibleText(text);
		}

		// deselect Dropdown By index
		public void DeselectDDbyIndex(WebElement dropdown, int index) {
			Select sobj = new Select(dropdown);
			sobj.deselectByIndex(index);
		}

		// deselect Dropdown By value
		public void DeselectDDbyvalue(WebElement dropdown, String value) {
			Select sobj = new Select(dropdown);
			sobj.deselectByValue(value);
		}

		// deselect Dropdown By visible text
		public void DeselectDDbyvisible(WebElement dropdown, String text) {
			Select sobj = new Select(dropdown);
			sobj.deselectByVisibleText(text);
		}

		// deselect Dropdown By Contains visible text
		public void DeselectDDbycontainsvisible(WebElement dropdown, String text) {
			Select sobj = new Select(dropdown);
			sobj.deSelectByContainsVisibleText(text);
		}

		// deselect All
		public void DeselectDDbyAll(WebElement dropdown) {
			Select sobj = new Select(dropdown);
			sobj.deselectAll();
		}

		// Get All Text From Dropdown
		public String FetchAllTextFrommDD(WebElement dropdown) {
			Select sobj = new Select(dropdown);
			for (WebElement s : sobj.getOptions()) {
				String data = s.getText();
				return data;
			}
			return null;
		}

		// Get First Text From Dropdown
		public String FetchFirstTextFrommDD(WebElement dropdown) {
			Select sobj = new Select(dropdown);
			String data = sobj.getFirstSelectedOption().getText();
			return data;
		}

		// Get First Text From Dropdown
		public List<WebElement> FetchAllSelectedTextFrommDD(WebElement dropdown) {
			Select sobj = new Select(dropdown);
			List<WebElement> data = sobj.getAllSelectedOptions();
			return data;
		}

		// Move Cursor to element Using mouse
		public void MouserOverToElement(WebDriver driver, WebElement ele) {
			Actions act = new Actions(driver);
			act.moveToElement(ele).perform();
		}

		// Click On element Using Mouse
		public void ClickOnElement(WebDriver driver, WebElement ele) {
			Actions act = new Actions(driver);
			act.click(ele).perform();
		}

		// Drag the Element Target To Target_Location
		public void DragandDrop(WebDriver driver, WebElement tarele, WebElement tarloc) {
			Actions act = new Actions(driver);
			act.dragAndDrop(tarele, tarloc).perform();
		}

		// Click Ok button in Alert popup
		public void handleAlertClickonOK(WebDriver driver) {
			driver.switchTo().alert().accept();
		}

		// Click Cancel button in Alert popup
		public void handleAlertClickonCancel(WebDriver driver) {
			driver.switchTo().alert().dismiss();
		}

		// Get Text From Alert popup
		public String handleAlertFetchTheText(WebDriver driver) {
			String text = driver.switchTo().alert().getText();
			return text;
		}

		//Pass The data to text field On Alert Popup
		public void handleAlertEnterText(WebDriver driver, String text) {
			driver.switchTo().alert().sendKeys(text);
		}
		
		//Close The Browser
		public void QuitTheBrowser(WebDriver driver) {
			driver.quit();
		}
}
