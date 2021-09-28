package com.sterp.multitenant.tenant.modules.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "module_actions_mapping")
public class ModuleActionsMapping implements Serializable {

	private static final long serialVersionUID = -1324753214706313943L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "module_id")
	private long moduleId;

	@Column(name = "action_id")
	private long actionId;
	
	public ModuleActionsMapping() {
		super();
	}

	public ModuleActionsMapping(long moduleId, long actionId) {
		super();
		this.moduleId = moduleId;
		this.actionId = actionId;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getModuleId() {
		return moduleId;
	}

	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}

	public long getActionId() {
		return actionId;
	}

	public void setActionId(long actionId) {
		this.actionId = actionId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (actionId ^ (actionId >>> 32));
		result = prime * result + (int) (moduleId ^ (moduleId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModuleActionsMapping other = (ModuleActionsMapping) obj;
		if (actionId != other.actionId)
			return false;
		if (moduleId != other.moduleId)
			return false;
		return true;
	}
	
	
	
}
