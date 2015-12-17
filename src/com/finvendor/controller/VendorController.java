/**
 * 
 */
package com.finvendor.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.finvendor.model.AssetClass;
import com.finvendor.model.AssetClassSecurityMap;
import com.finvendor.model.Awards;
import com.finvendor.model.Cost;
import com.finvendor.model.Country;
import com.finvendor.model.Exchange;
import com.finvendor.model.Region;
import com.finvendor.model.SecurityType;
import com.finvendor.model.Solutions;
import com.finvendor.model.Support;
import com.finvendor.model.Vendor;
import com.finvendor.model.VendorAwardsMap;
import com.finvendor.model.VendorMyofferingsDataCoverage;
import com.finvendor.model.VendorOffering;
import com.finvendor.model.VendorRegionCountryExchangeMap;
import com.finvendor.model.VendorSolution;
import com.finvendor.model.VendorSupport;
import com.finvendor.model.VendorSupportCoverage;
import com.finvendor.service.MarketDataAggregatorsService;
import com.finvendor.service.UserService;
import com.finvendor.service.VendorService;
import com.finvendor.util.CommonUtils;
import com.finvendor.util.RequestConstans;
import com.google.gson.Gson;

@Controller
public class VendorController {
	
	private static Logger logger = LoggerFactory.getLogger(VendorController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="vendorService")
	private VendorService vendorService;
	
	@Autowired
	private MarketDataAggregatorsService marketDataAggregatorsService;
	
