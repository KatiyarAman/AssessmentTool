package com.sterp.multitenant.tenant.assesmenttool.entity.dto;

public class HiringRoundDto {
	private Long id;
	private String roundName;
	private String mode;
	private Long responsiblePersonId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoundName() {
		return roundName;
	}
	public void setRoundName(String roundName) {
		this.roundName = roundName;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public Long getResponsiblePersonId() {
		return responsiblePersonId;
	}
	public void setResponsiblePersonId(Long responsiblePersonId) {
		this.responsiblePersonId = responsiblePersonId;
	}
	
}
