package com.finvendor.serverwebapi.resources.researchreport.sector.dao;

import com.finvendor.common.util.JsonUtil;
import com.finvendor.common.util.Pair;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.serverwebapi.resources.researchreport.sector.dto.SectorReportDto;
import com.finvendor.serverwebapi.resources.researchreport.sector.dto.SectorReportFilter;
import com.finvendor.serverwebapi.resources.researchreport.sector.enums.SectorReportFilterTypes;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static com.finvendor.model.VendorResearchReportsResearchDetails.RESEARCH_REPORT_DETAILS_NAMED_QUERY;

@Repository
public class SectorReportDao {
    private static final Logger log = LoggerFactory.getLogger(SectorReportDao.class.getName());
    private final String SECTOR_TYPE_QUERY = "select a.research_area_id, a.description from research_sub_area a where a.research_area_id=2 and a.description!='All sectors' order by a.description";
    private final String SECTOR_SUB_TYPE_QUERY = "select a.rsch_area_id, a.industry_sub_type_name from industry_sub_type a where a.rsch_area_id=2 order by a.industry_sub_type_name;";
    private final String ANALYST_TYPE_QUERY = "select b.vendor_id,b.analystType from ven_rsrch_rpt_offering a,vendor b where a.research_area=2 and a.vendor_id=b.vendor_id";
    private final String RESEARCHED_BY_QUERY = "select b.vendor_id,b.username from ven_rsrch_rpt_offering a,vendor b where a.research_area=2 and a.vendor_id=b.vendor_id";
    private final String RESEARCH_DATE_QUERY = "select b.product_id, UNIX_TIMESTAMP(DATE_FORMAT(STR_TO_DATE(  b.rep_date, '%d/%m/%Y'), '%Y-%m-%d')) dateinmillis from ven_rsrch_rpt_offering a,ven_rsrch_rpt_dtls b where a.research_area=2 and a.product_id=b.product_id";
    private final String REPORT_TONE_QUERY = "select b.product_id,b.rsrch_recomm_type from ven_rsrch_rpt_offering a,ven_rsrch_rpt_dtls b where a.research_area=2 and a.product_id=b.product_id ";//and b.rsrch_recomm_type !='none'";
    private final String REPORT_FREQUENCY_QUERY = "select b.product_id, b.report_name from ven_rsrch_rpt_offering a,ven_rsrch_rpt_dtls b where a.research_area=2 and a.product_id=b.product_id";
    private final String SECTOR_REPORT_MAIN_QUERY = "select c.product_id, a.description Industry, b.industry_sub_type_name IndustrySubType, f.username ResearchedBy, f.analystType AnalystType, d.rsrch_recomm_type Tones, c.product_name frequency, d.report_name reportName, d.rep_date ReportDate, e.analyst_name AnalystName, d.rsrch_report_desc, e.anayst_cfa_charter from research_sub_area a, industry_sub_type b, ven_rsrch_rpt_offering c, ven_rsrch_rpt_dtls d, ven_rsrch_rpt_analyst_prof e, vendor f  where a.research_sub_area_id=b.rsch_sub_area_id and b.rsch_area_id=c.research_area and b.id=c.industry_subtype_id and c.product_id=d.product_id and c.vendor_id=f.vendor_id and d.product_id=e.product_id";

    @Autowired
    private ICommonDao commonDao;

    private Map<String, Object> dataMap = new LinkedHashMap<>();

