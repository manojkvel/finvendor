package com.finvendor.common.util;

import com.finvendor.common.constant.AppConstant;
import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
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
	   // int year = cal.get(Calendar.YEAR);
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
	
	public static long convertStringToTimestamp(String str_date) throws ParseException {
		DateFormat formatter;
		formatter = new SimpleDateFormat("MM/dd/yy");
		Date date = (Date) formatter.parse(str_date);
		return date.getTime();
	}

	public static long convertFvPriceDateToTimestamp(String str_date) throws ParseException {
		DateFormat formatter;
		formatter = new SimpleDateFormat(AppConstant.FV_PRICE_DATE_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
		Date date = formatter.parse(str_date);

		Calendar reserchDateCalendar = Calendar.getInstance();
		reserchDateCalendar.setTime(date);
		reserchDateCalendar.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));

		return reserchDateCalendar.getTimeInMillis();
	}

	public static boolean isDateValid(String dateToValidate, String dateFromat){

		if(dateToValidate == null){
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);

		try {
			//if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);

		} catch (ParseException e) {
			return false;
		}

		return true;
	}

	public static String getDayNumber(){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		String dayString = "";
		if (String.valueOf(day).length() == 1) {
			dayString = "0" + day;
		} else {
			dayString = String.valueOf(day);
		}
		return dayString;
	}

	public static String getThreeLetterMonthName(){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		cal.set(year, month, day);
		java.util.Date d = new java.util.Date(cal.getTimeInMillis());
		String mmm = new SimpleDateFormat("MMM").format(d).toUpperCase();

		return mmm;
	}

	public static String getYear(){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);

		return String.valueOf(year);
	}



	public static void main(String args[]) throws ParseException {
//		boolean thisDateValid = isDateValid("04/11/2018", "dd/MM/yyyy");
//		System.out.println(thisDateValid);
//
//		thisDateValid = isDateValid("12/23/2011", "dd/MM/yyyy");
//		System.out.println(thisDateValid);

//		String americaTime="14/Aug/18 08:16:47";
//		DateFormat formatter;
//		formatter = new SimpleDateFormat(AppConstant.FV_PRICE_DATE_FORMAT);
//		formatter.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
//		Date date = formatter.parse(americaTime);
//
//		Calendar reserchDateCalendar = Calendar.getInstance();
//		reserchDateCalendar.setTime(date);
//		reserchDateCalendar.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
//		System.out.println(reserchDateCalendar.getTime());

		String bhavUrl="https://www.nseindia.com/content/historical/EQUITIES/$YEAR/$MON/cm$DAY$MON$YEARbhav.csv.zip";
		String dayNumber = getDayNumber();
		String threeLetterMonthName = getThreeLetterMonthName();
		String year = getYear();
		bhavUrl=StringUtils.replace(bhavUrl, "$DAY", dayNumber);
		bhavUrl=StringUtils.replace(bhavUrl, "$MON", threeLetterMonthName);
		bhavUrl=StringUtils.replace(bhavUrl, "$YEAR", year);
		System.out.println(bhavUrl);

		System.out.println(StringUtils.replace(StringUtils.substring(bhavUrl,bhavUrl.lastIndexOf("/")+1),".zip",""));

	}

}
