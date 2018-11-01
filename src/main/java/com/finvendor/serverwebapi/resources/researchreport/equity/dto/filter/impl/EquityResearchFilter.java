package com.finvendor.serverwebapi.resources.researchreport.equity.dto.filter.impl;

import com.finvendor.serverwebapi.resources.researchreport.equity.dto.filter.ResearchReportFilter;

import java.util.List;

public class EquityResearchFilter implements ResearchReportFilter {
	private static final long serialVersionUID = 6430389508253318857L;
	private String geo;
	private String companyId;
    private List<String> mcap = null;
    private List<String> style = null;
    private List<String> analystType = null;
    private List<String> researchedBroker = null;
    private List<String> researchDate = null;
    private List<String> brokerRank = null;
    private List<String> recommType = null;
    private List<String> others = null;
    private List<String> upside = null;
	private List<String> industry = null; //which is sector
	private List<String> productId;

	public String getGeo() {
		return geo;
	}
	public void setGeo(String geo) {
		this.geo = geo;
	}
	public List<String> getMcap() {
		return mcap;
	}
	public void setMcap(List<String> mcap) {
		this.mcap = mcap;
	}
	public List<String> getStyle() {
		return style;
	}
	public void setStyle(List<String> style) {
		this.style = style;
	}
	public List<String> getAnalystType() {
		return analystType;
	}
	public void setAnalystType(List<String> analystType) {
		this.analystType = analystType;
	}
	public List<String> getResearchedBroker() {
		return researchedBroker;
	}
	public void setResearchedBroker(List<String> researchedBroker) {
		this.researchedBroker = researchedBroker;
	}

	public List<String> getResearchDate() {
		return researchDate;
	}

	public void setResearchDate(List<String> researchDate) {
		this.researchDate = researchDate;
	}

	public List<String> getBrokerRank() {
		return brokerRank;
	}
	public void setBrokerRank(List<String> brokerRank) {
		this.brokerRank = brokerRank;
	}
	public List<String> getRecommType() {
		return recommType;
	}
	public void setRecommType(List<String> recommType) {
		this.recommType = recommType;
	}
	
	public List<String> getOthers() {
		return others;
	}
	public void setOthers(List<String> others) {
		this.others = others;
	}
	public List<String> getUpside() {
		return upside;
	}
	public void setUpside(List<String> upside) {
		this.upside = upside;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public List<String> getIndustry() {
		return industry;
	}

	public void setIndustry(List<String> industry) {
		this.industry = industry;
	}

	public List<String> getProductId() {
		return productId;
	}

	public void setProductId(List<String> productId) {
		this.productId = productId;
	}
}
