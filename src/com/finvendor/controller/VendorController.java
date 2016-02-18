package com.finvendor.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.finvendor.exception.ApplicationException;
import com.finvendor.form.VendorAnalystProfileForm;
import com.finvendor.form.VendorAnalyticsSoftwareDetailsForm;
import com.finvendor.form.VendorResearchCoverageForm;
import com.finvendor.form.VendorResearchDetailsForm;
import com.finvendor.form.VendorTradingCapabilitiesSupportedForm;
import com.finvendor.form.VendorTradingSoftwareDetailsForm;
import com.finvendor.model.AssetClass;
import com.finvendor.model.AssetClassSecurityMap;
import com.finvendor.model.Awards;
import com.finvendor.model.Cost;
import com.finvendor.model.Country;
import com.finvendor.model.Exchange;
import com.finvendor.model.FileDetails;
import com.finvendor.model.FileFields;
import com.finvendor.model.OfferingFiles;
import com.finvendor.model.Region;
import com.finvendor.model.SecurityType;
import com.finvendor.model.SolutionTypes;
import com.finvendor.model.Solutions;
import com.finvendor.model.Support;
import com.finvendor.model.Vendor;
import com.finvendor.model.VendorAnalystProfile;
import com.finvendor.model.VendorAnalyticsSoftwareDetails;
import com.finvendor.model.VendorAwardsMap;
import com.finvendor.model.VendorDataCoverage;
import com.finvendor.model.VendorDistribution;
import com.finvendor.model.VendorMyofferingsDataCoverage;
import com.finvendor.model.VendorOffering;
import com.finvendor.model.VendorResearchCoverage;
import com.finvendor.model.VendorResearchDetails;
import com.finvendor.model.VendorSolution;
import com.finvendor.model.VendorSupport;
import com.finvendor.model.VendorTradingCapabilitiesSupported;
import com.finvendor.model.VendorTradingSoftwareDetails;
import com.finvendor.service.MarketDataAggregatorsService;
import com.finvendor.service.UserService;
import com.finvendor.service.VendorService;
import com.finvendor.util.CommonUtils;
import com.finvendor.util.RequestConstans;
import com.google.gson.Gson;


@Controller
public class VendorController {
	
