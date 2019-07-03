package com.finvendor.api.fvreport.service;

import com.finvendor.api.fvreport.dao.BroaderBenchmarkIndexData;
import com.finvendor.api.fvreport.dto.UserWatchListData;
import com.finvendor.api.fvreport.service.pdfcontent.IPdfContentService;
import com.finvendor.common.infra.pdf.IPdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FvReportService {

    public static final String MKT_PDF = "mkt.pdf";
    @Autowired
    @Qualifier(value = "mktDataContentServiceImpl")
    private IPdfContentService<BroaderBenchmarkIndexData> mktDataContentService;

    @Autowired
    @Qualifier(value = "mktDataPdfGenerator")
    private IPdfGenerator mktDataPdfGenerator;

    public void sendReport(UserWatchListData userWatchListData) throws Exception {
        BroaderBenchmarkIndexData mktContent = mktDataContentService.getContent(userWatchListData);
        boolean isMktDataReportGenerated = mktDataPdfGenerator.generate(mktContent, MKT_PDF);

    }
}
