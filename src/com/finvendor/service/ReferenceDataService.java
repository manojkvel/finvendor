package com.finvendor.service;

import java.util.List;

import com.finvendor.exception.ApplicationException;
import com.finvendor.json.bean.ReferenceDataJson;
import com.finvendor.model.AssetClass;
import com.finvendor.model.Country;
import com.finvendor.model.Exchange;
import com.finvendor.model.Region;
import com.finvendor.model.SecurityType;

public interface ReferenceDataService {
	
	public List<SecurityType> getSecurityTypesForAssetClassId(
			int assetClassId) throws ApplicationException;
	public AssetClass getAssetClassDetails(String assetClassDescription)
			throws ApplicationException;
	public SecurityType getSecurityTypes(String security)
			throws ApplicationException;
	public Region getRegionsByName(String regionsName)
			throws ApplicationException;
	public Country getCountryByName(String countryName)
			throws ApplicationException;
	public Exchange getExchangesByName(String exchangeName)
			throws ApplicationException;
	public String getRegion(String country)
			throws ApplicationException;
	public Country getCountryById(String countryId)
			throws ApplicationException;
	public List<ReferenceDataJson> getJsonReferenceData(String type)
			 throws ApplicationException;
	public ReferenceDataJson getJsonReferenceDataById(String type, 
			String id)  throws ApplicationException;
	public List<ReferenceDataJson> getJsonReferenceDataByParentId(
			String type, String id) throws ApplicationException;
}
