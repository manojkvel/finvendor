package com.finvendor.model.metrics;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "download_eqty_research_report_metrics")
@NamedQueries({
		@NamedQuery(name = DownloadEqtyResearchReportsMetrics.LOCAL_DATE_NAMED_QUERY, query = "from com.finvendor.model.metrics.DownloadEqtyResearchReportsMetrics t where t.local_date like:localdate and t.user_name like:username order by t.id desc") })
public class DownloadEqtyResearchReportsMetrics implements Serializable {
	public static final String LOCAL_DATE_NAMED_QUERY = "downloadReportMetricsByLocalDate";

	@Id
	@GeneratedValue
	@Column(name = "id")
	protected String id;

	@Column(name = "user_name")
	private String user_name;
	
	@Column(name = "count")
	private String count;

	@Column(name = "local_date")
	private String local_date;

	@Column(name = "ip_address")
	private String ip_address;

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

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
}
