package com.finvendor.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author ayush on May 13, 2018
 */
public class DateUtil {
	public static Date getPreviousWorkingDay(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);

	    int dayOfWeek;
	    do {
	        cal.add(Calendar.DAY_OF_MONTH, -1);
	        dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	    } while (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY || isHoliday(cal));

	    return cal.getTime();
	}
	
	public static boolean isHoliday(Calendar cal) {
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH) + 1;
	    int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

	    //TBD need to calculate No of working days in Current Month
	    if (month == 12 && dayOfMonth == 25) {
	        return true;
	    }

	    // more checks

	    return false;
	}
	
	public static String getCurrentYear() {
		return String.valueOf(Calendar.getInstance(TimeZone.getDefault()).get(Calendar.YEAR));
	}

	public static String getCurrentMonth() {
		return new SimpleDateFormat("MMM").format(new java.util.Date(Calendar.getInstance().getTimeInMillis()))
				.toUpperCase();
	}

	public static String getCurrentDay() {
		return String.valueOf(Calendar.getInstance(TimeZone.getDefault()).get(Calendar.DATE) - 1);
	}
	
	public static int getPreviousDayOfMonthAsInteger(Date prevWorkingDate) {
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(prevWorkingDate);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		return dayOfMonth;
	}
	
	public static void main(String[] args) {
		int dayOfMonth = getPreviousDayOfMonthAsInteger(getPreviousWorkingDay(Calendar.getInstance().getTime()));
		System.out.println(dayOfMonth); 
	}

}
