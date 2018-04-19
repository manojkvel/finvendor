package com.finvendor.server.researchreport.dao.ifc;

import java.util.Map;

import com.finvendor.server.researchreport.dto.filter.ifc.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.result.ifc.AbsResearchReportResult;

public interface IReseaechReportTableDao {
	Map<String,? extends AbsResearchReportResult> findResearchReportTableData(String query, ResearchReportFilter filter,String offset,String perPageMaxRecords, String sortBy, String orderBy) throws RuntimeException;
}