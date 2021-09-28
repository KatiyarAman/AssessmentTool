package com.sterp.multitenant.tenant.settings.approval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.settings.approval.entity.ApproverLevel;

@Repository
public interface ApprovalAssignRepository extends JpaRepository<ApproverLevel, Long> {

	ApproverLevel findByParentId(Integer parentId);

}
