package com.finvendor.api.report.service.pdfgenerator;

import com.finvendor.api.report.dto.marketdatacontent.MarketIndexData;
import com.finvendor.api.report.dto.marketdatacontent.TopGainers;
import com.finvendor.api.report.dto.marketdatacontent.TopLoosers;
import com.finvendor.common.infra.pdf.IPDFGenerator;
import com.finvendor.common.infra.pdf.PDFContentHelper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.draw.LineSeparator;

import static com.finvendor.common.infra.pdf.PDFContentHelper.*;

/**
 * @author ayush on 10/11/2019
 */
public abstract class AbstractPDFGenerator implements IPDFGenerator {

    void addFromFinvendorTeam(Document document) throws DocumentException {
        Paragraph from = new Paragraph();
        from.add(new Chunk("From", BLACK_BOLD));

        Anchor finvendorTeamAnchor = new Anchor("Finvendor Team", BLUE);
        finvendorTeamAnchor.setReference("https://finvendor.com");
        Paragraph finvendorTeam = new Paragraph();
        finvendorTeam.add(finvendorTeamAnchor);
        List fromOrderedList = new List(List.ORDERED);
        fromOrderedList.add(new ListItem(from));
        fromOrderedList.add(new ListItem(finvendorTeamAnchor));

        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(from);
        document.add(finvendorTeam);
    }

    void addIndexData(Document document, MarketIndexData marketIndexData, String heading, String headingType, boolean isSummary,
            boolean isAnalytics, boolean isPE) throws DocumentException {
        Paragraph broaderBenchMarkIndex = new Paragraph();
        broaderBenchMarkIndex.add(new Chunk(heading, BLACK_BOLD));

        Paragraph nifty50IndexLine = new Paragraph();
        nifty50IndexLine.setSpacingAfter(10);
        nifty50IndexLine.add(new Chunk(headingType));
        nifty50IndexLine.add(new Chunk(marketIndexData.getNifty50MappingKeyword(), BLUE));

        Paragraph consecutiveLine = new Paragraph();
        consecutiveLine.setSpacingAfter(10);
        consecutiveLine.add(new Chunk(marketIndexData.getConsecutiveMsg()));
        //consecutiveLine.add(new Chunk("3rd", BLUE));
        //consecutiveLine.add(new Chunk(" consecutive day when NIFTY closed higher"));

        Paragraph closed = new Paragraph();
        closed.setSpacingAfter(10);
        closed.add(new Chunk("Index closed"));
        closed.add(new Chunk(marketIndexData.getCloseBy() + " by "));
        closed.add(new Chunk(marketIndexData.getCloseAt() + " (%" + marketIndexData.getPercentChangeStr() + ")", BLUE));
        closed.add(new Chunk(" at "));
        closed.add(new Chunk(marketIndexData.getCloseAt(), BLUE));
        closed.add(new Chunk(" level. Index opened at "));
        closed.add(new Chunk(marketIndexData.getIndexOpen(), BLUE));
        closed.add(new Chunk(" . Day high was "));
        closed.add(new Chunk(marketIndexData.getDayHigh(), BLUE));
        closed.add(new Chunk(" and day low was "));
        closed.add(new Chunk(marketIndexData.getDayLow(), BLUE));

        Paragraph higherLooserUnchangedLine = new Paragraph();
        higherLooserUnchangedLine.setSpacingAfter(10);
        higherLooserUnchangedLine.add(new Chunk(marketIndexData.getGainer(), BLUE));
        higherLooserUnchangedLine.add(new Chunk(" stocks of index closed higher, while "));
        higherLooserUnchangedLine.add(new Chunk(marketIndexData.getLooser(), BLUE));
        higherLooserUnchangedLine.add(new Chunk(" stocks closed lower and "));
        higherLooserUnchangedLine.add(new Chunk(marketIndexData.getUnchanged(), BLUE));
        higherLooserUnchangedLine.add(new Chunk(" remained unchanged."));

        //NIFTY is hovering at P/E
        Paragraph broaderBenchMark_PE = new Paragraph();
        broaderBenchMark_PE.setSpacingAfter(10);
        broaderBenchMark_PE.add(new Chunk("NIFTY is hovering at P/E of "));
        broaderBenchMark_PE.add(new Chunk(marketIndexData.getPeStr(), BLUE));
        //broaderBenchMark_PE.add(new Chunk(" (1 YR average P/E is " + 0 + ")"));

        //Top gainers were
        Paragraph broaderBenchMark_top_gainers = new Paragraph();
        broaderBenchMark_top_gainers.setSpacingAfter(10);
        broaderBenchMark_top_gainers.add(new Chunk("Top gainers were: "));
        StringBuilder sb = new StringBuilder();
        java.util.List<TopGainers> topGainers = marketIndexData.getTopGainers();
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
        java.util.List<TopLoosers> topLosers = marketIndexData.getTopLoosers();
        for (int i = 0; i < topLosers.size(); i++) {
            TopLoosers topLooser1 = topLosers.get(i);
            sb.append(topLooser1.getCompanyName()).append(" (").append(topLooser1.get_percentChange()).append("%)");

            if (i == topLosers.size() - 1) {
                sb.append(" ");
            }
            else {
                sb.append(", ");
            }
            broaderBenchMark_top_looser.add(new Chunk(sb.toString(), RED));
            sb.setLength(0);

        }

        List orderedList = new List(List.ORDERED);
        orderedList.add(new ListItem(nifty50IndexLine));
        orderedList.add(new ListItem(consecutiveLine));
        if (isSummary) {
            orderedList.add(new ListItem(closed));
        }
        if (isAnalytics) {
            orderedList.add(new ListItem(higherLooserUnchangedLine));
        }
        if (isPE) {
            orderedList.add(new ListItem(broaderBenchMark_PE));
        }
        orderedList.add(new ListItem(broaderBenchMark_top_gainers));
        orderedList.add(new ListItem(broaderBenchMark_top_looser));

        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(broaderBenchMarkIndex);
        document.add(new LineSeparator(0.5f, 100, null, 0, -5));
        document.add(Chunk.NEWLINE);
        document.add(orderedList);
    }

    Paragraph getDearUserLine(String userName) {
        Chunk c1 = new Chunk("Dear ");
        Chunk c2 = new Chunk(userName, BLUE);
        return PDFContentHelper.getParagraph(new Chunk[] { c1, c2 }, 10);
    }

    Paragraph getSummaryReportForLine(String topic, String currentDate) {
        Chunk c1 = new Chunk(topic + " Summary Report for:  ");
        Chunk c2 = new Chunk(currentDate, BLUE);
        return PDFContentHelper.getParagraph(new Chunk[] { c1, c2 }, 10);
    }
}
