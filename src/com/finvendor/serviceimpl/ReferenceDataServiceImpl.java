package com.finvendor.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
	public List<SecurityType> getSecurityTypesForAssetClassId(int assetClassId) 
			throws ApplicationException {
		logger.debug("Entering : ReferenceDataServiceImpl - getSecurityTypesForAssetClassId for : {}", assetClassId);		
		List<SecurityType> secList = null;
		secList = referenceDataDao.getSecurityTypesForAssetClassId(assetClassId);
		return secList;
	}
	
	@Override
	public AssetClass getAssetClassDetails(String asset_class)
			throws ApplicationException {
		return referenceDataDao.getAssetClassDetails(asset_class);
	}
	
	@Override
	public SecurityType getSecurityTypes(String security)
			throws ApplicationException {
		return referenceDataDao.getSecurityTypes(security);
	}
	
	@Override
	public Region getRegionsByName(String regionsName)
			throws ApplicationException {
		return referenceDataDao.getRegionsByName(regionsName);
	}
	
	@Override
	public Country getCountryByName(String countryName)
			throws ApplicationException {
		return referenceDataDao.getCountryByName(countryName);
	}
	
	@Override
	public Exchange getExchangesByName(String exchangeName)
			throws ApplicationException {
		return referenceDataDao.getExchangesByName(exchangeName);
	}
	
	@Override
	public String getRegion(String country)
			throws ApplicationException {
		return referenceDataDao.getRegion(country);
	}
	
	@Override
	public Country getCountryById(String countryId)
			throws ApplicationException {
		return referenceDataDao.getCountryById(countryId);
	}

}
