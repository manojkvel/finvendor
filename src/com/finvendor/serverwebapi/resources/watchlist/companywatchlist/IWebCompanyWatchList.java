package com.finvendor.serverwebapi.resources.watchlist.companywatchlist;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finvendor.modelpojo.staticpojo.StatusPojo;
import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyWatchListPojo;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.WebUriConstants;

/**
 * 
 * @author ayush on April 30, 2018
 */
@RequestMapping(WebUriConstants.BASE_URI)
public interface IWebCompanyWatchList {

	/**
	 * 
	 * @param companyWatchList
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = "/companywatchlist", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	StatusPojo addCompanyWatchList(CompanyWatchListPojo companyWatchListPojo) throws WebApiException;

	/**
	 * 
	 * @param userName
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = "/companywatchlists", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	List<CompanyWatchListPojo> findAllCompanyWatchlist(String userName) throws WebApiException;

	/**
	 * 
	 * @param userName
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = "/deletecompanywatchlist", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	List<CompanyWatchListPojo> deleteCompanyWatchlist(String userName) throws WebApiException;

}
