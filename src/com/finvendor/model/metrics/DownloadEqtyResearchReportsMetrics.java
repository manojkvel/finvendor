package com.finvendor.model.metrics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "download_eqty_research_report_metrics")
@NamedQueries({
		@NamedQuery(name = DownloadEqtyResearchReportsMetrics.METIRCS_BY_REQUEST_NAME_NAMED_QUERY, query = "from com.finvendor.model.metrics.DownloadEqtyResearchReportsMetrics t where t.web_request like:webrequest order by t.id desc") })
public class DownloadEqtyResearchReportsMetrics {
	public static final String METIRCS_BY_REQUEST_NAME_NAMED_QUERY = "metricsByRequest2";

	@Id
	@GeneratedValue
	@Column(name = "id")
	protected String id;

	@Column(name = "user_name")
	private String user_name;
	
	@Column(name = "web_request")
	private String web_request;

	@Column(name = "count")
	private String count;

	@Column(name = "local_date")
	private String local_date;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getWeb_request() {
		return web_request;
	}

	public void setWeb_request(String web_request) {
		this.web_request = web_request;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getLocal_date() {
		return local_date;
	}

	public void setLocal_date(String local_date) {
		this.local_date = local_date;
	}

}
