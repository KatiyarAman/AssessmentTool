package com.sterp.multitenant.tenant.modules.response.dto;

public class SearchModuleFilterDTO {
	private long id;
	private String url;
	private String apiPath;
	private String name;
	private String icon;

	public SearchModuleFilterDTO(long id, String url, String apiPath, String name, String icon) {
		this.id = id;
		this.url = url;
		this.apiPath = apiPath;
		this.name = name;
		this.icon = icon;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getApiPath() {
		return apiPath;
	}

	public void setApiPath(String apiPath) {
		this.apiPath = apiPath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}
