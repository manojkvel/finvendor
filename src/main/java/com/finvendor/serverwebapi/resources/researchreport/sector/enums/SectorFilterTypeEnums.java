package com.finvendor.serverwebapi.resources.researchreport.sector.enums;

public enum SectorFilterTypeEnums {
    SECTOR_TYPE("sectorType"),
    SECTOR_SUB_TYPE("sectorSubType"),
    ANALYTST_TYPE("analystType"),
    RESEARCHED_BY("researchedBy"),
    RESEARCH_DATE("researchDate"),
    REPORT_TONE("reportTone"),
    REPORT_FREQUENCY("reportFrequency");

    private String type;

    SectorFilterTypeEnums(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
