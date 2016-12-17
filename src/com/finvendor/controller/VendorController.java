package com.finvendor.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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
import com.finvendor.form.FileDetails;
import com.finvendor.form.JsonResponseData;
import com.finvendor.form.VendorAnalystProfileForm;
import com.finvendor.form.VendorAnalyticsSoftwareDetailsForm;
import com.finvendor.form.VendorResearchCoverageForm;
import com.finvendor.form.VendorResearchDetailsForm;
import com.finvendor.form.VendorTradingCapabilitiesSupportedForm;
import com.finvendor.form.VendorTradingSoftwareDetailsForm;
import com.finvendor.json.bean.VendorDataAggregatorsOfferingJson;
import com.finvendor.model.AssetClass;
import com.finvendor.model.AssetClassSecurityMap;
import com.finvendor.model.Awards;
import com.finvendor.model.Consumer;
import com.finvendor.model.Cost;
import com.finvendor.model.Country;
import com.finvendor.model.Exchange;
import com.finvendor.model.FileFields;
import com.finvendor.model.OfferingFiles;
import com.finvendor.model.Region;
import com.finvendor.model.RfpBean;
import com.finvendor.model.SecurityType;
import com.finvendor.model.SolutionTypes;
import com.finvendor.model.Solutions;
import com.finvendor.model.Support;
import com.finvendor.model.Vendor;
import com.finvendor.model.VendorAnalystProfile;
import com.finvendor.model.VendorAnalyticsSoftwareDetails;
import com.finvendor.model.VendorAwardsMap;
import com.finvendor.model.VendorDataAggregatorsOffering;
import com.finvendor.model.VendorDataAggregatorsOfferingCoverage;
import com.finvendor.model.VendorDataAggregatorsOfferingDistribution;
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
import com.finvendor.service.ReferenceDataService;
import com.finvendor.service.RfpService;
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
	
	@Resource(name="referenceDataService")
	private ReferenceDataService referenceDataService;
	
	@Autowired
	private MarketDataAggregatorsService marketDataAggregatorsService;
	
	@Resource(name="rfpService")
	private RfpService rfpService;
	
	@RequestMapping(value="vendorMyStats", method=RequestMethod.GET)
	public ModelAndView vendorMyStats(HttpServletRequest request) {		
		logger.debug("Entering VendorController : vendorMyStats");
		ModelAndView modelAndView = new ModelAndView("vendorMyStats");
		List<AssetClass> assetClasses = null;
		List<Region> regions = null;
		List<Country> countries = null;
		List<Exchange> exchanges = null;
		List<Support> supports = null;
		List<Cost> costs = null;
		List<Awards> awards = null;
		Vendor vendor = null;
		logger.debug("Entering VendorController : vendorMyStats");
		try{
			assetClasses = marketDataAggregatorsService.getAllAssetClass();
			regions = marketDataAggregatorsService.getAllRegionClass();
			countries = marketDataAggregatorsService.getAllCountries();
			exchanges = marketDataAggregatorsService.getAllExchanges();
			supports =  marketDataAggregatorsService.getAllVendorSupports();
			costs  = marketDataAggregatorsService.getAllCostInfo();
			awards = marketDataAggregatorsService.getAllAwards();
			User appUser = (User)SecurityContextHolder.getContext().
					getAuthentication().getPrincipal();
			String username = appUser.getUsername();
			vendor = userService.getUserDetailsByUsername(username).getVendor();
			List<SecurityType> listSecurityType = marketDataAggregatorsService.listSecurityType();
			modelAndView.addObject("securityTypes",listSecurityType);
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
			modelAndView.addObject("breadcrum", "My Stats");
		}catch (Exception exp) {
			exp.printStackTrace();
			logger.error("VendorController : vendorMyStats - Error reading details", exp);
		}
		return modelAndView;
	}
	
	@RequestMapping(value="vendorMyBlogs", method=RequestMethod.GET)
	public ModelAndView vendorMyBlogs(HttpServletRequest request) {		
		logger.debug("Entering VendorController : vendorMyStats");
		ModelAndView modelAndView = new ModelAndView("vendorMyBlog");
		List<AssetClass> assetClasses = null;
		List<Region> regions = null;
		List<Country> countries = null;
		List<Exchange> exchanges = null;
		List<Support> supports = null;
		List<Cost> costs = null;
		List<Awards> awards = null;
		Vendor vendor = null;
		logger.debug("Entering VendorController : vendorMyBlogs");
		try{
			assetClasses = marketDataAggregatorsService.getAllAssetClass();
			regions = marketDataAggregatorsService.getAllRegionClass();
			countries = marketDataAggregatorsService.getAllCountries();
			exchanges = marketDataAggregatorsService.getAllExchanges();
			supports =  marketDataAggregatorsService.getAllVendorSupports();
			costs  = marketDataAggregatorsService.getAllCostInfo();
			awards = marketDataAggregatorsService.getAllAwards();
			User appUser = (User)SecurityContextHolder.getContext().
					getAuthentication().getPrincipal();
			String username = appUser.getUsername();
			vendor = userService.getUserDetailsByUsername(username).getVendor();
			List<SecurityType> listSecurityType = marketDataAggregatorsService.listSecurityType();
			modelAndView.addObject("securityTypes",listSecurityType);
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
			modelAndView.addObject("breadcrum", "My Blogs");
		}catch (Exception exp) {
			exp.printStackTrace();
			logger.error("VendorController : vendorMyBlogs - Error reading details", exp);
		}
		return modelAndView;
	}
	
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
			List<Solutions> solutions = vendorService.getSolutionsBasedOnOfferingTypes(RequestConstans.Vendor.DATA_AGGREGATOR, vendor);
			modelAndView.addObject("securityTypes",listSecurityType);
			modelAndView.addObject("solutions",solutions);
			
		}catch (Exception exp) {
			exp.printStackTrace();
			logger.error("VendorController : vendorMyOfferings - Error reading details", exp);
		}
		
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
		modelAndView.addObject("breadcrum", RequestConstans.Vendor.VENDOR_MY_OFFERINGS);
		modelAndView.addObject("username", username);
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
			modelAndView.addObject("breadcrum", RequestConstans.Vendor.VENDOR_SOLUTION);
			modelAndView.addObject("username", username);
		}catch (Exception exp) {
			logger.error("VendorController : vendorMyOfferings - Error reading details", exp);
		}
		logger.debug("Leaving VendorController : vendorMyOfferings");
		return modelAndView;
	}
		
	
	@RequestMapping(value=RequestConstans.Vendor.VENDOR_SPECIFIC_SOLUTION_LIST, method=RequestMethod.GET)
	public @ResponseBody Set<JsonResponseData> getVendorSpecificSolutionList(@RequestParam(value = "vendorProvider", required = false) String vendorProvider) {
		
		User appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Vendor vendor;
		Set<JsonResponseData> JsonResponseData = new HashSet<JsonResponseData>();
		try {
			vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();
		List<Solutions> solutions = vendorService.getSolutionsBasedOnOfferingTypes(vendorProvider,vendor);
		for(Solutions solution : solutions){
			JsonResponseData addResponseData = new JsonResponseData();
			addResponseData.setId(solution.getSolution_id().toString());
			addResponseData.setName(solution.getName());
			JsonResponseData.add(addResponseData);
		}
		} catch (ApplicationException e) {
			logger.error("VendorController : - Error reading details", e);
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
	
	/* Vendor - RFP inbox */
	@RequestMapping(value=RequestConstans.Vendor.VENDOR_RFP_INBOX, method=RequestMethod.GET)
	public ModelAndView vendorRfpInbox(HttpServletRequest request){
		logger.debug("Entering : vendorRfpInbox");
		ModelAndView modelAndView=new ModelAndView(RequestConstans.Vendor.VENDOR_RFP_INBOX);
		try {
			if(request.getSession().getAttribute("loggedInUser") == null){
				return new ModelAndView(RequestConstans.Login.HOME);
			}
			User loggedInUser = (User)request.getSession().getAttribute("loggedInUser");	
			String userName = loggedInUser.getUsername();
			Vendor vendor = userService.getUserDetailsByUsername(userName).getVendor();
			List<Object[]> rfpDetails = rfpService.selectMyRfpVendor(vendor.getId());
			modelAndView.addObject("rfpDetails", rfpDetails);
		}catch (Exception exp) {
			logger.error("Error : expressRfpInterest", exp);
			modelAndView.addObject("statusMessage", "Error selecting RFP details");
		}
		logger.debug("Exiting : vendorRfpInbox");
		return modelAndView;
	}
	
	
	/* Vendor - Applicable RFP list */
	@RequestMapping(value="vendorMyRFP", method=RequestMethod.GET)
	public ModelAndView selectVendorApplicableRfp(HttpServletRequest request){
		logger.debug("Entering : selectVendorApplicableRfp");
		ModelAndView modelAndView = new ModelAndView("rfpinbox");
		try {
			if(request.getSession().getAttribute("loggedInUser") == null){
				return new ModelAndView(RequestConstans.Login.HOME);
			}
			List<Object[]> rfpDetails = rfpService.selectMyRfpListVendor();
			modelAndView.addObject("rfpDetails", rfpDetails);
		}catch (Exception exp) {
			logger.error("Error : expressRfpInterest", exp);
			modelAndView.addObject("statusMessage", "Error selecting RFP details");
		}
		logger.debug("Exiting : selectVendorApplicableRfp");
		return modelAndView;
	}
	
	/* Vendor - express/revoke RFP interest */
	@RequestMapping(value="expressRfpInterest", method=RequestMethod.POST)
	public ModelAndView expressRfpInterest(HttpServletRequest request,
			@RequestParam(value = "rfpId", required = true) String rfpId,
			@RequestParam(value = "consumerName", required = true) String consumerName,
			@RequestParam(value = "revoke", required = true) boolean revoke){
		logger.debug("Entering : expressRfpInterest");
		ModelAndView modelAndView = new ModelAndView("vendorRfpInbox");
		try {
			if(request.getSession().getAttribute("loggedInUser") == null){
				return new ModelAndView(RequestConstans.Login.HOME);
			}
			User loggedInUser = (User)request.getSession().getAttribute("loggedInUser");	
			String userName = loggedInUser.getUsername();
			Consumer consumer = userService.getUserDetailsByUsername(consumerName).getConsumer();
			Vendor vendor = userService.getUserDetailsByUsername(userName).getVendor();
			Object[] rfpDetails = rfpService.selectRfpDetails(rfpId, null).get(0);
			RfpBean rfpBean = new RfpBean();
			rfpBean.setRfpId(rfpId);
			rfpBean.setRfpTitle(rfpDetails[2].toString());
			rfpService.expresssRfpInterest(rfpBean, vendor, consumer, revoke);
			modelAndView.addObject("statusMessage", "RFP interest successfully updated");
		}catch (Exception exp) {
			logger.error("Error : expressRfpInterest", exp);
			modelAndView.addObject("statusMessage", "Error Updating RFP interest");
		}
		logger.debug("Exiting : expressRfpInterest");
		return modelAndView;
	}
	
	/* Vendor - RFP Details */
	@RequestMapping(value="selectRfpDetailsForVendor", method=RequestMethod.GET)
	public ModelAndView selectRfpDetailsForVendor(HttpServletRequest request,
			@RequestParam(value = "rfpId", required = true) String rfpId){
		logger.debug("Entering : selectRfpDetailsForVendor");
		ModelAndView modelAndView = new ModelAndView("vendorRfpInbox");
		try {
			if(request.getSession().getAttribute("loggedInUser") == null){
				return new ModelAndView(RequestConstans.Login.HOME);
			}
			User loggedInUser = (User)request.getSession().getAttribute("loggedInUser");	
			String userName = loggedInUser.getUsername();
			Vendor vendor = userService.getUserDetailsByUsername(userName).getVendor();
			Object[] rfpDetails = rfpService.selectRfpDetails(rfpId, vendor.getId()).get(0);
			modelAndView.addObject("rfpDetails", rfpDetails);
		}catch (Exception exp) {
			logger.error("Error : selectRfpDetailsForVendor", exp);
			modelAndView.addObject("statusMessage", "Error selecting RFP details");
		}
		logger.debug("Exiting : selectRfpDetailsForVendor");
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
		
		Set<JsonResponseData> JsonResponseData = new HashSet<JsonResponseData>();
		try{
			OfferingFiles offeringFiles = new OfferingFiles();
			offeringFiles.setFileName(fileName);
			offeringFiles.setDescription(description);
			SecurityType securityTypes = referenceDataService.getSecurityTypeByName(securityType);
			offeringFiles.setSecurityType(securityTypes);
			marketDataAggregatorsService.addOfferingFiles(selectedId, offeringFiles);
			
			
			Set<OfferingFiles> listOfferingFiles = marketDataAggregatorsService.listOfferingFiles(selectedId);
			
			for(OfferingFiles OfferingFile : listOfferingFiles){
				JsonResponseData addResponseData = new JsonResponseData();
				addResponseData.setId(OfferingFile.getOfferingFilesId().toString());
				addResponseData.setName(OfferingFile.getFileName());
				addResponseData.setDescription(OfferingFile.getDescription());
				addResponseData.setSecurityType(OfferingFile.getSecurityType().getName());
				JsonResponseData.add(addResponseData);
			}
		}catch(Exception exp) {
			
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
	

	@RequestMapping(value =RequestConstans.Vendor.DELETE_RECORD, method = (RequestMethod.GET))
	public @ResponseBody Set<JsonResponseData>  deleteRecord(@RequestParam(value = "recordId", required = false) String objectVar,
			@RequestParam(value = "recordName", required = false) String recordName) {
		Set<JsonResponseData> JsonResponseData = new HashSet<JsonResponseData>();
		
		if(RequestConstans.Vendor.DELETE_RECORD_OFFERING.equals(recordName)){
			marketDataAggregatorsService.deleteOfferings(objectVar);
		}else if(RequestConstans.Vendor.AWARDDETAILS.equals(recordName)){
			vendorService.deleteAwardDetails(objectVar);
		}else if(RequestConstans.Vendor.DELETE_VENDOR_DATACOVERAGE.equals(recordName)){
			vendorService.deleteVendorDataCoverage(objectVar);
		}else if(RequestConstans.Vendor.DELETE_FIELDS_FILE.equals(recordName)){
			marketDataAggregatorsService.deleteFieldsToFile(objectVar);
		}else if(RequestConstans.Vendor.DELETE_OFFERING_FILE.equals(recordName)){
			 marketDataAggregatorsService.deleteOfferingFiles(objectVar);
		}else if(RequestConstans.Vendor.DELETE_VENDOR_DATADISTRIBUTION.equals(recordName)){
			vendorService.deleteVendorDistribution(objectVar);
		}else if(RequestConstans.Vendor.ADD_VENDOR_TRADINGSOFTWAREDETAILS.equals(recordName)){
			vendorService.deleteTradingSoftwareDetails(objectVar);
		}else if(RequestConstans.Vendor.ADD_VENDOR_TRADINGCAPABILITIESSUPPORTED.equals(recordName)){
			vendorService.deleteTradingCapabilitiesSupported(objectVar);
		}else if(RequestConstans.Vendor.ADD_VENDOR_ANALYTICSSOFTWAREDETAILS.equals(recordName)){
			vendorService.deleteAnalyticsSoftwareDetails(objectVar);
		}else if(RequestConstans.Vendor.ADD_VENDOR_RESEARCHDETAILS.equals(recordName)){
			vendorService.deleteResearchDetails(objectVar);
		}else if(RequestConstans.Vendor.ADD_VENDOR_RESEARCHCOVERAGE.equals(recordName)){
			vendorService.deleteResearchCoverage(objectVar);
		}else if(RequestConstans.Vendor.ADD_VENDOR_ANALYSTPROFILE.equals(recordName)){
			vendorService.deleteAnalystProfile(objectVar);
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
		
		logger.info("vendorSolutionTypes == " + vendorSolutionTypes);
		
		SolutionTypes solutionTypes = vendorService.getSolutionTypes(vendorSolutionTypes);
		solutions.setSolutionTypes(solutionTypes);
		solutions.setVendor(vendor);
		
		
		vendorService.addSolutionsInfo(solutions);
		
		logger.info("Succeesfully added solution");
		
		List<Solutions> listVednorSolution = vendorService.listVednorSolution(vendor.getId());
		
			for(Solutions solution : listVednorSolution){
			JsonResponseData addResponseData = new JsonResponseData();
			addResponseData.setId(solution.getSolution_id().toString());
			addResponseData.setName(solution.getName());
			addResponseData.setDescription(solution.getDescription());
			addResponseData.setSolutionType(solution.getSolutionTypes().getName());
			JsonResponseData.add(addResponseData);
		}
		 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		logger.info("Retturn solution response");
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
			@RequestParam(value = "launchedYear", required = false) String launchedYear,
			@RequestParam(value = "assetClass", required = false) String assetClassDescription) {
		if(offeringName != null && !(offeringName.isEmpty())){
        try {
        	User appUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	
			Vendor vendor = userService.getUserDetailsByUsername(appUser.getUsername()).getVendor();
			if(solution != null && !("null".equals(solution)) && assetClassDescription != null){
				Solutions solutionsInfo = vendorService.getSolutionsInfo(solution);
				AssetClass assetClassDetails = referenceDataService.getAssetClassByDescription(assetClassDescription);
				VendorOffering vendorOffering = new VendorOffering();
				vendorOffering.setSolutions(solutionsInfo);
				vendorOffering.setName(offeringName);
				vendorOffering.setDescription(description);
				vendorOffering.setAssetClass(assetClassDetails);
				vendorOffering.setLaunchedYear(Integer.parseInt(launchedYear));
				
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
			addResponseData.setLaunchedYear(endorOffering.getLaunchedYear()+"");
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
				
				region = (Region)referenceDataService.getModelObjectById(Region.class, 
						new Integer(venRegionOfIncorp));
				vendor.setRegionofincorp(new Integer(venRegionOfIncorp));
				country = (Country)referenceDataService.getModelObjectById(Country.class,
						new Integer(venCountryOfIncorp));
				vendor.setCountryofincorp(country.getCountry_id() + "");
				
				/*
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
				*/
				
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
	 * method to update vendor award details
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value =RequestConstans.Vendor.UPDATE_VENDOR_AWARD_DETAILS_TAB, method = RequestMethod.GET)
	public @ResponseBody Set<JsonResponseData> updateVendorAwardDetails(@RequestParam(value = "awardname", required = false) String awardname,
			@RequestParam(value = "awardsponsor", required = false) String awardsponsor,
			@RequestParam(value = "awardedyear", required = false) String awardedyear,
			@RequestParam(value = "awardResearchArea", required = false) String awardResearchArea,
			@RequestParam(value = "awardSolutionTypes", required = false) String awardSolutionTypes,
			@RequestParam(value = "awardVendorType", required = false) String awardVendorType,
			@RequestParam(value = "awardAnalyticsSolutionsType", required = false) String awardAnalyticsSolutionsType,
			@RequestParam(value = "awardAssetclass", required = false) String awardAssetclass) {
		
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
				vendorAwardsMap.setAwardedyear(Integer.parseInt(awardedyear));
				vendorAwardsMap.setVendor(vendor);
				vendorAwardsMap.setAwardResearchArea(awardResearchArea);
				vendorAwardsMap.setAwardSolutionTypes(awardSolutionTypes);
				vendorAwardsMap.setAwardVendorType(awardVendorType);
				vendorAwardsMap.setAwardAnalyticsSolutionsType(awardAnalyticsSolutionsType);
				vendorAwardsMap.setAwardAssetclass(awardAssetclass); 
				
				
				// Update vendor award details
				vendorService.updateVendorAwardDetails(vendorAwardsMap);
		 	}
			List<VendorAwardsMap> vendorAwardsMaps = vendorService.listVendorAwardDetails(vendor.getId());
			
			for(VendorAwardsMap vendorAwardsMap : vendorAwardsMaps){
				JsonResponseData addResponseData = new JsonResponseData();
				addResponseData.setId(vendorAwardsMap.getVa_id().toString());
				addResponseData.setName(vendorAwardsMap.getAwardname());
				addResponseData.setDescription(vendorAwardsMap.getAwardsponsor());
				addResponseData.setFrequency(vendorAwardsMap.getAwardedyear()+"");
				addResponseData.setAwardResearchArea(vendorAwardsMap.getAwardResearchArea());
				addResponseData.setAwardSolutionTypes(vendorAwardsMap.getAwardSolutionTypes());
				addResponseData.setAwardVendorType(vendorAwardsMap.getAwardVendorType());
				addResponseData.setAwardAnalyticsSolutionsType(vendorAwardsMap.getAwardAnalyticsSolutionsType());
				addResponseData.setAwardAssetclass(vendorAwardsMap.getAwardAssetclass()); 
				
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
			String isRecordExist = vendorService.addTradingCapabilitiesSupported(insertIntoModel);
			
			
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
		List<VendorResearchCoverage> listVendorAnalystProfile = vendorService.listResearchReportingVendorOfferingBasedOnSolutionId(solutionId);
		 Set<JsonResponseData> jsonResponseDataSet = new HashSet<JsonResponseData>();
	     for(VendorResearchCoverage vendorAnalystProfile : listVendorAnalystProfile){
	    	 JsonResponseData  jsonResponseData = new JsonResponseData();
	    	 jsonResponseData.setId(vendorAnalystProfile.getResearchCoverageId().toString());
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
				String assetClass2 = vendorTradingSoftwareDetailsForm.getAssetClass();
				if(vendorTradingSoftwareDetailsForm.getAssetClass() != null){
					String[] split = assetClass2.split("-");
					AssetClass assetClass = marketDataAggregatorsService.getAssetClassByName(split[0]);
					insertDataToModel.setAssetClass(assetClass);
					insertDataToModel.setSecurityName(split[1]);
				}
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
			String addVendorDistribution = vendorService.addVendorDistribution(vendorDistribution);
			if(addVendorDistribution != null){
				jsonResponseData = new HashSet<JsonResponseData>();
				JsonResponseData addResponseData = new JsonResponseData();
				addResponseData.setRecordExist(addVendorDistribution);
				jsonResponseData.add(addResponseData);
				return jsonResponseData;
			}

			}
			List<VendorDistribution> listVendorDistribution = vendorService.listVendorDistribution(vendor.getId());

			 jsonResponseData = new HashSet<JsonResponseData>();
			for(VendorDistribution vendorDistribution1 : listVendorDistribution){
				JsonResponseData addResponseData = new JsonResponseData();
				addResponseData.setId(vendorDistribution1.getVendorDistributionId().toString());
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
	@RequestMapping(value =RequestConstans.Vendor.ADD_VENDOR_DATACOVERAGE, method = RequestMethod.GET)
	public @ResponseBody Set<JsonResponseData>  addVendorDataCoverage(
			@RequestParam(value = "solutionDataCoverage", required = false) String solutionDataCoverage,
			@RequestParam(value = "offeringsDataCoverage", required = false) String offeringsDataCoverage,
			@RequestParam(value = "supportcoverageregion", required = false) String supportcoverageregion,
			@RequestParam(value = "supportcoveragecountry", required = false) String supportcoveragecountry,
			@RequestParam(value = "coverageexchange", required = false) String coverageexchange,
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
			vendorDataCoverage.setCoverageexchange(coverageexchange);
			vendorDataCoverage.setCost(vendorcostrange);
			vendorDataCoverage.setPhoneNo(phonenumber);
			vendorDataCoverage.setEmail(email);
			String addVendorDataCoverage = vendorService.addVendorDataCoverage(vendorDataCoverage);
			if(addVendorDataCoverage != null){
				jsonResponseData = new HashSet<JsonResponseData>();
				JsonResponseData addResponseData = new JsonResponseData();
				addResponseData.setRecordExist(addVendorDataCoverage);
				jsonResponseData.add(addResponseData);
				return jsonResponseData;
			}
			}
			List<VendorDataCoverage> listVendorDataCoverage = vendorService.listVendorDataCoverage(vendor.getId());
			
			 jsonResponseData = new HashSet<JsonResponseData>();
				for(VendorDataCoverage dataCoverage : listVendorDataCoverage){
					JsonResponseData addResponseData = new JsonResponseData();
					addResponseData.setId(dataCoverage.getDataCoverageId().toString());
					addResponseData.setSolution(dataCoverage.getSolution().getName());
					addResponseData.setOffering(dataCoverage.getVendorOffering().getName());
					addResponseData.setRegion(dataCoverage.getRegion());
					addResponseData.setCoverageexchange(dataCoverage.getCoverageexchange());
					addResponseData.setCountry(dataCoverage.getCountry());
					addResponseData.setCost(dataCoverage.getCost());
					addResponseData.setPhonNo(dataCoverage.getPhoneNo());
					addResponseData.setEmail(dataCoverage.getEmail());
					jsonResponseData.add(addResponseData);
				}
		} catch (Exception ex) {
			logger.error("Mehtod for update Vendor  my offerings data coverage info-- ", ex);
		}
		return jsonResponseData;
	}
	

	/*
	@SuppressWarnings("unused")
	@RequestMapping(value =RequestConstans.Vendor.VENDOR_GET_REGION, method = RequestMethod.GET)
	public @ResponseBody String getRegion(@RequestParam(value = "country", required = false) String country){
		try{
			String region = referenceDataService.getRegion(country);
			return region;
		}catch(Exception exp){
			logger.error("Error getRegion", exp);
			return null;
		}
	}
	*/
	
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
			}else if(RequestConstans.Vendor.ADD_VENDOR_TRADINGSOFTWAREDETAILS.equals(actionComponent)){
				 isExist = vendorService.isTradingSoftwareDetailsOfferingExist(value);
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
	
	
	
	/* Vendor Data Aggregator Offering Begin */
	
	@RequestMapping(value="addDataAggregatorOffering", method = RequestMethod.POST)
	public ModelAndView addDataAggregatorOffering(HttpServletRequest request,
			@RequestParam(value = "productId", required = false) String productId,
			@RequestParam(value = "productName", required = true) String productName,
			@RequestParam(value = "productDescription", required = true) String productDescription,
			@RequestParam(value = "assetClassId", required = true) int assetClassId,
			@RequestParam(value = "securityTypes", required = true) String securityTypes,
			@RequestParam(value = "launchedYear", required = false) String launchedYear,
			@RequestParam(value = "coverageRegion", required = true) String coverageRegion,
			@RequestParam(value = "coverageCountry", required = true) String coverageCountry,
			@RequestParam(value = "coverageExchange", required = true) String coverageExchange,
			@RequestParam(value = "phoneNumber", required = false) String phoneNumber,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "costRange", required = true) float costRange,
			@RequestParam(value = "feedType", required = false) String feedType,
			@RequestParam(value = "feedSubType", required = false) String feedSubType,
			@RequestParam(value = "frequency", required = false) String frequency,
			@RequestParam(value = "distributionMethod", required = false) String distributionMethod) {
		
		logger.debug("Entering  - VendorController : addDataAggregatorOffering");
		ModelAndView modelAndView = new ModelAndView("empty");
		
		try {
			if(request.getSession().getAttribute("loggedInUser") == null){
				return new ModelAndView(RequestConstans.Login.HOME);
			}
			User loggedInUser = (User)request.getSession().getAttribute("loggedInUser");	
			String userName = loggedInUser.getUsername();
			Vendor vendor = userService.getUserDetailsByUsername(userName).getVendor();
			
			VendorDataAggregatorsOffering dataOffering = new VendorDataAggregatorsOffering();
			VendorDataAggregatorsOfferingCoverage dataCoverage = new VendorDataAggregatorsOfferingCoverage();
			VendorDataAggregatorsOfferingDistribution dataDistribution = new VendorDataAggregatorsOfferingDistribution();
			
			if(productId == null || productId.trim().equals("")) {
				productId = UUID.randomUUID().toString(); 
			}
			dataOffering.setProductId(productId);
			dataOffering.setProductName(productName);
			dataOffering.setProductDescription(productDescription);
			dataOffering.setLaunchedYear(launchedYear);
			AssetClass assetClass = (AssetClass)marketDataAggregatorsService.getModelObjectById(
					AssetClass.class, assetClassId);
			dataOffering.setAssetClass(assetClass);
			dataOffering.setSecurityTypes(securityTypes);
			dataOffering.setVendor(vendor);
		
			dataCoverage.setProductId(productId);
			dataCoverage.setCoverageRegion(coverageRegion);
			dataCoverage.setCoverageCountry(coverageCountry);
			dataCoverage.setCoverageExchange(coverageExchange);
			dataCoverage.setCostRange(costRange);
			dataCoverage.setEmail(email);
			dataCoverage.setPhoneNumber(phoneNumber);
			dataOffering.setOfferingCoverge(dataCoverage);
			
			dataDistribution.setProductId(productId);
			dataDistribution.setFeedType(feedType);
			dataDistribution.setFeedSubType(feedSubType);
			dataDistribution.setFrequency(frequency);
			dataDistribution.setDistributionMethod(distributionMethod);
			dataOffering.setOfferingDistribution(dataDistribution);
					
			vendorService.addVendorDataAggregatorsOffering(dataOffering);
		} catch (Exception exp) {
			logger.error("Error Saving Market Data Aggregator Offering", exp); 
			modelAndView.addObject("status", "Error Updating Offering details");
		}
		modelAndView.addObject("status", "Offering details Updated successfully");
		logger.debug("Leaving  - VendorController : addDataAggregatorOffering");
		return modelAndView;
		
	}
	
	@RequestMapping(value="deleteDataAggregatorOffering", method = RequestMethod.POST)
	public ModelAndView deleteDataAggregatorOffering(
			HttpServletRequest request,
			@RequestParam(value = "productId", required = true) String productId) {
		
		logger.debug("Entering  - VendorController : deleteDataAggregatorOffering for product {}", productId);
		ModelAndView modelAndView = new ModelAndView("empty");
		List<VendorDataAggregatorsOffering> offerings = null;
		List<VendorDataAggregatorsOfferingJson> jsonOfferings = new ArrayList<VendorDataAggregatorsOfferingJson>();
		try {
			if(request.getSession().getAttribute("loggedInUser") == null){
				return new ModelAndView(RequestConstans.Login.HOME);
			}
			User loggedInUser = (User)request.getSession().getAttribute("loggedInUser");	
			String userName = loggedInUser.getUsername();
			boolean matchFound = false;
			offerings = vendorService.getVendorDataAggregatorsOffering(userName);
			for(VendorDataAggregatorsOffering dataAggreOffering : offerings) {
				if (dataAggreOffering.getProductId().equals(productId)) {
					matchFound = true;
					break;
				}
			}
			if(matchFound) {
				vendorService.deleteVendorDataAggregatorsOffering(productId);
				modelAndView.addObject("status", "Successfully deleted Offering record");
			} else {
				logger.error("Selected Offering does not belong to logged in User !!");
				modelAndView.addObject("status", "Error deleting Offering record");
			}
			
		} catch (Exception exp) {
			logger.error("Error Deleting Market Data Aggregator Offering for Product {}", 
					productId, exp); 
			modelAndView.addObject("status", "Error deleting Offering record");
		}
		logger.debug("Leaving  - VendorController : deleteDataAggregatorOffering for product {}", productId);
		return modelAndView;
	}
	
	@RequestMapping(value="listDataAggregatorOffering", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody List<VendorDataAggregatorsOfferingJson> listDataAggregatorOffering(HttpServletRequest request, 
			HttpServletResponse response) {
		
		logger.debug("Entering  - VendorController : listDataAggregatorOffering");
		List<VendorDataAggregatorsOffering> offerings = null;
		List<VendorDataAggregatorsOfferingJson> jsonOfferings = new ArrayList<VendorDataAggregatorsOfferingJson>();
		String userName = null;
		try {
			if(request.getSession().getAttribute("loggedInUser") == null){
				request.getRequestDispatcher("/").forward(request, response);
			}
			User loggedInUser = (User)request.getSession().getAttribute("loggedInUser");	
			userName = loggedInUser.getUsername();
			offerings = vendorService.
					getVendorDataAggregatorsOffering(userName);
			populateJsonVendorOfferingList(offerings, jsonOfferings);			
		} catch (Exception exp) {
			logger.error("Error Reading Market Data Aggregator Offering for {}", userName, exp); 
			
		}
		logger.debug("Leaving  - VendorController : listDataAggregatorOffering");
		return jsonOfferings;
	}
	
	private void populateJsonVendorOfferingList(List<VendorDataAggregatorsOffering> offerings,
			List<VendorDataAggregatorsOfferingJson> jsonOfferings) {
		for(VendorDataAggregatorsOffering offering : offerings) {
			VendorDataAggregatorsOfferingJson jsonOffering = new VendorDataAggregatorsOfferingJson();
			jsonOffering.setProductId(offering.getProductId());
			jsonOffering.setProductName(offering.getProductName());
			jsonOffering.setProductDescription(offering.getProductDescription());
			jsonOffering.setAssetClassCode(offering.getAssetClass().getAsset_class_cd());
			jsonOffering.setAssetClassDescription(offering.getAssetClass().getDescription());
			jsonOffering.setCoverageCountry(offering.getOfferingCoverge().getCoverageCountry());
			jsonOffering.setLaunchedYear(offering.getLaunchedYear());
			jsonOffering.setCoverageRegion(offering.getOfferingCoverge().getCoverageRegion());
			jsonOfferings.add(jsonOffering);
		}
	}
	
	@RequestMapping(value="fetchDataAggregatorOffering", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody VendorDataAggregatorsOfferingJson fetchDataAggregatorOffering(
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "productId", required = true) String productId) {
		
		logger.debug("Entering  - VendorController : fetchDataAggregatorOffering for product {}", productId);
		VendorDataAggregatorsOfferingJson vendorOffering = null;

		try {
			if(request.getSession().getAttribute("loggedInUser") == null){
				request.getRequestDispatcher("/").forward(request, response);
			}
			User loggedInUser = (User)request.getSession().getAttribute("loggedInUser");	
			String userName = loggedInUser.getUsername();
			VendorDataAggregatorsOffering offering = null;
			List<VendorDataAggregatorsOffering> offerings = vendorService.
					getVendorDataAggregatorsOffering(userName);
			for(VendorDataAggregatorsOffering dataAggreOffering : offerings) {
				if (dataAggreOffering.getProductId().equals(productId)) {
					offering = dataAggreOffering;
					break;
				}
			}
			if(offering == null) {
				logger.error("Selected Offering does not belong to logged in User !!");
			} else {
				vendorOffering = new VendorDataAggregatorsOfferingJson();
				vendorOffering.setProductId(offering.getProductId());
				vendorOffering.setProductName(offering.getProductName());
				vendorOffering.setProductDescription(offering.getProductDescription());
				vendorOffering.setAssetClassCode(offering.getAssetClass().getAsset_class_cd());
				vendorOffering.setAssetClassDescription(offering.getAssetClass().getDescription());
				vendorOffering.setSecurityTypes(offering.getSecurityTypes());
				vendorOffering.setLaunchedYear(offering.getLaunchedYear());
				vendorOffering.setCoverageRegion(offering.getOfferingCoverge().getCoverageRegion());
				vendorOffering.setCoverageCountry(offering.getOfferingCoverge().getCoverageCountry());
				vendorOffering.setCoverageExchange(offering.getOfferingCoverge().getCoverageExchange());
				vendorOffering.setEmail(offering.getOfferingCoverge().getEmail());
				vendorOffering.setPhoneNumber(offering.getOfferingCoverge().getPhoneNumber());
				vendorOffering.setCostRange(offering.getOfferingCoverge().getCostRange());
				vendorOffering.setDistributionMethod(offering.getOfferingDistribution().getDistributionMethod());
				vendorOffering.setFeedType(offering.getOfferingDistribution().getFeedType());
				vendorOffering.setFeedSubType(offering.getOfferingDistribution().getFeedSubType());
				vendorOffering.setFrequency(offering.getOfferingDistribution().getFrequency());
			}			
		} catch (Exception exp) {
			logger.error("Error Fetching Market Data Aggregator Offering for product {}", productId, exp); 
		}
		logger.debug("Leaving  - VendorController : fetchDataAggregatorOffering for product {}", productId);
		return vendorOffering;
	}
		
	/* Vendor Data Aggregator Offering End */
		
}
