package com.finvendor.api.report.service.pdfgenerator;

import com.finvendor.api.report.dto.marketdatacontent.MarketDataContent;
import com.finvendor.common.infra.pdf.PDFContent;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.io.IOException;

@Transactional
@Service
public class MarketDataPDFGenerator extends AbstractPDFGenerator {
    private static final Logger logger = LoggerFactory.getLogger(MarketDataPDFGenerator.class.getName());

    @Override
    public boolean generate(PDFContent pdfContent, String file) throws Exception {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            if (pdfContent instanceof MarketDataContent) {
                MarketDataContent marketDataContent = (MarketDataContent) pdfContent;
                forMarketData(document, marketDataContent);
            }
        } catch (IOException | DocumentException e) {
            logger.error("Error while generating market data pdf, error details: ",e);
            return false;
        } finally {
            document.close();
        }
        return true;
    }

    private void forMarketData(Document document, MarketDataContent marketDataContent) throws DocumentException {
        document.add(getDearUserLine(marketDataContent.getBroaderIndexData().getUserName()));
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(getSummaryReportForLine("Market", marketDataContent.getBroaderIndexData().getCurrentDate()));
        addIndexData(document, marketDataContent.getBroaderIndexData(), "Broader Benchmark Index:", "Nifty 50 Index(NIFTY): ", true,
                true, true);
        addIndexData(document, marketDataContent.getMidCapIndexData(), "MidCap Index:", "Nifty Midcap 100 Index: ", true,
                true, true);
        addIndexData(document, marketDataContent.getSmallCapIndexData(), "SmallCap Index:", "Nifty Smallcap 100 Index: ", true,
                true, true);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        addFromFinvendorTeam(document);
    }
}
