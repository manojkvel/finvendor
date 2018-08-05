package com.finvendor.server.common.infra.download.dao.impl;

import com.finvendor.common.util.Pair;
import com.finvendor.model.VendorResearchReportsResearchDetails;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.server.common.infra.download.dao.IDownloadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.finvendor.model.VendorResearchReportsResearchDetails.RESEARCH_REPORT_DETAILS_NAMED_QUERY;

/**
 * Ayush on 5-Aug-2018
 */
@Repository
public class DownloadDaoImpl implements IDownloadDao {
    @Autowired
    private ICommonDao commonDao;

    @Override
    public Pair<Long,InputStream> download(String productId) throws RuntimeException {
        Map<Object, Object> paramMap = new HashMap<>();
        paramMap.put("productId", productId);
        org.hibernate.Query query = commonDao.getNamedQuery(RESEARCH_REPORT_DETAILS_NAMED_QUERY, paramMap);
        List<VendorResearchReportsResearchDetails> researchDetailsList = query.list();

        InputStream inputStream=null;
        Long length = null;
        try {
            for (VendorResearchReportsResearchDetails researchReportsResearchDetails : researchDetailsList) {
                Blob rsrchUploadReportBlob = researchReportsResearchDetails.getRsrchUploadReport();
                inputStream = rsrchUploadReportBlob.getBinaryStream();
                length= rsrchUploadReportBlob.length();
                break;
            }
            return new Pair<>(length,inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Error has occurred while fetching blob from table", e);
        }
    }
}
