package com.finvendor.server.researchreport.dao;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.finvendor.server.researchreport.dto.filter.ResearchReportFilter;

/**
 * ResearchReportDao abstract interface
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
public abstract class AbsResearchReportDao implements IResearchReportDao{
	protected static Logger logger = LoggerFactory.getLogger(EquityResearchDao.class);
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	public AbsResearchReportDao() {
	}
	
	protected String applyFilter(String query,ResearchReportFilter filter) {
		return query;
	}
}
