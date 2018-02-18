package com.finvendor.server.researchreport.dao.ifc;

import java.util.List;

import com.finvendor.server.researchreport.dto.filter.ifc.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.result.dashboard.AbsResearchReportDashboardResult;
import com.finvendor.server.researchreport.dto.result.ifc.AbsResearchReportResult;

/**
 * Top level interface
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
public interface IResearchReportDao {
	List<? extends AbsResearchReportResult> findResearchReportTableData(ResearchReportFilter filter) throws RuntimeException;
	AbsResearchReportDashboardResult findResearchReportDashboardData(String... params) throws RuntimeException;
}
