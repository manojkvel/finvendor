package com.finvendor.server.researchreport.dao.ifc;

import java.util.Map;

import com.finvendor.server.researchreport.dto.filter.ifc.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.result.EquityResearchResult;
import com.finvendor.server.researchreport.dto.result.ifc.AbsResearchReportResult;

/**
 * Top level interface
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
public interface IResearchReportDao {
	String getRecordStatistics(ResearchReportFilter filter, String perPageMaxRecords) throws RuntimeException;
	Map<String,? extends AbsResearchReportResult> findResearchReportTableData(ResearchReportFilter filter,String offset,String perPageMaxRecords, String sortBy, String orderBy) throws RuntimeException;
}
