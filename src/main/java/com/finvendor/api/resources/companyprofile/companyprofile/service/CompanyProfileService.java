package com.finvendor.api.resources.companyprofile.companyprofile.service;

import com.finvendor.api.resources.companyprofile.companyprofile.dao.CompanyProfileDao;
import com.finvendor.api.resources.companyprofile.companyprofile.dto.EarningPreviewDto;
import com.finvendor.api.resources.screener.stock.recommendation.dao.EquityReportDao;
import com.finvendor.api.resources.screener.stock.recommendation.dto.filter.impl.EquityResearchFilter;
import com.finvendor.common.commondao.DaoUtils;
import com.finvendor.common.util.CommonCodeUtils;
import com.finvendor.common.util.LocaleUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ayush on May 01, 2018
 */
@Service
@Transactional
public class CompanyProfileService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyProfileService.class.getName());

    @Autowired
    CompanyProfileDao companyProfileDao;

    @Autowired
    EquityReportDao equityReportDao;

    public String getCompanyProfile(final String isinCode) throws Exception {
        try {
            int researchAreaId = 7; // TDB: Ayush : get id 7 (Equity) from db based on ISINCODE
            int countryId = CommonCodeUtils.getCountryId(isinCode);
            String mainQuery = DaoUtils.getParamertizedQuery(companyProfileDao.companyProfileDataQuery,
                    new Object[]{researchAreaId, countryId, isinCode});
            return companyProfileDao.getCompanyProfile(mainQuery, isinCode);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }


    public String getCompanyProfileRecordStat(final String isinCode, String perPageMaxRecords) throws Exception {
        final String geo = LocaleUtil.getCurrentGeo();
        try {
            int researchAreaId = 7; // TDB: Ayush : get id 7 (Equity) from db based on ISINCODE
            int countryId = CommonCodeUtils.getCountryId(isinCode);
            // Future work
            // else if (isinCode.contains("UK")) {
            // } else {
            // }
//			String mainQuery = DaoUtils.getParamertizedQuery(companyProfileDao.companyResearchReportQuery,
//					new Object[] { researchAreaId, countryId, researchAreaId, isinCode });
//			mainQuery = StringUtils.replace(mainQuery,"dateformat","%d/%m/%Y");
            EquityResearchFilter filter = new EquityResearchFilter();
            filter.setGeo(geo);
            String mainQuery1 = StringUtils.replace(CompanyProfileDao.mainQuery, "COUNTRYID", "" + countryId);
            String mainQuery = StringUtils.replace(mainQuery1, "?", "'" + isinCode + "'");
            logger.info("## getCompanyProfileRecordStat: {} ", mainQuery);
            return equityReportDao.getRecordStatistics(mainQuery, filter, perPageMaxRecords);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public String getCompanyProfileResearchReport(final String isinCode, String pageNumber, String perPageMaxRecords,
                                                  String sortBy, String orderBy) throws Exception {
        final String geo = LocaleUtil.getCurrentGeo();
        try {
            int countryId = CommonCodeUtils.getCountryId(isinCode);

            EquityResearchFilter filter = new EquityResearchFilter();
            filter.setGeo(geo);
            String mainQuery1 = StringUtils.replace(CompanyProfileDao.mainQuery, "COUNTRYID", "" + countryId);
            String mainQuery = StringUtils.replace(mainQuery1, "?", "'" + isinCode + "'");
            logger.info("## getCompanyProfileResearchReport: {} ", mainQuery);
            return companyProfileDao.getCompanyProfileReasearchReport(mainQuery, isinCode, filter, pageNumber, perPageMaxRecords,
                    sortBy, orderBy);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public EarningPreviewDto findEarningPreview(final String type, final String isin) throws Exception {
        try {
            return companyProfileDao.findEarningPreview(type, isin);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public String findCompanyNewsRecordStats(final String ticker, final String perPageMaxRecords) throws Exception {
        try {
            return companyProfileDao.findCompanyNewsRecordStats(ticker, perPageMaxRecords);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public String findCompanyNews(final String ticker, String pageNumber, String perPageMaxRecords) throws Exception {
        try {
            return companyProfileDao.findCompanyNews(ticker, pageNumber, perPageMaxRecords);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public String findCorporateActionRecordStats(final String ticker, final String perPageMaxRecords) throws Exception {
        try {
            return companyProfileDao.findCorporateActionRecordStats(ticker, perPageMaxRecords);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public String findCorporateAction(final String ticker, String pageNumber, String perPageMaxRecords) throws Exception {
        try {
            return companyProfileDao.findCorporateAction(ticker, pageNumber, perPageMaxRecords);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public String findCalendarRecordStats(final String ticker, final String perPageMaxRecords) throws Exception {
        try {
            return companyProfileDao.findCalendarRecordStats(ticker, perPageMaxRecords);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public String findCalendar(final String ticker, String pageNumber, String perPageMaxRecords) throws Exception {
        try {
            return companyProfileDao.findCalendar(ticker, pageNumber, perPageMaxRecords);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public String findPriceHistoryRecordStats(final String ticker, final String perPageMaxRecords) throws Exception {
        try {
            return companyProfileDao.findPriceHistoryRecordStats(ticker, perPageMaxRecords);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public String findPriceHistory(final String isin, String pageNumber, String perPageMaxRecords) throws Exception {
        try {
            return companyProfileDao.findPriceHistory(isin, pageNumber, perPageMaxRecords);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }
}
