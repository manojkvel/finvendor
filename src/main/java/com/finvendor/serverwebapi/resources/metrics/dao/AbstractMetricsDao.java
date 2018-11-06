package com.finvendor.serverwebapi.resources.metrics.dao;

import com.finvendor.server.common.commondao.GenericDao;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.serverwebapi.resources.metrics.dto.MetricsDto;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Ayush
 * @param <T>
 */
public abstract class AbstractMetricsDao<T> extends GenericDao<T> {
    @Autowired
    protected ICommonDao commonDao;

    public static final String EQTY_RESEARCH_ALL_SQL="select * from eqty_research_report_metrics";
    public static final String EQTY_RESEARCH_YEAR_SQL="select * from eqty_research_report_metrics where year(local_date)=?";
    public static final String EQTY_RESEARCH_YEAR_MONTH_SQL="select * from eqty_research_report_metrics where year(local_date)=? and month(local_date)=?";
    public static final String EQTY_RESEARCH_YEAR_MONTH_DAY_SQL="select * from eqty_research_report_metrics where year(local_date)=? and month(local_date)=? and day(local_date)=?";

    public static final String DOWNLOAD_EQTY_RESEARCH_REPORT_ALL_SQL="select * from download_eqty_research_report_metrics";
    public static final String DOWNLOAD_EQTY_RESEARCH_REPORT_YEAR_SQL="select * from download_eqty_research_report_metrics where year(local_date)=?";
    public static final String DOWNLOAD_EQTY_RESEARCH_REPORT_YEAR_MONTH_SQL="select * from download_eqty_research_report_metrics where year(local_date)=? and month(local_date)=?";
    public static final String DOWNLOAD_EQTY_RESEARCH_REPORT_YEAR_MONTH_DAY_SQL="select * from download_eqty_research_report_metrics where year(local_date)=? and month(local_date)=? and day(local_date)=?";

    public static final String HOME_PAGE_ALL_SQL="select * from home_page_metrics";
    public static final String HOME_PAGE_YEAR_SQL="select * from home_page_metrics where year(local_date)=?";
    public static final String HOME_PAGE_YEAR_MONTH_SQL="select * from home_page_metrics where year(local_date)=? and month(local_date)=?";
    public static final String HOME_PAGE_YEAR_MONTH_DAY_SQL="select * from home_page_metrics where year(local_date)=? and month(local_date)=? and day(local_date)=?";

    public static final String LOCAL_DATE_FORMAT_YYY_MM_DD = "yyy/MM/dd";

    protected Map<String, Object>  getMetrics( String sql) throws RuntimeException {
        SQLQuery sqlQuery = commonDao.getNativeQuery(sql, null);
        Map<String, Object>  dtoList = getMetricsData(sqlQuery);
        return dtoList;
    }

    private Map<String, Object>  getMetricsData(SQLQuery sqlQuery) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<MetricsDto> dtoList = new ArrayList<>();
        int totalCount=0;
        try {
            List<Object[]> rows = sqlQuery.list();
            for (Object[] row : rows) {
                MetricsDto dto=new MetricsDto();
                String userName = row[1] != null ? row[1].toString().trim() : "";
                String count = row[2] != null ? row[2].toString().trim() : "";
                String localDate = row[3] != null ? row[3].toString().trim() : "";
                String ipAddress = row[4] != null ? row[4].toString().trim() : "";
                dto.setUserName(userName);
                dto.setCount(count);
                dto.setLocalDate(localDate);
                dto.setIpAddress(ipAddress);
                dtoList.add(dto);
                totalCount+=Integer.parseInt(count);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error has occurred while get request count metrics", e);
        }
        map.put("totalCount",totalCount);
        map.put("details",dtoList);
        return map;
    }
}
