package com.finvendor.api.resources.researchreport.sector.service;

import com.finvendor.common.util.JsonUtil;
import com.finvendor.common.util.Pair;
import com.finvendor.api.resources.researchreport.sector.dao.SectorReportDao;
import com.finvendor.api.resources.researchreport.sector.dto.SectorReportDto;
import com.finvendor.api.resources.researchreport.sector.dto.SectorReportFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SectorReportService {

    @Autowired
    private SectorReportDao dao;

    public String getIndustrySubTypeName(String researchArea) throws Exception {
        try {
            return dao.getIndustrySubTypeNames(researchArea);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public String getFilterValue(String type) throws Exception {
        try {
            return dao.getFilterValue(type);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public String getRecordStats(SectorReportFilter filter, String perPageMaxRecords) throws Exception {
        try {
            return dao.getRecordStats(filter, perPageMaxRecords);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public String getSectorReports(SectorReportFilter sectorFilter, String pageNumber, String perPageMaxRecords, String sortBy, String orderBy)
            throws Exception {
        try {
            List<SectorReportDto> sectorReports = dao.getSectorReports(sectorFilter, pageNumber, perPageMaxRecords, sortBy, orderBy);
            Map<String, Object> dataMap = new LinkedHashMap<>();
            dataMap.put("data", sectorReports);
            String resultJson = JsonUtil.createJsonFromParamsMap(dataMap);
            return resultJson;
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public String getReportDashboard(SectorReportFilter sectorFilter, String pageNumber, String perPageMaxRecords, String sortBy, String orderBy) throws Exception {
        try {
            return getSectorReports(sectorFilter,pageNumber,perPageMaxRecords,sortBy,orderBy);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public Pair<Long, InputStream> download(String productId) throws RuntimeException {
        Map<Object, Object> paramMap = new HashMap<>();
        paramMap.put("productId", productId);
        try {
            return dao.download(productId);
        } catch (Exception e) {
            throw new RuntimeException("Error has occurred while fetching blob from table", e);
        }
    }
}
