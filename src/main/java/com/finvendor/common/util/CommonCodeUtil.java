package com.finvendor.common.util;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public final class CommonCodeUtil {

    public static long calculatePaginationLastPage(String perPageMaxRecords, long totalRecords) {
        long lastPageNumber;
        long perPageMaxRecordCount = Integer.parseInt(perPageMaxRecords);
        if (perPageMaxRecordCount <= totalRecords) {
            long remainder = totalRecords % perPageMaxRecordCount;
            if (remainder == 0) {
                lastPageNumber = totalRecords / perPageMaxRecordCount;
            } else {
                lastPageNumber = (totalRecords / perPageMaxRecordCount) + 1;
            }
        } else {
            lastPageNumber = 1;
        }
        return lastPageNumber;
    }

    /**
     * Pagination Algorithm ~~~~~~~~~~~~~~~~~~~~~ IF maxRecordCountPerPage=2
     * THEN IF pageNumber=1 THEN offset=(pageNumber - 1) * maxRecordCountPerPage
     * //0
     * <p>
     * IF pageNumber=2 THEN offset=(pageNumber - 1) * maxRecordCountPerPage //2
     * <p>
     * IF pageNumber=3 THEN offset=(pageNumber - 1) * maxRecordCountPerPage //4
     * ENDIF ------- ------- ENDIF
     **/
    public static String applyPagination(String pageNumber, String perPageMaxRecords) {
        int pageNumberAsInt = Integer.parseInt(pageNumber);
        int maxRecordCountPerPageAsLimit = Integer.parseInt(perPageMaxRecords);
        int offset = (pageNumberAsInt - 1) * maxRecordCountPerPageAsLimit;
        return " limit " + maxRecordCountPerPageAsLimit + " offset " + offset;
    }

    public static String getRecordStatsJson(long totalRecords, long lastPageNumber) throws IOException {
        Map<String, Object> paramsMap = new LinkedHashMap<>();
        paramsMap.put("firstPageNumber", 1);
        paramsMap.put("lastPageNumber", lastPageNumber);
        paramsMap.put("totalRecords", totalRecords);
        String recordStatsJson = JsonUtil.createJsonFromObject(paramsMap);
        return recordStatsJson;
    }

    public static  String convertObjectToJson(String key, Object companyDataList) throws IOException {
        Map<String, Object> paramsMap = new LinkedHashMap<>();
        paramsMap.put(key, companyDataList);
        return JsonUtil.createJsonFromParamsMap(paramsMap);
    }
}
