package com.sterp.multitenant.tenant.settings.template.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Ankit Panwar
 *
 */
@Entity
@Table(name = "module_button_mapping")

public class ModuleButtonsMapping implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6172015680505303626L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "module_action_id")
	private long moduleActionId;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "button_id")
	private Buttons button;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getModuleActionId() {
		return moduleActionId;
	}

	public void setModuleActionId(long moduleActionId) {
		this.moduleActionId = moduleActionId;
	}

	public Buttons getButton() {
		return button;
	}

	public void setButton(Buttons button) {
		this.button = button;
	}

}