    public String getFilterValue(String type) throws RuntimeException {
        try {
            if (SectorReportFilterTypes.OTHERS.getType().equals(type)) {
                List<String> othersValueList = new ArrayList<>();
                othersValueList.add("Research Reports by CFA");
                dataMap.put("data", othersValueList);
                return JsonUtil.createJsonFromParamsMap(dataMap);
            } else {
                return getQueryResult(getQueryBasedOnType(type));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public String getRecordStats(SectorReportFilter filter, String perPageMaxRecords) throws RuntimeException {
        log.info("filter: {}", filter);
        log.info("perPageMaxRecords:{}", perPageMaxRecords);
        String recordStatsJson;
        try {
            String filteredQuery = applyFilter(filter);
            log.info("filteredQuery: {}", filteredQuery);
            recordStatsJson = commonDao.getRecordStats(filteredQuery, perPageMaxRecords);
        } catch (Exception e) {
            throw new RuntimeException("Error while processing record stats", e);
        }
        return recordStatsJson;
    }

    public List<SectorReportDto> getSectorReports(SectorReportFilter sectorFilter, String pageNumber,
                                                  String perPageMaxRecords, String sortBy, String orderBy)
            throws RuntimeException {
        try {
            List<SectorReportDto> sectorReports = new ArrayList<>();
            try {
                String filteredQuery = applyFilter(sectorFilter);
                filteredQuery = applyOrderBy(filteredQuery, sortBy, orderBy);
                filteredQuery = applyPagination(pageNumber, perPageMaxRecords, filteredQuery);
                log.info("finalFilteredQuery:{}", filteredQuery);

                SQLQuery nativeQuery = commonDao.getNativeQuery(filteredQuery, null);
                List<Object[]> rows = nativeQuery.list();

                for (Object[] row : rows) {
                    String productId = row[0] != null ? row[0].toString().trim() : null;
                    String sectorType = row[1] != null ? row[1].toString().trim() : null;
                    String sectorSubType = row[2] != null ? row[2].toString().trim() : null;
                    String researchedBy = row[3] != null ? row[3].toString().trim() : null;
                    String analystType = row[4] != null ? row[4].toString().trim() : null;
                    String reportTone = row[5] != null ? row[5].toString().trim() : null;
                    String reportFrequency = row[6] != null ? row[6].toString().trim() : null;
                    String reportName = row[7] != null ? row[7].toString().trim() : null;
                    String reportDate = row[8] != null ? row[8].toString().trim() : null;
                    String analystName = row[9] != null ? row[9].toString().trim() : null;
                    String reportDesc = row[10] != null ? row[10].toString().trim() : null;
                    String byCfa = row[11] != null ? row[11].toString().trim() : null;

                    SectorReportDto dto = new SectorReportDto();
                    dto.setProductId(productId);
                    dto.setSectorType(sectorType);
                    dto.setSectorSubType(sectorSubType);
                    dto.setResearchedBy(researchedBy);
                    dto.setAnalystType(analystType);
                    dto.setReportTone(reportTone);
                    dto.setReportFrequency(reportFrequency);
                    dto.setReportName(reportName);
                    dto.setReportDate(reportDate);
                    dto.setAnalystName(analystName);

                    if(byCfa.isEmpty()){
                        dto.setResearchReportByCfa(null);
                    }else {
                        dto.setResearchReportByCfa(byCfa);
                    }

                    //Here setting report description as null so that it wont come as part of json resposne
                    if (sectorFilter.getProductIds() == null) {
                        dto.setReportDescription(null);
                    } else {
                        dto.setReportDescription(reportDesc);
                    }
                    sectorReports.add(dto);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error while processing record stats", e);
            }
            return sectorReports;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Pair<Long, InputStream> download(String productId) throws RuntimeException {
        Map<Object, Object> paramMap = new HashMap<>();
        paramMap.put("productId", productId);
        try {
            Pair<Long, InputStream> longInputStreamPair = commonDao.fetchBlobFromTable(RESEARCH_REPORT_DETAILS_NAMED_QUERY, paramMap);
            return longInputStreamPair;
        } catch (Exception e) {
            throw new RuntimeException("Error has occurred while fetching blob from table", e);
        }
    }

    private String applyOrderBy(String query, String sortBy, String orderByMode) {
        String field = "";
//        if (SectorReportFilterTypes.SECTOR_TYPE.getType().equals(sortBy)) {
//            field = "a.description";
//        }
        if (SectorReportFilterTypes.SECTOR_SUB_TYPE.getType().equals(sortBy)) {
            field = "b.industry_sub_type_name";
        }

//        if (SectorReportFilterTypes.ANALYTST_TYPE.getType().equals(sortBy)) {
//            field = "b.industry_sub_type_name";
//        }

        if (SectorReportFilterTypes.RESEARCHED_BY.getType().equals(sortBy)) {
            field = "f.username";
        }

//        if (SectorReportFilterTypes.RESEARCH_DATE.getType().equals(sortBy)) {
//            field = "d.rep_date";
//        }

        if (SectorReportFilterTypes.REPORT_TONE.getType().equals(sortBy)) {
            field = "d.rsrch_recomm_type";
        }

        if (SectorReportFilterTypes.REPORT_NAME.getType().equals(sortBy)) {
            field = "d.report_name";
        }

        query = query + " order by " + field + " " + orderByMode;
        return query;
    }

    private String applyPagination(String pageNumber, String perPageMaxRecords, String filteredQuery) {
        filteredQuery = filteredQuery + commonDao.applyPagination(pageNumber, perPageMaxRecords);
        return filteredQuery;
    }

    private String applyFilter(SectorReportFilter filter) {
        String filteredQuery = SECTOR_REPORT_MAIN_QUERY;

        List<String> sectorTypes = filter.getSectorTypes();
        if (sectorTypes != null) {
            String inClauseValues = getInClauseValues(sectorTypes);
            filteredQuery = filteredQuery + " AND a.description IN " + inClauseValues;
        }

        List<String> sectorSubTypes = filter.getSectorSubTypes();
        if (sectorSubTypes != null) {
            String inClauseValues = getInClauseValues(sectorSubTypes);
            filteredQuery = filteredQuery + " AND b.industry_sub_type_name IN " + inClauseValues;
        }

        List<String> researchedBy = filter.getResearchedBy();
        if (researchedBy != null) {
            String inClauseValues = getInClauseValues(researchedBy);
            filteredQuery = filteredQuery + " AND f.username IN " + inClauseValues;
        }

        List<String> analystType = filter.getAnalystTypes();
        if (analystType != null) {
            String inClauseValues = getInClauseValues(analystType);
            filteredQuery = filteredQuery + " AND f.analystType IN " + inClauseValues;
        }

        List<String> recommTypes = filter.getReportTones();
        if (recommTypes != null) {
            String inClauseValues = getInClauseValues(recommTypes);
            filteredQuery = filteredQuery + " AND d.rsrch_recomm_type IN " + inClauseValues;
        }

        List<String> reportFrequencyList = filter.getReportFrequency();
        if (reportFrequencyList != null) {
            String inClauseValues = getInClauseValues(reportFrequencyList);
            filteredQuery = filteredQuery + " AND  c.product_name IN " + inClauseValues;
        }

        List<String> reportDateList = filter.getResearchDates();
        if (reportDateList != null) {
            String inClauseValues = getInClauseValues(reportDateList);
            filteredQuery = filteredQuery + " AND  d.rep_date IN " + inClauseValues;
        }

        List<String> analystNames = filter.getAnalystNames();
        if (analystNames != null) {
            String inClauseValues = getInClauseValues(analystNames);
            filteredQuery = filteredQuery + " AND  e.analyst_name IN " + inClauseValues;
        }

        List<String> productIds = filter.getProductIds();
        if (productIds != null) {
            String inClauseValues = getInClauseValues(productIds);
            filteredQuery = filteredQuery + " AND  c.product_id IN " + inClauseValues;
        }

        List<String> others = filter.getOthers();
        if (others != null) {
            String inClauseValues = getInClauseValues(others);
            filteredQuery = filteredQuery + " AND e.anayst_cfa_charter IN " + inClauseValues;
        }

        return filteredQuery;
    }

    private String getInClauseValues(List<String> sectorTypes) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (String value : sectorTypes) {
            String newValue;
            if (StringUtils.isEmpty(value)) {
                newValue = "";
            } else {
                if ("Research Reports by CFA".equals(value)) {
                    newValue = "Y";
                } else {
                    newValue = value;
                }
            }
            sb.append("'").append(newValue).append("'").append(",");
        }
        sb.deleteCharAt(sb.toString().length() - 1);
        sb.append(")");
        return sb.toString();
    }

    private String getQueryBasedOnType(String type) {
        String query;
        if (SectorReportFilterTypes.SECTOR_TYPE.getType().equals(type)) {
            query = SECTOR_TYPE_QUERY;
        } else if (SectorReportFilterTypes.SECTOR_SUB_TYPE.getType().equals(type)) {
            query = SECTOR_SUB_TYPE_QUERY;
        } else if (SectorReportFilterTypes.ANALYTST_TYPE.getType().equals(type)) {
            query = ANALYST_TYPE_QUERY;
        } else if (SectorReportFilterTypes.RESEARCHED_BY.getType().equals(type)) {
            query = RESEARCHED_BY_QUERY;
        } else if (SectorReportFilterTypes.RESEARCH_DATE.getType().equals(type)) {
            query = RESEARCH_DATE_QUERY;
        } else if (SectorReportFilterTypes.REPORT_TONE.getType().equals(type)) {
            query = REPORT_TONE_QUERY;
        } else if (SectorReportFilterTypes.REPORT_FREQUENCY.getType().equals(type)) {
            query = REPORT_FREQUENCY_QUERY;
        } else {
            query = "";
        }
        return query;
    }

    private String getQueryResult(String query) throws IOException {
        SQLQuery nativeQuery = commonDao.getNativeQuery(query, null);
        List<Object[]> rows = nativeQuery.list();
        List<String> sectorTypes = new ArrayList<>();
        for (Object[] row : rows) {
            String filterValue = row[1] != null ? row[1].toString().trim() : null;
            sectorTypes.add(filterValue);
        }
        dataMap.put("data", sectorTypes);
        return JsonUtil.createJsonFromParamsMap(dataMap);
    }
}