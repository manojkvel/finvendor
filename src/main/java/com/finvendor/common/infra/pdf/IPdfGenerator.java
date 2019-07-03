package com.finvendor.common.infra.pdf;

import java.io.File;

/**
 * @author ayush agrahari
 */
public interface IPdfGenerator {
   boolean generate(PdfContent pdfContent, String file) throws Exception;
}
