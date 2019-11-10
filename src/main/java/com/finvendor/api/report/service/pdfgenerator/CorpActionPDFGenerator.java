package com.finvendor.api.report.service.pdfgenerator;

import com.finvendor.api.report.dto.corpaction.CorpAction;
import com.finvendor.api.report.dto.corpaction.CorpActionPDFContent;
import com.finvendor.common.infra.pdf.PDFContent;
import com.finvendor.common.infra.pdf.PDFContentHelper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static com.finvendor.common.infra.pdf.PDFContentHelper.BLACK_BOLD;
import static com.finvendor.common.infra.pdf.PDFContentHelper.BLUE;

@Transactional
@Service
public class CorpActionPDFGenerator extends AbstractPDFGenerator {

    private static void insertCell(PdfPTable table, String text, int align, int colspan, Font font) {
        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        if (text.equals("Ticker") || text.equals("Company Name") || text.equals("Purpose") || text.equals("Face Value") || text.equals("Ex-Date")
                || text.equals("Record Date")) {
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
            if (pdfContent instanceof CorpActionPDFContent) {
                CorpActionPDFContent corpActionPDFContent = (CorpActionPDFContent) pdfContent;
                List<CorpAction> resultCalendars = corpActionPDFContent.getCorpActions();

                document.add(getDearUserLine(corpActionPDFContent.getUserName()));
                document.add(Chunk.NEWLINE);
                document.add(getCorpActionOnNSELine());
                document.add(Chunk.NEWLINE);
                document.add(getTable(resultCalendars));

                Paragraph from = new Paragraph();
                from.add(new Chunk("From", BLACK_BOLD));

                Anchor finvendorTeamAnchor = new Anchor("Finvendor Team", BLUE);
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


    private Paragraph getCorpActionOnNSELine() {
        Chunk c1 = new Chunk("Corporate Actions on NSE listed stocks: ");
        return PDFContentHelper.getParagraph(new Chunk[] { c1 }, 10);
    }

    private Paragraph getTable(List<CorpAction> resultCalendars ){
        //special font sizes
        Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
        Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN, 12);
        //specify column widths
        float[] columnWidths = { 5f, 6f, 2f, 3f, 3f };
        //create PDF table with the given widths
        PdfPTable table = new PdfPTable(columnWidths);
        // set table width a percentage of the page width
        table.setWidthPercentage(90f);

        //insert column headings
        insertCell(table, "Company Name", Element.ALIGN_CENTER, 1, bfBold12);
//        insertCell(table, "Ticker", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "Purpose", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "Face Value", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "Ex-Date", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "Record Date", Element.ALIGN_CENTER, 1, bfBold12);
        table.setHeaderRows(1);

        //just some random data to fill
        for(CorpAction corpAction:resultCalendars){
            insertCell(table, corpAction.getCompanyName(), Element.ALIGN_LEFT, 1, bf12);
//            insertCell(table, corpAction.getTicker(), Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, corpAction.getPurpose(), Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, corpAction.getFaceValue(), Element.ALIGN_CENTER, 1, bf12);
            insertCell(table, corpAction.getExDate(), Element.ALIGN_CENTER, 1, bf12);
            insertCell(table, corpAction.getRecordDate(), Element.ALIGN_CENTER, 1, bf12);
        }

        //Add content to the document using Table objects.
        Paragraph t=new Paragraph();
        t.add(table);
        return t;
    }
}
