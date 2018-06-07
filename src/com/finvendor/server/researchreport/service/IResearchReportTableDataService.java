package com.finvendor.server.researchreport.service;

import java.util.Map;

import com.finvendor.server.researchreport.dto.filter.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.result.AbsResearchReportResult;


/**
 * @author ayush on Feb 18, 2018
 */
public interface IResearchReportTableDataService {
	<T extends ResearchReportFilter> Map<String, ? extends AbsResearchReportResult> getResearchReportTableData(
			T rrfilter, String pageNumber, String perPageMaxRecords, String sortBy, String orderBy) throws Exception;

	<T extends ResearchReportFilter> String getRecordStatistics(T rrfilter, String perPageMaxRecords) throws Exception;
}
