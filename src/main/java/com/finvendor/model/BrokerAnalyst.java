package com.finvendor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "broker_analyst")
public class BrokerAnalyst {

	@Id
	@Column(name = "broker_id")
	@GeneratedValue
	private String broker_id;

	@Column(name = "broker_rank")
	private String broker_rank;

	
	public String getBroker_id() {
		return broker_id;
	}

	public void setBroker_id(String broker_id) {
		this.broker_id = broker_id;
	}

	public String getBroker_rank() {
		return broker_rank;
	}

	public void setBroker_rank(String broker_rank) {
		this.broker_rank = broker_rank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((broker_id == null) ? 0 : broker_id.hashCode());
		result = prime * result + ((broker_rank == null) ? 0 : broker_rank.hashCode());
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
		BrokerAnalyst other = (BrokerAnalyst) obj;
		if (broker_id == null) {
			if (other.broker_id != null)
				return false;
		} else if (!broker_id.equals(other.broker_id))
			return false;
		if (broker_rank == null) {
			if (other.broker_rank != null)
				return false;
		} else if (!broker_rank.equals(other.broker_rank))
			return false;
		return true;
	}
}
