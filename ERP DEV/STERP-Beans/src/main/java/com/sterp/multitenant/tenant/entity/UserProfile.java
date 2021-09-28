package com.sterp.multitenant.tenant.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.sterp.multitenant.tenant.enumtype.AuthProvider;

@Entity(name = "UserProfile")
@Table(name = "user_profiles")
public class UserProfile implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_profile_id")
	private int profileId;
	@Column(name = "type", unique = true)
	private String type;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "modified_at")
	private Date modifiedAt;
	@NotNull
	@Enumerated(EnumType.STRING)
	private AuthProvider provider;

	public UserProfile() {
	}

	public UserProfile(String type) {
		this.type = type;
		this.createdAt = new Date(System.currentTimeMillis());
		this.modifiedAt = new Date(System.currentTimeMillis());
	}

	public int getProfileId() {
		return this.profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedAt() {
		return this.modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public AuthProvider getProvider() {
		return provider;
	}

	public void setProvider(AuthProvider provider) {
		this.provider = provider;
	}

}
