package com.sterp.multitenant.tenant.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sterp.multitenant.tenant.settings.template.entity.CustomFieldData;

public class AreaRequestDTO implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 9147447080027717468L;
	private Long id;
	private String area;
	private int cityId;
	private int pincode;
	private Long status;
	private List<CustomFieldData> customFieldDatas = new ArrayList<CustomFieldData>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public List<CustomFieldData> getCustomFieldDatas() {
		return customFieldDatas;
	}

	public void setCustomFieldDatas(List<CustomFieldData> customFieldDatas) {
		this.customFieldDatas = customFieldDatas;
	}

	@Override
	public String toString() {
		return "AreaRequestDTO [id=" + id + ", area=" + area + ", cityId=" + cityId + ", pincode=" + pincode
				+ ", status=" + status + ", customFieldDatas=" + customFieldDatas + "]";
	}

	
	

}
