package com.sterp.multitenant.tenant.settings.codegeneration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sterp.multitenant.tenant.settings.template.entity.CustomField;
import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "code_generation")
public class CodeGeneration extends SuperBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4156506891310572736L;

	@Id
	@Column(name = "Id", length = 11)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "module_id")
	private Long moduleId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "custom_field_id")
	private CustomField field;
	
	@Column(name = "prefix")
	private String prefix;
	
	@Column(name = "other_prefix")
	private String otherPrefix;
	
	@Column(name = "module_prefix")
	private Long modulePrefix;
	
	@Column(name = "module_prefix_ref")
	private Long modulePrefixRef;
	
	@Column(name = "delimiter")
	private String delimiter;
	
	@Column(name = "start_from")
	private String startFrom;
	
	@Column(name = "code_format")
	private String codeFormat;
	
	@Column(name = "char_count")
	private Integer charCount;
	
	@Column(name = "code_base")
	private String codeBase;
	
	@Column(name = "code_base_ref")
	private Long codeBaseRef;
	
	@Column(name = "fy_reset")
	private boolean fyReset;
	
//	@Column(name = "status")
//	@Enumerated(EnumType.ORDINAL)
//	private StatusEnum status;
	
//	@Column(name="status")
//	private Long status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public CustomField getField() {
		return field;
	}

	public void setField(CustomField field) {
		this.field = field;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getOtherPrefix() {
		return otherPrefix;
	}

	public void setOtherPrefix(String otherPrefix) {
		this.otherPrefix = otherPrefix;
	}

	public Long getModulePrefix() {
		return modulePrefix;
	}

	public void setModulePrefix(Long modulePrefix) {
		this.modulePrefix = modulePrefix;
	}

	public Long getModulePrefixRef() {
		return modulePrefixRef;
	}

	public void setModulePrefixRef(Long modulePrefixRef) {
		this.modulePrefixRef = modulePrefixRef;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public String getStartFrom() {
		return startFrom;
	}

	public void setStartFrom(String startFrom) {
		this.startFrom = startFrom;
	}

	public String getCodeFormat() {
		return codeFormat;
	}

	public void setCodeFormat(String codeFormat) {
		this.codeFormat = codeFormat;
	}

	public Integer getCharCount() {
		return charCount;
	}

	public void setCharCount(Integer charCount) {
		this.charCount = charCount;
	}

	public String getCodeBase() {
		return codeBase;
	}

	public void setCodeBase(String codeBase) {
		this.codeBase = codeBase;
	}

	public Long getCodeBaseRef() {
		return codeBaseRef;
	}

	public void setCodeBaseRef(Long codeBaseRef) {
		this.codeBaseRef = codeBaseRef;
	}

	public boolean isFyReset() {
		return fyReset;
	}

	public void setFyReset(boolean fyReset) {
		this.fyReset = fyReset;
	}

	@Override
	public String toString() {
		return "CodeGeneration [id=" + id + ", moduleId=" + moduleId + ", field=" + field + ", prefix=" + prefix
				+ ", otherPrefix=" + otherPrefix + ", modulePrefix=" + modulePrefix + ", modulePrefixRef="
				+ modulePrefixRef + ", delimiter=" + delimiter + ", startFrom=" + startFrom + ", codeFormat="
				+ codeFormat + ", charCount=" + charCount + ", codeBase=" + codeBase + ", codeBaseRef=" + codeBaseRef
				+ ", fyReset=" + fyReset + "]";
	}

	

	
}
