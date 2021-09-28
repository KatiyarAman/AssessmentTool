package com.sterp.multitenant.mastertenant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "country")
//@JsonIgnoreProperties(value = "states")
public class Country {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "country_id") Integer id;
	private @Column(name = "country_code") String countryCode;
	private @Column(name = "name") String countryName;
	private @OneToOne @JoinColumn(name = "currency") Currency currency;
	private @Column(name = "isd_code") String isdCode;
	private @Column(name = "country_language") String countryLanguage;
	private @Column(name = "status") Long status;

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name = "country_id")
//	private List<State> states;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public String getIsdCode() {
		return isdCode;
	}

	public void setIsdCode(String isdCode) {
		this.isdCode = isdCode;
	}

	public String getCountryLanguage() {
		return countryLanguage;
	}

	public void setCountryLanguage(String countryLanguage) {
		this.countryLanguage = countryLanguage;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	

//	public List<State> getStates() {
//		return states;
//	}
//
//	public void setStates(List<State> states) {
//		this.states = states;
//	}

}
