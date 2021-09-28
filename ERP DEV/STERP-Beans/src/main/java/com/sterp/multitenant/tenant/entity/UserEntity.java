package com.sterp.multitenant.tenant.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence. NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.sterp.multitenant.tenant.enumtype.AuthProvider;
import com.sterp.multitenant.tenant.model.StatusDetails;
import com.sterp.multitenant.tenant.modules.entity.Roles;
import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "user")
@NamedStoredProcedureQueries(value = {
		@NamedStoredProcedureQuery(procedureName = "createBranchUserRole", name = "createBranchUserRole", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "action", type = String.class) }) })
public class UserEntity  implements Serializable {
	private static final long serialVersionUID = -4851032432869845244L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String uuid;
	@NotNull
	@NotEmpty(message = "Username is required.")
	@Column(nullable = false, unique = true)
	private String username;
	@NotEmpty
	private String password;
	@NotNull
	@NotEmpty
	@Email(message = "invalid email address.")
	private String email;
	@NotNull
	private Long mobile;
	@Column(name = "password_expiry")
	private Integer passwordExpiry;
	
	@Transient
	private Map<String,Object> infoApproval;
	//@Enumerated(EnumType.ORDINAL)
	//@Column(name = "status")
	//private StatusEnum status;
	
	@Column(name = "status")
	private Long status;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "status", insertable = false, updatable = false)
	private StatusDetails statusDetail;

	public StatusDetails getStatusDetail() {
		return statusDetail;
	}

	public void setStatusDetail(StatusDetails statusDetail) {
		this.statusDetail = statusDetail;
	}

	@Column(name = "created_date", insertable = true, updatable = false)
	private Date createdAt;

	@Column(name = "modified_date")
	private Date modifiedAt;

	@Column(name = "company_id")
	private Long companyId;

	@Column(name = "branch_id")
	private Long branchId;

	@Column(name = "created_by", insertable = true, updatable = false)
	private Long createdBy;
	@Column(name = "modified_by")
	private Long modifiedBy;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_authority", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "authority_id") })
	private Set<Authority> authorities = new HashSet<>();

	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "user_id")
	private Set<UserBranchAssign> branchAssign;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	Roles roles;

	@Column(name = "first_login")
	private boolean firstLogin;

	@NotNull
	@Enumerated(EnumType.STRING)
	private AuthProvider provider;

	@Column(name = "default_login_branch_id")
	private Long defaultLoginBranch;

	@Column(name = "employee_id")
	private Long employeeId;

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public Integer getPasswordExpiry() {
		return passwordExpiry;
	}

	public void setPasswordExpiry(Integer passwordExpiry) {
		this.passwordExpiry = passwordExpiry;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public boolean isFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

	public AuthProvider getProvider() {
		return provider;
	}

	public void setProvider(AuthProvider provider) {
		this.provider = provider;
	}

	public Long getDefaultLoginBranch() {
		return defaultLoginBranch;
	}

	public void setDefaultLoginBranch(Long defaultLoginBranch) {
		this.defaultLoginBranch = defaultLoginBranch;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Set<UserBranchAssign> getBranchAssign() {
		return branchAssign;
	}

	public void setBranchAssign(Set<UserBranchAssign> branchAssign) {
		this.branchAssign = branchAssign;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Map<String, Object> getInfoApproval() {
		return infoApproval;
	}

	public void setInfoApproval(Map<String, Object> infoApproval) {
		this.infoApproval = infoApproval;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", uuid=" + uuid + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", mobile=" + mobile + ", passwordExpiry=" + passwordExpiry + ", infoApproval="
				+ infoApproval + ", status=" + status + ", statusDetail=" + statusDetail + ", createdAt=" + createdAt
				+ ", modifiedAt=" + modifiedAt + ", companyId=" + companyId + ", branchId=" + branchId + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + ", authorities=" + authorities + ", branchAssign="
				+ branchAssign + ", roles=" + roles + ", firstLogin=" + firstLogin + ", provider=" + provider
				+ ", defaultLoginBranch=" + defaultLoginBranch + ", employeeId=" + employeeId + "]";
	}
}