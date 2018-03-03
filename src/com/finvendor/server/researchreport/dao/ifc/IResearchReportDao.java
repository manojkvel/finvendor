package com.finvendor.server.researchreport.dao.ifc;

import java.util.List;
import java.util.Map;

import com.finvendor.server.researchreport.dto.filter.ifc.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.result.EquityResearchResult;
import com.finvendor.server.researchreport.dto.result.dashboard.AbsResearchReportDashboardResult;

/**
 * Top level interface
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
public interface IResearchReportDao {
	List<EquityResearchResult> findResearchReportTableData(ResearchReportFilter filter) throws RuntimeException;
	AbsResearchReportDashboardResult findResearchReportDashboardData(String... params) throws RuntimeException;
	ResearchReportFilter getFilterValues();
}
