package com.finvendor.serverwebapi.resources.ifc.admin;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.ifc.common.WebUriConstants;

@RequestMapping(WebUriConstants.BASE_URI)
public interface WebSystemAdminIfc {
	
	/**
	 * 
	 * @param type
	 * @return
	 * @throws WebApiException
	 */
	@RequestMapping(value = WebUriConstants.FinvendorAdmin.PER_PAGE_MAX_RECORD_COUNT, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	String getPerPageMaxRecordCount() throws WebApiException;
}
