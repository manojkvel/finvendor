/**
 * 
 */
package com.finvendor.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author rayulu vemula
 *
 */
@Entity
@Table(name="awards")
public class Awards implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
    @Column(name="award_id")
    @GeneratedValue
    private Integer award_id;
	
    @Column(name="name")
    private String name;
    
    @Column(name="sponsor")
    private String sponsor;
    
    @Column(name="rank")
    private String rank;
    
    @Column(name="logo")
    private String logo;
    
	@OneToMany(fetch=FetchType.LAZY,mappedBy="awards")
	private Set<VendorAwardsMap> vendorAwardsMaps=new HashSet<VendorAwardsMap>();
	 

    
	/**
	 * @return the award_id
	 */
	public Integer getAward_id() {
		return award_id;
	}

	/**
	 * @param award_id the award_id to set
	 */
	public void setAward_id(Integer award_id) {
		this.award_id = award_id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the sponsor
	 */
	public String getSponsor() {
		return sponsor;
	}

	/**
	 * @param sponsor the sponsor to set
	 */
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	/**
	 * @return the rank
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}

	/**
	 * @return the logo
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * @param logo the logo to set
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * @return the vendorAwardsMaps
	 */
	public Set<VendorAwardsMap> getVendorAwardsMaps() {
		return vendorAwardsMaps;
	}

	/**
	 * @param vendorAwardsMaps the vendorAwardsMaps to set
	 */
	public void setVendorAwardsMaps(Set<VendorAwardsMap> vendorAwardsMaps) {
		this.vendorAwardsMaps = vendorAwardsMaps;
	}
    
    

}
