package com.sterp.multitenant.tenant.settings.template.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "custom_fields")
@NamedStoredProcedureQueries(value = {
		@NamedStoredProcedureQuery(name = "procedure-one", procedureName = "findAllCustomFields"),
		@NamedStoredProcedureQuery(name = "procedure-two", procedureName = "findCustomFieldByID", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = Integer.class) }),
		@NamedStoredProcedureQuery(name = "procedure-third", procedureName = "findCustomFieldCountByID", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "customField_count", type = Integer.class) }) })
public class CustomField extends SuperBean implements Serializable {
	private static final long serialVersionUID = -8076743459828747076L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "parent_field_id")
	private Integer parentFieldId;

//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "parent_field_id")
//	private Set<CustomField> customFields; // `parent_field_id` bigint DEFAULT '0',
	@Column(name = "field_name")
	private String fieldName;// `field_name` varchar(225) NOT NULL,
	@Column(name = "field_header")
	private String fieldHeader;// `field_header` text NOT NULL,
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "field_type")
	private FieldType fieldTypeObject;// `field_type` varchar(45) NOT NULL,

	@Transient
	private int fieldType;

	public FieldType getFieldTypeObject() {
		return fieldTypeObject;
	}

	public void setFieldTypeObject(FieldType fieldTypeObject) {
		this.fieldTypeObject = fieldTypeObject;
	}

	public void setFieldType(int fieldType) {
		this.fieldType = fieldType;
	}

	@Column(name = "is_mandatory")
	private String isMandatory;// `is_mandatory` varchar(100) DEFAULT NULL,
	@Column(name = "error_message")
	private String errorMessage;// `error_message` text,
	@Column(name = "type")
	private String type;// `type` varchar(45) NOT NULL,
	@Column(name = "src_module")
	private Integer srcModule;// `src_module` bigint DEFAULT NULL,
	@Column(name = "on_change")
	private Long onChange;// `on_change` varchar(200) DEFAULT NULL,
	@Column(name = "on_click")
	private Long onClick;// `on_click` varchar(200) DEFAULT NULL,
	@Column(name = "default_show")
	private boolean defaultShow;// `default_show` tinyint DEFAULT '1',
	@Column(name = "show_on")
	private int showOn;// `show_on` bigint DEFAULT '0',
	@Column(name = "entry_type")
	private String entryType;// `entry_type` varchar(45) DEFAULT NULL,
	@Column(name = "module_id")
	private long module;// `module_id` bigint NOT NULL,
	@Column(name = "display_order")
	private int displayOrder;// `display_order` int NOT NULL,
	@Column(name = "data_src")
	private String dataSource;// `data_src` varchar(45) DEFAULT NULL,
//	@Enumerated(EnumType.ORDINAL)
//	@Column(name = "status")
//	private StatusEnum status;// `status` varchar(45) DEFAULT NULL,
//	@Column(name = "status")
//	private Long status;
	@Column(name = "is_indexing")
	private boolean indexing;// `is_indexing` tinyint DEFAULT '0',

	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "custom_field_id")
	@Where(clause="status = 1")
	private List<Options> options;
	
	@Column(name = "tooltip_type")
	private String tooltipType;
	@Column(name = "tooltip_title")
	private String tooltipTitle;
	@Column(name = "tooltip_content")
	private String tooltipContent;
	@Column(name = "tootip_activation")
	private String tooltipActivation;
	@Column(name = "short_link")
	private boolean shortLink;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "field_indexing_id")
	private FieldIndexing fieldIndexing;
	@Transient
	private List<CustomField> fields;

	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "custom_field_id")
	private List<FieldValidations> fieldValidations;

	@Column(name = "short_link_enabled")
	private boolean shortLinkEnabled;
	
	//@Column(name="folder_flag")
	//private boolean folderFlag;
    
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public Set<CustomField> getCustomFields() {
//		return customFields;
//	}
//
//	public void setCustomFields(Set<CustomField> customFields) {
//		this.customFields = customFields;
//	}

	public Integer getParentFieldId() {
		return parentFieldId;
	}

	public boolean isShortLinkEnabled() {
		return shortLinkEnabled;
	}

	public void setShortLinkEnabled(boolean shortLinkEnabled) {
		this.shortLinkEnabled = shortLinkEnabled;
	}

	public void setParentFieldId(Integer parentFieldId) {
		this.parentFieldId = parentFieldId;
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

	public void setFieldHeader(String fieldHeader) {
		this.fieldHeader = fieldHeader;
	}

	public int getFieldType() {
		return fieldType;
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

	public Integer getSrcModule() {
		return srcModule;
	}

	public void setSrcModule(Integer srcModule) {
		this.srcModule = srcModule;
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

	public List<Options> getOptions() {
		return options;
	}

	public void setOptions(List<Options> options) {
		this.options = options;
	}

	public boolean isDefaultShow() {
		return defaultShow;
	}

	public void setDefaultShow(boolean defaultShow) {
		this.defaultShow = defaultShow;
	}

	public List<CustomField> getFields() {
		return fields;
	}

	public void setFields(List<CustomField> fields) {
		this.fields = fields;
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

	public FieldIndexing getFieldIndexing() {
		return fieldIndexing;
	}

	public void setFieldIndexing(FieldIndexing fieldIndexing) {
		this.fieldIndexing = fieldIndexing;
	}

	public List<FieldValidations> getFieldValidations() {
		return fieldValidations;
	}

	public void setFieldValidations(List<FieldValidations> fieldValidations) {
		this.fieldValidations = fieldValidations;
	}

	@Override
	public String toString() {
		return "CustomField [id=" + id + ", parentFieldId=" + parentFieldId + ", fieldName=" + fieldName
				+ ", fieldHeader=" + fieldHeader + ", fieldTypeObject=" + fieldTypeObject + ", fieldType=" + fieldType
				+ ", isMandatory=" + isMandatory + ", errorMessage=" + errorMessage + ", type=" + type + ", srcModule="
				+ srcModule + ", onChange=" + onChange + ", onClick=" + onClick + ", defaultShow=" + defaultShow
				+ ", showOn=" + showOn + ", entryType=" + entryType + ", module=" + module + ", displayOrder="
				+ displayOrder + ", dataSource=" + dataSource + ", indexing=" + indexing + ", options=" + options
				+ ", tooltipType=" + tooltipType + ", tooltipTitle=" + tooltipTitle + ", tooltipContent="
				+ tooltipContent + ", tooltipActivation=" + tooltipActivation + ", shortLink=" + shortLink
				+ ", fieldIndexing=" + fieldIndexing + ", fields=" + fields + ", fieldValidations=" + fieldValidations
				+ ", shortLinkEnabled=" + shortLinkEnabled + "]";
	}
	
}
