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
public class MacroResearchDao extends AbsResearchReportDao {

	@Override
	public Map<String, ? extends AbsResearchReportResult> findResearchReportTableData(ResearchReportFilter filter,String offset)
			throws RuntimeException {
		return null;
	}

	@Override
	public String getRecordStatistics(ResearchReportFilter filter) throws RuntimeException {
		return null;
	}


}
