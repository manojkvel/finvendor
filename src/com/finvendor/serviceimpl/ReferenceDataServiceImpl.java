package com.finvendor.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.dao.ReferenceDataDao;
import com.finvendor.exception.ApplicationException;
import com.finvendor.json.bean.ReferenceDataJson;
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
	public Object getModelObjectById(Class<?> type, Serializable id) {
		return referenceDataDao.getModelObjectById(type, id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<SecurityType> getSecurityTypesForAssetClassId(int assetClassId) 
			throws ApplicationException {
		logger.debug("Entering : ReferenceDataServiceImpl - getSecurityTypesForAssetClassId for : {}", 
				assetClassId);		
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
	public Region getRegionByName(String regionsName)
			throws ApplicationException {
		return referenceDataDao.getRegionByName(regionsName);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Country getCountryByName(String countryName)
			throws ApplicationException {
		return referenceDataDao.getCountryByName(countryName);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Exchange getExchangeByName(String exchangeName)
			throws ApplicationException {
		return referenceDataDao.getExchangeByName(exchangeName);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<ReferenceDataJson> getJsonReferenceData(String type, String parentId) 
			 throws ApplicationException {
		logger.debug("Entering : ReferenceDataServiceImpl - getJsonReferenceData for : {}", 
				type);
		List<ReferenceDataJson> refDataList = new ArrayList<ReferenceDataJson>();
		try {
			switch(type) {
				case "Region" :
					List<Region> regionList = referenceDataDao.getAllRegions();
					for(Region region : regionList) {
						ReferenceDataJson refData = new ReferenceDataJson();
						refData.setId(region.getName().toString());
						refData.setName(region.getName());
						refDataList.add(refData);
					}
					break;	
				
				case "Country" :
					List<Country> countryList = null;
					if(parentId == null || parentId.trim().equals("")) {
						countryList = referenceDataDao.getAllCountries();
					}else {
						countryList = referenceDataDao.getCountriesByRegionId(parentId);
					}
					for(Country country : countryList) {
						ReferenceDataJson refData = new ReferenceDataJson();
						refData.setId(country.getCountry_id().toString());
						refData.setName(country.getName());
						refData.setParentId(country.getRegion().getRegion_id().toString());						
						refDataList.add(refData);
					}
					break;
				
				case "Exchange" :
					List<Exchange> exchangeList = null;
					if(parentId == null || parentId.trim().equals("")) {
						exchangeList = referenceDataDao.getAllExchanges();
					}else {
						exchangeList = referenceDataDao.getExchangesByCountryId(parentId);
					}
					for(Exchange exchange : exchangeList) {
						ReferenceDataJson refData = new ReferenceDataJson();
						refData.setId(exchange.getExchange_id().toString());
						refData.setName(exchange.getName());
						refData.setParentId(exchange.getCountry().getCountry_id().toString());
						refDataList.add(refData);
					}
					break;
				
				default :
					logger.error("Wrong Type in getJsonReferenceData : {}", 
							type);
			}
		} catch (Exception exp) {
			logger.debug("Error fetching Reference data for : {}", type);
			throw new ApplicationException("Error fetching Reference data");
		}
		return refDataList;
	}
	
	@Override
	@Transactional(readOnly=true)
	public ReferenceDataJson getJsonReferenceDataById(String type, 
			String id) {
		return null;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<ReferenceDataJson> getJsonReferenceDataByParentId(
			String type, String id) {
		return null;
	}

}
