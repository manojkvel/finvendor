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
@Table(name="country_exchange_map")
public class CountryExchangeMap implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="ce_id")
    @GeneratedValue
    private Integer ce_id;
	
	
	@ManyToOne(targetEntity=Country.class,fetch=FetchType.LAZY)
	@JoinColumn(name="country_id", nullable=false)
	private Country country;
	
	@ManyToOne(targetEntity=Exchange.class,fetch=FetchType.LAZY)
	@JoinColumn(name="exchange_id", nullable=false)
	private Exchange exchange;
	
	

	/**
	 * @return the ce_id
	 */
	public Integer getCe_id() {
		return ce_id;
	}

	/**
	 * @param ce_id the ce_id to set
	 */
	public void setCe_id(Integer ce_id) {
		this.ce_id = ce_id;
	}

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * @return the exchange
	 */
	public Exchange getExchange() {
		return exchange;
	}

	/**
	 * @param exchange the exchange to set
	 */
	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}
	
}
