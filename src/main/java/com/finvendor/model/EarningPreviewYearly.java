package com.finvendor.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="earning_preview_yearly")
public class EarningPreviewYearly implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="row_id")
    private Integer rowId;
	
	@Column(name="period")
	private String period;

	@Column(name="revenue")
	private String revenue;

	@Column(name="operatingProfitMargin")
	private String operatingProfitMargin;

	@Column(name="profitAfterTax")
	private String profitAfterTax;

	@Column(name="eps")
	private String eps;

	@Column(name="netOperatingCashFlow")
	private String netOperatingCashFlow;

	@Column(name="roe")
	private String roe;
		
	@ManyToOne
	@JoinColumn(name="stock_id")
	private EarningPreview earningPreview;

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getRevenue() {
		return revenue;
	}

	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}

	public String getOperatingProfitMargin() {
		return operatingProfitMargin;
	}

	public void setOperatingProfitMargin(String operatingProfitMargin) {
		this.operatingProfitMargin = operatingProfitMargin;
	}

	public String getProfitAfterTax() {
		return profitAfterTax;
	}

	public void setProfitAfterTax(String profitAfterTax) {
		this.profitAfterTax = profitAfterTax;
	}

	public String getEps() {
		return eps;
	}

	public void setEps(String eps) {
		this.eps = eps;
	}

	public String getNetOperatingCashFlow() {
		return netOperatingCashFlow;
	}

	public void setNetOperatingCashFlow(String netOperatingCashFlow) {
		this.netOperatingCashFlow = netOperatingCashFlow;
	}

	public EarningPreview getEarningPreview() {
		return earningPreview;
	}

	public void setEarningPreview(EarningPreview earningPreview) {
		this.earningPreview = earningPreview;
	}

	public String getRoe() {
		return roe;
	}

	public void setRoe(String roe) {
		this.roe = roe;
	}
}
