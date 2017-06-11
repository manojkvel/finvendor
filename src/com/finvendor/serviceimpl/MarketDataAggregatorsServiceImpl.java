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

public class MarketDataAggregatorsServiceImpl implements MarketDataAggregatorsService{

	private static Logger logger = Logger.getLogger(MarketDataAggregatorsServiceImpl.class);

	@Autowired
	private MarketDataAggregatorsDao marketDataAggregatorsDao;

	@Override
	public List<AssetClass> getAllAssetClass() {
		logger.info("Method for getAllAssetClass---");
		return marketDataAggregatorsDao.getAllAssetClass();
	}

	@Override
	public List<AssetClassSecurityMap> getSecurityTypeByAssetClassId(Integer assetId) {
		logger.info("Method for getAllAssetClass---");
		return marketDataAggregatorsDao.getSecurityTypeByAssetClassId(assetId);
	}

	@Override
	public List<Region> getAllRegionClass() {
		logger.info("Method for getAllRegionClass---");
		return marketDataAggregatorsDao.getAllRegionClass();
	}

	@Override
	public List<RegionCountryMap> getRegionCountryMapsRegionId(Integer regionId) {
		logger.info("Method for getRegionCountryMapsRegionId---");
		return marketDataAggregatorsDao.getRegionCountryMapsRegionId(regionId);
	}

	@Override
	public List<CountryExchangeMap> getCountryExchangeMapsByCountryId(
			Integer countryId) {
		logger.info("Method for getCountryExchangeMapsByCountryId---");
		return marketDataAggregatorsDao.getCountryExchangeMapsByCountryId(countryId);
	}

	@Override
	public List<Country> getAllCountries() {
		logger.info("Method for getAllCountries---");
		return marketDataAggregatorsDao.getAllCountries();
	}

	@Override
	public List<Support> getAllVendorSupports() {
		logger.info("Method for getAllVendorSupports---");
		return marketDataAggregatorsDao.getAllVendorSupports();
	}

	@Override
	public List<Cost> getAllCostInfo() {
		logger.info("Method for getAllCostInfo---");
		return marketDataAggregatorsDao.getAllCostInfo();
	}

	@Override
	public List<Awards> getAllAwards(String vendorId) {
		logger.info("Method for getAllAwards---");
		return marketDataAggregatorsDao.getAllAwards(vendorId);
	}

	@Override
	public List<Exchange> getAllExchanges() {
		logger.info("Method for getAllExchanges---");
		return marketDataAggregatorsDao.getAllExchanges();
	}
	
	@Override
	public List<AssetClassDataDetails> getSingleAssetClassSearchResultInfo(
			String assetclassId, List<String> securitytypeId, String dataattribute,
			List<String> regionList,  List<String> countryList,
			List<String> exchangeList) {
		logger.info("Method for getSingleAssetClassSearchResultInfo---");
		return marketDataAggregatorsDao.getSingleAssetClassSearchResultInfo(assetclassId,securitytypeId,dataattribute,
				regionList,countryList,exchangeList);
	}

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

	@Override
	public List<AssetClassDataDetails> getMultiAssetClassSearchResultInfo(
			List<String> assetClassList, List<String> securityList) {
		logger.info("Method for getMultiAssetClassSearchResultInfo---");
		return marketDataAggregatorsDao.getMultiAssetClassSearchResultInfo(assetClassList,securityList);
	}

		@Override
	public List<Region> getRegionNamesByName(String regionName) {
		logger.info("Method for getRegionNamesByName---");
		return marketDataAggregatorsDao.getRegionNamesByName(regionName);
	}

	@Override
	public AssetClass getAssetClassByName(String assetType) {
		logger.info("Method for getAssetClassByName---");
		return marketDataAggregatorsDao.getAssetClassByName(assetType);
	}

	@Override
	public VendorOffering createOfferings(String id,VendorOffering vendorOffering) {
		return marketDataAggregatorsDao.createOfferings(id, vendorOffering);
		
	}

	@Override
	public OfferingFiles addOfferingFiles(String id, OfferingFiles OfferingFiles) {
		return marketDataAggregatorsDao.addOfferingFiles(id, OfferingFiles);
	}

	@Override
	public FileFields addFieldsToFile(String id,FileFields fileFields) {
		return marketDataAggregatorsDao.addFieldsToFile(id, fileFields);
	}

	@Override
	public Set<VendorOffering> listOfferings(String id) {
		return marketDataAggregatorsDao.listOfferings(id);
	}

	@Override
	public Set<OfferingFiles> listOfferingFiles(String id) {
		return marketDataAggregatorsDao.listOfferingFiles(id);
	}

	@Override
	public Set<FileFields> listFieldsToFile(String id) {
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
		return marketDataAggregatorsDao.getRegionById(id);
	}

	@Override
	public Country getCountryById(String id) {
		return marketDataAggregatorsDao.getCountryById(id);
	}

	@Override
	public Cost getCostById(String id) {
		return marketDataAggregatorsDao.getCostById(id);
	}

	@Override
	public Exchange getExchangeById(String exchangeId) {
		return marketDataAggregatorsDao.getExchangeById(exchangeId);
	}

	@Override
	public List<CompanySubType> getCompanySubTypeList() {
		return marketDataAggregatorsDao.getCompanySubTypeList();
	}

	@Override
	public Map<String, Object> getMultiAssetClassSearchResult(Map<Object, Object> searchData, 
			MarketDataAggregatorsVendorSearchForm dataForm) {
		return marketDataAggregatorsDao.getMultiAssetClassSearchResult(searchData, dataForm);
	}
	
	@Override
	@Transactional
	public Map<String, Object> getResearchReportVendorSearchResult(
			Map<Object, Object> searchData, MarketDataAggregatorsVendorSearchForm dataForm) {
		return marketDataAggregatorsDao.getResearchReportVendorSearchResult(searchData, dataForm);
	}
	
	@Override
	@Transactional
	public Map<String, Object> getTradingApplicationVendorSearchResult(
			Map<Object, Object> searchData, MarketDataAggregatorsVendorSearchForm dataForm) {
		return marketDataAggregatorsDao.getTradingApplicationVendorSearchResult(searchData, dataForm);
	}
	
	@Override
	public List<FinancialAnalyticsApplicationVendorSearchForm> getFAMultiAssetClassSearchResult(
			Map<Object, Object> searchData, FinancialAnalyticsApplicationVendorSearchForm dataForm) {
		return marketDataAggregatorsDao.getFAMultiAssetClassSearchResult(searchData,dataForm);
	}

	@Override
	public List<ResearchReportProvidersVendorSearchForm> getRRMultiAssetClassSearchResult(
			Map<Object, Object> searchData, ResearchReportProvidersVendorSearchForm dataForm) {
		return marketDataAggregatorsDao.getRRMultiAssetClassSearchResult(searchData,dataForm);
	}

	@Override
	public List<TradingApplicationVendorSearchForm> getTAMultiAssetClassSearchResult(
			Map<Object, Object> searchData, TradingApplicationVendorSearchForm dataForm) {
		return marketDataAggregatorsDao.getTAMultiAssetClassSearchResult(searchData,dataForm);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Object getModelObjectById(Class<?> type, Serializable id) {
		return marketDataAggregatorsDao.getModelObjectById(type, id);
	}

}
