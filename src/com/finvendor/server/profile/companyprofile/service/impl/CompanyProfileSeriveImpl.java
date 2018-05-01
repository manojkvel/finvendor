package com.finvendor.server.profile.companyprofile.service.impl;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.common.util.StringUtil;
import com.finvendor.server.profile.companyprofile.dao.ICompanyProfileDao;
import com.finvendor.server.profile.companyprofile.dao.impl.CompanyProfileDaoImpl;
import com.finvendor.server.profile.companyprofile.service.ICompanyProfileService;
import com.finvendor.server.researchreport.dao.IResearchReportDao;
import com.finvendor.server.researchreport.dto.filter.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.filter.impl.EquityResearchFilter;

/**
 * 
 * @author ayush on May 01, 2018
 */
@Service
public class CompanyProfileSeriveImpl implements ICompanyProfileService {
	public static Logger logger = LoggerFactory.getLogger(CompanyProfileSeriveImpl.class);

	@Autowired
	ICompanyProfileDao companyProfileDao;

	@Autowired
	@Qualifier(value = "equityResearchDaoImpl")
	IResearchReportDao equityResearchReportDao;

	@SuppressWarnings("serial")
	@Override
	@Transactional(readOnly = true)
	public String getProfile(final String isinCode, final EquityResearchFilter filter) throws Exception {
		String mainQuery = StringUtil.replaceString(CompanyProfileDaoImpl.companyProfileDataQuery,
				new HashMap<String, String>() {
					{
						put("COUNTRYID", ((EquityResearchFilter) filter).getGeo());
						put("ISINCODE", isinCode);
					}
				});

		return companyProfileDao.getProfile(mainQuery);
	}

	@SuppressWarnings("serial")
	@Override
	@Transactional(readOnly = true)
	public String getResearchReportRecordStatistics(final String isinCode, final ResearchReportFilter filter,
			String perPageMaxRecords) {
		String mainQuery = StringUtil.replaceString(CompanyProfileDaoImpl.companyResearchReportQuery,
				new HashMap<String, String>() {
					{
						put("COUNTRYID", ((EquityResearchFilter) filter).getGeo());
						put("ISINCODE", isinCode);
					}
				});
		return equityResearchReportDao.getRecordStatistics(mainQuery, filter, perPageMaxRecords);
	}

	@SuppressWarnings("serial")
	@Override
	@Transactional(readOnly = true)
	public String getResearchReport(final String isinCode, final ResearchReportFilter filter, String pageNumber,
			String perPageMaxRecords, String sortBy, String orderBy) throws Exception {
		String mainQuery = StringUtil.replaceString(CompanyProfileDaoImpl.companyResearchReportQuery,
				new HashMap<String, String>() {
					{
						put("COUNTRYID", ((EquityResearchFilter) filter).getGeo());
						put("ISINCODE", isinCode);
					}
				});
		return companyProfileDao.getReasearchReport(isinCode, mainQuery, filter, pageNumber, perPageMaxRecords,
				sortBy, orderBy);
	}
}
