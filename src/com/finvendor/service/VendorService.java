package com.finvendor.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.finvendor.exception.ApplicationException;
import com.finvendor.form.FileDetails;
import com.finvendor.model.Awards;
import com.finvendor.model.Cost;
import com.finvendor.model.SolutionTypes;
import com.finvendor.model.Solutions;
import com.finvendor.model.Support;
import com.finvendor.model.Vendor;
import com.finvendor.model.VendorAnalystProfile;
import com.finvendor.model.VendorAnalyticsApplicationsOffering;
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
import com.finvendor.util.VendorEnum;

@Service
public interface VendorService {

	void saveVendorInfo(Vendor vendor);
	Vendor getVendorInfoByEmail(String email);
	Vendor getVendorInfoByUserName(String userName);
	void updateVendorPersonalInfoTab(Vendor vendor, String username);
	void updateVendorOfferingDetails(VendorOffering vendorOffering);
	void updateVendorRegionCountryExchangeInfos(
			VendorRegionCountryExchangeMap vendorRegionCountryExchangeMap);
	Awards saveAwardDetails(Awards awards);
	void updateVendorAwardDetails(VendorAwardsMap vendorAwardsMap);
	Cost getCostInfo(String costNames);
	VendorSolution updateVendorSolutionDetails(VendorSolution vendorSolution);
	List<Solutions> listVednorSolution(String id);
	List<Solutions> deleteVendorSolution(String id);
	Support getSupportInfo(String supportname);
	void updateVendorSupportInfo(VendorSupport vendorSupport);
	List<VendorOffering> getVendorOfferingDetails(String id);
	public Solutions getSolutionsInfo(String solutionId);
	public Solutions addSolutionsInfo(Solutions solutions);	
	public SolutionTypes getSolutionTypes(String solutionName);	
	public List<Solutions> getSolutionsBasedOnOfferingTypes(String offeringName, Vendor vendor);
	String addVendorDataCoverage(VendorDataCoverage vendorDataCoverage);	
	String addTradingCapabilitiesSupported(VendorTradingCapabilitiesSupported tradingCapabilitiesSupported);
	void addTradingSoftwareDetails(VendorTradingSoftwareDetails tradingSoftwareDetails);
	void addAnalyticsfeaturesSupported(VendorAnalyticsfeaturesSupported analyticsfeaturesSupported);
	void addAnalyticsSoftwareDetails(VendorAnalyticsSoftwareDetails analyticsSoftwareDetails);
	void addResearchCoverage(VendorResearchCoverage researchCoverage);
	void addResearchDetails(VendorResearchDetails researchDetails);
	void addAnalystProfile(VendorAnalystProfile analystProfile);
	
	public List<VendorTradingCapabilitiesSupported> listTradingCapabilitiesSupported(String objectId);
	public List<VendorTradingSoftwareDetails> listTradingSoftwareDetails(String objectId);
	public List<VendorAnalyticsfeaturesSupported> listAnalyticsfeaturesSupported(String objectId);
	public List<VendorAnalyticsSoftwareDetails> listAnalyticsSoftwareDetails(String objectId);
	public List<VendorResearchCoverage> listResearchCoverage(String objectId);
	public List<VendorResearchDetails> listResearchDetails(String objectId);
	public List<VendorAnalystProfile> listAnalystProfile(String objectId);
	
	void deleteTradingCapabilitiesSupported(String objectId);
	void deleteTradingSoftwareDetails(String objectId);
	void deleteAnalyticsfeaturesSupported(String objectId);
	void deleteAnalyticsSoftwareDetails(String objectId);
	void deleteResearchCoverage(String objectId);
	void deleteResearchDetails(String objectId);
	void deleteAnalystProfile(String objectId);
	
	List<VendorDataCoverage> listVendorDataCoverage(String id);

	String addVendorDistribution(VendorDistribution vendorDistribution);
	List<VendorDistribution> listVendorDistribution(String id);

