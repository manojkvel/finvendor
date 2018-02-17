package com.finvendor.server.common.dao.ifc;

import java.util.List;

import com.finvendor.model.Roles;
import com.finvendor.modelpojo.staticpojo.CompanyDetails;

public interface ICommonDao {
	List<Roles> executeNamedQuery(String namedQueryname);
	List<CompanyDetails> getCompanyDetails(int rsrchAreaId);
	
}
