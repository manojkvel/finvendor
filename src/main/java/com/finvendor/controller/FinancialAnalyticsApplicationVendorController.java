/**
 *
 */
package com.finvendor.controller;

import com.finvendor.form.FinancialAnalyticsApplicationVendorSearchForm;
import com.finvendor.model.*;
import com.finvendor.service.MarketDataAggregatorsService;
import com.finvendor.util.RequestConstans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rayulu vemula
 */
@Controller
public class FinancialAnalyticsApplicationVendorController {

    private static final Logger logger = LoggerFactory.getLogger(FinancialAnalyticsApplicationVendorController.class.getName());


    @Autowired
    private MarketDataAggregatorsService marketDataAggregatorsService;

    /**
     * method for analytics application page navigation
     *
     * @return modelAndView
     * @throws Exception the exception
     */

    @RequestMapping(value = RequestConstans.FinancialAnalyticsApplication.FINANCIAL_ANALYTICS_APPLICATION_INDEX_PAGE, method = RequestMethod.GET)
    public ModelAndView financialAnalyticsApplicationIndex(HttpServletRequest request, @ModelAttribute("users") FinVendorUser users,
                                                           @RequestParam(value = "RaYUnA", required = false) String username) {
        logger.debug("Entering FinancialAnalyticsApplication : financialAnalyticsApplicationIndex");
        List<AssetClass> assetClasses = null;
        List<Region> regions = null;
        List<Country> countries = null;
        List<Exchange> exchanges = null;
        List<Support> supports = null;
        List<Cost> costs = null;
        List<Awards> awards = null;
        ModelAndView modelAndView = new ModelAndView(RequestConstans.FinancialAnalyticsApplication.FINANCIAL_ANALYTICS_APPLICATION_INDEX_PAGE);
        try {
            if (request.getSession().getAttribute("loggedInUser") == null) {
                return new ModelAndView(RequestConstans.Login.HOME);
            }

            assetClasses = marketDataAggregatorsService.getAllAssetClass();
            regions = marketDataAggregatorsService.getAllRegionClass();
            countries = marketDataAggregatorsService.getAllCountries();
            exchanges = marketDataAggregatorsService.getAllExchanges();
            supports = marketDataAggregatorsService.getAllVendorSupports();
            costs = marketDataAggregatorsService.getAllCostInfo();
            awards = marketDataAggregatorsService.getAllAwards(null);

            modelAndView.addObject("assetClasses", assetClasses);
            modelAndView.addObject("regions", regions);
            modelAndView.addObject("regionslist", regions);
            modelAndView.addObject("countries", countries);
            modelAndView.addObject("exchanges", exchanges);
            modelAndView.addObject("supports", supports);
            modelAndView.addObject("costs", costs);
            modelAndView.addObject("awards", awards);
				
				/*
				username = CommonUtils.decrypt(username.getBytes());
				 String usernameCheck= SecurityContextHolder.getContext().
						getAuthentication().getName(); 
				 if(username.equals("")){
					modelAndView.addObject("usernameCheck", usernameCheck);
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
        } catch (Exception e) {
            logger.error("Error FinancialAnalyticsApplication : financialAnalyticsApplicationIndex", e);
            modelAndView.addObject("errorMessage", "Error reading FinancialAnalyticsApplication details : " + e.getMessage());
        }
        logger.debug("Leaving FinancialAnalyticsApplication : financialAnalyticsApplicationIndex");
        return modelAndView;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = RequestConstans.FinancialAnalyticsApplication.MULTI_ASSET_CLASS_SEARCH_RESULT, method = RequestMethod.POST)
    public ModelAndView multiSearchAssetClass(HttpServletRequest request, @ModelAttribute("financialAnalyticsApplicationVendorSearchForm") FinancialAnalyticsApplicationVendorSearchForm dataForm,
                                              @RequestParam(value = "RaYUnA", required = false) String username
    ) {
        ModelAndView modelAndView = new ModelAndView("multiassetsearchresult");
        try {

            Map parameterMap = request.getParameterMap();
            Map<Object, Object> searchData = new LinkedHashMap<Object, Object>();
            Iterator entries = parameterMap.entrySet().iterator();
            int counter = 0;
            while (entries.hasNext()) {
                Map.Entry entry = (Map.Entry) entries.next();
                try {
                    String[] s = (String[]) entry.getValue();
                    String tempStr = "";
                    for (String str : s) {
                        tempStr = str != null && tempStr.length() > 1 ? str + "," + tempStr : str;
                    }
                    searchData.put(entry.getKey(), tempStr);
                    System.out.println((++counter) + " : Key = " + entry.getKey() + ", Value = " + tempStr);

                } catch (Exception e) {

                }
            }

            //for(Map.Entry<Object,Object> t: parameterMap.entrySet())
            List<FinancialAnalyticsApplicationVendorSearchForm> faMultiAssetClassSearchResult = marketDataAggregatorsService.getFAMultiAssetClassSearchResult(searchData, dataForm);

            modelAndView.addObject("marketDataAggregatorsVendorSearchs", faMultiAssetClassSearchResult);
            modelAndView.addObject("result", RequestConstans.FinancialAnalyticsApplication.MULTI_ASSET_CLASS_SEARCH_RESULT);
            modelAndView.addObject("username", username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return modelAndView;
    }


}
