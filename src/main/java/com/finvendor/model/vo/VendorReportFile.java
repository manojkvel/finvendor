package com.finvendor.model.vo;

import com.finvendor.model.VendorResearchReportsResearchDetails;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "vendor_report_file")
@NamedQueries({
        @NamedQuery(name = VendorReportFile.REPORT_FILE_NAMED_QUERY, query = "from com.finvendor.model.vo.VendorReportFile where productId like:productId")
})
public class VendorReportFile {
    public static final String REPORT_FILE_NAMED_QUERY = "reportFile";

    @Id
    @Column(name="product_id")
    private String productId;

    @Column(name="report_file")
    @Lob
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
