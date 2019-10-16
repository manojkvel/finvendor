package com.finvendor.common.commondao;

import com.finvendor.common.util.Pair;
import com.finvendor.model.Roles;
import com.finvendor.modelpojo.staticpojo.admindashboard.ResearchReportFor;
import org.hibernate.SQLQuery;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface ICommonDao {
	List<Roles> executeNamedQuery(String namedQueryname) throws RuntimeException;

	List<ResearchReportFor> getCompanyDetails(String sql, String rsrchAreaId) throws RuntimeException;
	List<ResearchReportFor>  getIndustrySubTypes(String query, String[] values) throws IOException;
	SQLQuery getNativeQuery(String sql, Object[] conditionValue);

	String runSql(String sql, Map<String, Map<String, String>> columnNameMap, Object[] conditionValue,
			Map<String, Object> firstDefaultParamsMap, Map<String, Object> lastDefaultParamsMap, int colIndex)
			throws RuntimeException;

	org.hibernate.Query getNamedQuery(String namedQuery, Map<Object, Object> paramMap);

	int insert(String sql, Map<Integer, Object> params) throws RuntimeException;
	String getRecordStats(String query, String perPageMaxRecords) throws RuntimeException;
	String applyPagination(String pageNumber, String perPageMaxRecords);
	Pair<Long,InputStream> fetchBlobFromTable(String namedQuery,Map<Object, Object> paramMap) throws RuntimeException;

    Map<String, String> findStockHistoricalPrices(float todaysCmp, String isinCode, boolean _1W, boolean _1M, boolean _3M,boolean _6M) throws Exception;
	Map<String, String> findNifty50HistoricalPrices(float nifty50Closing) throws Exception;
	Pair<String, Float> findCmp(String isin) throws Exception;
}
