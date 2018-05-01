package com.finvendor.serverwebapi.resources.watchlist.companywatchlist.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.finvendor.common.util.ExceptionUtil;
import com.finvendor.modelpojo.staticpojo.StatusPojo;
import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyWatchListPojo;
import com.finvendor.server.watchlist.companywatchlist.service.ICompanyWatchListService;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.watchlist.companywatchlist.IWebCompanyWatchList;

/**
 * 
 * @author ayush on April 30, 2018
 */
@Controller
public class WebCompanyWatchListImpl implements IWebCompanyWatchList {
	private static Logger logger = LoggerFactory.getLogger(WebCompanyWatchListImpl.class);

	@Autowired
	ICompanyWatchListService comapnyWatchListService;

	@Override
	public StatusPojo addCompanyWatchList(@RequestBody CompanyWatchListPojo companyWatchListPojo)
			throws WebApiException {
		try {
			boolean addStatus = comapnyWatchListService.addWatchList(companyWatchListPojo);
			if (addStatus) {
				return new StatusPojo("true", "Company Watchlist added successfully.");
			} else {
				return new StatusPojo("false", "Company Watchlist already added.");
			}
		} catch (Exception e) {
			String apiErrorMessage = ExceptionUtil
					.buildErrorMessage("Error has occurred in WebHomePageSearch -> addCompanyWatchList(...) method", e);
			logger.error("Web API Error: " + apiErrorMessage);
			throw new WebApiException(apiErrorMessage);
		}
	}

	@Override
	public List<CompanyWatchListPojo> deleteCompanyWatchlist(String userName) throws WebApiException {
		return null;
	}

	@Override
	public List<CompanyWatchListPojo> findAllCompanyWatchlist(
			@RequestParam(value = "userName", required = true) String userName) throws WebApiException {
		try {
			return comapnyWatchListService.findAllWatchList(userName);
		} catch (Exception e) {
			String apiErrorMessage = ExceptionUtil.buildErrorMessage(
					"Error has occurred in WebHomePageSearch -> findAllcompanywatchlist(...) method", e);
			logger.error("Web API Error: " + apiErrorMessage);
			throw new WebApiException(apiErrorMessage);
		}
	}
}
