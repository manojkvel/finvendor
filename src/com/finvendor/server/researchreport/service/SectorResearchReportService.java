package com.finvendor.server.researchreport.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.finvendor.server.researchreport.dao.ifc.IResearchReportDao;
import com.finvendor.server.researchreport.dto.filter.ifc.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.result.ifc.AbsResearchReportResult;
import com.finvendor.server.researchreport.service.ifc.AbsResearchReportService;

/**
 * @author ayush on Feb 18, 2018
 */
@Service
public class SectorResearchReportService extends AbsResearchReportService {

	@Autowired
	@Qualifier(value = "sectorResearchDao")
	IResearchReportDao sectorReserachReportDao;

	@Override
	public <T extends ResearchReportFilter> Map<String, ? extends AbsResearchReportResult> getResearchReportTableData(
			T rrfilter, String pageNumber, String perPageMaxRecords, String sortBy, String orderBy) throws Exception {
		return null;
	}

	@Override
	public <T extends ResearchReportFilter> String getRecordStatistics(T rrfilter, String perPageMaxRecords) {
		return null;
	}

}
