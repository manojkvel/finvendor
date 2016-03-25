package com.finvendor.dao;

import java.util.List;

import com.finvendor.exception.ApplicationException;
import com.finvendor.model.SecurityType;

public interface ReferenceDataDao {

	public List<SecurityType> getSecurityTypesForAssetClassId(int assetClassId)
			throws ApplicationException ;
}
