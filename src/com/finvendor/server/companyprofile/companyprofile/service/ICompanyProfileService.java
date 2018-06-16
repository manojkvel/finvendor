package com.finvendor.server.companyprofile.companyprofile.service;

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
	String getCompanyProfile(String isinCode) throws Exception;

	/**
	 * 
	 * @param isinCode
	 * @param rrfilter
	 * @param perPageMaxRecords
	 * @return
	 */
	String getCompanyProfileRecordStat(String isinCode, String perPageMaxRecords) throws Exception;

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
	String getCompanyProfileResearchReport(String isinCode, String pageNumber, String perPageMaxRecords, String sortBy,
			String orderBy) throws Exception;
}
