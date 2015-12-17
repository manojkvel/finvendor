/**
 * 
 */
package com.finvendor.service;

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
public interface MarketDataAggregatorsService {
	
	/** --------------------------------------------------------------------- */
	/**
	 * Method to get all asset class values
	 * 
	 * @param 
	 * @return 
	 */
	List<AssetClass> getAllAssetClass();

	/** --------------------------------------------------------------------- */
	/**
	 * Method to get all security types
	 * 
	 * @param assetId
	 * @return 
	 */
	List<AssetClassSecurityMap> getSecurityTypeByAssetClassId(Integer assetId);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to get all regions
	 * 
	 * @param 
	 * @return 
	 */
	List<Region> getAllRegionClass();

	/** --------------------------------------------------------------------- */
	/**
	 * Method to get all region country maps
	 * 
	 * @param 
	 * @return 
	 */
	List<RegionCountryMap> getRegionCountryMapsRegionId(Integer regionId);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to get all country exchange maps
	 * 
	 * @param 
	 * @return 
	 */
	List<CountryExchangeMap> getCountryExchangeMapsByCountryId(Integer countryId);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to get all countries
	 * 
	 * @param 
	 * @return 
	 */
	List<Country> getAllCountries();

	/** --------------------------------------------------------------------- */
	/**
	 * Method to get all support
	 * 
	 * @param 
	 * @return 
	 */
	List<Support> getAllVendorSupports();

	/** --------------------------------------------------------------------- */
	/**
	 * Method to get all cost
	 * 
	 * @param 
	 * @return 
	 */
	List<Cost> getAllCostInfo();

	/** --------------------------------------------------------------------- */
	/**
	 * Method to get all awards
	 * 
	 * @param 
	 * @return 
	 */
	List<Awards> getAllAwards();
	/** --------------------------------------------------------------------- */
	/**
	 * Method to get all exchanges
	 * 
	 * @param 
	 * @return 
	 */
	List<Exchange> getAllExchanges();

	/** --------------------------------------------------------------------- */
	/**
	 * Method to get regionNames
	 * 
	 * @param 
	 * @return 
	 */
	List<Region> getRegionNamesByName(String regionName);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to get Single Asset Class Search
	 * 
	 * @param 
	 * @return 
	 */
	List<AssetClassDataDetails> getSingleAssetClassSearchResultInfo(String assetclassId,
			List<String> securitytypeId, String dataattribute,
			List<String> regionList, List<String> countryList,
			List<String> exchangesList);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to get Single Asset Class Search for Vendor details
	 * 
	 * @param 
	 * @return 
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
	 * Method to get Single Asset Class Search for Vendor details
	 * 
	 * @param 
	 * @return 
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
	 * Method to get Multi Asset Class Search for Vendor details
	 * 
	 * @param 
	 * @return 
	 */
	List<AssetClassDataDetails> getMultiAssetClassSearchResultInfo(
			List<String> assetClassList, List<String> securityList);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to get Asset Class by passing name
	 * 
	 * @param 
	 * @return 
	 */
	AssetClass getAssetClassByName(String assetType);



}
