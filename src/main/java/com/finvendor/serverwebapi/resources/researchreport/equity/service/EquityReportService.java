package com.finvendor.serverwebapi.resources.researchreport.equity.service;

import com.finvendor.common.util.Pair;
import com.finvendor.serverwebapi.resources.researchreport.equity.dao.EquityReportDao;
import com.finvendor.serverwebapi.resources.researchreport.equity.dto.filter.ResearchReportFilter;
import com.finvendor.serverwebapi.resources.researchreport.equity.dto.filter.impl.EquityResearchFilter;
import com.finvendor.serverwebapi.resources.researchreport.equity.dto.result.AbsResearchReportResult;
import com.finvendor.serverwebapi.resources.researchreport.equity.util.ResearchReportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Map;

/**
 * @author ayush on Feb 18, 2018
 */
@Service
@Transactional
public class EquityReportService {

    @Autowired
    private EquityReportDao equityReportDao;

    public <T extends ResearchReportFilter> Map<String, ? extends AbsResearchReportResult>
    getResearchReportTableData(T rrfilter, String pageNumber, String perPageMaxRecords, String sortBy, String orderBy) throws Exception {
        try {
            String mainQuery = ResearchReportUtil.MAIN_QUERY.replace("?",
                    "'" + ((EquityResearchFilter) rrfilter).getGeo() + "'");

            return equityReportDao
                    .findResearchReportTableData(mainQuery, rrfilter, pageNumber, perPageMaxRecords, sortBy, orderBy);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public <T extends ResearchReportFilter> String getRecordStatistics(T rrfilter, String perPageMaxRecords)
            throws Exception {
        try {
            String mainQuery = ResearchReportUtil.MAIN_QUERY.replace("?",
                    "'" + ((EquityResearchFilter) rrfilter).getGeo() + "'");
            return equityReportDao.getRecordStatistics(mainQuery, rrfilter, perPageMaxRecords);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }


    public Pair<Long, InputStream> download(String productId) throws Exception {
        try {
            return equityReportDao.download(productId);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }
}
