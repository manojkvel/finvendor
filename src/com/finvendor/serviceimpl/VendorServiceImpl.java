/**
 * 
 */
package com.finvendor.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import com.finvendor.model.SolutionTypes;
import com.finvendor.model.Solutions;
import com.finvendor.model.Support;
import com.finvendor.model.Vendor;
import com.finvendor.model.VendorAnalystProfile;
import com.finvendor.model.VendorAnalyticsSoftwareDetails;
import com.finvendor.model.VendorAnalyticsfeaturesSupported;
import com.finvendor.model.VendorAwardsMap;
import com.finvendor.model.VendorDataCoverage;
import com.finvendor.model.VendorDistribution;
import com.finvendor.model.VendorOffering;
import com.finvendor.model.VendorRegionCountryExchangeMap;
import com.finvendor.model.VendorResearchCoverage;
import com.finvendor.model.VendorResearchDetails;
import com.finvendor.model.VendorSolution;
import com.finvendor.model.VendorSupport;
import com.finvendor.model.VendorTradingCapabilitiesSupported;
import com.finvendor.model.VendorTradingSoftwareDetails;
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

	
	@Override
	public Vendor getVendorInfoByUserName(String userName) {
		logger.info("getVendorInfoByuserName method---:");
		return vendorDAO.getVendorInfoByUserName(userName);
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
	
	@Override
	public Solutions getSolutionsInfo(String id) {
		logger.info("getSolutionsInfo method---:");
		 return vendorDAO.getSolutionsInfo(id);
	}

	@Override
	public List<Solutions> listVednorSolution(String id) {
		
		return vendorDAO.listVednorSolution(id);
	}

	@Override
	public List<Solutions> deleteVendorSolution(String id) {
		vendorDAO.deleteVendorSolution(id);
		return null;
	}

	@Override
	public SolutionTypes getSolutionTypes(String solutionName) {
		SolutionTypes solutionTypes = vendorDAO.getSolutionTypes(solutionName);
		return solutionTypes;
	}

	@Override
	public Solutions addSolutionsInfo(Solutions solutions) {
		return vendorDAO.addSolutionsInfo(solutions);
	}

	@Override
	public List<Solutions> getSolutionsBasedOnOfferingTypes(String offeringName) {
		
		SolutionTypes solutionTypes = vendorDAO.getSolutionTypes(offeringName);
		Set<Solutions> solutions = solutionTypes.getSolutions();
		// List<Solutions> solutionsBasedOnOfferingTypes = vendorDAO.getSolutionsBasedOnOfferingTypes(solutionTypes);
		return new ArrayList<Solutions>(solutions);
	}

	@Override
	public String addVendorDataCoverage(VendorDataCoverage vendorDataCoverage) {
		return vendorDAO.addVendorDataCoverage(vendorDataCoverage);
	}

	@Override
	public List<VendorDataCoverage> listVendorDataCoverage(String id) {
		List<VendorDataCoverage> listVendorDataCoverage = vendorDAO.listVendorDataCoverage(id); 
		return listVendorDataCoverage;
	}

	@Override
	public String addVendorDistribution(VendorDistribution vendorDistribution) {
		return vendorDAO.addVendorDistribution(vendorDistribution);	
	}

	@Override
	public List<VendorDistribution> listVendorDistribution(String id) {
		List<VendorDistribution> listVendorDistribution = vendorDAO.listVendorDistribution(id);
		return listVendorDistribution;
	}

	@Override
	public void deleteVendorDataCoverage(String selectedId) {
		vendorDAO.deleteVendorDataCoverage(selectedId);
	}

	@Override
	public void deleteVendorDistribution(String selectedId) {
		vendorDAO.deleteVendorDistribution(selectedId);
		
	}

	@Override
	public String addTradingCapabilitiesSupported(VendorTradingCapabilitiesSupported tradingCapabilitiesSupported) {
		return vendorDAO.addTradingCapabilitiesSupported(tradingCapabilitiesSupported);
		
	}

	@Override
	public void addTradingSoftwareDetails(VendorTradingSoftwareDetails tradingSoftwareDetails) {
		vendorDAO.addTradingSoftwareDetails(tradingSoftwareDetails);
		
	}

	@Override
	public void addAnalyticsfeaturesSupported(VendorAnalyticsfeaturesSupported analyticsfeaturesSupported) {
		vendorDAO.addAnalyticsfeaturesSupported(analyticsfeaturesSupported);
		
	}

	@Override
	public void addAnalyticsSoftwareDetails(VendorAnalyticsSoftwareDetails analyticsSoftwareDetails) {
		vendorDAO.addAnalyticsSoftwareDetails(analyticsSoftwareDetails);
		
	}

	@Override
	public void addResearchCoverage(VendorResearchCoverage researchCoverage) {
		vendorDAO.addResearchCoverage(researchCoverage);
		
	}

	@Override
	public void addResearchDetails(VendorResearchDetails researchDetails) {
		vendorDAO.addResearchDetails(researchDetails);
		
	}

	@Override
	public void addAnalystProfile(VendorAnalystProfile analystProfile) {
		vendorDAO.addAnalystProfile(analystProfile);
		
	}

	@Override
	public List<VendorTradingCapabilitiesSupported> listTradingCapabilitiesSupported(String objectId) {
		List<VendorTradingCapabilitiesSupported> listTradingCapabilitiesSupported = vendorDAO.listTradingCapabilitiesSupported(objectId);
		return listTradingCapabilitiesSupported;
	
	}

	@Override
	public List<VendorTradingSoftwareDetails> listTradingSoftwareDetails(String objectId) {
		List<VendorTradingSoftwareDetails> listTradingSoftwareDetails = vendorDAO.listTradingSoftwareDetails(objectId);
		return listTradingSoftwareDetails;
	
	}

	@Override
	public List<VendorAnalyticsfeaturesSupported> listAnalyticsfeaturesSupported(String objectId) {
		List<VendorAnalyticsfeaturesSupported> listAnalyticsfeaturesSupported = vendorDAO.listAnalyticsfeaturesSupported(objectId);
		return listAnalyticsfeaturesSupported;
	
	}

	@Override
	public List<VendorAnalyticsSoftwareDetails> listAnalyticsSoftwareDetails(String objectId) {
		List<VendorAnalyticsSoftwareDetails> listAnalyticsSoftwareDetails = vendorDAO.listAnalyticsSoftwareDetails(objectId);
		return listAnalyticsSoftwareDetails;
	
	}

	@Override
	public List<VendorResearchCoverage> listResearchCoverage(String objectId) {
		List<VendorResearchCoverage> listResearchCoverage = vendorDAO.listResearchCoverage(objectId);
		return listResearchCoverage;
	}

	@Override
	public List<VendorResearchDetails> listResearchDetails(String objectId) {
		List<VendorResearchDetails> listResearchDetails = vendorDAO.listResearchDetails(objectId);
		return listResearchDetails;
	
	}

	@Override
	public List<VendorAnalystProfile> listAnalystProfile(String objectId) {
		// TODO Auto-generated method stub
		List<VendorAnalystProfile> listAnalystProfile = vendorDAO.listAnalystProfile(objectId);
		return listAnalystProfile;
	}

	@Override
	public void deleteTradingCapabilitiesSupported(String objectId) {
		// TODO Auto-generated method stub
		vendorDAO.deleteTradingCapabilitiesSupported(objectId);
	}

	@Override
	public void deleteTradingSoftwareDetails(String objectId) {
		// TODO Auto-generated method stub
		vendorDAO.deleteTradingSoftwareDetails(objectId);
	}

	@Override
	public void deleteAnalyticsfeaturesSupported(String objectId) {
		// TODO Auto-generated method stub
		vendorDAO.deleteAnalyticsfeaturesSupported(objectId);
	}

	@Override
	public void deleteAnalyticsSoftwareDetails(String objectId) {
		// TODO Auto-generated method stub
		vendorDAO.deleteAnalyticsSoftwareDetails(objectId);
	}

	@Override
	public void deleteResearchCoverage(String objectId) {
		// TODO Auto-generated method stub
		vendorDAO.deleteResearchCoverage(objectId);
	}

	@Override
	public void deleteResearchDetails(String objectId) {
		// TODO Auto-generated method stub
		vendorDAO.deleteResearchDetails(objectId);
	}

	@Override
	public void deleteAnalystProfile(String objectId) {
		// TODO Auto-generated method stub
		vendorDAO.deleteAnalystProfile(objectId);
		
	}

	@Override
	public List<VendorTradingSoftwareDetails> listTradingSoftwareDetailsBasedOnSolutionId(String solutionId) {
		List<VendorTradingSoftwareDetails> vendorTradingSoftwareDetails = vendorDAO.listTradingSoftwareDetailsBasedOnSolutionId(solutionId);
		return vendorTradingSoftwareDetails;
	}

	@Override
	public List<VendorAnalystProfile> listResearchReportingVendorOfferingBasedOnSolutionId(String solutionId) {
		List<VendorAnalystProfile> listResearchReportingVendorOfferingBasedOnSolutionId = vendorDAO.listResearchReportingVendorOfferingBasedOnSolutionId(solutionId);
		return listResearchReportingVendorOfferingBasedOnSolutionId;
	}

	@Override
	public String getRegion(String country) {
		String region = vendorDAO.getRegion(country);
		return region;
	}

	@Override
	public Country getCountryById(String countryId) {
		Country country = vendorDAO.getCountryById(countryId);
		return country;
	}

	@Override
	public List<VendorAwardsMap> listVendorAwardDetails(String id) {
		List<VendorAwardsMap> vendorAwardsMaps  = vendorDAO.listVendorAwardDetails(id);
		
		return vendorAwardsMaps;
	}

	@Override
	public Boolean isAwardAlreadyExist(String value) {
		
		
		return vendorDAO.isAwardAlreadyExist(value);
	}

	@Override
	public Boolean isSolutionAlreadyExist(String value) {
		return vendorDAO.isSolutionAlreadyExist(value);
	}

	@Override
	public void deleteAwardDetails(String objectVar) {
		vendorDAO.deleteAwardDetails(objectVar);
		
	}

	@Override
	public Boolean isTradingSoftwareDetailsOfferingExist(String value) {
		// TODO Auto-generated method stub
		return vendorDAO.isTradingSoftwareDetailsOfferingExist(value);
	}

	
}
