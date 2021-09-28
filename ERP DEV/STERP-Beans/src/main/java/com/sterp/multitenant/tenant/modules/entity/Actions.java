package com.sterp.multitenant.tenant.modules.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "actions")
public class Actions implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1415537703620701091L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String actions;
	
//	@Enumerated(EnumType.ORDINAL)
//	private StatusEnum status;
	
	@Column(name = "status")
	private Long status;

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}
	
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

}
