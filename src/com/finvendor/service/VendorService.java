/**
 * 
 */
package com.finvendor.service;

import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

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
@Service
public interface VendorService {

	/** --------------------------------------------------------------------- */
	/**
	 * Method to save vendor info 
	 * 
	 * @param vendor
	 * @return 
	 */
	void saveVendorInfo(Vendor vendor);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to get vendor info 
	 * 
	 * @param email
	 * @return 
	 */
	Vendor getVendorInfoByEmail(String email);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to update vendor personal info tab 
	 * 
	 * @param vendor
	 * @param username
	 * @return 
	 */
	void updateVendorPersonalInfoTab(Vendor vendor, String username);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to get vendor details
	 * 
	 * @param username
	 * @return 
	 */
	//Vendor getVendorDetails(String username);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to get Asset Class Details
	 * 
	 * @param asset_class
	 * @return 
	 */
	AssetClass getAssetClassDetails(String asset_class);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to get security type Details
	 * 
	 * @param securities
	 * @return 
	 */
	SecurityType getSecurityTypes(String securities);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to update vendor offering details
	 * 
	 * @param vendorOffering
	 * @return 
	 */
	void updateVendorOfferingDetails(VendorOffering vendorOffering);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to get regions by name
	 * 
	 * @param regionsName
	 * @return 
	 */
	Region getRegionsByName(String regionsName);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to get countries by name
	 * 
	 * @param countryName
	 * @return 
	 */
	Country getCountryByName(String countryName);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to get exchange by name
	 * 
	 * @param exchangeName
	 * @return 
	 */
	Exchange getExchangesByName(String exchangeName);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to update vendor region country exchange map infos
	 * 
	 * @param vendorRegionCountryExchangeMap
	 * @return 
	 */
	void updateVendorRegionCountryExchangeInfos(
			VendorRegionCountryExchangeMap vendorRegionCountryExchangeMap);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to save award details
	 * 
	 * @param awards
	 * @return 
	 */
	Awards saveAwardDetails(Awards awards);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to update vendor award map details
	 * 
	 * @param vendorAwardsMap
	 * @return 
	 */
	void updateVendorAwardDetails(VendorAwardsMap vendorAwardsMap);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to get cost details
	 * 
	 * @param costNames
	 * @return 
	 */
	Cost getCostInfo(String costNames);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to update vendor solution details
	 * 
	 * @param vendorSolution
	 * @return 
	 */
	VendorSolution updateVendorSolutionDetails(VendorSolution vendorSolution);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to get support details
	 * 
	 * @param supportname
	 * @return 
	 */
	Support getSupportInfo(String supportname);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to update vendor support info
	 * 
	 * @param vendorSupport
	 * @return 
	 */
	void updateVendorSupportInfo(VendorSupport vendorSupport);

	/** --------------------------------------------------------------------- */
	/**
	 * Method to get vendor offering details
	 * 
	 * @param id
	 * @return 
	 */
	List<VendorOffering> getVendorOfferingDetails(String id);

}
