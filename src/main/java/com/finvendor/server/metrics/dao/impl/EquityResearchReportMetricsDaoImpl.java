package com.finvendor.server.metrics.dao.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finvendor.model.metrics.EqtyResearchReportsMetrics;
import com.finvendor.server.common.commondao.GenericDao;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.server.metrics.dao.IMetricsDao;
import com.finvendor.server.metrics.dto.MetricsDto;

@Repository
public class EquityResearchReportMetricsDaoImpl extends GenericDao<EqtyResearchReportsMetrics> implements IMetricsDao {

	private static final String eqtyRRMetricsSql= "select user_name username, web_request WebRequest,sum(count) TotalHitCount, monthname(local_date) Month,  year(local_date) Year from eqty_research_report_metrics group by user_name, monthname(local_date), year(local_date) order by user_name";

	private static final String LOCAL_DATE_FORMAT_YYY_MM_DD = "yyy/MM/dd";

	@Autowired
	private ICommonDao commonDao;

	@SuppressWarnings("unchecked")
	@Override
	public void increaseCount(String userName, String request) throws RuntimeException {
		SimpleDateFormat formatter=new SimpleDateFormat(LOCAL_DATE_FORMAT_YYY_MM_DD);
		String localDate = formatter.format(Calendar.getInstance().getTime());
		Map<Object, Object> paramMap = new HashMap<>();
		paramMap.put("webrequest", request);
		org.hibernate.Query query = commonDao
				.getNamedQuery(EqtyResearchReportsMetrics.METIRCS_BY_REQUEST_NAME_NAMED_QUERY, paramMap);
		List<EqtyResearchReportsMetrics> metricsEntityList = query.setMaxResults(1).list();
		EqtyResearchReportsMetrics metricsEntity = null;
		if (metricsEntityList.size() == 0) {
			metricsEntity = new EqtyResearchReportsMetrics();
			metricsEntity.setUser_name(userName);
			metricsEntity.setWeb_request(request);
			metricsEntity.setCount("1");
			metricsEntity.setLocal_date(localDate);
		} else {
			metricsEntity = metricsEntityList.get(0);
			if (!metricsEntity.getLocal_date().equals(localDate)) {
				metricsEntity = new EqtyResearchReportsMetrics();
				metricsEntity.setLocal_date(localDate);
			} else {
				metricsEntity.setLocal_date(localDate);
				metricsEntity.setId(metricsEntity.getId());
			}
			metricsEntity.setUser_name(userName);
			metricsEntity.setWeb_request(request);
			metricsEntity.setCount(String
					.valueOf(Integer.parseInt(metricsEntity.getCount() != null ? metricsEntity.getCount() : "0") + 1));
		}
		saveOrUpdate(metricsEntity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MetricsDto> getRequestMetrics() throws RuntimeException {
		List<MetricsDto> dtoList = new ArrayList<>();
		try {
		SQLQuery sqlQuery = commonDao.getNativeQuery(eqtyRRMetricsSql, null);
		List<Object[]> rows = sqlQuery.list();
		for (Object[] row : rows) {
			MetricsDto dto=new MetricsDto();
			String userName = row[0] != null ? row[0].toString().trim() : "";
			String webRequest = row[1] != null ? row[1].toString().trim() : "";
			String totalHit = row[2] != null ? row[2].toString().trim() : "";
			String month = row[3] != null ? row[3].toString().trim() : "";
			String year = row[4] != null ? row[4].toString().trim() : "";
			dto.setUserName(userName);
			dto.setRequest(webRequest);
			dto.setCount(totalHit);
			dto.setMonth(month);
			dto.setYear(year);
			dtoList.add(dto);
		}
		} catch (Exception e) {
			throw new RuntimeException("Error has occurred while get request count metrics", e);
		}
		return dtoList;
	}
}
