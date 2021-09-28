package com.sterp.multitenant.tenant.modules.dto;

import java.util.List;

public class ModuleDto {
	private long id;
	private String title;
	private String path;
	private String apiPath;
	private Long parentId;
	private String moduleType;
	private String icon;
	private String className;
	private boolean externalLink;
	private String badge;
	private String badgeClass;
	private String formType;
	private int columns;
	private int displayOrder;
	private Long status;
	private String listType;
	private String serverClassName;

	private List<ActionMappingDto> actionMapping;

	
	public ModuleDto(long id, String title, String path, String apiPath, String moduleType) {
		this.id = id;
		this.title = title;
		this.path = path;
		this.apiPath = apiPath;
		this.moduleType = moduleType;
	}

	public ModuleDto() {
		super();
	}
	
	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getApiPath() {
		return apiPath;
	}
	public void setApiPath(String apiPath) {
		this.apiPath = apiPath;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getModuleType() {
		return moduleType;
	}
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	public boolean isExternalLink() {
		return externalLink;
	}
	public void setExternalLink(boolean externalLink) {
		this.externalLink = externalLink;
	}
	public String getBadge() {
		return badge;
	}
	public void setBadge(String badge) {
		this.badge = badge;
	}
	public String getBadgeClass() {
		return badgeClass;
	}
	public void setBadgeClass(String badgeClass) {
		this.badgeClass = badgeClass;
	}
	public String getFormType() {
		return formType;
	}
	public void setFormType(String formType) {
		this.formType = formType;
	}
	public int getColumns() {
		return columns;
	}
	public void setColumns(int columns) {
		this.columns = columns;
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

	public List<ActionMappingDto> getActionMapping() {
		return actionMapping;
	}
	public void setActionMapping(List<ActionMappingDto> actionMapping) {
		this.actionMapping = actionMapping;
	}
	public String getServerClassName() {
		return serverClassName;
	}
	public void setServerClassName(String serverClassName) {
		this.serverClassName = serverClassName;
	}
	
}
