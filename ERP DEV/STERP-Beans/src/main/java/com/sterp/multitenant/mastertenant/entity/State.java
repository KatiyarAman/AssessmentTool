package com.sterp.multitenant.mastertenant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "state")
//@JsonIgnoreProperties(value = "cities")
public class State {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "state_id") Integer id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id")	
	private Country country;
	private @Column(name = "language") String language;
	private @Column(name = "short_name") String shortName;
	private @Column(name = "state_code") String stateCode;
	private @Column(name = "state_name") String stateName;
	private @Column(name = "is_ut") boolean ut;
	private @Column(name = "status") Long status;

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name = "state_id")
//	private List<City> cities;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public boolean isUt() {
		return ut;
	}

	public void setUt(boolean ut) {
		this.ut = ut;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	
//	public List<City> getCity() {
//		return cities;
//	}
//
//	public void setCity(List<City> city) {
//		this.cities = city;
//	}

}
