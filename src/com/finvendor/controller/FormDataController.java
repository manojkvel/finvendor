package com.finvendor.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.finvendor.form.FileDetails;
import com.finvendor.json.bean.ReferenceDataJson;
import com.finvendor.model.SecurityType;
import com.finvendor.service.ReferenceDataService;
import com.finvendor.service.UserService;
import com.finvendor.util.RequestConstans;

@Controller
public class FormDataController {

	private static Logger logger = LoggerFactory.getLogger(
			FormDataController.class);
	
	@Resource(name="referenceDataService")
	private ReferenceDataService referenceDataService;
	
	@Resource(name="userService")
	private UserService userService;
	
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
			@RequestParam(value = "referenceDataType", required = true) String referenceDataType,
			@RequestParam(value = "parentId", required = false) String parentId) {
		
		logger.debug("Entering  - FormDataController : getJsonReferenceData for {}"
				, referenceDataType);
		List<ReferenceDataJson> refData = null;
		try {
			if(request.getSession().getAttribute("loggedInUser") == null){
				request.getRequestDispatcher("/").forward(request, response);
			}			
			refData =  referenceDataService.getJsonReferenceData(referenceDataType, parentId);			
		} catch (Exception exp) {
			logger.error("Error Reading reference data for {}", referenceDataType); 			
		}
		logger.debug("Leaving  - FormDataController : getJsonReferenceData for {}"
				, referenceDataType);
		return refData;
	}
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody String uploadCompanyLogo(MultipartHttpServletRequest request, 
			HttpServletResponse response) {                 
	  	 
	User appUser = (User)SecurityContextHolder.getContext().getAuthentication().
			getPrincipal();
	logger.debug("Entering  - FormDataController : uploadCompanyLogo for {}", 
			appUser.getUsername());
	Iterator<String> itr =  request.getFileNames();	
	MultipartFile mpf = request.getFile(itr.next());
	FileDetails ufile = new FileDetails();
	boolean vendor = false;
	
	try {
		ufile.setLength(mpf.getBytes().length);
		ufile.setBytes(mpf.getBytes());
		ufile.setType(mpf.getContentType());
		ufile.setName(mpf.getOriginalFilename());
		ufile.setBlob( Hibernate.createBlob(mpf.getInputStream())); 
		if (RequestConstans.Roles.ROLE_VENDOR.equals(
					(String)request.getSession().getAttribute("loggedInRole"))) {
			vendor = true;
		}
		userService.updateCompanyLogo(ufile, appUser.getUsername(), vendor);
	} catch (IOException exp) {
		logger.error("Error Uploading Logo file for {} ", appUser.getUsername());
	}	 
		logger.debug("Leaving  - FormDataController : uploadCompanyLogo for {}", 
			appUser.getUsername());
		//2. send it back to the client as <img> that calls get method
		//we are using getTimeInMillis to avoid server cached image
		return "<img src='/getfile/"+Calendar.getInstance().getTimeInMillis()+"'/>";	  	
	}	  
}