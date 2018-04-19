package com.finvendor.serverwebapi.resources;

public interface WebUriConstants {

	String BASE_URI = "/system/api";
	String VENDORS = "/vendors";
	String VENDOR_DETAILS = "/vendorDetails";
	String FILTER_DATA_URI = "/filterdata";
	interface FinvendorAdmin{
		String PER_PAGE_MAX_RECORD_COUNT = "/admin/perPageMaxRecords";
	}
	
	interface ResearchReport {
		String RESEARCH_REPORTS = "/researchReports";
		String DASHBOARD_RESEARCH_REPORTS = "/dashboardResearchReports";
		String DOWNLOAD_RESEARCH_REPORTS = "/downloadResearchReports";
		String RESEARCH_REPORT_FILTERS = "/researchReportFilters";
		String RECORD_STATS = "/researchReports/recordStats";
	}
	
	interface HomePageSearch {
		String COMPANY_DATA_URI = "/homepagesearch";
		String COMPANY_PROFILE_DATA_URI = "/companyprofile";
		String COMPANY_RECORD_STATS="/companyrecordstats";
		String COMPANY_RESEARCH_REPORT="/companyresearchreport";
		
	}

	interface AdminDashBoard {
		String COMPANY_DETAILS_URI = "/companydetails";
	}
}
