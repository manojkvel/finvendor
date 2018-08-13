package com.finvendor.server.common.commondao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.SQLQuery;

import com.finvendor.model.Roles;
import com.finvendor.modelpojo.staticpojo.admindashboard.CompanyDetails;

public interface ICommonDao {
	List<Roles> executeNamedQuery(String namedQueryname) throws RuntimeException;

	Set<CompanyDetails> getCompanyDetails(String sql, String rsrchAreaId) throws RuntimeException;

	SQLQuery getNativeQuery(String sql, Object[] conditionValue);

	String runSql(String sql, Map<String, Map<String, String>> columnNameMap, Object[] conditionValue,
			Map<String, Object> firstDefaultParamsMap, Map<String, Object> lastDefaultParamsMap, int colIndex)
			throws RuntimeException;

	org.hibernate.Query getNamedQuery(String namedQuery, Map<Object, Object> paramMap);

	int insert(String sql, Map<Integer, Object> params) throws RuntimeException;
}
