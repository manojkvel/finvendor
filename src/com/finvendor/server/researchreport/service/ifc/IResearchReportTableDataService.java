package com.finvendor.server.researchreport.service.ifc;

import java.util.List;

import com.finvendor.server.researchreport.dto.filter.ifc.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.result.ifc.AbsResearchReportResult;

/**
 * @author ayush on Feb 18, 2018
 */
public interface IResearchReportTableDataService {
	List<? extends AbsResearchReportResult> getResearchReportTableData(ResearchReportFilter rrfilter) throws Exception;
}
