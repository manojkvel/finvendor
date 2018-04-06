package com.finvendor.server.researchreport.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.finvendor.server.researchreport.dao.ifc.AbsResearchReportDao;
import com.finvendor.server.researchreport.dto.filter.ifc.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.result.ifc.AbsResearchReportResult;

/**
 * 
 * @author ayush
 * @since 04-Feb-2018
 */
@Repository
public class SectorResearchDao extends AbsResearchReportDao {

	@Override
	public Map<String, ? extends AbsResearchReportResult> findResearchReportTableData(ResearchReportFilter filter,
			String offset, String perPageMaxRecords, String sortBy, String orderBy) throws RuntimeException {
		return null;
	}

	@Override
	public String getRecordStatistics(ResearchReportFilter filter, String perPageMaxRecords) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

}
