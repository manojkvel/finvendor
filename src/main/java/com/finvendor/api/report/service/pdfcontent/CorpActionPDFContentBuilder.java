package com.finvendor.api.report.service.pdfcontent;

import com.finvendor.api.report.dao.ReportDao;
import com.finvendor.api.report.dto.corpaction.CorpAction;
import com.finvendor.api.report.dto.corpaction.CorpActionPDFContent;
import com.finvendor.common.infra.pdf.IPDFContentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CorpActionPDFContentBuilder implements IPDFContentBuilder<String, CorpActionPDFContent> {

    @Autowired
    protected ReportDao reportDao;

    @Override
    public CorpActionPDFContent buildContent(String userName) throws Exception {
        List<CorpAction> corpActions = reportDao.findCorpAction();
        return new CorpActionPDFContent(userName, corpActions);
    }
}
