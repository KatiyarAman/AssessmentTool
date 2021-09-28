package com.sterp.multitenant.tenant.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.entity.UserBranchAssign;

@Repository
public interface UserBranchAssignRepository extends JpaRepository<UserBranchAssign, Long> {
	Set<UserBranchAssign> findByUserId(Long userId);
}
