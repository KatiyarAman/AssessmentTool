package com.sterp.multitenant.tenant.core.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.dto.TypeRequestDTO;
import com.sterp.multitenant.tenant.model.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

	@Query(value = "select new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject(type.id, type.type)"
			+ " from com.sterp.multitenant.tenant.model.Type type JOIN type.modules m "
			+ "where type.status=:status and m.id=:moduleId")
	List<CommonDropdownResponseDataTravelObject> getSelectionListByModuleId(Long status, long moduleId);
	
	@Query(value = "select new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject(type.id, type.type)"
			+ " from com.sterp.multitenant.tenant.model.Type type where type.status=:status")
	List<CommonDropdownResponseDataTravelObject> getSelectionList(Long status);

	@Query("SELECT new com.sterp.multitenant.tenant.dto.TypeRequestDTO(t.id, t.type, t.status) FROM com.sterp.multitenant.tenant.model.Type t WHERE t.status = :status")
	List<TypeRequestDTO> getTypeList(Long status);
	
	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.TypeRequestDTO(t.id, t.type, t.status) from "
			+ "com.sterp.multitenant.tenant.model.Type t "
			+ "where t.id like concat('%',:wildCard,'%') or t.type like concat('%',:wildCard,'%')")
	Page<TypeRequestDTO> findCustomSearch(String wildCard, Pageable pageable);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.TypeRequestDTO(t.id, t.type, t.status) from "
			+ "com.sterp.multitenant.tenant.model.Type t")
	Page<TypeRequestDTO> findCustom(Pageable pageable);
	
	@Query("SELECT t FROM com.sterp.multitenant.tenant.model.Type t WHERE t.id IN :typesId")
	List<Type> getTypesById(@Param("typesId") List<Long> typesId);
}
