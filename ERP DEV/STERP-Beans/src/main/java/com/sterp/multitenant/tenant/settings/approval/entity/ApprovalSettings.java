package com.sterp.multitenant.tenant.settings.approval.entity;

import java.io.Serializable;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.sterp.multitenant.tenant.settings.approval.enumtype.ApprovalBased;
import com.sterp.multitenant.tenant.settings.approval.enumtype.ApprovalSystem;
import com.sterp.multitenant.tenant.settings.approval.enumtype.BulkApprove;
import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity(name = "approval_config")
public class ApprovalSettings extends SuperBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3654922523675623841L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "module_id")
	private long moduleId;

	@Column(name = "type_id")
	private long typeId;

	@Column(name = "approval_count")
	private Long approvalCount;

	@Column(name = "approval_system")
	@Enumerated(EnumType.STRING)
	private ApprovalSystem approvalSystem;

	@Column(name = "min_approvals")
	private long minApprovals;

	@Column(name = "approval_base")
	@Enumerated(EnumType.ORDINAL)
	private ApprovalBased approvalBased;

	@Column(name = "bulk_approval")
	@Enumerated(EnumType.ORDINAL)
	private BulkApprove bulkApproval;
	
//	@Column(name = "status")
//	@Enumerated(EnumType.ORDINAL)
//	private StatusEnum status;
	
//	@Column(name="status")
//	private Long status;

	public ApprovalBased getApprovalBased() {
		return approvalBased;
	}

	public void setApprovalBased(ApprovalBased approvalBased) {
		this.approvalBased = approvalBased;
	}

	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id")
	private List<ApproverLevel> approverAssignList;

	@Transient
	private String submoduleTitle;

//	@OneToMany
//	@JoinColumn(name = "parentId")
//	List<ApprovalDetails> approvalDetailsList;

//	public List<ApprovalDetails> getApprovalDetailsList() {
//		return approvalDetailsList;
//	}
//
//	public void setApprovalDetailsList(List<ApprovalDetails> approvalDetailsList) {
//		this.approvalDetailsList = approvalDetailsList;
//	}

	public String getSubmoduleTitle() {
		return submoduleTitle;
	}

	public void setSubmoduleTitle(String submoduleTitle) {
		this.submoduleTitle = submoduleTitle;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public List<ApproverLevel> getApproverAssignList() {
		return approverAssignList;
	}

	public void setApproverAssignList(List<ApproverLevel> approverAssignList) {
		this.approverAssignList = approverAssignList;
	}

	public ApprovalSystem getApprovalSystem() {
		return approvalSystem;
	}

	public void setApprovalSystem(ApprovalSystem approvalSystem) {
		this.approvalSystem = approvalSystem;
	}

	public long getMinApprovals() {
		return minApprovals;
	}

	public void setMinApprovals(long minApprovals) {
		this.minApprovals = minApprovals;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BulkApprove getBulkApproval() {
		return bulkApproval;
	}

	public void setBulkApproval(BulkApprove bulkApproval) {
		this.bulkApproval = bulkApproval;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getModuleId() {
		return moduleId;
	}

	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}

	public Long getApprovalCount() {
		return approvalCount;
	}

	public void setApprovalCount(Long approvalCount) {
		this.approvalCount = approvalCount;
	}

	@Override
	public String toString() {
		return "ApprovalSettings [id=" + id + ", moduleId=" + moduleId + ", typeId=" + typeId + ", approvalCount="
				+ approvalCount + ", approvalSystem=" + approvalSystem + ", minApprovals=" + minApprovals
				+ ", approvalBased=" + approvalBased + ", bulkApproval=" + bulkApproval + ", approverAssignList="
				+ approverAssignList + ", submoduleTitle=" + submoduleTitle + "]";
	}

	
	

}
