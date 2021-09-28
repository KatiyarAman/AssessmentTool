package com.sterp.multitenant.tenant.assesmenttool.entity;

import javax.persistence.Embeddable;

@Embeddable
public class UploadedFileResource {
	
	private String filename;
	private String contentType;
	private Long filesize;
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public Long getFilesize() {
		return filesize;
	}
	public void setFilesize(Long filesize) {
		this.filesize = filesize;
	}
	
	
}
