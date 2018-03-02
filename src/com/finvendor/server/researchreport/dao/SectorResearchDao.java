package com.finvendor.server.researchreport.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.finvendor.server.researchreport.dao.ifc.AbsResearchReportDao;
import com.finvendor.server.researchreport.dto.filter.ifc.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.result.EquityResearchResult;
import com.finvendor.server.researchreport.dto.result.dashboard.AbsResearchReportDashboardResult;

/**
 * 
 * @author ayush
 * @since 04-Feb-2018
 */
@Repository
public class SectorResearchDao extends AbsResearchReportDao {


	@Override
	public AbsResearchReportDashboardResult findResearchReportDashboardData(String... params) throws RuntimeException {
		return null;
	}

	@Override
	public ResearchReportFilter getFilterValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, EquityResearchResult> findResearchReportTableData(ResearchReportFilter filter)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}
}
