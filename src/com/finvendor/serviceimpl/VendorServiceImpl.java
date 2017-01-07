package com.finvendor.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.dao.VendorDao;
import com.finvendor.exception.ApplicationException;
import com.finvendor.form.FileDetails;
import com.finvendor.model.Awards;
import com.finvendor.model.Cost;
import com.finvendor.model.SolutionTypes;
import com.finvendor.model.Solutions;
import com.finvendor.model.Support;
import com.finvendor.model.Vendor;
import com.finvendor.model.VendorAnalystProfile;
import com.finvendor.model.VendorAnalyticsSoftwareDetails;
import com.finvendor.model.VendorAnalyticsfeaturesSupported;
import com.finvendor.model.VendorAwardsMap;
import com.finvendor.model.VendorDataAggregatorsOffering;
import com.finvendor.model.VendorDataCoverage;
import com.finvendor.model.VendorDistribution;
import com.finvendor.model.VendorOffering;
import com.finvendor.model.VendorRegionCountryExchangeMap;
import com.finvendor.model.VendorResearchCoverage;
import com.finvendor.model.VendorResearchDetails;
import com.finvendor.model.VendorResearchReportsOffering;
import com.finvendor.model.VendorSolution;
import com.finvendor.model.VendorSupport;
import com.finvendor.model.VendorTradingApplicationsOffering;
import com.finvendor.model.VendorTradingCapabilitiesSupported;
import com.finvendor.model.VendorTradingSoftwareDetails;
import com.finvendor.service.UserService;
import com.finvendor.service.VendorService;

public class VendorServiceImpl implements VendorService{

	private static Logger logger = Logger.getLogger(VendorServiceImpl.class);
	
	@Autowired
	private VendorDao vendorDao;
	
	@Autowired
	private UserService userService;

	@Override
	public void saveVendorInfo(Vendor vendor) {
		logger.info("saveVendorInfo method---:");
		vendorDao.saveVendorInfo(vendor);
	}

	@Override
	public Vendor getVendorInfoByEmail(String email) {
		logger.info("getVendorInfoByEmail method---:");
		return vendorDao.getVendorInfoByEmail(email);
	}

	
	@Override
	public Vendor getVendorInfoByUserName(String userName) {
		logger.info("getVendorInfoByuserName method---:");
		return vendorDao.getVendorInfoByUserName(userName);
	}
	
	@Override
	public void updateVendorPersonalInfoTab(Vendor vendor, String username) {
		logger.info("updateVendorPersonalInfoTab method---:");
		vendorDao.updateVendorPersonalInfoTab(vendor,username);
	}

	/*-------------------------------------------------------
	@Override
	public AssetClass getAssetClassDetails(String asset_class) {
		logger.info("getAssetClassDetails method---:");
		return vendorDAO.getAssetClassDetails(asset_class);
	}
	*/
	
	/*-------------------------------------------------------------
	@Override
	public SecurityType getSecurityTypes(String securities) {
		logger.info("getSecurityTypes method---:");
		return vendorDAO.getSecurityTypes(securities);
	}
	*/

	@Override
	public void updateVendorOfferingDetails(VendorOffering vendorOffering) {
		logger.info("updateVendorOfferingDetails method---:");
		 vendorDao.updateVendorOfferingDetails(vendorOffering);
		
	}

	/*----------------------------------------------------------------------
	@Override
	public Region getRegionsByName(String regionsName) {
		logger.info("getRegionsByName method---:");
		return vendorDAO.getRegionsByName(regionsName);
	}
	*/
	
	/*--------------------------------------------------------------------------
	@Override
	public Country getCountryByName(String countryName) {
		logger.info("getCountryByName method---:");
		return vendorDAO.getCountryByName(countryName);
	}
	*/

	/*-------------------------------------------------------------------------------
	@Override
	public Exchange getExchangesByName(String exchangeName) {
		logger.info("getExchangesByName method---:");
		return vendorDAO.getExchangesByName(exchangeName);
	}
	*/

