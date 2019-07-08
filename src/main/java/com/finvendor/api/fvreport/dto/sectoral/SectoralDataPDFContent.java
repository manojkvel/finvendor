package com.finvendor.api.fvreport.dto.sectoral;

import com.finvendor.common.infra.pdf.PDFContent;

public class SectoralDataPDFContent extends PDFContent {
    private Sectoral sectoral;

    public SectoralDataPDFContent(Sectoral sectoral) {
        this.sectoral = sectoral;
    }

    public Sectoral getSectoral() {
        return sectoral;
    }
}
