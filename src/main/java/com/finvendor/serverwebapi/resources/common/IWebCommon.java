//package com.finvendor.serverwebapi.resources.common;
//
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.finvendor.serverwebapi.exception.WebApiException;
//import com.finvendor.serverwebapi.resources.WebUriConstants;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author ayush on Feb 17, 2018
// */
//@RequestMapping(WebUriConstants.BASE_URI)
//public interface IWebCommon {
//
//	@RequestMapping(value ="/researchreportfor", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	ResponseEntity<?> getResearchReportFor(@RequestParam("researchAreaId") String researchAreaId) throws WebApiException;
//
//	/**
//	 *
//	 * @return
//	 * @throws WebApiException
//	 */
//	@RequestMapping(value = WebUriConstants.FILTER_DATA_URI, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	ResponseEntity<?> getResearchFilterData(@RequestParam("type") String  type) throws WebApiException;
//
//	@RequestMapping(value = "/analystTypes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	ResponseEntity<?> getAnalystType() throws WebApiException;
//
//	@RequestMapping(value = "/analystTypes/{analystType}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	ResponseEntity<?> updateAnalystyType(HttpServletRequest request, @PathVariable("analystType") String analystType) throws WebApiException;
//}
