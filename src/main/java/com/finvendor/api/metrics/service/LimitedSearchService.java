package com.finvendor.api.metrics.service;

import com.finvendor.api.metrics.dto.FeatureAllowedDto;
import com.finvendor.api.metrics.enums.FeatureAccessEnum;
import com.finvendor.api.metrics.enums.FvFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ayush
 */
@Service
public class LimitedSearchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LimitedSearchService.class.getName());
    private static Map<Integer, Integer> equityResearchReportWeeklyLimitMap;
    private static Map<Integer, Integer> doItYourSelfWeeklyLimitMap;

    static {
        equityResearchReportWeeklyLimitMap = new LinkedHashMap<>();
        equityResearchReportWeeklyLimitMap.put(1, 3);
        equityResearchReportWeeklyLimitMap.put(2, 3);
        equityResearchReportWeeklyLimitMap.put(3, 3);
        equityResearchReportWeeklyLimitMap.put(4, 3);
        equityResearchReportWeeklyLimitMap.put(5, 3);
        equityResearchReportWeeklyLimitMap.put(6, 3);
        equityResearchReportWeeklyLimitMap.put(7, 3);

        doItYourSelfWeeklyLimitMap = new LinkedHashMap<>();
        doItYourSelfWeeklyLimitMap.put(1, 3);
        doItYourSelfWeeklyLimitMap.put(2, 3);
        doItYourSelfWeeklyLimitMap.put(3, 3);
        doItYourSelfWeeklyLimitMap.put(4, 3);
        doItYourSelfWeeklyLimitMap.put(5, 3);
        doItYourSelfWeeklyLimitMap.put(6, 3);
        doItYourSelfWeeklyLimitMap.put(7, 3);
    }

    public FeatureAllowedDto isAllowedForSearch(FvFeature fvFeature, int dayNumOfWeek) {
        LOGGER.info("Service isAllowedForSearch - START weekDayNum: {}", dayNumOfWeek);
        FeatureAllowedDto featureAllowedDto = new FeatureAllowedDto();
        if (dayNumOfWeek == 5 || dayNumOfWeek == 6 || dayNumOfWeek % 7 == 0) {
            equityResearchReportWeeklyLimitMap.put(1, 3);
            equityResearchReportWeeklyLimitMap.put(2, 3);
            equityResearchReportWeeklyLimitMap.put(3, 3);
            equityResearchReportWeeklyLimitMap.put(4, 3);
            equityResearchReportWeeklyLimitMap.put(5, 3);
            equityResearchReportWeeklyLimitMap.put(6, 3);
            equityResearchReportWeeklyLimitMap.put(7, 3);
            featureAllowedDto.setFeatureAccess(FeatureAccessEnum.NOT_ALLOWED);
            featureAllowedDto.setMessage("Weekly limit is over, please go for monthly subscriptions.");
        }
        else {
            Map<Integer, Integer> featureWeeklyLimitMap = getFeatureWeeklyLimitMap(fvFeature);
            int creditLimit = featureWeeklyLimitMap.get(dayNumOfWeek);
            if (creditLimit == 0) {
                featureAllowedDto.setFeatureAccess(FeatureAccessEnum.NOT_ALLOWED);
                featureAllowedDto.setMessage("Daily limit is over, please go for monthly subscriptions.");
            }
            else {
                featureAllowedDto.setFeatureAccess(FeatureAccessEnum.ALLOWED);
                featureAllowedDto.setMessage("You are allowed to access Stock|Sector Screener upto 3 times in a day!");
                featureWeeklyLimitMap.put(dayNumOfWeek, --creditLimit);
            }
        }
        LOGGER.info("Service isAllowedForSearch - END");
        return featureAllowedDto;
    }

    /**
     * Find day number of week, Sunday=1, Monday=2 ...
     */
    public int findDayNumOfWeek(String currentDate) throws ParseException {
        if (currentDate == null) {
            currentDate = findCurrentDate();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(new SimpleDateFormat("yyyy/MM/dd").parse(currentDate));
        return c.get(Calendar.DAY_OF_WEEK);
    }

    private Map<Integer, Integer> getFeatureWeeklyLimitMap(FvFeature fnfla) {
        Map<Integer, Integer> resultMap = null;
        switch (fnfla) {
        case EQUITY_RESEARCH_REPORT:
            resultMap = equityResearchReportWeeklyLimitMap;
            break;
        case DO_IT_YOUR_SELF:
            resultMap = doItYourSelfWeeklyLimitMap;
            break;
        }
        return resultMap;
    }

    /**
     * Find current date in format yyyy/MM/dd
     */
    private String findCurrentDate() {
        String pattern = "yyyy/MM/dd";//"yyyy/MM/dd HH:mm:ss"
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(Calendar.getInstance().getTime());
    }

    //    public static void main(String[] args) {
    //        String pattern = "yyyy/MM/dd HH:mm:ss";
    //        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    //
    //        String date = simpleDateFormat.format(Calendar.getInstance().getTime());
    //        System.out.println("Current Date with time: " + date);
    //
    //        //Registration Date: 2016-10-20 18:15:06
    //        //        String dateStart = "01/14/2012 09:29:58";
    //        //        String dateStop = "01/15/2012 09:29:57";
    //        String dateStart = "2019/09/09 09:29:58";
    //        String dateStop = "2019/10/06 09:29:57";
    //
    //        //HH converts hour in 24 hours format (0-23), day calculation
    //        //        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    //        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    //
    //        Date d1 = null;
    //        Date d2 = null;
    //
    //        try {
    //            d1 = format.parse(dateStart);
    //            d2 = format.parse(dateStop);
    //
    //            //in milliseconds
    //            long diff = d2.getTime() - d1.getTime();
    //
    //            long diffSeconds = diff / 1000 % 60;
    //            long diffMinutes = diff / (60 * 1000) % 60;
    //            long diffHours = diff / (60 * 60 * 1000) % 24;
    //            long diffDays = diff / (24 * 60 * 60 * 1000);
    //            ++diffDays;
    //            long d;
    //            if (diffDays >= 7) {
    //                d = diffDays % 7;
    //                d++;
    //            }
    //            else {
    //                d = diffDays;
    //            }
    //            System.out.print(d + " days ");
    //            //            System.out.print(diffHours + " hours, ");
    //            //            System.out.print(diffMinutes + " minutes, ");
    //            //            System.out.print(diffSeconds + " seconds.");
    //
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //    }
    //
    //    private long findDn(String registrationDate) {
    //        //Registration Date: 2016-10-20 18:15:06
    //        //        String dateStart = "01/14/2012 09:29:58";
    //        //        String dateStop = "01/15/2012 09:29:57";
    //        String dateStart = registrationDate;//"2019/10/06 09:29:58";
    //        String dateStop = "";//findCurrentDate();//"2019/10/07 09:29:57";
    //
    //        //HH converts hour in 24 hours format (0-23), day calculation
    //        //        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    //        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    //
    //        Date d1;
    //        Date d2;
    //        long diffDays;
    //        long d = 0L;
    //        try {
    //            d1 = format.parse(dateStart);
    //            d2 = format.parse(dateStop);
    //
    //            //in milliseconds
    //            long diff = d2.getTime() - d1.getTime();
    //
    //            long diffSeconds = diff / 1000 % 60;
    //            long diffMinutes = diff / (60 * 1000) % 60;
    //            long diffHours = diff / (60 * 60 * 1000) % 24;
    //            diffDays = diff / (24 * 60 * 60 * 1000);
    //            ++diffDays;
    //
    //            if (diffDays >= 7) {
    //                d = diffDays % 7;
    //                d++;
    //            }
    //            else {
    //                d = diffDays;
    //            }
    //            System.out.print(d + " days, ");
    //            //            System.out.print(diffHours + " hours, ");
    //            //            System.out.print(diffMinutes + " minutes, ");
    //            //            System.out.print(diffSeconds + " seconds.");
    //
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //        return d;
    //    }
}
