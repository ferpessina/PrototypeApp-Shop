package fer.shop.helpers;

import java.util.Calendar;
import java.util.Date;

public class DateHelper {
	public static String dateToString(Date date){
		return null;
	}
	
	public static Date stringToDate(){
		Date date = new Date();
		System.out.println(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -40);
		date = cal.getTime();
		System.out.println(date);
		return null;
	}
}


