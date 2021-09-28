package com.sterp.multitenant.tenant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.dto.UserDTO;
import com.sterp.multitenant.tenant.dto.UserResponseDTO;
import com.sterp.multitenant.tenant.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByUsername(String username);

	Optional<UserEntity> findByEmail(String email);

	Boolean existsByEmail(String email);

	UserEntity findByUsernameOrEmail(String username, String email);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.UserResponseDTO(u.id,u.uuid,u.username,u.email,u.mobile,u.passwordExpiry,u.status,"
			+ "u.createdAt,u.modifiedAt,c.companyName,b.branchName,role.roleName) "
			+ "from com.sterp.multitenant.tenant.entity.UserEntity u "
			+ "left join com.sterp.multitenant.tenant.company.entity.Company c on u.companyId=c.id "
			+ "left join com.sterp.multitenant.tenant.branch.entity.Branch b on u.branchId=b.id "
			+ "left join com.sterp.multitenant.tenant.modules.entity.Roles role on u.roles.id=role.id")
	Page<UserResponseDTO> findCustom(Pageable pageable);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.UserDTO(user.id,user.uuid,user.username,user.password,user.email,"
			+ "user.mobile,user.passwordExpiry,user.status,user.roles.id,user.employeeId) from com.sterp.multitenant.tenant.entity.UserEntity user " // join
																																						// user.branchAssign
																																						// bas
			+ "where user.id=:userId")
	UserDTO findCustomUserResponseId(long userId);
	
	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto."
			+ "CommonDropdownResponseDataTravelObject(user.id,user.username)"
			+ " from com.sterp.multitenant.tenant.entity.UserEntity user where user.status=:status")
	List<CommonDropdownResponseDataTravelObject> getSelectionList(Long status);

//	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.UserResponseDTO(u.id,u.uuid,u.username,u.email,u.mobile,u.passwordExpiry,u.status,"
//			+ "u.createdAt,u.modifiedAt,c.companyName,b.branchName,u.createdBy,u.modifiedBy,role.roleName) "
//			+ "from com.sterp.multitenant.tenant.entity.UserEntity u "
//			+ "left join com.sterp.multitenant.tenant.company.entity.Company c on u.companyId=c.id "
//			+ "left join com.sterp.multitenant.tenant.branch.entity.Branch b on u.branchId=b.id "
//			+ "left join com.sterp.multitenant.tenant.modules.entity.Roles role on u.roles.id=role.id "
//			+ "where cast(u.id as string) like concat('%',:wildCard,'%') or u.uuid like concat('%',:wildCard,'%') or u.username like concat('%',:wildCard,'%') "
//			+ "or u.email like concat('%',:wildCard,'%') or cast(u.status as string) like concat('%',:wildCard,'%') or cast(u.createdAt as string) like concat('%',:wildCard,'%') "
//			+ "or cast(u.modifiedAt as string) like concat('%',:wildCard,'%') or c.companyName like concat('%',:wildCard,'%') or b.branchName like concat('%',:wildCard,'%') "
//			+ "or role.roleName like concat('%',:wildCard,'%')")
//	Page<UserResponseDTO> findCustomGlobalSearch(String wildCard, Pageable pageable);

	@Query(value = "SELECT b.branch_name,c.company_name FROM branch as b "
			+ "left join company as c on c.id=b.company_id " + "where b.id=:defaultLoggedInBranch", nativeQuery = true)
	Object[][] findBranchAndCompanyNameByDefaultLoggedInBranch(long defaultLoggedInBranch);
}
