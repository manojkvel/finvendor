package com.finvendor.api.fvreport.service.pdfgenerator;

import com.finvendor.api.fvreport.dao.BroaderBenchmarkIndexData;
import com.finvendor.common.infra.pdf.PdfContent;
import com.finvendor.common.infra.pdf.PdfContentHelper;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Paragraph;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.finvendor.common.infra.pdf.PdfContentHelper.BLUE_BOLD;

@Transactional
@Service
public class MktDataPdfGenerator extends AbstractPdfGenerator {

    @Override
    public boolean generate(PdfContent pdfContent, String file) throws Exception {
        BroaderBenchmarkIndexData mdc = null;
        if (pdfContent instanceof BroaderBenchmarkIndexData) {
            mdc = (BroaderBenchmarkIndexData) pdfContent;
            Paragraph dearUserLine = getDearUserLine(mdc);
            Paragraph mktSummaryReportForLine = getMktSummaryReportForLine(mdc);

        }
        return true;
    }

    private Paragraph getDearUserLine(BroaderBenchmarkIndexData mdc) {
        Chunk c1 = new Chunk("Dear ");
        Chunk c2 = new Chunk(mdc.getUserName(), BLUE_BOLD);
        return PdfContentHelper.getParagraph(new Chunk[] { c1, c2 }, 10);
    }

    private Paragraph getMktSummaryReportForLine(BroaderBenchmarkIndexData mdc) {
        Chunk c1 = new Chunk("Market Summary Report for:  ");
        Chunk c2 = new Chunk(mdc.getCurrentDate(), BLUE_BOLD);
        return PdfContentHelper.getParagraph(new Chunk[] { c1, c2 }, 10);
    }
}
