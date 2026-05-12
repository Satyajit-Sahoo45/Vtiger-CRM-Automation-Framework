package utils;

import com.aventstack.extentreports.ExtentTest;

public class UtilityObjectClass {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public static ExtentTest getTest() {
		return test.get();
	}

	public static void setTest(ExtentTest acttest) {
		test.set(acttest);
	}
	
	
}
