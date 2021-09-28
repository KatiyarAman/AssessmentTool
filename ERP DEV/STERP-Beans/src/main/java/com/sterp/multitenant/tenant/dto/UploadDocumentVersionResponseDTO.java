package com.sterp.multitenant.tenant.dto;

public class UploadDocumentVersionResponseDTO {

	private Long versionId;
	private Long versionNo;
	private String fileName;
	private String filePath;
	public Long getVersionId() {
		return versionId;
	}
	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}
	public Long getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public UploadDocumentVersionResponseDTO(Long versionId, Long versionNo, String fileName, String filePath) {
		super();
		this.versionId = versionId;
		this.versionNo = versionNo;
		this.fileName = fileName;
		this.filePath = filePath;
	}
	
	
	
}
