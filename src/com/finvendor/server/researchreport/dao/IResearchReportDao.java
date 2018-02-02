package com.finvendor.server.researchreport.dao;

import java.util.List;

import com.finvendor.server.researchreport.dto.filter.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.result.ResearchReportResultBase;

/**
 * Top level interface
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
public interface IResearchReportDao {

	String EQUITY_RESEARCH_SQL_QUERY="";
	List<? extends ResearchReportResultBase> findResearchResult(ResearchReportFilter filter);
}
