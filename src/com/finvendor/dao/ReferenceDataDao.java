package com.finvendor.dao;

import java.io.Serializable;
import java.util.List;

import com.finvendor.exception.ApplicationException;
import com.finvendor.model.AnalyticalSolutionSubType;
import com.finvendor.model.AnalyticalSolutionType;
import com.finvendor.model.AssetClass;
import com.finvendor.model.Country;
import com.finvendor.model.Exchange;
import com.finvendor.model.Region;
import com.finvendor.model.ResearchArea;
import com.finvendor.model.ResearchAreaCompanyDetails;
import com.finvendor.model.ResearchAreaStockClassification;
import com.finvendor.model.ResearchSubArea;
import com.finvendor.model.SecurityType;

public interface ReferenceDataDao {
	
	public Object getModelObjectById(Class<?> type, Serializable id);
	
	public List<AssetClass> getAllAssetClasses() 
			throws ApplicationException;
	public AssetClass getAssetClassByDescription(String assetClassDescription)
			throws ApplicationException;
	
	public List<SecurityType> getAllSecurityTypes() 
			throws ApplicationException;
	public List<SecurityType> getSecurityTypesForAssetClassId(int 
			assetClassId) throws ApplicationException;
	public SecurityType getSecurityTypeByName(String securityName)
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
	
	public List<ResearchArea> getAllResearchAreaForResearchReportVendorOffering() 
			throws ApplicationException;
	public List<ResearchSubArea> getAllResearchSubAreaForResearchReportVendorOffering() 
			throws ApplicationException;
	public List<ResearchSubArea> getResearchSubAreaForResearchReportVendorOfferingByResearchAreaId(String researchAreaId) 
			throws ApplicationException;
	public List<ResearchAreaStockClassification> getAllResearchAreaStockClassificationForResearchReportVendorOffering() 
			throws ApplicationException;
	public List<ResearchAreaCompanyDetails> getAllResearchAreaCompanyDetailsForResearchReportVendorOffering() 
			throws ApplicationException;
	public List<ResearchAreaCompanyDetails> getResearchAreaCompanyDetailsResearchReportVendorOfferingByResearchAreaId(String researchAreaId) 
			throws ApplicationException;
		
	public List<AnalyticalSolutionType> getAllAnalyticalSolutionTypeForAnalyticsApplicationVendorOffering() 
			throws ApplicationException;
	public List<AnalyticalSolutionSubType> getAllAnalyticalSolutionSubTypeForAnalyticsApplicationVendorOffering() 
			throws ApplicationException;
	public List<AnalyticalSolutionSubType> getAnalyticalSolutionSubTypeForAnalyticsApplicationVendorOfferingByAnalyticalSolutionTypeId(String analyticalSolutionTypeId) 
			throws ApplicationException;
}
