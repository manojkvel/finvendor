package com.finvendor.serverwebapi.resources.ifc;

public interface WebUriConstants {

	String BASE_URI = "/system/api";
	String VENDORS = "/vendors";
	String VENDOR_DETAILS = "/vendorDetails";

	interface ResearchReport {
		String RESEARCH_REPORTS = "/researchReports";
		String DASHBOARD_RESEARCH_REPORTS = "/dashboardResearchReports";
		String DOWNLOAD_RESEARCH_REPORTS = "/downloadResearchReports";
	}

	interface AdminDashBoard {
		String COMPANY_DETAILS_URI = "/companydetails";
	}
}
