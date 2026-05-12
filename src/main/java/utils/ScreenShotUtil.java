package utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import java.util.Date;

public class ScreenShotUtil {
	
	public static String captureScreenShot(WebDriver driver, String testName) {
		
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		
		TakesScreenshot ss = (TakesScreenshot) driver;
		File src = ss.getScreenshotAs(OutputType.FILE);
		
		String path = "./screenshots/" + testName + time + ".png";
		
		File dest = new File(path);
		
		try {
			FileHandler.copy(src, dest);
			return System.getProperty("user.dir")+path;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
}
