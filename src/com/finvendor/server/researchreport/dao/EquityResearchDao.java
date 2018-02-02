package com.finvendor.server.researchreport.dao;

import java.util.List;

import org.hibernate.SQLQuery;

import com.finvendor.server.researchreport.dto.filter.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.result.EquityResearchResult;

/**
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
public class EquityResearchDao extends AbsResearchReportDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<EquityResearchResult> findResearchResult(ResearchReportFilter filter) {
		applyFilter(EQUITY_RESEARCH_SQL_QUERY,filter);

		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(EQUITY_RESEARCH_SQL_QUERY);
		List<EquityResearchResult> results = (List<EquityResearchResult>) query.list();
		return results;
	}
}
