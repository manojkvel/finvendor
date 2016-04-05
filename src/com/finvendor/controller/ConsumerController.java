package com.finvendor.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Hibernate;
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
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.finvendor.bean.ConsumerMyProfileBusinessNeedMarketData;
import com.finvendor.model.AssetClass;
import com.finvendor.model.AssetClassSecurityMap;
import com.finvendor.model.Awards;
import com.finvendor.model.CompanySubType;
import com.finvendor.model.Consumer;
import com.finvendor.model.Cost;
import com.finvendor.model.Country;
import com.finvendor.model.Exchange;
import com.finvendor.model.FinVendorUser;
import com.finvendor.model.Region;
import com.finvendor.model.SecurityType;
import com.finvendor.model.Support;
import com.finvendor.service.ConsumerService;
import com.finvendor.service.MarketDataAggregatorsService;
import com.finvendor.service.ReferenceDataService;
import com.finvendor.service.UserService;
import com.finvendor.util.CommonUtils;
import com.finvendor.util.RequestConstans;

@Controller
public class ConsumerController implements HandlerExceptionResolver {

	private static Logger logger = LoggerFactory.getLogger(
			ConsumerController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="consumerService")
	private ConsumerService consumerService;
	
	@Resource(name="referenceDataService")
	private ReferenceDataService referenceDataService;
	
	@Autowired
	private MarketDataAggregatorsService marketDataAggregatorsService;
	
