package com.finvendor.server.metrics.dao.impl;

import com.finvendor.model.metrics.EqtyResearchReportsMetrics;
import com.finvendor.model.metrics.HomePageMetrics;
import com.finvendor.server.metrics.dao.IMetricsDao;
import com.finvendor.server.metrics.dto.MetricsDto;
import org.springframework.stereotype.Repository;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class HomePageMetricsDaoImpl extends AbstractMetricsDao<HomePageMetrics> implements IMetricsDao {

	@SuppressWarnings("unchecked")
	@Override
	public void increaseCount(String userName) throws RuntimeException {
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
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
				metricsEntity.setIp_address(inetAddress.getHostAddress());
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
				metricsEntity.setIp_address(inetAddress.getHostAddress());
			}
			saveOrUpdate(metricsEntity);
		} catch (Exception e) {
			throw new RuntimeException("Error has occurred while get request count metrics", e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getAllMetrics() throws RuntimeException {
		return getMetrics(HOME_PAGE_ALL_SQL);
	}

	@Override
	public Map<String, Object> getYearMetrics() throws RuntimeException {
		return getMetrics(HOME_PAGE_YEAR_SQL);
	}

	@Override
	public Map<String, Object> getYearMonthMetrics() throws RuntimeException {
		return getMetrics(HOME_PAGE_YEAR_MONTH_SQL);
	}

	@Override
	public Map<String, Object> getYearMonthDayMetrics() throws RuntimeException {
		return getMetrics(HOME_PAGE_YEAR_MONTH_DAY_SQL);
	}
}
