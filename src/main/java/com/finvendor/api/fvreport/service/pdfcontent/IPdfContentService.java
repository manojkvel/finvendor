package com.finvendor.api.fvreport.service.pdfcontent;

import com.finvendor.api.fvreport.dto.UserWatchListData;
import com.finvendor.common.infra.pdf.PdfContent;

public interface IPdfContentService<T extends PdfContent> {
   T getContent(UserWatchListData userWatchListData) throws Exception;
}
