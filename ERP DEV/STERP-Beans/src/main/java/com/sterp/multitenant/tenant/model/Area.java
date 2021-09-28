package com.sterp.multitenant.tenant.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "area")
public class Area extends SuperBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4222497059309572154L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String area;
	
	@Column(name = "city_id")
	private int cityId;
	
	@Column(name = "pin_code")
	private int pincode;
	
//	@Enumerated(EnumType.ORDINAL)
//	private StatusEnum status;
	
//	@Column(name = "status")
//	private Long status;

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

	@Override
	public String toString() {
		return "Area [id=" + id + ", area=" + area + ", cityId=" + cityId + ", pincode=" + pincode + "]";
	}
	
	
	
	
}
