package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


/**
 * @author Satyajit Sahoo
 * This class is used to fetch the data from Excel File
 */

public class ExcelFileUtil {
	
	/**
	 * 
	 * @param sheetName
	 * @param tastCaseType
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 * 
	 * This Method is used to read/fetch the data from the Excel File
	 */	
	public static Map<String, String> readData(String sheetName, String tastCaseType) throws EncryptedDocumentException, IOException {
		
		Map<String, String> dataMap = new HashMap<>();
		
//		convert physical file to java object
		FileInputStream fis = new FileInputStream("./src/test/resources/vtiger-excel-data.xlsx");
		
//		Generate a WorkBook
		Workbook wb = WorkbookFactory.create(fis);
		
//		Fetch the sheet
		Sheet sh = wb.getSheet(sheetName);
		
		// get the last row number        
        int rowLen = sh.getLastRowNum();
        
//      iterate over the current sheet
        for (int i = 0; i <= rowLen; i++) {
            Row row = sh.getRow(i);
            
            if(row != null) {
	            	int cellLen = row.getLastCellNum();
	            	
	            	for(int c=0; c<cellLen; c++) {
	            		
	            		if(row.getCell(2).toString().equals(tastCaseType)) {
	            			if(row.getCell(c) != null) {
//	            			System.out.println(row.getCell(c) + " ");	            			
	            				
	            				if(i == 1 || i == 4 || i == 7) {
	            					String key = sh.getRow(i-1).getCell(c).toString();
	            					String value = row.getCell(c).toString();
	            					
//	            					System.out.print(key + " --- " + value);
	            					dataMap.putIfAbsent(key, value);
	            				}
	            			}	            			
	            		} else {
	            			continue;
	            		}
//	            		System.out.println();
	            	}
            }

        }
        wb.close();
        
        return dataMap;
	}


	/**
	 * 
	 * @param SheetName
	 * @param row_index
	 * @param col_index
	 * @param value
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 * 
	 * This method is used to write back or update the data to the excel file
	 */
	public static void writeData(String SheetName, int row_index, int col_index, String value) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/vtiger-excel-data.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		
		wb.getSheet(SheetName).getRow(row_index).createCell(col_index).setCellValue(value);
		
		FileOutputStream fos = new FileOutputStream("./src/test/resources/vtiger-excel-data.xlsx");
		wb.write(fos);
		
		wb.close();
		
	}
}
