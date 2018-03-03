package com.finvendor.server.common.dao.ifc;

import java.util.List;

import com.finvendor.model.Roles;
import com.finvendor.modelpojo.staticpojo.admindashboard.CompanyDetails;

public interface ICommonDao {
	List<Roles> executeNamedQuery(String namedQueryname) throws RuntimeException;

	List<CompanyDetails> getCompanyDetails(String sql, String rsrchAreaId) throws RuntimeException;
	String findAll(Class<?> claaz,String[] cols,String[] conditionColumns) throws RuntimeException;
		
}
