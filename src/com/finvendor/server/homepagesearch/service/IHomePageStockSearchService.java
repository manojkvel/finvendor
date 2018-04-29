package com.finvendor.server.homepagesearch.service;

import java.util.List;

import com.finvendor.server.homepagesearch.dto.staticpojo.CompanyWatchListPojo;
import com.finvendor.server.researchreport.dto.filter.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.filter.impl.EquityResearchFilter;

public interface IHomePageStockSearchService {

	/**
	 * 
	 * @param initialHint
	 * @return
	 * @throws Exception
	 */
	String getHomePageSearchData(String initialHint, final EquityResearchFilter filter) throws Exception;

	/**
	 * 
	 * @param isinCode
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	String getCompanyProfileData(String isinCode, EquityResearchFilter filter) throws Exception;
	
	int addCompanyWatchList(CompanyWatchListPojo companyWatchListPojo) throws Exception;
	List<CompanyWatchListPojo> findAllCompanyWatchListByUser(String userName) throws Exception;

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
