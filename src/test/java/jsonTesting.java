import java.io.IOException;

import org.json.simple.parser.ParseException;

import utils.JSONFileUtility;

public class jsonTesting {
	public static void main(String[] args) throws IOException, ParseException {
		JSONFileUtility jsu = new JSONFileUtility();
		System.out.println(jsu.getAttributeValue("name"));
		System.out.println(jsu.getAttributeValue("from"));
		System.out.println(jsu.getAttributeValue("phno"));
	}
}
