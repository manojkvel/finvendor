package com.finvendor.api.report.service.pdfcontent;

import com.finvendor.api.report.dao.ReportDao;
import com.finvendor.api.report.dto.CompanyIdNameDto;
import com.finvendor.api.report.dto.financials.Financials;
import com.finvendor.api.report.dto.financials.FinancialsQuarterly;
import com.finvendor.api.report.dto.financials.FinancialsYearly;
import com.finvendor.common.infra.pdf.IPDFContentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FinancialsPDFContentBuilder implements IPDFContentBuilder<String, Financials> {

    @Autowired
    protected ReportDao reportDao;

    @Override
    public Financials buildContent(String userName) throws Exception {
        List<CompanyIdNameDto> companyIdNameDtoList = reportDao.findCompanyName(userName);
        List<FinancialsQuarterly> financialsQuarterly = reportDao.findFinancialsQuarterly(companyIdNameDtoList);
        List<FinancialsYearly> financialsYearly = reportDao.findFinancialsYearly(companyIdNameDtoList);

        return new Financials(userName, financialsQuarterly,financialsYearly);
    }
}