	void deleteVendorDataCoverage(String selectedId);

	void deleteVendorDistribution(String selectedId);

	List<VendorTradingSoftwareDetails> listTradingSoftwareDetailsBasedOnSolutionId(String solutionId);

	List<VendorResearchCoverage> listResearchReportingVendorOfferingBasedOnSolutionId(String solutionId);

	List<VendorAwardsMap> listVendorAwardDetails(String id);

	Boolean isAwardAlreadyExist(String value);

	Boolean isSolutionAlreadyExist(String value);

	void deleteAwardDetails(String objectVar);

	Boolean isTradingSoftwareDetailsOfferingExist(String value);

	void updateVendorLogo(FileDetails ufile, String username);

	
	List<Object[]> getMarketDataVendorOfferingsForProfile(String vendorId);
	List<Object[]> getTradingApplicationOfferingsForProfile(String vendorId);
	List<Object[]> getAnalyticsApplicationOfferingsForProfile(String vendorId);
	List<Object[]> getResearchReportOfferingsForProfile(String vendorId);
	public List<Object[]> getVendorAwardDetailsForProfile(String vendorId);
	
	/* Vendor Data Aggregator Offering Begin */
	public void addVendorDataAggregatorsOffering(VendorDataAggregatorsOffering 
			vendorDataAggregatorsOffering) throws ApplicationException;
	public VendorDataAggregatorsOffering fetchVendorDataAggregatorsOffering(
			String productId) throws ApplicationException;
	public void deleteVendorDataAggregatorsOffering(String productId) throws ApplicationException;
	public List<VendorDataAggregatorsOffering> getVendorDataAggregatorsOffering(
			String vendorName) throws ApplicationException;
	/* Vendor Data Aggregator Offering End */
	
	/* Vendor Trading Applications Offering Begin */
	public void addVendorTradingApplicationsOffering(VendorTradingApplicationsOffering 
			vendorTradingApplicationsOffering) throws ApplicationException;
	public VendorTradingApplicationsOffering fetchVendorTradingApplicationsOffering(
			String productId) throws ApplicationException;
	public void deleteVendorTradingApplicationsOffering(String productId) throws ApplicationException;
	public List<VendorTradingApplicationsOffering> getVendorTradingApplicationsOffering(
			String vendorName) throws ApplicationException;
	/* Vendor Trading Applications Offering End */
	
	/* Vendor Research Report Offering Begin */
	public void addVendorResearchReportsOffering(VendorResearchReportsOffering 
			vendorResearchReportsOffering) throws ApplicationException;
	public VendorResearchReportsOffering fetchVendorResearchReportsOffering(
			String productId) throws ApplicationException;
	public void deleteVendorResearchReportsOffering(String productId) throws ApplicationException;
	public List<VendorResearchReportsOffering> getVendorResearchReportsOffering(
			String vendorName) throws ApplicationException;
	/* Vendor Research Report Offering End */
	
	/* Vendor Analytics Applications Offering Begin */
	public void addVendorAnalyticsApplicationsOffering(VendorAnalyticsApplicationsOffering 
			vendorAnalyticsApplicationsOffering) throws ApplicationException;
	public VendorAnalyticsApplicationsOffering fetchVendorAnalyticsApplicationsOffering(
			String productId) throws ApplicationException;
	public void deleteVendorAnalyticsApplicationsOffering(String productId) throws ApplicationException;
	public List<VendorAnalyticsApplicationsOffering> getVendorAnalyticsApplicationsOffering(
			String vendorName) throws ApplicationException;
	/* Vendor Analytics Applications Offering End */
	
	boolean uploadFile(VendorEnum type, byte[] bytes, String destPath) throws IOException;
	void deleteFile(VendorEnum type, String path) throws IOException;
	String getAllVendorOffering(String vendorName) throws ApplicationException;
	String getCompanyName(String companyId) throws ApplicationException;
	
}
