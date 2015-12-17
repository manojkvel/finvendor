/**
 * 
 */
package com.finvendor.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.finvendor.dao.VendorDAO;
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
import com.finvendor.service.VendorService;

/**
 * @author rayulu vemula
 *
 */
public class VendorServiceImpl implements VendorService{

	private static Logger logger = Logger.getLogger(VendorServiceImpl.class);
	
	@Autowired
	private VendorDAO vendorDAO;


	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.VendorServiceImpl#saveVendorInfo(com.finvendor.model.Vendor)
	 */
	@Override
	public void saveVendorInfo(Vendor vendor) {
		logger.info("saveVendorInfo method---:");
		vendorDAO.saveVendorInfo(vendor);
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.VendorServiceImpl#getVendorInfoByEmail(com.finvendor.model.Vendor)
	 */
	@Override
	public Vendor getVendorInfoByEmail(String email) {
		logger.info("getVendorInfoByEmail method---:");
		return vendorDAO.getVendorInfoByEmail(email);
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.VendorServiceImpl#updateVendorPersonalInfoTab(com.finvendor.model.Vendor)
	 */
	@Override
	public void updateVendorPersonalInfoTab(Vendor vendor, String username) {
		logger.info("updateVendorPersonalInfoTab method---:");
		vendorDAO.updateVendorPersonalInfoTab(vendor,username);
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.VendorServiceImpl#getVendorDetails(com.finvendor.model.Vendor)
	 */
	/*@Override
	public Vendor getVendorDetails(String appUser) {
		logger.info("getVendorDetails method---:");
		return vendorDAO.getVendorDetails(appUser);
	}*/

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.VendorServiceImpl#getAssetClassDetails(com.finvendor.model.AssetClass)
	 */
	@Override
	public AssetClass getAssetClassDetails(String asset_class) {
		logger.info("getAssetClassDetails method---:");
		return vendorDAO.getAssetClassDetails(asset_class);
	}
	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.VendorServiceImpl#getSecurityTypes(com.finvendor.model.SecurityType)
	 */
	@Override
	public SecurityType getSecurityTypes(String securities) {
		logger.info("getSecurityTypes method---:");
		return vendorDAO.getSecurityTypes(securities);
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.VendorServiceImpl#updateVendorOfferingDetails(com.finvendor.model.VendorOffering)
	 */
	@Override
	public void updateVendorOfferingDetails(VendorOffering vendorOffering) {
		logger.info("updateVendorOfferingDetails method---:");
		 vendorDAO.updateVendorOfferingDetails(vendorOffering);
		
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.VendorServiceImpl#getRegionsByName(com.finvendor.model.Region)
	 */
	@Override
	public Region getRegionsByName(String regionsName) {
		logger.info("getRegionsByName method---:");
		return vendorDAO.getRegionsByName(regionsName);
	}
	
	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.VendorServiceImpl#getCountryByName(com.finvendor.model.Country)
	 */
	@Override
	public Country getCountryByName(String countryName) {
		logger.info("getCountryByName method---:");
		return vendorDAO.getCountryByName(countryName);
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.VendorServiceImpl#getExchangesByName(com.finvendor.model.Exchange)
	 */
	@Override
	public Exchange getExchangesByName(String exchangeName) {
		logger.info("getExchangesByName method---:");
		return vendorDAO.getExchangesByName(exchangeName);
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.VendorServiceImpl#updateVendorRegionCountryExchangeInfos(com.finvendor.model.VendorRegionCountryExchangeMap)
	 */
	@Override
	public void updateVendorRegionCountryExchangeInfos(
			VendorRegionCountryExchangeMap vendorRegionCountryExchangeMap) {
		logger.info("updateVendorRegionCountryExchangeInfos method---:");
		 vendorDAO.updateVendorRegionCountryExchangeInfos(vendorRegionCountryExchangeMap);
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.VendorServiceImpl#saveAwardDetails(com.finvendor.model.Awards)
	 */
	@Override
	public Awards saveAwardDetails(Awards awards) {
		logger.info("saveAwardDetails method---:");
		return vendorDAO.saveAwardDetails(awards);
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.VendorServiceImpl#updateVendorOfferingDetails(com.finvendor.model.VendorAwardsMap)
	 */
	@Override
	public void updateVendorAwardDetails(VendorAwardsMap vendorAwardsMap) {
		logger.info("updateVendorAwardDetails method---:");
		 vendorDAO.updateVendorAwardDetails(vendorAwardsMap);
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.VendorServiceImpl#getCostInfo(com.finvendor.model.Cost)
	 */
	@Override
	public Cost getCostInfo(String costNames) {
		logger.info("getCostInfo method---:");
		return vendorDAO.getCostInfo(costNames);
	}
	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.VendorServiceImpl#updateVendorSolutionDetails(com.finvendor.model.VendorSolution)
	 */
	@Override
	public VendorSolution updateVendorSolutionDetails(
			VendorSolution vendorSolution) {
		logger.info("updateVendorSolutionDetails method---:");
		return vendorDAO.updateVendorSolutionDetails(vendorSolution);
	}


	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.VendorServiceImpl#getSupportInfo(com.finvendor.model.Support)
	 */
	@Override
	public Support getSupportInfo(String supportname) {
		logger.info("getSupportInfo method---:");
		return vendorDAO.getSupportInfo(supportname);
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.VendorServiceImpl#updateVendorSupportInfo(com.finvendor.model.VendorSupport)
	 */
	@Override
	public void updateVendorSupportInfo(VendorSupport vendorSupport) {
		logger.info("updateVendorSupportInfo method---:");
		 vendorDAO.updateVendorSupportInfo(vendorSupport);
	}

	/** --------------------------------------------------------------------- */
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.finvendor.dao.VendorServiceImpl#getVendorOfferingDetails(com.finvendor.model.VendorOffering)
	 */
	@Override
	public List<VendorOffering> getVendorOfferingDetails(String id) {
		logger.info("getVendorOfferingDetails method---:");
		 return vendorDAO.getVendorOfferingDetails(id);
	}
}
