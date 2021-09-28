package com.sterp.multitenant.tenant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_branch_assign")
public class UserBranchAssign implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7791637397970145675L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "branch_id")
	private Long branchId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}

	@Override
	public String toString() {
		return "UserBranchAssign [id=" + id + ", userId=" + userId + ", branchId=" + branchId + "]";
	}

}