	private static Logger logger = LoggerFactory.getLogger(VendorController.class);
	FileDetails ufile = new FileDetails();
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="vendorService")
	private VendorService vendorService;
	
	@Autowired
	private MarketDataAggregatorsService marketDataAggregatorsService;
	
	@RequestMapping(value=RequestConstans.Vendor.VENDOR_MY_PROFILE, method=RequestMethod.GET)
	public ModelAndView vendorMyProfile(HttpServletRequest request,
			@RequestParam(value = "RaYUnA", required = false) String username,
			@ModelAttribute("vendor") Vendor vendor) {
		
		logger.debug("Entering VendorController : vendorMyProfile");
		List<AssetClass> assetClasses = null;
		List<Region> regions = null;
		List<Country> countries = null;
		List<Exchange> exchanges = null;
		List<Support> supports = null;
		List<Cost> costs = null;
		List<Awards> awards = null;
		ModelAndView modelAndView = new ModelAndView(RequestConstans.Login.VENDOR_INFO);
		
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
			
			String telephone = vendor.getTelephone();
       		if(telephone != null && !telephone.isEmpty()){
       			String[] split = telephone.split("-");	
       			if(split.length == 2){
       			vendor.setTelephoneCode(split[0]);
       			vendor.setTelephone(split[1]);
       			}
       		}
			
			
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

			//Set<VendorOffering> listOfferings = marketDataAggregatorsService.listOfferings(vendor.getId());
			List<SecurityType> listSecurityType = marketDataAggregatorsService.listSecurityType();
			List<Solutions> solutions = vendorService.getSolutionsBasedOnOfferingTypes(RequestConstans.Vendor.DATA_AGGREGATOR);
			
			modelAndView.addObject("securityTypes",listSecurityType);
			modelAndView.addObject("solutions",solutions);
			//modelAndView.addObject("listOfferings",listOfferings);
			modelAndView.addObject("assetClasses", assetClasses);
			modelAndView.addObject("regions", regions);
			modelAndView.addObject("regionslist", regions);
			modelAndView.addObject("countries", countries);
			modelAndView.addObject("exchanges", exchanges);
			modelAndView.addObject("supports", supports);
			modelAndView.addObject("costs", costs);
			modelAndView.addObject("awards", awards);
			//modelAndView.addObject("myofferingstab", "myofferings");
			modelAndView.addObject("myprofiletab", "myprofile");
			modelAndView.addObject("username", username);
		}catch (Exception exp) {
			exp.printStackTrace();
			logger.error("VendorController : vendorMyOfferings - Error reading details", exp);
		}
		logger.debug("Leaving VendorController : vendorMyOfferings");
		return modelAndView;
	}
	
	
	
	@RequestMapping(value=RequestConstans.Vendor.VENDOR_SOLUTION, method=RequestMethod.GET)
	public ModelAndView vendorSolutions(HttpServletRequest request,
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
		ModelAndView modelAndView = new ModelAndView(RequestConstans.Vendor.VENDOR_SOLUTION);
		
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
			modelAndView.addObject("myprofiletab", "myprofile");
			modelAndView.addObject("username", username);
		}catch (Exception exp) {
			logger.error("VendorController : vendorMyOfferings - Error reading details", exp);
		}
		logger.debug("Leaving VendorController : vendorMyOfferings");
		return modelAndView;
	}
	
	
	
	@RequestMapping(value=RequestConstans.Vendor.VENDOR_SPECIFIC_SOLUTION_LIST, method=RequestMethod.GET)
	public @ResponseBody Set<JsonResponseData> getVendorSpecificSolutionList(@RequestParam(value = "vendorProvider", required = false) String vendorProvider) {
		List<Solutions> solutions = vendorService.getSolutionsBasedOnOfferingTypes(vendorProvider);
		Set<JsonResponseData> JsonResponseData = new HashSet<JsonResponseData>();
		for(Solutions solution : solutions){
			JsonResponseData addResponseData = new JsonResponseData();
			addResponseData.setId(solution.getSolution_id().toString());
			addResponseData.setName(solution.getName());
			JsonResponseData.add(addResponseData);
		}
				
		return JsonResponseData;
		
	}

	
	@RequestMapping(value=RequestConstans.Vendor.MY_OFFERTINGS_FILE, method=RequestMethod.GET)
	public ModelAndView vendorMyOfferingsFiles(HttpServletRequest request,
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
		ModelAndView modelAndView = new ModelAndView(RequestConstans.Vendor.MY_OFFERTINGS_FILE);
		
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
			modelAndView.addObject("myprofiletab", "myprofile");
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
	public ModelAndView vendorRFPInbox(HttpServletRequest request,
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
	public ModelAndView vendorSearchDataBuyers(HttpServletRequest request,
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
	
	@RequestMapping(value =RequestConstans.Vendor.LOAD_VENDOR_FOCUS_ADD_FIELD_TO_FILE, method = RequestMethod.GET)
	public @ResponseBody Set<JsonResponseData>  addFieldsToFile(@RequestParam(value = "fieldName", required = false) String fieldName,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "fieldIndex", required = false) String fieldIndex,
			@RequestParam(value = "fieldMaxLength", required = false) String fieldMaxLength,
			@RequestParam(value = "fieldFormat", required = false) String fieldFormat,
			@RequestParam(value = "fieldDataType", required = false) String fieldDataType,
			@RequestParam(value = "selectedId", required = false) String selectedId
			) {
	
		// fieldName,fieldDescription,fieldIndex,fieldMaxLength,fieldFormat,fieldDataType
		try{
				FileFields fileFields = new FileFields();
				fileFields.setFieldName(fieldName);
				fileFields.setDescription(description);
				fileFields.setFieldIndex(fieldIndex);
				fileFields.setFieldFormat(fieldFormat);
				fileFields.setFieldDataType(fieldDataType);
				fileFields.setFieldMaxLength(fieldMaxLength);		
				
				marketDataAggregatorsService.addFieldsToFile(selectedId, fileFields);
		}catch(Exception e){
			e.printStackTrace();
		}
		
			Set<FileFields> listFieldsToFile = marketDataAggregatorsService.listFieldsToFile(selectedId);
			Set<JsonResponseData> JsonResponseData = new HashSet<JsonResponseData>();
			for(FileFields fileFields : listFieldsToFile){
				JsonResponseData addResponseData = new JsonResponseData();
				addResponseData.setId(fileFields.getId().toString());
				addResponseData.setName(fileFields.getFieldName());
				addResponseData.setDescription(fileFields.getDescription());
				addResponseData.setFieldDataType(fileFields.getFieldDataType());
				addResponseData.setFieldFormat(fileFields.getFieldFormat());
				addResponseData.setFieldIndex(fileFields.getFieldIndex());
				addResponseData.setFieldMaxLength(fileFields.getFieldMaxLength());
				addResponseData.setOfferingFiles( fileFields.getOfferingFiles().getFileName());
				JsonResponseData.add(addResponseData);
			}
			
			return JsonResponseData;
	}
	
	@RequestMapping(value =RequestConstans.Vendor.LOAD_VENDOR_FOCUS_ADD_OFFERING_FILES, method = RequestMethod.GET)
	public @ResponseBody Set<JsonResponseData>  addOfferingFiles(@RequestParam(value = "selectedId", required = false) String selectedId,
			@RequestParam(value = "fileName", required = false) String fileName,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "securityType", required = false) String securityType) {
		
		OfferingFiles offeringFiles = new OfferingFiles();
		offeringFiles.setFileName(fileName);
		offeringFiles.setDescription(description);
		SecurityType securityTypes = vendorService.getSecurityTypes(securityType);
		offeringFiles.setSecurityType(securityTypes);
		marketDataAggregatorsService.addOfferingFiles(selectedId, offeringFiles);
		
		
		Set<OfferingFiles> listOfferingFiles = marketDataAggregatorsService.listOfferingFiles(selectedId);
		Set<JsonResponseData> JsonResponseData = new HashSet<JsonResponseData>();
		for(OfferingFiles OfferingFile : listOfferingFiles){
			JsonResponseData addResponseData = new JsonResponseData();
			addResponseData.setId(OfferingFile.getOfferingFilesId().toString());
			addResponseData.setName(OfferingFile.getFileName());
			addResponseData.setDescription(OfferingFile.getDescription());
			addResponseData.setSecurityType(OfferingFile.getSecurityType().getName());
			JsonResponseData.add(addResponseData);
		}
				
		return JsonResponseData;
	}
	
	
	@RequestMapping(value =RequestConstans.Vendor.LIST_OFFERING_DATA, method = RequestMethod.POST )
	public @ResponseBody Set<JsonResponseData>  listOfferingData(@RequestParam(value = "objectVar", required = false) String objectVar) {
		Set<OfferingFiles> listOfferingFiles = marketDataAggregatorsService.listOfferingFiles(objectVar);
		Set<JsonResponseData> JsonResponseData = new HashSet<JsonResponseData>();
		for(OfferingFiles OfferingFile : listOfferingFiles){
			JsonResponseData addResponseData = new JsonResponseData();
			addResponseData.setId(OfferingFile.getOfferingFilesId().toString());
			addResponseData.setName(OfferingFile.getFileName());
			addResponseData.setDescription(OfferingFile.getDescription());
			addResponseData.setSecurityType(OfferingFile.getSecurityType().getName());
			JsonResponseData.add(addResponseData);
		}
		
		return JsonResponseData;
	}
	
	@RequestMapping(value =RequestConstans.Vendor.LIST_OFFERING_FIELD_DATA, method = RequestMethod.POST )
	public @ResponseBody Set<JsonResponseData>  listOfferingFieldData(@RequestParam(value = "objectVar", required = false) String objectVar) {
		Set<FileFields> listFieldsToFile = marketDataAggregatorsService.listFieldsToFile(objectVar);
		Set<JsonResponseData> JsonResponseData = new HashSet<JsonResponseData>();
		for(FileFields fileFields : listFieldsToFile){
			JsonResponseData addResponseData = new JsonResponseData();
			addResponseData.setId(fileFields.getId().toString());
			addResponseData.setName(fileFields.getFieldName());
			addResponseData.setDescription(fileFields.getDescription());
			addResponseData.setFieldDataType(fileFields.getFieldDataType());
			addResponseData.setFieldFormat(fileFields.getFieldFormat());
			addResponseData.setFieldIndex(fileFields.getFieldIndex());
			addResponseData.setFieldMaxLength(fileFields.getFieldMaxLength());
			addResponseData.setOfferingFiles( fileFields.getOfferingFiles().getFileName());
			JsonResponseData.add(addResponseData);
		}
		
		return JsonResponseData;
	}
	
	@RequestMapping(value =RequestConstans.Vendor.DELETE_RECORD_OFFERING, method = RequestMethod.POST )
	public @ResponseBody Set<JsonResponseData>  deleteRecordOffering(@RequestParam(value = "objectVar", required = false) String objectVar) {
		marketDataAggregatorsService.deleteOfferings(objectVar);
		Set<JsonResponseData> JsonResponseData = new HashSet<JsonResponseData>();
		/*Set<OfferingFiles> listOfferingFiles = marketDataAggregatorsService.listOfferingFiles(objectVar);
		for(OfferingFiles OfferingFile : listOfferingFiles){
			JsonResponseData addResponseData = new JsonResponseData();
			addResponseData.setId(OfferingFile.getId().toString());
			addResponseData.setName(OfferingFile.getFileName());
			addResponseData.setDescription(OfferingFile.getDescription());
			addResponseData.setSecurityType(OfferingFile.getSecurityType().getName());
			JsonResponseData.add(addResponseData);
		}
		*/
		return JsonResponseData;
		
	}
	
	
	
	@RequestMapping(value =RequestConstans.Vendor.DELETE_OFFERING_FILE, method = RequestMethod.POST)
	public @ResponseBody Set<JsonResponseData>  deleteOfferingFile(@RequestParam(value = "objectVar", required = false) String objectVar) {
		
		
		 marketDataAggregatorsService.deleteOfferingFiles(objectVar);
		Set<JsonResponseData> JsonResponseData = new HashSet<JsonResponseData>();
		
		/*Set<OfferingFiles> listOfferingFiles = marketDataAggregatorsService.listOfferingFiles(deleteOfferingFiles.getVendorOffering().getVendor_offering_id().toString());
		for(OfferingFiles OfferingFile : listOfferingFiles){
			JsonResponseData addResponseData = new JsonResponseData();
			addResponseData.setId(OfferingFile.getId().toString());
			addResponseData.setName(OfferingFile.getFileName());
			addResponseData.setDescription(OfferingFile.getDescription());
			addResponseData.setSecurityType(OfferingFile.getSecurityType().getName());
			JsonResponseData.add(addResponseData);
		}*/
		
		return JsonResponseData;
	}
	
	@RequestMapping(value =RequestConstans.Vendor.DELETE_FIELDS_FILE, method = RequestMethod.POST)
	public @ResponseBody Set<JsonResponseData>  deleteFieldsFile(@RequestParam(value = "objectVar", required = false) String objectVar) {
		marketDataAggregatorsService.deleteFieldsToFile(objectVar);
		Set<JsonResponseData> JsonResponseData = new HashSet<JsonResponseData>();
		
/*		Set<FileFields> listFieldsToFile = marketDataAggregatorsService.listFieldsToFile(objectVar);
		for(FileFields fileFields : listFieldsToFile){
			JsonResponseData addResponseData = new JsonResponseData();
			addResponseData.setId(fileFields.getId().toString());
			addResponseData.setName(fileFields.getFieldName());
			addResponseData.setDescription(fileFields.getDescription());
			addResponseData.setFieldDataType(fileFields.getFieldDataType());
			addResponseData.setFieldFormat(fileFields.getFieldFormat());
			addResponseData.setFieldIndex(fileFields.getFieldIndex());
			addResponseData.setFieldMaxLength(fileFields.getFieldMaxLength());
			addResponseData.setOfferingFiles( fileFields.getOfferingFiles().getFileName());
			JsonResponseData.add(addResponseData);
		}
*/		
		
		return JsonResponseData;
	}
	

	@RequestMapping(value =RequestConstans.Vendor.DELETE_VENDOR_SOLUTION, method = (RequestMethod.POST))
	public @ResponseBody Set<JsonResponseData>  deleteVendorSolution(@RequestParam(value = "objectVar", required = false) String objectVar) {
		vendorService.deleteVendorSolution(objectVar);
	
User appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<JsonResponseData> JsonResponseData = new HashSet<JsonResponseData>();
		try {
		Vendor vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();

		List<Solutions> listVednorSolution = vendorService.listVednorSolution(vendor.getId());
		
			for(Solutions solution : listVednorSolution){
			JsonResponseData addResponseData = new JsonResponseData();
			addResponseData.setId(solution.getSolution_id().toString());
			addResponseData.setName(solution.getName());
			addResponseData.setDescription(solution.getDescription());
			addResponseData.setSolutionType(solution.getSolutionTypes().getName());
			JsonResponseData.add(addResponseData);
		}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JsonResponseData;
	}
	

	@RequestMapping(value =RequestConstans.Vendor.ADD_VENDOR_SOLUTION, method = RequestMethod.POST)
	public @ResponseBody Set<JsonResponseData>  addVendorSolution(@RequestParam(value = "vendorSolutionTypes", required = false) String vendorSolutionTypes,
	@RequestParam(value = "fieldDescription", required = false) String fieldDescription,
	@RequestParam(value = "solutionName", required = false) String solutionName) {
		User appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<JsonResponseData> JsonResponseData = new HashSet<JsonResponseData>();
		try {
		Vendor vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();
	
		Solutions solutions = new Solutions();
		solutions.setName(solutionName);
		solutions.setDescription(fieldDescription);
		SolutionTypes solutionTypes = vendorService.getSolutionTypes(vendorSolutionTypes);
		solutions.setSolutionTypes(solutionTypes);
		solutions.setVendor(vendor);
		vendorService.addSolutionsInfo(solutions);
		
		List<Solutions> listVednorSolution = vendorService.listVednorSolution(vendor.getId());
		
			for(Solutions solution : listVednorSolution){
			JsonResponseData addResponseData = new JsonResponseData();
			addResponseData.setId(solution.getSolution_id().toString());
			addResponseData.setName(solution.getName());
			addResponseData.setDescription(solution.getDescription());
			addResponseData.setSolutionType(solution.getSolutionTypes().getName());
			JsonResponseData.add(addResponseData);
		}
		 } catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return JsonResponseData;
	}
	
	@RequestMapping(value =RequestConstans.Vendor.LIST_VENDOR_SOLUTION, method = RequestMethod.POST)
	public @ResponseBody Set<JsonResponseData>  listVendorSolution() {
		User appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<JsonResponseData> JsonResponseData = new HashSet<JsonResponseData>();
		try {
		Vendor vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();
		List<Solutions> listVednorSolution = vendorService.listVednorSolution(vendor.getId());
			for(Solutions solution : listVednorSolution){
			JsonResponseData addResponseData = new JsonResponseData();
			addResponseData.setId(solution.getSolution_id().toString());
			addResponseData.setName(solution.getName());
			addResponseData.setDescription(solution.getDescription());
			addResponseData.setSolutionType(solution.getSolutionTypes().getName());
			JsonResponseData.add(addResponseData);
		}
		 } catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return JsonResponseData;
	}
	
	
	@RequestMapping(value =RequestConstans.Vendor.LOAD_VENDOR_FOCUS_CREATE_OFFERINGS, method = RequestMethod.GET)
	public @ResponseBody Set<JsonResponseData>  updateOfferings(@RequestParam(value = "solution", required = false) String solution,
			@RequestParam(value = "offeringName", required = false) String offeringName,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "assetClass", required = false) String assetClass) {
		if(offeringName != null && !(offeringName.isEmpty())){
        try {
        	User appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	
			Vendor vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();
			if(solution != null && !("null".equals(solution)) && assetClass != null){
				Solutions solutionsInfo = vendorService.getSolutionsInfo(solution);
				AssetClass assetClassDetails = vendorService.getAssetClassDetails(assetClass);
				VendorOffering vendorOffering = new VendorOffering();
				vendorOffering.setSolutions(solutionsInfo);
				vendorOffering.setName(offeringName);
				vendorOffering.setDescription(description);
				vendorOffering.setAssetClass(assetClassDetails);
				vendorOffering.setVendor(vendor);
				marketDataAggregatorsService.createOfferings(vendor.getId(), vendorOffering);
			}
			
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
        List<VendorOffering> vendorOfferingDetails = vendorService.getVendorOfferingDetails(solution);
		Set<JsonResponseData> JsonResponseData = new HashSet<JsonResponseData>();
		for(VendorOffering endorOffering : vendorOfferingDetails){
			JsonResponseData addResponseData = new JsonResponseData();
			addResponseData.setId(endorOffering.getVendor_offering_id().toString());
			addResponseData.setName(endorOffering.getName());
			addResponseData.setDescription(endorOffering.getDescription());
			addResponseData.setSecurityType(endorOffering.getAssetClass().getDescription());
			JsonResponseData.add(addResponseData);
		}
				
		return JsonResponseData;
	}
	
	
	@RequestMapping(value =RequestConstans.Vendor.CREATE_TREE, method = RequestMethod.GET)
	public ModelAndView createTree(@RequestParam(value = "solution", required = false) String solution,
			@RequestParam(value = "offeringName", required = false) String offeringName,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "assetClass", required = false) String assetClass) {
			ModelAndView modelAndView = new ModelAndView("jqueryFileTree");
		
        try {
        	User appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	
			Vendor vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();
			  Set<VendorOffering> listOfferings = marketDataAggregatorsService.listOfferings(vendor.getId());
			modelAndView.addObject("listOfferings",listOfferings);
			
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	
	
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
	
	 @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	   public @ResponseBody String upload(MultipartHttpServletRequest request, HttpServletResponse response,@ModelAttribute("vendor") Vendor vendor) {                 
	  	 
	  	 Iterator<String> itr =  request.getFileNames();
		 
		 MultipartFile mpf = request.getFile(itr.next());
	  	 System.out.println(mpf.getOriginalFilename() +" uploaded!");

	  	 try {
			ufile.setLength(mpf.getBytes().length);
			ufile.setBytes(mpf.getBytes());
		  	ufile.setType(mpf.getContentType());
		  	ufile.setName(mpf.getOriginalFilename());
		  	ufile.setBlob( Hibernate.createBlob(mpf.getInputStream())); 
		  	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	 
	  	 //2. send it back to the client as <img> that calls get method
	  	 //we are using getTimeInMillis to avoid server cached image 
	  	 
	  	 return "<img src='/getfile/"+Calendar.getInstance().getTimeInMillis()+"' />";
	  	
	  }
	 
	  @RequestMapping(value = "/getfile/{value}", method = RequestMethod.GET)
	  public void get(HttpServletResponse response,@PathVariable String value){
	 		try {
	 	
	 			// FileDetails fileDetails = vendor.getFileDetails();
	 			
	 			User appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 			
	 			Vendor vendorInfoByUserName = vendorService.getVendorInfoByUserName(appUser.getUsername());
	 			
	 			if(vendorInfoByUserName != null){
	 				response.setContentType(vendorInfoByUserName.getLogoType());
	 				if(vendorInfoByUserName.getLogoLength() != null)
	 				response.setContentLength(vendorInfoByUserName.getLogoLength());
					if(vendorInfoByUserName.getLogoBytes() != null)
			 		FileCopyUtils.copy(vendorInfoByUserName.getLogoBytes().getBinaryStream() , response.getOutputStream());
	 			}else{
		 		 	response.setContentType(ufile.getType());
					response.setContentLength(ufile.getLength());
			 		FileCopyUtils.copy(ufile.getBytes(), response.getOutputStream());
	 			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  		
  
	  
	  @RequestMapping(value ="testFile", method = RequestMethod.GET)
		public ModelAndView testFile() {
			ModelAndView modelAndView = new ModelAndView("testFile");
			
           try{
        	User appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	
			Vendor vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();
			  Set<VendorOffering> listOfferings = marketDataAggregatorsService.listOfferings(vendor.getId());
			modelAndView.addObject("listOfferings",listOfferings);
			
           }catch(Exception e){
        	   e.printStackTrace();
        	   
           }
			
			System.out.println("test file.... ");
			return modelAndView;
		}

	  
	  

	
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
			@RequestParam(value = "support", required = false) String support,
			@RequestParam(value = "weekend", required = false) String weekend,
			@RequestParam(value = "publicHolidays", required = false) String publicHolidays,
			
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
				
		 		vendor.setLogoType(ufile.getType());
		 		vendor.setLogoLength(ufile.getLength());
		 		vendor.setLogoName(ufile.getName());
		 		vendor.setLogoBytes(ufile.getBlob());
				region = vendorService.getRegionsByName(venRegionOfIncorp);
				vendor.setRegionofincorp(region.getRegion_id());
				country = vendorService.getCountryById(venCountryOfIncorp);
				vendor.setCountryofincorp(country.getCountry_id().toString());
				
				VendorSupport vendorSupport = new VendorSupport();
				try{
				Class<? extends VendorSupport> c = vendorSupport.getClass();
			    Class[] cArg = new Class[1];
			    cArg[0] = Boolean.class;
			     Method method = c.getMethod("setC"+support, cArg);
			    method.invoke(vendorSupport, true);
				vendorSupport.setcWeekend(new Boolean(weekend));
				vendorSupport.setcWeekend(new Boolean(publicHolidays));
				vendor.setVendorSupport(vendorSupport);
				}catch(NoSuchMethodException e){
					logger.error("Mehtod not found -- ", e);
				}
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
	/*@RequestMapping(value =RequestConstans.Vendor.UPDATE_VENDOR_SUPPORT_COVEAGE_TAB, method = RequestMethod.GET)
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
*/
	/**
	 * method to update vendor award details
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value =RequestConstans.Vendor.UPDATE_VENDOR_AWARD_DETAILS_TAB, method = RequestMethod.GET)
	public @ResponseBody Set<JsonResponseData> updateVendorAwardDetails(@RequestParam(value = "awardname", required = false) String awardname,
			@RequestParam(value = "awardsponsor", required = false) String awardsponsor,
			@RequestParam(value = "awardedyear", required = false) String awardedyear) {
		
		Set<JsonResponseData> JsonResponseData = new HashSet<JsonResponseData>();
		User appUser = null;
		try {
			
			
			appUser = (User)SecurityContextHolder.getContext().
					getAuthentication().getPrincipal();
			Vendor vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();
			if(awardname != null){
				
				
				
				
				VendorAwardsMap vendorAwardsMap = new VendorAwardsMap();
				vendorAwardsMap.setAwardname(awardname);
				vendorAwardsMap.setAwardsponsor(awardsponsor);
				vendorAwardsMap.setAwardedyear(awardedyear);
				vendorAwardsMap.setVendor(vendor);
				// Update vendor award details
				vendorService.updateVendorAwardDetails(vendorAwardsMap);
		 	}
			List<VendorAwardsMap> vendorAwardsMaps = vendorService.listVendorAwardDetails(vendor.getId());
			
			for(VendorAwardsMap vendorAwardsMap : vendorAwardsMaps){
				JsonResponseData addResponseData = new JsonResponseData();
				addResponseData.setId(vendorAwardsMap.getVa_id().toString());
				addResponseData.setName(vendorAwardsMap.getAwardname());
				addResponseData.setDescription(vendorAwardsMap.getAwardsponsor());
				addResponseData.setFrequency(vendorAwardsMap.getAwardedyear());
				JsonResponseData.add(addResponseData);
			}
					
			
			
		} catch (Exception ex) {
			logger.error("Mehtod for update Award details tab -- ", ex);
		}
		return JsonResponseData;
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
			appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
							if(costNames.contains("$200"))
							  cost = vendorService.getCostInfo(RequestConstans.CostValues.$200);
							else if(costNames.contains("$300"))
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
	 * method to update vendor my offerings data coverage info
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	

	@SuppressWarnings("unused")
	@RequestMapping(value =RequestConstans.Vendor.ADD_VENDOR_TRADINGCAPABILITIESSUPPORTED, method = RequestMethod.GET)
	public @ResponseBody Set<VendorTradingCapabilitiesSupportedForm> addTradingCapabilitiesSupported(
			@ModelAttribute("vendorTradingCapabilitiesSupportedForm") VendorTradingCapabilitiesSupportedForm vendorTradingCapabilitiesSupportedForm
			) {
		ModelAndView modelAndView = new ModelAndView("empty");
		logger.info("Mehtod for update Vendor my offerings data coverage info tab--:");
		User appUser = null;
		
		Set<VendorTradingCapabilitiesSupportedForm> jsonResponseData = null;
		try {
	
			appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Vendor vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();
			
			if(vendorTradingCapabilitiesSupportedForm.getSolution() != null){
			Solutions solutionsInfo = vendorService.getSolutionsInfo(vendorTradingCapabilitiesSupportedForm.getSolution());
			
		/*	VendorOffering vendorOfferingToStoreInDB = new VendorOffering();
			for(VendorOffering vendorOffering: vendorOfferings){
				if(vendorOffering.getVendor_offering_id().toString().equals(offering)){
					vendorOfferingToStoreInDB = vendorOffering;
					break;
				}
			}*/
			
			VendorTradingCapabilitiesSupported insertIntoModel = vendorTradingCapabilitiesSupportedForm.insertIntoModel(vendorTradingCapabilitiesSupportedForm);
			
			insertIntoModel.setVendor(vendor);
			insertIntoModel.setSolution(solutionsInfo);
			vendorService.addTradingCapabilitiesSupported(insertIntoModel);
			}
			List<VendorTradingCapabilitiesSupported> listVendorTradingCapabilitiesSupported = vendorService.listTradingCapabilitiesSupported(vendor.getId());

			 jsonResponseData = new HashSet<VendorTradingCapabilitiesSupportedForm>();
			for(VendorTradingCapabilitiesSupported vendorTradingCapabilitiesSupported : listVendorTradingCapabilitiesSupported){

				VendorTradingCapabilitiesSupportedForm insertDataToForm = vendorTradingCapabilitiesSupportedForm.insertDataToForm(vendorTradingCapabilitiesSupported);
				jsonResponseData.add(insertDataToForm);
			
			}
			
			
			
		} catch (Exception ex) {
			logger.error("Mehtod for update Vendor  my offerings data coverage info-- ", ex);
			modelAndView.addObject("errorMsg", "Unable to update vendor s my offerings data coverage info, Please contact administrator");
		}
		return jsonResponseData;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value =RequestConstans.Vendor.TRADING_CAPABILITIES_SUPPORTED_OFFERING, method = RequestMethod.GET)
	public @ResponseBody Set<JsonResponseData> tradingCapabilitiesSupportedOffering(@RequestParam(value = "solutionId", required = false) String solutionId ) {
		List<VendorTradingSoftwareDetails> listTradingSoftwareDetails = vendorService.listTradingSoftwareDetailsBasedOnSolutionId(solutionId);
		 Set<JsonResponseData> jsonResponseDataSet = new HashSet<JsonResponseData>();
	     for(VendorTradingSoftwareDetails vendorTradingSoftwareDetail : listTradingSoftwareDetails){
	    	 JsonResponseData  jsonResponseData = new JsonResponseData();
	    	 jsonResponseData.setId(vendorTradingSoftwareDetail.getTradingSoftwareDetailsId().toString());
	    	 jsonResponseData.setName(vendorTradingSoftwareDetail.getOffering());
	    	 jsonResponseDataSet.add(jsonResponseData);
	     }
	     return jsonResponseDataSet;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value =RequestConstans.Vendor.RESEARCH_REPORTING_VENDOR_OFFERING, method = RequestMethod.GET)
	public @ResponseBody Set<JsonResponseData> researchReportingVendorOffering(@RequestParam(value = "solutionId", required = false) String solutionId ) {
		List<VendorAnalystProfile> listVendorAnalystProfile = vendorService.listResearchReportingVendorOfferingBasedOnSolutionId(solutionId);
		 Set<JsonResponseData> jsonResponseDataSet = new HashSet<JsonResponseData>();
	     for(VendorAnalystProfile vendorAnalystProfile : listVendorAnalystProfile){
	    	 JsonResponseData  jsonResponseData = new JsonResponseData();
	    	 jsonResponseData.setId(vendorAnalystProfile.getAnalystProfileId().toString());
	    	 jsonResponseData.setName(vendorAnalystProfile.getOffering());
	    	 jsonResponseDataSet.add(jsonResponseData);
	     }
	     return jsonResponseDataSet;
	}
	
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value =RequestConstans.Vendor.ADD_VENDOR_TRADINGSOFTWAREDETAILS, method = RequestMethod.GET)
	public @ResponseBody Set<VendorTradingSoftwareDetailsForm> addTradingSoftwareDetails(@ModelAttribute("vendorTradingSoftwareDetailsForm") VendorTradingSoftwareDetailsForm vendorTradingSoftwareDetailsForm ) {
		ModelAndView modelAndView = new ModelAndView("empty");
		logger.info("Mehtod for update Vendor my addTradingSoftwareDetails info tab--:");
		User appUser = null;
		Set<VendorTradingSoftwareDetailsForm> jsonResponseData = null;
		
		try {
			appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Vendor vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();
			if(vendorTradingSoftwareDetailsForm.getSolution() != null){
				VendorTradingSoftwareDetails insertDataToModel = vendorTradingSoftwareDetailsForm.insertDataToModel(vendorTradingSoftwareDetailsForm);
				insertDataToModel.setVendor(vendor);
				AssetClass assetClass = marketDataAggregatorsService.getAssetClassByName(vendorTradingSoftwareDetailsForm.getAssetClass());
				insertDataToModel.setAssetClass(assetClass);
			Solutions solutionsInfo = vendorService.getSolutionsInfo(vendorTradingSoftwareDetailsForm.getSolution());
			insertDataToModel.setSolution(solutionsInfo);
			vendorService.addTradingSoftwareDetails(insertDataToModel);
			}
			List<VendorTradingSoftwareDetails> listTradingSoftwareDetails = vendorService.listTradingSoftwareDetails(vendor.getId());

			 jsonResponseData = new HashSet<VendorTradingSoftwareDetailsForm>();
			for(VendorTradingSoftwareDetails vendorTradingSoftwareDetails : listTradingSoftwareDetails){
				VendorTradingSoftwareDetailsForm insertDataToForm = vendorTradingSoftwareDetailsForm.insertDataToForm(vendorTradingSoftwareDetails);
				jsonResponseData.add(insertDataToForm);
			}
		} catch (Exception ex) {
			logger.error("Mehtod for update Vendor  my offerings data coverage info-- ", ex);
			modelAndView.addObject("errorMsg", "Unable to update vendor s my offerings data coverage info, Please contact administrator");
		}
		return jsonResponseData;
	}
	@SuppressWarnings("unused")
	@RequestMapping(value =RequestConstans.Vendor.ADD_VENDOR_ANALYTICSFEATURESSUPPORTED, method = RequestMethod.GET)
	public @ResponseBody Set<JsonResponseData> addAnalyticsfeaturesSupported(
			@RequestParam(value = "solution", required = false) String solution,
			@RequestParam(value = "offering", required = false) String offering,
			@RequestParam(value = "fileDataCoverage", required = false) String fileDataCoverage,
			@RequestParam(value = "feedtype", required = false) String feedtype,
			@RequestParam(value = "feedsubtype", required = false) String feedsubtype,
			@RequestParam(value = "frequency", required = false) String frequency,
			@RequestParam(value = "distributionmethod", required = false) String distributionmethod,
			@RequestParam(value = "coverageregion", required = false) String coverageregion,
			@RequestParam(value = "coveragecountry", required = false) String coveragecountry,
			@RequestParam(value = "coverageexchange", required = false) String coverageexchange
			) {
		ModelAndView modelAndView = new ModelAndView("empty");
		logger.info("Mehtod for update Vendor my offerings data coverage info tab--:");
		User appUser = null;
		
		Set<JsonResponseData> jsonResponseData = null;
		try {
			// Gson gson = new Gson();
			// VendorMyofferingsDataCoverage[] vendorMyofferingsDataCoverages = null;
			String[] regions = null;
			String[] countries = null;
			String[] exchanges = null;
			appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Vendor vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();
			
			if(solution != null){
			Solutions solutionsInfo = vendorService.getSolutionsInfo(solution);
			
			Set<VendorOffering> vendorOfferings = solutionsInfo.getVendorOffering();
			VendorOffering vendorOfferingToStoreInDB = new VendorOffering();
			for(VendorOffering vendorOffering: vendorOfferings){
				if(vendorOffering.getVendor_offering_id().toString().equals(offering)){
					vendorOfferingToStoreInDB = vendorOffering;
					break;
				}
			}
			
			Set<OfferingFiles> offeringFiles = vendorOfferingToStoreInDB.getOfferingFiles();
			
			OfferingFiles offeringFilesToStorInDB = new OfferingFiles();
			
			for(OfferingFiles offeringFile: offeringFiles){
				if(offeringFile.getOfferingFilesId().toString().equals(fileDataCoverage)){
					offeringFilesToStorInDB = offeringFile;
					break;
				}
			}
			VendorDistribution vendorDistribution = new VendorDistribution();
			vendorDistribution.setVendor(vendor);
			vendorDistribution.setSolution(solutionsInfo);
			vendorDistribution.setVendorOffering(vendorOfferingToStoreInDB);
			vendorDistribution.setOfferingFiles(offeringFilesToStorInDB);
			vendorDistribution.setRegion(coverageregion);
			vendorDistribution.setCountry(coveragecountry);
			vendorDistribution.setExchange(coverageexchange);
			vendorDistribution.setFeedtype(feedtype);
			vendorDistribution.setFeedsubtype(feedsubtype);
			vendorDistribution.setFrequency(frequency);
			vendorDistribution.setDistributionmethod(distributionmethod);
			vendorService.addVendorDistribution(vendorDistribution);
			}
			List<VendorDistribution> listVendorDistribution = vendorService.listVendorDistribution(vendor.getId());

			 jsonResponseData = new HashSet<JsonResponseData>();
			for(VendorDistribution vendorDistribution1 : listVendorDistribution){
				JsonResponseData addResponseData = new JsonResponseData();
				addResponseData.setSolution(vendorDistribution1.getSolution().getName());
				addResponseData.setOffering(vendorDistribution1.getVendorOffering().getName());
				addResponseData.setOfferingFiles(vendorDistribution1.getOfferingFiles().getFileName());
				addResponseData.setRegion(vendorDistribution1.getRegion());
				addResponseData.setCountry(vendorDistribution1.getCountry());
				addResponseData.setExchange(vendorDistribution1.getExchange());
				addResponseData.setFeedType(vendorDistribution1.getFeedtype());
				addResponseData.setFeedSubType(vendorDistribution1.getFeedsubtype());
				addResponseData.setFrequency(vendorDistribution1.getFrequency());
				addResponseData.setDistributionMethod(vendorDistribution1.getDistributionmethod());
				
				jsonResponseData.add(addResponseData);
			}
			
			
			
		} catch (Exception ex) {
			logger.error("Mehtod for update Vendor  my offerings data coverage info-- ", ex);
			modelAndView.addObject("errorMsg", "Unable to update vendor s my offerings data coverage info, Please contact administrator");
		}
		return jsonResponseData;
	}
	@SuppressWarnings("unused")
	@RequestMapping(value =RequestConstans.Vendor.ADD_VENDOR_ANALYTICSSOFTWAREDETAILS, method = RequestMethod.GET)
	public @ResponseBody Set<VendorAnalyticsSoftwareDetailsForm> addAnalyticsSoftwareDetails(@ModelAttribute("analyticsSoftwareDetailsForm") VendorAnalyticsSoftwareDetailsForm analyticsSoftwareDetailsForm
			) {

		ModelAndView modelAndView = new ModelAndView("empty");
		logger.info("Mehtod for update Vendor my addAnalyticsSoftwareDetails info tab--:");
		User appUser = null;
		Set<VendorAnalyticsSoftwareDetailsForm> jsonResponseData = null;
		
		try {
			appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Vendor vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();
			if(analyticsSoftwareDetailsForm.getSolution() != null){
				VendorAnalyticsSoftwareDetails insertDataToModel = analyticsSoftwareDetailsForm.insertDataToModel(analyticsSoftwareDetailsForm);
				insertDataToModel.setVendor(vendor);
			Solutions solutionsInfo = vendorService.getSolutionsInfo(analyticsSoftwareDetailsForm.getSolution());
			insertDataToModel.setSolution(solutionsInfo);
			vendorService.addAnalyticsSoftwareDetails(insertDataToModel);
			}
			List<VendorAnalyticsSoftwareDetails> listAnlyticsSoftwareDetails = vendorService.listAnalyticsSoftwareDetails(vendor.getId());

			 jsonResponseData = new HashSet<VendorAnalyticsSoftwareDetailsForm>();
			for(VendorAnalyticsSoftwareDetails vendorAnalyticsSoftwareDetails : listAnlyticsSoftwareDetails){
				VendorAnalyticsSoftwareDetailsForm insertDataToForm = analyticsSoftwareDetailsForm.insertDataToForm(vendorAnalyticsSoftwareDetails);
				jsonResponseData.add(insertDataToForm);
			}
		} catch (Exception ex) {
			logger.error("Mehtod for update Vendor  my offerings data coverage info-- ", ex);
			modelAndView.addObject("errorMsg", "Unable to update vendor s my offerings data coverage info, Please contact administrator");
		}
		return jsonResponseData;
	
	}
	@SuppressWarnings("unused")
	@RequestMapping(value =RequestConstans.Vendor.ADD_VENDOR_RESEARCHCOVERAGE, method = RequestMethod.GET)
	public @ResponseBody Set<VendorResearchCoverageForm> addResearchCoverage(@ModelAttribute("vendorResearchCoverageForm") VendorResearchCoverageForm vendorResearchCoverageForm ) {

		logger.info("Mehtod for update Vendor my addResearchCoverage info tab--:");
		User appUser = null;
		Set<VendorResearchCoverageForm> jsonResponseData = null;
		
		try {
			appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Vendor vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();
			if(vendorResearchCoverageForm.getSolution() != null){
				VendorResearchCoverage insertDataToModel = vendorResearchCoverageForm.insertDataToModel(vendorResearchCoverageForm);
				insertDataToModel.setVendor(vendor);
				
			Solutions solutionsInfo = vendorService.getSolutionsInfo(vendorResearchCoverageForm.getSolution());
			insertDataToModel.setSolution(solutionsInfo);
			vendorService.addResearchCoverage(insertDataToModel);
			}
			List<VendorResearchCoverage> listResearchCoverage = vendorService.listResearchCoverage(vendor.getId());

			 jsonResponseData = new HashSet<VendorResearchCoverageForm>();
			for(VendorResearchCoverage vendorResearchCoverage : listResearchCoverage){
				VendorResearchCoverageForm insertDataToForm = vendorResearchCoverageForm.insertDataToForm(vendorResearchCoverage);
				jsonResponseData.add(insertDataToForm);
			}
		} catch (Exception ex) {
			logger.error("Mehtod for update Vendor  my offerings data coverage info-- ", ex);
		}
		return jsonResponseData;
		
		
	
	}
	@SuppressWarnings("unused")
	@RequestMapping(value =RequestConstans.Vendor.ADD_VENDOR_DATADISTRIBUTION, method = RequestMethod.GET)
	public @ResponseBody Set<JsonResponseData> addVendorDataDistribution(
			@RequestParam(value = "solution", required = false) String solution,
			@RequestParam(value = "offering", required = false) String offering,
			@RequestParam(value = "fileDataCoverage", required = false) String fileDataCoverage,
			@RequestParam(value = "feedtype", required = false) String feedtype,
			@RequestParam(value = "feedsubtype", required = false) String feedsubtype,
			@RequestParam(value = "frequency", required = false) String frequency,
			@RequestParam(value = "distributionmethod", required = false) String distributionmethod,
			@RequestParam(value = "coverageregion", required = false) String coverageregion,
			@RequestParam(value = "coveragecountry", required = false) String coveragecountry,
			@RequestParam(value = "coverageexchange", required = false) String coverageexchange
			) {
		ModelAndView modelAndView = new ModelAndView("empty");
		logger.info("Mehtod for update Vendor my offerings data coverage info tab--:");
		User appUser = null;
		
		Set<JsonResponseData> jsonResponseData = null;
		try {
			// Gson gson = new Gson();
			// VendorMyofferingsDataCoverage[] vendorMyofferingsDataCoverages = null;
			String[] regions = null;
			String[] countries = null;
			String[] exchanges = null;
			appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Vendor vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();
			
			if(solution != null){
			Solutions solutionsInfo = vendorService.getSolutionsInfo(solution);
			
			Set<VendorOffering> vendorOfferings = solutionsInfo.getVendorOffering();
			VendorOffering vendorOfferingToStoreInDB = new VendorOffering();
			for(VendorOffering vendorOffering: vendorOfferings){
				if(vendorOffering.getVendor_offering_id().toString().equals(offering)){
					vendorOfferingToStoreInDB = vendorOffering;
					break;
				}
			}
			
			Set<OfferingFiles> offeringFiles = vendorOfferingToStoreInDB.getOfferingFiles();
			
			OfferingFiles offeringFilesToStorInDB = new OfferingFiles();
			
			for(OfferingFiles offeringFile: offeringFiles){
				if(offeringFile.getOfferingFilesId().toString().equals(fileDataCoverage)){
					offeringFilesToStorInDB = offeringFile;
					break;
				}
			}
			VendorDistribution vendorDistribution = new VendorDistribution();
			vendorDistribution.setVendor(vendor);
			vendorDistribution.setSolution(solutionsInfo);
			vendorDistribution.setVendorOffering(vendorOfferingToStoreInDB);
			vendorDistribution.setOfferingFiles(offeringFilesToStorInDB);
			vendorDistribution.setRegion(coverageregion);
			vendorDistribution.setCountry(coveragecountry);
			vendorDistribution.setExchange(coverageexchange);
			vendorDistribution.setFeedtype(feedtype);
			vendorDistribution.setFeedsubtype(feedsubtype);
			vendorDistribution.setFrequency(frequency);
			vendorDistribution.setDistributionmethod(distributionmethod);
			vendorService.addVendorDistribution(vendorDistribution);
			}
			List<VendorDistribution> listVendorDistribution = vendorService.listVendorDistribution(vendor.getId());

			 jsonResponseData = new HashSet<JsonResponseData>();
			for(VendorDistribution vendorDistribution1 : listVendorDistribution){
				JsonResponseData addResponseData = new JsonResponseData();
				addResponseData.setSolution(vendorDistribution1.getSolution().getName());
				addResponseData.setOffering(vendorDistribution1.getVendorOffering().getName());
				addResponseData.setOfferingFiles(vendorDistribution1.getOfferingFiles().getFileName());
				addResponseData.setRegion(vendorDistribution1.getRegion());
				addResponseData.setCountry(vendorDistribution1.getCountry());
				addResponseData.setExchange(vendorDistribution1.getExchange());
				addResponseData.setFeedType(vendorDistribution1.getFeedtype());
				addResponseData.setFeedSubType(vendorDistribution1.getFeedsubtype());
				addResponseData.setFrequency(vendorDistribution1.getFrequency());
				addResponseData.setDistributionMethod(vendorDistribution1.getDistributionmethod());
				
				jsonResponseData.add(addResponseData);
			}
			
			
			
		} catch (Exception ex) {
			logger.error("Mehtod for update Vendor  my offerings data coverage info-- ", ex);
			modelAndView.addObject("errorMsg", "Unable to update vendor s my offerings data coverage info, Please contact administrator");
		}
		return jsonResponseData;
	}
	@SuppressWarnings("unused")
	@RequestMapping(value =RequestConstans.Vendor.ADD_VENDOR_RESEARCHDETAILS, method = RequestMethod.GET)
	public @ResponseBody Set<VendorResearchDetailsForm> addResearchDetails(@ModelAttribute("vendorResearchDetailsForm") VendorResearchDetailsForm vendorResearchDetailsForm ) {
	

		ModelAndView modelAndView = new ModelAndView("empty");
		logger.info("Mehtod for update Vendor my addTradingSoftwareDetails info tab--:");
		User appUser = null;
		Set<VendorResearchDetailsForm> jsonResponseData = null;
		
		try {
			appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Vendor vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();
			if(vendorResearchDetailsForm.getSolution() != null){
				VendorResearchDetails insertDataToModel = vendorResearchDetailsForm.insertDataToModel(vendorResearchDetailsForm);
				insertDataToModel.setVendor(vendor);
				
			Solutions solutionsInfo = vendorService.getSolutionsInfo(vendorResearchDetailsForm.getSolution());
			insertDataToModel.setSolution(solutionsInfo);
			vendorService.addResearchDetails(insertDataToModel);
			}
			List<VendorResearchDetails> listResearchDetails = vendorService.listResearchDetails(vendor.getId());

			 jsonResponseData = new HashSet<VendorResearchDetailsForm>();
			for(VendorResearchDetails vendorResearchDetails : listResearchDetails){
				VendorResearchDetailsForm insertDataToForm = vendorResearchDetailsForm.insertDataToForm(vendorResearchDetails);
				jsonResponseData.add(insertDataToForm);
			}
		} catch (Exception ex) {
			logger.error("Mehtod for update Vendor  my offerings data coverage info-- ", ex);
			modelAndView.addObject("errorMsg", "Unable to update vendor s my offerings data coverage info, Please contact administrator");
		}
		return jsonResponseData;
		
		
	}
	@SuppressWarnings("unused")
	@RequestMapping(value =RequestConstans.Vendor.ADD_VENDOR_ANALYSTPROFILE, method = RequestMethod.GET)
	public @ResponseBody Set<VendorAnalystProfileForm> addAnalystProfile(@ModelAttribute("vendorAnalystProfileForm") VendorAnalystProfileForm vendorAnalystProfileForm
			) {

		ModelAndView modelAndView = new ModelAndView("empty");
		logger.info("Mehtod for update Vendor my addTradingSoftwareDetails info tab--:");
		User appUser = null;
		Set<VendorAnalystProfileForm> jsonResponseData = null;
		
		try {
			appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Vendor vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();
			if(vendorAnalystProfileForm.getSolution() != null){
				VendorAnalystProfile insertDataToModel = vendorAnalystProfileForm.insertDataToModel(vendorAnalystProfileForm);
				insertDataToModel.setVendor(vendor);
				
			Solutions solutionsInfo = vendorService.getSolutionsInfo(vendorAnalystProfileForm.getSolution());
			insertDataToModel.setSolution(solutionsInfo);
			vendorService.addAnalystProfile(insertDataToModel);
			}
			List<VendorAnalystProfile> listAnalystProfile = vendorService.listAnalystProfile(vendor.getId());

			 jsonResponseData = new HashSet<VendorAnalystProfileForm>();
			for(VendorAnalystProfile vendorAnalystProfile : listAnalystProfile){
				VendorAnalystProfileForm insertDataToForm = vendorAnalystProfileForm.insertDataToForm(vendorAnalystProfile);
				jsonResponseData.add(insertDataToForm);
			}
		} catch (Exception ex) {
			logger.error("Mehtod for update Vendor  my offerings data coverage info-- ", ex);
			modelAndView.addObject("errorMsg", "Unable to update vendor s my offerings data coverage info, Please contact administrator");
		}
		return jsonResponseData;

		
	}


	@SuppressWarnings("unused")
	@RequestMapping(value =RequestConstans.Vendor.DELETE_VENDOR_DATADISTRIBUTION, method = RequestMethod.GET)
	public @ResponseBody Set<JsonResponseData> deleteVendorDataDistribution(
			@RequestParam(value = "slectedId", required = false) String slectedId
			) {
		logger.info("Mehtod to delete data distribution--:");
		User appUser = null;
		
		Set<JsonResponseData> jsonResponseData = null;
		try {
			
			appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Vendor vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();
			vendorService.detletVendorDistribution(slectedId);
			List<VendorDistribution> listVendorDistribution = vendorService.listVendorDistribution(vendor.getId());

			 jsonResponseData = new HashSet<JsonResponseData>();
			for(VendorDistribution vendorDistribution1 : listVendorDistribution){
				JsonResponseData addResponseData = new JsonResponseData();
				addResponseData.setSolution(vendorDistribution1.getSolution().getName());
				addResponseData.setOffering(vendorDistribution1.getVendorOffering().getName());
				addResponseData.setOfferingFiles(vendorDistribution1.getOfferingFiles().getFileName());
				addResponseData.setRegion(vendorDistribution1.getRegion());
				addResponseData.setCountry(vendorDistribution1.getCountry());
				addResponseData.setExchange(vendorDistribution1.getExchange());
				addResponseData.setFeedType(vendorDistribution1.getFeedtype());
				addResponseData.setFeedSubType(vendorDistribution1.getFeedsubtype());
				addResponseData.setFrequency(vendorDistribution1.getFrequency());
				addResponseData.setDistributionMethod(vendorDistribution1.getDistributionmethod());
				
				jsonResponseData.add(addResponseData);
			}
			
		} catch (Exception ex) {
			logger.error("Mehtod for update Vendor  my offerings data coverage info-- ", ex);
		}
		return jsonResponseData;
	}
	
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value =RequestConstans.Vendor.ADD_VENDOR_DATACOVERAGE, method = RequestMethod.GET)
	public @ResponseBody Set<JsonResponseData>  addVendorDataCoverage(
			@RequestParam(value = "solutionDataCoverage", required = false) String solutionDataCoverage,
			@RequestParam(value = "offeringsDataCoverage", required = false) String offeringsDataCoverage,
			@RequestParam(value = "supportcoverageregion", required = false) String supportcoverageregion,
			@RequestParam(value = "supportcoveragecountry", required = false) String supportcoveragecountry,
			@RequestParam(value = "vendorcostrange", required = false) String vendorcostrange,
			@RequestParam(value = "phonenumber", required = false) String phonenumber,
			@RequestParam(value = "email", required = false) String email
			) {
		logger.info("Mehtod for update Vendor my offerings data coverage info tab--:");
		User appUser = null;
		Set<JsonResponseData> jsonResponseData = null;
		try {
			String[] regions = null;
			String[] countries = null;
			String[] costs = null;
			appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Vendor vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();
			if(solutionDataCoverage != null){
			Solutions solutionsInfo = vendorService.getSolutionsInfo(solutionDataCoverage);
			VendorOffering vendorOfferingById = marketDataAggregatorsService.getVendorOfferingById(offeringsDataCoverage);
			
			regions = supportcoveragecountry.split(",");
			VendorDataCoverage vendorDataCoverage = new VendorDataCoverage();
			vendorDataCoverage.setVendor(vendor);
			vendorDataCoverage.setSolution(solutionsInfo);
			vendorDataCoverage.setVendorOffering(vendorOfferingById);
			vendorDataCoverage.setRegion(supportcoverageregion);
			vendorDataCoverage.setCountry(supportcoveragecountry);
			vendorDataCoverage.setCost(vendorcostrange);
			vendorDataCoverage.setPhoneNo(phonenumber);
			vendorDataCoverage.setEmail(email);
			vendorService.addVendorDataCoverage(vendorDataCoverage);
			}
			List<VendorDataCoverage> listVendorDataCoverage = vendorService.listVendorDataCoverage(vendor.getId());
			
			 jsonResponseData = new HashSet<JsonResponseData>();
				for(VendorDataCoverage dataCoverage : listVendorDataCoverage){
					JsonResponseData addResponseData = new JsonResponseData();
					addResponseData.setSolution(dataCoverage.getSolution().getName());
					addResponseData.setOffering(dataCoverage.getVendorOffering().getName());
					addResponseData.setRegion(dataCoverage.getRegion());
					addResponseData.setCountry(dataCoverage.getCountry());
					addResponseData.setCost(dataCoverage.getCost());
					addResponseData.setPhonNo(dataCoverage.getPhoneNo());
					addResponseData.setEmail(dataCoverage.getCost());
					jsonResponseData.add(addResponseData);
				}
		} catch (Exception ex) {
			logger.error("Mehtod for update Vendor  my offerings data coverage info-- ", ex);
		}
		return jsonResponseData;
	}

	
	@SuppressWarnings("unused")
	@RequestMapping(value =RequestConstans.Vendor.DELETE_VENDOR_DATACOVERAGE, method = RequestMethod.GET)
	public @ResponseBody Set<JsonResponseData>  deleteVendorDataCoverage(
			@RequestParam(value = "selectedId", required = false) String selectedId
			
			) {
		logger.info("Mehtod for update Vendor my offerings data coverage info tab--:");
		User appUser = null;
		Set<JsonResponseData> jsonResponseData = null;
		try {
			vendorService.deleteVendorDataCoverage(selectedId);
			appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Vendor vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();
			List<VendorDataCoverage> listVendorDataCoverage = vendorService.listVendorDataCoverage(vendor.getId());
			
			 jsonResponseData = new HashSet<JsonResponseData>();
				for(VendorDataCoverage dataCoverage : listVendorDataCoverage){
					JsonResponseData addResponseData = new JsonResponseData();
					addResponseData.setSolution(dataCoverage.getSolution().getName());
					addResponseData.setOffering(dataCoverage.getVendorOffering().getName());
					addResponseData.setRegion(dataCoverage.getRegion());
					addResponseData.setCountry(dataCoverage.getCountry());
					addResponseData.setCost(dataCoverage.getCost());
					addResponseData.setPhonNo(dataCoverage.getPhoneNo());
					addResponseData.setEmail(dataCoverage.getCost());
					jsonResponseData.add(addResponseData);
				}
		} catch (Exception ex) {
			logger.error("Mehtod for update Vendor  my offerings data coverage info-- ", ex);
		}
		return jsonResponseData;
	}

	

	@SuppressWarnings("unused")
	@RequestMapping(value =RequestConstans.Vendor.VENDOR_GET_REGION, method = RequestMethod.GET)
	public @ResponseBody String getRegion(@RequestParam(value = "country", required = false) String country){
	String region = vendorService.getRegion(country);
		return region;
	}
	
	/**
	 * method to update vendor my offerings data coverage info
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value =RequestConstans.Vendor.UPDATE_VENDOR_MYOFFEINGS_TRADING_CAPABILITIES_SUPPORTED_TAB , method = RequestMethod.GET)
	public ModelAndView updateVendorOfferingTradingCapabilitiesSupportedInfo(@ModelAttribute("vendor") Vendor vendor,
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
			appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
							if(costNames.contains("$200"))
							  cost = vendorService.getCostInfo(RequestConstans.CostValues.$200);
							else if(costNames.contains("$300"))
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
	 * method to update vendor my offerings data coverage info
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value =RequestConstans.Vendor.UPDATE_VENDOR_MYOFFEINGS_TRADING_SOFT_WAREDETAILS_TAB , method = RequestMethod.GET)
	public ModelAndView updateVendorOfferingTradingSoftwareDetailsInfo(@ModelAttribute("vendor") Vendor vendor,
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
			appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
							if(costNames.contains("$200"))
							  cost = vendorService.getCostInfo(RequestConstans.CostValues.$200);
							else if(costNames.contains("$300"))
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
	 * method to update vendor my offerings data coverage info
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value =RequestConstans.Vendor.UPDATE_VENDOR_MYOFFEINGS_ANALYTICS_FEATURES_SUPPORTED_TAB, method = RequestMethod.GET)
	public ModelAndView updateVendorOfferingAnalyticsFeaturesSupportedInfo(@ModelAttribute("vendor") Vendor vendor,
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
			appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
							if(costNames.contains("$200"))
							  cost = vendorService.getCostInfo(RequestConstans.CostValues.$200);
							else if(costNames.contains("$300"))
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
	 * method to update vendor my offerings data coverage info
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value =RequestConstans.Vendor.UPDATE_VENDOR_MYOFFEINGS_AnalyticsSoftwareDetails_TAB , method = RequestMethod.GET)
	public ModelAndView updateVendorOfferingAnalyticsSoftwareDetailsInfo(@ModelAttribute("vendor") Vendor vendor,
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
			appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
							if(costNames.contains("$200"))
							  cost = vendorService.getCostInfo(RequestConstans.CostValues.$200);
							else if(costNames.contains("$300"))
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
	 * method to update vendor my offerings data coverage info
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value =RequestConstans.Vendor.UPDATE_VENDOR_MYOFFEINGS_RESEARCH_COVERAGE_TAB , method = RequestMethod.GET)
	public ModelAndView updateVendorOfferingResearchCoverageInfo(@ModelAttribute("vendor") Vendor vendor,
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
			appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
							if(costNames.contains("$200"))
							  cost = vendorService.getCostInfo(RequestConstans.CostValues.$200);
							else if(costNames.contains("$300"))
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
	 * method to update vendor my offerings data coverage info
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value =RequestConstans.Vendor.UPDATE_VENDOR_MYOFFEINGS_RESEARCH_DETAILS_TAB , method = RequestMethod.GET)
	public ModelAndView updateVendorOfferingResearchDetailsInfo(@ModelAttribute("vendor") Vendor vendor,
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
			appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
							if(costNames.contains("$200"))
							  cost = vendorService.getCostInfo(RequestConstans.CostValues.$200);
							else if(costNames.contains("$300"))
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
	 * method to update vendor my offerings data coverage info
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value =RequestConstans.Vendor.UPDATE_VENDOR_MYOFFEINGS_ANALYST_PROFILE_TAB, method = RequestMethod.GET)
	public ModelAndView updateVendorOfferingAnalystProfileInfo(@ModelAttribute("vendor") Vendor vendor,
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
			appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
							if(costNames.contains("$200"))
							  cost = vendorService.getCostInfo(RequestConstans.CostValues.$200);
							else if(costNames.contains("$300"))
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

	
	@RequestMapping(value="checkExistingValue", method=RequestMethod.POST)
	public String checkExistingValue(HttpServletRequest request, HttpServletResponse response) {
		String value = request.getParameter("param");
		String actionComponent = request.getParameter("actionComponent");
		
		logger.info("Validate existing value : " + value);		
		Boolean isExist = false;
		try{
			if(RequestConstans.Vendor.AWARDDETAILS.equals(actionComponent)){
				 isExist = vendorService.isAwardAlreadyExist(value);
			}else if(RequestConstans.Vendor.VENDOR_SOLUTION.equals(actionComponent)){
				 isExist = vendorService.isSolutionAlreadyExist(value);
			}
		if(isExist){
			response.getWriter().print("Name already exists");	
		}
			
		}catch (IOException exp) {
			logger.error("Error checking Email id : " + exp);
			handleExceptionMessage(response, "Error validating Email id");
		}
		return null;
	}
	private void handleExceptionMessage(HttpServletResponse response, String message) {
		try{
			response.getWriter().print(message);
		}catch (IOException exp) {
			logger.error(message + " : " + exp);			
		}
	}	
}


class JsonResponseData {
	private String id;
	private String Name; 
	private String description;
	private String solution;
	private String offering;
	private String country;
	private String region;
	private String cost;
	private String exchange;
	private String phonNo;
	private String email;
	private String feedType;
	private String feedSubType;
	private String distributionMethod;
	private String frequency;

	private String securityType;
	private String solutionType;
	private String fieldIndex;
	private String fieldFormat;
	private String fieldDataType;
	private String fieldMaxLength;
	private String offeringFiles;

	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSecurityType() {
		return securityType;
	}
	public void setSecurityType(String securityType) {
		this.securityType = securityType;
	}
	public String getFieldIndex() {
		return fieldIndex;
	}
	public void setFieldIndex(String fieldIndex) {
		this.fieldIndex = fieldIndex;
	}
	public String getFieldFormat() {
		return fieldFormat;
	}
	public void setFieldFormat(String fieldFormat) {
		this.fieldFormat = fieldFormat;
	}
	public String getFieldDataType() {
		return fieldDataType;
	}
	public void setFieldDataType(String fieldDataType) {
		this.fieldDataType = fieldDataType;
	}
	public String getFieldMaxLength() {
		return fieldMaxLength;
	}
	public void setFieldMaxLength(String fieldMaxLength) {
		this.fieldMaxLength = fieldMaxLength;
	}
	public String getOfferingFiles() {
		return offeringFiles;
	}
	public void setOfferingFiles(String offeringFiles) {
		this.offeringFiles = offeringFiles;
	}
	public String getSolutionType() {
		return solutionType;
	}
	public void setSolutionType(String solutionType) {
		this.solutionType = solutionType;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getOffering() {
		return offering;
	}
	public void setOffering(String offering) {
		this.offering = offering;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public String getPhonNo() {
		return phonNo;
	}
	public void setPhonNo(String phonNo) {
		this.phonNo = phonNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFeedType() {
		return feedType;
	}
	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}
	public String getFeedSubType() {
		return feedSubType;
	}
	public void setFeedSubType(String feedSubType) {
		this.feedSubType = feedSubType;
	}
	public String getDistributionMethod() {
		return distributionMethod;
	}
	public void setDistributionMethod(String distributionMethod) {
		this.distributionMethod = distributionMethod;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
   	
	
}