/**
 * 
 */
package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author rayulu vemula
 *
 */
@Entity
@Table(name="marketaggregators_search")
public class MarketaggregatorsSearch implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="marketaggregators_search_id")
    @GeneratedValue
    private Integer marketaggregatorsSearchId;
	private String assetclassId;
	private String securitytypeId;
	private String dataattribute;
	private String datacoverageregionId;
	private String datacoveragecountryId;
	private String datacoverageexchangeId;
	private String vendorregionofincorp;
	private String vendorcountryofincorp;
	private String vendorprofilefreshness;
	private String vendoryearoperation;
	private String searchkeyword;
	private String vendorsupportregion;
	private String vendorsupporttime;
	private String vendorawards;
	private String vendorcostrange;
	private String userid;
	public Integer getMarketaggregatorsSearchId() {
		return marketaggregatorsSearchId;
	}
	public void setMarketaggregatorsSearchId(Integer marketaggregatorsSearchId) {
		this.marketaggregatorsSearchId = marketaggregatorsSearchId;
	}
	public String getAssetclassId() {
		return assetclassId;
	}
	public void setAssetclassId(String assetclassId) {
		this.assetclassId = assetclassId;
	}
	public String getSecuritytypeId() {
		return securitytypeId;
	}
	public void setSecuritytypeId(String securitytypeId) {
		this.securitytypeId = securitytypeId;
	}
	public String getDataattribute() {
		return dataattribute;
	}
	public void setDataattribute(String dataattribute) {
		this.dataattribute = dataattribute;
	}
	public String getDatacoverageregionId() {
		return datacoverageregionId;
	}
	public void setDatacoverageregionId(String datacoverageregionId) {
		this.datacoverageregionId = datacoverageregionId;
	}
	public String getDatacoveragecountryId() {
		return datacoveragecountryId;
	}
	public void setDatacoveragecountryId(String datacoveragecountryId) {
		this.datacoveragecountryId = datacoveragecountryId;
	}
	public String getDatacoverageexchangeId() {
		return datacoverageexchangeId;
	}
	public void setDatacoverageexchangeId(String datacoverageexchangeId) {
		this.datacoverageexchangeId = datacoverageexchangeId;
	}
	public String getVendorregionofincorp() {
		return vendorregionofincorp;
	}
	public void setVendorregionofincorp(String vendorregionofincorp) {
		this.vendorregionofincorp = vendorregionofincorp;
	}
	public String getVendorcountryofincorp() {
		return vendorcountryofincorp;
	}
	public void setVendorcountryofincorp(String vendorcountryofincorp) {
		this.vendorcountryofincorp = vendorcountryofincorp;
	}
	public String getVendorprofilefreshness() {
		return vendorprofilefreshness;
	}
	public void setVendorprofilefreshness(String vendorprofilefreshness) {
		this.vendorprofilefreshness = vendorprofilefreshness;
	}
	public String getVendoryearoperation() {
		return vendoryearoperation;
	}
	public void setVendoryearoperation(String vendoryearoperation) {
		this.vendoryearoperation = vendoryearoperation;
	}
	public String getSearchkeyword() {
		return searchkeyword;
	}
	public void setSearchkeyword(String searchkeyword) {
		this.searchkeyword = searchkeyword;
	}
	public String getVendorsupportregion() {
		return vendorsupportregion;
	}
	public void setVendorsupportregion(String vendorsupportregion) {
		this.vendorsupportregion = vendorsupportregion;
	}
	public String getVendorsupporttime() {
		return vendorsupporttime;
	}
	public void setVendorsupporttime(String vendorsupporttime) {
		this.vendorsupporttime = vendorsupporttime;
	}
	public String getVendorawards() {
		return vendorawards;
	}
	public void setVendorawards(String vendorawards) {
		this.vendorawards = vendorawards;
	}
	public String getVendorcostrange() {
		return vendorcostrange;
	}
	public void setVendorcostrange(String vendorcostrange) {
		this.vendorcostrange = vendorcostrange;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}	
}
