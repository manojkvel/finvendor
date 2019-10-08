package com.finvendor.api.metrics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LimitSearch {
    private static Map<Integer, Integer> dayWithLimitMap;
    private static int total = 0;

    static {
        dayWithLimitMap = new LinkedHashMap<>();
        dayWithLimitMap.put(1, 3);
        dayWithLimitMap.put(2, 3);
        dayWithLimitMap.put(3, 3);
        dayWithLimitMap.put(4, 3);
        dayWithLimitMap.put(5, 3);
        dayWithLimitMap.put(6, 3);
        dayWithLimitMap.put(7, 3);
    }

    public static void main(String[] args) throws ParseException {
//        int dn = findDayOfWeek("9/10/2019");
//        System.out.println(dn);
//        System.out.println(isAllowedForSearch(dn));
//        System.out.println(isAllowedForSearch(dn));
//        System.out.println(isAllowedForSearch(dn));
//        System.out.println(isAllowedForSearch(dn));
        System.out.println(findDayNameOfWeek("13/10/2019"));
    }

    private static boolean isAllowedForSearch(int dn) throws ParseException {
        boolean allowedSearch;

        int cr = dayWithLimitMap.get(dn);
//        int tmp_cr;
        if (dn % 7 == 0) {
            total = 0;
        }
        if (cr != 0) {
//            tmp_cr = cr;
            if (total <= 11) {
                if (cr > 0) {
                    cr--;
                    dayWithLimitMap.put(dn, cr);
                }
                total++;
                allowedSearch = true;
            }
            else {
                if (dn % 7 == 0) {
                    total = 0;
                }
                allowedSearch = false;
            }
        }
        else {
            allowedSearch = false;
        }
        return allowedSearch;
    }

    private static int findDayOfWeek(String date) throws ParseException {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        Date dt1 = format1.parse(date);
        c.setTime(dt1);
        return c.get(Calendar.DAY_OF_WEEK);
    }
    private static String findDayNameOfWeek(String date) throws ParseException {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        Date dt1 = format1.parse(date);
        c.setTime(dt1);

        return c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
    }
}

