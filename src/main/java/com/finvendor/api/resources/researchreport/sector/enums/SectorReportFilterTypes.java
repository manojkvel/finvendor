package com.finvendor.api.resources.researchreport.sector.enums;

public enum SectorReportFilterTypes {
    COUNTRY("country"),
    SECTOR_TYPE("sectorType"),
    SECTOR_SUB_TYPE("sectorSubType"),
    ANALYTST_TYPE("analystType"),
    RESEARCHED_BY("researchedBy"),
    RESEARCH_DATE("researchDate"),
    REPORT_TONE("reportTone"),
    REPORT_FREQUENCY("reportFrequency"),
    REPORT_NAME("reportName"),
    OTHERS("others"),
    ;

    private String type;

    SectorReportFilterTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
