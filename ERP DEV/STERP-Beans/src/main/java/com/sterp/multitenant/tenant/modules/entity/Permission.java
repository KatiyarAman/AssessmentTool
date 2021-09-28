package com.sterp.multitenant.tenant.modules.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sterp.multitenant.tenant.enumtype.YesNoEnum;

@Entity
@Table(name = "permission")
public class Permission implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6831942161863326393L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "action_id")
	private Actions actions;

	@Enumerated(EnumType.STRING)
	@Column(name = "permission")
	private YesNoEnum permission;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Actions getActions() {
		return actions;
	}

	public void setActions(Actions actions) {
		this.actions = actions;
	}

	public YesNoEnum getPermission() {
		return permission;
	}

	public void setPermission(YesNoEnum permission) {
		this.permission = permission;
	}

}
