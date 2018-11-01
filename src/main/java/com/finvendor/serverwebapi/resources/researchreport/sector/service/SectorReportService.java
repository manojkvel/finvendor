package com.finvendor.serverwebapi.resources.researchreport.sector.service;

import com.finvendor.serverwebapi.resources.researchreport.sector.dao.SectorReportDao;
import com.finvendor.serverwebapi.resources.researchreport.sector.dto.SectorReportFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SectorReportService {

    @Autowired
    private SectorReportDao dao;

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
            return dao.getSectorReport(sectorFilter, pageNumber, perPageMaxRecords,sortBy,orderBy);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }
}
