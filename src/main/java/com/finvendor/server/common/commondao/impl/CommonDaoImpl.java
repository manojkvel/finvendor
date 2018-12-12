package com.finvendor.server.common.commondao.impl;

import com.finvendor.common.util.CommonCodeUtil;
import com.finvendor.common.util.JsonUtil;
import com.finvendor.common.util.Pair;
import com.finvendor.model.Roles;
import com.finvendor.model.VendorResearchReportsResearchDetails;
import com.finvendor.model.vo.VendorReportFile;
import com.finvendor.modelpojo.staticpojo.admindashboard.ResearchReportFor;
import com.finvendor.server.common.commondao.AbstractCommonDao;
import com.finvendor.serverwebapi.resources.researchreport.sector.dto.IndustrySubTypeNameDto;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
	public List<ResearchReportFor> getCompanyDetails(String sql, String rsrchAreaId) {
		try {
			SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
			query.setInteger(0, Integer.parseInt(rsrchAreaId));
			List<Object[]> rows = query.list();

			List<ResearchReportFor> results = new ArrayList<>();
			for (Object[] row : rows) {
				results.add(new ResearchReportFor(Integer.parseInt(row[0].toString()), row[1].toString()));
			}
			return results;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<ResearchReportFor>  getIndustrySubTypes(String query, String[] values) throws IOException {
		SQLQuery nativeQuery = getNativeQuery(query, values);
		List<Object[]> rows = nativeQuery.list();
		List<ResearchReportFor> industrySubTypes = new ArrayList<>();
		Map<String, Object> dataMap = new LinkedHashMap<>();
		for (Object[] row : rows) {
			String id = row[0] != null ? row[0].toString().trim() : null;
			String value = row[1] != null ? row[1].toString().trim() : null;
			ResearchReportFor dto = new ResearchReportFor(Integer.parseInt(id),value);
			industrySubTypes.add(dto);
		}
		return industrySubTypes;
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
				long lastPageNumber = CommonCodeUtil.calculatePaginationLastPage(perPageMaxRecords, totalRecords);
				recordStatsJson = CommonCodeUtil.getRecordStatsJson(totalRecords, lastPageNumber);
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
		return CommonCodeUtil.applyPagination(pageNumber,perPageMaxRecords);
	}

	@Override
	public Pair<Long, InputStream> fetchBlobFromTable(String namedQuery,Map<Object, Object> paramMap) throws RuntimeException {
		org.hibernate.Query query = getNamedQuery(namedQuery, paramMap);
		List<VendorReportFile> researchDetailsList = query.list();

		InputStream inputStream=null;
		Long length = null;
		try {
			for (VendorReportFile vendorReportFile : researchDetailsList) {
				Blob rsrchUploadReportBlob = vendorReportFile.getReportFile();
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
