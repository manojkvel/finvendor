<<<<<<< HEAD
/**
 * 
 */
package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author rayulu vemula
 *
 */
@Entity
@Table(name="asset_class_security_type_map")
public class AssetClassSecurityMap implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="asset_class_security_type_id")
    @GeneratedValue
    private Integer asset_class_security_type_id;
	
	@ManyToOne(targetEntity=AssetClass.class,fetch=FetchType.LAZY)
	@JoinColumn(name="asset_class_id", nullable=false)
	private AssetClass assetClass;
	
	@ManyToOne(targetEntity=SecurityType.class,fetch=FetchType.LAZY)
	@JoinColumn(name="security_type_id", nullable=false)
	private SecurityType securityType;
	
	/**
	 * @return the asset_class_security_type_id
	 */
	public Integer getAsset_class_security_type_id() {
		return asset_class_security_type_id;
	}

	/**
	 * @param asset_class_security_type_id the asset_class_security_type_id to set
	 */
	public void setAsset_class_security_type_id(
			Integer asset_class_security_type_id) {
		this.asset_class_security_type_id = asset_class_security_type_id;
	}

	/**
	 * @return the assetClass
	 */
	public AssetClass getAssetClass() {
		return assetClass;
	}

	/**
	 * @param assetClass the assetClass to set
	 */
	public void setAssetClass(AssetClass assetClass) {
		this.assetClass = assetClass;
	}

	/**
	 * @return the securityType
	 */
	public SecurityType getSecurityType() {
		return securityType;
	}

	/**
	 * @param securityType the securityType to set
	 */
	public void setSecurityType(SecurityType securityType) {
		this.securityType = securityType;
	}

	
	
}
=======
/**
 * 
 */
package com.finvendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author rayulu vemula
 *
 */
@Entity
@Table(name="asset_class_security_type_map")
public class AssetClassSecurityMap implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="asset_class_security_type_id")
    @GeneratedValue
    private Integer asset_class_security_type_id;
	
	@ManyToOne(targetEntity=AssetClass.class,fetch=FetchType.LAZY)
	@JoinColumn(name="asset_class_id", nullable=false)
	private AssetClass assetClass;
	
	@ManyToOne(targetEntity=SecurityType.class,fetch=FetchType.LAZY)
	@JoinColumn(name="security_type_id", nullable=false)
	private SecurityType securityType;
	
	/**
	 * @return the asset_class_security_type_id
	 */
	public Integer getAsset_class_security_type_id() {
		return asset_class_security_type_id;
	}

	/**
	 * @param asset_class_security_type_id the asset_class_security_type_id to set
	 */
	public void setAsset_class_security_type_id(
			Integer asset_class_security_type_id) {
		this.asset_class_security_type_id = asset_class_security_type_id;
	}

	/**
	 * @return the assetClass
	 */
	public AssetClass getAssetClass() {
		return assetClass;
	}

	/**
	 * @param assetClass the assetClass to set
	 */
	public void setAssetClass(AssetClass assetClass) {
		this.assetClass = assetClass;
	}

	/**
	 * @return the securityType
	 */
	public SecurityType getSecurityType() {
		return securityType;
	}

	/**
	 * @param securityType the securityType to set
	 */
	public void setSecurityType(SecurityType securityType) {
		this.securityType = securityType;
	}

	
	
}
>>>>>>> origin/master
