package com.finvendor.api.marketdata.service;

import com.finvendor.api.marketdata.dao.MarketDataAggregatorsDaoImpl;
import com.finvendor.model.*;
import com.finvendor.modelpojo.staticpojo.FinancialAnalyticsApplicationVendorSearchForm;
import com.finvendor.modelpojo.staticpojo.MarketDataAggregatorsVendorSearchForm;
import com.finvendor.modelpojo.staticpojo.ResearchReportProvidersVendorSearchForm;
import com.finvendor.modelpojo.staticpojo.TradingApplicationVendorSearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class MarketDataAggregatorsServiceImpl {//implements MarketDataAggregatorsService{

	private static final Logger logger = LoggerFactory.getLogger(MarketDataAggregatorsServiceImpl.class.getName());

	@Autowired
	private MarketDataAggregatorsDaoImpl marketDataAggregatorsDao;

	
	public List<AssetClass> getAllAssetClass() {
		logger.info("Method for getAllAssetClass---");
		return marketDataAggregatorsDao.getAllAssetClass();
	}

	
	public List<AssetClassSecurityMap> getSecurityTypeByAssetClassId(Integer assetId) {
		logger.info("Method for getAllAssetClass---");
		return marketDataAggregatorsDao.getSecurityTypeByAssetClassId(assetId);
	}

	
	public List<Region> getAllRegionClass() {
		logger.info("Method for getAllRegionClass---");
		return marketDataAggregatorsDao.getAllRegionClass();
	}

	
	public List<RegionCountryMap> getRegionCountryMapsRegionId(Integer regionId) {
		logger.info("Method for getRegionCountryMapsRegionId---");
		return marketDataAggregatorsDao.getRegionCountryMapsRegionId(regionId);
	}

	
	public List<CountryExchangeMap> getCountryExchangeMapsByCountryId(
			Integer countryId) {
		logger.info("Method for getCountryExchangeMapsByCountryId---");
		return marketDataAggregatorsDao.getCountryExchangeMapsByCountryId(countryId);
	}

	
	public List<Country> getAllCountries() {
		logger.info("Method for getAllCountries---");
		return marketDataAggregatorsDao.getAllCountries();
	}

	
	public List<Support> getAllVendorSupports() {
		logger.info("Method for getAllVendorSupports---");
		return marketDataAggregatorsDao.getAllVendorSupports();
	}

	
	public List<Cost> getAllCostInfo() {
		logger.info("Method for getAllCostInfo---");
		return marketDataAggregatorsDao.getAllCostInfo();
	}

	
	public List<Awards> getAllAwards(String vendorId) {
		logger.info("Method for getAllAwards---");
		return marketDataAggregatorsDao.getAllAwards(vendorId);
	}

	
	public List<Exchange> getAllExchanges() {
		logger.info("Method for getAllExchanges---");
		return marketDataAggregatorsDao.getAllExchanges();
	}

	
	public List<AssetClassDataDetails> getSingleAssetClassSearchResultInfo(
			String assetclassId, List<String> securitytypeId, String dataattribute,
			List<String> regionList,  List<String> countryList,
			List<String> exchangeList) {
		logger.info("Method for getSingleAssetClassSearchResultInfo---");
		return marketDataAggregatorsDao.getSingleAssetClassSearchResultInfo(assetclassId,securitytypeId,dataattribute,
				regionList,countryList,exchangeList);
	}

	
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

	
	public List<AssetClassDataDetails> getMultiAssetClassSearchResultInfo(
			List<String> assetClassList, List<String> securityList) {
		logger.info("Method for getMultiAssetClassSearchResultInfo---");
		return marketDataAggregatorsDao.getMultiAssetClassSearchResultInfo(assetClassList,securityList);
	}

		
	public List<Region> getRegionNamesByName(String regionName) {
		logger.info("Method for getRegionNamesByName---");
		return marketDataAggregatorsDao.getRegionNamesByName(regionName);
	}

	
	public AssetClass getAssetClassByName(String assetType) {
		logger.info("Method for getAssetClassByName---");
		return marketDataAggregatorsDao.getAssetClassByName(assetType);
	}

	
	public VendorOffering createOfferings(String id,VendorOffering vendorOffering) {
		return marketDataAggregatorsDao.createOfferings(id, vendorOffering);

	}

	
	public OfferingFiles addOfferingFiles(String id, OfferingFiles OfferingFiles) {
		return marketDataAggregatorsDao.addOfferingFiles(id, OfferingFiles);
	}

	
	public FileFields addFieldsToFile(String id,FileFields fileFields) {
		return marketDataAggregatorsDao.addFieldsToFile(id, fileFields);
	}

	
	public Set<VendorOffering> listOfferings(String id) {
		return marketDataAggregatorsDao.listOfferings(id);
	}

	
	public Set<OfferingFiles> listOfferingFiles(String id) {
		return marketDataAggregatorsDao.listOfferingFiles(id);
	}

	
	public Set<FileFields> listFieldsToFile(String id) {
		return marketDataAggregatorsDao.listFieldsToFile(id);
	}

	
	public VendorOffering deleteOfferings(String id) {
		VendorOffering deleteOfferings = null;
		deleteOfferings = marketDataAggregatorsDao.findOfferingById(id);
		marketDataAggregatorsDao.deleteOfferings(deleteOfferings);
		return deleteOfferings;
	}

	
	public OfferingFiles deleteOfferingFiles(String id) {
		OfferingFiles deleteOfferingFiles = marketDataAggregatorsDao.deleteOfferingFiles(id);
		return deleteOfferingFiles;
	}

	
	public FileFields deleteFieldsToFile(String id) {
		FileFields deleteFieldsToFile = marketDataAggregatorsDao.deleteFieldsToFile(id);
		return deleteFieldsToFile;
	}

	
	public List<SecurityType> listSecurityType() {

		return marketDataAggregatorsDao.listSecurityType();
	}

	
	public VendorOffering getVendorOfferingById(String id) {
		return marketDataAggregatorsDao.getVendorOfferingById(id);
	}

	
	public Region getRegionById(String id) {
		return marketDataAggregatorsDao.getRegionById(id);
	}

	
	public Country getCountryById(String id) {
		return marketDataAggregatorsDao.getCountryById(id);
	}

	
	public Cost getCostById(String id) {
		return marketDataAggregatorsDao.getCostById(id);
	}

	
	public Exchange getExchangeById(String exchangeId) {
		return marketDataAggregatorsDao.getExchangeById(exchangeId);
	}

	
	public List<CompanySubType> getCompanySubTypeList() {
		return marketDataAggregatorsDao.getCompanySubTypeList();
	}

	
	public Map<String, Object> getMultiAssetClassSearchResult(Map<Object, Object> searchData,
			MarketDataAggregatorsVendorSearchForm dataForm) {
		return marketDataAggregatorsDao.getMultiAssetClassSearchResult(searchData, dataForm);
	}

	
	@Transactional
	public Map<String, Object> getResearchReportVendorSearchResult(
			Map<Object, Object> searchData, MarketDataAggregatorsVendorSearchForm dataForm) {
		return marketDataAggregatorsDao.getResearchReportVendorSearchResult(searchData, dataForm);
	}

	
	@Transactional
	public Map<String, Object> getTradingApplicationVendorSearchResult(
			Map<Object, Object> searchData, MarketDataAggregatorsVendorSearchForm dataForm) {
		return marketDataAggregatorsDao.getTradingApplicationVendorSearchResult(searchData, dataForm);
	}

	
	public List<FinancialAnalyticsApplicationVendorSearchForm> getFAMultiAssetClassSearchResult(
			Map<Object, Object> searchData, FinancialAnalyticsApplicationVendorSearchForm dataForm) {
		return marketDataAggregatorsDao.getFAMultiAssetClassSearchResult(searchData,dataForm);
	}

	
	public List<ResearchReportProvidersVendorSearchForm> getRRMultiAssetClassSearchResult(
			Map<Object, Object> searchData, ResearchReportProvidersVendorSearchForm dataForm) {
		return marketDataAggregatorsDao.getRRMultiAssetClassSearchResult(searchData,dataForm);
	}

	
	public List<TradingApplicationVendorSearchForm> getTAMultiAssetClassSearchResult(
			Map<Object, Object> searchData, TradingApplicationVendorSearchForm dataForm) {
		return marketDataAggregatorsDao.getTAMultiAssetClassSearchResult(searchData,dataForm);
	}

	
	@Transactional(readOnly=true)
	public Object getModelObjectById(Class<?> type, Serializable id) {
		return marketDataAggregatorsDao.getModelObjectById(type, id);
	}

}
