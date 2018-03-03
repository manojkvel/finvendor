package com.finvendor.server.researchreport.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.finvendor.server.researchreport.dao.ifc.IResearchReportDao;
import com.finvendor.server.researchreport.dto.filter.ifc.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.result.dashboard.AbsResearchReportDashboardResult;
import com.finvendor.server.researchreport.dto.result.ifc.AbsResearchReportResult;
import com.finvendor.server.researchreport.service.ifc.AbsResearchReportService;

/**
 * 
 * @author ayush
 * @since 04-Feb-2018
 */
@Service
public class MacroResearchReportService extends AbsResearchReportService {

	@Autowired
	@Qualifier(value="macroResearchDao")
	IResearchReportDao macroReserachReportDao;


	@Override
	public AbsResearchReportDashboardResult getResearchReportDashboardData(String...params) throws Exception {
		return null;
	}


	@Override
	public <T extends ResearchReportFilter> List<? extends AbsResearchReportResult> getResearchReportTableData(
			T rrfilter) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
