package com.finvendor.server.researchreport.service.ifc;

import java.util.Map;

import com.finvendor.server.researchreport.dto.filter.ifc.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.result.ifc.AbsResearchReportResult;

/**
 * @author ayush on Feb 18, 2018
 */
public interface IResearchReportTableDataService {
	<T extends ResearchReportFilter> Map<String,? extends AbsResearchReportResult> getResearchReportTableData(T rrfilter) throws Exception;
}
