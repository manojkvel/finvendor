package com.finvendor.api.report.service.pdfgenerator;

import com.finvendor.api.report.dto.financials.Financials;
import com.finvendor.api.report.dto.financials.FinancialsQuarterly;
import com.finvendor.api.report.dto.financials.FinancialsYearly;
import com.finvendor.common.infra.pdf.PDFContent;
import com.finvendor.common.infra.pdf.PDFContentHelper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static com.finvendor.common.infra.pdf.PDFContentHelper.BLACK_BOLD;
import static com.finvendor.common.infra.pdf.PDFContentHelper.BLUE_BOLD;

@Transactional
@Service
public class FinancialsPDFGenerator extends AbstractPDFGenerator {

    private static void insertCell(PdfPTable table, String text, int align, int colspan, Font font) {
        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        if(text.equals("CompanyName")||text.equals("Period")||text.equals("Revenue")||text.equals("OperatingProfitMargin")||
                text.equals("ProfitAfterTax")||text.equals("EPS")||text.equals("NetOperatingCashFlow")||
                text.equals("ROE")) {
            cell.setBackgroundColor(BaseColor.CYAN);
        }
        //set the cell alignment
        cell.setHorizontalAlignment(align);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);
        //in case there is no text and you wan to create an empty row
        if(text.trim().equalsIgnoreCase("")){
            cell.setMinimumHeight(10f);
        }
        //add the call to the table
        table.addCell(cell);
    }

    @Override
    public boolean generate(PDFContent pdfContent, String file) throws Exception {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            if (pdfContent instanceof Financials) {
                Financials financials = (Financials) pdfContent;

                document.add(getDearUserLine(financials.getUserName()));
                document.add(Chunk.NEWLINE);
                document.add(getCompanyFinancialResultSummaryNSELine());
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                document.add(quarterlyResult());
                document.add(new LineSeparator(1.0f, 100, null, 0, -5));
                document.add(Chunk.NEWLINE);
                document.add(getQuarterlyTable(financials.getFinancialsQuarterly()));

                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                document.add(annualResult());
                document.add(new LineSeparator(1.0f, 100, null, 0, -5));
                document.add(Chunk.NEWLINE);
                document.add(getYearlyTable(financials.getFinancialsYearly()));




                Paragraph from = new Paragraph();
                from.add(new Chunk("From", BLACK_BOLD));

                Anchor finvendorTeamAnchor = new Anchor("Finvendor Team", BLUE_BOLD);
                finvendorTeamAnchor.setReference("https://finvendor.com");
                Paragraph finvendorTeam = new Paragraph();
                finvendorTeam.add(finvendorTeamAnchor);
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                document.add(from);
                document.add(finvendorTeam);
            }
        } catch (IOException | DocumentException e) {
            throw new Exception(e);
        } finally {
            document.close();
        }
        return true;
    }

    private Paragraph annualResult() {
        Chunk c1 = new Chunk("Annual Result",BLUE_BOLD);
        return PDFContentHelper.getParagraph(new Chunk[] { c1 }, 10);
    }

    private Paragraph quarterlyResult() {
        Chunk c1 = new Chunk("Quarterly Result",BLUE_BOLD);
        return PDFContentHelper.getParagraph(new Chunk[] { c1 }, 10);
    }

    private Paragraph getCompanyFinancialResultSummaryNSELine() {
        Chunk c1 = new Chunk("Company Financial Result Summary");
        return PDFContentHelper.getParagraph(new Chunk[] { c1 }, 10);
    }

    private Paragraph getQuarterlyTable(List<FinancialsQuarterly> financialsQuarterlyList){
        //special font sizes
        Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
        Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN, 12);
        //specify column widths
        float[] columnWidths = { 5f, 2f, 2f, 2f, 2f, 2f };
        //create PDF table with the given widths
        PdfPTable table = new PdfPTable(columnWidths);
        // set table width a percentage of the page width
        table.setWidthPercentage(90f);

        //insert column headings
        insertCell(table, "CompanyName", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "Period", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "Revenue", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "OperatingProfitMargin", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "ProfitAfterTax", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "EPS", Element.ALIGN_CENTER, 1, bfBold12);
        table.setHeaderRows(1);

        //just some random data to fill
        for(FinancialsQuarterly financialsQuarterly:financialsQuarterlyList){
            insertCell(table, financialsQuarterly.getCompanyName(), Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, financialsQuarterly.getPeriod(), Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, financialsQuarterly.getRevenue(), Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, financialsQuarterly.getOperatingProfitMargin(), Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, financialsQuarterly.getProfitAfterTax(), Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, financialsQuarterly.getEps(), Element.ALIGN_LEFT, 1, bf12);
        }

        //Add content to the document using Table objects.
        Paragraph t=new Paragraph();
        t.add(table);
        return t;
    }

    private Paragraph getYearlyTable(List<FinancialsYearly> financialsYearlyList){
        //special font sizes
        Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
        Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN, 12);
        //specify column widths
        float[] columnWidths = { 5f, 2f, 2f, 2f, 2f, 2f, 2f, 2f};
        //create PDF table with the given widths
        PdfPTable table = new PdfPTable(columnWidths);
        // set table width a percentage of the page width
        table.setWidthPercentage(90f);

        //insert column headings
        insertCell(table, "CompanyName", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "Period", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "Revenue", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "OperatingProfitMargin", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "ProfitAfterTax", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "EPS", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "NetOperatingCashFlow", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "ROE", Element.ALIGN_CENTER, 1, bfBold12);
        table.setHeaderRows(1);

        //just some random data to fill
        for(FinancialsYearly financialsYearly:financialsYearlyList) {
            insertCell(table, financialsYearly.getCompanyName(), Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, financialsYearly.getPeriod(), Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, financialsYearly.getRevenue(), Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, financialsYearly.getOperatingProfitMargin(), Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, financialsYearly.getProfitAfterTax(), Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, financialsYearly.getEps(), Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, financialsYearly.getNetOperatingCashFlow(), Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, financialsYearly.getRoe(), Element.ALIGN_LEFT, 1, bf12);
        }

        //Add content to the document using Table objects.
        Paragraph t=new Paragraph();
        t.add(table);
        return t;
    }
}
