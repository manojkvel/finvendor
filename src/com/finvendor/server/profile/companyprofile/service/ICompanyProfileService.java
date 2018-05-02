package com.finvendor.server.profile.companyprofile.service;

import com.finvendor.server.researchreport.dto.filter.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.filter.impl.EquityResearchFilter;

/**
 * 
 * @author ayush on May 01, 2018
 */
public interface ICompanyProfileService {

	/**
	 * 
	 * @param isinCode
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	String getProfile(String isinCode, EquityResearchFilter filter) throws Exception;

	/**
	 * 
	 * @param isinCode
	 * @param rrfilter
	 * @param perPageMaxRecords
	 * @return
	 */
	String getResearchReportRecordStatistics(String isinCode, ResearchReportFilter rrfilter, String perPageMaxRecords)
			throws Exception;

	/**
	 * 
	 * @param isinCode
	 * @param filter
	 * @param pageNumber
	 * @param perPageMaxRecords
	 * @param sortBy
	 * @param orderBy
	 * @return
	 * @throws Exception
	 */
	String getResearchReport(String isinCode, ResearchReportFilter filter, String pageNumber, String perPageMaxRecords,
			String sortBy, String orderBy) throws Exception;
}
