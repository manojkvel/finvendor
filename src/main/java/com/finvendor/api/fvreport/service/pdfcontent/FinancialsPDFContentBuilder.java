package com.finvendor.api.fvreport.service.pdfcontent;

import com.finvendor.api.fvreport.dao.FvReportDao;
import com.finvendor.api.fvreport.dto.financials.Financials;
import com.finvendor.api.fvreport.dto.financials.FinancialsQuarterly;
import com.finvendor.api.fvreport.dto.financials.FinancialsYearly;
import com.finvendor.common.infra.pdf.IPDFContentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FinancialsPDFContentBuilder implements IPDFContentBuilder<String, Financials> {

    @Autowired
    protected FvReportDao reportDao;

    @Override
    public Financials buildContent(String userName) throws Exception {
        List<FinancialsQuarterly> financialsQuarterly = reportDao.findFinancialsQuarterly();
        List<FinancialsYearly> financialsYearly = reportDao.findFinancialsYearly();

        return new Financials(userName, financialsQuarterly,financialsYearly);
    }
}
