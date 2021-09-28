package com.sterp.multitenant.tenant.assesmenttool.entity.dto;

public class IndustryTypeDto {
	private long id;
	private String name;
	private Long status;
	private String description;
	
	public IndustryTypeDto(long id, String name, String description, Long status) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
	}
	
	public IndustryTypeDto() {
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
