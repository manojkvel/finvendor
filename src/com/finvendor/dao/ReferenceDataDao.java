package com.finvendor.dao;

import java.io.Serializable;
import java.util.List;

import com.finvendor.exception.ApplicationException;
import com.finvendor.model.AssetClass;
import com.finvendor.model.Country;
import com.finvendor.model.Exchange;
import com.finvendor.model.Region;
import com.finvendor.model.SecurityType;

public interface ReferenceDataDao {
	
	public AssetClass getAssetClassDetails(String asset_class)
			throws ApplicationException;
	
	public List<SecurityType> getSecurityTypesForAssetClassId(int 
			assetClassId) throws ApplicationException;
	public SecurityType getSecurityTypes(String security)
			throws ApplicationException;
	
	public Region getRegionByName(String regionsName)
			throws ApplicationException;
	public List<Region> getAllRegions() 
			throws ApplicationException;
	
	public List<Country> getAllCountries() 
			throws ApplicationException;	
	public Country getCountryByName(String countryName)
			throws ApplicationException;
	public List<Country> getCountriesByRegionId(String regionId) 
			throws ApplicationException;	
	
	public List<Exchange> getAllExchanges()
			throws ApplicationException;
	public Exchange getExchangeByName(String exchangeName)
			throws ApplicationException;
	public List<Exchange> getExchangesByCountryId(String countryId)
			throws ApplicationException;
	
	public Object getModelObjectById(Class<?> type, Serializable id);
}
