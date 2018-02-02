package com.finvendor.server.researchreport.service;

import java.util.List;

import com.finvendor.server.researchreport.dto.filter.EquityResearchFilter;
import com.finvendor.server.researchreport.dto.result.ResearchReportResultBase;

/**
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
public interface ResearchReportService {

	 List<? extends ResearchReportResultBase> getResearchReport(EquityResearchFilter rrfilter);
}
