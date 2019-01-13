package com.finvendor.api.resources.homepage.dao;

import com.finvendor.api.resources.homepage.dto.MutualFundData;
import com.finvendor.api.resources.homepage.dto.SectorData;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HomePageSectorSearch extends AbstractHomePageSearch {

    @Override
    public List<SectorData> getSearchResult(String hint) throws RuntimeException {
        String SEARCH_HINT_QUERY = "select d.product_id,b.description SectorType, c.industry_sub_type_name SectorSubType,d.vendor_name RESEARCHEDBY,d.vendor_analyst_type ANALYSTTYPE,d.rsrch_recomm_type REPORT_TONE, d.report_frequency REPORT_FREQUENCY,d.report_name REPORT,d.report_date RESEARCH_DATE,d.analyst_name ANALYST_NAME,d.rsrch_report_desc DESCR,d.anayst_cfa_charter cfa from industry_sub_type c,research_sub_area b,vendor_report_data d where c.rsch_sub_area_id=b.research_sub_area_id and c.id=d.research_report_for_id and d.research_area_id=2 and (b.description like '%HINT%' or c.industry_sub_type_name like '%HINT%')  group by b.description,c.industry_sub_type_name";
        SEARCH_HINT_QUERY = StringUtils.replace(SEARCH_HINT_QUERY,"HINT",hint);
        List<SectorData> sectorSearchDataList = new ArrayList<>();
        try {
            SQLQuery sqlQuery = commonDao.getNativeQuery(SEARCH_HINT_QUERY, null);
            List<Object[]> rows = sqlQuery.list();
            for (Object[] row : rows) {
                String productId = row[0] != null ? row[0].toString().trim() : "";
                String sectorType = row[1] != null ? row[1].toString().trim() : "";
                String sectorSubType = row[2] != null ? row[2].toString().trim() : "";
                sectorSearchDataList.add(new SectorData(productId,sectorType,sectorSubType));
            }
            return sectorSearchDataList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
