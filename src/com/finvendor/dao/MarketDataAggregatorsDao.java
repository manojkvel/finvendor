/**
 * 
 */
package com.finvendor.dao;

import java.io.Serializable;
import java.util.Collection;
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
import com.finvendor.model.VendorSearchResult;

/**
 * @author rayulu vemula
 *
 */
public interface MarketDataAggregatorsDao {

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get asset class
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDao#getAllAssetClass()
	 */
	List<AssetClass> getAllAssetClass();

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get all corresponding security types
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDao#getSecurityTypeByAssetClassId(assetId)
	 */
	List<AssetClassSecurityMap> getSecurityTypeByAssetClassId(Integer assetId);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get all regions
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDao#getAllRegionClass()
	 */
	List<Region> getAllRegionClass();

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get region country maps
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDao#getRegionCountryMapsRegionId(regionId)
	 */
	List<RegionCountryMap> getRegionCountryMapsRegionId(Integer regionId);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get country exchange maps
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDao#getCountryExchangeMapsByCountryId(countryId)
	 */
	List<CountryExchangeMap> getCountryExchangeMapsByCountryId(Integer countryId);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get countries
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDao#getAllCountries()
	 */
	List<Country> getAllCountries();

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get vendor support
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDao#getAllVendorSupports()
	 */
	List<Support> getAllVendorSupports();

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get all costs
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDao#getAllCostInfo()
	 */
	List<Cost> getAllCostInfo();

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get all awards
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDao#getAllAwards()
	 */
	List<Awards> getAllAwards();

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get all awards
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDao#getAllExchanges()
	 */
	List<Exchange> getAllExchanges();
	
	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get region names
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDao#getRegionNamesByName(regionName)
	 */
	List<Region> getRegionNamesByName(String regionName);
	
	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get Single Asset Class Search Result
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDao#getSingleAssetClassSearchResultInfo()
	 */
	List<AssetClassDataDetails> getSingleAssetClassSearchResultInfo(String assetclassId,
			List<String> securitytypeId, String dataattribute,
			List<String> regionList, List<String> countryList,
			List<String> exchangeList);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get Single Asset Class Search Result
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDao#getSingleAssetClassSearchResultVendorInfo()
	 */
	List<AssetClassDataDetails> getSingleAssetClassSearchResultVendorInfo(
			String assetclassId, List<String> securitytypeList,
			String vendorregionofincorp,
			List<String> vendorcountryofincorpList,
			List<String> vendorprofilefreshnessList,
			List<String> vendoryearoperationList, String searchkeyword,
			List<String> vendorsupportregionList,
			List<String> vendorsupporttimeList, List<String> awardsList,
			List<String> acquisitioncostrangeList);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get Single Asset Class Search Result
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDao#getSingleAssetClassVendorDetails()
	 */
	List<AssetClassDataDetails> getSingleAssetClassVendorDetails(
			String assetclassId, List<String> securitytypeList,
			List<String> vendorregionofincorpList,
			List<String> vendorcountryofincorpList,
			String vendorprofilefreshness, String vendoryearoperation,
			String searchkeyword, String vendorsupportregion,
			String vendorsupporttime, String vendorawards,
			String vendorcostrange);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get Single Asset Class Search Result
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDao#getMultiAssetClassSearchResultInfo()
	 */
	List<AssetClassDataDetails> getMultiAssetClassSearchResultInfo(
			List<String> assetClassList, List<String> securityList);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get Asset Class by passing name 
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDao#getAssetClassByName(assetType)
	 */
	AssetClass getAssetClassByName(String assetType);


	
	VendorOffering createOfferings(String id, VendorOffering vendorOffering );
	OfferingFiles addOfferingFiles(String id, OfferingFiles offeringFiles);
	FileFields addFieldsToFile(String id,FileFields fileFields);
	
	Set<VendorOffering> listOfferings(String id);
	Set<OfferingFiles> listOfferingFiles(String id);
	Set<FileFields> listFieldsToFile(String id);
	VendorOffering findOfferingById(String id);
	VendorOffering deleteOfferings(VendorOffering vendorOffering);
	OfferingFiles deleteOfferingFiles(String id);
	FileFields deleteFieldsToFile(String id);
	
	public List<SecurityType> listSecurityType();
	VendorOffering getVendorOfferingById(String Id);
	
	Region getRegionById(String Id);
	Country getCountryById(String Id);
	Cost getCostById(String Id);
	Exchange getExchangeById(String exchange);
	List<CompanySubType> getCompanySubTypeList();

	List<FinancialAnalyticsApplicationVendorSearchForm> getFAMultiAssetClassSearchResult(Map<Object, Object> searchData,FinancialAnalyticsApplicationVendorSearchForm dataForm);
	List<ResearchReportProvidersVendorSearchForm> getRRMultiAssetClassSearchResult(Map<Object, Object> searchData,ResearchReportProvidersVendorSearchForm dataForm);
	List<TradingApplicationVendorSearchForm> getTAMultiAssetClassSearchResult(Map<Object, Object> searchData,TradingApplicationVendorSearchForm dataForm);
	Map<String, Object> getMultiAssetClassSearchResult(Map<Object, Object> searchData,MarketDataAggregatorsVendorSearchForm dataForm);

	public Object getModelObjectById(Class<?> type, Serializable id);
	public Map<String, Object> getResearchReportVendorSearchResult(
			Map<Object, Object> searchData, MarketDataAggregatorsVendorSearchForm dataForm);
	
	public Map<String, Object> getTradingApplicationVendorSearchResult(Map<Object, Object> searchData, 
			MarketDataAggregatorsVendorSearchForm dataForm);
}
