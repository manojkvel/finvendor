package com.finvendor.common.infra.pdf;

public interface IPDFContentBuilder<E, T extends PDFContent> {
   T buildContent(E userName) throws Exception;
}
