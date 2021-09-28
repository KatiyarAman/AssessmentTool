package com.sterp.multitenant.tenant.settings.template.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sterp.multitenant.tenant.settings.smtp.enumtype.SortOrder;

@Entity
@Table(name = "screen_filter")
public class ScreenFilter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4793713868962204758L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "user_id")
	private long userId;

	@Column(name = "field_indexing_id")
	private int fieldIndexingId;

	/*
	 * @Transient private FieldIndexing fieldIndexing;
	 */

	@Column(name = "filter_value")
	private String filterValue;

	private boolean hidden;

	@Column(name = "module_id")
	private long moduleId;

	@Column(name = "filter_range")
	private String filterRange;

	@Column(name = "sort_flag")
	private boolean sortFlag;

	@Column(name = "sort_order")
	@Enumerated(EnumType.STRING)
	private SortOrder sortOrder;

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
		return "ScreenFilter [id=" + id + ", userId=" + userId + ", fieldIndexingId=" + fieldIndexingId
				+ ", filterValue=" + filterValue + ", hidden=" + hidden + ", moduleId=" + moduleId + ", filterRange="
				+ filterRange + ", sortFlag=" + sortFlag + ", sortOrder=" + sortOrder + "]";
	}

	/*
	 * public FieldIndexing getFieldIndexing() { return fieldIndexing; }
	 * 
	 * public void setFieldIndexing(FieldIndexing fieldIndexing) {
	 * this.fieldIndexing = fieldIndexing; }
	 */

}
