package com.finvendor.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.finvendor.dao.ReferenceDataDao;
import com.finvendor.exception.ApplicationException;
import com.finvendor.model.SecurityType;
import com.finvendor.service.ReferenceDataService;

public class ReferenceDataServiceImpl 
	implements ReferenceDataService {
	
	private static Logger logger = LoggerFactory.getLogger(
			ReferenceDataServiceImpl.class);

	@Autowired
	private ReferenceDataDao referenceDataDao;
	
	public List<SecurityType> getSecurityTypesForAssetClassId(int assetClassId) 
			throws ApplicationException {
		logger.debug("Entering : ReferenceDataServiceImpl - getSecurityTypesForAssetClassId for : {}", assetClassId);		
		List<SecurityType> secList = null;
		secList = referenceDataDao.getSecurityTypesForAssetClassId(assetClassId);
		return secList;
	}

}
