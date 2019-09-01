///**
// *
// */
//package com.finvendor.api.vendor.dao;
//
//import com.finvendor.common.exception.ApplicationException;
//import com.finvendor.model.*;
//import com.finvendor.modelpojo.staticpojo.FileDetails;
//
//import java.util.List;
//
///**
// * @author rayulu vemula
// *
// */
//public interface VendorDao {
//
//	/** --------------------------------------------------------------------- */
//	/**
//	 * (non-Javadoc) Method to save vendor info
//	 *
//	 * @return
//	 * @see VendorDao#saveVendorInfo()
//	 */
//	void saveVendorInfo(Vendor vendor);
//
//	/** --------------------------------------------------------------------- */
//	/**
//	 * (non-Javadoc) Method to get vendor info
//	 *
//	 * @return
//	 * @see VendorDao#getVendorInfoByEmail()
//	 */
//	Vendor getVendorInfoByEmail(String email);
//	Vendor getVendorInfoByUserName(String userName);
//
//	/** --------------------------------------------------------------------- */
//	/**
//	 * (non-Javadoc) Method to update vendor personal info
//	 *
//	 * @return
//	 * @see VendorDao#updateVendorPersonalInfoTab()
//	 */
//	void updateVendorPersonalInfoTab(Vendor vendor, String username);
//
//	/** --------------------------------------------------------------------- */
//	/**
//	 * (non-Javadoc) Method to get vendor details
//	 *
//	 * @return
//	 * @see com.finvendor.dao.VendorDAO#getVendorDetails()
//	 */
//	//Vendor getVendorDetails(String appUser);
//
//	/** --------------------------------------------------------------------- */
//	/**
//	 * (non-Javadoc) Method to get asset class details
//	 *
//	 * @return
//	 * @see com.finvendor.dao.VendorDAO#getAssetClassDetails()
//	 */
//	//AssetClass getAssetClassDetails(String asset_class);
//
//	/** --------------------------------------------------------------------- */
//	/**
//	 * (non-Javadoc) Method to get asset class details
//	 *
//	 * @return
//	 * @see com.finvendor.dao.VendorDAO#getSecurityTypes()
//	 */
//	//SecurityType getSecurityTypes(String securities);
//
//	/** --------------------------------------------------------------------- */
//	/**
//	 * (non-Javadoc) Method to get asset class details
//	 *
//	 * @see VendorDao#updateVendorOfferingDetails()
//	 */
//	void updateVendorOfferingDetails(VendorOffering vendorOffering);
//
//	/** --------------------------------------------------------------------- */
//	/**
//	 * (non-Javadoc) Method to get region details
//	 *
//	 * @return
//	 * @see com.finvendor.dao.VendorDAO#getRegionsByName()
//	 */
//	//Region getRegionsByName(String regionsName);
//
//	/** --------------------------------------------------------------------- */
//	/**
//	 * (non-Javadoc) Method to get  country details
//	 *
//	 * @return
//	 * @see com.finvendor.dao.VendorDAO#getCountryByName()
//	 */
//	//Country getCountryByName(String countryName);
//
//	/** --------------------------------------------------------------------- */
//	/**
//	 * (non-Javadoc) Method to get exchange details
//	 *
//	 * @return
//	 * @see com.finvendor.dao.VendorDAO#getExchangesByName()
//	 */
//	//Exchange getExchangesByName(String exchangeName);
//
//	/** --------------------------------------------------------------------- */
//	/**
//	 * (non-Javadoc) Method to update vendor region country exchange map
//	 *
//	 * @return
//	 * @see VendorDao#updateVendorRegionCountryExchangeInfos()
//	 */
//	void updateVendorRegionCountryExchangeInfos(
//            VendorRegionCountryExchangeMap vendorRegionCountryExchangeMap);
//
//	/** --------------------------------------------------------------------- */
//	/**
//	 * (non-Javadoc) Method to save award details
//	 *
//	 * @return
//	 * @see VendorDao#saveAwardDetails()
//	 */
//	Awards saveAwardDetails(Awards awards);
//
//	/** --------------------------------------------------------------------- */
//	/**
//	 * (non-Javadoc) Method to update vendor award details
//	 *
//	 * @return
//	 * @see VendorDao#updateVendorAwardDetails()
//	 */
//	void updateVendorAwardDetails(VendorAwardsMap vendorAwardsMap);
//
//	/** --------------------------------------------------------------------- */
//	/**
//	 * (non-Javadoc) Method to get cost details
//	 *
//	 * @return
//	 * @see VendorDao#getCostInfo()
//	 */
//	Cost getCostInfo(String costNames);
//
//	/** --------------------------------------------------------------------- */
//	/**
//	 * (non-Javadoc) Method to update vendor solution details
//	 *
//	 * @return
//	 * @see VendorDao#updateVendorSolutionDetails()
//	 */
//	VendorSolution updateVendorSolutionDetails(VendorSolution vendorSolution);
//
//	/** --------------------------------------------------------------------- */
//	/**
//	 * (non-Javadoc) Method to get support details
//	 *
//	 * @return
//	 * @see VendorDao#getSupportInfo()
//	 */
//	Support getSupportInfo(String supportname);
//
//	/** --------------------------------------------------------------------- */
//	/**
//	 * (non-Javadoc) Method to update vendor support details
//	 *
//	 * @return
//	 * @see VendorDao#updateVendorSupportInfo()
//	 */
//	void updateVendorSupportInfo(VendorSupport vendorSupport);
//
//	/** --------------------------------------------------------------------- */
//	/**
//	 * (non-Javadoc) Method to update vendor offering  details
//	 *
//	 * @return
//	 * @see VendorDao#getVendorOfferingDetails()
//	 */
//	List<VendorOffering> getVendorOfferingDetails(String id);
//
//	public Solutions getSolutionsInfo(String solutionId);
//
//
//	List<Solutions> listVednorSolution(String id);
//	Solutions deleteVendorSolution(String id);
//	public Solutions addSolutionsInfo(Solutions solutions);
//	SolutionTypes getSolutionTypes(String name);
//	public List<Solutions> getSolutionsBasedOnOfferingTypes(SolutionTypes solutionTypes);
//	String addVendorDataCoverage(VendorDataCoverage vendorDataCoverage);
//	List<VendorDataCoverage> listVendorDataCoverage(String id);
//	String addVendorDistribution(VendorDistribution vendorDistribution);
//
//	public List<VendorDistribution> listVendorDistribution(String id);
//
//	void deleteVendorDataCoverage(String selectedId);
//
//	void deleteVendorDistribution(String selectedId);
//
//	String addTradingCapabilitiesSupported(VendorTradingCapabilitiesSupported tradingCapabilitiesSupported);
//	void addTradingSoftwareDetails(VendorTradingSoftwareDetails tradingSoftwareDetails);
//	void addAnalyticsfeaturesSupported(VendorAnalyticsfeaturesSupported analyticsfeaturesSupported);
//	void addAnalyticsSoftwareDetails(VendorAnalyticsSoftwareDetails analyticsSoftwareDetails);
//	void addResearchCoverage(VendorResearchCoverage researchCoverage);
//	void addResearchDetails(VendorResearchDetails researchDetails);
//	void addAnalystProfile(VendorAnalystProfile analystProfile);
//
//	public List<VendorTradingCapabilitiesSupported> listTradingCapabilitiesSupported(String objectId);
//	public List<VendorTradingSoftwareDetails> listTradingSoftwareDetails(String objectId);
//	public List<VendorAnalyticsfeaturesSupported> listAnalyticsfeaturesSupported(String objectId);
//	public List<VendorAnalyticsSoftwareDetails> listAnalyticsSoftwareDetails(String objectId);
//	public List<VendorResearchCoverage> listResearchCoverage(String objectId);
//	public List<VendorResearchDetails> listResearchDetails(String objectId);
//	public List<VendorAnalystProfile> listAnalystProfile(String objectId);
//
//	void deleteTradingCapabilitiesSupported(String objectId);
//	void deleteTradingSoftwareDetails(String objectId);
//	void deleteAnalyticsfeaturesSupported(String objectId);
//	void deleteAnalyticsSoftwareDetails(String objectId);
//	void deleteResearchCoverage(String objectId);
//	void deleteResearchDetails(String objectId);
//	void deleteAnalystProfile(String objectId);
//
//	List<VendorTradingSoftwareDetails> listTradingSoftwareDetailsBasedOnSolutionId(String solutionId);
//
//	List<VendorResearchCoverage> listResearchReportingVendorOfferingBasedOnSolutionId(String solutionId);
//	//public String getRegion(String country);
//
//	//Country getCountryById(String countryId);
//
//	List<VendorAwardsMap> listVendorAwardDetails(String id);
//
//	Boolean isAwardAlreadyExist(String value);
//
//	Boolean isSolutionAlreadyExist(String value);
//
//	void deleteAwardDetails(String objectVar);
//
//	Boolean isTradingSoftwareDetailsOfferingExist(String value);
//
//	Object updateVendorLogo(FileDetails ufile, String username);
//
//	List<Object[]> getMarketDataVendorOfferingsForProfile(String vendorId);
//	List<Object[]> getTradingApplicationOfferingsForProfile(String vendorId);
//	List<Object[]> getAnalyticsApplicationOfferingsForProfile(String vendorId);
//	List<Object[]> getResearchReportOfferingsForProfile(String vendorId);
//	public List<Object[]> getVendorAwardDetailsForProfile(String vendorId);
//
//	/* Vendor Data Aggregator Offering Begin */
//	public void addVendorDataAggregatorsOffering(VendorDataAggregatorsOffering
//            vendorDataAggregatorsOffering) throws ApplicationException;
//	public VendorDataAggregatorsOffering fetchVendorDataAggregatorsOffering(
//            String productId) throws ApplicationException;
//	public void deleteVendorDataAggregatorsOffering(String productId) throws ApplicationException;
//	public List<VendorDataAggregatorsOffering> getVendorDataAggregatorsOffering(
//            String vendorName) throws ApplicationException;
//	/* Vendor Data Aggregator Offering End */
//
//	/* Vendor Trading Applications Offering Begin */
//	public void addVendorTradingApplicationsOffering(VendorTradingApplicationsOffering
//            vendorTradingApplicationsOffering) throws ApplicationException;
//	public VendorTradingApplicationsOffering fetchVendorTradingApplicationsOffering(
//            String productId) throws ApplicationException;
//	public void deleteVendorTradingApplicationsOffering(String productId) throws ApplicationException;
//	public List<VendorTradingApplicationsOffering> getVendorTradingApplicationsOffering(
//            String vendorName) throws ApplicationException;
//	/* Vendor Trading Applications Offering End */
//
//	/* Vendor Research Report Offering Begin */
//	public void addVendorResearchReportsOffering(VendorResearchReportsOffering
//            vendorResearchReportsOffering) throws ApplicationException;
//	public VendorResearchReportsOffering fetchVendorResearchReportsOffering(
//            String productId) throws ApplicationException;
//	public void deleteVendorResearchReportsOffering(String productId) throws ApplicationException;
//	public List<VendorResearchReportsOffering> getVendorResearchReportsOffering(
//            String vendorName) throws ApplicationException;
//	/* Vendor Research Report Offering End */
//
//	/* Vendor Analytics Applications Offering Begin */
//	public void addVendorAnalyticsApplicationsOffering(VendorAnalyticsApplicationsOffering
//            vendorAnalyticsApplicationsOffering) throws ApplicationException;
//	public VendorAnalyticsApplicationsOffering fetchVendorAnalyticsApplicationsOffering(
//            String productId) throws ApplicationException;
//	public void deleteVendorAnalyticsApplicationsOffering(String productId) throws ApplicationException;
//	public List<VendorAnalyticsApplicationsOffering> getVendorAnalyticsApplicationsOffering(
//            String vendorName) throws ApplicationException;
//
//	String findAllVendorOffering(String vendorName) throws ApplicationException;
//	/* Vendor Analytics Applications Offering End */
//
// }
