package com.sterp.multitenant.tenant.settings.template.entity.dto;

import java.io.Serializable;
import java.util.List;

import com.sterp.multitenant.tenant.settings.template.entity.FieldValidations;

public class CustomFieldRequestDto implements Serializable {
	private static final long serialVersionUID = 198687171983929223L;
	private int id;
	private int parentFieldId;
	private String fieldName;
	private String fieldHeader;
	private int fieldType;
	private String isMandatory;
	private String errorMessage;
	private String type;
	private int srcModule;
	private Long onChange;
	private Long onClick;
	private boolean defaultShow;
	private int showOn;
	private int module;
	private String entryType;
	private int displayOrder;
	private String dataSource;
	private Long status;
	private boolean indexing;
	private List<OptionsDto> options;
	private String tooltipType;
	private String tooltipTitle;
	private String tooltipContent;
	private String tooltipActivation;
	private boolean shortLink;
	private FieldIndexingDTO fieldIndexing;
	private List<FieldValidations> fieldValidations;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFieldHeader() {
		return fieldHeader;
	}

	public void setFieldHeader(String fieldHeader) {
		this.fieldHeader = fieldHeader;
	}

	public int getFieldType() {
		return fieldType;
	}

	public void setFieldType(int fieldType) {
		this.fieldType = fieldType;
	}

	public String getIsMandatory() {
		return isMandatory;
	}

	public void setIsMandatory(String isMandatory) {
		this.isMandatory = isMandatory;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSrcModule() {
		return srcModule;
	}

	public void setSrcModule(int srcModule) {
		this.srcModule = srcModule;
	}

	public int getModule() {
		return module;
	}

	public void setModule(int module) {
		this.module = module;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public boolean isIndexing() {
		return indexing;
	}

	public void setIndexing(boolean indexing) {
		this.indexing = indexing;
	}

	public List<OptionsDto> getOptions() {
		return options;
	}

	public void setOptions(List<OptionsDto> options) {
		this.options = options;
	}

	public int getParentFieldId() {
		return parentFieldId;
	}

	public void setParentFieldId(int parentFieldId) {
		this.parentFieldId = parentFieldId;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getTooltipType() {
		return tooltipType;
	}

	public void setTooltipType(String tooltipType) {
		this.tooltipType = tooltipType;
	}

	public String getTooltipTitle() {
		return tooltipTitle;
	}

	public void setTooltipTitle(String tooltipTitle) {
		this.tooltipTitle = tooltipTitle;
	}

	public String getTooltipContent() {
		return tooltipContent;
	}

	public void setTooltipContent(String tooltipContent) {
		this.tooltipContent = tooltipContent;
	}

	public String getTooltipActivation() {
		return tooltipActivation;
	}

	public void setTooltipActivation(String tooltipActivation) {
		this.tooltipActivation = tooltipActivation;
	}

	public boolean isShortLink() {
		return shortLink;
	}

	public void setShortLink(boolean shortLink) {
		this.shortLink = shortLink;
	}

	public String getEntryType() {
		return entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	public FieldIndexingDTO getFieldIndexing() {
		return fieldIndexing;
	}

	public void setFieldIndexing(FieldIndexingDTO fieldIndexing) {
		this.fieldIndexing = fieldIndexing;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public List<FieldValidations> getFieldValidations() {
		return fieldValidations;
	}

	public void setFieldValidations(List<FieldValidations> fieldValidations) {
		this.fieldValidations = fieldValidations;
	}

	public Long getOnChange() {
		return onChange;
	}

	public void setOnChange(Long onChange) {
		this.onChange = onChange;
	}

	public Long getOnClick() {
		return onClick;
	}

	public void setOnClick(Long onClick) {
		this.onClick = onClick;
	}

	public boolean isDefaultShow() {
		return defaultShow;
	}

	public void setDefaultShow(boolean defaultShow) {
		this.defaultShow = defaultShow;
	}

	public int getShowOn() {
		return showOn;
	}

	public void setShowOn(int showOn) {
		this.showOn = showOn;
	}
	
	

}
