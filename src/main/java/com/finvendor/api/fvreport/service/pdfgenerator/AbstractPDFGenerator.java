package com.finvendor.api.fvreport.service.pdfgenerator;

import com.finvendor.api.fvreport.dto.marketdatacontent.MarketIndexData;
import com.finvendor.api.fvreport.dto.marketdatacontent.TopGainers;
import com.finvendor.api.fvreport.dto.marketdatacontent.TopLoosers;
import com.finvendor.common.infra.pdf.IPDFGenerator;
import com.finvendor.common.infra.pdf.PDFContentHelper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.draw.LineSeparator;

import static com.finvendor.common.infra.pdf.PDFContentHelper.*;

public abstract class AbstractPDFGenerator implements IPDFGenerator {
    protected void addFromFinvendorTeam(Document document) throws DocumentException {
        Paragraph from = new Paragraph();
        from.add(new Chunk("From", BLACK_BOLD));

        Anchor finvendorTeamAnchor = new Anchor("Finvendor Team", BLUE_BOLD);
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

    protected void addIndexData(Document document, MarketIndexData marketIndexData, String heading, String headingType, boolean isSummary,
            boolean isAnalytics, boolean isPE) throws DocumentException {
        Paragraph broaderBenchMarkIndex = new Paragraph();
        broaderBenchMarkIndex.add(new Chunk(heading, BLACK_BOLD));

        Paragraph nifty50IndexLine = new Paragraph();
        nifty50IndexLine.setSpacingAfter(10);
        nifty50IndexLine.add(new Chunk(headingType));
        nifty50IndexLine.add(new Chunk(marketIndexData.getNiftyIndex(), BLUE_BOLD));

        Paragraph consecutiveLine = new Paragraph();
        consecutiveLine.setSpacingAfter(10);
        consecutiveLine.add(new Chunk("This is "));
        consecutiveLine.add(new Chunk("3rd", BLUE_BOLD));
        consecutiveLine.add(new Chunk(" consecutive day when NIFTY closed higher"));

        Paragraph closed = new Paragraph();
        closed.setSpacingAfter(10);
        closed.add(new Chunk("Index closed"));
        closed.add(new Chunk(" up by "));
        closed.add(new Chunk(marketIndexData.getCloseBy() + " (%" + marketIndexData.getUpDown() + ")", BLUE_BOLD));
        closed.add(new Chunk(" at "));
        closed.add(new Chunk(marketIndexData.getCloseAt(), BLUE_BOLD));
        closed.add(new Chunk(" level. Index opened at "));
        closed.add(new Chunk(marketIndexData.getIndexOpen(), BLUE_BOLD));
        closed.add(new Chunk(" . Day high was "));
        closed.add(new Chunk(marketIndexData.getDayHigh(), BLUE_BOLD));
        closed.add(new Chunk(" and day low was "));
        closed.add(new Chunk(marketIndexData.getDayLow(), BLUE_BOLD));

        Paragraph higherLooserUnchangedLine = new Paragraph();
        higherLooserUnchangedLine.setSpacingAfter(10);
        higherLooserUnchangedLine.add(new Chunk(marketIndexData.getGainer(), BLUE_BOLD));
        higherLooserUnchangedLine.add(new Chunk(" stocks of index closed higher, while "));
        higherLooserUnchangedLine.add(new Chunk(marketIndexData.getLooser(), BLUE_BOLD));
        higherLooserUnchangedLine.add(new Chunk(" stocks closed lower and "));
        higherLooserUnchangedLine.add(new Chunk(marketIndexData.getUnchanged(), BLUE_BOLD));
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
        java.util.List<TopLoosers> topLoosers = marketIndexData.getTopLoosers();
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

        List orderedList = new List(List.ORDERED);
        orderedList.add(new ListItem(nifty50IndexLine));
        orderedList.add(new ListItem(consecutiveLine));
        if(isSummary) {
            orderedList.add(new ListItem(closed));
        }
        if(isAnalytics) {
            orderedList.add(new ListItem(higherLooserUnchangedLine));
        }
        if(isPE) {
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

    protected Paragraph getDearUserLine(String userName) {
        Chunk c1 = new Chunk("Dear ");
        Chunk c2 = new Chunk(userName, BLUE_BOLD);
        return PDFContentHelper.getParagraph(new Chunk[] { c1, c2 }, 10);
    }

    protected Paragraph getSummaryReportForLine(String topic, String currentDate) {
        Chunk c1 = new Chunk(topic+" Summary Report for:  ");
        Chunk c2 = new Chunk(currentDate, BLUE_BOLD);
        return PDFContentHelper.getParagraph(new Chunk[] { c1, c2 }, 10);
    }
}
