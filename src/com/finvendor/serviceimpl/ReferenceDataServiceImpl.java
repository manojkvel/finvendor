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
import com.finvendor.model.AnalyticalSolutionSubType;
import com.finvendor.model.AnalyticalSolutionType;
import com.finvendor.model.AssetClass;
import com.finvendor.model.Country;
import com.finvendor.model.Exchange;
import com.finvendor.model.Region;
import com.finvendor.model.ResearchArea;
import com.finvendor.model.ResearchSubAreaCompanyDetails;
import com.finvendor.model.ResearchAreaStockClassification;
import com.finvendor.model.ResearchSubArea;
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
	public AssetClass getAssetClassByDescription(String assetClassDescription)
			throws ApplicationException {
		return referenceDataDao.getAssetClassByDescription(assetClassDescription);
	}
	
	@Override
	@Transactional(readOnly=true)
	public SecurityType getSecurityTypeByName(String securityName)
			throws ApplicationException {
		return referenceDataDao.getSecurityTypeByName(securityName);
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
						refData.setId(region.getRegion_id().toString());
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
				
				case "AssetClass" :
					List<AssetClass> assetClassList = referenceDataDao.getAllAssetClasses();
					for(AssetClass assetClass : assetClassList) {
						ReferenceDataJson refData = new ReferenceDataJson();
						refData.setId(assetClass.getAsset_class_id().toString());
						refData.setName(assetClass.getDescription());
						refDataList.add(refData);
					}
					break;
				
				case "SecurityType" :
					List<SecurityType> securityTypeList = null;
					if(parentId == null || parentId.trim().equals("")) {
						securityTypeList = referenceDataDao.getAllSecurityTypes();
					}else {
						securityTypeList = referenceDataDao.getSecurityTypesForAssetClassId(
								new Integer(parentId));
					}
					for(SecurityType securityType : securityTypeList) {
						ReferenceDataJson refData = new ReferenceDataJson();
						refData.setId(securityType.getSecurityTypeId().toString());
						refData.setName(securityType.getName());
						refData.setParentId(securityType.getAssetClassId().toString());
						refDataList.add(refData);
					}
					break;
					
				case "AnalyticalSolutionType" :
					List<AnalyticalSolutionType> analyticalSolutionTypeList = referenceDataDao.
						getAllAnalyticalSolutionTypeForAnalyticsApplicationVendorOffering();
					for(AnalyticalSolutionType analyticalSolutionType : analyticalSolutionTypeList) {
						ReferenceDataJson refData = new ReferenceDataJson();
						refData.setId(analyticalSolutionType.getAnalyticalSolutionTypeId().toString());
						refData.setName(analyticalSolutionType.getDescription());
						refDataList.add(refData);
					}
					break;	
					
				case "AnalyticalSolutionSubType" :
					List<AnalyticalSolutionSubType> analyticalSolutionSubTypeList = null;
					if(parentId == null || parentId.trim().equals("")) {
						analyticalSolutionSubTypeList = referenceDataDao.
								getAllAnalyticalSolutionSubTypeForAnalyticsApplicationVendorOffering();
					}else {
						analyticalSolutionSubTypeList = referenceDataDao.
								getAnalyticalSolutionSubTypeForAnalyticsApplicationVendorOfferingByAnalyticalSolutionTypeId(parentId);
					}						
					for(AnalyticalSolutionSubType analyticalSolutionSubType : analyticalSolutionSubTypeList) {
						ReferenceDataJson refData = new ReferenceDataJson();
						refData.setId(analyticalSolutionSubType.getAnalyticalSolutionSubTypeId().toString());
						refData.setName(analyticalSolutionSubType.getDescription());
						refData.setParentId(analyticalSolutionSubType.getAnalyticalSolutionType().getAnalyticalSolutionTypeId().toString());						
						refDataList.add(refData);
					}
					break;
				
				case "ResearchArea" :
					List<ResearchArea> researchAreaList = referenceDataDao.
						getAllResearchAreaForResearchReportVendorOffering();
					for(ResearchArea researchArea : researchAreaList) {
						ReferenceDataJson refData = new ReferenceDataJson();
						refData.setId(researchArea.getResearchAreaId().toString());
						refData.setName(researchArea.getDescription());
						refDataList.add(refData);
					}
					break;
					
				case "ResearchSubArea" :
					List<ResearchSubArea> researchSubAreaList = null;
					if(parentId == null || parentId.trim().equals("")) {
						researchSubAreaList = referenceDataDao.
								getAllResearchSubAreaForResearchReportVendorOffering();
					}else {
						researchSubAreaList = referenceDataDao.
								getResearchSubAreaForResearchReportVendorOfferingByResearchAreaId(parentId);
					}					
					for(ResearchSubArea researchSubArea : researchSubAreaList) {
						ReferenceDataJson refData = new ReferenceDataJson();
						refData.setId(researchSubArea.getResearchSubAreaId().toString());
						refData.setName(researchSubArea.getDescription());
						refData.setParentId(researchSubArea.getResearchArea().getResearchAreaId().toString());						
						refDataList.add(refData);
					}
					break;
					
				case "ResearchAreaStockClassification" :
					List<ResearchAreaStockClassification> researchAreaStockClassificationList = null;
					researchAreaStockClassificationList = referenceDataDao.
							getAllResearchAreaStockClassificationForResearchReportVendorOffering();
					
					for(ResearchAreaStockClassification researchAreaStockClassification : researchAreaStockClassificationList) {
						ReferenceDataJson refData = new ReferenceDataJson();
						refData.setId(researchAreaStockClassification.getStockClassificationTypeId().toString());
						refData.setName(researchAreaStockClassification.getStockClassificationName().toString());
						refData.setOtherAttributes("stock_classicfication_name=" + researchAreaStockClassification.getStockClassificationName().toString());					
						refDataList.add(refData);
					}
					
					/*
					researchAreaStockClassificationList.forEach(e -> {
						ReferenceDataJson refData = new ReferenceDataJson();
						refData.setId(e.getStockClassificationTypeId().toString());
						refData.setName(e.getStockClassificationName().toString());
						refData.setOtherAttributes("stock_classicfication_name=" + e.getStockClassificationName().toString());
						refDataList.add(refData);
					});
					*/
					
					break;
					
				case "ResearchAreaCompanyDetails" :
					List<ResearchSubAreaCompanyDetails> researchAreaCompanyDetailsList = null;
					if(parentId == null || parentId.trim().equals("")) {
						researchAreaCompanyDetailsList = referenceDataDao.
								getAllResearchAreaCompanyDetailsForResearchReportVendorOffering();
					}else {
						researchAreaCompanyDetailsList = referenceDataDao.
								getResearchAreaCompanyDetailsResearchReportVendorOfferingByResearchAreaId(parentId);
					}
					
					for(ResearchSubAreaCompanyDetails researchAreaCompanyDetails : researchAreaCompanyDetailsList) {
						ReferenceDataJson refData = new ReferenceDataJson();
						refData.setId(researchAreaCompanyDetails.getCompnayId().toString());
						refData.setName(researchAreaCompanyDetails.getCompanyName().toString());
						refData.setParentId(researchAreaCompanyDetails.getResearchSubArea().getResearchSubAreaId().toString());
						refData.setOtherAttributes("stock_classicfication_type_id=" + researchAreaCompanyDetails.getResearchAreaStockClassification().getStockClassificationTypeId());
						refDataList.add(refData);
					}
					
					/*
					researchAreaCompanyDetailsList.forEach(e -> {
						ReferenceDataJson refData = new ReferenceDataJson();
						refData.setId(e.getCompnayId().toString());
						refData.setName(e.getCompanyName().toString());
						refData.setParentId(e.getResearchSubArea().getResearchSubAreaId().toString());
						refData.setOtherAttributes("stock_classicfication_type_id=" + e.getResearchAreaStockClassification().getStockClassificationTypeId());
						refDataList.add(refData);
					});
					*/
					
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
