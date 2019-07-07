package com.finvendor.common.infra.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.draw.LineSeparator;

/**
 * Utility class for pdfgenerator contents
 * @author Ayush Agrahari
 */
public class PDFContentHelper {
    public static final Font BLUE_BOLD = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLUE);
    public static final Font RED = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.RED);
    public static final Font GREEN = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.GREEN);
    public static final Font GREEN_ITALIC = new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC, BaseColor.GREEN);
    public static final Font BLACK_BOLD = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);

    public static Rectangle getPageBackground() {
        Rectangle layout = new Rectangle(PageSize.A4);
        layout.setBackgroundColor(BaseColor.WHITE);
        return layout;
    }

    /**
     * Get Paragraph
     *
     * @param text            text
     * @param spaceBefore     set the spacing before a paragraph
     * @param spaceAfter      set the spacing after a paragraph
     * @param alignLeft       This sets what side of the page the text is aligned to
     * @param alignRight      This sets what side of the page the text is aligned to
     * @param alignCenter     This sets what side of the page the text is aligned to
     * @param identationLeft  This moves the paragraph content further away from the edges of the page
     * @param identationRight This moves the paragraph content further away from the edges of the page
     * @return
     */
    public static Paragraph getParagraph(String text, int spaceBefore, int spaceAfter, int alignLeft, int alignRight, int alignCenter,
            int identationLeft, int identationRight) {
        Paragraph textParagraph = new Paragraph(text);
        textParagraph.setSpacingAfter(spaceAfter);
        return textParagraph;
    }

    public static Paragraph getParagraph(String text, int spaceAfter) {
        Paragraph textParagraph = new Paragraph(text);
        textParagraph.setSpacingAfter(spaceAfter);
        return textParagraph;
    }

    public static Paragraph getParagraph(Chunk[] chunks, int spaceAfter) {
        Paragraph chunkParagraph = new Paragraph();
        chunkParagraph.setSpacingAfter(spaceAfter);
        for (Chunk chnk : chunks) {
            chunkParagraph.add(chnk);
        }
        return chunkParagraph;
    }

    public static Paragraph getParagraph(Object[] objs) {
        Paragraph chunkParagraph = new Paragraph();
        for (Object obj : objs) {
            if (obj instanceof Chunk)
                chunkParagraph.add((Chunk) obj);
            else if (obj instanceof Anchor) {
                chunkParagraph.add((Anchor) obj);
            }
        }
        return chunkParagraph;
    }

    public static List getOrderedList(Paragraph[] paras) {
        List orderedList = new List(com.itextpdf.text.List.ORDERED);
        for (Paragraph para : paras) {
            orderedList.add(para);
        }
        return orderedList;
    }

    public static LineSeparator getLineSeparator(){
        return new LineSeparator(0.5f, 100, null, 0, -5);
    }
}
