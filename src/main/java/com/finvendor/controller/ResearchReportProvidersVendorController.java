//package com.finvendor.controller;
//
//import com.finvendor.form.MarketDataAggregatorsVendorSearchForm;
//import com.finvendor.form.ResearchReportProvidersVendorSearchForm;
//import com.finvendor.model.*;
//import com.finvendor.service.MarketDataAggregatorsService;
//import com.finvendor.util.RequestConstans;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.*;
//
//@Controller
//public class ResearchReportProvidersVendorController {
//
//	private static final Logger logger = LoggerFactory.getLogger(ResearchReportProvidersVendorController.class.getName());
//
//	@Autowired
//	private MarketDataAggregatorsService marketDataAggregatorsService;
//
//	/* Research Reports Vendor Search */
//	@SuppressWarnings("rawtypes")
//	@RequestMapping(value="reserachReportVendorSearch", method=RequestMethod.POST)
//	public ModelAndView multiSearchAssetClass(HttpServletRequest request,
//			@ModelAttribute("vendorSearchForm")
//				MarketDataAggregatorsVendorSearchForm dataForm) {
//
//		ModelAndView modelAndView = new ModelAndView("multiassetsearchresult");
//		try{
//			Map parameterMap = request.getParameterMap();
//			Map<Object, Object> searchData = new LinkedHashMap<Object, Object>();
//			Iterator entries = parameterMap.entrySet().iterator();
//			int counter = 0;
//
//			while (entries.hasNext()) {
//				Map.Entry entry = (Map.Entry) entries.next();
//				try {
//					String []s = (String[])entry.getValue();
//					String tempStr = "";
//					for(String str: s) {
//						tempStr =str != null && tempStr.length()>1? str+","+tempStr:str;
//					}
//					searchData.put(entry.getKey(), tempStr);
//					System.out.println((++counter)+" : Key = " + entry.getKey() + ", Value = " + tempStr);
//
//				} catch(Exception e) {
//
//				}
//			}
//
//			System.out.println("dataForm = " + dataForm);
//
//			Map<String, Object> multiAssetClassSearchResult = marketDataAggregatorsService.
//					getResearchReportVendorSearchResult(searchData, dataForm);
//			Set<VendorSearchResult> marketDataAggregatorsVendorSearchs =
//					(Set<VendorSearchResult>)multiAssetClassSearchResult.get("vendorSearchResultList");
//
//			modelAndView.addObject("marketDataAggregatorsVendorSearchs", marketDataAggregatorsVendorSearchs);
//			modelAndView.addObject("assetCountries", multiAssetClassSearchResult.get("assetCountries"));
//			modelAndView.addObject("assetRegions", multiAssetClassSearchResult.get("assetRegions"));
//			modelAndView.addObject("researchSubAreas", multiAssetClassSearchResult.get("researchSubAreas"));
//			modelAndView.addObject("awardsMap", multiAssetClassSearchResult.get("awardsMap"));
//			modelAndView.addObject("result", RequestConstans.ResearchReportProviders.MULTI_ASSET_CLASS_SEARCH_RESULT);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return modelAndView;
//	}
//
//
//	@RequestMapping(value=RequestConstans.ResearchReportProviders.RESEARCH_REPORT_PROVIDERS_INDEX_PAGE, method=RequestMethod.GET)
//	public ModelAndView researchReportProviderIndex(HttpServletRequest request,@ModelAttribute("users") FinVendorUser users,
//			@RequestParam(value = "RaYUnA", required = false) String username){
//			logger.debug("Entering ResearchReportProvidersVendor : researchReportProviderIndex");
//			List<AssetClass> assetClasses = null;
//			List<Region> regions = null;
//			List<Country> countries = null;
//			List<Exchange> exchanges = null;
//			List<Support> supports = null;
//			List<Cost> costs = null;
//			List<Awards> awards = null;
//			ModelAndView modelAndView=new ModelAndView(RequestConstans.ResearchReportProviders.RESEARCH_REPORT_PROVIDERS_INDEX_PAGE);
//			try{
//				if(request.getSession().getAttribute("loggedInUser") == null){
//					return new ModelAndView(RequestConstans.Login.HOME);
//				}
//
//				assetClasses = marketDataAggregatorsService.getAllAssetClass();
//				regions = marketDataAggregatorsService.getAllRegionClass();
//				countries = marketDataAggregatorsService.getAllCountries();
//				exchanges = marketDataAggregatorsService.getAllExchanges();
//				supports =  marketDataAggregatorsService.getAllVendorSupports();
//				costs  = marketDataAggregatorsService.getAllCostInfo();
//				awards = marketDataAggregatorsService.getAllAwards(null);
//
//				modelAndView.addObject("assetClasses", assetClasses);
//				modelAndView.addObject("regions", regions);
//				modelAndView.addObject("regionslist", regions);
//				modelAndView.addObject("countries", countries);
//				modelAndView.addObject("exchanges", exchanges);
//				modelAndView.addObject("supports", supports);
//				modelAndView.addObject("costs", costs);
//				modelAndView.addObject("awards", awards);
//
//
//			}catch (Exception e) {
//				logger.error("Error ResearchReportProvidersVendor : researchReportProviderIndex", e);
//				modelAndView.addObject("errorMessage", "Error reading ResearchReportProvidersVendor details : " + e.getMessage());
//			}
//			logger.debug("Leaving ResearchReportProvidersVendor : researchReportProviderIndex");
//			return modelAndView;
//	}
//
//
//	@SuppressWarnings("rawtypes")
//	@RequestMapping(value=RequestConstans.ResearchReportProviders.MULTI_ASSET_CLASS_SEARCH_RESULT, method=RequestMethod.POST)
//	public ModelAndView multiSearchAssetClass(HttpServletRequest request, @ModelAttribute("researchReportProvidersVendorSearchForm") ResearchReportProvidersVendorSearchForm dataForm,
//			@RequestParam(value = "RaYUnA", required = false) String username
//			){
//					ModelAndView modelAndView=new ModelAndView("multiassetsearchresult");
//					try{
//
//						Map parameterMap = request.getParameterMap();
//						Map<Object, Object> searchData = new LinkedHashMap<Object, Object>();
//						Iterator entries = parameterMap.entrySet().iterator();
//						int counter=0;
//						while (entries.hasNext()) {
//						    Map.Entry entry = (Map.Entry) entries.next();
//						    try{
//						    String []s =(String[])entry.getValue();
//						    String tempStr = "";
//						    for(String str: s){
//						    	tempStr =str != null && tempStr.length()>1? str+","+tempStr:str;
//						    }
//						    searchData.put(entry.getKey(), tempStr);
//						    System.out.println((++counter)+" : Key = " + entry.getKey() + ", Value = " + tempStr);
//
//						    }catch(Exception e){
//
//						    }
//						}
//
//						//for(Map.Entry<Object,Object> t: parameterMap.entrySet())
//					 //List<ResearchReportProvidersVendorSearchForm> rrMultiAssetClassSearchResult = marketDataAggregatorsService.getRRMultiAssetClassSearchResult(searchData, dataForm);
//
//			//modelAndView.addObject("marketDataAggregatorsVendorSearchs", rrMultiAssetClassSearchResult);
//			modelAndView.addObject("result", RequestConstans.ResearchReportProviders.MULTI_ASSET_CLASS_SEARCH_RESULT);
//			modelAndView.addObject("username", username);
// 			}catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			return modelAndView;
//	}
//
//
//
//
//
//}
