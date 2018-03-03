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
    private Boolean awardWinnig;
    private Boolean researchCfa;
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
	
	public Boolean getAwardWinnig() {
		return awardWinnig;
	}
	public void setAwardWinnig(Boolean awardWinnig) {
		this.awardWinnig = awardWinnig;
	}
	public Boolean getResearchCfa() {
		return researchCfa;
	}
	public void setResearchCfa(Boolean researchCfa) {
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((analystType == null) ? 0 : analystType.hashCode());
		result = prime * result + ((awardWinnig == null) ? 0 : awardWinnig.hashCode());
		result = prime * result + ((brokerRank == null) ? 0 : brokerRank.hashCode());
		result = prime * result + ((brokerYearOfInCorp == null) ? 0 : brokerYearOfInCorp.hashCode());
		result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result + ((geo == null) ? 0 : geo.hashCode());
		result = prime * result + ((mcap == null) ? 0 : mcap.hashCode());
		result = prime * result + ((recommType == null) ? 0 : recommType.hashCode());
		result = prime * result + ((researchCfa == null) ? 0 : researchCfa.hashCode());
		result = prime * result + ((researchedBroker == null) ? 0 : researchedBroker.hashCode());
		result = prime * result + ((style == null) ? 0 : style.hashCode());
		result = prime * result + ((upside == null) ? 0 : upside.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EquityResearchFilter other = (EquityResearchFilter) obj;
		if (analystType == null) {
			if (other.analystType != null)
				return false;
		} else if (!analystType.equals(other.analystType))
			return false;
		if (awardWinnig == null) {
			if (other.awardWinnig != null)
				return false;
		} else if (!awardWinnig.equals(other.awardWinnig))
			return false;
		if (brokerRank == null) {
			if (other.brokerRank != null)
				return false;
		} else if (!brokerRank.equals(other.brokerRank))
			return false;
		if (brokerYearOfInCorp == null) {
			if (other.brokerYearOfInCorp != null)
				return false;
		} else if (!brokerYearOfInCorp.equals(other.brokerYearOfInCorp))
			return false;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (geo == null) {
			if (other.geo != null)
				return false;
		} else if (!geo.equals(other.geo))
			return false;
		if (mcap == null) {
			if (other.mcap != null)
				return false;
		} else if (!mcap.equals(other.mcap))
			return false;
		if (recommType == null) {
			if (other.recommType != null)
				return false;
		} else if (!recommType.equals(other.recommType))
			return false;
		if (researchCfa == null) {
			if (other.researchCfa != null)
				return false;
		} else if (!researchCfa.equals(other.researchCfa))
			return false;
		if (researchedBroker == null) {
			if (other.researchedBroker != null)
				return false;
		} else if (!researchedBroker.equals(other.researchedBroker))
			return false;
		if (style == null) {
			if (other.style != null)
				return false;
		} else if (!style.equals(other.style))
			return false;
		if (upside == null) {
			if (other.upside != null)
				return false;
		} else if (!upside.equals(other.upside))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "EquityResearchFilter [geo=" + geo + ", companyId=" + companyId + ", mcap=" + mcap + ", style=" + style
				+ ", analystType=" + analystType + ", researchedBroker=" + researchedBroker + ", brokerYearOfInCorp="
				+ brokerYearOfInCorp + ", brokerRank=" + brokerRank + ", recommType=" + recommType + ", awardWinnig="
				+ awardWinnig + ", researchCfa=" + researchCfa + ", upside=" + upside + "]";
	}
}
