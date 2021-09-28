package com.sterp.multitenant.tenant.settings.template.entity.dto;

import java.io.Serializable;
import java.util.List;

public class CustomFieldByModuleAndFieldTypeRequestDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4014151341347543403L;
	private Long moduleId;
	private List<Integer> fieldTypeIds;

	public CustomFieldByModuleAndFieldTypeRequestDTO(Long moduleId, List<Integer> fieldTypeIds) {
		this.moduleId = moduleId;
		this.fieldTypeIds = fieldTypeIds;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public List<Integer> getFieldTypeIds() {
		return fieldTypeIds;
	}

	public void setFieldTypeIds(List<Integer> fieldTypeIds) {
		this.fieldTypeIds = fieldTypeIds;
	}
}
