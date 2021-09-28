package com.sterp.multitenant.tenant.modules.dto;

import java.io.Serializable;
import java.util.List;

public class ModuleMappedToActionResponseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7544411022156203057L;
	private long moduleId;
	private String title;
	private List<ModuleActionMappingResponseDTO> mappingResponseDTOs;

	public long getModuleId() {
		return moduleId;
	}

	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ModuleActionMappingResponseDTO> getMappingResponseDTOs() {
		return mappingResponseDTOs;
	}

	public void setMappingResponseDTOs(List<ModuleActionMappingResponseDTO> mappingResponseDTOs) {
		this.mappingResponseDTOs = mappingResponseDTOs;
	}

}
