package com.sterp.multitenant.tenant.settings.template.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fields_indexing")
public class FieldIndexing implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1059321201111153593L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "index_field_name")
	private String indexFieldName;

	@Column(name = "index_field_header")
	private String indexFieldHeader;

	@Column(name = "field_width")
	private int fieldWidth;

	@Column(name = "sortable")
	private boolean sortable;

	@Column(name = "filter")
	private boolean filter;

	@Column(name = "filter_type")
	private String filterType;

	@Column(name = "hiddable")
	private boolean hiddable;

	@Column(name = "display")
	private boolean display;

	@Column(name = "display_order")
	private int displayOrder;

	@Column(name = "data_field_name")
	private String dataFieldName;

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

	public String getDataFieldName() {
		return dataFieldName;
	}

	public void setDataFieldName(String dataFieldName) {
		this.dataFieldName = dataFieldName;
	}

	@Override
	public String toString() {
		return "FieldIndexing [id=" + id + ", indexFieldName=" + indexFieldName + ", indexFieldHeader="
				+ indexFieldHeader + ", fieldWidth=" + fieldWidth + ", sortable=" + sortable + ", filter=" + filter
				+ ", filterType=" + filterType + ", hiddable=" + hiddable + ", display=" + display + ", displayOrder="
				+ displayOrder + ", dataFieldName=" + dataFieldName + "]";
	}

}
