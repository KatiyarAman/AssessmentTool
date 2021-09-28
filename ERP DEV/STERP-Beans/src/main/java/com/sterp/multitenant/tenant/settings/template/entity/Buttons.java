package com.sterp.multitenant.tenant.settings.template.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sterp.multitenant.tenant.model.StatusDetails;
import com.sterp.multitenant.tenant.modules.entity.Actions;

@Entity
@Table(name = "buttons")
public class Buttons extends SuperBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2346248580468784865L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "button_name")
	private String buttonName;

	@Column(name = "button_class")
	private String buttonClass;
	
	@Column(name="action_id")
	private Long actionId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "action_id", updatable = false, insertable = false)
	private Actions action;

	@Column(name = "icon")
	private String icon;

//	@Enumerated(EnumType.ORDINAL)
//	private StatusEnum status;
	
//	@Column(name = "status")
//	private Long status;

	@Column(name = "status_details_id")
	private Long statusDetailsId;

	@Column(name = "purpose")
	private String purpose;

	@Column(name = "type")
	private String type;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "status_details_id", insertable = false, updatable = false)
	private StatusDetails statusDetails;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public String getButtonClass() {
		return buttonClass;
	}

	public void setButtonClass(String buttonClass) {
		this.buttonClass = buttonClass;
	}

	public Actions getAction() {
		return action;
	}

	public void setAction(Actions action) {
		this.action = action;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
//	public Long getStatus() {
//		return status;
//	}
//
//	public void setStatus(Long status) {
//		this.status = status;
//	}

	public Long getStatusDetailsId() {
		return statusDetailsId;
	}

	public void setStatusDetailsId(Long statusDetailsId) {
		this.statusDetailsId = statusDetailsId;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public StatusDetails getStatusDetails() {
		return statusDetails;
	}

	public void setStatusDetails(StatusDetails statusDetails) {
		this.statusDetails = statusDetails;
	}
	

}
