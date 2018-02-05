package com.finvendor.serverwebapi.resources.restapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.finvendor.server.researchreport.dto.result.EquityResearchResult;
import com.finvendor.server.researchreport.service.ifc.IResearchReportService;
import com.finvendor.serverwebapi.resources.exception.RestApiException;
import com.finvendor.serverwebapi.resources.ifc.IResearchReportRestApi;

/**
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
@Controller
// TBD: Later will replace with RestController to avoid @Responsebody annotation
public class ResearchReportRestApi implements IResearchReportRestApi {

	@Autowired
	@Qualifier(value = "equityResearchService")
	private IResearchReportService equityResearchService;

	@Autowired
	@Qualifier(value = "sectorResearchService")
	private IResearchReportService sectorResearchService;

	@Autowired
	@Qualifier(value = "macroResearchService")
	private IResearchReportService macroResearchService;

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<EquityResearchResult>> getResearchResult(String type) throws RestApiException {
		Map<String, List<EquityResearchResult>> result = new HashMap<>();
		try {
			List<EquityResearchResult> researchReport = (List<EquityResearchResult>) equityResearchService.getResearchReport(null);
			result.put(type, researchReport);
			return result;
		} catch (Exception e) {
			throw new RestApiException("getResearchResult has REST API Error, cause: " + e.getMessage(), e);
		}
	}
}
