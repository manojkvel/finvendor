/**
 * 
 */
package com.finvendor.dao;

import java.util.List;

import com.finvendor.model.AssetClass;
import com.finvendor.model.Awards;
import com.finvendor.model.Cost;
import com.finvendor.model.Country;
import com.finvendor.model.Exchange;
import com.finvendor.model.Region;
import com.finvendor.model.SecurityType;
import com.finvendor.model.Support;
import com.finvendor.model.Vendor;
import com.finvendor.model.VendorAwardsMap;
import com.finvendor.model.VendorOffering;
import com.finvendor.model.VendorRegionCountryExchangeMap;
import com.finvendor.model.VendorSolution;
import com.finvendor.model.VendorSupport;

/**
 * @author rayulu vemula
 *
 */
public interface VendorDAO {

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to save vendor info
	 * 
	 * @return  
	 * @see com.finvendor.dao.VendorDAO#saveVendorInfo()
	 */
	void saveVendorInfo(Vendor vendor);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get vendor info
	 * 
	 * @return  
	 * @see com.finvendor.dao.VendorDAO#getVendorInfoByEmail()
	 */
	Vendor getVendorInfoByEmail(String email);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to update vendor personal info
	 * 
	 * @return  
	 * @see com.finvendor.dao.VendorDAO#updateVendorPersonalInfoTab()
	 */
	void updateVendorPersonalInfoTab(Vendor vendor, String username);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get vendor details
	 * 
	 * @return  
	 * @see com.finvendor.dao.VendorDAO#getVendorDetails()
	 */
	//Vendor getVendorDetails(String appUser);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get asset class details
	 * 
	 * @return  
	 * @see com.finvendor.dao.VendorDAO#getAssetClassDetails()
	 */
	AssetClass getAssetClassDetails(String asset_class);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get asset class details
	 * 
	 * @return  
	 * @see com.finvendor.dao.VendorDAO#getSecurityTypes()
	 */
	SecurityType getSecurityTypes(String securities);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get asset class details
	 * 
	 * @see com.finvendor.dao.VendorDAO#updateVendorOfferingDetails()
	 */
	void updateVendorOfferingDetails(VendorOffering vendorOffering);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get region details
	 * 
	 * @return  
	 * @see com.finvendor.dao.VendorDAO#getRegionsByName()
	 */
	Region getRegionsByName(String regionsName);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get  country details
	 * 
	 * @return  
	 * @see com.finvendor.dao.VendorDAO#getCountryByName()
	 */
	Country getCountryByName(String countryName);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get exchange details
	 * 
	 * @return  
	 * @see com.finvendor.dao.VendorDAO#getExchangesByName()
	 */
	Exchange getExchangesByName(String exchangeName);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to update vendor region country exchange map
	 * 
	 * @return  
	 * @see com.finvendor.dao.VendorDAO#updateVendorRegionCountryExchangeInfos()
	 */
	void updateVendorRegionCountryExchangeInfos(
			VendorRegionCountryExchangeMap vendorRegionCountryExchangeMap);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to save award details
	 * 
	 * @return  
	 * @see com.finvendor.dao.VendorDAO#saveAwardDetails()
	 */
	Awards saveAwardDetails(Awards awards);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to update vendor award details
	 * 
	 * @return  
	 * @see com.finvendor.dao.VendorDAO#updateVendorAwardDetails()
	 */
	void updateVendorAwardDetails(VendorAwardsMap vendorAwardsMap);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get cost details
	 * 
	 * @return  
	 * @see com.finvendor.dao.VendorDAO#getCostInfo()
	 */
	Cost getCostInfo(String costNames);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to update vendor solution details
	 * 
	 * @return  
	 * @see com.finvendor.dao.VendorDAO#updateVendorSolutionDetails()
	 */
	VendorSolution updateVendorSolutionDetails(VendorSolution vendorSolution);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to get support details
	 * 
	 * @return  
	 * @see com.finvendor.dao.VendorDAO#getSupportInfo()
	 */
	Support getSupportInfo(String supportname);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to update vendor support details
	 * 
	 * @return  
	 * @see com.finvendor.dao.VendorDAO#updateVendorSupportInfo()
	 */
	void updateVendorSupportInfo(VendorSupport vendorSupport);

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc) Method to update vendor offering  details
	 * 
	 * @return  
	 * @see com.finvendor.dao.VendorDAO#getVendorOfferingDetails()
	 */
	List<VendorOffering> getVendorOfferingDetails(String id);
 }
