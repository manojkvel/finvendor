package com.finvendor.server.common.dao.ifc;

import java.util.List;

import com.finvendor.model.Roles;
import com.finvendor.modelpojo.staticpojo.CompanyDetails;
import com.finvendor.modelpojo.staticpojo.VoVendorDetails;

public interface ICommonDao {
	List<Roles> executeNamedQuery(String namedQueryname) throws RuntimeException;

	List<CompanyDetails> getCompanyDetails(String sql, String rsrchAreaId) throws RuntimeException;

	List<VoVendorDetails> getVoVendorDetails(String sql) throws RuntimeException;
}
