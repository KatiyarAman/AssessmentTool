package com.sterp.multitenant.tenant.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.dto.BranchUserRoleResponseDTO;
import com.sterp.multitenant.tenant.entity.BranchUserRole;

@Repository
public interface BranchUserRoleRepository extends JpaRepository<BranchUserRole, Long> {

	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.BranchUserRoleResponseDTO(bur.id,bur.moduleActionId, bur.branchUserAssignId, bur.permission, bur.roleId,bur.createdBy,bur.modifiedBy,"
			+ "bur.companyId,bur.branchId, bch.branchName, m.title ,a.actions) FROM com.sterp.multitenant.tenant.entity.UserEntity as u "
			+ "LEFT JOIN com.sterp.multitenant.tenant.entity.UserBranchAssign as uba ON uba.userId = u.id "
			+ "LEFT JOIN com.sterp.multitenant.tenant.entity.BranchUserRole as bur ON bur.branchUserAssignId = uba.id "
			+ "LEFT JOIN com.sterp.multitenant.tenant.branch.entity.Branch as bch on uba.branchId = bch.id "
			+ "LEFT JOIN com.sterp.multitenant.tenant.modules.entity.ModuleActionsMapping as map ON map.id = bur.moduleActionId "
			+ "LEFT JOIN com.sterp.multitenant.tenant.modules.entity.Modules as m ON m.id = map.moduleId "
			+ "LEFT JOIN com.sterp.multitenant.tenant.modules.entity.Actions as a on a.id = map.actionId "
			+ "where u.id =:userId ")
	List<BranchUserRoleResponseDTO> findbranchUserRoleForPermissionUpdate(long userId);

//	@Query(value = "SELECT m.id FROM com.sterp.multitenant.tenant.entity.UserEntity as u "
//			+ "LEFT JOIN com.sterp.multitenant.tenant.entity.UserBranchAssign as uba ON uba.userId = u.id "
//			+ "LEFT JOIN com.sterp.multitenant.tenant.entity.BranchUserRole as bur ON bur.branchUserAssignId = uba.id "
//			+ "LEFT JOIN com.sterp.multitenant.tenant.branch.entity.Branch as bch on uba.branchId = bch.id "
//			+ "LEFT JOIN com.sterp.multitenant.tenant.modules.entity.ModuleActionsMapping as map ON map.id = bur.moduleActionId "
//			+ "LEFT JOIN com.sterp.multitenant.tenant.modules.entity.Modules as m ON m.id = map.moduleId "
//			+ "LEFT JOIN com.sterp.multitenant.tenant.modules.entity.Actions as a on a.id = map.actionId "
//			+ "where u.id =:userId and uba.branchId=:branchId and u.roles.id=bur.roleId and bur.permission=:permission group by m.id")
//	List<Long> findAssignedModule(long userId, long branchId, boolean permission);
//
//	@Query(value = "SELECT a.id FROM com.sterp.multitenant.tenant.entity.UserEntity as u "
//			+ "LEFT JOIN com.sterp.multitenant.tenant.entity.UserBranchAssign as uba ON uba.userId = u.id "
//			+ "LEFT JOIN com.sterp.multitenant.tenant.entity.BranchUserRole as bur ON bur.branchUserAssignId = uba.id "
//			+ "LEFT JOIN com.sterp.multitenant.tenant.branch.entity.Branch as bch on uba.branchId = bch.id "
//			+ "LEFT JOIN com.sterp.multitenant.tenant.modules.entity.ModuleActionsMapping as map ON map.id = bur.moduleActionId "
//			+ "LEFT JOIN com.sterp.multitenant.tenant.modules.entity.Modules as m ON m.id = map.moduleId "
//			+ "LEFT JOIN com.sterp.multitenant.tenant.modules.entity.Actions as a on a.id = map.actionId "
//			+ "where u.id =:userId and uba.branchId=:branchId and u.roles.id=bur.roleId and m.id=:moduleId and bur.permission=:permission")
//	Set<Long> findAssignedActionsOnModule(long userId, long branchId, long moduleId, boolean permission);

	@Modifying
	@Query(value = "SELECT m.id FROM user as u " + "LEFT JOIN role_permissions as rp ON rp.role_id = u.role_id "
			+ "LEFT JOIN module_actions_mapping as mam ON mam.id = rp.module_action_id "
			+ "LEFT JOIN modules as m ON mam.module_id = m.id where rp.permission=1 and u.id=:userId and m.status=:status "
			+ "group by m.id", nativeQuery = true)
	List<Long> findAssignedModule(long userId, Long status);

	@Modifying
	@Query(value = "SELECT a.id FROM user as u " + "LEFT JOIN role_permissions as rp ON rp.role_id = u.role_id "
			+ "LEFT JOIN module_actions_mapping as mam ON mam.id = rp.module_action_id "
			+ "LEFT JOIN actions as a ON mam.action_id = a.id where rp.permission=1 and u.id=:userId and a.status=:status and mam.module_id=:moduleId", nativeQuery = true)
	Set<Long> findAssignedActionsOnModule(long userId, long moduleId,Long status);

	@Modifying
	@Query(value = "SELECT u.id FROM role_permissions as rp "
			+ "LEFT JOIN module_actions_mapping as map ON rp.module_action_id = map.id "
			+ "LEFT JOIN user as u on u.role_id = rp.role_id " + "LEFT JOIN actions as a ON a.id = map.action_id "
			+ "WHERE map.module_id=:moduleId and a.id = 12 group by u.id", nativeQuery = true)
	Set<Long> findModuleWiseApprovalPermissionUserList(long moduleId);
}
