/**
 * 
 */
package com.finvendor.dao;

import java.util.List;

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
import com.finvendor.model.Support;

/**
 * @author rayulu vemula
 *
 */
public interface MarketDataAggregatorsDAO {

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get asset class
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDAO#getAllAssetClass()
	 */
	List<AssetClass> getAllAssetClass();

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get all corresponding security types
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDAO#getSecurityTypeByAssetClassId(assetId)
	 */
	List<AssetClassSecurityMap> getSecurityTypeByAssetClassId(Integer assetId);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get all regions
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDAO#getAllRegionClass()
	 */
	List<Region> getAllRegionClass();

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get region country maps
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDAO#getRegionCountryMapsRegionId(regionId)
	 */
	List<RegionCountryMap> getRegionCountryMapsRegionId(Integer regionId);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get country exchange maps
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDAO#getCountryExchangeMapsByCountryId(countryId)
	 */
	List<CountryExchangeMap> getCountryExchangeMapsByCountryId(Integer countryId);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get countries
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDAO#getAllCountries()
	 */
	List<Country> getAllCountries();

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get vendor support
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDAO#getAllVendorSupports()
	 */
	List<Support> getAllVendorSupports();

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get all costs
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDAO#getAllCostInfo()
	 */
	List<Cost> getAllCostInfo();

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get all awards
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDAO#getAllAwards()
	 */
	List<Awards> getAllAwards();

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get all awards
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDAO#getAllExchanges()
	 */
	List<Exchange> getAllExchanges();
	
	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get region names
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDAO#getRegionNamesByName(regionName)
	 */
	List<Region> getRegionNamesByName(String regionName);
	
	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get Single Asset Class Search Result
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDAO#getSingleAssetClassSearchResultInfo()
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
	 * @see com.finvendor.dao.MarketDataAggregatorsDAO#getSingleAssetClassSearchResultVendorInfo()
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
	 * @see com.finvendor.dao.MarketDataAggregatorsDAO#getSingleAssetClassVendorDetails()
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
	 * @see com.finvendor.dao.MarketDataAggregatorsDAO#getMultiAssetClassSearchResultInfo()
	 */
	List<AssetClassDataDetails> getMultiAssetClassSearchResultInfo(
			List<String> assetClassList, List<String> securityList);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get Asset Class by passing name 
	 * 
	 * @return Object with success or failure messages
	 * @see com.finvendor.dao.MarketDataAggregatorsDAO#getAssetClassByName(assetType)
	 */
	AssetClass getAssetClassByName(String assetType);


	

}
