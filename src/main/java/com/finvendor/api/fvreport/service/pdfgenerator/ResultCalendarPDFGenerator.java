package com.finvendor.api.fvreport.service.pdfgenerator;

import com.finvendor.api.fvreport.dto.resultCalendar.ResultCalendar;
import com.finvendor.api.fvreport.dto.resultCalendar.ResultCalendarPDFContent;
import com.finvendor.common.infra.pdf.PDFContent;
import com.finvendor.common.infra.pdf.PDFContentHelper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.List;

import static com.finvendor.common.infra.pdf.PDFContentHelper.BLACK_BOLD;
import static com.finvendor.common.infra.pdf.PDFContentHelper.BLUE_BOLD;

@Transactional
@Service
public class ResultCalendarPDFGenerator extends AbstractPDFGenerator {

    public static void main(String[] args) {
        //special font sizes
        Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
        Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN, 12);
        DecimalFormat df = new DecimalFormat("0.00");
        try {
            //Create Document instance.
            Document document = new Document(PageSize.A4);

            //Create OutputStream instance.
            OutputStream outputStream =
                    new FileOutputStream(new File("D:\\TestTableFile.pdf"));

            //Create PDFWriter instance.
            PdfWriter.getInstance(document, outputStream);

            //Open the document.
            document.open();

            //specify column widths
            float[] columnWidths = {1.5f, 2f};
            //create PDF table with the given widths
            PdfPTable table = new PdfPTable(columnWidths);
            // set table width a percentage of the page width
            table.setWidthPercentage(90f);

            //insert column headings
            insertCell(table, "Date", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "CompanyName", Element.ALIGN_CENTER, 1, bfBold12);
            table.setHeaderRows(1);

            //just some random data to fill
            for(int x=1; x<5; x++){
                insertCell(table, "10010" + x, Element.ALIGN_CENTER, 1, bf12);
                insertCell(table, "ABC00" + x, Element.ALIGN_CENTER, 1, bf12);
            }

            //Add content to the document using Table objects.
            Paragraph t=new Paragraph();
            t.add(new Chunk("This Week:"));
            t.add(table);
            document.add(t);

            //Close document and outputStream.
            document.close();
            outputStream.close();

            System.out.println("Pdf created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertCell(PdfPTable table, String text, int align, int colspan, Font font) {
        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        if(text.contains("Date")||text.contains("Company")) {
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
            if (pdfContent instanceof ResultCalendarPDFContent) {
                ResultCalendarPDFContent resultCalendarPDFContent = (ResultCalendarPDFContent) pdfContent;
                List<ResultCalendar> resultCalendars = resultCalendarPDFContent.getResultCalendar();

                document.add(getDearUserLine(resultCalendarPDFContent.getUserName()));
                document.add(Chunk.NEWLINE);
                document.add(getFinancialResultCalendarForNSELine());
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                document.add(getThisWeekLine());
                document.add(new LineSeparator(1.0f, 100, null, 0, -5));
                document.add(Chunk.NEWLINE);
                document.add(getTable(resultCalendars));

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

    private Paragraph getDearUserLine(String userName) {
        Chunk c1 = new Chunk("Dear ");
        Chunk c2 = new Chunk(userName, BLUE_BOLD);
        return PDFContentHelper.getParagraph(new Chunk[] { c1, c2 }, 10);
    }

    private Paragraph getFinancialResultCalendarForNSELine() {
        Chunk c1 = new Chunk("Financial Results Calendar for NSE listed stocks: ");
        return PDFContentHelper.getParagraph(new Chunk[] { c1 }, 10);
    }

    private Paragraph getThisWeekLine() {
        Chunk c1 = new Chunk("This Week: ");
        return PDFContentHelper.getParagraph(new Chunk[] { c1 }, 10);
    }

    private Paragraph getTable(List<ResultCalendar> resultCalendars){
        //special font sizes
        Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
        Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN, 12);
        //specify column widths
        float[] columnWidths = {1.5f, 2f};
        //create PDF table with the given widths
        PdfPTable table = new PdfPTable(columnWidths);
        // set table width a percentage of the page width
        table.setWidthPercentage(90f);

        //insert column headings
        insertCell(table, "Date", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "CompanyName", Element.ALIGN_CENTER, 1, bfBold12);
        table.setHeaderRows(1);

        //just some random data to fill
        for(ResultCalendar resultCalendar:resultCalendars){
            insertCell(table, resultCalendar.getDate(), Element.ALIGN_CENTER, 1, bf12);
            insertCell(table, resultCalendar.getCompanyName(), Element.ALIGN_RIGHT, 1, bf12);
        }

        //Add content to the document using Table objects.
        Paragraph t=new Paragraph();
        t.add(table);
        return t;
    }
}