	@Override
	public void updateVendorRegionCountryExchangeInfos(
			VendorRegionCountryExchangeMap vendorRegionCountryExchangeMap) {
		logger.info("updateVendorRegionCountryExchangeInfos method---:");
		 vendorDao.updateVendorRegionCountryExchangeInfos(vendorRegionCountryExchangeMap);
	}

	@Override
	public Awards saveAwardDetails(Awards awards) {
		logger.info("saveAwardDetails method---:");
		return vendorDao.saveAwardDetails(awards);
	}

	@Override
	public void updateVendorAwardDetails(VendorAwardsMap vendorAwardsMap) {
		logger.info("updateVendorAwardDetails method---:");
		 vendorDao.updateVendorAwardDetails(vendorAwardsMap);
	}

	@Override
	public Cost getCostInfo(String costNames) {
		logger.info("getCostInfo method---:");
		return vendorDao.getCostInfo(costNames);
	}
	
	@Override
	public VendorSolution updateVendorSolutionDetails(
			VendorSolution vendorSolution) {
		logger.info("updateVendorSolutionDetails method---:");
		return vendorDao.updateVendorSolutionDetails(vendorSolution);
	}


	@Override
	public Support getSupportInfo(String supportname) {
		logger.info("getSupportInfo method---:");
		return vendorDao.getSupportInfo(supportname);
	}

	@Override
	public void updateVendorSupportInfo(VendorSupport vendorSupport) {
		logger.info("updateVendorSupportInfo method---:");
		 vendorDao.updateVendorSupportInfo(vendorSupport);
	}

	@Override
	public List<VendorOffering> getVendorOfferingDetails(String id) {
		logger.info("getVendorOfferingDetails method---:");
		 return vendorDao.getVendorOfferingDetails(id);
	}
	
	@Override
	public Solutions getSolutionsInfo(String id) {
		logger.info("getSolutionsInfo method---:");
		 return vendorDao.getSolutionsInfo(id);
	}

	@Override
	public List<Solutions> listVednorSolution(String id) {
		
		return vendorDao.listVednorSolution(id);
	}

	@Override
	public List<Solutions> deleteVendorSolution(String id) {
		vendorDao.deleteVendorSolution(id);
		return null;
	}

	@Override
	public SolutionTypes getSolutionTypes(String solutionName) {
		SolutionTypes solutionTypes = vendorDao.getSolutionTypes(solutionName);
		return solutionTypes;
	}

	@Override
	public Solutions addSolutionsInfo(Solutions solutions) {
		return vendorDao.addSolutionsInfo(solutions);
	}

	@Override
	public List<Solutions> getSolutionsBasedOnOfferingTypes(String offeringName, Vendor vendor) {
		
		SolutionTypes solutionTypes = vendorDao.getSolutionTypes(offeringName);
		Set<Solutions> solutions = solutionTypes.getSolutions();
		ArrayList<Solutions> vendorSolution = new ArrayList<Solutions>();
		for(Solutions solution : solutions){
			if(solution.getVendor().getId().equals(vendor.getId())) 
			vendorSolution.add(solution);
		}
		// List<Solutions> solutionsBasedOnOfferingTypes = vendorDAO.getSolutionsBasedOnOfferingTypes(solutionTypes);
		return new ArrayList<Solutions>(vendorSolution);
	}

	@Override
	public String addVendorDataCoverage(VendorDataCoverage vendorDataCoverage) {
		return vendorDao.addVendorDataCoverage(vendorDataCoverage);
	}

	@Override
	public List<VendorDataCoverage> listVendorDataCoverage(String id) {
		List<VendorDataCoverage> listVendorDataCoverage = vendorDao.listVendorDataCoverage(id); 
		return listVendorDataCoverage;
	}

