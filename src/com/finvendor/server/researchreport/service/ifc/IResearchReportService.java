package com.finvendor.server.researchreport.service.ifc;

import java.util.List;

import com.finvendor.server.researchreport.dto.filter.EquityResearchFilter;
import com.finvendor.server.researchreport.dto.result.ifc.AbsResearchReportResult;

/**
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
public interface IResearchReportService {

	 List<? extends AbsResearchReportResult> getResearchReport(EquityResearchFilter rrfilter) throws Exception;
}
