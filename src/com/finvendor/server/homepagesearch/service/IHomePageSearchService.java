package com.finvendor.server.homepagesearch.service;

import com.finvendor.server.researchreport.dto.filter.EquityResearchFilter;
import com.finvendor.server.researchreport.dto.filter.ifc.ResearchReportFilter;

public interface IHomePageSearchService {

	String getCompanyData(String initialHint) throws Exception;

	String getCompanyProfileData(String isinCode, EquityResearchFilter filter) throws Exception;

	public String getCompanyRecordStatistics(String isinCode,ResearchReportFilter rrfilter, String perPageMaxRecords);

	String getCompanyResearchReportData(String isinCode, ResearchReportFilter filter, String pageNumber,
			String perPageMaxRecords, String sortBy, String orderBy) throws Exception;

}
