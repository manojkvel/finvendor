/**
 * 
 */
package com.finvendor.serviceimpl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.dao.MarketDataAggregatorsDao;
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
import com.finvendor.service.MarketDataAggregatorsService;

/**
 * @author rayulu vemula
 *
 */
public class MarketDataAggregatorsServiceImpl implements MarketDataAggregatorsService{

	private static Logger logger = Logger.getLogger(MarketDataAggregatorsServiceImpl.class);

	@Autowired
	private MarketDataAggregatorsDao marketDataAggregatorsDao;

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.service.MarketDataAggregatorsServiceImpl#getAllAssetClass(com.finvendor.model.AssetClass)
	 */
	@Override
	public List<AssetClass> getAllAssetClass() {
		logger.info("Method for getAllAssetClass---");
		return marketDataAggregatorsDao.getAllAssetClass();
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.service.MarketDataAggregatorsServiceImpl#getSecurityTypeByAssetClassId(com.finvendor.model.AssetClassSecurityMap)
	 */
	@Override
	public List<AssetClassSecurityMap> getSecurityTypeByAssetClassId(Integer assetId) {
		logger.info("Method for getAllAssetClass---");
		return marketDataAggregatorsDao.getSecurityTypeByAssetClassId(assetId);
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.service.MarketDataAggregatorsServiceImpl#getAllRegionClass(com.finvendor.model.Region)
	 */
	@Override
	public List<Region> getAllRegionClass() {
		logger.info("Method for getAllRegionClass---");
		return marketDataAggregatorsDao.getAllRegionClass();
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.service.MarketDataAggregatorsServiceImpl#getRegionCountryMapsRegionId(com.finvendor.model.RegionCountryMap)
	 */
	@Override
	public List<RegionCountryMap> getRegionCountryMapsRegionId(Integer regionId) {
		logger.info("Method for getRegionCountryMapsRegionId---");
		return marketDataAggregatorsDao.getRegionCountryMapsRegionId(regionId);
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.service.MarketDataAggregatorsServiceImpl#getCountryExchangeMapsByCountryId(com.finvendor.model.CountryExchangeMap)
	 */
	@Override
	public List<CountryExchangeMap> getCountryExchangeMapsByCountryId(
			Integer countryId) {
		logger.info("Method for getCountryExchangeMapsByCountryId---");
		return marketDataAggregatorsDao.getCountryExchangeMapsByCountryId(countryId);
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.service.MarketDataAggregatorsServiceImpl#getAllCountries(com.finvendor.model.Country)
	 */
	@Override
	public List<Country> getAllCountries() {
		logger.info("Method for getAllCountries---");
		return marketDataAggregatorsDao.getAllCountries();
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.service.MarketDataAggregatorsServiceImpl#getAllVendorSupports(com.finvendor.model.VendorSupport)
	 */
	@Override
	public List<Support> getAllVendorSupports() {
		logger.info("Method for getAllVendorSupports---");
		return marketDataAggregatorsDao.getAllVendorSupports();
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.service.MarketDataAggregatorsServiceImpl#getAllCostInfo(com.finvendor.model.Cost)
	 */
	@Override
	public List<Cost> getAllCostInfo() {
		logger.info("Method for getAllCostInfo---");
		return marketDataAggregatorsDao.getAllCostInfo();
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.service.MarketDataAggregatorsServiceImpl#getAllAwards(com.finvendor.model.Awards)
	 */
	@Override
	public List<Awards> getAllAwards() {
		logger.info("Method for getAllAwards---");
		return marketDataAggregatorsDao.getAllAwards();
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.service.MarketDataAggregatorsServiceImpl#getAllExchanges(com.finvendor.model.Exchange)
	 */
	@Override
	public List<Exchange> getAllExchanges() {
		logger.info("Method for getAllExchanges---");
		return marketDataAggregatorsDao.getAllExchanges();
	}
	
	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.service.MarketDataAggregatorsServiceImpl#getSingleAssetClassSearchResultInfo(com.finvendor.model.AssetClassDataDetails)
	 */
	@Override
	public List<AssetClassDataDetails> getSingleAssetClassSearchResultInfo(
			String assetclassId, List<String> securitytypeId, String dataattribute,
			List<String> regionList,  List<String> countryList,
			List<String> exchangeList) {
		logger.info("Method for getSingleAssetClassSearchResultInfo---");
		return marketDataAggregatorsDao.getSingleAssetClassSearchResultInfo(assetclassId,securitytypeId,dataattribute,
				regionList,countryList,exchangeList);
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.service.MarketDataAggregatorsServiceImpl#getSingleAssetClassSearchResultVendorInfo(com.finvendor.model.AssetClassDataDetails)
	 */
	@Override
	public List<AssetClassDataDetails> getSingleAssetClassSearchResultVendorInfo(
			String assetclassId, List<String> securitytypeList,
			String vendorregionofincorp,
			List<String> vendorcountryofincorpList,
			List<String> vendorprofilefreshnessList,
			List<String> vendoryearoperationList, String searchkeyword,
			List<String> vendorsupportregionList,
			List<String> vendorsupporttimeList, List<String> awardsList,
			List<String> acquisitioncostrangeList) {
		logger.info("Method for getSingleAssetClassSearchResultVendorInfo---");
		return marketDataAggregatorsDao.getSingleAssetClassSearchResultVendorInfo(assetclassId,securitytypeList,vendorregionofincorp,
				vendorcountryofincorpList,vendorprofilefreshnessList,vendoryearoperationList,
				searchkeyword,vendorsupportregionList,vendorsupporttimeList,awardsList,acquisitioncostrangeList);
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.service.MarketDataAggregatorsServiceImpl#getSingleAssetClassVendorDetails(com.finvendor.model.AssetClassDataDetails)
	 */
	@Override
	public List<AssetClassDataDetails> getSingleAssetClassVendorDetails(
			String assetclassId, List<String> securitytypeList,
			List<String> vendorregionofincorpList,
			List<String> vendorcountryofincorpList,
			String vendorprofilefreshness, String vendoryearoperation,
			String searchkeyword, String vendorsupportregion,
			String vendorsupporttime, String vendorawards,
			String vendorcostrange) {
		logger.info("Method for getSingleAssetClassVendorDetails---");
		return marketDataAggregatorsDao.getSingleAssetClassVendorDetails(assetclassId,securitytypeList,vendorregionofincorpList,vendorcountryofincorpList,
				vendorprofilefreshness,vendoryearoperation,searchkeyword,vendorsupportregion,vendorsupporttime,vendorawards,
				vendorcostrange);
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.service.MarketDataAggregatorsServiceImpl#getMultiAssetClassSearchResultInfo(com.finvendor.model.AssetClassDataDetails)
	 */
	@Override
	public List<AssetClassDataDetails> getMultiAssetClassSearchResultInfo(
			List<String> assetClassList, List<String> securityList) {
		logger.info("Method for getMultiAssetClassSearchResultInfo---");
		return marketDataAggregatorsDao.getMultiAssetClassSearchResultInfo(assetClassList,securityList);
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.service.MarketDataAggregatorsServiceImpl#getRegionNamesByName(com.finvendor.model.Region)
	 */
	@Override
	public List<Region> getRegionNamesByName(String regionName) {
		logger.info("Method for getRegionNamesByName---");
		return marketDataAggregatorsDao.getRegionNamesByName(regionName);
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.service.MarketDataAggregatorsServiceImpl#getAssetClassByName(com.finvendor.model.AssetClass)
	 */
	@Override
	public AssetClass getAssetClassByName(String assetType) {
		logger.info("Method for getAssetClassByName---");
		return marketDataAggregatorsDao.getAssetClassByName(assetType);
	}

	@Override
	public VendorOffering createOfferings(String id,VendorOffering vendorOffering) {
		// TODO Auto-generated method stub
		return marketDataAggregatorsDao.createOfferings(id, vendorOffering);
		
	}

	@Override
	public OfferingFiles addOfferingFiles(String id, OfferingFiles OfferingFiles) {
		// TODO Auto-generated method stub
		return marketDataAggregatorsDao.addOfferingFiles(id, OfferingFiles);
	}

	@Override
	public FileFields addFieldsToFile(String id,FileFields fileFields) {
		// TODO Auto-generated method stub
		return marketDataAggregatorsDao.addFieldsToFile(id, fileFields);
	}

	@Override
	public Set<VendorOffering> listOfferings(String id) {
		// TODO Auto-generated method stub
		return marketDataAggregatorsDao.listOfferings(id);
	}

	@Override
	public Set<OfferingFiles> listOfferingFiles(String id) {
		// TODO Auto-generated method stub
		return marketDataAggregatorsDao.listOfferingFiles(id);
	}

	@Override
	public Set<FileFields> listFieldsToFile(String id) {
		// TODO Auto-generated method stub
		return marketDataAggregatorsDao.listFieldsToFile(id);
	}

	@Override
	public VendorOffering deleteOfferings(String id) {
		VendorOffering deleteOfferings = null;
		deleteOfferings = marketDataAggregatorsDao.findOfferingById(id);
		marketDataAggregatorsDao.deleteOfferings(deleteOfferings);
		return deleteOfferings;
	}

	@Override
	public OfferingFiles deleteOfferingFiles(String id) {
		OfferingFiles deleteOfferingFiles = marketDataAggregatorsDao.deleteOfferingFiles(id);
		return deleteOfferingFiles;
	}

	@Override
	public FileFields deleteFieldsToFile(String id) {
		FileFields deleteFieldsToFile = marketDataAggregatorsDao.deleteFieldsToFile(id);
		return deleteFieldsToFile;
	}

	@Override
	public List<SecurityType> listSecurityType() {
		
		return marketDataAggregatorsDao.listSecurityType();
	}

	@Override
	public VendorOffering getVendorOfferingById(String id) {
		return marketDataAggregatorsDao.getVendorOfferingById(id);
	}

	@Override
	public Region getRegionById(String id) {
		// TODO Auto-generated method stub
		return marketDataAggregatorsDao.getRegionById(id);
	}

	@Override
	public Country getCountryById(String id) {
		// TODO Auto-generated method stub
		return marketDataAggregatorsDao.getCountryById(id);
	}

	@Override
	public Cost getCostById(String id) {
		// TODO Auto-generated method stub
		return marketDataAggregatorsDao.getCostById(id);
	}

	@Override
	public Exchange getExchangeById(String exchangeId) {
		// TODO Auto-generated method stub
		return marketDataAggregatorsDao.getExchangeById(exchangeId);
	}

	@Override
	public List<CompanySubType> getCompanySubTypeList() {
		return marketDataAggregatorsDao.getCompanySubTypeList();
	}

	@Override
	public Map<String, Object> getMultiAssetClassSearchResult(Map<Object, Object> searchData,MarketDataAggregatorsVendorSearchForm dataForm) {
		return marketDataAggregatorsDao.getMultiAssetClassSearchResult(searchData,dataForm);
	}
	
	@Override
	public List<FinancialAnalyticsApplicationVendorSearchForm> getFAMultiAssetClassSearchResult(Map<Object, Object> searchData, FinancialAnalyticsApplicationVendorSearchForm dataForm) {
		// TODO Auto-generated method stub
		return marketDataAggregatorsDao.getFAMultiAssetClassSearchResult(searchData,dataForm);
	}

	@Override
	public List<ResearchReportProvidersVendorSearchForm> getRRMultiAssetClassSearchResult(Map<Object, Object> searchData, ResearchReportProvidersVendorSearchForm dataForm) {
		// TODO Auto-generated method stub
		return marketDataAggregatorsDao.getRRMultiAssetClassSearchResult(searchData,dataForm);
	}

	@Override
	public List<TradingApplicationVendorSearchForm> getTAMultiAssetClassSearchResult(Map<Object, Object> searchData, TradingApplicationVendorSearchForm dataForm) {
		// TODO Auto-generated method stub
		return marketDataAggregatorsDao.getTAMultiAssetClassSearchResult(searchData,dataForm);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Object getModelObjectById(Class<?> type, Serializable id) {
		return marketDataAggregatorsDao.getModelObjectById(type, id);
	}

}
