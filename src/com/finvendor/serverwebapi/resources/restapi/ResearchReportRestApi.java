package com.finvendor.serverwebapi.resources.restapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.finvendor.server.researchreport.dto.result.EquityResearchResult;
import com.finvendor.server.researchreport.service.ifc.IResearchReportService;
import com.finvendor.serverwebapi.resources.ifc.IResearchReportRestApi;

/**
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
@Controller
//TBD: Later will replace with RestController to avoid @Responsebody annotation
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
	
	
	@Override
	public Map<String, List<EquityResearchResult>> getResearchResult(String type) {
		
		Map<String, List<EquityResearchResult>> result = new HashMap<>();
		
		@SuppressWarnings("unchecked")
		List<EquityResearchResult> researchReport = (List<EquityResearchResult>) equityResearchService.getResearchReport(null);
		result.put("equity", researchReport);
		
		return result;
	}

}
