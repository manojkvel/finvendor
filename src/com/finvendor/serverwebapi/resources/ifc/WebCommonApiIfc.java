package com.finvendor.serverwebapi.resources.ifc;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finvendor.modelpojo.staticpojo.VoVendorDetails;
import com.finvendor.serverwebapi.exception.WebApiException;

/**
 * @author ayush on Feb 17, 2018
 */
@RequestMapping(WebUriConstants.BASE_URI)
public interface WebCommonApiIfc {
	
	@RequestMapping(value = WebUriConstants.VENDOR_DETAILS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<VoVendorDetails> getVoVendorDetails() throws WebApiException;
}
