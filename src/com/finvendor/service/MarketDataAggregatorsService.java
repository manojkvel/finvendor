package com.finvendor.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.finvendor.form.FinancialAnalyticsApplicationVendorSearchForm;
import com.finvendor.form.MarketDataAggregatorsVendorSearchForm;
import com.finvendor.form.ResearchReportProvidersVendorSearchForm;
import com.finvendor.form.TradingApplicationVendorSearchForm;
import com.finvendor.model.AssetClass;
import com.finvendor.model.AssetClassDataDetails;
import com.finvendor.model.AssetClassSecurityMap;
import com.finvendor.model.Awards;
import com.finvendor.model.CompanySubType;
import com.finvendor.model.Cost;
import com.finvendor.model.Country;
import com.finvendor.model.CountryExchangeMap;
import com.finvendor.model.Exchange;
import com.finvendor.model.FileFields;
import com.finvendor.model.OfferingFiles;
import com.finvendor.model.Region;
import com.finvendor.model.RegionCountryMap;
import com.finvendor.model.SecurityType;
import com.finvendor.model.Support;
import com.finvendor.model.VendorOffering;

public interface MarketDataAggregatorsService {
	
	List<AssetClass> getAllAssetClass();
	List<AssetClassSecurityMap> getSecurityTypeByAssetClassId(Integer assetId);
	List<Region> getAllRegionClass();
	List<RegionCountryMap> getRegionCountryMapsRegionId(Integer regionId);
	List<CountryExchangeMap> getCountryExchangeMapsByCountryId(Integer countryId);
	List<Country> getAllCountries();
	List<Support> getAllVendorSupports();
	List<Cost> getAllCostInfo();
	List<Awards> getAllAwards(String vendorId);
	List<Exchange> getAllExchanges();
	List<Region> getRegionNamesByName(String regionName);
	
	Region getRegionById(String Id);
	Country getCountryById(String Id);
	Cost getCostById(String Id);
	
	List<AssetClassDataDetails> getSingleAssetClassSearchResultInfo(String assetclassId,
			List<String> securitytypeId, String dataattribute,
			List<String> regionList, List<String> countryList,
			List<String> exchangesList);

	List<AssetClassDataDetails> getSingleAssetClassSearchResultVendorInfo(
			String assetclassId, List<String> securitytypeList,
			String vendorregionofincorp,
			List<String> vendorcountryofincorpList,
			List<String> vendorprofilefreshnessList,
			List<String> vendoryearoperationList, String searchkeyword,
			List<String> vendorsupportregionList,
			List<String> vendorsupporttimeList, List<String> awardsList,
			List<String> acquisitioncostrangeList);

	List<AssetClassDataDetails> getSingleAssetClassVendorDetails(
			String assetclassId, List<String> securitytypeList,
			List<String> vendorregionofincorpList,
			List<String> vendorcountryofincorpList,
			String vendorprofilefreshness, String vendoryearoperation,
			String searchkeyword, String vendorsupportregion,
			String vendorsupporttime, String vendorawards,
			String vendorcostrange);

	List<AssetClassDataDetails> getMultiAssetClassSearchResultInfo(
			List<String> assetClassList, List<String> securityList);

	AssetClass getAssetClassByName(String assetType);
	
	VendorOffering createOfferings(String id, VendorOffering vendorOffering);
	OfferingFiles addOfferingFiles(String id, OfferingFiles OfferingFiles);
	FileFields addFieldsToFile(String id, FileFields fileFields);
	
	Set<VendorOffering> listOfferings(String id);
	Set<OfferingFiles> listOfferingFiles(String id);
	Set<FileFields> listFieldsToFile(String id);
	
	VendorOffering deleteOfferings(String id);
	OfferingFiles deleteOfferingFiles(String id);
	FileFields deleteFieldsToFile(String id);
	List<SecurityType> listSecurityType();
	
	VendorOffering getVendorOfferingById(String Id);

	Exchange getExchangeById(String exchange);
	
	List<CompanySubType> getCompanySubTypeList();

	List<FinancialAnalyticsApplicationVendorSearchForm> getFAMultiAssetClassSearchResult(
			Map<Object, Object> searchData, FinancialAnalyticsApplicationVendorSearchForm dataForm);
	List<ResearchReportProvidersVendorSearchForm> getRRMultiAssetClassSearchResult(
			Map<Object, Object> searchData, ResearchReportProvidersVendorSearchForm dataForm);
	List<TradingApplicationVendorSearchForm> getTAMultiAssetClassSearchResult(
			Map<Object, Object> searchData, TradingApplicationVendorSearchForm dataForm);
	Map<String, Object> getMultiAssetClassSearchResult(Map<Object, Object> searchData,
			MarketDataAggregatorsVendorSearchForm dataForm);

	public Object getModelObjectById(Class<?> type, Serializable id);
	
	public Map<String, Object> getResearchReportVendorSearchResult(
			Map<Object, Object> searchData, MarketDataAggregatorsVendorSearchForm dataForm);
	
	public Map<String, Object> getTradingApplicationVendorSearchResult(Map<Object, Object> searchData, 
			MarketDataAggregatorsVendorSearchForm dataForm);
}
