package com.finvendor.server.common.commondao;

import com.finvendor.common.util.Pair;
import com.finvendor.model.Roles;
import com.finvendor.modelpojo.staticpojo.admindashboard.CompanyDetails;
import org.hibernate.SQLQuery;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface ICommonDao {
	List<Roles> executeNamedQuery(String namedQueryname) throws RuntimeException;

	List<CompanyDetails> getCompanyDetails(String sql, String rsrchAreaId) throws RuntimeException;

	SQLQuery getNativeQuery(String sql, Object[] conditionValue);

	String runSql(String sql, Map<String, Map<String, String>> columnNameMap, Object[] conditionValue,
			Map<String, Object> firstDefaultParamsMap, Map<String, Object> lastDefaultParamsMap, int colIndex)
			throws RuntimeException;

	org.hibernate.Query getNamedQuery(String namedQuery, Map<Object, Object> paramMap);

	int insert(String sql, Map<Integer, Object> params) throws RuntimeException;
	String getRecordStats(String query, String perPageMaxRecords) throws RuntimeException;
	String applyPagination(String pageNumber, String perPageMaxRecords);
	Pair<Long,InputStream> fetchBlobFromTable(String namedQuery,Map<Object, Object> paramMap) throws RuntimeException;
}
