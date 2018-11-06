//package com.finvendor.serverwebapi.resources.companyprofile.pricealert;
//
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.finvendor.modelpojo.staticpojo.StatusPojo;
//import com.finvendor.serverwebapi.exception.WebApiException;
//import com.finvendor.serverwebapi.resources.WebUriConstants;
//
//@RequestMapping(WebUriConstants.BASE_URI)
//public interface IWebConsumerPriceAlertMail {
//
//	/**
//	 * Price Alert Mail
//	 *
//	 * @return
//	 * @throws WebApiException
//	 */
//	@RequestMapping(value = "/sendpricealertmail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	ResponseEntity<?> sendPriceAlertMail(StatusPojo statusPojo) throws WebApiException;
//
//	/**
//	 * Research Report Alert Mail
//	 *
//	 * @return
//	 * @throws WebApiException
//	 */
//	@RequestMapping(value = "/sendrsearchreportmail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	ResponseEntity<?> sendResearchReportAlertMail(String userName, String companyId, String companyName) throws WebApiException;
//}
