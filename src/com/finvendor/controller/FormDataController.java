package com.finvendor.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.finvendor.model.SecurityType;
import com.finvendor.service.ReferenceDataService;

@Controller
public class FormDataController {

	private static Logger logger = LoggerFactory.getLogger(
			FormDataController.class);
	
	@Resource(name="referenceDataService")
	private ReferenceDataService referenceDataService;
	
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
}
