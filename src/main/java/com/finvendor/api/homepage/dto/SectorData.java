package com.finvendor.api.homepage.dto;

public class SectorData extends HomePageSearchData {
    private String productId;
    private String sectorType;
    private String sectorSubType;

    public SectorData(String productId, String sectorType, String sectorSubType) {
        this.productId = productId;
        this.sectorType = sectorType;
        this.sectorSubType = sectorSubType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSectorType() {
        return sectorType;
    }

    public void setSectorType(String sectorType) {
        this.sectorType = sectorType;
    }

    public String getSectorSubType() {
        return sectorSubType;
    }

    public void setSectorSubType(String sectorSubType) {
        this.sectorSubType = sectorSubType;
    }
}
