package com.finvendor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "metrics")
public class Metrics extends ManagedEntity {

	private static final long serialVersionUID = -1579525626882794084L;

	@Column(name = "equityrsrch")
	private Integer equityResearch;

	@Column(name = "equityrsrchdownload")
	private Integer equityResearchDownload;

	public Integer getEquityResearch() {
		return equityResearch;
	}

	public void setEquityResearch(Integer equityResearch) {
		this.equityResearch = equityResearch;
	}

	public Integer getEquityResearchDownload() {
		return equityResearchDownload;
	}

	public void setEquityResearchDownload(Integer equityResearchDownload) {
		this.equityResearchDownload = equityResearchDownload;
	}
}
