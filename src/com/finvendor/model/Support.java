<<<<<<< HEAD
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
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author rayulu vemula
 *
 */
@Entity
@Table(name="support")
public class Support implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="support_id")
    private String support_id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="support")
	private Set<VendorSupport> vendorSupports=new HashSet<VendorSupport>();
	
	

	/**
	 * @return the support_id
	 */
	public String getSupport_id() {
		return support_id;
	}

	/**
	 * @param support_id the support_id to set
	 */
	public void setSupport_id(String support_id) {
		this.support_id = support_id;
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
	 * @return the vendorSupports
	 */
	public Set<VendorSupport> getVendorSupports() {
		return vendorSupports;
	}

	/**
	 * @param vendorSupports the vendorSupports to set
	 */
	public void setVendorSupports(Set<VendorSupport> vendorSupports) {
		this.vendorSupports = vendorSupports;
	}
	

}
=======
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
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author rayulu vemula
 *
 */
@Entity
@Table(name="support")
public class Support implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="support_id")
    private String support_id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="support")
	private Set<VendorSupport> vendorSupports=new HashSet<VendorSupport>();
	
	

	/**
	 * @return the support_id
	 */
	public String getSupport_id() {
		return support_id;
	}

	/**
	 * @param support_id the support_id to set
	 */
	public void setSupport_id(String support_id) {
		this.support_id = support_id;
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
	 * @return the vendorSupports
	 */
	public Set<VendorSupport> getVendorSupports() {
		return vendorSupports;
	}

	/**
	 * @param vendorSupports the vendorSupports to set
	 */
	public void setVendorSupports(Set<VendorSupport> vendorSupports) {
		this.vendorSupports = vendorSupports;
	}
	

}
>>>>>>> origin/master
