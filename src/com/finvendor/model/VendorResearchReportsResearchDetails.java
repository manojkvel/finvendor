package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ven_rsrch_rpt_dtls")
public class VendorResearchReportsResearchDetails 
	implements Serializable {
	
	private static final long serialVersionUID = 03012017230601L;
	
	@Id
    @Column(name="product_id")
	private String productId; 
	
	@Column(name="accessbility")
	private String accessbility;
	
	@Column(name="suitability")
	private String suitability;
	
/*	@Column(name="cost_type")
	private String costType;
	
	@Column(name="sub_cost_pm")
	private float subCostPm;*/
	
	@Column(name="sub_cost_py")
	private float subCostPy;
	
	@Column(name="rep_format")
	private String repFormat;
	
	@Column(name="company_id")
	private String rsrchReportFor;
	
	@Column(name="rep_date")
	private String repDate;
	
	@Column(name="rsrch_report_access")
	private String rsrchReportAccess;
	
	@Column(name="rsrch_recomm_type")
	private String rsrchRecommType;

	public String getRsrchReportFor() {
		return rsrchReportFor;
	}

	public void setRsrchReportFor(String rsrchReportFor) {
		this.rsrchReportFor = rsrchReportFor;
	}

	public String getRsrchRecommType() {
		return rsrchRecommType;
	}

	public void setRsrchRecommType(String rsrchRecommType) {
		this.rsrchRecommType = rsrchRecommType;
	}

	public String getRepDate() {
		return repDate;
	}

	public void setRepDate(String repDate) {
		this.repDate = repDate;
	}

	public String getRsrchReportAccess() {
		return rsrchReportAccess;
	}

	public void setRsrchReportAccess(String rsrchReportAccess) {
		this.rsrchReportAccess = rsrchReportAccess;
	}

	public String getRsrchReportDesc() {
		return rsrchReportDesc;
	}

	public void setRsrchReportDesc(String rsrchReportDesc) {
		this.rsrchReportDesc = rsrchReportDesc;
	}

	public String getRsrchUploadReport() {
		return rsrchUploadReport;
	}

	public void setRsrchUploadReport(String rsrchUploadReport) {
		this.rsrchUploadReport = rsrchUploadReport;
	}

	public String getTargetPrice() {
		return targetPrice;
	}

	public void setTargetPrice(String targetPrice) {
		this.targetPrice = targetPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(name="rsrch_report_desc")
	private String rsrchReportDesc;
	
	@Column(name="rsrch_upload_report")
	private String rsrchUploadReport;
	
	@Column(name="target_price")
	private String targetPrice;
	
	
	
/*	@Column(name="res_period_mon")
	private String resPeriodMon;
	
	@Column(name="res_period_year")
	private String resPeriodYear;
*/
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getAccessbility() {
		return accessbility;
	}

	public void setAccessbility(String accessbility) {
		this.accessbility = accessbility;
	}

	public String getSuitability() {
		return suitability;
	}

	public void setSuitability(String suitability) {
		this.suitability = suitability;
	}

	/*public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public float getSubCostPm() {
		return subCostPm;
	}

	public void setSubCostPm(float subCostPm) {
		this.subCostPm = subCostPm;
	}
*/
	public float getSubCostPy() {
		return subCostPy;
	}

	public void setSubCostPy(float subCostPy) {
		this.subCostPy = subCostPy;
	}

	public String getRepFormat() {
		return repFormat;
	}

	public void setRepFormat(String repFormat) {
		this.repFormat = repFormat;
	}

	/*public String getResPeriodMon() {
		return resPeriodMon;
	}

	public void setResPeriodMon(String resPeriodMon) {
		this.resPeriodMon = resPeriodMon;
	}

	public String getResPeriodYear() {
		return resPeriodYear;
	}

	public void setResPeriodYear(String resPeriodYear) {
		this.resPeriodYear = resPeriodYear;
	}
	*/
}
