package com.sterp.multitenant.tenant.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authority")
public class Authority implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7298178671272090129L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Enumerated(EnumType.STRING)
	private AuthorityType name;

	public Authority() {
	}

	public Authority(AuthorityType name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AuthorityType getName() {
		return name;
	}

	public void setName(AuthorityType name) {
		this.name = name;
	}
}