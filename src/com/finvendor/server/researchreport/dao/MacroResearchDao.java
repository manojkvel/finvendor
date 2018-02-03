package com.finvendor.server.researchreport.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.finvendor.server.researchreport.dao.ifc.AbsResearchReportDao;
import com.finvendor.server.researchreport.dto.filter.ifc.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.result.EquityResearchResult;

/**
 * 
 * @author ayush
 * @since 04-Feb-2018
 */
@Repository
public class MacroResearchDao extends AbsResearchReportDao {

	@Override
	public List<EquityResearchResult> findResearchResult(ResearchReportFilter filter) {
		return null;
	}
}
