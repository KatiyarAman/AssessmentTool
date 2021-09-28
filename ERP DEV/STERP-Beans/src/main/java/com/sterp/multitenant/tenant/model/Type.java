package com.sterp.multitenant.tenant.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sterp.multitenant.tenant.modules.entity.Modules;
import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "type")
public class Type extends SuperBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "type")
	private String type;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "type_module_mapping",
			joinColumns = @JoinColumn(name = "type_id"),
			inverseJoinColumns = @JoinColumn(name = "module_id")
	)
	private Set<Modules> modules = new HashSet<Modules>();

	//private Long status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Modules> getModules() {
		return modules;
	}

	public void setModules(Set<Modules> modules) {
		this.modules = modules;
	}

	@Override
	public String toString() {
		return "Type [id=" + id + ", type=" + type + ", modules=" + modules + "]";
	}

	

}
