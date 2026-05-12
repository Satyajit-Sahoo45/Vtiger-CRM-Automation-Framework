package dataProviders;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.ExcelFileUtil;

/**
 * @author Satyajit Sahoo
 * 
 * This is a DataProvider Class, which will share the contact information to the test cases
 */
public class ContactDataProvider {
	
	/**
	 * 
	 * @param method
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 * 
	 * This method is used to fetch the data from the Excel file
	 */
	@DataProvider(name = "contactDataProvider")
	public Object[][] getData(Method method) throws EncryptedDocumentException, IOException {
		
		 String testCaseDesc = method.getAnnotation(Test.class).description();
		
		 Map<String, String> dataMap = ExcelFileUtil.readData("Contact Data", testCaseDesc);	

	     Object[][] data = new Object[1][1];
	     data[0][0] = dataMap;

	     return data;
	}
}
