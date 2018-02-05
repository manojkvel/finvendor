package com.finvendor.common.dao.ifc;

import java.util.List;

import com.finvendor.bean.CompanyDetails;
import com.finvendor.model.Roles;

public interface ICommonDao {
	List<Roles> executeNamedQuery(String namedQueryname);
	List<CompanyDetails> getCompanyDetails(int rsrchAreaId);
}
