package com.finvendor.common.util;

import com.finvendor.common.constant.AppConstants;

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
    //User Registration date format 2017-10-09 11:23:33
    private static final SimpleDateFormat registrationDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final DateFormat dd_MMM_yyyy_hh_mmformatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm");
    private static final DateFormat dd_MMM_yyyy_hh_mm_subscription_formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
    public static final DateFormat dd_MMM_yyyy_formatter = new SimpleDateFormat("dd-MMM-yyyy");
    public static final DateFormat dd_MMM_yyyy_formatter1 = new SimpleDateFormat("dd/MMM/yy HH:mm:ss");
    private static final SimpleDateFormat FV_DATE_FORMATTER = new SimpleDateFormat(AppConstants.FV_PRICE_DATE_FORMAT);

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
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static String convertStringToTimestamp(DateFormat formatter, String str_date) throws ParseException {
        Date date = (Date) formatter.parse(str_date);
        return String.valueOf(date.getTime());
    }

    public static long convertFvPriceDateToTimestamp(String str_date) throws ParseException {
        DateFormat formatter;
        formatter = new SimpleDateFormat(AppConstants.FV_PRICE_DATE_FORMAT);
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
        return new SimpleDateFormat("MMM").format(d).toUpperCase();
    }

    public static String getYear() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);

        return String.valueOf(year);
    }

    /**
     * @param dateStr format must be like  Mar_19
     */
    public static String getTimeStamp_MMM_yy(String dateStr) throws ParseException {
        String timestamp;
        Date date = null;
        date = simpleDateFormat_MMM_yy.parse(dateStr);
        timestamp = String.valueOf(date.getTime());
        return timestamp;
    }

    public static String get_Date_To_DD_MMM_YYYY_hh_Format() {
        return FV_DATE_FORMATTER.format(Calendar.getInstance().getTime());
    }

    public static String get_Date_To_DD_MMM_YYYY_hh_Format(String timeStamp) {
        return dd_MMM_yyyy_hh_mmformatter.format(new Date(Long.parseLong(timeStamp)));
    }

    public static String get_Date_To_DD_MMM_YYYY_hh_Format(Long timeStamp) {
        return dd_MMM_yyyy_hh_mmformatter.format(new Date(timeStamp));
    }

    public static long get_Timestamp_From_DD_MMM_YYYY_hh_Format(String date) throws ParseException {
        return dd_MMM_yyyy_hh_mmformatter.parse(date).getTime();
    }

    public static String getCurrentDateHaveMonthDigit() {
        return new SimpleDateFormat(AppConstants.FV_PRICE_DATE_ONLY_FORMAT).format(Calendar.getInstance().getTime());
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
        String startDateTimeInHumanDate = dd_MMM_yyyy_hh_mmformatter.format(startTimeInMillis);
        Date currentDate = new Date();

        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);

        c.add(Calendar.DATE, numberOfDays);

        // convert calendar to date
        String endDateTimeInHumanDate = dd_MMM_yyyy_hh_mmformatter.format(c.getTime());

        return new Pair<>(startDateTimeInHumanDate, endDateTimeInHumanDate);
    }

    public static Date getCurrentDateInDate() {
        return Calendar.getInstance().getTime();
    }

    public static Date addDaysInCurrentDate(Date currentDate, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, days);
        return c.getTime();
    }

    /**
     * ConvertRegistration Date into Timestamp
     *
     * @param registrationDate Registration date format in db: 2017-10-09 11:23:33
     */
    public static long convertRegistrationDateFormatToTimestamp(String registrationDate) {
        try {
            return registrationDateFormat.parse(registrationDate).getTime();
        } catch (Exception e) {
            return 0L;
        }
    }

    public static long convertSubscriptionDateToMillis(String subscriptionDate) {
        try {
            return dd_MMM_yyyy_hh_mm_subscription_formatter.parse(subscriptionDate).getTime();
        } catch (Exception e) {
            return 0L;
        }
    }

    public static void main(String[] args) {
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
        //        Pair<Long, Long> ms = getSubscriptionStartAndEndDateInMillis(30);
        //        System.out.println("Start time: " + ms.getElement1());
        //        System.out.println("End time: " + ms.getElement2());
        //        getSubscriptionStartAndEndDateInHumanDate(30);

        //        long difference = getDateDifferenceInDays(1569421242000L, 1570717617263L);
        //        System.out.println(difference);

        //        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //        Date currentDate = getCurrentDateInDate();
        //        System.out.println(dateFormat.format(currentDate));
        //
        //        // convert date to calendar
        //        Calendar c = Calendar.getInstance();
        //        c.setTime(currentDate);
        //
        //        c.add(Calendar.DATE, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
        //        // convert calendar to date
        //        Date currentDatePlusOne = c.getTime();
        //
        //        System.out.println(dateFormat.format(currentDatePlusOne));

        System.out.println(convertRegistrationDateFormatToTimestamp("2017-10-09 11:23:33"));
    }

    public static long getDateDifferenceInDays(long start, long end) {
        return (end - start) / 86400000;
    }

}
