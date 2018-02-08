package com.finvendor.serverwebapi.resources.ifc;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finvendor.bean.CompanyDetails;
import com.finvendor.serverwebapi.exception.WebApiException;

/**
 * 
 * @author ayush
 *
 */
@RequestMapping(WebUriConstants.BASE_URI)
public interface WebFvAdminDashboardIfc {
	@RequestMapping(value = WebUriConstants.AdminDashBoard.COMPANY_DETAILS_URI, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	List<CompanyDetails> getResearchResult(@RequestParam("researchAreaId") String researchAreaId) throws WebApiException;
}
