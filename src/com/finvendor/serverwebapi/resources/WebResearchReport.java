package com.finvendor.serverwebapi.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.finvendor.common.util.ExceptionUtil;
import com.finvendor.server.researchreport.dto.result.EquityResearchResult;
import com.finvendor.server.researchreport.service.ifc.IResearchReportService;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.ifc.WebResearchReportIfc;

/**
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
@Controller
// TBD: Later will replace with RestController to avoid @Responsebody annotation
public class WebResearchReport implements WebResearchReportIfc {

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
	public Map<String, List<EquityResearchResult>> getResearchResult(String type) throws WebApiException {
		Map<String, List<EquityResearchResult>> result = new HashMap<>();
		try {
			List<EquityResearchResult> researchReport = (List<EquityResearchResult>) equityResearchService.getResearchReport(null);
			result.put(type, researchReport);
			return result;
		} catch (Exception e) {
			//TODO need to refactor
			String generalError="Error has occured in Finvendor Web API .../system/api/researchReports?type=equity";
			String technicalError="Technical has occred in WebResearchReport-> getResearchResult() :: Error Cause:"+ExceptionUtil.getRootCauseMessage(e);
			StringBuffer sb=new StringBuffer();
			sb.append("User Error:"+generalError).append("\n").append("Developer Error:").append(technicalError).append("\n")
			.append("Please contact Finvendor support team!!");
			throw new WebApiException(sb.toString());
		}
	}
}
