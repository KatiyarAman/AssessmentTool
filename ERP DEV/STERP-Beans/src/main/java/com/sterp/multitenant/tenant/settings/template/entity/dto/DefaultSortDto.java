package com.sterp.multitenant.tenant.settings.template.entity.dto;

import java.io.Serializable;

import com.sterp.multitenant.tenant.settings.smtp.enumtype.SortOrder;
import com.sterp.multitenant.tenant.settings.template.entity.FieldIndexing;

public class DefaultSortDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2791757303554703093L;
	private long id;
	private long moduleId;
	private SortOrder sortOrder;
	private FieldIndexing fieldIndexing;
	private String sortingName;
	private boolean defaultFlag;
	private int fieldIndexingId;

	public DefaultSortDto(long id, long moduleId, SortOrder sortOrder, FieldIndexing fieldIndexing, String sortingName,
			boolean defaultFlag, int fieldIndexingId) {
		super();
		this.id = id;
		this.moduleId = moduleId;
		this.sortOrder = sortOrder;
		this.fieldIndexing = fieldIndexing;
		this.sortingName = sortingName;
		this.defaultFlag = defaultFlag;
		this.fieldIndexingId = fieldIndexingId;
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

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	public FieldIndexing getFieldIndexing() {
		return fieldIndexing;
	}

	public void setFieldIndexing(FieldIndexing fieldIndexing) {
		this.fieldIndexing = fieldIndexing;
	}

	public String getSortingName() {
		return sortingName;
	}

	public void setSortingName(String sortingName) {
		this.sortingName = sortingName;
	}

	public boolean isDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(boolean defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	public long getFieldIndexingId() {
		return fieldIndexingId;
	}

	public void setFieldIndexingId(int fieldIndexingId) {
		this.fieldIndexingId = fieldIndexingId;
	}

	@Override
	public String toString() {
		return "DefaultSortDto [id=" + id + ", moduleId=" + moduleId + ", sortOrder=" + sortOrder + ", fieldIndexing="
				+ fieldIndexing + ", sortingName=" + sortingName + ", defaultFlag=" + defaultFlag + ", fieldIndexingId="
				+ fieldIndexingId + "]";
	}

}
