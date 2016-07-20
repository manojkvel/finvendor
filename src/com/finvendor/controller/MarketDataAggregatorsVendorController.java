/**
 * 
 */
package com.finvendor.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.finvendor.form.MarketDataAggregatorsVendorSearchForm;
import com.finvendor.model.AssetClass;
import com.finvendor.model.AssetClassDataDetails;
import com.finvendor.model.AssetClassSecurityMap;
import com.finvendor.model.Awards;
import com.finvendor.model.Cost;
import com.finvendor.model.Country;
import com.finvendor.model.CountryExchangeMap;
import com.finvendor.model.Exchange;
import com.finvendor.model.FinVendorUser;
import com.finvendor.model.Region;
import com.finvendor.model.RegionCountryMap;
import com.finvendor.model.SecurityType;
import com.finvendor.model.Support;
import com.finvendor.model.VendorSearchResult;
import com.finvendor.service.MarketDataAggregatorsService;
import com.finvendor.util.AssetSecurityTypes.Assets;
import com.finvendor.util.AssetSecurityTypes.SecurityTypes;
import com.finvendor.util.CommonUtils;
import com.finvendor.util.RequestConstans;

/**
 * @author rayulu vemula
 *
 */
@Controller
public class MarketDataAggregatorsVendorController {

	private static Logger logger = LoggerFactory.getLogger(MarketDataAggregatorsVendorController.class);
	
	@Autowired
	private MarketDataAggregatorsService marketDataAggregatorsService;
	
