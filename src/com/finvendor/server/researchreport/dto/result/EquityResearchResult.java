package com.finvendor.server.researchreport.dto.result;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.finvendor.server.researchreport.dto.result.ifc.AbsEquitySectorResearchResult;

/**
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
@JsonPropertyOrder({ "companyId", "company", "style", "mcap", "sector", "subSector", "broker", "since", "awarded", "researchedByCfa",
		"brokerRank", "cmp", "priceDate", "pe",
		"_3YrPatGrowth", "recommType", "targetPrice", "priceAtRecomm", "upside", "report", "researchDate",
		"analystName" })
public class EquityResearchResult extends AbsEquitySectorResearchResult {
	private String productId;
	private String companyId;
	private String company;
	private String style;
	private String mcap;

	private Map<String,String> brokerRank;

	private String cmp;
	private String priceDate;
	private String pe;
	private String _3YrPatGrowth;

	private String recommType;
	private String targetPrice;
	private String priceAtRecomm;
	private String upside;
	private String analystType;
	private String yrOfInCorp;
	

	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getMcap() {
		return mcap;
	}

	public void setMcap(String mcap) {
		this.mcap = mcap;
	}

	public String getCmp() {
		return cmp;
	}

	public void setCmp(String cmp) {
		this.cmp = cmp;
	}

	public String getPriceDate() {
		return priceDate;
	}

	public void setPriceDate(String priceDate) {
		this.priceDate = priceDate;
	}

	public String getPe() {
		return pe;
	}

	public void setPe(String pe) {
		this.pe = pe;
	}

	public String get_3YrPatGrowth() {
		return _3YrPatGrowth;
	}

	public void set_3YrPatGrowth(String _3YrPatGrowth) {
		this._3YrPatGrowth = _3YrPatGrowth;
	}

	public String getRecommType() {
		return recommType;
	}

	public void setRecommType(String recommType) {
		this.recommType = recommType;
	}

	public String getTargetPrice() {
		return targetPrice;
	}

	public void setTargetPrice(String targetPrice) {
		this.targetPrice = targetPrice;
	}

	public String getPriceAtRecomm() {
		return priceAtRecomm;
	}

	public void setPriceAtRecomm(String priceAtRecomm) {
		this.priceAtRecomm = priceAtRecomm;
	}

	public String getUpside() {
		return upside;
	}

	public void setUpside(String upside) {
		this.upside = upside;
	}


	public String getAnalystType() {
		return analystType;
	}

	public void setAnalystType(String analystType) {
		this.analystType = analystType;
	}

	public String getYrOfInCorp() {
		return yrOfInCorp;
	}

	public void setYrOfInCorp(String yrOfInCorp) {
		this.yrOfInCorp = yrOfInCorp;
	}

	public Map<String, String> getBrokerRank() {
		return brokerRank;
	}

	public void setBrokerRank(Map<String, String> brokerRank) {
		this.brokerRank = brokerRank;
	}

	
}
