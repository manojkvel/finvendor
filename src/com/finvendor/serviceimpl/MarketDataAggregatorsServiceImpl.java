/**
 * 
 */
package com.finvendor.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.finvendor.dao.MarketDataAggregatorsDAO;
import com.finvendor.model.AssetClass;
import com.finvendor.model.AssetClassDataDetails;
import com.finvendor.model.AssetClassSecurityMap;
import com.finvendor.model.Awards;
import com.finvendor.model.Cost;
import com.finvendor.model.Country;
import com.finvendor.model.CountryExchangeMap;
import com.finvendor.model.Exchange;
import com.finvendor.model.Region;
import com.finvendor.model.RegionCountryMap;
import com.finvendor.model.SecurityType;
import com.finvendor.model.Support;
import com.finvendor.model.Vendor;
import com.finvendor.service.MarketDataAggregatorsService;

/**
 * @author rayulu vemula
 *
 */
public class MarketDataAggregatorsServiceImpl implements MarketDataAggregatorsService{

	private static Logger logger = Logger.getLogger(MarketDataAggregatorsServiceImpl.class);

	@Autowired
	private MarketDataAggregatorsDAO marketDataAggregatorsDAO;

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.service.MarketDataAggregatorsServiceImpl#getAllAssetClass(com.finvendor.model.AssetClass)
	 */
	@Override
	public List<AssetClass> getAllAssetClass() {
		logger.info("Method for getAllAssetClass---");
		return marketDataAggregatorsDAO.getAllAssetClass();
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
		return marketDataAggregatorsDAO.getSecurityTypeByAssetClassId(assetId);
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
		return marketDataAggregatorsDAO.getAllRegionClass();
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
		return marketDataAggregatorsDAO.getRegionCountryMapsRegionId(regionId);
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
		return marketDataAggregatorsDAO.getCountryExchangeMapsByCountryId(countryId);
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
		return marketDataAggregatorsDAO.getAllCountries();
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
		return marketDataAggregatorsDAO.getAllVendorSupports();
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
		return marketDataAggregatorsDAO.getAllCostInfo();
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
		return marketDataAggregatorsDAO.getAllAwards();
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
		return marketDataAggregatorsDAO.getAllExchanges();
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
		return marketDataAggregatorsDAO.getSingleAssetClassSearchResultInfo(assetclassId,securitytypeId,dataattribute,
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
		return marketDataAggregatorsDAO.getSingleAssetClassSearchResultVendorInfo(assetclassId,securitytypeList,vendorregionofincorp,
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
		return marketDataAggregatorsDAO.getSingleAssetClassVendorDetails(assetclassId,securitytypeList,vendorregionofincorpList,vendorcountryofincorpList,
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
		return marketDataAggregatorsDAO.getMultiAssetClassSearchResultInfo(assetClassList,securityList);
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
		return marketDataAggregatorsDAO.getRegionNamesByName(regionName);
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
		return marketDataAggregatorsDAO.getAssetClassByName(assetType);
	}


}
