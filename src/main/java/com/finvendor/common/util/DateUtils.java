package com.finvendor.common.util;

import com.finvendor.common.constant.AppConstant;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author ayush on May 13, 2018
 */
public class DateUtils {

    //for Mar_18
    private static final SimpleDateFormat simpleDateFormat_MMM_yy = new SimpleDateFormat("MMM_yy");
    public static final DateFormat dd_MMM_yyyy_hh_mmformatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm");
    public static final DateFormat dd_MMM_yyyy_hh_mm_subscription_formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
    public static final DateFormat dd_MMM_yyyy_formatter = new SimpleDateFormat("dd-MMM-yyyy");
    public static final DateFormat dd_MMM_yyyy_formatter1 = new SimpleDateFormat("dd/MMM/yy HH:mm:ss");
    public static final SimpleDateFormat FV_DATE_FORMATTER = new SimpleDateFormat(AppConstant.FV_PRICE_DATE_FORMAT);

    public static String getCurrentYear() {
        return String.valueOf(Calendar.getInstance(TimeZone.getDefault()).get(Calendar.YEAR));
    }

    public static String get2DigitCurrentYear() {
        DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
        return df.format(Calendar.getInstance().getTime());
    }

    public static String getCurrentMonth() {
        return new SimpleDateFormat("MMM").format(new java.util.Date(Calendar.getInstance().getTimeInMillis()))
                .toUpperCase();
    }

    public static String getCurrentMonthDigit() {
        int monthNumber = Calendar.getInstance().get(Calendar.MONTH);
        String monthDigit = String.valueOf(monthNumber + 1);
        if (monthDigit.length() == 1) {
            monthDigit = "0" + monthDigit;
        }
        return monthDigit;
    }

    public static String getCurrentDayDigit() {
        int monthNumber = Calendar.getInstance().get(Calendar.DATE);
        String monthDigit = String.valueOf(monthNumber + 1);
        if (monthDigit.length() == 1) {
            monthDigit = "0" + monthDigit;
        }
        return monthDigit;
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

    public static String convertStringToTimestamp(DateFormat formatter, String str_date) throws ParseException {
        Date date = (Date) formatter.parse(str_date);
        return String.valueOf(date.getTime());
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

    public static boolean isDateValid(String dateToValidate, String dateFromat) {

        if (dateToValidate == null) {
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

    public static String getDayNumber() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String dayString = "";
        if (String.valueOf(day).length() == 1) {
            dayString = "0" + day;
        }
        else {
            dayString = String.valueOf(day);
        }
        return dayString;
    }

    public static String getThreeLetterMonthName() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        cal.set(year, month, day);
        java.util.Date d = new java.util.Date(cal.getTimeInMillis());
        String mmm = new SimpleDateFormat("MMM").format(d).toUpperCase();

        return mmm;
    }

    public static String getYear() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);

        return String.valueOf(year);
    }

    /**
     * @param dateStr format must be like  Mar_19
     * @return
     */
    public static String getTimeStamp_MMM_yy(String dateStr) {
        String timestamp;
        Date date = null;
        try {
            date = simpleDateFormat_MMM_yy.parse(dateStr);
        } catch (ParseException e) {
            timestamp = "0";
        }
        timestamp = String.valueOf(date.getTime());
        return timestamp;
    }

    public static String getCurrentDate() {
        String currentDate = FV_DATE_FORMATTER.format(Calendar.getInstance().getTime());
        return currentDate;
    }

    public static String getCurrentDate(String timeStamp) {
        String currentDate = dd_MMM_yyyy_hh_mmformatter.format(new Date(Long.parseLong(timeStamp)));
        return currentDate;
    }

    public static String getCurrentDateHaveMonthDigit() {
        String currentDate = new SimpleDateFormat(AppConstant.FV_PRICE_DATE_ONLY_FORMAT).format(Calendar.getInstance().getTime());
        return currentDate;
    }

    public static Pair<Long, Long> getSubscriptionStartAndEndDateInMillis(int numberOfDays) {
        long startTimeInMillis = Calendar.getInstance().getTimeInMillis();
        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, numberOfDays);
        long endTimeInMillis = c.getTimeInMillis();
        return new Pair<>(startTimeInMillis, endTimeInMillis);
    }

    public static Pair<String, String> getSubscriptionStartAndEndDateInHumanDate(int numberOfDays) {

        long startTimeInMillis = Calendar.getInstance().getTimeInMillis();
        String startDateTimeInHumanDate = dd_MMM_yyyy_hh_mm_subscription_formatter.format(startTimeInMillis);
        Date currentDate = new Date();

        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);

        c.add(Calendar.DATE, numberOfDays);

        // convert calendar to date
        String endDateTimeInHumanDate = dd_MMM_yyyy_hh_mm_subscription_formatter.format(c.getTime());

        return new Pair<>(startDateTimeInHumanDate, endDateTimeInHumanDate);
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

        //		String bhavUrl="https://www.nseindia.com/content/historical/EQUITIES/$YEAR/$MON/cm$DAY$MON$YEARbhav.csv.zip";
        //		String dayNumber = getDayNumber();
        //		String threeLetterMonthName = getThreeLetterMonthName();
        //		String year = getYear();
        //		bhavUrl=StringUtils.replace(bhavUrl, "$DAY", dayNumber);
        //		bhavUrl=StringUtils.replace(bhavUrl, "$MON", threeLetterMonthName);
        //		bhavUrl=StringUtils.replace(bhavUrl, "$YEAR", year);
        //		System.out.println(bhavUrl);
        //
        //		System.out.println(StringUtils.replace(StringUtils.substring(bhavUrl,bhavUrl.lastIndexOf("/")+1),".zip",""));

        //		String currentDay = getCurrentDay();
        //		String currentMonth = getCurrentMonthDigit();
        //		String currentYear = getCurrentYear();
        //		System.out.println(currentDay+currentMonth+currentYear);

        //        System.out.println(getCurrentDateHaveMonthDigit());
        //        System.out.println(DateUtils.convertStringToTimestamp(DateUtils.dd_MMM_yyyy_formatter1, "20/Mar/19 08:00:04"));
        Pair<Long, Long> ms = getSubscriptionStartAndEndDateInMillis(30);
        System.out.println("Start time: " + ms.getElement1());
        System.out.println("End time: " + ms.getElement2());
        //        getSubscriptionStartAndEndDateInHumanDate(30);
    }

}
