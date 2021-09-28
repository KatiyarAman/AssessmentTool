package com.sterp.multitenant.tenant.assesmenttool.entity.dto;

public class ErrorDto {
	private String title;
	private String message;
	private Long status;
	private long timestamp;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public ErrorDto(String title, String message, Long status) {
		super();
		this.title = title;
		this.message = message;
		this.status = status;
	}
	public ErrorDto() {
		super();
	}
	
	
}
