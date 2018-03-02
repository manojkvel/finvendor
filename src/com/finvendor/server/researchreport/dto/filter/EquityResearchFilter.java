package com.finvendor.server.researchreport.dto.filter;

import java.util.List;

import com.finvendor.server.researchreport.dto.filter.ifc.ResearchReportFilter;


public class EquityResearchFilter implements ResearchReportFilter {
	private static final long serialVersionUID = 6430389508253318857L;
	private String geo;
	private String companyId;
    private List<String> mcap = null;
    private List<String> style = null;
    private List<String> analystType = null;
    private List<String> researchedBroker = null;
    private List<String> brokerYearOfInCorp = null;
    private List<String> brokerRank = null;
    private List<String> recommType = null;
    private boolean awardWinnig;
    private boolean researchCfa;
    private List<String> upside = null;
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
	public List<String> getBrokerYearOfInCorp() {
		return brokerYearOfInCorp;
	}
	public void setBrokerYearOfInCorp(List<String> brokerYearOfInCorp) {
		this.brokerYearOfInCorp = brokerYearOfInCorp;
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
	public boolean isAwardWinnig() {
		return awardWinnig;
	}
	public void setAwardWinnig(boolean awardWinnig) {
		this.awardWinnig = awardWinnig;
	}
	public boolean isResearchCfa() {
		return researchCfa;
	}
	public void setResearchCfa(boolean researchCfa) {
		this.researchCfa = researchCfa;
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
	
}
