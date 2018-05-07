package com.finvendor.server.profile.rsrcharea.stock.service.impl;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.common.util.StringUtil;
import com.finvendor.server.profile.rsrcharea.stock.dao.ICompanyProfileDao;
import com.finvendor.server.profile.rsrcharea.stock.dao.impl.CompanyProfileDaoImpl;
import com.finvendor.server.profile.rsrcharea.stock.service.IRsrchAreaStockProfileService;
import com.finvendor.server.researchreport.dao.IResearchReportDao;
import com.finvendor.server.researchreport.dto.filter.impl.EquityResearchFilter;

/**
 * 
 * @author ayush on May 01, 2018
 */
@Service
public class RsrchAreaStockProfileSeriveImpl implements IRsrchAreaStockProfileService {
	public static Logger logger = LoggerFactory.getLogger(RsrchAreaStockProfileSeriveImpl.class);

	@Autowired
	ICompanyProfileDao companyProfileDao;

	@Autowired
	@Qualifier(value = "equityResearchDaoImpl")
	IResearchReportDao equityResearchReportDao;

	@SuppressWarnings("serial")
	@Override
	@Transactional(readOnly = true)
	public String getResearchAreaStockProfile(final String id, final String geo, final String isinCode)
			throws Exception {
		try {
			String mainQuery = StringUtil.replaceString(CompanyProfileDaoImpl.companyProfileDataQuery,
					new HashMap<String, String>() {
						{
							put("COUNTRYID", geo);
							put("ISINCODE", isinCode);
						}
					});

			return companyProfileDao.getProfile(mainQuery);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}

	@SuppressWarnings("serial")
	@Override
	@Transactional(readOnly = true)
	public String getResearchAreaStockRecordStats(final String id, final String geo, final String isinCode,
			String perPageMaxRecords) throws Exception {
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
	public String getResearchReport(final String id, final String geo, final String isinCode, String pageNumber,
			String perPageMaxRecords, String sortBy, String orderBy) throws Exception {
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
			return companyProfileDao.getReasearchReport(isinCode, mainQuery, filter, pageNumber, perPageMaxRecords,
					sortBy, orderBy);
		} catch (RuntimeException e) {
			throw new Exception(e);
		}
	}
}
