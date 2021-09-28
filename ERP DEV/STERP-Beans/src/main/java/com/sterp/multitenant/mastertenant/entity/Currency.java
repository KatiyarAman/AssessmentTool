package com.sterp.multitenant.mastertenant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sterp.multitenant.tenant.entity.StatusEnum;

@Entity
@Table(name = "currency")
public class Currency {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "currency_code", unique = true)
	private String currencyCode;

	@Column(name = "short_name")
	private String shortName;

	@Column(name = "currency_name")
	private String currencyName;

	@Column(name = "symbol")
	private String symbol;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private StatusEnum status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Currency [id=" + id + ", currencyCode=" + currencyCode + ", shortName=" + shortName + ", currencyName="
				+ currencyName + ", symbol=" + symbol + ", status=" + status + "]";
	}
}
