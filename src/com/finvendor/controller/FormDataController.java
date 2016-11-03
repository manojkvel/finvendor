package com.finvendor.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finvendor.json.bean.ReferenceDataJson;
import com.finvendor.json.bean.VendorDataAggregatorsOfferingJson;
import com.finvendor.model.SecurityType;
import com.finvendor.model.VendorDataAggregatorsOffering;
import com.finvendor.service.ReferenceDataService;

@Controller
public class FormDataController {

	private static Logger logger = LoggerFactory.getLogger(
			FormDataController.class);
	
	@Resource(name="referenceDataService")
	private ReferenceDataService referenceDataService;
	
	/*
	@RequestMapping(value="loadSecurityTypesForAssetClass", method = RequestMethod.POST)
	public String loadSecurityTypesForAssetClass(
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "assetClassId", required = true) int assetClassId) {
		logger.debug("Entering - FormDataController : loadSecurityTypesForAssetClass for assetClass Id {}", 
				assetClassId);
		List<SecurityType> securityTypeList = null;
		StringBuilder securityTypeFormElement = null;
		try {
			securityTypeList = referenceDataService.getSecurityTypesForAssetClassId(assetClassId);
			securityTypeFormElement = new StringBuilder();
			for(SecurityType secType : securityTypeList) {
				securityTypeFormElement.append("<option value='");
				securityTypeFormElement.append(secType.getSecurityTypeId());
				securityTypeFormElement.append("'>");
				securityTypeFormElement.append(secType.getName());
				securityTypeFormElement.append("</option>");
			}
			response.getWriter().print(securityTypeFormElement.toString());
		} catch (Exception exp) {
			logger.error("Error loading Security Types for assetClass Id {}",
					assetClassId, exp);
			return null;
			
		}
		logger.debug("Leaving - FormDataController : loadSecurityTypesForAssetClass for assetClass Id {}", 
				assetClassId);
		return null;
	}
	*/
	
	@RequestMapping(value="loadFormReferenceDataForSelect", method = RequestMethod.POST)
	public String loadFormReferenceDataForSelect(
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "selectedElement", required = true) int selectedElement,
			@RequestParam(value = "selectedValue", required = false) String selectedValue,
			@RequestParam(value = "methodName", required = true) String methodName) {
		logger.info("Entering - FormDataController : loadFormReferenceDataForSelect for method {}", 
				methodName);
		StringBuilder refrerenceDataOptions = new StringBuilder(50);
		try{
			switch(methodName) {
				case "loadSecurityTypesForAssetClass" :
					List<SecurityType> securityTypeList = null;
					securityTypeList = referenceDataService.
							getSecurityTypesForAssetClassId(selectedElement);
					for(SecurityType secType : securityTypeList) {
						refrerenceDataOptions.append("<option value='");
						refrerenceDataOptions.append(secType.getSecurityTypeId());
						refrerenceDataOptions.append("'>");
						refrerenceDataOptions.append(secType.getName());
						refrerenceDataOptions.append("</option>");
					}
					break;
				default :
					logger.error("Wrong methodName in loadFormReferenceDataForSelect : {}", 
							methodName);
			}
			response.getWriter().print(refrerenceDataOptions.toString());
		}catch (Exception exp) {
			logger.error("Error loading Reference data for {}",
					methodName, exp);
			return null;
			
		}		
		logger.debug("Leaving - FormDataController : loadFormReferenceDataForSelect for method {}", 
				methodName);
		return null;
	}
	
	@RequestMapping(value="getJsonReferenceData", method = RequestMethod.GET)
	public @ResponseBody List<ReferenceDataJson> getJsonReferenceData(
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "referenceDataType", required = true) String referenceDataType) {
		
		logger.debug("Entering  - FormDataController : getJsonReferenceData for {}"
				, referenceDataType);
		List<ReferenceDataJson> refData = null;
		try {
			if(request.getSession().getAttribute("loggedInUser") == null){
				request.getRequestDispatcher("/").forward(request, response);
			}			
			refData =  referenceDataService.getJsonReferenceData(referenceDataType);			
		} catch (Exception exp) {
			logger.error("Error Reading reference data for {}", referenceDataType); 			
		}
		logger.debug("Leaving  - FormDataController : getJsonReferenceData for {}"
				, referenceDataType);
		return refData;
	}
}