	/**
	 * method for navigate vendor profile
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	
	@RequestMapping(value=RequestConstans.Vendor.VENDOR_MY_PROFILE, method=RequestMethod.GET)
	public ModelAndView vendorMyProfile(HttpServletRequest request,@ModelAttribute("users") User users,
			@RequestParam(value = "RaYUnA", required = false) String username,
			@ModelAttribute("vendor") Vendor vendor){
		logger.info("Mehtod for vendorNavigation--:");
		List<AssetClass> assetClasses = null;
		List<Region> regions = null;
		List<Country> countries = null;
		List<Exchange> exchanges = null;
		List<Support> supports = null;
		List<Cost> costs = null;
		List<Awards> awards = null;
		ModelAndView modelAndView=new ModelAndView(RequestConstans.Login.VENDOR_INFO);
		try{
			if(request.getSession().getAttribute("loggedInUser") == null){
				return new ModelAndView(RequestConstans.Login.HOME);
			}
			assetClasses = marketDataAggregatorsService.getAllAssetClass();
			regions = marketDataAggregatorsService.getAllRegionClass();
			countries = marketDataAggregatorsService.getAllCountries();
			exchanges = marketDataAggregatorsService.getAllExchanges();
			supports =  marketDataAggregatorsService.getAllVendorSupports();
			costs  = marketDataAggregatorsService.getAllCostInfo();
			awards = marketDataAggregatorsService.getAllAwards();
			
			username = CommonUtils.decrypt(username.getBytes());
			
			vendor = userService.getUserDetailsByUsername(username).getVendor();
			
			//vendor = vendorService.getVendorDetails(username);
			modelAndView.addObject("assetClasses", assetClasses);
			modelAndView.addObject("regions", regions);
			modelAndView.addObject("regionslist", regions);
			modelAndView.addObject("countries", countries);
			modelAndView.addObject("exchanges", exchanges);
			modelAndView.addObject("supports", supports);
			modelAndView.addObject("costs", costs);
			modelAndView.addObject("awards", awards);
			modelAndView.addObject("myprofiletab", "myprofile");
			modelAndView.addObject("username", username);
			modelAndView.addObject("vendor", vendor);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Mehtod for vendorNavigation--:");
		}
		return modelAndView;
	}
	
	@RequestMapping(value=RequestConstans.Vendor.VENDOR_MY_OFFERINGS, method=RequestMethod.GET)
	public ModelAndView vendorMyOfferings(HttpServletRequest request,
			@RequestParam(value = "RaYUnA", required = false) String username) {
		
		logger.debug("Entering VendorController : vendorMyOfferings");
		List<AssetClass> assetClasses = null;
		List<Region> regions = null;
		List<Country> countries = null;
		List<Exchange> exchanges = null;
		List<Support> supports = null;
		List<Cost> costs = null;
		List<Awards> awards = null;
		Vendor vendor = null;
		String[] vendorOfferings = null;
		ModelAndView modelAndView = new ModelAndView(RequestConstans.Vendor.VENDOR_MY_OFFERINGS);
		
		try{
			assetClasses = marketDataAggregatorsService.getAllAssetClass();
			regions = marketDataAggregatorsService.getAllRegionClass();
			countries = marketDataAggregatorsService.getAllCountries();
			exchanges = marketDataAggregatorsService.getAllExchanges();
			supports =  marketDataAggregatorsService.getAllVendorSupports();
			costs  = marketDataAggregatorsService.getAllCostInfo();
			awards = marketDataAggregatorsService.getAllAwards();
			username = CommonUtils.decrypt(username.getBytes());
			vendor = userService.getUserDetailsByUsername(username).getVendor();
			String vendorCompanyTypes = vendor.getCompanyType();
			logger.debug("Registered Company Types for Vendor {} are {}", username, vendorCompanyTypes);
			vendorOfferings  = vendorCompanyTypes.split(",");
			for (String vendormyofferingtags : vendorOfferings) {
				 if(vendormyofferingtags.equals(RequestConstans.Vendor.DATA_AGGREGATOR)) {
					 logger.debug("Set My Offerings tab of {} for {}", username, RequestConstans.Vendor.DATA_AGGREGATOR);
					 modelAndView.addObject("dataaggregator", vendormyofferingtags);
				 }
				 if(vendormyofferingtags.equals(RequestConstans.Vendor.TRADING_APPLICATION)) {
					 logger.debug("Set My Offerings tab of {} for {}", username, RequestConstans.Vendor.TRADING_APPLICATION);
					 modelAndView.addObject("tradingapplication", vendormyofferingtags);
				 }
				 if(vendormyofferingtags.equals(RequestConstans.Vendor.ANALYTICS_APPLICATION)) {
					 logger.debug("Set My Offerings tab of {} for {}", username, RequestConstans.Vendor.ANALYTICS_APPLICATION);
					 modelAndView.addObject("analyticsapplication", vendormyofferingtags);
				 }
				 if(vendormyofferingtags.equals(RequestConstans.Vendor.RESEARCH_REPORT)) {
					 logger.debug("Set My Offerings tab of {} for {}", username, RequestConstans.Vendor.RESEARCH_REPORT);
					 modelAndView.addObject("researchreport", vendormyofferingtags);
				 }
			} 			
			modelAndView.addObject("assetClasses", assetClasses);
			modelAndView.addObject("regions", regions);
			modelAndView.addObject("regionslist", regions);
			modelAndView.addObject("countries", countries);
			modelAndView.addObject("exchanges", exchanges);
			modelAndView.addObject("supports", supports);
			modelAndView.addObject("costs", costs);
			modelAndView.addObject("awards", awards);
			//modelAndView.addObject("myofferingstab", "myofferings");			
			modelAndView.addObject("username", username);
		}catch (Exception exp) {
			logger.error("VendorController : vendorMyOfferings - Error reading details", exp);
		}
		logger.debug("Leaving VendorController : vendorMyOfferings");
		return modelAndView;
	}
	
	
	/**
	 * method for navigate vendor profile
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	
	@RequestMapping(value=RequestConstans.Vendor.VENDOR_RFP_INBOX, method=RequestMethod.GET)
	public ModelAndView vendorRFPInbox(HttpServletRequest request,@ModelAttribute("users") User users,
			@RequestParam(value = "RaYUnA", required = false) String username){
		logger.info("Mehtod for vendorNavigation--:");
		List<AssetClass> assetClasses = null;
		List<Region> regions = null;
		List<Country> countries = null;
		List<Exchange> exchanges = null;
		List<Support> supports = null;
		List<Cost> costs = null;
		List<Awards> awards = null;
		@SuppressWarnings("unused")
		Vendor vendor=null;
		ModelAndView modelAndView=new ModelAndView(RequestConstans.Vendor.VENDOR_RFP_INBOX);
		try{
			assetClasses = marketDataAggregatorsService.getAllAssetClass();
			regions = marketDataAggregatorsService.getAllRegionClass();
			countries = marketDataAggregatorsService.getAllCountries();
			exchanges = marketDataAggregatorsService.getAllExchanges();
			supports =  marketDataAggregatorsService.getAllVendorSupports();
			costs  = marketDataAggregatorsService.getAllCostInfo();
			awards = marketDataAggregatorsService.getAllAwards();
			username = CommonUtils.decrypt(username.getBytes());
			
			vendor = vendorService.getVendorInfoByEmail(username);
			
			modelAndView.addObject("assetClasses", assetClasses);
			modelAndView.addObject("regions", regions);
			modelAndView.addObject("regionslist", regions);
			modelAndView.addObject("countries", countries);
			modelAndView.addObject("exchanges", exchanges);
			modelAndView.addObject("supports", supports);
			modelAndView.addObject("costs", costs);
			modelAndView.addObject("awards", awards);
			modelAndView.addObject("RFPInbox", "RFPInbox");
			
			modelAndView.addObject("username", username);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Mehtod for vendorNavigation--:");
		}
		return modelAndView;
	}
	
	/**
	 * method for navigate vendor search data buyers
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	
	@RequestMapping(value=RequestConstans.Vendor.VENDOR_SEARCH_DATABUYER, method=RequestMethod.GET)
	public ModelAndView vendorSearchDataBuyers(HttpServletRequest request,@ModelAttribute("users") User users,
			@RequestParam(value = "RaYUnA", required = false) String username){
		logger.info("Mehtod for vendorsearch data buyers--:");
		@SuppressWarnings("unused")
		Vendor vendor=null;
		List<AssetClass> assetClasses = null;
		List<Region> regions = null;
		ModelAndView modelAndView=new ModelAndView(RequestConstans.Vendor.VENDOR_SEARCH_DATABUYER);
		try{
			username = CommonUtils.decrypt(username.getBytes());
			assetClasses = marketDataAggregatorsService.getAllAssetClass();
			regions = marketDataAggregatorsService.getAllRegionClass();
			
			modelAndView.addObject("assetClasses", assetClasses);
			modelAndView.addObject("regions", regions);
			modelAndView.addObject("username", username);
			modelAndView.addObject("searchDataBuyers", "searchDataBuyers");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Mehtod for vendorsearch data buyers--:");
		}
		return modelAndView;
	}
	
	
	/**
	 * method to full Security types
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value =RequestConstans.Vendor.LOAD_VENDOR_SECURITY_TYPES, method = RequestMethod.GET)
	public ModelAndView loadVendorSecurityType(@RequestParam(value = "RAyuL", required = false) String assetType) {
		ModelAndView modelAndView = new ModelAndView("vendorpage/vendorsecuritylist");
		List<AssetClassSecurityMap> assetClassSecurityMaps = null;
		try {
			assetType = CommonUtils.decrypt(assetType.getBytes());
			if(!assetType.equals("") && !assetType.equals("-SELECT-")){
				AssetClass assetClass = marketDataAggregatorsService.getAssetClassByName(assetType);
				assetClassSecurityMaps = marketDataAggregatorsService.getSecurityTypeByAssetClassId(Integer.parseInt(assetClass.getAsset_class_cd()));
		 	}
			modelAndView.addObject("assetClassVendorSecurityMaps", assetClassSecurityMaps);
		} catch (Exception ex) {
			logger.error("Exception in loadSecurityType -- ", ex);
			modelAndView.addObject("errorMsg", "Unable to load Security type details, Please contact administrator");
		}
		return modelAndView;
	}
	/**
	 * method to full Security types
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value =RequestConstans.Vendor.LOAD_VENDOR_SECURITY_AWARD_TYPES, method = RequestMethod.GET)
	public ModelAndView loadVendorAwardSecurityType(@RequestParam(value = "RAyuL", required = false) String assetType) {
		ModelAndView modelAndView = new ModelAndView("vendorpage/vendorsecurityawardlist");
		List<AssetClassSecurityMap> assetClassSecurityMaps = null;
		try {
			assetType = CommonUtils.decrypt(assetType.getBytes());
			if(!assetType.equals("") && !assetType.equals("-SELECT-")){
				AssetClass assetClass = marketDataAggregatorsService.getAssetClassByName(assetType);
				assetClassSecurityMaps = marketDataAggregatorsService.getSecurityTypeByAssetClassId(assetClass.getAsset_class_id());
		 	}
			modelAndView.addObject("assetClassVendorSecurityAwardMaps", assetClassSecurityMaps);
		} catch (Exception ex) {
			logger.error("Exception in loadSecurityType -- ", ex);
			modelAndView.addObject("errorMsg", "Unable to load Security type details, Please contact administrator");
		}
		return modelAndView;
	}
	/**
	 * method to full Security types
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value =RequestConstans.Vendor.LOAD_VENDOR_SECURITY_DISTRI_TYPES, method = RequestMethod.GET)
	public ModelAndView loadVendorDstributionSecurityType(@RequestParam(value = "RAyuL", required = false) String assetType) {
		ModelAndView modelAndView = new ModelAndView("vendorpage/vendorsecurityDistrilist");
		List<AssetClassSecurityMap> assetClassSecurityMaps = null;
		try {
			assetType = CommonUtils.decrypt(assetType.getBytes());
			if(!assetType.equals("") && !assetType.equals("-SELECT-")){
				AssetClass assetClass = marketDataAggregatorsService.getAssetClassByName(assetType);
				assetClassSecurityMaps = marketDataAggregatorsService.getSecurityTypeByAssetClassId(Integer.parseInt(assetClass.getAsset_class_cd()));
		 	}
			modelAndView.addObject("assetClassVendorSecurityDistriMaps", assetClassSecurityMaps);
		} catch (Exception ex) {
			logger.error("Exception in loadSecurityType -- ", ex);
			modelAndView.addObject("errorMsg", "Unable to load Security type details, Please contact administrator");
		}
		return modelAndView;
	}
	
	
	/**
	 * method to full Security types
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value =RequestConstans.Vendor.LOAD_VENDOR_FOCUS_SECURITY_DISTRI_TYPES, method = RequestMethod.GET)
	public ModelAndView loadVendorFocusSecurityType(@RequestParam(value = "RAyuL", required = false) String assetType) {
		ModelAndView modelAndView = new ModelAndView("vendorpage/vendorfocussecuritylist");
		List<AssetClassSecurityMap> assetClassSecurityMaps = null;
		try {
			assetType = CommonUtils.decrypt(assetType.getBytes());
			if(!assetType.equals("") && !assetType.equals("-SELECT-")){
				AssetClass assetClass = marketDataAggregatorsService.getAssetClassByName(assetType);
				assetClassSecurityMaps = marketDataAggregatorsService.getSecurityTypeByAssetClassId(Integer.parseInt(assetClass.getAsset_class_cd()));
		 	}
			modelAndView.addObject("assetclassVendorloadFocusSecurityTypes", assetClassSecurityMaps);
		} catch (Exception ex) {
			logger.error("Exception in loadSecurityType -- ", ex);
			modelAndView.addObject("errorMsg", "Unable to load Security type details, Please contact administrator");
		}
		return modelAndView;
	}
	
	/**
	 * method to update vendor personal info
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value =RequestConstans.Vendor.UPDATE_VENDOR_PERSONAL_INFO_TAB, method = RequestMethod.GET)
	public ModelAndView updateVendorPersonalTabInfo(@ModelAttribute("vendor") Vendor vendor,
			@RequestParam(value = "venFirstname", required = false) String venFirstname,
			@RequestParam(value = "venLastname", required = false) String venLastname,
			@RequestParam(value = "venDesignation", required = false) String venDesignation,
			@RequestParam(value = "venCompany", required = false) String venCompany,
			@RequestParam(value = "venCompanyUrl", required = false) String venCompanyUrl,
			@RequestParam(value = "venCompanyInfo", required = false) String venCompanyInfo,
			@RequestParam(value = "venPrimEmail", required = false) String venPrimEmail,
			@RequestParam(value = "venSecEmail", required = false) String venSecEmail,
			@RequestParam(value = "venPhoneNum", required = false) String venPhoneNum,
			@RequestParam(value = "venRegionOfIncorp", required = false) String venRegionOfIncorp,
			@RequestParam(value = "venCountryOfIncorp", required = false) String venCountryOfIncorp,
			@RequestParam(value = "venCompanyLogo", required = false) String venCompanyLogoFile,
			@ModelAttribute("region") Region region,
			@ModelAttribute("country") Country country) {
		ModelAndView modelAndView = new ModelAndView("empty");
		logger.info("Mehtod for update Vendor Personal info tab--:");
		User appUser = null;
		
		try {
			System.out.println("I'm executing successfully----:");
			appUser = (User)SecurityContextHolder.getContext().
					getAuthentication().getPrincipal();
			if(!venFirstname.equals("")){
				vendor.setFirstName(venFirstname);
				vendor.setLastName(venLastname);
				vendor.setDesignation(venDesignation);
				vendor.setCompanyUrl(venCompanyUrl);
				vendor.setCompanyInfo(venCompanyInfo);
				vendor.setCompany(venCompany);
				//vendor.setEmail(venPrimEmail);
				vendor.setSecondaryEmail(venSecEmail);
				vendor.setTelephone(venPhoneNum);
				// Image upload code in vendor dashboard---:
				/*@SuppressWarnings("deprecation")
				Blob blob = Hibernate.createBlob(venCompanyLogoFile.getInputStream());
				vendor.setCertificateFile(venCompanyLogoFile.getOriginalFilename());
				vendor.setCompany_logo(blob);
				vendor.setContentType(venCompanyLogoFile.getContentType());
				*/
				region = vendorService.getRegionsByName(venRegionOfIncorp);
				vendor.setRegionofincorp(region.getRegion_id());
				country = vendorService.getCountryByName(venCountryOfIncorp);
				vendor.setCountryofincorp(country.getCountry_id().toString());
				vendorService.updateVendorPersonalInfoTab(vendor,appUser.getUsername());
				vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();
				//vendor = vendorService.getVendorDetails(appUser.getUsername());
				modelAndView.addObject("vendorDetails", vendor);
		 	}
		} catch (Exception ex) {
			logger.error("Mehtod for update Vendor Personal info tab -- ", ex);
			modelAndView.addObject("errorMsg", "Unable to update vendor personal details, Please contact administrator");
		}
		return modelAndView;
	}
	
	/**
	 * method to update vendor support coverage info
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value =RequestConstans.Vendor.UPDATE_VENDOR_SUPPORT_COVEAGE_TAB, method = RequestMethod.GET)
	public ModelAndView updateVendorSupportCoverageInfo(@ModelAttribute("vendor") Vendor vendor,
			@ModelAttribute("assetClass") AssetClass assetClass,
			@ModelAttribute("securityType") SecurityType securityType,
			@ModelAttribute("region") Region region,
			@ModelAttribute("country") Country country,
			@ModelAttribute("exchange") Exchange exchange,
			@ModelAttribute("vendorOffering") VendorOffering vendorOffering,
			@ModelAttribute("solutions") Solutions solutions,
			@ModelAttribute("vendorRegionCountryExchangeMap") VendorRegionCountryExchangeMap vendorRegionCountryExchangeMap,
			@RequestParam(value = "supportCoverageInfo", required = false) String supportCoverageInfo) {
		ModelAndView modelAndView = new ModelAndView("empty");
		logger.info("Mehtod for update Vendor Personal info tab--:");
		User appUser = null;
		try {
			System.out.println("getting support coverage info--:" + supportCoverageInfo);
			Gson gson = new Gson();
			VendorSupportCoverage[] vendorSupportCoverages = null;
			String[] securityTypes= null;
			String[] regions = null;
			String[] countries = null;
			String[] exchanges = null;
			appUser = (User)SecurityContextHolder.getContext().
					getAuthentication().getPrincipal();
			if(!supportCoverageInfo.equals("") && supportCoverageInfo != null){
				vendorSupportCoverages = gson.fromJson(replaceJsonInput(supportCoverageInfo.toString()), VendorSupportCoverage[].class);
				//vendor = vendorService.getVendorDetails(appUser.getUsername());
				vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();
				vendor.setId(vendor.getId());
				if(vendorSupportCoverages.length > 0)
				for (VendorSupportCoverage vendorSupportCoverage : vendorSupportCoverages) {
					  assetClass = vendorService.getAssetClassDetails(vendorSupportCoverage.getAsset_class());
					  assetClass.setAsset_class_id(assetClass.getAsset_class_id());
					  securityTypes = vendorSupportCoverage.getSecurity_type().split(",");	
					  regions = vendorSupportCoverage.getCoverage_region().split(",");
					  countries = vendorSupportCoverage.getCoverage_country().split(",");
					  exchanges = vendorSupportCoverage.getCoverage_exchange().split(",");
					  if(securityTypes.length > 0)
						for (String securities : securityTypes) {
							securityType = vendorService.getSecurityTypes(securities);
							securityType.setSecurity_type_id(securityType.getSecurity_type_id());
							solutions.setSolution_id(1);
							vendorOffering.setAssetClass(assetClass);
							vendorOffering.setSecurityType(securityType);
							vendorOffering.setVendor(vendor);
							vendorOffering.setSolutions(solutions);
							vendorOffering.setName("Best plans");
							// Updating vendor offering details
							vendorService.updateVendorOfferingDetails(vendorOffering);
						}
						for (String regionsName : regions) {
							region = vendorService.getRegionsByName(regionsName);
							vendorRegionCountryExchangeMap.setVendor(vendor);
							region.setRegion_id(region.getRegion_id());
							vendorRegionCountryExchangeMap.setRegion(region);
							for (String countryName : countries) {
								country = vendorService.getCountryByName(countryName);
								country.setCountry_id(country.getCountry_id());
								vendorRegionCountryExchangeMap.setCountry(country);
								for (String exchangeName : exchanges) {
									exchange = vendorService.getExchangesByName(exchangeName);
									exchange.setExchange_id(exchange.getExchange_id());
									vendorRegionCountryExchangeMap.setExchange(exchange);
									// Updating vendor region, country and exchange details
								    vendorService.updateVendorRegionCountryExchangeInfos(vendorRegionCountryExchangeMap);
								}
							}
					}
					
				}
				
		 	}
		} catch (Exception ex) {
			logger.error("Mehtod for update Vendor Support Coverage info-- ", ex);
			modelAndView.addObject("errorMsg", "Unable to update vendor support coverage info, Please contact administrator");
		}
		return modelAndView;
	}

	/**
	 * method to convert JSON values
	 * 
	 * @return String
	 * @throws Exception
	 *             the exception
	 */
	private String replaceJsonInput(String inputField) {
		String returnValue = "";
		try {
			returnValue = inputField.replace("Asset Class", "asset_class");
			returnValue = returnValue.replace("Security type", "security_type");
			returnValue = returnValue.replace("Data Coverage Region", "coverage_region");
			returnValue = returnValue.replace("Data Coverage Country", "coverage_country");
			returnValue = returnValue.replace("Data Coverage Exchange", "coverage_exchange");
			returnValue = returnValue.replace("Data Attribute", "data_attribute");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return returnValue;
	}
	
	/**
	 * method to update vendor award details
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value =RequestConstans.Vendor.UPDATE_VENDOR_AWARD_DETAILS_TAB, method = RequestMethod.GET)
	public ModelAndView updateVendorAwardDetails(@ModelAttribute("vendor") Vendor vendor,
			@ModelAttribute("awards") Awards awards,
			@ModelAttribute("assetClass") AssetClass assetClass,
			@ModelAttribute("securityType") SecurityType securityType,
			@ModelAttribute("vendorAwardsMap") VendorAwardsMap vendorAwardsMap,
			@RequestParam(value = "awardassetclass", required = false) String awardassetclass,
			@RequestParam(value = "assetClassVendorSecurityAwardMaps", required = false) String securityTypeName,
			@RequestParam(value = "awardname", required = false) String awardname,
			@RequestParam(value = "awardsponsor", required = false) String awardsponsor,
			@RequestParam(value = "awardedyear", required = false) String awardedyear) {
		ModelAndView modelAndView = new ModelAndView("empty");
		logger.info("Mehtod for update Vendor Personal info tab--:");
		User appUser = null;
		try {
			appUser = (User)SecurityContextHolder.getContext().
					getAuthentication().getPrincipal();
			if(!awardassetclass.equals("")){
				vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();
				//vendor = vendorService.getVendorDetails(appUser.getUsername());
				vendor.setId(vendor.getId());
				assetClass = vendorService.getAssetClassDetails(awardassetclass);
				securityType = vendorService.getSecurityTypes(securityTypeName);
				awards.setName(awardname);
				awards.setSponsor(awardsponsor);
				awards.setLogo("");
				awards.setRank(UUID.randomUUID().toString());
				// Saving Award details
				awards = vendorService.saveAwardDetails(awards);
				awards.setAward_id(awards.getAward_id());
				
				vendorAwardsMap.setYearofaward(awardedyear);
				vendorAwardsMap.setAwardedfor(awards.getSponsor());
				vendorAwardsMap.setPosition(awardedyear);
				vendorAwardsMap.setAwards(awards);
				vendorAwardsMap.setVendor(vendor);
				// Update vendor award details
				vendorService.updateVendorAwardDetails(vendorAwardsMap);
		 	}
		} catch (Exception ex) {
			logger.error("Mehtod for update Award details tab -- ", ex);
			modelAndView.addObject("errorMsg", "Unable to update vendor award details, Please contact administrator");
		}
		return modelAndView;
	}
	
	
	/**
	 * method to update vendor data buyers
	 * 	 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value =RequestConstans.Vendor.UPDATE_VENDOR_DATA_SEARCH_BUYERS, method = RequestMethod.GET)
	public ModelAndView updateVendorSearchDataBuyers(@ModelAttribute("vendor") Vendor vendor,
			@ModelAttribute("assetClass") AssetClass assetClass,
			@ModelAttribute("securityType") SecurityType securityType,
			@RequestParam(value = "databuyername", required = false) String databuyername,
			@RequestParam(value = "focusregion", required = false) String focusregion,
			@RequestParam(value = "focusassetclass", required = false) String focusassetclass,
			@RequestParam(value = "focussecuritytype", required = false) String focussecuritytype) {
		ModelAndView modelAndView = new ModelAndView("empty");
		logger.info("Mehtod for update Vendor Personal info tab--:");
		User appUser = null;
		try {
			appUser = (User)SecurityContextHolder.getContext().
					getAuthentication().getPrincipal();
			if(!databuyername.equals("")){
				vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();
				//vendor = vendorService.getVendorDetails(appUser.getUsername());
				vendor.setId(vendor.getId());
		 	}
		} catch (Exception ex) {
			logger.error("Mehtod for update data buyers tab -- ", ex);
			modelAndView.addObject("errorMsg", "Unable to update vendor data buyers details, Please contact administrator");
		}
		return modelAndView;
	}
	
	/**
	 * method to update vendor my offerings data coverage info
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value =RequestConstans.Vendor.UPDATE_VENDOR_MYOFFEINGS_DATA_COVEAGE_TAB, method = RequestMethod.GET)
	public ModelAndView updateVendorMyOfferingsDataCoverageInfo(@ModelAttribute("vendor") Vendor vendor,
			@ModelAttribute("solutions") Solutions solutions,
			@ModelAttribute("region") Region region,
			@ModelAttribute("country") Country country,
			@ModelAttribute("cost") Cost cost,
			@ModelAttribute("support") Support support,
			@ModelAttribute("vendorSolution") VendorSolution vendorSolution,
			@ModelAttribute("vendorSupport") VendorSupport vendorSupport,
			@RequestParam(value = "dataCoverageInfo", required = false) String dataCoverageInfo) {
		ModelAndView modelAndView = new ModelAndView("empty");
		logger.info("Mehtod for update Vendor my offerings data coverage info tab--:");
		User appUser = null;
		try {
			System.out.println("getting support coverage info--:" + dataCoverageInfo);
			Gson gson = new Gson();
			VendorMyofferingsDataCoverage[] vendorMyofferingsDataCoverages = null;
			String[] regions = null;
			String[] countries = null;
			String[] costs = null;
			String[] supports = null;
			appUser = (User)SecurityContextHolder.getContext().
					getAuthentication().getPrincipal();
			if(!dataCoverageInfo.equals("") && dataCoverageInfo != null){
				vendorMyofferingsDataCoverages = gson.fromJson(replaceVendorMyOfferingsDataCoverageJsonInput(dataCoverageInfo.toString()), VendorMyofferingsDataCoverage[].class);
				vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();
				//vendor = vendorService.getVendorDetails(appUser.getUsername());
				vendor.setId(vendor.getId());
				if(vendorMyofferingsDataCoverages.length > 0)
				for (VendorMyofferingsDataCoverage vendorMyofferings : vendorMyofferingsDataCoverages) {
					  regions = vendorMyofferings.getCoverage_region().split(",");
					  countries = vendorMyofferings.getCoverage_country().split(",");
					  supports = vendorMyofferings.getSupport_timings().split(",");
					  costs = vendorMyofferings.getVendor_costrange().split(",");
					  if(supports.length > 0)
						for (String costNames : costs) {
							if(costNames.equals("$200"))
							  cost = vendorService.getCostInfo(RequestConstans.CostValues.$200);
							else if(costNames.equals("$300"))
							  cost = vendorService.getCostInfo(RequestConstans.CostValues.$300);
							cost.setCost_id(cost.getCost_id());
							solutions.setSolution_id(1);
							vendorSolution.setVendor(vendor);
							vendorSolution.setSolutions(solutions);
							vendorSolution.setCost(cost);
							// Updating vendor vendor solution details
							vendorSolution =	vendorService.updateVendorSolutionDetails(vendorSolution);
							for (String supportname : supports) {
								support = vendorService.getSupportInfo(supportname);
								support.setSupport_id(support.getSupport_id());
								vendorSolution.setVendor_solution_id(vendorSolution.getVendor_solution_id());
								vendorSupport.setSupport(support);
								vendorSupport.setVendorSolution(vendorSolution);
								vendorSupport.setVendor(vendor);
								vendorService.updateVendorSupportInfo(vendorSupport);
							}
						}
				}
				
		 	}
		} catch (Exception ex) {
			logger.error("Mehtod for update Vendor  my offerings data coverage info-- ", ex);
			modelAndView.addObject("errorMsg", "Unable to update vendor s my offerings data coverage info, Please contact administrator");
		}
		return modelAndView;
	}

	/**
	 * method to convert my offerings data coverage JSON values
	 * 
	 * @return String
	 * @throws Exception
	 *             the exception
	 */
	private String replaceVendorMyOfferingsDataCoverageJsonInput(String inputField) {
		String returnValue = "";
		try {
			returnValue = inputField.replace("Coverage Region", "coverage_region");
			returnValue = returnValue.replace("Coverage Country", "coverage_country");
			returnValue = returnValue.replace("Support Timings", "support_timings");
			returnValue = returnValue.replace("Vendor Cost Range", "vendor_costrange");
			returnValue = returnValue.replace("Phone Number", "phone_number");
			returnValue = returnValue.replace("Email", "email");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return returnValue;
	}

	

}
