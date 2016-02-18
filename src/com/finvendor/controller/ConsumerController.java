/**
 * 
 */
package com.finvendor.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.finvendor.model.FinVendorUser;
import com.finvendor.model.Region;
import com.finvendor.model.Support;
import com.finvendor.service.MarketDataAggregatorsService;
import com.finvendor.service.UserService;
import com.finvendor.service.VendorService;
import com.finvendor.util.CommonUtils;
import com.finvendor.util.RequestConstans;

/**
 * @author rayulu vemula
 *
 */
@Controller
public class ConsumerController {

private static Logger logger = Logger.getLogger(ConsumerController.class);
	
	String[] vendorTypes = {"Financial Vendor- Data Aggregators", "Financial Vendor- Trading Applications","Financial Vendor- Financial Analytics applications","Financial Vendor- Research report"};
	String[] consumerTypes = {"Financial Firm - Sell side", "Financial Firm - Buy side","Financial Firm - Others","University/College","Other firm"};
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private VendorService vendorService;

	@Autowired
	private MarketDataAggregatorsService marketDataAggregatorsService;
	
	/**
	 * method for navigate consumer my profile
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	
	@RequestMapping(value=RequestConstans.Consumer.CONSUMER_MY_PROFILE, method=RequestMethod.GET)
	public ModelAndView consumerMyProfile(HttpServletRequest request,@ModelAttribute("users") FinVendorUser users,
			@RequestParam(value = "RaYVeMu", required = false) String username){
		logger.info("Mehtod for vendorNavigation--:");
		List<AssetClass> assetClasses = null;
		List<Region> regions = null;
		List<Country> countries = null;
		List<Exchange> exchanges = null;
		List<Support> supports = null;
		List<Cost> costs = null;
		List<Awards> awards = null;
		ModelAndView modelAndView=new ModelAndView(RequestConstans.Consumer.CONSUMER_MY_PROFILE);
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
			
			modelAndView.addObject("consumerMyProfiletab", "consumermyprofile");
			
			modelAndView.addObject("username", username);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Mehtod for vendorNavigation--:");
		}
		return modelAndView;
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
	@RequestMapping(value =RequestConstans.Consumer.LOAD_CONSUMER_SECURITY_TYPES, method = RequestMethod.GET)
	public ModelAndView loadConsumerSecurityType(@RequestParam(value = "RAyuL", required = false) String assetType) {
		ModelAndView modelAndView = new ModelAndView("consumerpage/consumersecuritylist");
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
