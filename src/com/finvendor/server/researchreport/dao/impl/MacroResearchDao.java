package com.finvendor.server.researchreport.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.finvendor.server.researchreport.dao.IResearchReportDao;
import com.finvendor.server.researchreport.dto.filter.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.result.AbsResearchReportResult;

/**
 * 
 * @author ayush
 * @since 04-Feb-2018
 */
@Repository
public class MacroResearchDao implements IResearchReportDao {

	@Override
	public Map<String, ? extends AbsResearchReportResult> findResearchReportTableData(String query,ResearchReportFilter filter,
			String offset, String perPageMaxRecords, String sortBy, String orderBy) throws RuntimeException {
		return null;
	}

	@Override
	public String getRecordStatistics(String query,ResearchReportFilter filter, String perPageMaxRecords) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

}
