package com.sterp.multitenant.tenant.zone.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sterp.multitenant.tenant.branch.entity.Branch;
import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "zone")
public class Zone extends SuperBean implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "zone_code")
	private String zoneCode;

	@Column(name = "zone_name")
	private String zoneName;

	@Column(name = "description")
	private String description;

//	@Column(name = "status")
//	@Enumerated(EnumType.ORDINAL)
//	private StatusEnum status;
	
//	@Column(name="status")
//	private Long status;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "zone_id")
	private Set<Branch> branch;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getZoneCode() {
		return zoneCode;
	}

	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Set<Branch> getBranch() {
		return branch;
	}

	public void setBranch(Set<Branch> branch) {
		this.branch = branch;
	}

	@Override
	public String toString() {
		return "Zone [id=" + id + ", zoneCode=" + zoneCode + ", zoneName=" + zoneName + ", description=" + description
				+ ", branch=" + branch + "]";
	}

	

}
