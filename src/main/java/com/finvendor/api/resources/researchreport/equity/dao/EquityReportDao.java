package com.finvendor.api.resources.researchreport.equity.dao;

import com.finvendor.common.util.CommonCodeUtils;
import com.finvendor.common.util.DateUtils;
import com.finvendor.common.util.JsonUtil;
import com.finvendor.common.util.Pair;
import com.finvendor.common.commondao.ICommonDao;
import com.finvendor.api.resources.researchreport.equity.dto.filter.ResearchReportFilter;
import com.finvendor.api.resources.researchreport.equity.dto.filter.impl.EquityResearchFilter;
import com.finvendor.api.resources.researchreport.equity.dto.result.impl.EquityResearchResult;
import com.finvendor.api.resources.researchreport.equity.util.ResearchReportUtil;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.finvendor.model.vo.VendorReportFile.REPORT_FILE_NAMED_QUERY;

/**
 * @author ayush
 * @since 03-Feb-2018
 */
@Repository
public class EquityReportDao {
    private static final Logger logger = LoggerFactory.getLogger(EquityReportDao.class.getName());

    @Autowired
    private ICommonDao commonDao;

    @SuppressWarnings("unchecked")
    public String getRecordStatistics(String mainQuery, ResearchReportFilter filter, String perPageMaxRecords)
            throws RuntimeException {
        try {
            EquityResearchFilter equityFilter = (EquityResearchFilter) filter;

            String queryWithAppliedFilter = ResearchReportUtil.applyFilter(mainQuery,
                    ResearchReportUtil.getFilteredQueryPart(equityFilter));
            SQLQuery query = commonDao.getNativeQuery(queryWithAppliedFilter, null);
            logger.info("getRecordStatistics - Query:{}",queryWithAppliedFilter);
            long strt = System.currentTimeMillis();
            List<Object[]> rows = query.list();
            logger.info("Time Metrics - EquityResearchDaoImpl - getRecordStatistics - Total time record stat:" + (System.currentTimeMillis() - strt) / 1000L + " sec");
            int totalRecords = rows.size();

            // Calculate Last page number
            long lastPageNumber = CommonCodeUtils.calculatePaginationLastPage(perPageMaxRecords, totalRecords);

            // Prepare Json result
            Map<String, Object> paramsMap = new LinkedHashMap<>();
            paramsMap.put("firstPageNumber", 1);
            paramsMap.put("lastPageNumber", lastPageNumber);
            paramsMap.put("totalRecords", totalRecords);

            return JsonUtil.createJsonFromObject(paramsMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, EquityResearchResult> findResearchReportTableData(String mainQuery, ResearchReportFilter filter,
                                                                         String pageNumber,
                                                                         String perPageMaxRecords, String sortBy, String orderBy) throws RuntimeException {
        logger.debug("findResearchReportTableData - START");
        EquityResearchFilter equityFilter = (EquityResearchFilter) filter;
        Map<String, EquityResearchResult> resultMap = new LinkedHashMap<>();
        long start;
        try {
            // Prepare brokerRank data from db
            List<ResearchReportUtil.BrokerRankInfo> brokerRankData = ResearchReportUtil.getBrokerRankData(commonDao,
                    ResearchReportUtil.BROKER_RANK_SELECT_QUERY, orderBy);

            // Apply filter in main query
            start=System.currentTimeMillis();
            String queryWithAppliedFilter = ResearchReportUtil.applyFilter(mainQuery,
                    ResearchReportUtil.getFilteredQueryPart(equityFilter));
            logger.info("Time Metrics- findResearchReportTableData - Total time taken to apply filter: "+(System.currentTimeMillis()-start)/1000 +" sec");

            // Apply OrderBy
            String applyOrderBy = ResearchReportUtil.applyOrderBy(sortBy, orderBy);

            // Apply Pagination
            String applyPagination = CommonCodeUtils.applyPagination(pageNumber, perPageMaxRecords);

            // Prepare final query
            String finalMainQuery = queryWithAppliedFilter + applyOrderBy + applyPagination;

            logger.info("Equity Research Report Quert: {}", finalMainQuery);

            // Execute Query
            SQLQuery query = commonDao.getNativeQuery(finalMainQuery, null);

            start=System.currentTimeMillis();
            List<Object[]> rows = query.list();
            logger.info("Time Metrics- findResearchReportTableData - Total time taken to fetch Equity Research Report: "+(System.currentTimeMillis()-start)/1000 +" sec");

            List<String> researchDateList = equityFilter.getResearchDate();
            // Process Result
            start=System.currentTimeMillis();
            for (Object[] row : rows) {
                EquityResearchResult equityResult = new EquityResearchResult();
                equityResult.setCompanyId(row[0] != null ? row[0].toString() : "");
                equityResult.setCompany(row[1] != null ? row[1].toString() : "");
                equityResult.setIsinCode(row[2] != null ? row[2].toString() : "");
                equityResult.setStyle(row[3] != null ? row[3].toString() : "");
                equityResult.setMcap(row[4] != null ? row[4].toString() : "");
                equityResult.setSector(row[5] != null ? row[5].toString() : "");
                String cmp = row[6] != null ? row[6].toString().trim() : "";
                float cmpAsFloat = Float.parseFloat(cmp);
                equityResult.setCmp(cmp);
                String priceDate = row[7] != null ? row[7].toString() : "";
                equityResult.setPriceDate(String
                        .valueOf(DateUtils.convertFvPriceDateToTimestamp(priceDate)));
                //equityResult.setPe(row[8] != null ? row[8].toString() : "");
                equityResult.set_3YrPatGrowth(row[9] != null ? row[9].toString() : "");

                String productId = row[13] != null ? row[13].toString() : "";
                equityResult.setProductId(productId);

                equityResult.setBroker(row[14] != null ? row[14].toString() : "");
                equityResult.setRecommType(row[15] != null ? row[15].toString() : "");

                equityResult.setTargetPrice(row[16] != null ? row[16].toString() : "");
                equityResult.setPriceAtRecomm(row[17] != null ? row[17].toString() : "");
                equityResult.setUpside(row[18] != null ? row[18].toString() : "");

                String reportName = row[19] != null ? row[19].toString() : "";
                reportName = reportName.substring(reportName.lastIndexOf('/') + 1);
                equityResult.setReport(reportName);

                String researchDate = row[20] != null ? row[20].toString() : "";
                long researchDateAsTimeStamp = ResearchReportUtil.convertStringToTimestamp(researchDate);
                equityResult.setResearchDate(String.valueOf(researchDateAsTimeStamp));

                String awarded;
                if (row[21] != null) {
                    if (row[21].toString().isEmpty()) {
                        awarded = "N";
                    } else {
                        awarded = row[21].toString();
                    }
                } else {
                    awarded = "NA";
                }
                equityResult.setAwarded(awarded);

                String researchedByCfa = "";
                if (row[22] != null) {
                    if (row[22].toString().isEmpty()) {
                        researchedByCfa = "N";
                    } else {
                        researchedByCfa = row[22].toString();
                    }
                } else {
                    researchedByCfa = "NA";
                }
                equityResult.setResearchedByCfa(researchedByCfa);

                equityResult.setAnalystName(row[23] != null ? row[23].toString() : "");
                equityResult.setAnalystType(row[24] != null ? row[24].toString() : "");

                // Since
                String vendorId = row[25] != null ? row[25].toString() : "";
                String vendorSince = row[26] != null ? row[26].toString() : "";
                equityResult.setSince(vendorSince);

                equityResult.setVendorName(row[27] != null ? row[27].toString() : "");
                equityResult.setReportDesc(row[28] != null ? row[28].toString() : "");
                equityResult.set_3YrEpsGrowth(row[10] != null ? row[10].toString() : "");
                float epsTtmAsFloat = Float.parseFloat(row[11] != null ? row[11].toString().trim() : "");

                //calculation of PE
                String newPeStr;
                float newPe;
                if (epsTtmAsFloat == 0.0f) {
                    newPeStr = "N/A";
                } else {
                    newPe = cmpAsFloat / epsTtmAsFloat;
                    newPeStr = String.valueOf(newPe);
                }
                equityResult.setPe(newPeStr);
                // Broker Rank
                Map<String, String> brokerRanks = ResearchReportUtil.getBrokerRank(brokerRankData, vendorId,
                        equityFilter);
                equityResult.setBrokerRank(brokerRanks);

                // Set Current Page number
                equityResult.setPageNumber(pageNumber);

                String productNameAsReportName = row[29] != null ? row[29].toString() : "";
                equityResult.setReportName(productNameAsReportName);
                resultMap.put(productId, equityResult);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        logger.info("Time Metrics- findResearchReportTableData - Total time taken to process Equity Research Report: "+(System.currentTimeMillis()-start)/1000 +" sec");
        logger.debug("findResearchReportTableData - END");
        return resultMap;
    }

    public Pair<Long, InputStream> download(String productId) throws RuntimeException {
        Map<Object, Object> paramMap = new HashMap<>();
        paramMap.put("productId", productId);
        try {
            Pair<Long, InputStream> longInputStreamPair = commonDao.fetchBlobFromTable(REPORT_FILE_NAMED_QUERY, paramMap);
            return longInputStreamPair;
        } catch (Exception e) {
            throw new RuntimeException("Error has occurred while fetching blob from table", e);
        }
    }
}
