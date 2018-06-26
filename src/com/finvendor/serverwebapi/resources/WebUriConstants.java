package com.finvendor.serverwebapi.resources;

/**
 * 
 * @author ayush on Jan 10, 2018
 */
public interface WebUriConstants {

	String BASE_URI = "/system/api";
	String VENDORS = "/vendors";
	String VENDOR_DETAILS = "/vendorDetails";
	String FILTER_DATA_URI = "/filterdata";

	interface FinvendorAdmin {
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
		String COMPANY_HOME_PAGE_SEARCH_URI = "/search/company";
		String COMPANY_PROFILE_DATA_URI = "/companyprofile";
		String COMPANY_RECORD_STATS = "/companyrecordstats";
		String COMPANY_RESEARCH_REPORT = "/companyresearchreport";
		String COMPANY_WATCHLIST_URI = "/companywatchlist";

	}

	interface AdminDashBoard {
		String COMPANY_DETAILS_URI = "/companydetails";
	}
}
