package com.finvendor.serverwebapi.resources.watchlist.rsrcharea.stock;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyWatchListPojo;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.WebUriConstants;

/**
 * 
 * @author ayush on April 30, 2018
 */
@RequestMapping(WebUriConstants.BASE_URI)
public interface IWebRsrchStockWatchList {

	/**
	 * 
	 * @param companyWatchList
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = "/watchlist/researcharea", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<?> addCompanyWatchList(CompanyWatchListPojo companyWatchListPojo, String id) throws WebApiException;

	/**
	 * 
	 * @param userName
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = "/watchlists/researcharea", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<?> findAllCompanyWatchlist(HttpServletRequest request, String id) throws WebApiException;

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
