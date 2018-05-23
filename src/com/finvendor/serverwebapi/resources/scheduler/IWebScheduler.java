package com.finvendor.serverwebapi.resources.scheduler;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPricePojo;
import com.finvendor.server.scheduler.dto.UserCompanyMailContent;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.WebUriConstants;

@RequestMapping(WebUriConstants.BASE_URI)
public interface IWebScheduler {

	/**
	 * @param stockCurrentPricePojo
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = "/updatestockprice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<?> updateStockPrice(StockCurrentPricePojo stockCurrentPricePojo) throws WebApiException;

	/**
	 * 
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = "/sendpricealertmail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<?> sendMail(UserCompanyMailContent userCompaniesMailContent) throws WebApiException;

	/**
	 * 
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = "/sendmailforrsearchreport", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<?> sendMailForResearchReport(String userName, String companyName) throws WebApiException;
}
