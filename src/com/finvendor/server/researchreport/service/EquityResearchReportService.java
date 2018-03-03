package com.finvendor.server.researchreport.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.server.researchreport.dao.ifc.IResearchReportDao;
import com.finvendor.server.researchreport.dto.filter.ifc.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.result.EquityResearchResult;
import com.finvendor.server.researchreport.dto.result.dashboard.AbsResearchReportDashboardResult;
import com.finvendor.server.researchreport.dto.result.ifc.AbsResearchReportResult;
import com.finvendor.server.researchreport.service.ifc.AbsResearchReportService;

/**
 * @author ayush on Feb 18, 2018
 */
@Service
public class EquityResearchReportService extends AbsResearchReportService {

	@Autowired
	@Qualifier(value = "equityResearchDao")
	IResearchReportDao equityReserachReportDao;

	@Override
	@Transactional(readOnly = true)
	public <T extends ResearchReportFilter> List<? extends AbsResearchReportResult>  getResearchReportTableData(T rrfilter) throws Exception {
		try {
			return (List<EquityResearchResult>) equityReserachReportDao.findResearchReportTableData(rrfilter);
		} catch (RuntimeException e) {// TODO need to replace with custom Exception
			throw new Exception(e);
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public AbsResearchReportDashboardResult getResearchReportDashboardData(String... params) throws Exception {
		return equityReserachReportDao.findResearchReportDashboardData(params);
	}

	
}
