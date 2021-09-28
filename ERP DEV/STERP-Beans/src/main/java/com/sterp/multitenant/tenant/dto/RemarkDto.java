package com.sterp.multitenant.tenant.dto;

import java.io.Serializable;

public class RemarkDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3216853684617969927L;
	private Integer id;
	private int moduleId;
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
