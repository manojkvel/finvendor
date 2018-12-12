package com.finvendor.serverwebapi.resources.vendoroffering.dto;

import java.io.Serializable;
import java.sql.Blob;

public class VendorReportFileDto implements Serializable {
    private String productId;
    private Blob reportFile;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Blob getReportFile() {
        return reportFile;
    }

    public void setReportFile(Blob reportFile) {
        this.reportFile = reportFile;
    }
}
