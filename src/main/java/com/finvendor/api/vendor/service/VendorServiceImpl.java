package com.finvendor.api.vendor.service;

import com.finvendor.api.vendor.dao.VendorDaoImpl;
import com.finvendor.common.commondao.ICommonDao;
import com.finvendor.common.util.FileUtil;
import com.finvendor.common.exception.ApplicationException;
import com.finvendor.model.*;
import com.finvendor.modelpojo.staticpojo.FileDetails;
import com.finvendor.util.VendorEnum;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class VendorServiceImpl {

	private static final Logger logger = LoggerFactory.getLogger(VendorServiceImpl.class.getName());

	@Autowired
	private VendorDaoImpl vendorDao;
	
	@Autowired
	private ICommonDao commonDao;
	
//	
	public void saveVendorInfo(Vendor vendor) {
		logger.info("saveVendorInfo method---:");
		vendorDao.saveVendorInfo(vendor);
	}

	
	public Vendor getVendorInfoByEmail(String email) {
		logger.info("getVendorInfoByEmail method---:");
		return vendorDao.getVendorInfoByEmail(email);
	}

	
	public Vendor getVendorInfoByUserName(String userName) {
		logger.info("getVendorInfoByuserName method---:");
		return vendorDao.getVendorInfoByUserName(userName);
	}

	
	public void updateVendorPersonalInfoTab(Vendor vendor, String username) {
		logger.info("updateVendorPersonalInfoTab method---:");
		vendorDao.updateVendorPersonalInfoTab(vendor, username);
	}

	/*-------------------------------------------------------
	
	public AssetClass getAssetClassDetails(String asset_class) {
		logger.info("getAssetClassDetails method---:");
		return vendorDAO.getAssetClassDetails(asset_class);
	}
	*/

	/*-------------------------------------------------------------
	
	public SecurityType getSecurityTypes(String securities) {
		logger.info("getSecurityTypes method---:");
		return vendorDAO.getSecurityTypes(securities);
	}
	*/

	
	public void updateVendorOfferingDetails(VendorOffering vendorOffering) {
		logger.info("updateVendorOfferingDetails method---:");
		vendorDao.updateVendorOfferingDetails(vendorOffering);

	}

	/*----------------------------------------------------------------------
	
	public Region getRegionsByName(String regionsName) {
		logger.info("getRegionsByName method---:");
		return vendorDAO.getRegionsByName(regionsName);
	}
	*/

	/*--------------------------------------------------------------------------
	
	public Country getCountryByName(String countryName) {
		logger.info("getCountryByName method---:");
		return vendorDAO.getCountryByName(countryName);
	}
	*/

	/*-------------------------------------------------------------------------------
	
	public Exchange getExchangesByName(String exchangeName) {
		logger.info("getExchangesByName method---:");
		return vendorDAO.getExchangesByName(exchangeName);
	}
	*/

	
	public void updateVendorRegionCountryExchangeInfos(VendorRegionCountryExchangeMap vendorRegionCountryExchangeMap) {
		logger.info("updateVendorRegionCountryExchangeInfos method---:");
		vendorDao.updateVendorRegionCountryExchangeInfos(vendorRegionCountryExchangeMap);
	}

	
	public Awards saveAwardDetails(Awards awards) {
		logger.info("saveAwardDetails method---:");
		return vendorDao.saveAwardDetails(awards);
	}

	
	public void updateVendorAwardDetails(VendorAwardsMap vendorAwardsMap) {
		logger.info("updateVendorAwardDetails method---:");
		vendorDao.updateVendorAwardDetails(vendorAwardsMap);
	}

	
	public Cost getCostInfo(String costNames) {
		logger.info("getCostInfo method---:");
		return vendorDao.getCostInfo(costNames);
	}

	
	public VendorSolution updateVendorSolutionDetails(VendorSolution vendorSolution) {
		logger.info("updateVendorSolutionDetails method---:");
		return vendorDao.updateVendorSolutionDetails(vendorSolution);
	}

	
	public Support getSupportInfo(String supportname) {
		logger.info("getSupportInfo method---:");
		return vendorDao.getSupportInfo(supportname);
	}

	
	public void updateVendorSupportInfo(VendorSupport vendorSupport) {
		logger.info("updateVendorSupportInfo method---:");
		vendorDao.updateVendorSupportInfo(vendorSupport);
	}

	
	public List<VendorOffering> getVendorOfferingDetails(String id) {
		logger.info("getVendorOfferingDetails method---:");
		return vendorDao.getVendorOfferingDetails(id);
	}

	
	public Solutions getSolutionsInfo(String id) {
		logger.info("getSolutionsInfo method---:");
		return vendorDao.getSolutionsInfo(id);
	}

	
	public List<Solutions> listVednorSolution(String id) {

		return vendorDao.listVednorSolution(id);
	}

	
	public List<Solutions> deleteVendorSolution(String id) {
		vendorDao.deleteVendorSolution(id);
		return null;
	}

	
	public SolutionTypes getSolutionTypes(String solutionName) {
		SolutionTypes solutionTypes = vendorDao.getSolutionTypes(solutionName);
		return solutionTypes;
	}

	
	public Solutions addSolutionsInfo(Solutions solutions) {
		return vendorDao.addSolutionsInfo(solutions);
	}

	
	public List<Solutions> getSolutionsBasedOnOfferingTypes(String offeringName, Vendor vendor) {

		SolutionTypes solutionTypes = vendorDao.getSolutionTypes(offeringName);
		Set<Solutions> solutions = solutionTypes.getSolutions();
		ArrayList<Solutions> vendorSolution = new ArrayList<Solutions>();
		for (Solutions solution : solutions) {
			if (solution.getVendor().getId().equals(vendor.getId()))
				vendorSolution.add(solution);
		}
		// List<Solutions> solutionsBasedOnOfferingTypes =
		// vendorDAO.getSolutionsBasedOnOfferingTypes(solutionTypes);
		return new ArrayList<Solutions>(vendorSolution);
	}

	
	public String addVendorDataCoverage(VendorDataCoverage vendorDataCoverage) {
		return vendorDao.addVendorDataCoverage(vendorDataCoverage);
	}

	
	public List<VendorDataCoverage> listVendorDataCoverage(String id) {
		List<VendorDataCoverage> listVendorDataCoverage = vendorDao.listVendorDataCoverage(id);
		return listVendorDataCoverage;
	}

	
	public String addVendorDistribution(VendorDistribution vendorDistribution) {
		return vendorDao.addVendorDistribution(vendorDistribution);
	}

	
	public List<VendorDistribution> listVendorDistribution(String id) {
		List<VendorDistribution> listVendorDistribution = vendorDao.listVendorDistribution(id);
		return listVendorDistribution;
	}

	
	public void deleteVendorDataCoverage(String selectedId) {
		vendorDao.deleteVendorDataCoverage(selectedId);
	}

	
	public void deleteVendorDistribution(String selectedId) {
		vendorDao.deleteVendorDistribution(selectedId);

	}

	
	public String addTradingCapabilitiesSupported(VendorTradingCapabilitiesSupported tradingCapabilitiesSupported) {
		return vendorDao.addTradingCapabilitiesSupported(tradingCapabilitiesSupported);

	}

	
	public void addTradingSoftwareDetails(VendorTradingSoftwareDetails tradingSoftwareDetails) {
		vendorDao.addTradingSoftwareDetails(tradingSoftwareDetails);

	}

	
	public void addAnalyticsfeaturesSupported(VendorAnalyticsfeaturesSupported analyticsfeaturesSupported) {
		vendorDao.addAnalyticsfeaturesSupported(analyticsfeaturesSupported);

	}

	
	public void addAnalyticsSoftwareDetails(VendorAnalyticsSoftwareDetails analyticsSoftwareDetails) {
		vendorDao.addAnalyticsSoftwareDetails(analyticsSoftwareDetails);

	}

	
	public void addResearchCoverage(VendorResearchCoverage researchCoverage) {
		vendorDao.addResearchCoverage(researchCoverage);

	}

	
	public void addResearchDetails(VendorResearchDetails researchDetails) {
		vendorDao.addResearchDetails(researchDetails);

	}

	
	public void addAnalystProfile(VendorAnalystProfile analystProfile) {
		vendorDao.addAnalystProfile(analystProfile);

	}

	
	public List<VendorTradingCapabilitiesSupported> listTradingCapabilitiesSupported(String objectId) {
		List<VendorTradingCapabilitiesSupported> listTradingCapabilitiesSupported = vendorDao
				.listTradingCapabilitiesSupported(objectId);
		return listTradingCapabilitiesSupported;

	}

	
	public List<VendorTradingSoftwareDetails> listTradingSoftwareDetails(String objectId) {
		List<VendorTradingSoftwareDetails> listTradingSoftwareDetails = vendorDao.listTradingSoftwareDetails(objectId);
		return listTradingSoftwareDetails;

	}

	
	public List<VendorAnalyticsfeaturesSupported> listAnalyticsfeaturesSupported(String objectId) {
		List<VendorAnalyticsfeaturesSupported> listAnalyticsfeaturesSupported = vendorDao
				.listAnalyticsfeaturesSupported(objectId);
		return listAnalyticsfeaturesSupported;

	}

	
	public List<VendorAnalyticsSoftwareDetails> listAnalyticsSoftwareDetails(String objectId) {
		List<VendorAnalyticsSoftwareDetails> listAnalyticsSoftwareDetails = vendorDao
				.listAnalyticsSoftwareDetails(objectId);
		return listAnalyticsSoftwareDetails;

	}

	
	public List<VendorResearchCoverage> listResearchCoverage(String objectId) {
		List<VendorResearchCoverage> listResearchCoverage = vendorDao.listResearchCoverage(objectId);
		return listResearchCoverage;
	}

	
	public List<VendorResearchDetails> listResearchDetails(String objectId) {
		List<VendorResearchDetails> listResearchDetails = vendorDao.listResearchDetails(objectId);
		return listResearchDetails;

	}

	
	public List<VendorAnalystProfile> listAnalystProfile(String objectId) {
		// TODO Auto-generated method stub
		List<VendorAnalystProfile> listAnalystProfile = vendorDao.listAnalystProfile(objectId);
		return listAnalystProfile;
	}

	
	public void deleteTradingCapabilitiesSupported(String objectId) {
		// TODO Auto-generated method stub
		vendorDao.deleteTradingCapabilitiesSupported(objectId);
	}

	
	public void deleteTradingSoftwareDetails(String objectId) {
		// TODO Auto-generated method stub
		vendorDao.deleteTradingSoftwareDetails(objectId);
	}

	
	public void deleteAnalyticsfeaturesSupported(String objectId) {
		// TODO Auto-generated method stub
		vendorDao.deleteAnalyticsfeaturesSupported(objectId);
	}

	
	public void deleteAnalyticsSoftwareDetails(String objectId) {
		// TODO Auto-generated method stub
		vendorDao.deleteAnalyticsSoftwareDetails(objectId);
	}

	
	public void deleteResearchCoverage(String objectId) {
		// TODO Auto-generated method stub
		vendorDao.deleteResearchCoverage(objectId);
	}

	
	public void deleteResearchDetails(String objectId) {
		// TODO Auto-generated method stub
		vendorDao.deleteResearchDetails(objectId);
	}

	
	public void deleteAnalystProfile(String objectId) {
		// TODO Auto-generated method stub
		vendorDao.deleteAnalystProfile(objectId);

	}

	
	public List<VendorTradingSoftwareDetails> listTradingSoftwareDetailsBasedOnSolutionId(String solutionId) {
		List<VendorTradingSoftwareDetails> vendorTradingSoftwareDetails = vendorDao
				.listTradingSoftwareDetailsBasedOnSolutionId(solutionId);
		return vendorTradingSoftwareDetails;
	}

	
	public List<VendorResearchCoverage> listResearchReportingVendorOfferingBasedOnSolutionId(String solutionId) {
		List<VendorResearchCoverage> listResearchReportingVendorOfferingBasedOnSolutionId = vendorDao
				.listResearchReportingVendorOfferingBasedOnSolutionId(solutionId);
		return listResearchReportingVendorOfferingBasedOnSolutionId;
	}

	/*---------------------------------------------------------------------------------------------------------------
	
	public String getRegion(String country) {
		String region = vendorDAO.getRegion(country);
		return region;
	}
	*/

	/*------------------------------------------------------------------------------------------------------------------
	
	public Country getCountryById(String countryId) {
		Country country = vendorDAO.getCountryById(countryId);
		return country;
	}
	*/

	
	public List<VendorAwardsMap> listVendorAwardDetails(String id) {
		List<VendorAwardsMap> vendorAwardsMaps = vendorDao.listVendorAwardDetails(id);

		return vendorAwardsMaps;
	}

	
	public Boolean isAwardAlreadyExist(String value) {

		return vendorDao.isAwardAlreadyExist(value);
	}

	
	public Boolean isSolutionAlreadyExist(String value) {
		return vendorDao.isSolutionAlreadyExist(value);
	}

	
	public void deleteAwardDetails(String objectVar) {
		vendorDao.deleteAwardDetails(objectVar);

	}

	
	public Boolean isTradingSoftwareDetailsOfferingExist(String value) {
		// TODO Auto-generated method stub
		return vendorDao.isTradingSoftwareDetailsOfferingExist(value);
	}

	
	public void updateVendorLogo(FileDetails ufile, String username) {
		vendorDao.updateVendorLogo(ufile, username);

	}

	
	
	public List<Object[]> getMarketDataVendorOfferingsForProfile(String vendorId) {
		return vendorDao.getMarketDataVendorOfferingsForProfile(vendorId);
	}

	
	
	public List<Object[]> getTradingApplicationOfferingsForProfile(String vendorId) {
		return vendorDao.getTradingApplicationOfferingsForProfile(vendorId);
	}

	
	
	public List<Object[]> getAnalyticsApplicationOfferingsForProfile(String vendorId) {
		return vendorDao.getAnalyticsApplicationOfferingsForProfile(vendorId);
	}

	
	
	public List<Object[]> getResearchReportOfferingsForProfile(String vendorId) {
		return vendorDao.getResearchReportOfferingsForProfile(vendorId);
	}

	
	
	public List<Object[]> getVendorAwardDetailsForProfile(String vendorId) {
		return vendorDao.getVendorAwardDetailsForProfile(vendorId);
	}

	/* Vendor Data Aggregator Offering Begin */

	
	
	public void addVendorDataAggregatorsOffering(VendorDataAggregatorsOffering vendorDataAggregatorsOffering)
			throws ApplicationException {
		vendorDao.addVendorDataAggregatorsOffering(vendorDataAggregatorsOffering);
	}

	
	
	public VendorDataAggregatorsOffering fetchVendorDataAggregatorsOffering(String productId)
			throws ApplicationException {
		return vendorDao.fetchVendorDataAggregatorsOffering(productId);
	}

	
	
	public void deleteVendorDataAggregatorsOffering(String productId) throws ApplicationException {
		vendorDao.deleteVendorDataAggregatorsOffering(productId);
	}

	
	
	public List<VendorDataAggregatorsOffering> getVendorDataAggregatorsOffering(String userName)
			throws ApplicationException {
		return vendorDao.getVendorDataAggregatorsOffering(userName);
	}

	/* Vendor Data Aggregator Offering End */

	/* Vendor Trading Applications Offering Begin */
	
	
	public void addVendorTradingApplicationsOffering(
			VendorTradingApplicationsOffering vendorTradingApplicationsOffering) throws ApplicationException {
		vendorDao.addVendorTradingApplicationsOffering(vendorTradingApplicationsOffering);
	}

	
	
	public VendorTradingApplicationsOffering fetchVendorTradingApplicationsOffering(String productId)
			throws ApplicationException {
		return vendorDao.fetchVendorTradingApplicationsOffering(productId);
	}

	
	
	public void deleteVendorTradingApplicationsOffering(String productId) throws ApplicationException {
		vendorDao.deleteVendorTradingApplicationsOffering(productId);
	}

	
	
	public List<VendorTradingApplicationsOffering> getVendorTradingApplicationsOffering(String vendorName)
			throws ApplicationException {
		return vendorDao.getVendorTradingApplicationsOffering(vendorName);
	}
	/* Vendor Trading Applications Offering End */

	/* Vendor Research Reports Offering Begin */
	
	
	public void addVendorResearchReportsOffering(VendorResearchReportsOffering vendorResearchReportsOffering)
			throws ApplicationException {
		vendorDao.addVendorResearchReportsOffering(vendorResearchReportsOffering);
	}

	
	
	public VendorResearchReportsOffering fetchVendorResearchReportsOffering(String productId)
			throws ApplicationException {
		return vendorDao.fetchVendorResearchReportsOffering(productId);
	}

	
	
	public void deleteVendorResearchReportsOffering(String productId) throws ApplicationException {
		vendorDao.deleteVendorResearchReportsOffering(productId);
	}

	
	
	public List<VendorResearchReportsOffering> getVendorResearchReportsOffering(String vendorName)
			throws ApplicationException {
		return vendorDao.getVendorResearchReportsOffering(vendorName);
	}
	/* Vendor Research Reports Offering End */

	/* Vendor Analytics Applications Offering Begin */
	
	
	public void addVendorAnalyticsApplicationsOffering(
			VendorAnalyticsApplicationsOffering vendorAnalyticsApplicationsOffering) throws ApplicationException {
		vendorDao.addVendorAnalyticsApplicationsOffering(vendorAnalyticsApplicationsOffering);
	}

	
	
	public VendorAnalyticsApplicationsOffering fetchVendorAnalyticsApplicationsOffering(String productId)
			throws ApplicationException {
		return vendorDao.fetchVendorAnalyticsApplicationsOffering(productId);
	}

	
	
	public void deleteVendorAnalyticsApplicationsOffering(String productId) throws ApplicationException {
		vendorDao.deleteVendorAnalyticsApplicationsOffering(productId);
	}

	
	
	public List<VendorAnalyticsApplicationsOffering> getVendorAnalyticsApplicationsOffering(String vendorName)
			throws ApplicationException {
		return vendorDao.getVendorAnalyticsApplicationsOffering(vendorName);
	}
	/* Vendor Analytics Applications Offering End */

	
	public boolean uploadFile(VendorEnum type, byte[] bytes, String destPath) {
		boolean uploadFileStatus = true;
		switch (type) {
		case VENDOR_RESEARCH_REPORT_OFFERING:
			try {
				FileUtil.writeByteArrayToFile(destPath, bytes);
			} catch (IOException e) {
				uploadFileStatus = false;
			}
			break;
		default:
			uploadFileStatus = false;
			break;

		}
		return uploadFileStatus;
	}

	
	public void deleteFile(VendorEnum type, String path) throws IOException {
		switch (type) {
		case VENDOR_RESEARCH_REPORT_OFFERING:
			if (path != null && !path.isEmpty() )
				FileUtil.deleteFile(path);
			break;
		}

	}

	
	public String getAllVendorOffering(String vendorName) throws ApplicationException {
		return vendorDao.findAllVendorOffering(vendorName);
	}

	
	
	public String getCompanyName(String companyId) throws ApplicationException {
		 String companyName ="";
		SQLQuery nativeQuery = commonDao.getNativeQuery("select company_id, company_name from rsch_sub_area_company_dtls where company_id=?",
				new String[] {companyId});
		@SuppressWarnings("unchecked")
		List<Object[]> rows = nativeQuery.list();
		for (Object[] row : rows) {
			 companyName = row[1] != null ? row[1].toString().trim() : "";
			 break;
		}
		return companyName;
	}
	
	
}
