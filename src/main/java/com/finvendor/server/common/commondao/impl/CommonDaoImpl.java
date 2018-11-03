package com.finvendor.server.common.commondao.impl;

import com.finvendor.common.util.CommonUtil;
import com.finvendor.common.util.Pair;
import com.finvendor.model.Roles;
import com.finvendor.model.VendorResearchReportsResearchDetails;
import com.finvendor.modelpojo.staticpojo.admindashboard.CompanyDetails;
import com.finvendor.server.common.commondao.AbstractCommonDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ayush on Feb 17, 2018
 */
@Repository
public class CommonDaoImpl extends AbstractCommonDao {

	@SuppressWarnings("unchecked")
	public List<Roles> executeNamedQuery(String namedQueryname) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery(namedQueryname);
		return (List<Roles>) query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyDetails> getCompanyDetails(String sql, String rsrchAreaId) {
		try {
			SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
			query.setInteger(0, Integer.parseInt(rsrchAreaId));
			List<Object[]> rows = query.list();

			List<CompanyDetails> results = new ArrayList<>();
			for (Object[] row : rows) {
				results.add(new CompanyDetails(Integer.parseInt(row[0].toString()), row[1].toString()));
			}
			return results;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Query getNamedQuery(String namedQuery, Map<Object, Object> paramMap) {
		try {
			org.hibernate.Query query = sessionFactory.openSession().getNamedQuery(namedQuery);
			for (Map.Entry<Object, Object> paramEntry : paramMap.entrySet()) {
				query.setParameter((String) paramEntry.getKey(), paramEntry.getValue());
			}
			return query;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int insert(String sql, Map<Integer, Object> params) throws RuntimeException {
		try {
			SQLQuery insertQuery = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
			for (Map.Entry<Integer, Object> entry : params.entrySet()) {
				Integer index = entry.getKey();
				Object value = entry.getValue();
				insertQuery.setParameter(index, value);
			}
			return insertQuery.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getRecordStats(String query, String perPageMaxRecords) throws RuntimeException {
		String recordStatsJson;
		long totalRecords;
		try {

			SQLQuery sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(query);
			List<Object[]> rows = sqlQuery.list();
			totalRecords = rows.size();
			if (totalRecords != 0L) {
				long lastPageNumber = CommonUtil.calculatePaginationLastPage(perPageMaxRecords, totalRecords);
				recordStatsJson = CommonUtil.getRecordStatsJson(totalRecords, lastPageNumber);
			} else {
				recordStatsJson = "";
			}
		} catch (IOException e) {
			throw new RuntimeException("Error while processing record stats", e);
		}
		return recordStatsJson;
	}

	@Override
	public String applyPagination(String pageNumber, String perPageMaxRecords) {
		return CommonUtil.applyPagination(pageNumber,perPageMaxRecords);
	}

	@Override
	public Pair<Long, InputStream> fetchBlobFromTable(String namedQuery,Map<Object, Object> paramMap) throws RuntimeException {
		org.hibernate.Query query = getNamedQuery(namedQuery, paramMap);
		List<VendorResearchReportsResearchDetails> researchDetailsList = query.list();

		InputStream inputStream=null;
		Long length = null;
		try {
			for (VendorResearchReportsResearchDetails researchReportsResearchDetails : researchDetailsList) {
				Blob rsrchUploadReportBlob = researchReportsResearchDetails.getRsrchUploadReport();
				inputStream = rsrchUploadReportBlob.getBinaryStream();
				length= rsrchUploadReportBlob.length();
				break;
			}
			return new Pair<>(length,inputStream);
		} catch (Exception e) {
			throw new RuntimeException("Error has occurred while fetching blob from table", e);
		}
	}
}
