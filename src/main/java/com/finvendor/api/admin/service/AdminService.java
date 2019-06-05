package com.finvendor.api.admin.service;

import com.finvendor.common.exception.ApplicationException;
import com.finvendor.model.ReferenceData;

import java.util.List;

public interface AdminService {

	public List<Object[]> getReferenceData(ReferenceData refData);
	
	public List<Object[]> getReferenceDataRow(ReferenceData refData, String primaryKeyValue);
	
	public int updateReferenceDataRow(ReferenceData refData, List<String> params, List<Boolean> paramSelected) throws ApplicationException;

	public int addReferenceDataRow(ReferenceData refData, List<String> params, List<Boolean> paramSelected) throws ApplicationException;
	
	public int deleteReferenceDataRow(ReferenceData refData, List<String> primaryKeyName, List<String> primaryKeyValue) throws ApplicationException;
}