	@Override
	public String addVendorDistribution(VendorDistribution vendorDistribution) {
		return vendorDao.addVendorDistribution(vendorDistribution);	
	}

	@Override
	public List<VendorDistribution> listVendorDistribution(String id) {
		List<VendorDistribution> listVendorDistribution = vendorDao.listVendorDistribution(id);
		return listVendorDistribution;
	}

	@Override
	public void deleteVendorDataCoverage(String selectedId) {
		vendorDao.deleteVendorDataCoverage(selectedId);
	}

	@Override
	public void deleteVendorDistribution(String selectedId) {
		vendorDao.deleteVendorDistribution(selectedId);
		
	}

	@Override
	public String addTradingCapabilitiesSupported(VendorTradingCapabilitiesSupported tradingCapabilitiesSupported) {
		return vendorDao.addTradingCapabilitiesSupported(tradingCapabilitiesSupported);
		
	}

	@Override
	public void addTradingSoftwareDetails(VendorTradingSoftwareDetails tradingSoftwareDetails) {
		vendorDao.addTradingSoftwareDetails(tradingSoftwareDetails);
		
	}

	@Override
	public void addAnalyticsfeaturesSupported(VendorAnalyticsfeaturesSupported analyticsfeaturesSupported) {
		vendorDao.addAnalyticsfeaturesSupported(analyticsfeaturesSupported);
		
	}

	@Override
	public void addAnalyticsSoftwareDetails(VendorAnalyticsSoftwareDetails analyticsSoftwareDetails) {
		vendorDao.addAnalyticsSoftwareDetails(analyticsSoftwareDetails);
		
	}

	@Override
	public void addResearchCoverage(VendorResearchCoverage researchCoverage) {
		vendorDao.addResearchCoverage(researchCoverage);
		
	}

	@Override
	public void addResearchDetails(VendorResearchDetails researchDetails) {
		vendorDao.addResearchDetails(researchDetails);
		
	}

	@Override
	public void addAnalystProfile(VendorAnalystProfile analystProfile) {
		vendorDao.addAnalystProfile(analystProfile);
		
	}

	@Override
	public List<VendorTradingCapabilitiesSupported> listTradingCapabilitiesSupported(String objectId) {
		List<VendorTradingCapabilitiesSupported> listTradingCapabilitiesSupported = vendorDao.listTradingCapabilitiesSupported(objectId);
		return listTradingCapabilitiesSupported;
	
	}

	@Override
	public List<VendorTradingSoftwareDetails> listTradingSoftwareDetails(String objectId) {
		List<VendorTradingSoftwareDetails> listTradingSoftwareDetails = vendorDao.listTradingSoftwareDetails(objectId);
		return listTradingSoftwareDetails;
	
	}

	@Override
	public List<VendorAnalyticsfeaturesSupported> listAnalyticsfeaturesSupported(String objectId) {
		List<VendorAnalyticsfeaturesSupported> listAnalyticsfeaturesSupported = vendorDao.listAnalyticsfeaturesSupported(objectId);
		return listAnalyticsfeaturesSupported;
	
	}

	@Override
	public List<VendorAnalyticsSoftwareDetails> listAnalyticsSoftwareDetails(String objectId) {
		List<VendorAnalyticsSoftwareDetails> listAnalyticsSoftwareDetails = vendorDao.listAnalyticsSoftwareDetails(objectId);
		return listAnalyticsSoftwareDetails;
	
	}

	@Override
	public List<VendorResearchCoverage> listResearchCoverage(String objectId) {
		List<VendorResearchCoverage> listResearchCoverage = vendorDao.listResearchCoverage(objectId);
		return listResearchCoverage;
	}

	@Override
	public List<VendorResearchDetails> listResearchDetails(String objectId) {
		List<VendorResearchDetails> listResearchDetails = vendorDao.listResearchDetails(objectId);
		return listResearchDetails;
	
	}

