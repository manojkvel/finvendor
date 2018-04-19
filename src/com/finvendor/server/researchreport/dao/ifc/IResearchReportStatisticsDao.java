package com.finvendor.server.researchreport.dao.ifc;

import com.finvendor.server.researchreport.dto.filter.ifc.ResearchReportFilter;

public interface IResearchReportStatisticsDao {
	String getRecordStatistics(String query, ResearchReportFilter filter, String perPageMaxRecords) throws RuntimeException;
}
