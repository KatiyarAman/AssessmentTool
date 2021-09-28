package com.sterp.multitenant.tenant.settings.codegeneration.dto;

public class CodeGenerationRequestDto {
	
	private Long id;
	private Long moduleId;
	private Long customFieldId;
	private String prefix;
	private String otherPrefix;
	private Long moduleRefAsPrefix;
	private Long moduleRefCustomFieldId;
	private String delimiter;
	private String startFrom;
	private String codeFormat;
	private Integer charCount;
	private String codeBase;
	private Long codebaseRefCustomFieldId;
	private boolean fyReset;
	private Long status;
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
	public Long getCustomFieldId() {
		return customFieldId;
	}
	public void setCustomFieldId(Long customFieldId) {
		this.customFieldId = customFieldId;
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
	public Long getModuleRefAsPrefix() {
		return moduleRefAsPrefix;
	}
	public void setModuleRefAsPrefix(Long moduleRefAsPrefix) {
		this.moduleRefAsPrefix = moduleRefAsPrefix;
	}
	public Long getModuleRefCustomFieldId() {
		return moduleRefCustomFieldId;
	}
	public void setModuleRefCustomFieldId(Long moduleRefCustomFieldId) {
		this.moduleRefCustomFieldId = moduleRefCustomFieldId;
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
	public Long getCodebaseRefCustomFieldId() {
		return codebaseRefCustomFieldId;
	}
	public void setCodebaseRefCustomFieldId(Long codebaseRefCustomFieldId) {
		this.codebaseRefCustomFieldId = codebaseRefCustomFieldId;
	}
	public boolean isFyReset() {
		return fyReset;
	}
	public void setFyReset(boolean fyReset) {
		this.fyReset = fyReset;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "CodeGenerationRequestDto [id=" + id + ", moduleId=" + moduleId + ", customFieldId=" + customFieldId
				+ ", prefix=" + prefix + ", otherPrefix=" + otherPrefix + ", moduleRefAsPrefix=" + moduleRefAsPrefix
				+ ", moduleRefCustomFieldId=" + moduleRefCustomFieldId + ", delimiter=" + delimiter + ", startFrom="
				+ startFrom + ", codeFormat=" + codeFormat + ", charCount=" + charCount + ", codeBase=" + codeBase
				+ ", codebaseRefCustomFieldId=" + codebaseRefCustomFieldId + ", fyReset=" + fyReset + ", status="
				+ status + "]";
	}
	
	
	
	

	
	
	
}
