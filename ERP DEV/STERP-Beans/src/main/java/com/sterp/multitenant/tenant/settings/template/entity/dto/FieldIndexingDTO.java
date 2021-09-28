package com.sterp.multitenant.tenant.settings.template.entity.dto;

import java.io.Serializable;

public class FieldIndexingDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5660834955877706860L;
	private Integer id;
	private String indexFieldName;
	private String indexFieldHeader;
	private int fieldWidth;
	private boolean sortable;
	private boolean filter;
	private String filterType;
	private boolean hiddable;
	private boolean display;
	private String dataFieldName;
	private int displayOrder;

	public String getDataFieldName() {
		return dataFieldName;
	}

	public void setDataFieldName(String dataFieldName) {
		this.dataFieldName = dataFieldName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIndexFieldName() {
		return indexFieldName;
	}

	public void setIndexFieldName(String indexFieldName) {
		this.indexFieldName = indexFieldName;
	}

	public String getIndexFieldHeader() {
		return indexFieldHeader;
	}

	public void setIndexFieldHeader(String indexFieldHeader) {
		this.indexFieldHeader = indexFieldHeader;
	}

	public int getFieldWidth() {
		return fieldWidth;
	}

	public void setFieldWidth(int fieldWidth) {
		this.fieldWidth = fieldWidth;
	}

	public boolean isSortable() {
		return sortable;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public boolean isFilter() {
		return filter;
	}

	public void setFilter(boolean filter) {
		this.filter = filter;
	}

	public String getFilterType() {
		return filterType;
	}

	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}

	public boolean isHiddable() {
		return hiddable;
	}

	public void setHiddable(boolean hiddable) {
		this.hiddable = hiddable;
	}

	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
}
