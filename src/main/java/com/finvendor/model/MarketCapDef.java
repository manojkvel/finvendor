package com.finvendor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "market_cap_def")
public class MarketCapDef {

	@Id
	@Column(name = "company_id")
	@GeneratedValue
	private Integer company_id;

	@Column(name = "mcap_name")
	private String mcap_name;

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public String getMcap_name() {
		return mcap_name;
	}

	public void setMcap_name(String mcap_name) {
		this.mcap_name = mcap_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company_id == null) ? 0 : company_id.hashCode());
		result = prime * result + ((mcap_name == null) ? 0 : mcap_name.hashCode());
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
		MarketCapDef other = (MarketCapDef) obj;
		if (company_id == null) {
			if (other.company_id != null)
				return false;
		} else if (!company_id.equals(other.company_id))
			return false;
		if (mcap_name == null) {
			if (other.mcap_name != null)
				return false;
		} else if (!mcap_name.equals(other.mcap_name))
			return false;
		return true;
	}
}
