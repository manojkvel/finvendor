package com.finvendor.serverwebapi.resources.ifc.common;

public interface WebUriConstants {

	String BASE_URI = "/system/api";
	String VENDORS = "/vendors";
	String VENDOR_DETAILS = "/vendorDetails";
	String FILTER_DATA_URI = "/filterdata";
	
	
	interface ResearchReport {
		String RESEARCH_REPORTS = "/researchReports";
		String DASHBOARD_RESEARCH_REPORTS = "/dashboardResearchReports";
		String DOWNLOAD_RESEARCH_REPORTS = "/downloadResearchReports";
		String RESEARCH_REPORT_FILTERS = "/researchReportFilters";
		
		String PER_PAGE_MAX_RECORD_COUNT = "/researchReports/perPageMaxRecords";
		String TOTAL_RECORD_COUNT = "/researchReports/totalRecordCount";
	}

	interface AdminDashBoard {
		String COMPANY_DETAILS_URI = "/companydetails";
	}
}
