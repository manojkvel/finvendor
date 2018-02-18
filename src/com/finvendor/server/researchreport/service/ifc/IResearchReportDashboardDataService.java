package com.finvendor.server.researchreport.service.ifc;

import com.finvendor.server.researchreport.dto.result.dashboard.AbsResearchReportDashboardResult;

public interface IResearchReportDashboardDataService {
	AbsResearchReportDashboardResult getResearchReportDashboardData(String...params) throws Exception;
}
