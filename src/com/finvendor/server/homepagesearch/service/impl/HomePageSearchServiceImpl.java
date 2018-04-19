package com.finvendor.server.homepagesearch.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.server.homepagesearch.dao.IHomePageSearchDao;
import com.finvendor.server.homepagesearch.service.IHomePageSearchService;
import com.finvendor.server.researchreport.dao.ifc.IResearchReportDao;
import com.finvendor.server.researchreport.dto.filter.EquityResearchFilter;
import com.finvendor.server.researchreport.dto.filter.ifc.ResearchReportFilter;

@Service
public class HomePageSearchServiceImpl implements IHomePageSearchService {

	@Autowired
	IHomePageSearchDao dao;
	
	@Autowired
	@Qualifier(value = "equityResearchDao")
	IResearchReportDao equityDao;

	@Override
	@Transactional(readOnly = true)
	public String getCompanyData(String initialHint) throws Exception {
		return dao.getCompanyData(initialHint);
	}

	@Override
	@Transactional(readOnly = true)
	public String getCompanyProfileData(String isinCode, EquityResearchFilter filter) throws Exception {
		String mainQuery = getNewQuery(IHomePageSearchDao.companyProfileDataQuery, isinCode, filter);
		return dao.getCompanyProfileData(mainQuery);
	}

	@Override
	@Transactional(readOnly = true)
	public String getCompanyRecordStatistics(String isinCode,ResearchReportFilter filter, String perPageMaxRecords) {
		String mainQuery = getNewQuery(IHomePageSearchDao.companyResearchReportQuery, isinCode, filter);
		return equityDao.getRecordStatistics(mainQuery, filter, perPageMaxRecords);
	}

	@Override
	@Transactional(readOnly = true)
	public String getCompanyResearchReportData(String isinCode, ResearchReportFilter filter, String pageNumber,
			String perPageMaxRecords, String sortBy, String orderBy) throws Exception {
		String mainQuery = getNewQuery(IHomePageSearchDao.companyResearchReportQuery, isinCode, filter);
		return dao.getReasearchReportData(isinCode, mainQuery, filter, pageNumber, perPageMaxRecords, sortBy, orderBy);
	}

	private String getNewQuery(String mainQuery, String isinCode, ResearchReportFilter filter) {
		String newQuery = StringUtils.replace(mainQuery, "COUNTRYID", "'" + ((EquityResearchFilter) filter).getGeo() + "'");
		newQuery = StringUtils.replace(newQuery, "ISINCODE", "'" + isinCode + "'");
		return newQuery;
	}
}
