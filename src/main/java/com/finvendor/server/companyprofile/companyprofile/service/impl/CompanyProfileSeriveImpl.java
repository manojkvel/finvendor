//package com.finvendor.server.companyprofile.companyprofile.service.impl;
//
//import com.finvendor.common.util.LocaleUtil;
//import com.finvendor.server.common.commondao.DaoUtils;
//import com.finvendor.server.companyprofile.companyprofile.dao.ICompanyProfileDao1;
//import com.finvendor.server.companyprofile.companyprofile.dao.impl.CompanyProfileDaoImpl;
//import com.finvendor.server.companyprofile.companyprofile.service.ICompanyProfileService;
//import com.finvendor.serverwebapi.resources.researchreport.equity.dao.EquityReportDao;
//import com.finvendor.serverwebapi.resources.researchreport.equity.dto.filter.impl.EquityResearchFilter;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author ayush on May 01, 2018
// */
//@Service
//public class CompanyProfileSeriveImpl implements ICompanyProfileService {
//
//	private static final Logger logger = LoggerFactory.getLogger(CompanyProfileSeriveImpl.class.getName());
//	@Autowired
//	ICompanyProfileDao1 dao;
//
//	@Autowired
////	@Qualifier(value = "equityResearchDaoImpl")
////	IResearchReportDao equityResearchReportDao;
//	EquityReportDao equityReportDao;
//
//
//	@Override
//	@Transactional(readOnly = true)
//	public String getCompanyProfile(final String isinCode) throws Exception {
//		try {
//			int researchAreaId = 7; // TDB: Ayush : get id 7 (Equity) from db based on ISINCODE
//			int countryId = 1;
//			if (isinCode.contains("IN")) {
//				countryId = 1;
//			}
//			// Future work
//			// else if (isinCode.contains("UK")) {
//			// } else {
//			// }
//			String mainQuery = DaoUtils.getParamertizedQuery(CompanyProfileDaoImpl.companyProfileDataQuery,
//					new Object[] { researchAreaId, countryId, isinCode });
//			return dao.getCompanyProfile(mainQuery);
//		} catch (RuntimeException e) {
//			throw new Exception(e);
//		}
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public String getCompanyProfileRecordStat(final String isinCode, String perPageMaxRecords) throws Exception {
//		final String geo = LocaleUtil.getCurrentGeo();
//		try {
//			int researchAreaId = 7; // TDB: Ayush : get id 7 (Equity) from db based on ISINCODE
//			int countryId = 1;
//			if (isinCode.contains("IN")) {
//				countryId = 1;
//			}
//			// Future work
//			// else if (isinCode.contains("UK")) {
//			// } else {
//			// }
//			String mainQuery = DaoUtils.getParamertizedQuery(CompanyProfileDaoImpl.companyResearchReportQuery,
//					new Object[] { researchAreaId, countryId, researchAreaId, isinCode });
//			mainQuery = StringUtils.replace(mainQuery,"dateformat","%d/%m/%Y");
//			EquityResearchFilter filter = new EquityResearchFilter();
//			filter.setGeo(geo);
//			return equityReportDao.getRecordStatistics(mainQuery, filter, perPageMaxRecords);
//		} catch (RuntimeException e) {
//			throw new Exception(e);
//		}
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public String getCompanyProfileResearchReport(final String isinCode, String pageNumber, String perPageMaxRecords,
//			String sortBy, String orderBy) throws Exception {
//		final String geo = LocaleUtil.getCurrentGeo();
//		try {
//			int researchAreaId = 7; // TDB: Ayush : get id 7 (Equity) from db based on ISINCODE
//			int countryId = 1;
//			if (isinCode.contains("IN")) {
//				countryId = 1;
//			}
//			// Future work
//			// else if (isinCode.contains("UK")) {
//			// } else {
//			// }
//			String mainQuery = DaoUtils.getParamertizedQuery(CompanyProfileDaoImpl.companyResearchReportQuery,
//					new Object[] { researchAreaId, countryId, researchAreaId, isinCode });
//			mainQuery = StringUtils.replace(mainQuery,"dateformat","%d/%m/%Y");
//			EquityResearchFilter filter = new EquityResearchFilter();
//			filter.setGeo(geo);
//			return dao.getCompanyProfileReasearchReport(isinCode, mainQuery, filter, pageNumber, perPageMaxRecords,
//					sortBy, orderBy);
//		} catch (RuntimeException e) {
//			throw new Exception(e);
//		}
//	}
//}
