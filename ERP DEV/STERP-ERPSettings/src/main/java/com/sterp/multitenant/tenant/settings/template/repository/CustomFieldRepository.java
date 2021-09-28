package com.sterp.multitenant.tenant.settings.template.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDto;
import com.sterp.multitenant.tenant.settings.template.entity.CustomField;
import com.sterp.multitenant.tenant.settings.template.entity.FieldType;
import com.sterp.multitenant.tenant.settings.template.entity.dto.CustomFieldResponse;

@Repository
public interface CustomFieldRepository extends JpaRepository<CustomField, Integer> {

	@Query(value = "SELECT new com.sterp.multitenant.tenant.settings.template.entity.dto.CustomFieldResponse(cf.id,cf.fieldName,cf.fieldHeader,cf.fieldTypeObject.type,"
			+ "			cf.isMandatory,cf.srcModule,cf.errorMessage,cf.onChange,cf.onClick,cf.defaultShow,cf.showOn,cf.entryType,cf.module,"
			+ "			cf.displayOrder,cf.dataSource,cf.indexing,cf.parentFieldId,cf.tooltipType,cf.tooltipTitle,cf.tooltipContent,cf.tooltipActivation,cf.shortLink,fI,cf.type,cf.status) "
			+ "from com.sterp.multitenant.tenant.settings.template.entity.CustomField cf left outer join cf.fieldIndexing fI "
			+ "where cf.module=:moduleId and cf.status=:status and cf.parentFieldId IS NULL and cf.shortLinkEnabled in :wildCard order by cf.displayOrder asc")
	List<CustomFieldResponse> findActiveCustomFieldByModuleIdAndParentFieldIdOrderByDisplayOrderAsc(long moduleId,
			Long status, List<Boolean> wildCard);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.settings.template.entity.dto.CustomFieldResponse(cf.id,cf.fieldName,cf.fieldHeader,cf.fieldTypeObject.type,"
			+ "			cf.isMandatory,cf.srcModule,cf.errorMessage,cf.onChange,cf.onClick,cf.defaultShow,cf.showOn,cf.entryType,cf.module,"
			+ "			cf.displayOrder,cf.dataSource,cf.indexing,cf.parentFieldId,cf.tooltipType,cf.tooltipTitle,cf.tooltipContent,cf.tooltipActivation,cf.shortLink,fI,cf.type,cf.status) "
			+ "from com.sterp.multitenant.tenant.settings.template.entity.CustomField cf left outer join cf.fieldIndexing fI ")
	List<CustomFieldResponse> findAllCustomFields();

	List<CustomField> findByModuleAndStatusOrderByDisplayOrderAsc(int moduleId, Long statusEnum);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.settings.template.entity.dto.CustomFieldResponse(cf.id,cf.fieldName,cf.fieldHeader,cf.fieldTypeObject.type,"
			+ "cf.isMandatory,cf.srcModule,cf.errorMessage,cf.onChange,cf.onClick,cf.defaultShow,cf.showOn,cf.entryType,cf.module,"
			+ "cf.displayOrder,cf.dataSource,cf.indexing,cf.parentFieldId,cf.tooltipType,cf.tooltipTitle,cf.tooltipContent,cf.tooltipActivation,cf.shortLink,fI,cf.type,cf.status) "
			+ "from com.sterp.multitenant.tenant.settings.template.entity.CustomField cf left outer join cf.fieldIndexing fI "
			+ "where cf.parentFieldId=:parentId and cf.status=:status order by cf.displayOrder asc")
	List<CustomFieldResponse> findActiveCustomFieldByParentFieldIdOrderByDisplayOrder(int parentId, Long status);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDto(cf.id,cf.fieldHeader) "
			+ "from com.sterp.multitenant.tenant.settings.template.entity.CustomField cf where cf.module=:moduleId and cf.fieldTypeObject in :fieldTypes and cf.status=:status")
	List<CommonDropdownResponseDto> findActiveCutomFieldByModuleIdAndFieldType(long moduleId,
			List<FieldType> fieldTypes, Long status);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDto(cf.id,cf.fieldHeader) "
			+ "from com.sterp.multitenant.tenant.settings.template.entity.CustomField cf where cf.module=:moduleId  and cf.status=:status")
	List<CommonDropdownResponseDto> findActiveCutomFieldByModuleId(long moduleId, Long status);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDto(cf.id,cf.fieldHeader) "
			+ "from com.sterp.multitenant.tenant.settings.template.entity.CustomField cf where cf.fieldTypeObject in :fieldTypes and cf.status=:status")
	List<CommonDropdownResponseDto> findActiveCutomFieldByFieldTypes(List<FieldType> fieldTypes, Long status);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDto(cf.id,cf.fieldHeader) "
			+ "from com.sterp.multitenant.tenant.settings.template.entity.CustomField cf where cf.status=:status")
	List<CommonDropdownResponseDto> findActiveCutomField(Long status);

	// Ankit
	
	List<CustomField> findByModule(long moduleId);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.settings.template.entity.dto.CustomFieldResponse(cf.id,cf.fieldName,"
			+ " cf.fieldHeader,cf.fieldTypeObject.type,cf.isMandatory,cf.srcModule,cf.errorMessage,cf.onChange,cf.onClick,cf.defaultShow,"
			+ " cf.showOn,cf.entryType,cf.module,md.title,cf.displayOrder,cf.dataSource,cf.indexing,cf.parentFieldId,cf.tooltipType,"
			+ " cf.tooltipTitle,cf.tooltipContent,cf.tooltipActivation,cf.shortLink,fI,cf.type, fT,cf.status) "
			+ " from com.sterp.multitenant.tenant.settings.template.entity.CustomField cf "
			+ " left outer join cf.fieldIndexing fI "
			+ " left join com.sterp.multitenant.tenant.modules.entity.Modules md on md.id = cf.module "
			+ " left join com.sterp.multitenant.tenant.settings.template.entity.FieldType fT on fT.id = fI.filterType "
			+ " where cf.entryType =:entryType")
	Page<CustomFieldResponse> findCustom(String entryType, Pageable createPageRequest);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.settings.template.entity.dto.CustomFieldResponse(cf.id,cf.fieldName,"
			+ " cf.fieldHeader,cf.fieldTypeObject.type,cf.isMandatory,cf.srcModule,cf.errorMessage,cf.onChange,cf.onClick,cf.defaultShow,"
			+ " cf.showOn,cf.entryType,cf.module,md.title,cf.displayOrder,cf.dataSource,cf.indexing,cf.parentFieldId,cf.tooltipType,"
			+ " cf.tooltipTitle,cf.tooltipContent,cf.tooltipActivation,cf.shortLink,fI,cf.type, fT,cf.status) "
			+ " from com.sterp.multitenant.tenant.settings.template.entity.CustomField cf "
			+ " left outer join cf.fieldIndexing fI "
			+ " left join com.sterp.multitenant.tenant.modules.entity.Modules md on md.id = cf.module "
			+ " left join com.sterp.multitenant.tenant.settings.template.entity.FieldType fT on fT.id = fI.filterType "
			+ " where cf.entryType =:entryType and (cf.fieldName like concat('%',:wildCard,'%') or cf.module like concat('%',:wildCard,'%')"
			+ " or cf.fieldHeader like concat('%',:wildCard,'%')" + " or fT.type like concat('%',:wildCard,'%'))")
	Page<CustomFieldResponse> findCustomGlobalSearch(String entryType, String wildCard, Pageable createPageRequest);

}
