package com.finvendor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="company_sub_type")
public class CompanySubType {
	
	private static final long serialVersionUID = 29022016082011L;
	
	@Id
    @Column(name="id")
	private Integer id;
	
	@Column(name="type")
	private String type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
