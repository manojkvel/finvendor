package com.finvendor.server.researchreport.dao;

import com.finvendor.server.researchreport.dto.filter.ResearchReportFilter;

public interface IResearchReportStatisticsDao {
	String getRecordStatistics(String query, ResearchReportFilter filter, String perPageMaxRecords) throws RuntimeException;
}
