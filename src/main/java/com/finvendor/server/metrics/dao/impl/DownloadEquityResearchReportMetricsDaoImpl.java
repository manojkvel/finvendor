package com.finvendor.server.metrics.dao.impl;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.*;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finvendor.model.metrics.DownloadEqtyResearchReportsMetrics;
import com.finvendor.server.common.commondao.GenericDao;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.server.metrics.dao.IMetricsDao;
import com.finvendor.server.metrics.dto.MetricsDto;

@Repository
public class DownloadEquityResearchReportMetricsDaoImpl extends AbstractMetricsDao<DownloadEqtyResearchReportsMetrics>
		implements IMetricsDao {


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
					.getNamedQuery(DownloadEqtyResearchReportsMetrics.LOCAL_DATE_NAMED_QUERY, paramMap);
			List<DownloadEqtyResearchReportsMetrics> metricsEntityList = query.setMaxResults(1).list();
			DownloadEqtyResearchReportsMetrics metricsEntity = null;
			if (metricsEntityList.size() == 0) {
				metricsEntity = new DownloadEqtyResearchReportsMetrics();
				metricsEntity.setUser_name(userName);
				metricsEntity.setCount("1");
				metricsEntity.setLocal_date(localDate);
				metricsEntity.setIp_address(inetAddress.getHostAddress());
			} else {
				metricsEntity = metricsEntityList.get(0);
				if (!metricsEntity.getLocal_date().equals(localDate)) {
					metricsEntity = new DownloadEqtyResearchReportsMetrics();
					metricsEntity.setLocal_date(localDate);
				} else {
					metricsEntity.setLocal_date(localDate);
					metricsEntity.setId(metricsEntity.getId());
				}
				metricsEntity.setUser_name(userName);
				metricsEntity.setCount(String.valueOf(
						Integer.parseInt(metricsEntity.getCount() != null ? metricsEntity.getCount() : "0") + 1));
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
		return getMetrics(DOWNLOAD_EQTY_RESEARCH_REPORT_ALL_SQL);
	}

	@Override
	public Map<String, Object> getYearMetrics() throws RuntimeException {
		return getMetrics(DOWNLOAD_EQTY_RESEARCH_REPORT_YEAR_SQL);
	}

	@Override
	public Map<String, Object> getYearMonthMetrics() throws RuntimeException {
		return getMetrics(DOWNLOAD_EQTY_RESEARCH_REPORT_YEAR_MONTH_SQL);
	}

	@Override
	public Map<String, Object> getYearMonthDayMetrics() throws RuntimeException {
		return getMetrics(DOWNLOAD_EQTY_RESEARCH_REPORT_YEAR_MONTH_DAY_SQL);
	}
}
