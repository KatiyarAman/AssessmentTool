package com.sterp.multitenant.tenant.settings.template.entity.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.sterp.multitenant.tenant.settings.template.entity.FieldIndexing;
import com.sterp.multitenant.tenant.settings.template.entity.FieldType;
import com.sterp.multitenant.tenant.settings.template.entity.FieldValidations;
import com.sterp.multitenant.tenant.settings.template.entity.Options;

public class CustomFieldResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5523442872765443041L;
	private int id;
	private String fieldName;
	private Integer parentFieldId;
	private String fieldHeader;
	private String fieldType;
	private Collection<Options> options;
	private String isMandatory;
	private Integer srcModule;
	private String errorMessage;
	private Long onChange;
	private Long onClick;
	private boolean defaultShow;
	private int showOn;
	private String entryType;
	private long module;
	private int displayOrder;
	private String dataSource;
	private boolean indexing;
	private List<CustomFieldResponse> childCustomFields;
	private List<FieldValidations> fieldValidations;
	private String tooltipType;
	private String tooltipTitle;
	private String tooltipContent;
	private String tooltipActivation;
	private boolean shortLink;
	private FieldIndexing fieldIndexing;
	private String type;
	private String moduleTitle;
	private Long status;

	public CustomFieldResponse() {
		// TODO Auto-generated constructor stub
	}

	public CustomFieldResponse(int id, String fieldName, String fieldHeader, String fieldType, String isMandatory,
			Integer srcModule, String errorMessage, Long onChange, Long onClick, boolean defaultShow, int showOn,
			String entryType, long module, int displayOrder, String dataSource, boolean indexing, Integer parentFieldId,
			String tooltipType, String tooltipTitle, String tooltipContent, String tooltipActivation, boolean shortLink,
			FieldIndexing fieldIndexing, String type, Long status) {
		this.id = id;
		this.fieldName = fieldName;
		this.fieldHeader = fieldHeader;
		this.fieldType = fieldType;
		this.isMandatory = isMandatory;
		this.srcModule = srcModule;
		this.errorMessage = errorMessage;
		this.onChange = onChange;
		this.onClick = onClick;
		this.defaultShow = defaultShow;
		this.showOn = showOn;
		this.entryType = entryType;
		this.module = module;
		this.displayOrder = displayOrder;
		this.dataSource = dataSource;
		this.indexing = indexing;
		this.parentFieldId = parentFieldId;
		this.tooltipType = tooltipType;
		this.tooltipTitle = tooltipTitle;
		this.tooltipContent = tooltipContent;
		this.tooltipActivation = tooltipActivation;
		this.shortLink = shortLink;
		this.fieldIndexing = fieldIndexing;
		this.type = type;
		this.status = status;
	}

	public CustomFieldResponse(int id, String fieldName, String fieldHeader, String fieldType, String isMandatory,
			Integer srcModule, String errorMessage, Long onChange, Long onClick, boolean defaultShow, int showOn,
			String entryType, long module, int displayOrder, String dataSource, boolean indexing, int parentFieldId,
			Long status) {
		this.id = id;
		this.fieldName = fieldName;
		this.fieldHeader = fieldHeader;
		this.fieldType = fieldType;
		this.isMandatory = isMandatory;
		this.srcModule = srcModule;
		this.errorMessage = errorMessage;
		this.onChange = onChange;
		this.onClick = onClick;
		this.defaultShow = defaultShow;
		this.showOn = showOn;
		this.entryType = entryType;
		this.module = module;
		this.displayOrder = displayOrder;
		this.dataSource = dataSource;
		this.indexing = indexing;
		this.parentFieldId = parentFieldId;
		this.status = status;
//		this.tooltipType = tooltipType;
//		this.tooltipTitle = tooltipTitle;
//		this.tooltipContent = tooltipContent;
//		this.tooltipActivation = tooltipActivation;
//		this.shortLink = shortLink;
	}

	public CustomFieldResponse(int id, String fieldName, String fieldHeader, String fieldType, String isMandatory,
			Integer srcModule, String errorMessage, Long onChange, Long onClick, boolean defaultShow, int showOn,
			String entryType, long module, String moduleTitle, int displayOrder, String dataSource, boolean indexing,
			Integer parentFieldId, String tooltipType, String tooltipTitle, String tooltipContent,
			String tooltipActivation, boolean shortLink, FieldIndexing fieldIndexing, String type,
			FieldType fieldTypeBean, Long status) {
		this.id = id;
		this.fieldName = fieldName;
		this.fieldHeader = fieldHeader;
		this.fieldType = fieldType;
		this.isMandatory = isMandatory;
		this.srcModule = srcModule;
		this.errorMessage = errorMessage;
		this.onChange = onChange;
		this.onClick = onClick;
		this.defaultShow = defaultShow;
		this.showOn = showOn;
		this.entryType = entryType;
		this.module = module;
		this.moduleTitle = moduleTitle;
		this.displayOrder = displayOrder;
		this.dataSource = dataSource;
		this.indexing = indexing;
		this.parentFieldId = parentFieldId;
		this.tooltipType = tooltipType;
		this.tooltipTitle = tooltipTitle;
		this.tooltipContent = tooltipContent;
		this.tooltipActivation = tooltipActivation;
		this.shortLink = shortLink;
		this.fieldIndexing = fieldIndexing;
		if (fieldTypeBean != null) {
			this.fieldIndexing.setFilterType(fieldTypeBean.getType().toString());
		}
//		this.fieldIndexing.setFilterType(fieldTypeBean.getType().toString());
		this.type = type;
		this.status = status;
//		this.fieldTypeBean = fieldTypeBean;

	}

	public CustomFieldResponse(FieldIndexing fieldIndexing) {
		this.fieldIndexing = fieldIndexing;
	}

	public CustomFieldResponse(String fieldName) {
		super();
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldHeader() {
		return fieldHeader;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFieldHeader(String fieldHeader) {
		this.fieldHeader = fieldHeader;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public Collection<Options> getOptions() {
		return options;
	}

	public void setOptions(Collection<Options> options) {
		this.options = options;
	}

	public String getIsMandatory() {
		return isMandatory;
	}

	public void setIsMandatory(String isMandatory) {
		this.isMandatory = isMandatory;
	}

	public String getModuleTitle() {
		return moduleTitle;
	}

	public void setModuleTitle(String moduleTitle) {
		this.moduleTitle = moduleTitle;
	}

	public Integer getSrcModule() {
		return srcModule;
	}

	public void setSrcModule(Integer srcModule) {
		this.srcModule = srcModule;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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

	public String getEntryType() {
		return entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	public long getModule() {
		return module;
	}

	public void setModule(long module) {
		this.module = module;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public boolean isIndexing() {
		return indexing;
	}

	public void setIndexing(boolean indexing) {
		this.indexing = indexing;
	}

	public Integer getParentFieldId() {
		return parentFieldId;
	}

	public void setParentFieldId(Integer parentFieldId) {
		this.parentFieldId = parentFieldId;
	}

	public List<CustomFieldResponse> getChildCustomFields() {
		return childCustomFields;
	}

	public void setChildCustomFields(List<CustomFieldResponse> childCustomFields) {
		this.childCustomFields = childCustomFields;
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

	public String getTooltipType() {
		return tooltipType;
	}

	public void setTooltipType(String tooltipType) {
		this.tooltipType = tooltipType;
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

	public FieldIndexing getFieldIndexing() {
		return fieldIndexing;
	}

	public void setFieldIndexing(FieldIndexing fieldIndexing) {
		this.fieldIndexing = fieldIndexing;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<FieldValidations> getFieldValidations() {
		return fieldValidations;
	}

	public void setFieldValidations(List<FieldValidations> fieldValidations) {
		this.fieldValidations = fieldValidations;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	

}
