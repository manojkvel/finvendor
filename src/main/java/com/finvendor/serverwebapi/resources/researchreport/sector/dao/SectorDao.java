package com.finvendor.serverwebapi.resources.researchreport.sector.dao;

import com.finvendor.common.util.JsonUtil;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.serverwebapi.resources.researchreport.sector.dto.SectorFilter;
import com.finvendor.serverwebapi.resources.researchreport.sector.enums.SectorFilterTypeEnums;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SectorDao {
    private static final Logger log = LoggerFactory.getLogger(SectorDao.class.getName());
    private final String SECTOR_TYPE_QUERY = "select a.research_area_id, a.description from research_sub_area a where a.research_area_id=2 and a.description!='All sectors' order by a.description";
    private final String SECTOR_SUB_TYPE_QUERY = "select a.rsch_area_id, a.industry_sub_type_name from industry_sub_type a where a.rsch_area_id=2 order by a.industry_sub_type_name;";
    private final String ANALYST_TYPE_QUERY = "select b.vendor_id,b.analystType from ven_rsrch_rpt_offering a,vendor b where a.research_area=2 and a.vendor_id=b.vendor_id";
    private final String RESEARCHED_BY_QUERY = "select b.vendor_id,b.username from ven_rsrch_rpt_offering a,vendor b where a.research_area=2 and a.vendor_id=b.vendor_id";
    private final String RESEARCH_DATE_QUERY = "select b.product_id,b.rep_date from ven_rsrch_rpt_offering a,ven_rsrch_rpt_dtls b where a.research_area=2 and a.product_id=b.product_id";
    private final String REPORT_TONE_QUERY = "select b.product_id,b.rsrch_recomm_type from ven_rsrch_rpt_offering a,ven_rsrch_rpt_dtls b where a.research_area=2 and a.product_id=b.product_id ";//and b.rsrch_recomm_type !='none'";
    private final String REPORT_FREQUENCY_QUERY = "select b.product_id, b.report_name from ven_rsrch_rpt_offering a,ven_rsrch_rpt_dtls b where a.research_area=2 and a.product_id=b.product_id";
    private final String SECTOR_REPORT_MAIN_QUERY = "select a.description Industry, b.industry_sub_type_name IndustrySubType, f.username ResearchedBy, f.analystType AnalystType, d.rsrch_recomm_type Tones, c.product_name frequency,  d.rep_date ReportDate, e.analyst_name AnalystName from research_sub_area a, industry_sub_type b, ven_rsrch_rpt_offering c, ven_rsrch_rpt_dtls d, ven_rsrch_rpt_analyst_prof e, vendor f  where a.research_sub_area_id=b.rsch_sub_area_id and b.rsch_area_id=c.research_area and b.id=c.industry_subtype_id and c.product_id=d.product_id and c.vendor_id=f.vendor_id and d.product_id=e.product_id";

    @Autowired
    private ICommonDao commonDao;

    private Map<String, Object> dataMap = new LinkedHashMap<>();

    public String getFilterValue(String type) throws RuntimeException {
        try {
            return getQueryResult(getQueryBasedOnType(type));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public String getRecordStats(SectorFilter filter, String perPageMaxRecords) throws RuntimeException {
        log.info("filter:{}", filter);
        log.info("perPageMaxRecords:{}", perPageMaxRecords);
        String recordStatsJson;
        try {
            String filteredQuery = applyFilter(filter);
            log.info("filteredQuery:{}", filteredQuery);
            recordStatsJson = commonDao.getRecordStats(filteredQuery, perPageMaxRecords);
        } catch (Exception e) {
            throw new RuntimeException("Error while processing record stats", e);
        }
        return recordStatsJson;
    }

    private String applyFilter(SectorFilter filter) {
        String filteredQuery = "";
        if (filter == null) {
            filteredQuery = SECTOR_REPORT_MAIN_QUERY;
        } else {
            List<String> sectorTypes = filter.getSectorTypes();
            if(sectorTypes!=null){

            }
            filteredQuery = SECTOR_REPORT_MAIN_QUERY + " and ";
        }
        return filteredQuery;
    }

    public String getSectorReport() throws RuntimeException {
        try {
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getQueryBasedOnType(String type) {
        String query;
        if (SectorFilterTypeEnums.SECTOR_TYPE.getType().equals(type)) {
            query = SECTOR_TYPE_QUERY;
        } else if (SectorFilterTypeEnums.SECTOR_SUB_TYPE.getType().equals(type)) {
            query = SECTOR_SUB_TYPE_QUERY;
        } else if (SectorFilterTypeEnums.ANALYTST_TYPE.getType().equals(type)) {
            query = ANALYST_TYPE_QUERY;
        } else if (SectorFilterTypeEnums.RESEARCHED_BY.getType().equals(type)) {
            query = RESEARCHED_BY_QUERY;
        } else if (SectorFilterTypeEnums.RESEARCH_DATE.getType().equals(type)) {
            query = RESEARCH_DATE_QUERY;
        } else if (SectorFilterTypeEnums.REPORT_TONE.getType().equals(type)) {
            query = REPORT_TONE_QUERY;
        } else if (SectorFilterTypeEnums.REPORT_FREQUENCY.getType().equals(type)) {
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