	@RequestMapping(value="updateConsumerProfileCompanyDetails", 
			method=RequestMethod.POST)
	public ModelAndView updateConsumerProfileCompanyDetails(
			HttpServletRequest request, 
			@ModelAttribute("user") FinVendorUser user,
			@RequestParam(value = "consumerProfileLastName", required = false) String lastName,
			@RequestParam(value = "consumerProfileDesignation", required = false) String designation,
			@RequestParam(value = "consumerProfileCompanyUrl", required = false) String companyUrl,
			@RequestParam(value = "consumerProfileCompanyInfo", required = false) String companyInfo,
			@RequestParam(value = "consumerProfileSecondaryEmail", required = false) String secondaryEmail,
			@RequestParam(value = "consumerProfilePhoneNumberCode", required = false) String consumerProfilePhoneNumberCode,
			@RequestParam(value = "consumerProfilePhoneNumber", required = false) String consumerProfilePhoneNumber,
			@RequestParam(value = "consumerProfileCity", required = false) String city,
			@RequestParam(value = "consumerProfileCountryOfIncorporation", required = false) int countryOfIncorporation,
			@RequestParam(value = "consumerProfileYearOfIncorporation", required = false) int yearOfIncorporation,
			@RequestParam(value = "consumerProfileCompanySubType", required = false) int companySubType,
			@RequestParam(value = "consumerProfileCompanyLogo", required = false) MultipartFile companyLogo) {
		logger.debug("Entering ConsumerController : updateConsumerProfileCompanyDetails");
		List<Country> countries = null;
		List<CompanySubType> companySubTypeList = null;
		List<SecurityType> securityTypeList = null;
		List<AssetClass> assetClasses = null;
		List<Region> regions = null;
		List<Exchange> exchanges = null;
		String username = null;
		ModelAndView modelAndView = new ModelAndView(
				RequestConstans.Login.CONSUMER_INFO); 
		try {
			User appUser = (User)SecurityContextHolder.getContext().
					getAuthentication().getPrincipal();
			username = appUser.getUsername();
			user = userService.getUserDetailsByUsername(username);
			Consumer consumer = user.getConsumer();
			if(!companyLogo.isEmpty()) {
				consumer.setLogoBytes(Hibernate.createBlob(
						companyLogo.getInputStream()));
				consumer.setLogoType(companyLogo.getContentType());
			}
			String phoneNumber = consumerProfilePhoneNumberCode + "-" 
					+ consumerProfilePhoneNumber;
			consumer.setLastName(lastName);
			consumer.setDesignation(designation);
			consumer.setCompanyUrl(companyUrl);
			consumer.setCompanyInfo(companyInfo);
			consumer.setSecondaryEmail(secondaryEmail);
			consumer.setTelephone(phoneNumber);
			consumer.setCity(city);
			consumer.setCountryOfIncorporation(countryOfIncorporation);
			consumer.setYearOfIncorporation(yearOfIncorporation);
			consumer.setCompanySubType(consumerService.
					getCompanySubType(companySubType));	
			user.setConsumer(consumer);
			consumerService.updateConsumerDetails(consumer);
			CommonUtils.populateConsumerProfileRequest(consumer, consumerService, modelAndView);
			assetClasses = marketDataAggregatorsService.getAllAssetClass();
			regions = marketDataAggregatorsService.getAllRegionClass();
			exchanges = marketDataAggregatorsService.getAllExchanges();
			countries = marketDataAggregatorsService.getAllCountries();
			companySubTypeList = marketDataAggregatorsService.getCompanySubTypeList();
			securityTypeList = referenceDataService.getSecurityTypesForAssetClassId(1);
			modelAndView.addObject("assetClasses", assetClasses);
			modelAndView.addObject("regions", regions);
			modelAndView.addObject("exchanges", exchanges);
			modelAndView.addObject("consumer", consumer);
			modelAndView.addObject("countries", countries);
			modelAndView.addObject("companySubType", companySubTypeList);
	   		modelAndView.addObject("username", username);
	   		modelAndView.addObject("securityTypeList", securityTypeList);
	   		modelAndView.addObject("lastActionStatus", "Successfully updated Consumer Profile");
		}catch (Exception exp) {
			logger.error("Error updating Consumer Profile - Company Details", 
					exp);
			modelAndView.addObject("lastActionError",  "Error updating Consumer Profile");
		}
		logger.debug("Leaving ConsumerController : updateConsumerProfileCompanyDetails");
		return modelAndView;
	}
		
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exp) { 
		ModelAndView modelAndView = new ModelAndView("errorPage"); 
		if (exp instanceof MaxUploadSizeExceededException) {
			logger.error("File upload size exceeds 100KB threshold", exp);
			modelAndView.addObject("errorMessage", "File upload size exceeds 100KB threshold");
		} 
		try {
			request.getRequestDispatcher("/view/errorPage.jsp").forward(request, response);
		}catch (IOException exc) {
			logger.error("Error forwarding to errorpage");
		}catch (ServletException exc) {
			logger.error("Error forwarding to errorpage");
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value="updateConsumerProfileMyBusinessNeedsMarketData", 
			method=RequestMethod.POST)
	public String updateConsumerProfileMyBusinessNeedsMarketData(
			HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") FinVendorUser user,
			@RequestParam(value = "tableKey", required = true) String tableKey,
			@RequestParam(value = "jsonTableData", required = true) String jsonTableData) {
		logger.debug("Entering ConsumerController : updateConsumerProfileMyBusinessNeeds");
		User appUser = (User)SecurityContextHolder.getContext().
				getAuthentication().getPrincipal();
		String username = appUser.getUsername();
		Set<ConsumerMyProfileBusinessNeedMarketData> tableData = null;
		try{
			user = userService.getUserDetailsByUsername(username);
			Consumer consumer = user.getConsumer();
			tableData = consumerService.updateConsumerMyProfileBusinessNeedMarketData(consumer.getId(), tableKey, jsonTableData);
			ObjectMapper mapper = new ObjectMapper();
			String jsonTableString = mapper.writeValueAsString(tableData);
			response.getWriter().print(jsonTableString);
		}catch (Exception exp) {
			logger.error("Error updating Consumer Profile" + exp);
			try{
				response.getWriter().print("Error Updating Consumer profile");
			}catch (Exception exc) {
				logger.error("Error updating Consumer Profile status message" + exc);
			}
		}
		logger.debug("Leaving ConsumerController : updateConsumerProfileMyBusinessNeeds");
		return null;
	}

	/**
	 * method for navigate consumer my offerings
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	
	@RequestMapping(value=RequestConstans.Consumer.CONSUMER_MY_OFFERINGS, method=RequestMethod.GET)
	public ModelAndView consumerMyOfferings(HttpServletRequest request,@ModelAttribute("users") FinVendorUser users,
			@RequestParam(value = "RaYVeMu", required = false) String username){
		logger.info("Mehtod for vendorNavigation--:");
		List<AssetClass> assetClasses = null;
		List<Region> regions = null;
		List<Country> countries = null;
		List<Exchange> exchanges = null;
		List<Support> supports = null;
		List<Cost> costs = null;
		List<Awards> awards = null;
		ModelAndView modelAndView=new ModelAndView(RequestConstans.Consumer.CONSUMER_MY_OFFERINGS);
		try{
			assetClasses = marketDataAggregatorsService.getAllAssetClass();
			regions = marketDataAggregatorsService.getAllRegionClass();
			countries = marketDataAggregatorsService.getAllCountries();
			exchanges = marketDataAggregatorsService.getAllExchanges();
			supports =  marketDataAggregatorsService.getAllVendorSupports();
			costs  = marketDataAggregatorsService.getAllCostInfo();
			awards = marketDataAggregatorsService.getAllAwards();
			
			username = CommonUtils.decrypt(username.getBytes());
			
			modelAndView.addObject("assetClasses", assetClasses);
			modelAndView.addObject("regions", regions);
			modelAndView.addObject("regionslist", regions);
			modelAndView.addObject("countries", countries);
			modelAndView.addObject("exchanges", exchanges);
			modelAndView.addObject("supports", supports);
			modelAndView.addObject("costs", costs);
			modelAndView.addObject("awards", awards);
			
			modelAndView.addObject("consumerMyOfferingstab", "consumermyofferings");
			
			modelAndView.addObject("username", username);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Mehtod for vendorNavigation--:");
		}
		return modelAndView;
	}

	/**
	 * method for navigate consumer my profile
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	
	@RequestMapping(value=RequestConstans.Consumer.CONSUMER_INVITE_AN_RFP, method=RequestMethod.GET)
	public ModelAndView consumerInViteAnRFP(HttpServletRequest request,@ModelAttribute("users") FinVendorUser users,
			@RequestParam(value = "RaYVeMu", required = false) String username){
		logger.info("Mehtod for vendorNavigation--:");
		List<AssetClass> assetClasses = null;
		List<Region> regions = null;
		List<Country> countries = null;
		List<Exchange> exchanges = null;
		List<Support> supports = null;
		List<Cost> costs = null;
		List<Awards> awards = null;
		ModelAndView modelAndView=new ModelAndView(RequestConstans.Consumer.CONSUMER_INVITE_AN_RFP);
		try{
			assetClasses = marketDataAggregatorsService.getAllAssetClass();
			regions = marketDataAggregatorsService.getAllRegionClass();
			countries = marketDataAggregatorsService.getAllCountries();
			exchanges = marketDataAggregatorsService.getAllExchanges();
			supports =  marketDataAggregatorsService.getAllVendorSupports();
			costs  = marketDataAggregatorsService.getAllCostInfo();
			awards = marketDataAggregatorsService.getAllAwards();
			
			username = CommonUtils.decrypt(username.getBytes());
			
			modelAndView.addObject("assetClasses", assetClasses);
			modelAndView.addObject("regions", regions);
			modelAndView.addObject("regionslist", regions);
			modelAndView.addObject("countries", countries);
			modelAndView.addObject("exchanges", exchanges);
			modelAndView.addObject("supports", supports);
			modelAndView.addObject("costs", costs);
			modelAndView.addObject("awards", awards);
			
			modelAndView.addObject("consumerInviteAnRFP", "consumerinviteanrfp");
			modelAndView.addObject("consumermarketdataneedsInviteAnRFP", "consumermarketdataneedsinviteanrfp");
			modelAndView.addObject("username", username);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Mehtod for vendorNavigation--:");
		}
		return modelAndView;
	}

	
	/**
	 * method for navigate consumer my profile
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	
	@RequestMapping(value=RequestConstans.Consumer.CONSUMER_MARKET_DATANEEDS_INVITE_AN_RFP, method=RequestMethod.GET)
	public ModelAndView consumerMarketDataNeedsInViteAnRFP(HttpServletRequest request,@ModelAttribute("users") FinVendorUser users,
			@RequestParam(value = "RaYVeMu", required = false) String username){
		logger.info("Mehtod for vendorNavigation--:");
		List<AssetClass> assetClasses = null;
		List<Region> regions = null;
		List<Country> countries = null;
		List<Exchange> exchanges = null;
		List<Support> supports = null;
		List<Cost> costs = null;
		List<Awards> awards = null;
		ModelAndView modelAndView=new ModelAndView(RequestConstans.Consumer.CONSUMER_INVITE_AN_RFP);
		try{
			assetClasses = marketDataAggregatorsService.getAllAssetClass();
			regions = marketDataAggregatorsService.getAllRegionClass();
			countries = marketDataAggregatorsService.getAllCountries();
			exchanges = marketDataAggregatorsService.getAllExchanges();
			supports =  marketDataAggregatorsService.getAllVendorSupports();
			costs  = marketDataAggregatorsService.getAllCostInfo();
			awards = marketDataAggregatorsService.getAllAwards();
			
			username = CommonUtils.decrypt(username.getBytes());
			
			modelAndView.addObject("assetClasses", assetClasses);
			modelAndView.addObject("regions", regions);
			modelAndView.addObject("regionslist", regions);
			modelAndView.addObject("countries", countries);
			modelAndView.addObject("exchanges", exchanges);
			modelAndView.addObject("supports", supports);
			modelAndView.addObject("costs", costs);
			modelAndView.addObject("awards", awards);
			
			modelAndView.addObject("consumerInviteAnRFP", "consumerinviteanrfp");
			modelAndView.addObject("consumermarketdataneedsInviteAnRFP", "consumermarketdataneedsinviteanrfp");
			modelAndView.addObject("username", username);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Mehtod for vendorNavigation--:");
		}
		return modelAndView;
	}
	
	
	
	/**
	 * method for navigate consumer my profile
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	
	@RequestMapping(value=RequestConstans.Consumer.CONSUMER_TRADING_APPLICATION_NEEDS_INVITE_AN_RFP, method=RequestMethod.GET)
	public ModelAndView consumerTRADINGAPPLICATIONInViteAnRFP(HttpServletRequest request,@ModelAttribute("users") FinVendorUser users,
			@RequestParam(value = "RaYVeMu", required = false) String username){
		logger.info("Mehtod for vendorNavigation--:");
		List<AssetClass> assetClasses = null;
		List<Region> regions = null;
		List<Country> countries = null;
		List<Exchange> exchanges = null;
		List<Support> supports = null;
		List<Cost> costs = null;
		List<Awards> awards = null;
		ModelAndView modelAndView=new ModelAndView(RequestConstans.Consumer.CONSUMER_TRADING_APPLICATION_NEEDS_INVITE_AN_RFP);
		try{
			assetClasses = marketDataAggregatorsService.getAllAssetClass();
			regions = marketDataAggregatorsService.getAllRegionClass();
			countries = marketDataAggregatorsService.getAllCountries();
			exchanges = marketDataAggregatorsService.getAllExchanges();
			supports =  marketDataAggregatorsService.getAllVendorSupports();
			costs  = marketDataAggregatorsService.getAllCostInfo();
			awards = marketDataAggregatorsService.getAllAwards();
			
			username = CommonUtils.decrypt(username.getBytes());
			
			modelAndView.addObject("assetClasses", assetClasses);
			modelAndView.addObject("regions", regions);
			modelAndView.addObject("regionslist", regions);
			modelAndView.addObject("countries", countries);
			modelAndView.addObject("exchanges", exchanges);
			modelAndView.addObject("supports", supports);
			modelAndView.addObject("costs", costs);
			modelAndView.addObject("awards", awards);
			
			modelAndView.addObject("consumerInviteAnRFP", "consumerinviteanrfp");
			modelAndView.addObject("consumertradingapplicationInviteAnRFP", "consumertradingapplicationInviteAnRFP");
			modelAndView.addObject("username", username);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Mehtod for vendorNavigation--:");
		}
		return modelAndView;
	}
	
	
	
	/**
	 * method for navigate consumer my profile
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	
	@RequestMapping(value=RequestConstans.Consumer.CONSUMER_ANALYTICS_APPLICATION_NEEDS_INVITE_AN_RFP, method=RequestMethod.GET)
	public ModelAndView consumerAanylyticApplicationInViteAnRFP(HttpServletRequest request,@ModelAttribute("users") FinVendorUser users,
			@RequestParam(value = "RaYVeMu", required = false) String username){
		logger.info("Mehtod for vendorNavigation--:");
		List<AssetClass> assetClasses = null;
		List<Region> regions = null;
		List<Country> countries = null;
		List<Exchange> exchanges = null;
		List<Support> supports = null;
		List<Cost> costs = null;
		List<Awards> awards = null;
		ModelAndView modelAndView=new ModelAndView(RequestConstans.Consumer.CONSUMER_ANALYTICS_APPLICATION_NEEDS_INVITE_AN_RFP);
		try{
			assetClasses = marketDataAggregatorsService.getAllAssetClass();
			regions = marketDataAggregatorsService.getAllRegionClass();
			countries = marketDataAggregatorsService.getAllCountries();
			exchanges = marketDataAggregatorsService.getAllExchanges();
			supports =  marketDataAggregatorsService.getAllVendorSupports();
			costs  = marketDataAggregatorsService.getAllCostInfo();
			awards = marketDataAggregatorsService.getAllAwards();
			
			username = CommonUtils.decrypt(username.getBytes());
			
			modelAndView.addObject("assetClasses", assetClasses);
			modelAndView.addObject("regions", regions);
			modelAndView.addObject("regionslist", regions);
			modelAndView.addObject("countries", countries);
			modelAndView.addObject("exchanges", exchanges);
			modelAndView.addObject("supports", supports);
			modelAndView.addObject("costs", costs);
			modelAndView.addObject("awards", awards);
			
			modelAndView.addObject("consumerInviteAnRFP", "consumerinviteanrfp");
			modelAndView.addObject("consumeranalyticsapplicationInviteAnRFP", "consumeranalyticsapplicationInviteAnRFP");
			modelAndView.addObject("username", username);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Mehtod for vendorNavigation--:");
		}
		return modelAndView;
	}
	
	
	
	/**
	 * method for navigate consumer my profile
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	
	@RequestMapping(value=RequestConstans.Consumer.CONSUMER_RESEARCG_REPORT_NEEDS_INVITE_AN_RFP, method=RequestMethod.GET)
	public ModelAndView consumerReaserchReportInViteAnRFP(HttpServletRequest request,@ModelAttribute("users") FinVendorUser users,
			@RequestParam(value = "RaYVeMu", required = false) String username){
		logger.info("Mehtod for vendorNavigation--:");
		List<AssetClass> assetClasses = null;
		List<Region> regions = null;
		List<Country> countries = null;
		List<Exchange> exchanges = null;
		List<Support> supports = null;
		List<Cost> costs = null;
		List<Awards> awards = null;
		ModelAndView modelAndView=new ModelAndView(RequestConstans.Consumer.CONSUMER_RESEARCG_REPORT_NEEDS_INVITE_AN_RFP);
		try{
			assetClasses = marketDataAggregatorsService.getAllAssetClass();
			regions = marketDataAggregatorsService.getAllRegionClass();
			countries = marketDataAggregatorsService.getAllCountries();
			exchanges = marketDataAggregatorsService.getAllExchanges();
			supports =  marketDataAggregatorsService.getAllVendorSupports();
			costs  = marketDataAggregatorsService.getAllCostInfo();
			awards = marketDataAggregatorsService.getAllAwards();
			
			username = CommonUtils.decrypt(username.getBytes());
			
			modelAndView.addObject("assetClasses", assetClasses);
			modelAndView.addObject("regions", regions);
			modelAndView.addObject("regionslist", regions);
			modelAndView.addObject("countries", countries);
			modelAndView.addObject("exchanges", exchanges);
			modelAndView.addObject("supports", supports);
			modelAndView.addObject("costs", costs);
			modelAndView.addObject("awards", awards);
			
			modelAndView.addObject("consumerInviteAnRFP", "consumerinviteanrfp");
			modelAndView.addObject("consumerresearchreportInviteAnRFP", "consumerresearchreportInviteAnRFP");
			modelAndView.addObject("username", username);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Mehtod for vendorNavigation--:");
		}
		return modelAndView;
	}
	
	
	
	
	
	/**
	 * method to load consumer Security types
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value =RequestConstans.Consumer.LOAD_CONSUMER_SECURITY_DATADELIVERY_TYPES, method = RequestMethod.GET)
	public ModelAndView loadConsumerDataDeliverySecurityType(@RequestParam(value = "RAyuL", required = false) String assetType) {
		ModelAndView modelAndView = new ModelAndView("consumerpage/consumerdatadeliverysecuritylist");
		List<AssetClassSecurityMap> assetClassSecurityMaps = null;
		try {
			assetType = CommonUtils.decrypt(assetType.getBytes());
			if(!assetType.equals("") && !assetType.equals("-SELECT-")){
				AssetClass assetClass = marketDataAggregatorsService.getAssetClassByName(assetType);
				assetClassSecurityMaps = marketDataAggregatorsService.getSecurityTypeByAssetClassId(assetClass.getAsset_class_id());
		 	}
			modelAndView.addObject("assetClassConsumerSecurityMaps", assetClassSecurityMaps);
		} catch (Exception ex) {
			logger.error("Exception in loadSecurityType -- ", ex);
			modelAndView.addObject("errorMsg", "Unable to load Security type details, Please contact administrator");
		}
		return modelAndView;
	}
	
	/**
	 * method to load consumer Security types
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value =RequestConstans.Consumer.LOAD_COST_PREFERENCE_SECURITY_DISTRI_TYPES, method = RequestMethod.GET)
	public ModelAndView loadCostPreferenceSecurityTypes(@RequestParam(value = "RAyuL", required = false) String assetType) {
		ModelAndView modelAndView = new ModelAndView("consumerpage/consumercostprefersecuritylist");
		List<AssetClassSecurityMap> assetClassSecurityMaps = null;
		try {
			assetType = CommonUtils.decrypt(assetType.getBytes());
			if(!assetType.equals("") && !assetType.equals("-SELECT-")){
				AssetClass assetClass = marketDataAggregatorsService.getAssetClassByName(assetType);
				assetClassSecurityMaps = marketDataAggregatorsService.getSecurityTypeByAssetClassId(assetClass.getAsset_class_id());
		 	}
			modelAndView.addObject("assetClassCostPreferenceSecurityDistriMaps", assetClassSecurityMaps);
		} catch (Exception ex) {
			logger.error("Exception in loadSecurityType -- ", ex);
			modelAndView.addObject("errorMsg", "Unable to load Security type details, Please contact administrator");
		}
		return modelAndView;
	}
}
