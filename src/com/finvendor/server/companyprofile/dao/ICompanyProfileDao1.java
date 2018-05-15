package com.finvendor.server.companyprofile.dao;

import com.finvendor.server.researchreport.dto.filter.ResearchReportFilter;

/**
 * 
 * @author ayush on May 01, 2018
 */
public interface ICompanyProfileDao1 {

	/**
	 * 
	 * @param query
	 * @return
	 * @throws RuntimeException
	 */
	String getCompanyProfile(String query) throws RuntimeException;

	/**
	 * 
	 * @param isinCode
	 * @param query
	 * @param filter
	 * @param pageNumber
	 * @param perPageMaxRecords
	 * @param sortBy
	 * @param orderBy
	 * @return
	 * @throws RuntimeException
	 */
	String getCompanyProfileReasearchReport(String isinCode, String query, ResearchReportFilter filter, String pageNumber,
			String perPageMaxRecords, String sortBy, String orderBy) throws RuntimeException;
}
