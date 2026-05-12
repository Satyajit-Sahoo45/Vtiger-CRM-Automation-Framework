package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONFileUtility {
	
	private JSONObject js = null;
	
	/**
	 * This method is used to fetch data from JSON file.
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	private JSONObject readData() throws FileNotFoundException, IOException, ParseException {
		
		if(js != null) {
			return js;
		}
		
//		create JSONParser object
		JSONParser jp = new JSONParser();
		
//		convert physical file to Java object
		Object obj = jp.parse(new FileReader("./src/test/resources/data.json"));
		
//		convert the parsed object to JSONObject
		js = (JSONObject) obj;
		
		return js;
	}
	
	/**
	 * This is the calling method to get a specific attribute value from the JSON file
	 * 
	 * @param attributeName
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public String getAttributeValue(String attributeName) throws IOException, ParseException {
		return readData().get(attributeName).toString();
	}
}
