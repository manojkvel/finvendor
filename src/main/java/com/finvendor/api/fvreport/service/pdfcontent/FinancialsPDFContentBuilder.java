package com.finvendor.api.fvreport.service.pdfcontent;

import com.finvendor.api.fvreport.dto.financials.Financials;
import com.finvendor.common.infra.pdf.IPDFContentBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FinancialsPDFContentBuilder implements IPDFContentBuilder<String, Financials> {

    @Override
    public Financials buildContent(String userName) throws Exception {
        return new Financials();
    }
}
