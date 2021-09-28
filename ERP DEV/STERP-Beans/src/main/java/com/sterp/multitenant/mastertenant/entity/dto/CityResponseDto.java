package com.sterp.multitenant.mastertenant.entity.dto;

import java.io.Serializable;

import com.sterp.multitenant.tenant.entity.StatusEnum;

public class CityResponseDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 73865508612435411L;
	private Integer id;
	private String cityCode;
	private String cityName;
	private String shortName;
	private String stateCode;
	private String stdCode;
	private StatusEnum status;

	public CityResponseDto(Integer id, String cityCode, String cityName, String shortName, String stateCode,
			String stdCode, StatusEnum status) {
		this.id = id;
		this.cityCode = cityCode;
		this.cityName = cityName;
		this.shortName = shortName;
		this.stateCode = stateCode;
		this.stdCode = stdCode;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getStdCode() {
		return stdCode;
	}

	public void setStdCode(String stdCode) {
		this.stdCode = stdCode;
	}

	public int getStatus() {
		return status.getVal();
	}

	public void setStatus(int status) {
		for (StatusEnum statusEnum : StatusEnum.values()) {
			if (statusEnum.getVal() == status) {
				this.status = statusEnum;
			}
		}
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

}
