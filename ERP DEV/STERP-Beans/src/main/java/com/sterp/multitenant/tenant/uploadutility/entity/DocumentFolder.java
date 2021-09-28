package com.sterp.multitenant.tenant.uploadutility.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "document_folder")
public class DocumentFolder extends SuperBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4049572382688637993L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "folder_name")
	private String folderName;
	@Column(name = "parent_folder_id")
	private Long parentFolderId;
	@Column(name = "module_id")
	private long moduleId;
	private Long status;
	
	@Transient
	List<DocumentFolder> subDirectoriesList;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	public Long getParentFolderId() {
		return parentFolderId;
	}
	public void setParentFolderId(Long parentFolderId) {
		this.parentFolderId = parentFolderId;
	}
	public long getModuleId() {
		return moduleId;
	}
	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public List<DocumentFolder> getSubDirectoriesList() {
		return subDirectoriesList;
	}
	public void setSubDirectoriesList(List<DocumentFolder> subDirectoriesList) {
		this.subDirectoriesList = subDirectoriesList;
	}
}
