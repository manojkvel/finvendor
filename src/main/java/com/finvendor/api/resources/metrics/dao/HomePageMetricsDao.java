package com.finvendor.api.resources.metrics.dao;

import com.finvendor.model.metrics.HomePageMetrics;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class HomePageMetricsDao extends AbstractMetricsDao<HomePageMetrics> {

	@SuppressWarnings("unchecked")
	public void increaseCount(String userName,String clientIp) throws RuntimeException {
		try {
			SimpleDateFormat formatter=new SimpleDateFormat(LOCAL_DATE_FORMAT_YYY_MM_DD);
			String localDate = formatter.format(Calendar.getInstance().getTime());
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("localdate", localDate);
			paramMap.put("username", userName);
			org.hibernate.Query query = commonDao
					.getNamedQuery(HomePageMetrics.LOCAL_DATE_NAMED_QUERY, paramMap);
			List<HomePageMetrics> metricsEntityList = query.setMaxResults(1).list();
			HomePageMetrics metricsEntity;
			if (metricsEntityList.size() == 0) {
				metricsEntity = new HomePageMetrics();
				metricsEntity.setUser_name(userName);
				metricsEntity.setCount("1");
				metricsEntity.setLocal_date(localDate);
				metricsEntity.setIp_address(clientIp);
			} else {
				metricsEntity = metricsEntityList.get(0);
				if (!metricsEntity.getLocal_date().equals(localDate)) {
					metricsEntity = new HomePageMetrics();
					metricsEntity.setLocal_date(localDate);
				} else {
					metricsEntity.setLocal_date(localDate);
					metricsEntity.setId(metricsEntity.getId());
				}
				metricsEntity.setUser_name(userName);
				metricsEntity.setCount(String
						.valueOf(Integer.parseInt(metricsEntity.getCount() != null ? metricsEntity.getCount() : "0") + 1));
				metricsEntity.setIp_address(clientIp);
			}
			saveOrUpdate(metricsEntity);
		} catch (Exception e) {
			throw new RuntimeException("Error has occurred while get request count metrics", e);
		}

	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getAllMetrics() throws RuntimeException {
		return getMetrics(HOME_PAGE_ALL_SQL);
	}

	public Map<String, Object> getYearMetrics() throws RuntimeException {
		return getMetrics(HOME_PAGE_YEAR_SQL);
	}

	public Map<String, Object> getYearMonthMetrics() throws RuntimeException {
		return getMetrics(HOME_PAGE_YEAR_MONTH_SQL);
	}

	public Map<String, Object> getYearMonthDayMetrics() throws RuntimeException {
		return getMetrics(HOME_PAGE_YEAR_MONTH_DAY_SQL);
	}
}
