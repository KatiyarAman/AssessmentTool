 package com.sterp.multitenant.tenant.settings.approval.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "approval_group")
public class ApprovalGroup extends SuperBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7996633983043488008L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "approval_group_name")
	private String approvalGroupName;
	
//	@Column(name = "status")
//	@Enumerated(EnumType.ORDINAL)
//	private StatusEnum status;
	
//	@Column(name="status")
//	private Long status;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "group_id")
	private Set<ApprovalGroupUsers> approvalGroupUsers;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getApprovalGroupName() {
		return approvalGroupName;
	}

	public void setApprovalGroupName(String approvalGroupName) {
		this.approvalGroupName = approvalGroupName;
	}

	public Set<ApprovalGroupUsers> getApprovalGroupUsers() {
		return approvalGroupUsers;
	}

	public void setApprovalGroupUsers(Set<ApprovalGroupUsers> approvalGroupUsers) {
		this.approvalGroupUsers = approvalGroupUsers;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ApprovalGroup [id=" + id + ", approvalGroupName=" + approvalGroupName + ", approvalGroupUsers="
				+ approvalGroupUsers + "]";
	}

	
}
