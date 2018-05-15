package com.finvendor.server.companyprofile.service.impl;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.common.util.LocaleUtil;
import com.finvendor.common.util.StringUtil;
import com.finvendor.server.companyprofile.dao.ICompanyProfileDao1;
import com.finvendor.server.companyprofile.dao.impl.CompanyProfileDaoImpl;
import com.finvendor.server.companyprofile.service.ICompanyProfileService;
import com.finvendor.server.researchreport.dao.IResearchReportDao;
import com.finvendor.server.researchreport.dto.filter.impl.EquityResearchFilter;

/**
 * 
 * @author ayush on May 01, 2018
 */
@Service
public class CompanyProfileSeriveImpl implements ICompanyProfileService {
	public static Logger logger = LoggerFactory.getLogger(CompanyProfileSeriveImpl.class);

	@Autowired
	ICompanyProfileDao1 dao;

	@Autowired
	@Qualifier(value = "equityResearchDaoImpl")
	IResearchReportDao equityResearchReportDao;
	
	@SuppressWarnings("serial")
	@Override
	@Transactional(readOnly = true)
	public String getCompanyProfile(final String isinCode) throws Exception {
		final String geo = LocaleUtil.getCurrentGeo();
		try {
			String mainQuery = StringUtil.replaceString(CompanyProfileDaoImpl.companyProfileDataQuery,
					new HashMap<String, String>() {
						{
							put("COUNTRYID", geo);
							put("ISINCODE", isinCode);
						}
					});

			return dao.getCompanyProfile(mainQuery);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@SuppressWarnings("serial")
	@Override
	@Transactional(readOnly = true)
	public String getCompanyProfileRecordStat(final String isinCode, String perPageMaxRecords)
			throws Exception {
		final String geo = LocaleUtil.getCurrentGeo();
		try {
			String mainQuery = StringUtil.replaceString(CompanyProfileDaoImpl.companyResearchReportQuery,
					new HashMap<String, String>() {
						{
							put("COUNTRYID", geo);
							put("ISINCODE", isinCode);
						}
					});
			EquityResearchFilter filter = new EquityResearchFilter();
			filter.setGeo(geo);
			return equityResearchReportDao.getRecordStatistics(mainQuery, filter, perPageMaxRecords);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@SuppressWarnings("serial")
	@Override
	@Transactional(readOnly = true)
	public String getCompanyProfileResearchReport(final String isinCode, String pageNumber, String perPageMaxRecords,
			String sortBy, String orderBy) throws Exception {
		final String geo = LocaleUtil.getCurrentGeo();
		try {
			String mainQuery = StringUtil.replaceString(CompanyProfileDaoImpl.companyResearchReportQuery,
					new HashMap<String, String>() {
						{
							put("COUNTRYID", geo);
							put("ISINCODE", isinCode);
						}
					});
			EquityResearchFilter filter = new EquityResearchFilter();
			filter.setGeo(geo);
			return dao.getCompanyProfileReasearchReport(isinCode, mainQuery, filter, pageNumber, perPageMaxRecords,
					sortBy, orderBy);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}
}
