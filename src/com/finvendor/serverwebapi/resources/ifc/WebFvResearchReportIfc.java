package com.finvendor.serverwebapi.resources.ifc;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finvendor.server.researchreport.dto.result.EquityResearchResult;
import com.finvendor.serverwebapi.exception.WebApiException;

/**
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
@RequestMapping(WebUriConstants.BASE_URI)
public interface WebFvResearchReportIfc {

	@RequestMapping(value = WebUriConstants.ResearchReport.RESEARCH_REPORT_EQUITY, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String,List<EquityResearchResult>> getResearchResult(@RequestParam("type") String type) throws WebApiException;
}
