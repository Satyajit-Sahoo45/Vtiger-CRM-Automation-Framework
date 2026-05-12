import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;

import utils.ExcelFileUtil;

public class exceltesting {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		ExcelFileUtil.readData("Create Org", "Create Org");
	}
}
