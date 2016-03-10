/**
 * 
 */
package com.finvendor.controller;

import java.util.List;

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

import com.finvendor.model.AssetClass;
import com.finvendor.model.Awards;
import com.finvendor.model.Cost;
import com.finvendor.model.Country;
import com.finvendor.model.Exchange;
import com.finvendor.model.Region;
import com.finvendor.model.Support;
import com.finvendor.model.FinVendorUser;
import com.finvendor.service.MarketDataAggregatorsService;
import com.finvendor.util.CommonUtils;
import com.finvendor.util.RequestConstans;

/**
 * @author rayulu vemula
 *
 */
@Controller
public class TradingApplicationVendorController {

	private static Logger logger = LoggerFactory.getLogger(TradingApplicationVendorController.class);
	
	
	@Autowired
	private MarketDataAggregatorsService marketDataAggregatorsService;
	
	/**
	 * method for trading application page navigation
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	
	@RequestMapping(value=RequestConstans.TradingApplication.TRADING_APPLICATION_INDEX_PAGE, method=RequestMethod.GET)
	public ModelAndView tradingApplicationIndex(HttpServletRequest request,@ModelAttribute("users") FinVendorUser users,
			@RequestParam(value = "RaYUnA", required = false) String username){
			logger.debug("Entering TradingApplicationVendor : tradingApplicationIndex");
			List<AssetClass> assetClasses = null;
			List<Region> regions = null;
			List<Country> countries = null;
			List<Exchange> exchanges = null;
			List<Support> supports = null;
			List<Cost> costs = null;
			List<Awards> awards = null;
			ModelAndView modelAndView=new ModelAndView(RequestConstans.TradingApplication.TRADING_APPLICATION_INDEX_PAGE);
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
			}catch (Exception e) {
				logger.error("Error TradingApplicationVendor : tradingApplicationIndex", e);
				modelAndView.addObject("errorMessage", "Error reading TradingApplicationVendor details : " + e.getMessage());
			}
			logger.debug("Leaving TradingApplicationVendor : tradingApplicationIndex");
			return modelAndView;
	}
	
}
