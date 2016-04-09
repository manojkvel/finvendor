package com.finvendor.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.dao.ReferenceDataDao;
import com.finvendor.exception.ApplicationException;
import com.finvendor.model.AssetClass;
import com.finvendor.model.Country;
import com.finvendor.model.Exchange;
import com.finvendor.model.Region;
import com.finvendor.model.SecurityType;
import com.finvendor.service.ReferenceDataService;

public class ReferenceDataServiceImpl 
	implements ReferenceDataService {
	
	private static Logger logger = LoggerFactory.getLogger(
			ReferenceDataServiceImpl.class);

	@Autowired
	private ReferenceDataDao referenceDataDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<SecurityType> getSecurityTypesForAssetClassId(int assetClassId) 
			throws ApplicationException {
		logger.debug("Entering : ReferenceDataServiceImpl - getSecurityTypesForAssetClassId for : {}", assetClassId);		
		List<SecurityType> secList = null;
		secList = referenceDataDao.getSecurityTypesForAssetClassId(assetClassId);
		return secList;
	}
	
	@Override
	@Transactional(readOnly=true)
	public AssetClass getAssetClassDetails(String asset_class)
			throws ApplicationException {
		return referenceDataDao.getAssetClassDetails(asset_class);
	}
	
	@Override
	@Transactional(readOnly=true)
	public SecurityType getSecurityTypes(String security)
			throws ApplicationException {
		return referenceDataDao.getSecurityTypes(security);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Region getRegionsByName(String regionsName)
			throws ApplicationException {
		return referenceDataDao.getRegionsByName(regionsName);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Country getCountryByName(String countryName)
			throws ApplicationException {
		return referenceDataDao.getCountryByName(countryName);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Exchange getExchangesByName(String exchangeName)
			throws ApplicationException {
		return referenceDataDao.getExchangesByName(exchangeName);
	}
	
	@Override
	@Transactional(readOnly=true)
	public String getRegion(String country)
			throws ApplicationException {
		return referenceDataDao.getRegion(country);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Country getCountryById(String countryId)
			throws ApplicationException {
		return referenceDataDao.getCountryById(countryId);
	}

}
