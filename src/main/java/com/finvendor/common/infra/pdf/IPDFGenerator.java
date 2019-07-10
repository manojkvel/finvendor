package com.finvendor.common.infra.pdf;

/**
 * @author ayush agrahari
 */
public interface IPDFGenerator {
   boolean generate(PDFContent pdfContent, String file) throws Exception;
}
