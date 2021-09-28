package com.sterp.multitenant.tenant.modules.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "modules")
@JsonIgnoreProperties(value = "actions")
public class Modules extends SuperBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5650646375787976934L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "display_order")
	private int displayOrder;
	
//	@Enumerated(EnumType.ORDINAL)
//	private StatusEnum status;
	
//	@Column(name = "status")
//	private Long status;

	@Column(name = "parent_id")
	private Long parentId;

//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "parent_id", updatable = false, insertable = false)
//	private Collection<Modules> modules;

	@Type(type = "text")
	private String path;
	@Column(name = "api_path")
	private String apiPath;
	private String title;
	private String icon;
	@Column(name = "class")
	private String className;
	@Column(name = "is_external_link")
	private boolean externalLink;
	@Column(name = "module_type")
	private String moduleType;
	@Column(name = "badge")
	private String badge;
	@Column(name = "badge_class")
	private String badgeClass;
	
	@Column(name = "form_type")
	private String formType;
	
	@Column(name = "list_type")
	private String listType;
	
	@Transient
	List<Modules> subModules;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "module_actions_mapping", joinColumns = {
			@JoinColumn(name = "module_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "action_id", nullable = false) })
	private Set<Actions> actions = new HashSet<Actions>();

	@Column(name = "server_class_name")
	private String serverClassName;

	@Column(name = "columns")
	private String columns;
	
//	@Transient
//	private List<ModuleActionsMapping> actionsMappings;

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	

//	public Collection<Modules> getModules() {
//		return modules;
//	}
//
//	public void setModules(Collection<Modules> modules) {
//		this.modules = modules;
//	}

//	public Long getStatus() {
//		return status;
//	}
//
//	public void setStatus(Long status) {
//		this.status = status;
//	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public long getId() {
		return id;
	}

	public String getApiPath() {
		return apiPath;
	}

	public void setApiPath(String apiPath) {
		this.apiPath = apiPath;
	}

	public Set<Actions> getActions() {
		return actions;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setActions(Set<Actions> actions) {
		this.actions = actions;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public List<Modules> getSubModules() {
		return subModules;
	}

	public void setSubModules(List<Modules> subModules) {
		this.subModules = subModules;
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

	public String getServerClassName() {
		return serverClassName;
	}

	public void setServerClassName(String serverClassName) {
		this.serverClassName = serverClassName;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}
	
//	public List<ModuleActionsMapping> getActionsMappings() {
//		return actionsMappings;
//	}
//
//	public void setActionsMappings(List<ModuleActionsMapping> actionsMappings) {
//		this.actionsMappings = actionsMappings;
//	}

}
