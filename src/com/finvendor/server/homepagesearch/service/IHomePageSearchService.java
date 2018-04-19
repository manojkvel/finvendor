package com.finvendor.server.homepagesearch.service;

import com.finvendor.server.researchreport.dto.filter.EquityResearchFilter;
import com.finvendor.server.researchreport.dto.filter.ifc.ResearchReportFilter;

public interface IHomePageSearchService {

	/**
	 * 
	 * @param initialHint
	 * @return
	 * @throws Exception
	 */
	String getHomePageSearchData(String initialHint) throws Exception;

	/**
	 * 
	 * @param isinCode
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	String getCompanyProfileData(String isinCode, EquityResearchFilter filter) throws Exception;

	/**
	 * 
	 * @param isinCode
	 * @param rrfilter
	 * @param perPageMaxRecords
	 * @return
	 */
	String getCompanyRecordStatistics(String isinCode,ResearchReportFilter rrfilter, String perPageMaxRecords);

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
	String getCompanyResearchReportData(String isinCode, ResearchReportFilter filter, String pageNumber,
			String perPageMaxRecords, String sortBy, String orderBy) throws Exception;

}
