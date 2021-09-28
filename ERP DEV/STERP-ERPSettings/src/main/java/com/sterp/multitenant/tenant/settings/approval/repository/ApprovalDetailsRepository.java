package com.sterp.multitenant.tenant.settings.approval.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.settings.approval.entity.ApprovalDetails;

@Repository
public interface ApprovalDetailsRepository extends JpaRepository<ApprovalDetails, Long> {
	ApprovalDetails findByModuleIdAndRefDocIdAndParentIdAndStatus(Integer submoduleId, Long docId, Long parentId,
			Long status);

	List<ApprovalDetails> findByModuleIdAndRefDocIdAndStatus(long moduleId, Long docId, Long status);

	ApprovalDetails findByModuleIdAndRefDocIdAndParentIdAndUserIdAndApprovalStatusAndStatus(long moduleId, Long docId,
			Long parentId, Long userId, Long approvalStatus, Long status);
}
