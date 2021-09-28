package com.sterp.multitenant.tenant.model.request;

public class ModuleRequestModel {
	private String badge;
	private String description;
	private int displayOrder;
	private String icon;
	private String path;
	private Long status;
	private String title;
	private int actionId;
	private Integer parentModuleId;
	private String moduleCode;
	private String uiPath;
	private int menuDisplay;

	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	public String getBadge() {
		return badge;
	}

	public void setBadge(String badge) {
		this.badge = badge;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getParentModuleId() {
		return parentModuleId;
	}

	public void setParentModuleId(Integer parentModuleId) {
		this.parentModuleId = parentModuleId;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getUiPath() {
		return uiPath;
	}

	public void setUiPath(String uiPath) {
		this.uiPath = uiPath;
	}

	public int getMenuDisplay() {
		return menuDisplay;
	}

	public void setMenuDisplay(int menuDisplay) {
		this.menuDisplay = menuDisplay;
	}

}