	/**
	 * method for market data aggregators page navigation
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	
	@RequestMapping(value=RequestConstans.MarketAggregators.MARKETAGGREGATORS, method=RequestMethod.GET)
	public ModelAndView registerNavigation(HttpServletRequest request,@ModelAttribute("users") FinVendorUser users,
			@RequestParam(value = "RaYUnA", required = false) String username){
			logger.debug("Entering MarketDataAggregatorsVendor : registerNavigation");
			List<AssetClass> assetClasses = null;
			List<Region> regions = null;
			List<Country> countries = null;
			List<Exchange> exchanges = null;
			List<Support> supports = null;
			List<Cost> costs = null;
			List<Awards> awards = null;
			ModelAndView modelAndView=new ModelAndView(RequestConstans.MarketAggregators.MARKETAGGREGATORS);
			try{
				if(request.getSession().getAttribute("loggedInUser") == null){
					logger.error("No active session present. Redirecting to Home page");
					return new ModelAndView(RequestConstans.Login.HOME);
				}
				
				assetClasses = marketDataAggregatorsService.getAllAssetClass();
				regions = marketDataAggregatorsService.getAllRegionClass();
				countries = marketDataAggregatorsService.getAllCountries();
				exchanges = marketDataAggregatorsService.getAllExchanges();
				supports =  marketDataAggregatorsService.getAllVendorSupports();
				costs  = marketDataAggregatorsService.getAllCostInfo();
				awards = marketDataAggregatorsService.getAllAwards();
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
				
				modelAndView.addObject("username", username);
				 
				/*
				String usernameCheck= SecurityContextHolder.getContext().
						getAuthentication().getName(); 
				 if(username.equals("")){
					modelAndView.addObject("usernameCheck", usernameCheck);
					modelAndView.addObject("checkingresultpageview", "checkingresultpageview");
					modelAndView=new ModelAndView(RequestConstans.Login.HOME);
				}else{ 
					assetClasses = marketDataAggregatorsService.getAllAssetClass();
					regions = marketDataAggregatorsService.getAllRegionClass();
					countries = marketDataAggregatorsService.getAllCountries();
					exchanges = marketDataAggregatorsService.getAllExchanges();
					supports =  marketDataAggregatorsService.getAllVendorSupports();
					costs  = marketDataAggregatorsService.getAllCostInfo();
					awards = marketDataAggregatorsService.getAllAwards();
					
					modelAndView.addObject("assetClasses", assetClasses);
					modelAndView.addObject("regions", regions);
					modelAndView.addObject("regionslist", regions);
					modelAndView.addObject("countries", countries);
					modelAndView.addObject("exchanges", exchanges);
					modelAndView.addObject("supports", supports);
					modelAndView.addObject("costs", costs);
					modelAndView.addObject("awards", awards);	
					
					modelAndView.addObject("username", username);
					
				 } 
				 */
			}catch (Exception e) {
				logger.error("Error MarketDataAggregatorsVendor : registerNavigation", e);
				modelAndView.addObject("errorMessage", "Error reading MarketDataAggregatorsVendor details : " + e.getMessage());
			}
			logger.debug("Leaving MarketDataAggregatorsVendor : registerNavigation");
			return modelAndView;
	}
	
	/**
	 * method to region Types
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value =RequestConstans.MarketAggregators.LOAD_REGION_NAME_TYPES, method = RequestMethod.GET)
	public ModelAndView loadRegionNameTypes(@RequestParam(value = "SiMhRaYuL", required = false) String regionName) {
		ModelAndView modelAndView = new ModelAndView("regionnameslist");
		List<Region> regions = null;
		try {
          
			regionName = CommonUtils.decrypt(regionName.getBytes());
			
			if(!regionName.equals("") && regionName != null){
				regions = marketDataAggregatorsService.getRegionNamesByName(regionName);
		 	}
			modelAndView.addObject("regionsNames", regions);
		} catch (Exception ex) {
			logger.error("Exception in load regionNames -- ", ex);
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
	@RequestMapping(value =RequestConstans.MarketAggregators.LOAD_SECURITY_TYPES, method = RequestMethod.GET)
	public ModelAndView loadSecurityType(@RequestParam(value = "RAyuL", required = false) String assetType) {
		ModelAndView modelAndView = new ModelAndView("assetsecurity/securitylist");
		List<AssetClassSecurityMap> assetClassSecurityMaps = null;
		try {
			assetType = CommonUtils.decrypt(assetType.getBytes());
			if(!assetType.equals("") && !assetType.equals("-SELECT-")){
				assetClassSecurityMaps = marketDataAggregatorsService.getSecurityTypeByAssetClassId(Integer.parseInt(assetType));
		 	}
			modelAndView.addObject("assetClassSecurityMaps", assetClassSecurityMaps);
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
	@RequestMapping(value =RequestConstans.MarketAggregators.LOAD_EQUITIES_SECURITY_TYPES, method = RequestMethod.GET)
	public ModelAndView loadEquitiesSecurityType(@RequestParam(value = "RAyuL", required = false) String assetType) {
		ModelAndView modelAndView = new ModelAndView("assetsecurity/equiSecuritylist");
		List<AssetClassSecurityMap> assetClassSecurityMaps = null;
		try {
			assetType = CommonUtils.decrypt(assetType.getBytes());
			if(!assetType.equals("") && !assetType.equals("-SELECT-")){
				assetClassSecurityMaps = marketDataAggregatorsService.getSecurityTypeByAssetClassId(Integer.parseInt(assetType));
		 	}
			modelAndView.addObject("assetClassEquiSecurityMaps", assetClassSecurityMaps);
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
	@RequestMapping(value =RequestConstans.MarketAggregators.LOAD_FI_SECURITY_TYPES, method = RequestMethod.GET)
	public ModelAndView loadFISecurityType(@RequestParam(value = "RAyuL", required = false) String assetType) {
		ModelAndView modelAndView = new ModelAndView("assetsecurity/fiSecuritylist");
		List<AssetClassSecurityMap> assetClassSecurityMaps = null;
		try {
			assetType = CommonUtils.decrypt(assetType.getBytes());
			if(!assetType.equals("") && !assetType.equals("-SELECT-")){
				assetClassSecurityMaps = marketDataAggregatorsService.getSecurityTypeByAssetClassId(Integer.parseInt(assetType));
		 	}
			modelAndView.addObject("assetClassFISecurityMaps", assetClassSecurityMaps);
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
	@RequestMapping(value =RequestConstans.MarketAggregators.LOAD_INDICES_SECURITY_TYPES, method = RequestMethod.GET)
	public ModelAndView loadIndicesSecurityType(@RequestParam(value = "RAyuL", required = false) String assetType) {
		ModelAndView modelAndView = new ModelAndView("assetsecurity/indicesSecuritylist");
		List<AssetClassSecurityMap> assetClassSecurityMaps = null;
		try {
			assetType = CommonUtils.decrypt(assetType.getBytes());
			if(!assetType.equals("") && !assetType.equals("-SELECT-")){
				assetClassSecurityMaps = marketDataAggregatorsService.getSecurityTypeByAssetClassId(Integer.parseInt(assetType));
		 	}
			modelAndView.addObject("assetClassIndicesSecurityMaps", assetClassSecurityMaps);
		} catch (Exception ex) {
			logger.error("Exception in loadSecurityType -- ", ex);
			modelAndView.addObject("errorMsg", "Unable to load Security type details, Please contact administrator");
		}
		return modelAndView;
	}
	
	/**
	 * method to full country types
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value =RequestConstans.MarketAggregators.LOAD_COUNTRY_TYPES, method = RequestMethod.GET)
	public ModelAndView loadCountryTypes(@RequestParam(value = "RAYVEM", required = false) String regionId) {
		ModelAndView modelAndView = new ModelAndView("country/countrylist");
		List<RegionCountryMap> regionCountryMaps = null;
		logger.info("Exception in loadCountry types -- ");
		try {
			regionId = CommonUtils.decrypt(regionId.getBytes());
			if(!regionId.equals("") && !regionId.equals("-SELECT-")){
				regionCountryMaps = marketDataAggregatorsService.getRegionCountryMapsRegionId(Integer.parseInt(regionId));
		 	}
			modelAndView.addObject("regionCountryMaps", regionCountryMaps);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Exception in loadCountry types -- ", ex);
			modelAndView.addObject("errorMsg", "Unable to load Security type details, Please contact administrator");
		}
		return modelAndView;
	}
	
	
	/**
	 * method to country list
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value =RequestConstans.MarketAggregators.LOAD_COUNTRY_TYPES_INFO, method = RequestMethod.GET)
	public ModelAndView loadCountryTypesInfo(@RequestParam(value = "RAYVEM", required = false) String regionId) {
		ModelAndView modelAndView = new ModelAndView("country/countrylistinfo");
		List<RegionCountryMap> regionCountryMaps = null;
		logger.info("Exception in loadCountry types -- ");
		try {
			regionId = CommonUtils.decrypt(regionId.getBytes());
			if(!regionId.equals("") && !regionId.equals("-SELECT-")){
				regionCountryMaps = marketDataAggregatorsService.getRegionCountryMapsRegionId(Integer.parseInt(regionId));
		 	}
			modelAndView.addObject("regionCountryMapsinfo", regionCountryMaps);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Exception in loadCountry types -- ", ex);
			modelAndView.addObject("errorMsg", "Unable to load Security type details, Please contact administrator");
		}
		return modelAndView;
	}
	
	
	/**
	 * method to country list
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value =RequestConstans.MarketAggregators.LOAD_COUNTRY_TYPES_INFO_MULTI, method = RequestMethod.GET)
	public ModelAndView loadCountryTypesInfoMulti(@RequestParam(value = "VeMuRaYu", required = false) String regionId) {
		ModelAndView modelAndView = new ModelAndView("country/countrylistinfomulti");
		List<RegionCountryMap> regionCountryMaps = null;
		logger.info("Exception in loadCountry types -- ");
		try {
			regionId = CommonUtils.decrypt(regionId.getBytes());
			if(!regionId.equals("") && !regionId.equals("-SELECT-")){
				regionCountryMaps = marketDataAggregatorsService.getRegionCountryMapsRegionId(Integer.parseInt(regionId));
		 	}
			modelAndView.addObject("regionCountryMapsinfoMulti", regionCountryMaps);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Exception in loadCountry types -- ", ex);
			modelAndView.addObject("errorMsg", "Unable to load Security type details, Please contact administrator");
		}
		return modelAndView;
	}
	
	/**
	 * method to country list
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value =RequestConstans.MarketAggregators.LOAD_COUNTRY_TYPES_INFO_ASSET, method = RequestMethod.GET)
	public ModelAndView loadCountryTypesInfoAsset(@RequestParam(value = "RaYuLuVeMuLa", required = false) String regionId) {
		ModelAndView modelAndView = new ModelAndView("country/countrylistinfoasset");
		List<RegionCountryMap> regionCountryMaps = null;
		logger.info("Exception in loadCountry types -- ");
		try {
			regionId = CommonUtils.decrypt(regionId.getBytes());
			if(!regionId.equals("") && !regionId.equals("-SELECT-")){
				regionCountryMaps = marketDataAggregatorsService.getRegionCountryMapsRegionId(Integer.parseInt(regionId));
		 	}
			modelAndView.addObject("regionCountryMapsinfoAsset", regionCountryMaps);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Exception in loadCountry types -- ", ex);
			modelAndView.addObject("errorMsg", "Unable to load Security type details, Please contact administrator");
		}
		return modelAndView;
	}
	/**
	 * method to country list
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value =RequestConstans.MarketAggregators.LOAD_COUNTRY_TYPES_INFO_ASSET_FI, method = RequestMethod.GET)
	public ModelAndView loadCountryTypesInfoAssetFI(@RequestParam(value = "RaYuLuVeMuLa", required = false) String regionId) {
		ModelAndView modelAndView = new ModelAndView("country/countrylistinfoassetfi");
		List<RegionCountryMap> regionCountryMaps = null;
		logger.info("Exception in loadCountry types -- ");
		try {
			regionId = CommonUtils.decrypt(regionId.getBytes());
			if(!regionId.equals("") && !regionId.equals("-SELECT-")){
				regionCountryMaps = marketDataAggregatorsService.getRegionCountryMapsRegionId(Integer.parseInt(regionId));
		 	}
			modelAndView.addObject("regionCountryMapsinfoAssetFI", regionCountryMaps);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Exception in loadCountry types -- ", ex);
			modelAndView.addObject("errorMsg", "Unable to load Security type details, Please contact administrator");
		}
		return modelAndView;
	}
	/**
	 * method to get region types
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value =RequestConstans.MarketAggregators.LOAD_EXCHANGE_TYPES_ASSET_FI, method = RequestMethod.GET)
	public ModelAndView loadExchangeTypesFI(@RequestParam(value = "RaYuLU", required = false) String countryId) {
		ModelAndView modelAndView = new ModelAndView("exchange/exchangelistassetfi");
		List<CountryExchangeMap> countryExchangeMaps = null;
		logger.info("Exception in loadExchange types -- ");
		try {
			countryId = CommonUtils.decrypt(countryId.getBytes());
			if(!countryId.equals("") && !countryId.equals("-SELECT-")){
				countryExchangeMaps = marketDataAggregatorsService.getCountryExchangeMapsByCountryId(Integer.parseInt(countryId));
		 	}
			modelAndView.addObject("countryExchangeMapsAssetFI", countryExchangeMaps);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Exception in loadExchange types -- ", ex);
			modelAndView.addObject("errorMsg", "Unable to load Security type details, Please contact administrator");
		}
		return modelAndView;
	}
	/**
	 * method to get region types
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value =RequestConstans.MarketAggregators.LOAD_EXCHANGE_TYPES, method = RequestMethod.GET)
	public ModelAndView loadExchangeTypes(@RequestParam(value = "RaYuLU", required = false) String countryId) {
		ModelAndView modelAndView = new ModelAndView("exchange/exchangelist");
		List<CountryExchangeMap> countryExchangeMaps = null;
		logger.info("Exception in loadExchange types -- ");
		try {
			countryId = CommonUtils.decrypt(countryId.getBytes());
			if(!countryId.equals("") && !countryId.equals("-SELECT-")){
				countryExchangeMaps = marketDataAggregatorsService.getCountryExchangeMapsByCountryId(Integer.parseInt(countryId));
		 	}
			modelAndView.addObject("countryExchangeMaps", countryExchangeMaps);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Exception in loadExchange types -- ", ex);
			modelAndView.addObject("errorMsg", "Unable to load Security type details, Please contact administrator");
		}
		return modelAndView;
	}
	
	/**
	 * method to get region types
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value =RequestConstans.MarketAggregators.LOAD_EXCHANGE_TYPES_ASSET, method = RequestMethod.GET)
	public ModelAndView loadExchangeAssetTypes(@RequestParam(value = "VeMuLaRaYuL", required = false) String countryId) {
		ModelAndView modelAndView = new ModelAndView("exchange/exchangelistasset");
		List<CountryExchangeMap> countryExchangeMaps = null;
		logger.info("Exception in loadExchange types -- ");
		try {
			countryId = CommonUtils.decrypt(countryId.getBytes());
			if(!countryId.equals("") && !countryId.equals("-SELECT-")){
				countryExchangeMaps = marketDataAggregatorsService.getCountryExchangeMapsByCountryId(Integer.parseInt(countryId));
		 	}
			modelAndView.addObject("countryExchangeMapsAsset", countryExchangeMaps);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Exception in loadExchange types -- ", ex);
			modelAndView.addObject("errorMsg", "Unable to load Security type details, Please contact administrator");
		}
		return modelAndView;
	}
	/**
	 * method to pull Security types
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("unused")
	private List<String> getSecurityTypes(String assetType) {
		List<String> securityTypes = null;
		try{
		if (assetType.equals(Assets.EQUITIES.getName())) 
			securityTypes = SecurityTypes.EQUITIES.getValues();
		if (assetType.equals(Assets.FI.getName())) 
			securityTypes = SecurityTypes.FI.getValues();
		if (assetType.equals(Assets.INDICES.getName())) 
			securityTypes = SecurityTypes.INDICES.getValues();
		if (assetType.equals(Assets.DERIVATIVES.getName())) 
			securityTypes = SecurityTypes.DERIVATIVES.getValues();
		if (assetType.equals(Assets.FX.getName())) 
			securityTypes = SecurityTypes.FX.getValues();
		if (assetType.equals(Assets.AI.getName())) 
			securityTypes = SecurityTypes.AI.getValues();
		if (assetType.equals(Assets.MISC.getName())) 
			securityTypes = SecurityTypes.MISC.getValues();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return securityTypes;
	}

	/**
	 * method for result of market data aggregators
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	
	@SuppressWarnings("unused")
	@RequestMapping(value=RequestConstans.MarketAggregators.SINGLE_ASSET_CLASS_SEARCH_RESULT, method=RequestMethod.POST)
	public ModelAndView singleSearchAssetClass(HttpServletRequest request,@ModelAttribute("users") FinVendorUser users,
			@RequestParam(value = "assetclass", required = false) String assetclassId,
			@RequestParam(value = "securitytype", required = false) String securitytypeId,
			@RequestParam(value = "dataattribute", required = false) String dataattribute,
			@RequestParam(value = "datacoverageregion", required = false) String datacoverageregionId,
			@RequestParam(value = "datacoveragecountry", required = false) String datacoveragecountryId,
			@RequestParam(value = "datacoverageexchange", required = false) String datacoverageexchangeId,
			
			@RequestParam(value = "vendorregionofincorp", required = false) String vendorregionofincorp,
			@RequestParam(value = "vendorcountryofincorp", required = false) String vendorcountryofincorp,
			@RequestParam(value = "vendorprofilefreshness", required = false) String vendorprofilefreshness,
			@RequestParam(value = "vendoryearoperation", required = false) String vendoryearoperation,
			@RequestParam(value = "searchkeyword", required = false) String searchkeyword,
			@RequestParam(value = "vendorsupportregion", required = false) String vendorsupportregion,
			@RequestParam(value = "vendorsupporttime", required = false) String vendorsupporttime,
			@RequestParam(value = "awards", required = false) String vendorawards,
			@RequestParam(value = "acquisitioncostrange", required = false) String vendorcostrange,
			@RequestParam(value = "RaYvEmUl", required = false) String username){
			logger.info("Mehtod to navigate market data aggregators toregisterNavigation--:");
			ModelAndView modelAndView=new ModelAndView("singleassetsearchresult");
			
			
			List<AssetClassDataDetails> assetClassDataInfoList = new ArrayList<AssetClassDataDetails>();
			AssetClassDataDetails assetClassDatainfo = null;
			List<AssetClassDataDetails> assetClassDataDetails = null;
			try{
			  username = CommonUtils.decrypt(username.getBytes());
			if(assetclassId != null && securitytypeId != null && vendorregionofincorp == null || 
					(datacoverageregionId!=null && !datacoverageregionId.equals("-SELECT-") && !datacoverageregionId.equals(""))
					||(datacoveragecountryId!=null && !datacoveragecountryId.equals("-SELECT-") && !datacoveragecountryId.equals(""))
					|| (datacoverageexchangeId!=null && !datacoverageexchangeId.equals("-SELECT-") && !datacoverageexchangeId.equals(""))){
				List<String> securitytypeList = Arrays.asList(securitytypeId.split(","));
				List<String> regionsList = null;
				if(datacoverageregionId != null) 
					regionsList = Arrays.asList(datacoverageregionId.split(","));	
				List<String> countryList = null;
				if(datacoveragecountryId != null) 
					countryList = Arrays.asList(datacoveragecountryId.split(","));	
				List<String> exchangeList = null;
				if(exchangeList != null) 
					exchangeList= Arrays.asList(datacoverageexchangeId.split(","));	
				if(securitytypeList.size() > 0) 
				assetClassDataDetails = marketDataAggregatorsService.getSingleAssetClassSearchResultInfo(assetclassId,securitytypeList,
						dataattribute,regionsList,countryList,exchangeList);
				 Set<AssetClassDataDetails> assetClassDataDetailsSet = new TreeSet<AssetClassDataDetails>(new Comparator<AssetClassDataDetails>() {
					@Override
					public int compare(AssetClassDataDetails assetClassDatainfo1,
							AssetClassDataDetails assetClassDatainfo2) {
						 if(assetClassDatainfo1.getCompany().equals(assetClassDatainfo2.getCompany())){
							 return 0; 
						 }
						return 1;
					}
				});
				assetClassDataDetailsSet.addAll(assetClassDataDetails);
				List<AssetClassDataDetails> assetClassDataDetailslist = new ArrayList<AssetClassDataDetails>(assetClassDataDetailsSet);
				for (AssetClassDataDetails assetClassDatalist1 : assetClassDataDetailslist) {
					assetClassDatainfo = new AssetClassDataDetails(null,null, null, null, null, null, null, null,
							null, null, null, null, null, null, null,null,null,null,null,null,null);
					for (AssetClassDataDetails assetClassDatalist2 : assetClassDataDetailslist) {
						if(assetClassDatalist1.getCompany().equals(assetClassDatalist2.getCompany())){
							assetClassDatainfo.setCompany(assetClassDatalist1.getCompany());
						} 
						if(assetClassDatalist1.getUsername().equals(assetClassDatalist2.getUsername())){
							assetClassDatainfo.setUsername(assetClassDatalist1.getUsername());
						}
						if(assetClassDatalist1.getCountry_id().equals(assetClassDatalist2.getCountry_id())){
							assetClassDatainfo.setCountry_id(assetClassDatalist1.getCountry_id());
						} 
						if(assetClassDatalist1.getExchange_id().equals(assetClassDatalist2.getExchange_id())){
							assetClassDatainfo.setExchange_id(assetClassDatalist1.getExchange_id());
						} 
						if(assetClassDatalist1.getCost_range().equals(assetClassDatalist2.getCost_range())){
							assetClassDatainfo.setCost_range(assetClassDatalist1.getCost_range());
						} 
						if(assetClassDatalist1.getDistribution_mode_name().equals(assetClassDatalist2.getDistribution_mode_name())){
							assetClassDatainfo.setDistribution_mode_name(assetClassDatalist1.getDistribution_mode_name());
						}
						assetClassDataInfoList.add(assetClassDatainfo);
					}
				}   
				modelAndView.addObject("assetClassDataInfoList", assetClassDataDetailslist);
				if(assetClassDataInfoList.size() > 0)
				modelAndView.addObject("username", assetClassDataInfoList.get(0).getUsername());
				
			}else{
				List<String> securitytypeList = null;
				if(securitytypeId != null)
				 securitytypeList = Arrays.asList(securitytypeId.split(","));
				List<String> vendorregionofincorpList = null;
				if(vendorregionofincorp != null)
				vendorregionofincorpList= Arrays.asList(vendorregionofincorp.split(","));
				List<String> vendorcountryofincorpList = null;
				if(vendorcountryofincorp != null)
				vendorcountryofincorpList = Arrays.asList(vendorcountryofincorp.split(","));
				assetClassDataDetails = marketDataAggregatorsService.getSingleAssetClassVendorDetails(assetclassId,securitytypeList,
						vendorregionofincorpList ,vendorcountryofincorpList,vendorprofilefreshness,vendoryearoperation,
						searchkeyword,vendorsupportregion,vendorsupporttime,vendorawards,vendorcostrange);
				/*List<String> vendorprofilefreshnessList = Arrays.asList(vendorprofilefreshness.split(","));
				List<String> vendoryearoperationList = Arrays.asList(vendoryearoperation.split(","));
				List<String> vendorsupportregionList = Arrays.asList(vendorsupportregion.split(","));
				List<String> vendorsupporttimeList = Arrays.asList(vendorsupporttime.split(","));
				List<String> awardsList = Arrays.asList(awards.split(","));
				List<String> acquisitioncostrangeList = Arrays.asList(acquisitioncostrange.split(","));
				
				if(vendorcountryofincorpList.size() > 0) if(vendorprofilefreshnessList.size() > 0) if(vendoryearoperationList.size() > 0)
					if(vendorsupportregionList.size() > 0) if(vendorsupporttimeList.size() > 0) if(awardsList.size() > 0) if(acquisitioncostrangeList.size() > 0)
						
				assetClassDataDetails = marketDataAggregatorsService.getSingleAssetClassSearchResultVendorInfo(assetclassId,securitytypeList,
						vendorregionofincorp ,vendorcountryofincorpList,vendorprofilefreshnessList,vendoryearoperationList,
						searchkeyword,vendorsupportregionList,vendorsupporttimeList,awardsList,acquisitioncostrangeList);*/
				Set<AssetClassDataDetails> assetClassDataDetailsSet = new TreeSet<AssetClassDataDetails>(new Comparator<AssetClassDataDetails>() {
					@Override
					public int compare(AssetClassDataDetails assetClassDatainfo1,
							AssetClassDataDetails assetClassDatainfo2) {
						 if(assetClassDatainfo1.getCompany().equals(assetClassDatainfo2.getCompany())){
							 return 0; 
						 }
						return 1;
					}
				});
				assetClassDataDetailsSet.addAll(assetClassDataDetails);
				List<AssetClassDataDetails> assetClassDataDetailslist = new ArrayList<AssetClassDataDetails>(assetClassDataDetailsSet);
				for (AssetClassDataDetails assetClassDatalist1 : assetClassDataDetailslist) {
					assetClassDatainfo = new AssetClassDataDetails(null,null, null, null, null, null, null, null,
							null, null, null, null, null, null, null,null,null,null,null,null,null);
					for (AssetClassDataDetails assetClassDatalist2 : assetClassDataDetailslist) {
						if(assetClassDatalist1.getCompany().equals(assetClassDatalist2.getCompany())){
							assetClassDatainfo.setCompany(assetClassDatalist1.getCompany());
						} 
						if(assetClassDatalist1.getUsername().equals(assetClassDatalist2.getUsername())){
							assetClassDatainfo.setUsername(assetClassDatalist1.getUsername());
						}
						if(assetClassDatalist1.getCountry_id().equals(assetClassDatalist2.getCountry_id())){
							assetClassDatainfo.setCountry_id(assetClassDatalist1.getCountry_id());
						} 
						if(assetClassDatalist1.getExchange_id().equals(assetClassDatalist2.getExchange_id())){
							assetClassDatainfo.setExchange_id(assetClassDatalist1.getExchange_id());
						} 
						if(assetClassDatalist1.getCost_range().equals(assetClassDatalist2.getCost_range())){
							assetClassDatainfo.setCost_range(assetClassDatalist1.getCost_range());
						} 
						if(assetClassDatalist1.getDistribution_mode_name().equals(assetClassDatalist2.getDistribution_mode_name())){
							assetClassDatainfo.setDistribution_mode_name(assetClassDatalist1.getDistribution_mode_name());
						}
						assetClassDataInfoList.add(assetClassDatainfo);
					}
				}   
				modelAndView.addObject("assetClassDataInfoList", assetClassDataDetailslist);
			}
			modelAndView.addObject("username", username);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return modelAndView;
	}
	
	/**
	 * method for result of multi asset class search
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value=RequestConstans.MarketAggregators.MULTI_ASSET_CLASS_SEARCH_RESULT, method=RequestMethod.POST)
	public ModelAndView multiSearchAssetClass(HttpServletRequest request, @ModelAttribute("marketDataAggregatorsVendorSearchForm") MarketDataAggregatorsVendorSearchForm dataForm,
			@RequestParam(value = "RaYUnA", required = false) String username
			){
					ModelAndView modelAndView=new ModelAndView("multiassetsearchresult");
					try{
					
						Map parameterMap = request.getParameterMap();
						Map<Object, Object> searchData = new LinkedHashMap<Object, Object>();
						Iterator entries = parameterMap.entrySet().iterator();
						int counter=0;
						while (entries.hasNext()) {
						    Map.Entry entry = (Map.Entry) entries.next();
						    try{
						    String []s =(String[])entry.getValue();
						    String tempStr = "";
						    for(String str: s){
						    	tempStr =str != null && tempStr.length()>1? str+","+tempStr:str;
						    }
						    searchData.put(entry.getKey(), tempStr);
						    System.out.println((++counter)+" : Key = " + entry.getKey() + ", Value = " + tempStr);
						    		
						    }catch(Exception e){
						    	
						    }
						}
						
						System.out.println("dataForm = " + dataForm);
						
						//for(Map.Entry<Object,Object> t: parameterMap.entrySet())
						Map<String, Object> multiAssetClassSearchResult = marketDataAggregatorsService.getMultiAssetClassSearchResult(searchData,dataForm);
						Set<VendorSearchResult> marketDataAggregatorsVendorSearchs = (Set<VendorSearchResult>)multiAssetClassSearchResult.get("vendorSearchResultList");
						
					
						
			modelAndView.addObject("marketDataAggregatorsVendorSearchs", marketDataAggregatorsVendorSearchs);
			modelAndView.addObject("assetCountries", multiAssetClassSearchResult.get("assetCountries"));
			modelAndView.addObject("assetExchanges", multiAssetClassSearchResult.get("assetExchanges"));
			modelAndView.addObject("result", RequestConstans.MarketAggregators.MULTI_ASSET_CLASS_SEARCH_RESULT);
			modelAndView.addObject("username", username);			 
 			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return modelAndView;
	}
	
	
}
