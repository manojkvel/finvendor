package com.finvendor.api.fvreport.service.pdfgenerator;

import com.finvendor.api.fvreport.dto.financials.Financials;
import com.finvendor.common.infra.pdf.PDFContent;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.io.IOException;

@Transactional
@Service
public class FinancialsPDFGenerator extends AbstractPDFGenerator {

    @Override
    public boolean generate(PDFContent pdfContent, String file) throws Exception {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            if (pdfContent instanceof Financials) {
                Financials financials = (Financials) pdfContent;
            }
        } catch (IOException | DocumentException e) {
            throw new Exception(e);
        } finally {
            document.close();
        }
        return true;
    }
}
