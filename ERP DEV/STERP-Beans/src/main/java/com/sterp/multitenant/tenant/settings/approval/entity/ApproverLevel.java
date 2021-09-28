package com.sterp.multitenant.tenant.settings.approval.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "approval_levels")
public class ApproverLevel implements Serializable {
	private static final long serialVersionUID = -3176638176823878802L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "parent_id")
	private Integer parentId;

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "level_name")
	private String levelName;

	@Column(name = "min_range")
	private double minRange;

	@Column(name = "max_range")
	private double maxRange;

	@Column(name = "department_wise")
	private int departmentWise; // 0 - NO/1 - YES

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id")
	private List<Approvers> approvers;

	@Column(name = "approval_type")
	private String approvalType;

	@Column(name = "min_approval_from_group")
	private Integer minApprovalFromGroup;

	@Column(name = "approval_group_id")
	private Long approvalGroupId;
	/*
	 * @OneToMany
	 * 
	 * @JoinColumn(name = "user_id") private Collection<UserEntity> users;
	 */

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public double getMinRange() {
		return minRange;
	}

	public void setMinRange(double minRange) {
		this.minRange = minRange;
	}

	public double getMaxRange() {
		return maxRange;
	}

	public void setMaxRange(double maxRange) {
		this.maxRange = maxRange;
	}

	public int getDepartmentWise() {
		return departmentWise;
	}

	public void setDepartmentWise(int departmentWise) {
		this.departmentWise = departmentWise;
	}

	public List<Approvers> getApprovers() {
		return approvers;
	}

	public void setApprovers(List<Approvers> approvers) {
		this.approvers = approvers;
	}

	public String getApprovalType() {
		return approvalType;
	}

	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}

	public Integer getMinApprovalFromGroup() {
		return minApprovalFromGroup;
	}

	public void setMinApprovalFromGroup(Integer minApprovalFromGroup) {
		this.minApprovalFromGroup = minApprovalFromGroup;
	}

	public Long getApprovalGroupId() {
		return approvalGroupId;
	}

	public void setApprovalGroupId(Long approvalGroupId) {
		this.approvalGroupId = approvalGroupId;
	}

}
