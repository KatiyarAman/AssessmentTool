package com.sterp.multitenant.tenant.settings.template.entity;

import java.io.Serializable;

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

import com.sterp.multitenant.tenant.settings.smtp.enumtype.SortOrder;

@Entity
@Table(name = "default_sort")
public class DefaultSort implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9138838538519260657L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "module_id")
	private long moduleId;

	@Column(name = "sort_order")
	@Enumerated(EnumType.STRING)
	private SortOrder sortOrder;

	@OneToOne
	@JoinColumn(name = "field_indexing_id")
	private FieldIndexing fieldIndexing;

	@Column(name = "sorting_name")
	private String sortingName;

	@Column(name = "default_flag", columnDefinition = "DEFAULT IS 0")
	private boolean defaultFlag;

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

	@Override
	public String toString() {
		return "DefaultSort [id=" + id + ", moduleId=" + moduleId + ", sortOrder=" + sortOrder + ", fieldIndexing="
				+ fieldIndexing + ", sortingName=" + sortingName + ", defaultFlag=" + defaultFlag + "]";
	}
}
