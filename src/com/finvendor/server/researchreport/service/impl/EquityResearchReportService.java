package com.finvendor.server.researchreport.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.server.researchreport.dao.IResearchReportDao;
import com.finvendor.server.researchreport.dto.filter.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.filter.impl.EquityResearchFilter;
import com.finvendor.server.researchreport.dto.result.AbsResearchReportResult;
import com.finvendor.server.researchreport.service.AbsResearchReportService;
import com.finvendor.server.researchreport.util.ResearchReportUtil;

/**
 * @author ayush on Feb 18, 2018
 */
@Service
public class EquityResearchReportService extends AbsResearchReportService {

	@Autowired
	@Qualifier(value = "equityResearchDaoImpl")
	IResearchReportDao equityReserachReportDao;

	@Override
	@Transactional(readOnly = true)
	public <T extends ResearchReportFilter> Map<String, ? extends AbsResearchReportResult> getResearchReportTableData(
			T rrfilter, String pageNumber, String perPageMaxRecords, String sortBy, String orderBy) throws Exception {
		try {
			String mainQuery = ResearchReportUtil.mainQuery.replace("?",
					"'" + ((EquityResearchFilter) rrfilter).getGeo() + "'");
			return (Map<String, ? extends AbsResearchReportResult>) equityReserachReportDao
					.findResearchReportTableData(mainQuery, rrfilter, pageNumber, perPageMaxRecords, sortBy, orderBy);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public <T extends ResearchReportFilter> String getRecordStatistics(T rrfilter, String perPageMaxRecords)
			throws Exception {
		try {
			String mainQuery = ResearchReportUtil.mainQuery.replace("?",
					"'" + ((EquityResearchFilter) rrfilter).getGeo() + "'");
			return equityReserachReportDao.getRecordStatistics(mainQuery, rrfilter, perPageMaxRecords);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}
}