	@Override
	public List<VendorAnalystProfile> listAnalystProfile(String objectId) {
		// TODO Auto-generated method stub
		List<VendorAnalystProfile> listAnalystProfile = vendorDao.listAnalystProfile(objectId);
		return listAnalystProfile;
	}

	@Override
	public void deleteTradingCapabilitiesSupported(String objectId) {
		// TODO Auto-generated method stub
		vendorDao.deleteTradingCapabilitiesSupported(objectId);
	}

	@Override
	public void deleteTradingSoftwareDetails(String objectId) {
		// TODO Auto-generated method stub
		vendorDao.deleteTradingSoftwareDetails(objectId);
	}

	@Override
	public void deleteAnalyticsfeaturesSupported(String objectId) {
		// TODO Auto-generated method stub
		vendorDao.deleteAnalyticsfeaturesSupported(objectId);
	}

	@Override
	public void deleteAnalyticsSoftwareDetails(String objectId) {
		// TODO Auto-generated method stub
		vendorDao.deleteAnalyticsSoftwareDetails(objectId);
	}

	@Override
	public void deleteResearchCoverage(String objectId) {
		// TODO Auto-generated method stub
		vendorDao.deleteResearchCoverage(objectId);
	}

	@Override
	public void deleteResearchDetails(String objectId) {
		// TODO Auto-generated method stub
		vendorDao.deleteResearchDetails(objectId);
	}

	@Override
	public void deleteAnalystProfile(String objectId) {
		// TODO Auto-generated method stub
		vendorDao.deleteAnalystProfile(objectId);
		
	}

	@Override
	public List<VendorTradingSoftwareDetails> listTradingSoftwareDetailsBasedOnSolutionId(String solutionId) {
		List<VendorTradingSoftwareDetails> vendorTradingSoftwareDetails = vendorDao.listTradingSoftwareDetailsBasedOnSolutionId(solutionId);
		return vendorTradingSoftwareDetails;
	}

	@Override
	public List<VendorResearchCoverage> listResearchReportingVendorOfferingBasedOnSolutionId(String solutionId) {
		List<VendorResearchCoverage> listResearchReportingVendorOfferingBasedOnSolutionId = vendorDao.listResearchReportingVendorOfferingBasedOnSolutionId(solutionId);
		return listResearchReportingVendorOfferingBasedOnSolutionId;
	}

	/*---------------------------------------------------------------------------------------------------------------
	@Override
	public String getRegion(String country) {
		String region = vendorDAO.getRegion(country);
		return region;
	}
	*/

	/*------------------------------------------------------------------------------------------------------------------
	@Override
	public Country getCountryById(String countryId) {
		Country country = vendorDAO.getCountryById(countryId);
		return country;
	}
	*/

	@Override
	public List<VendorAwardsMap> listVendorAwardDetails(String id) {
		List<VendorAwardsMap> vendorAwardsMaps  = vendorDao.listVendorAwardDetails(id);
		
		return vendorAwardsMaps;
	}

	@Override
	public Boolean isAwardAlreadyExist(String value) {
		
		
		return vendorDao.isAwardAlreadyExist(value);
	}

	@Override
	public Boolean isSolutionAlreadyExist(String value) {
		return vendorDao.isSolutionAlreadyExist(value);
	}

	@Override
	public void deleteAwardDetails(String objectVar) {
		vendorDao.deleteAwardDetails(objectVar);
		
	}

	@Override
	public Boolean isTradingSoftwareDetailsOfferingExist(String value) {
		// TODO Auto-generated method stub
		return vendorDao.isTradingSoftwareDetailsOfferingExist(value);
	}

