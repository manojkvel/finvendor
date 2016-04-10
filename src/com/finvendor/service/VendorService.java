/**
 * 
 */
package com.finvendor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.finvendor.form.FileDetails;
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

@Service
public interface VendorService {

	void saveVendorInfo(Vendor vendor);
	Vendor getVendorInfoByEmail(String email);
	Vendor getVendorInfoByUserName(String userName);
	void updateVendorPersonalInfoTab(Vendor vendor, String username);
	//AssetClass getAssetClassDetails(String asset_class);
	//SecurityType getSecurityTypes(String securities);
	void updateVendorOfferingDetails(VendorOffering vendorOffering);
	//Region getRegionsByName(String regionsName);
	//Country getCountryByName(String countryName);
	//Country getCountryById(String countryId);
	//Exchange getExchangesByName(String exchangeName);
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

	//String getRegion(String country);

	List<VendorAwardsMap> listVendorAwardDetails(String id);

	Boolean isAwardAlreadyExist(String value);

	Boolean isSolutionAlreadyExist(String value);

	void deleteAwardDetails(String objectVar);

	Boolean isTradingSoftwareDetailsOfferingExist(String value);

	void updateVendorLogo(FileDetails ufile, String username);

	
	List<Object[]> getMarketDataVendorOfferingsForProfile(String vendorId);
	public List<Object[]> getVendorAwardDetailsForProfile(String vendorId);
	
}
