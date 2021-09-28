package com.sterp.multitenant.tenant.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.modules.dto.RoleResponse;
import com.sterp.multitenant.tenant.modules.entity.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
	@Query(value = "SELECT new com.sterp.multitenant.tenant.modules.dto.RoleResponse(role.id,role.roleCode,role.roleName,role.roleDescription,role.status) "
			+ " from com.sterp.multitenant.tenant.modules.entity.Roles role")
	Page<RoleResponse> findCustom(Pageable createPageRequest);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.modules.dto.RoleResponse(role.id,role.roleCode,role.roleName,role.roleDescription,role.status) "
			+ " from com.sterp.multitenant.tenant.modules.entity.Roles role "
			+ " where role.id like concat('%',:wildCard,'%') or role.roleCode like concat('%',:wildCard,'%') or role.roleName like concat('%',:wildCard,'%')"
			+ " or role.roleDescription like concat('%',:wildCard,'%')  or cast(role.status as string) like concat('%',:wildCard,'%')")
	Page<RoleResponse> findCustomGlobalSearch(String wildCard, Pageable createPageRequest);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject(role.id,role.roleName)"
			+ " from com.sterp.multitenant.tenant.modules.entity.Roles role where role.status=:status")
	List<CommonDropdownResponseDataTravelObject> getSelectionList(Long status);

}
