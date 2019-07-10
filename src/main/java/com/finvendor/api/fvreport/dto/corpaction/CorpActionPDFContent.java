package com.finvendor.api.fvreport.dto.corpaction;

import com.finvendor.common.infra.pdf.PDFContent;

import java.util.List;

public class CorpActionPDFContent extends PDFContent {
    private String userName;
    private List<CorpAction> corpActions;

    public CorpActionPDFContent(String userName, List<CorpAction> corpActions) {
        super();
        this.userName = userName;
        this.corpActions = corpActions;
    }

    public List<CorpAction> getCorpActions() {
        return corpActions;
    }

    public String getUserName() {
        return userName;
    }
}
