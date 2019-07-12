package com.finvendor.api.report.service.pdfgenerator;

import com.finvendor.api.report.dto.sectoral.SectoralDataPDFContent;
import com.finvendor.common.infra.pdf.PDFContent;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.io.IOException;

@Transactional
@Service
public class SectoralDataPDFGenerator extends AbstractPDFGenerator {

    @Override
    public boolean generate(PDFContent pdfContent, String file) throws Exception {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            if (pdfContent instanceof SectoralDataPDFContent) {
                SectoralDataPDFContent sectoralDataPDFContent = (SectoralDataPDFContent) pdfContent;
                forSectoralData(document, sectoralDataPDFContent);
            }
        } catch (IOException | DocumentException e) {
            throw new Exception(e);
        } finally {
            document.close();
        }
        return true;
    }

    private void forSectoralData(Document document, SectoralDataPDFContent sectoralDataPDFContent) throws DocumentException {
        document.add(getDearUserLine(sectoralDataPDFContent.getSectoral().getAutoIndexData().getUserName()));
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(getSummaryReportForLine("Sectoral Performance", sectoralDataPDFContent.getSectoral().getAutoIndexData().getCurrentDate()));
        addIndexData(document, sectoralDataPDFContent.getSectoral().getBankIndexData(), "Bank Index:", "Nifty Bank Index(BANKNIFTY): ", false, false, false);
        addIndexData(document, sectoralDataPDFContent.getSectoral().getBankIndexData(), "IT Index:", "Nifty IT Index: ", false, false, false);
        addIndexData(document, sectoralDataPDFContent.getSectoral().getBankIndexData(), "Metal Index:", "Nifty Metal Index: ", false, false, false);
        addIndexData(document, sectoralDataPDFContent.getSectoral().getBankIndexData(), "Auto Index:", "Nifty Auto Index: ", false, false, false);
        addIndexData(document, sectoralDataPDFContent.getSectoral().getBankIndexData(), "Energy Index:", "Nifty Energy Index: ", false, false, false);
        addIndexData(document, sectoralDataPDFContent.getSectoral().getBankIndexData(), "FMCG Index:", "Nifty FMCG Index: ", false, false, false);
        addIndexData(document, sectoralDataPDFContent.getSectoral().getBankIndexData(), "Pharma Index:", "Nifty Pharma Index: ", false, false, false);
        addIndexData(document, sectoralDataPDFContent.getSectoral().getBankIndexData(), "Reality Index:", "Nifty Realty Index: ", false, false, false);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        addFromFinvendorTeam(document);
    }
}
