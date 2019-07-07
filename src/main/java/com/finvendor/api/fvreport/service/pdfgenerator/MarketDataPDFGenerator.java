package com.finvendor.api.fvreport.service.pdfgenerator;

import com.finvendor.api.fvreport.dto.marketdatacontent.MarketDataContent;
import com.finvendor.api.fvreport.dto.marketdatacontent.MarketIndexData;
import com.finvendor.api.fvreport.dto.marketdatacontent.TopGainers;
import com.finvendor.api.fvreport.dto.marketdatacontent.TopLoosers;
import com.finvendor.common.infra.pdf.PDFContent;
import com.finvendor.common.infra.pdf.PDFContentHelper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.io.IOException;

import static com.finvendor.common.infra.pdf.PDFContentHelper.*;

@Transactional
@Service
public class MarketDataPDFGenerator extends AbstractPDFGenerator {

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
            throw new Exception(e);
        } finally {

            document.close();
        }

        return true;
    }

    private void forMarketData(Document document, MarketDataContent marketDataContent) throws DocumentException {
        //Broader Index
        MarketIndexData broaderIndexData = marketDataContent.getBroaderIndexData();
        Paragraph broaderIndex_dearUserLine = getDearUserLine(broaderIndexData.getUserName());
        Paragraph broaderIndex_mktSummaryReportForLine = getMktSummaryReportForLine(broaderIndexData.getCurrentDate());

        Paragraph broaderBenchMarkIndex = new Paragraph();
        broaderBenchMarkIndex.add(new Chunk("Broader Benchmark Index:", BLACK_BOLD));

        Paragraph nifty50IndexLine = new Paragraph();
        nifty50IndexLine.setSpacingAfter(10);
        nifty50IndexLine.add(new Chunk("Nifty 50 Index(NIFTY): "));
        nifty50IndexLine.add(new Chunk(broaderIndexData.getNifty50Index(), BLUE_BOLD));

        Paragraph consecutiveLine = new Paragraph();
        consecutiveLine.setSpacingAfter(10);
        consecutiveLine.add(new Chunk("This is "));
        consecutiveLine.add(new Chunk("3rd", BLUE_BOLD));
        consecutiveLine.add(new Chunk(" consecutive day when NIFTY closed higher"));

        Paragraph closed = new Paragraph();
        closed.setSpacingAfter(10);
        closed.add(new Chunk("Index closed"));
        closed.add(new Chunk(" up by "));
        closed.add(new Chunk(broaderIndexData.getCloseBy() + " (%" + broaderIndexData.getUpDown() + ")", BLUE_BOLD));
        closed.add(new Chunk(" at "));
        closed.add(new Chunk(broaderIndexData.getCloseAt(), BLUE_BOLD));
        closed.add(new Chunk(" level. Index opened at "));
        closed.add(new Chunk(broaderIndexData.getIndexOpen(), BLUE_BOLD));
        closed.add(new Chunk(" . Day high was "));
        closed.add(new Chunk(broaderIndexData.getDayHigh(), BLUE_BOLD));
        closed.add(new Chunk(" and day low was "));
        closed.add(new Chunk(broaderIndexData.getDayLow(), BLUE_BOLD));

        Paragraph higherLooserUnchangedLine = new Paragraph();
        higherLooserUnchangedLine.setSpacingAfter(10);
        higherLooserUnchangedLine.add(new Chunk(broaderIndexData.getGainer(), BLUE_BOLD));
        higherLooserUnchangedLine.add(new Chunk(" stocks of index closed higher, while "));
        higherLooserUnchangedLine.add(new Chunk(broaderIndexData.getLooser(), BLUE_BOLD));
        higherLooserUnchangedLine.add(new Chunk(" stocks closed lower and "));
        higherLooserUnchangedLine.add(new Chunk(broaderIndexData.getUnchanged(), BLUE_BOLD));
        higherLooserUnchangedLine.add(new Chunk(" remained unchanged."));

        //NIFTY is hovering at P/E
        Paragraph broaderBenchMark_PE = new Paragraph();
        broaderBenchMark_PE.setSpacingAfter(10);
        broaderBenchMark_PE.add(new Chunk("NIFTY is hovering at P/E of "));
        broaderBenchMark_PE.add(new Chunk("NA", BLUE_BOLD));
        broaderBenchMark_PE.add(new Chunk(" (1 YR average P/E is " + 0 + ")"));

        //Top gainers were
        Paragraph broaderBenchMark_top_gainers = new Paragraph();
        broaderBenchMark_top_gainers.setSpacingAfter(10);
        broaderBenchMark_top_gainers.add(new Chunk("Top gainers were: "));
        StringBuilder sb = new StringBuilder();
        java.util.List<TopGainers> topGainers = broaderIndexData.getTopGainers();
        for (int i = 0; i < topGainers.size(); i++) {
            TopGainers topGainers1 = topGainers.get(i);
            sb.append(topGainers1.getCompanyName()).append(" (").append(topGainers1.get_percentChange()).append("%)");
            if (i == topGainers.size() - 1) {
                sb.append(" ");
            }
            else {
                sb.append(", ");
            }
            broaderBenchMark_top_gainers.add(new Chunk(sb.toString(), GREEN));
            sb.setLength(0);
        }

        //Top losers were
        Paragraph broaderBenchMark_top_looser = new Paragraph();
        broaderBenchMark_top_looser.setSpacingAfter(10);
        broaderBenchMark_top_looser.add(new Chunk("Top losers were: "));
        sb = new StringBuilder();
        java.util.List<TopLoosers> topLoosers = broaderIndexData.getTopLoosers();
        for (int i = 0; i < topLoosers.size(); i++) {
            TopLoosers topLooser1 = topLoosers.get(i);
            sb.append(topLooser1.getCompanyName()).append(" (").append(topLooser1.get_percentChange()).append("%)");

            if (i == topLoosers.size() - 1) {
                sb.append(" ");
            }
            else {
                sb.append(", ");
            }
            broaderBenchMark_top_looser.add(new Chunk(sb.toString(), RED));
            sb.setLength(0);

        }

        com.itextpdf.text.List orderedList = new com.itextpdf.text.List(com.itextpdf.text.List.ORDERED);
        orderedList.add(new ListItem(nifty50IndexLine));
        orderedList.add(new ListItem(consecutiveLine));
        orderedList.add(new ListItem(closed));
        orderedList.add(new ListItem(higherLooserUnchangedLine));
        orderedList.add(new ListItem(broaderBenchMark_PE));
        orderedList.add(new ListItem(broaderBenchMark_top_gainers));
        orderedList.add(new ListItem(broaderBenchMark_top_looser));

        document.add(broaderIndex_dearUserLine);
        document.add(Chunk.NEWLINE);
        document.add(broaderIndex_mktSummaryReportForLine);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(broaderBenchMarkIndex);
        document.add(new LineSeparator(0.5f, 100, null, 0, -5));
        document.add(Chunk.NEWLINE);
        document.add(orderedList);

        //MidCap Index
        MarketIndexData midCap_IndexData = marketDataContent.getMidCapIndexData();

        Paragraph midCapIndex = new Paragraph();
        midCapIndex.add(new Chunk("MidCap Index:", BLACK_BOLD));

        Paragraph niftyMidCapIndexLine = new Paragraph();
        niftyMidCapIndexLine.setSpacingAfter(10);
        niftyMidCapIndexLine.add(new Chunk("Nifty Midcap 100 Index: "));
        niftyMidCapIndexLine.add(new Chunk(midCap_IndexData.getNifty50Index(), BLUE_BOLD));

        Paragraph midcap_consecutiveLine = new Paragraph();
        midcap_consecutiveLine.setSpacingAfter(10);
        midcap_consecutiveLine.add(new Chunk("This is "));
        midcap_consecutiveLine.add(new Chunk("3rd", BLUE_BOLD));
        midcap_consecutiveLine.add(new Chunk(" consecutive day when NIFTY closed higher"));

        Paragraph midCap_closed = new Paragraph();
        midCap_closed.setSpacingAfter(10);
        midCap_closed.add(new Chunk("Index closed"));
        midCap_closed.add(new Chunk(" up by "));
        midCap_closed.add(new Chunk(midCap_IndexData.getCloseBy() + " (%" + midCap_IndexData.getUpDown() + ")", BLUE_BOLD));
        midCap_closed.add(new Chunk(" at "));
        midCap_closed.add(new Chunk(midCap_IndexData.getCloseAt(), BLUE_BOLD));
        midCap_closed.add(new Chunk(" level. Index opened at "));
        midCap_closed.add(new Chunk(midCap_IndexData.getIndexOpen(), BLUE_BOLD));
        midCap_closed.add(new Chunk(" . Day high was "));
        midCap_closed.add(new Chunk(midCap_IndexData.getDayHigh(), BLUE_BOLD));
        midCap_closed.add(new Chunk(" and day low was "));
        midCap_closed.add(new Chunk(midCap_IndexData.getDayLow(), BLUE_BOLD));

        Paragraph midCap_higherLooserUnchangedLine = new Paragraph();
        midCap_higherLooserUnchangedLine.setSpacingAfter(10);
        midCap_higherLooserUnchangedLine.add(new Chunk(midCap_IndexData.getGainer(), BLUE_BOLD));
        midCap_higherLooserUnchangedLine.add(new Chunk(" stocks of index closed higher, while "));
        midCap_higherLooserUnchangedLine.add(new Chunk(midCap_IndexData.getLooser(), BLUE_BOLD));
        midCap_higherLooserUnchangedLine.add(new Chunk(" stocks closed lower and "));
        midCap_higherLooserUnchangedLine.add(new Chunk(midCap_IndexData.getUnchanged(), BLUE_BOLD));
        midCap_higherLooserUnchangedLine.add(new Chunk(" remained unchanged."));

        //NIFTY Mid is hovering at P/E
        Paragraph midcap_PE = new Paragraph();
        midcap_PE.setSpacingAfter(10);
        midcap_PE.add(new Chunk("NIFTY Midcap 100 Index is hovering at P/E of "));
        midcap_PE.add(new Chunk("NA", BLUE_BOLD));
        midcap_PE.add(new Chunk(" (1 YR average P/E is " + 0 + ")"));

        //Mid Cap Top gainers were
        Paragraph midCap_top_gainers = new Paragraph();
        midCap_top_gainers.setSpacingAfter(10);
        midCap_top_gainers.add(new Chunk("Top gainers were: "));
        sb = new StringBuilder();
        java.util.List<TopGainers> gainers = midCap_IndexData.getTopGainers();
        for (int i = 0; i < gainers.size(); i++) {
            TopGainers topGainers1 = gainers.get(i);
            sb.append(topGainers1.getCompanyName()).append(" (").append(topGainers1.get_percentChange()).append("%)");
            if (i == gainers.size() - 1) {
                sb.append(" ");
            }
            else {
                sb.append(", ");
            }
            midCap_top_gainers.add(new Chunk(sb.toString(), GREEN));
            sb.setLength(0);
        }

        //Top losers were
        Paragraph midCap_top_looser = new Paragraph();
        midCap_top_looser.setSpacingAfter(10);
        midCap_top_looser.add(new Chunk("Top losers were: "));
        sb = new StringBuilder();
        java.util.List<TopLoosers> loosers = midCap_IndexData.getTopLoosers();
        for (int i = 0; i < loosers.size(); i++) {
            TopLoosers topLooser1 = loosers.get(i);
            sb.append(topLooser1.getCompanyName()).append(" (").append(topLooser1.get_percentChange()).append("%)");

            if (i == loosers.size() - 1) {
                sb.append(" ");
            }
            else {
                sb.append(", ");
            }
            midCap_top_looser.add(new Chunk(sb.toString(), RED));
            sb.setLength(0);
        }

        com.itextpdf.text.List midCapOrderedList = new com.itextpdf.text.List(com.itextpdf.text.List.ORDERED);
        midCapOrderedList.add(new ListItem(niftyMidCapIndexLine));
        midCapOrderedList.add(new ListItem(midcap_consecutiveLine));
        midCapOrderedList.add(new ListItem(midCap_closed));
        midCapOrderedList.add(new ListItem(midCap_higherLooserUnchangedLine));
        midCapOrderedList.add(new ListItem(midcap_PE));
        midCapOrderedList.add(new ListItem(midCap_top_gainers));
        midCapOrderedList.add(new ListItem(midCap_top_looser));

        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(midCapIndex);
        document.add(new LineSeparator(0.5f, 100, null, 0, -5));
        document.add(Chunk.NEWLINE);
        document.add(midCapOrderedList);

        //SmallCap Index
        MarketIndexData smallCap_IndexData = marketDataContent.getSmallCapIndexData();
        Paragraph smallCapIndex = new Paragraph();
        smallCapIndex.add(new Chunk("SmallCap Index: ", BLACK_BOLD));

        Paragraph niftySmallCapIndexLine = new Paragraph();
        niftySmallCapIndexLine.setSpacingAfter(10);
        niftySmallCapIndexLine.add(new Chunk("Nifty Smallcap 100 Index: "));
        niftySmallCapIndexLine.add(new Chunk(smallCap_IndexData.getNifty50Index(), BLUE_BOLD));

        Paragraph smallcap_consecutiveLine = new Paragraph();
        smallcap_consecutiveLine.setSpacingAfter(10);
        smallcap_consecutiveLine.add(new Chunk("This is "));
        smallcap_consecutiveLine.add(new Chunk("3rd", BLUE_BOLD));
        smallcap_consecutiveLine.add(new Chunk(" consecutive day when NIFTY closed higher"));

        Paragraph smallCap_closed = new Paragraph();
        smallCap_closed.setSpacingAfter(10);
        smallCap_closed.add(new Chunk("Index closed"));
        smallCap_closed.add(new Chunk(" up by "));
        smallCap_closed.add(new Chunk(smallCap_IndexData.getCloseBy() + " (%" + smallCap_IndexData.getUpDown() + ")", BLUE_BOLD));
        smallCap_closed.add(new Chunk(" at "));
        smallCap_closed.add(new Chunk(smallCap_IndexData.getCloseAt(), BLUE_BOLD));
        smallCap_closed.add(new Chunk(" level. Index opened at "));
        smallCap_closed.add(new Chunk(smallCap_IndexData.getIndexOpen(), BLUE_BOLD));
        smallCap_closed.add(new Chunk(" . Day high was "));
        smallCap_closed.add(new Chunk(smallCap_IndexData.getDayHigh(), BLUE_BOLD));
        smallCap_closed.add(new Chunk(" and day low was "));
        smallCap_closed.add(new Chunk(smallCap_IndexData.getDayLow(), BLUE_BOLD));

        Paragraph smallCap_higherLooserUnchangedLine = new Paragraph();
        smallCap_higherLooserUnchangedLine.setSpacingAfter(10);
        smallCap_higherLooserUnchangedLine.add(new Chunk(smallCap_IndexData.getGainer(), BLUE_BOLD));
        smallCap_higherLooserUnchangedLine.add(new Chunk(" stocks of index closed higher, while "));
        smallCap_higherLooserUnchangedLine.add(new Chunk(smallCap_IndexData.getLooser(), BLUE_BOLD));
        smallCap_higherLooserUnchangedLine.add(new Chunk(" stocks closed lower and "));
        smallCap_higherLooserUnchangedLine.add(new Chunk(smallCap_IndexData.getUnchanged(), BLUE_BOLD));
        smallCap_higherLooserUnchangedLine.add(new Chunk(" remained unchanged."));

        //NIFTY SmallCap is hovering at P/E
        Paragraph smallcap_PE = new Paragraph();
        smallcap_PE.setSpacingAfter(10);
        smallcap_PE.add(new Chunk("NIFTY Smallcap 100 Index is hovering at P/E of "));
        smallcap_PE.add(new Chunk("NA", BLUE_BOLD));
        smallcap_PE.add(new Chunk(" (1 YR average P/E is " + 0 + ")"));

        //Mid Cap Top gainers were
        Paragraph smallCap_top_gainers = new Paragraph();
        smallCap_top_gainers.setSpacingAfter(10);
        smallCap_top_gainers.add(new Chunk("Top gainers were: "));
        sb = new StringBuilder();
        java.util.List<TopGainers> smallCap_indexDataTopGainers = smallCap_IndexData.getTopGainers();
        for (int i = 0; i < smallCap_indexDataTopGainers.size(); i++) {
            TopGainers topGainers1 = smallCap_indexDataTopGainers.get(i);
            sb.append(topGainers1.getCompanyName()).append(" (").append(topGainers1.get_percentChange()).append("%)");

            if (i == smallCap_indexDataTopGainers.size() - 1) {
                sb.append(" ");
            }
            else {
                sb.append(", ");
            }
            smallCap_top_gainers.add(new Chunk(sb.toString(), GREEN));
            sb.setLength(0);
        }

        //Top losers were
        Paragraph smallCap_top_looser = new Paragraph();
        smallCap_top_looser.setSpacingAfter(10);
        smallCap_top_looser.add(new Chunk("Top losers were: "));
        sb = new StringBuilder();
        java.util.List<TopLoosers> smallCap_indexDataTopLoosers = smallCap_IndexData.getTopLoosers();
        for (int i = 0; i < smallCap_indexDataTopLoosers.size(); i++) {
            TopLoosers topLooser1 = smallCap_indexDataTopLoosers.get(i);
            sb.append(topLooser1.getCompanyName()).append(" (").append(topLooser1.get_percentChange()).append("%)");
            if (i == smallCap_indexDataTopLoosers.size() - 1) {
                sb.append(" ");
            }
            else {
                sb.append(", ");
            }
            smallCap_top_looser.add(new Chunk(sb.toString(), RED));
            sb.setLength(0);
        }

        com.itextpdf.text.List smallCapOrderedList = new com.itextpdf.text.List(com.itextpdf.text.List.ORDERED);
        smallCapOrderedList.add(new ListItem(niftySmallCapIndexLine));
        smallCapOrderedList.add(new ListItem(smallcap_consecutiveLine));
        smallCapOrderedList.add(new ListItem(smallCap_closed));
        smallCapOrderedList.add(new ListItem(smallCap_higherLooserUnchangedLine));
        smallCapOrderedList.add(new ListItem(smallcap_PE));
        smallCapOrderedList.add(new ListItem(smallCap_top_gainers));
        smallCapOrderedList.add(new ListItem(smallCap_top_looser));

        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(smallCapIndex);
        document.add(new LineSeparator(0.5f, 100, null, 0, -5));
        document.add(Chunk.NEWLINE);
        document.add(smallCapOrderedList);

        Paragraph from = new Paragraph();
        from.add(new Chunk("From", BLACK_BOLD));

        Anchor finvendorTeamAnchor = new Anchor(
                "Finvendor Team", BLUE_BOLD);
        finvendorTeamAnchor.setReference("https://finvendor.com");
        Paragraph finvendorTeam = new Paragraph();
        finvendorTeam.add(finvendorTeamAnchor);
        com.itextpdf.text.List fromOrderedList = new com.itextpdf.text.List(com.itextpdf.text.List.ORDERED);
        fromOrderedList.add(new ListItem(from));
        fromOrderedList.add(new ListItem(finvendorTeamAnchor));

        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(from);
        document.add(finvendorTeam);

    }

    private Paragraph getDearUserLine(String userName) {
        Chunk c1 = new Chunk("Dear ");
        Chunk c2 = new Chunk(userName, BLUE_BOLD);
        return PDFContentHelper.getParagraph(new Chunk[] { c1, c2 }, 10);
    }

    private Paragraph getMktSummaryReportForLine(String currentDate) {
        Chunk c1 = new Chunk("Market Summary Report for:  ");
        Chunk c2 = new Chunk(currentDate, BLUE_BOLD);
        return PDFContentHelper.getParagraph(new Chunk[] { c1, c2 }, 10);
    }
}
