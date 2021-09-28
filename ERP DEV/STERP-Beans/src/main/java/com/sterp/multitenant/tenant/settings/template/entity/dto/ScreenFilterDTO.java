package com.sterp.multitenant.tenant.settings.template.entity.dto;

import java.io.Serializable;

import com.sterp.multitenant.tenant.settings.smtp.enumtype.SortOrder;
import com.sterp.multitenant.tenant.settings.template.entity.FieldIndexing;

public class ScreenFilterDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8008466660961684210L;
	private long id;
	private long userId;
	private int fieldIndexingId;
	private FieldIndexing fieldIndexing;
	private String filterValue;
	private boolean hidden;
	private long moduleId;
	private String filterRange;
	private boolean sortFlag;
	private SortOrder sortOrder;

	public ScreenFilterDTO(long id, long userId, int fieldIndexingId, String filterValue, boolean hidden, long moduleId,
			String filterRange, boolean sortFlag, SortOrder sortOrder) {
		this.id = id;
		this.userId = userId;
		this.fieldIndexingId = fieldIndexingId;
		this.filterValue = filterValue;
		this.hidden = hidden;
		this.moduleId = moduleId;
		this.filterRange = filterRange;
		this.sortFlag = sortFlag;
		this.sortOrder = sortOrder;
	}

	public ScreenFilterDTO() {
		// TODO Auto-generated constructor stub
	}

	public ScreenFilterDTO(long id, long userId, int fieldIndexingId, String filterValue, boolean hidden, long moduleId,
			String filterRange, boolean sortFlag, SortOrder sortOrder, FieldIndexing fieldIndexing) {
		this(id, userId, fieldIndexingId, filterValue, hidden, moduleId, filterRange, sortFlag, sortOrder);
		this.fieldIndexing = fieldIndexing;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getFieldIndexingId() {
		return fieldIndexingId;
	}

	public void setFieldIndexingId(int fieldIndexingId) {
		this.fieldIndexingId = fieldIndexingId;
	}

	public String getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public long getModuleId() {
		return moduleId;
	}

	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}

	public String getFilterRange() {
		return filterRange;
	}

	public void setFilterRange(String filterRange) {
		this.filterRange = filterRange;
	}

	public FieldIndexing getFieldIndexing() {
		return fieldIndexing;
	}

	public void setFieldIndexing(FieldIndexing fieldIndexing) {
		this.fieldIndexing = fieldIndexing;
	}

	public boolean isSortFlag() {
		return sortFlag;
	}

	public void setSortFlag(boolean sortFlag) {
		this.sortFlag = sortFlag;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Override
	public String toString() {
		return "ScreenFilterDTO [id=" + id + ", userId=" + userId + ", fieldIndexingId=" + fieldIndexingId
				+ ", fieldIndexing=" + fieldIndexing + ", filterValue=" + filterValue + ", hidden=" + hidden
				+ ", moduleId=" + moduleId + ", filterRange=" + filterRange + ", sortFlag=" + sortFlag + ", sortOrder="
				+ sortOrder + "]";
	}

}
