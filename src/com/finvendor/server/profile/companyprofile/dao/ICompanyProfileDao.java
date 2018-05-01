package com.finvendor.server.profile.companyprofile.dao;

import com.finvendor.server.researchreport.dto.filter.ResearchReportFilter;

/**
 * 
 * @author ayush on May 01, 2018
 */
public interface ICompanyProfileDao {

	/**
	 * 
	 * @param query
	 * @return
	 * @throws RuntimeException
	 */
	String getProfile(String query) throws RuntimeException;

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
	String getReasearchReport(String isinCode, String query, ResearchReportFilter filter, String pageNumber,
			String perPageMaxRecords, String sortBy, String orderBy) throws RuntimeException;
}
