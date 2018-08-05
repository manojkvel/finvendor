package com.finvendor.serverwebapi.resources.companyprofile.pricealert;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finvendor.modelpojo.staticpojo.wathlist.company.ConsumerPriceAlertDTO;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.WebUriConstants;

/**
 * 
 * @author ayush on April 30, 2018
 */
@RequestMapping(WebUriConstants.BASE_URI)
public interface IWebConsumerPriceAlert {

	// Create
	/**
	 * 
	 * @param companyPriceAlertPojo
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = "/companypricealert/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<?> addCompanyPriceAlert(HttpServletRequest request, ConsumerPriceAlertDTO companyPriceAlertPojo)
			throws WebApiException;

	// Update
	/**
	 * 
	 * @param companyPriceAlertPojo
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = "/companypricealert/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<?> updateCompanyPriceAlert(HttpServletRequest request, ConsumerPriceAlertDTO companyPriceAlertPojo)
			throws WebApiException;

	// Delete
	/**
	 * 
	 * @param pojoList
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = "/companypricealert/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<?> deleteCompanyPriceAlert(HttpServletRequest request, List<ConsumerPriceAlertDTO> pojoList)
			throws WebApiException;

	// Find
	/**
	 * 
	 * @param request
	 * @param companyId
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = "/companypricealert", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<?> findCompanyPriceAlert(HttpServletRequest request, String companyId) throws WebApiException;

	// Find All
	/**
	 * 
	 * @param request
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = "/companypricealert/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<?> findAllCompanyPriceAlert(HttpServletRequest request) throws WebApiException;
}