	@Override
	public void updateVendorLogo(FileDetails ufile, String username) {
		 vendorDao.updateVendorLogo(ufile,username);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Object[]> getMarketDataVendorOfferingsForProfile(String vendorId) {
		return vendorDao.getMarketDataVendorOfferingsForProfile(vendorId);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Object[]> getTradingApplicationOfferingsForProfile(String vendorId) {
		return vendorDao.getTradingApplicationOfferingsForProfile(vendorId);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Object[]> getAnalyticsApplicationOfferingsForProfile(String vendorId) {
		return vendorDao.getAnalyticsApplicationOfferingsForProfile(vendorId);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Object[]> getResearchReportOfferingsForProfile(String vendorId) {
		return vendorDao.getResearchReportOfferingsForProfile(vendorId);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Object[]> getVendorAwardDetailsForProfile(String vendorId) {
		return vendorDao.getVendorAwardDetailsForProfile(vendorId);
	}
	
	/* Vendor Data Aggregator Offering Begin */
	
	@Override
	@Transactional
	public void addVendorDataAggregatorsOffering(VendorDataAggregatorsOffering 
			vendorDataAggregatorsOffering) throws ApplicationException {
		vendorDao.addVendorDataAggregatorsOffering(vendorDataAggregatorsOffering);
	}
	
	@Override
	@Transactional(readOnly=true)
	public VendorDataAggregatorsOffering fetchVendorDataAggregatorsOffering(
			String productId) throws ApplicationException {
		return vendorDao.fetchVendorDataAggregatorsOffering(productId);
	}
	
	@Override
	@Transactional
	public void deleteVendorDataAggregatorsOffering(String productId) 
			throws ApplicationException {
		vendorDao.deleteVendorDataAggregatorsOffering(productId);
	}
	
	@Override
	@Transactional
	public List<VendorDataAggregatorsOffering> getVendorDataAggregatorsOffering(
			String userName) throws ApplicationException {
		return vendorDao.getVendorDataAggregatorsOffering(
				userName);
	}
	
	/* Vendor Data Aggregator Offering End */
	
	/* Vendor Trading Applications Offering Begin */
	@Override
	@Transactional
	public void addVendorTradingApplicationsOffering(VendorTradingApplicationsOffering 
			vendorTradingApplicationsOffering) throws ApplicationException {
		vendorDao.addVendorTradingApplicationsOffering(
				vendorTradingApplicationsOffering);
	}
	
	@Override
	@Transactional
	public VendorTradingApplicationsOffering fetchVendorTradingApplicationsOffering(
			String productId) throws ApplicationException {
		return vendorDao.fetchVendorTradingApplicationsOffering(productId);
	}
	
	@Override
	@Transactional
	public void deleteVendorTradingApplicationsOffering(String productId) 
			throws ApplicationException {
		vendorDao.deleteVendorTradingApplicationsOffering(productId);
	}
	
	@Override
	@Transactional
	public List<VendorTradingApplicationsOffering> getVendorTradingApplicationsOffering(
			String vendorName) throws ApplicationException {
		return vendorDao.getVendorTradingApplicationsOffering(vendorName);
	}
	/* Vendor Trading Applications Offering End */
	
	/* Vendor Research Reports Offering Begin */
	@Override
	@Transactional
	public void addVendorResearchReportsOffering(VendorResearchReportsOffering 
			vendorResearchReportsOffering) throws ApplicationException {
		vendorDao.addVendorResearchReportsOffering(
				vendorResearchReportsOffering);
	}
	
	@Override
	@Transactional
	public VendorResearchReportsOffering fetchVendorResearchReportsOffering(
			String productId) throws ApplicationException {
		return vendorDao.fetchVendorResearchReportsOffering(productId);
	}
	
	@Override
	@Transactional
	public void deleteVendorResearchReportsOffering(String productId) 
			throws ApplicationException {
		vendorDao.deleteVendorResearchReportsOffering(productId);
	}
	
	@Override
	@Transactional
	public List<VendorResearchReportsOffering> getVendorResearchReportsOffering(
			String vendorName) throws ApplicationException {
		return vendorDao.getVendorResearchReportsOffering(vendorName);
	}
	/* Vendor Research Reports Offering End */
}
