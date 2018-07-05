package com.finvendor.serverwebapi.resources.metrics;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.WebUriConstants;

@RequestMapping(WebUriConstants.BASE_URI)
public interface IWebMetric {

	@RequestMapping(value = "/metrics/equityresearchreport", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<?> getEquityResearchReportMetrics() throws WebApiException;

	@RequestMapping(value = "/metrics/researchreportdownload", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<?> getEquityResearchReportDownloadMetrics() throws WebApiException;

}
