package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @author Satyajit Sahoo
 * This is a reusable class, it returns a random number, Today's date and date after given period
 */
public class JavaUtility {
	
	/**
	 * This is a reusable method to return the random integer value 
	 *  
	 * @return
	 */
	public int fetchRandomInteger() {
		Random r = new Random();
		return r.nextInt(1, 1000);
	}
	
	public String fetchCurrentDate() {
		Date dateObj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		return sim.format(dateObj);
	}
	
	public String fetchDateAfterGivenDays(int days) {
		Date dateObj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = sim.format(dateObj);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String date = sim.format(cal.getTime());
		return date;
	}
}
