package com.finvendor.api.fvreport.service.pdfcontent;

import com.finvendor.api.fvreport.dto.sectoral.Sectoral;
import com.finvendor.common.infra.pdf.IPDFContentBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional
public class SectoralDataPDFContentBuilder implements IPDFContentBuilder<String, Sectoral> {

    @Override
    public Sectoral buildContent(String userName) throws IOException {
        return new Sectoral();
    }
}
