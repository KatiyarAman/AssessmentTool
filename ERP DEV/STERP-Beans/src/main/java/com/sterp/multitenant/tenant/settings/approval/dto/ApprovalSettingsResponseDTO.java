package com.sterp.multitenant.tenant.settings.approval.dto;

import java.io.Serializable;

import com.sterp.multitenant.tenant.settings.approval.enumtype.ApprovalBased;
import com.sterp.multitenant.tenant.settings.approval.enumtype.ApprovalSystem;
import com.sterp.multitenant.tenant.settings.approval.enumtype.BulkApprove;

/**
 * @author Shivit
 *
 */
public class ApprovalSettingsResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5557736920166202353L;
	private Integer id;
	private String moduleName;
	private String typeName;
	private Long approvalCount;
	private ApprovalSystem approvalSystem;
	private long minApprovals;
	private ApprovalBased approvalBased;
	private BulkApprove bulkApproval;
	private Long status;
	
	public ApprovalSettingsResponseDTO(Integer id, String moduleName, String typeName, Long approvalCount,
			ApprovalSystem approvalSystem, long minApprovals, ApprovalBased approvalBased, BulkApprove bulkApproval,Long status) {
		this.id = id;
		this.moduleName = moduleName;
		this.typeName = typeName;
		this.approvalCount = approvalCount;
		this.approvalSystem = approvalSystem;
		this.minApprovals = minApprovals;
		this.approvalBased = approvalBased;
		this.bulkApproval = bulkApproval;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Long getApprovalCount() {
		return approvalCount;
	}

	public void setApprovalCount(Long approvalCount) {
		this.approvalCount = approvalCount;
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

	public ApprovalBased getApprovalBased() {
		return approvalBased;
	}

	public void setApprovalBased(ApprovalBased approvalBased) {
		this.approvalBased = approvalBased;
	}

	public BulkApprove getBulkApproval() {
		return bulkApproval;
	}

	public void setBulkApproval(BulkApprove bulkApproval) {
		this.bulkApproval = bulkApproval;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ApprovalSettingsResponseDTO [id=" + id + ", moduleName=" + moduleName + ", typeName=" + typeName
				+ ", approvalCount=" + approvalCount + ", approvalSystem=" + approvalSystem + ", minApprovals="
				+ minApprovals + ", approvalBased=" + approvalBased + ", bulkApproval=" + bulkApproval + ", status="
				+ status + "]";
	}

	
	
	 

}
