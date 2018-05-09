package com.finvendor.serverwebapi.resources.alert.rsrcharea.stock;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyPriceAlertPojo;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.WebUriConstants;

/**
 * 
 * @author ayush on April 30, 2018
 */
@RequestMapping(WebUriConstants.BASE_URI)
public interface IWebRsrchAreaStockPriceAlert {
	
	@RequestMapping(value = "/alert/researcharea", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<?> addPriceAlert(CompanyPriceAlertPojo companyPriceAlertPojo, String id) throws WebApiException;
	
	@RequestMapping(value = "/alert/researcharea", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<?> findAllRsrchAreaStockPriceAlerts(HttpServletRequest request, String id) throws WebApiException;
}
