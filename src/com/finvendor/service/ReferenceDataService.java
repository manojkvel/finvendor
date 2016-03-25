package com.finvendor.service;

import java.util.List;

import com.finvendor.exception.ApplicationException;
import com.finvendor.model.SecurityType;

public interface ReferenceDataService {
	
	public List<SecurityType> getSecurityTypesForAssetClassId(int assetClassId)
		throws ApplicationException ;

}
