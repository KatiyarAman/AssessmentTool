package com.sterp.multitenant.tenant.settings.template.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.map.HashedMap;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.core.services.ScreenFilterService;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.exceptionhandler.CustomException;
import com.sterp.multitenant.tenant.exceptionhandler.ResourceNotFoundException;
import com.sterp.multitenant.tenant.modules.entity.Actions;
import com.sterp.multitenant.tenant.modules.entity.ModuleActionsMapping;
import com.sterp.multitenant.tenant.modules.entity.Modules;
import com.sterp.multitenant.tenant.modules.service.ModuleService;
import com.sterp.multitenant.tenant.repository.BranchUserRoleRepository;
import com.sterp.multitenant.tenant.settings.template.entity.CustomField;
import com.sterp.multitenant.tenant.settings.template.entity.DefaultSort;
import com.sterp.multitenant.tenant.settings.template.entity.FieldType;
import com.sterp.multitenant.tenant.settings.template.entity.ScreenFilter;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ButtonsInfoResponse;
import com.sterp.multitenant.tenant.settings.template.entity.dto.CustomFieldByModuleAndFieldTypeRequestDTO;
import com.sterp.multitenant.tenant.settings.template.entity.dto.CustomFieldRequestDto;
import com.sterp.multitenant.tenant.settings.template.entity.dto.CustomFieldResponse;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;
import com.sterp.multitenant.tenant.settings.template.repository.ButtonsRepository;
import com.sterp.multitenant.tenant.settings.template.repository.CustomFieldRepository;
import com.sterp.multitenant.tenant.settings.template.repository.DefaultSortRepository;
import com.sterp.multitenant.tenant.settings.template.repository.FieldTypeRepository;
import com.sterp.multitenant.tenant.settings.template.repository.FieldValidationsRepository;
import com.sterp.multitenant.tenant.settings.template.repository.ModuleButtonsMappingRepository;
import com.sterp.multitenant.tenant.settings.template.repository.OptionsRepository;
import com.sterp.multitenant.tenant.settings.template.service.CustomFieldService;

@Service
public class CustomFieldServiceImpl implements CustomFieldService {

	@Autowired
	CommonService commonService;

	@Autowired
	CustomFieldRepository customFieldRepository;

	@Autowired
	OptionsRepository optionsRepository;

	@Autowired
	FieldValidationsRepository fieldValidationsRepository;

	@Autowired
	AppService appService;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	FieldTypeRepository fieldTypeRepository;

	@Autowired
	DefaultSortRepository defaultSortRepository;

	@Autowired
	@Qualifier(value = "tenantEntityManagerFactory")
	private EntityManager em;

	@Autowired
	ModuleService moduleService;
	
	@Autowired
	ScreenFilterService screenFilterService;

	@Autowired
	ModuleButtonsMappingRepository moduleButtonsMappingRepository;

	@Autowired
	ButtonsRepository buttonsRepository;

	@Autowired
	BranchUserRoleRepository branchUserRoleRepository;

	public long getCount() {
		return this.customFieldRepository.count();
	}

	@CacheEvict(value = "custom-field-cache", key = "'customFieldCache'+#moduleId+'_'+#type", condition = "#iscacheable ==null && #iscacheable", beforeInvocation = true)
	@Cacheable(value = "custom-field-cache", key = "'customFieldCache'+#moduleId+'_'+#type", condition = "#iscacheable !=null && #iscacheable")
	@Override
	public Map<String, Object> getActiveCustomFieldByModuleIdOrderByDisplayOrder(long moduleId, boolean iscacheable,
			List<Boolean> shortLinkEnabled, String type, HttpServletRequest httpServletRequest) {
		Map<String, Object> response = new HashedMap<String, Object>();
		List<CustomFieldResponse> fieldResponses = this.customFieldRepository
				.findActiveCustomFieldByModuleIdAndParentFieldIdOrderByDisplayOrderAsc(moduleId, 1L,
						shortLinkEnabled);
		for (CustomFieldResponse customFieldResponse : fieldResponses) {
			customFieldResponse.setOptions(this.optionsRepository.findByCustomFieldId(customFieldResponse.getId()));
			customFieldResponse.setFieldValidations(this.fieldValidationsRepository
					.findByCustomFieldIdAndStatus(customFieldResponse.getId(), 1L));
			if (customFieldResponse.getParentFieldId() == null) {
				customFieldResponse.setChildCustomFields(this.recursiveFetchChildCustomFields(customFieldResponse));
			}
		}

		// fetching default sorting from db and caching it - Note: If inserted a new
		// record update the cache for latest entry
		List<DefaultSort> defaultSorts = defaultSortRepository.findByModuleId(moduleId);
		response.put("defaultSort",
				defaultSorts == null || defaultSorts.size() == 0 ? "No Record Found" : defaultSorts);
		response.put("data", fieldResponses);

		Modules modules = this.moduleService.getModuleById(moduleId, false);

		// int loggedInBranchId = (int)
		// httpServletRequest.getAttribute("defaultloginbranch");
		Long userEntity = this.appService.getCurrentUser().getId();
		Set<Long> actionIds = this.branchUserRoleRepository
				.findAssignedActionsOnModule(this.appService.getCurrentUser().getId(), moduleId, 1L);
		Set<Actions> actions = modules.getActions();

		actions = actions.stream().filter(t -> actionIds.contains(t.getId())).collect(Collectors.toSet());

		if (actions.size() > 0) {

			Map<String, Object> actionsButtons = new HashedMap<String, Object>(8, 0.88f);
			for (Actions action : actions) {
				ButtonsInfoResponse buttonsInfoResponse = new ButtonsInfoResponse();
				buttonsInfoResponse.setButtons(this.buttonsRepository.findByActionId(action.getId()));
				ModuleActionsMapping actionsMapping = this.moduleService
						.getModuleActionMappingByModuleIdAndActionId(moduleId, action.getId());
				if (actionsMapping != null) {
					buttonsInfoResponse.setButtonList(
							this.moduleButtonsMappingRepository.findByModuleActionId(actionsMapping.getId()));
				}
				//actionsButtons.put(action.getActions() + "_" + action.getId(), buttonsInfoResponse);
				actionsButtons.put(action.getActions(), buttonsInfoResponse);
			}
			response.put("buttons", actionsButtons);
			UserEntity currentUser = this.appService.getCurrentUser();
			long userId = currentUser.getId();
			List<ScreenFilterDTO> storedScreenFilter = this.screenFilterService.getScreenFilter(userId, moduleId);
			response.put("screenFilter", storedScreenFilter);
		}
		return response;
	}

	@Override
	public Map<String, Object> getAll() {
		Map<String, Object> response = new HashedMap<String, Object>();
		List<CustomFieldResponse> fieldResponses = this.customFieldRepository.findAllCustomFields();
		for (CustomFieldResponse customFieldResponse : fieldResponses) {
			customFieldResponse.setOptions(this.optionsRepository.findByCustomFieldId(customFieldResponse.getId()));
			customFieldResponse.setFieldValidations(this.fieldValidationsRepository
					.findByCustomFieldIdAndStatus(customFieldResponse.getId(), 1L));
		}
		response.put("data", fieldResponses);
		return response;
	}

	@Override
	public Map<String, Object> saveOrUpdate(CustomFieldRequestDto customFieldRequestDto) {
		UserEntity currentUser = this.appService.getCurrentUser();
		CustomField customField = this.modelMapper.map(customFieldRequestDto, CustomField.class);
		String fieldHeader = customFieldRequestDto.getFieldHeader().replaceAll("\\s", "");

		if (customField.getId() == 0) {
			customField.setCreatedBy(currentUser.getId());
			customField.setCreatedDate(new Date(System.currentTimeMillis()));
			// customField.setEntryType("UDF");
		}
		customField.setParentFieldId(customField.getParentFieldId() == 0 ? null : customField.getParentFieldId());
		customField.setSrcModule(customField.getSrcModule() == 0 ? null : customField.getSrcModule());
		customField.setFieldTypeObject(this.fieldTypeRepository.findById(customFieldRequestDto.getFieldType()).get());
		if (customField.getEntryType().equalsIgnoreCase("UDF")) {
			String fieldName = fieldHeader.substring(0, 1).toLowerCase() + fieldHeader.substring(1);
			customField.setFieldName(fieldName);
		}
		customField.setModifiedBy(currentUser.getId());
		customField.setDefaultShow(customField.getStatus() == 1 ? true : false);
		customField.setModifiedDate(new Date(System.currentTimeMillis()));
		customField = this.customFieldRepository.save(customField);
		Map<String, Object> response = new HashedMap<String, Object>();
		response.put("message", customFieldRequestDto.getId() == 0 ? "Created Successfully!" : "Updated Successfully!");
		response.put("data", customField);
		return response;
	}

	public List<CustomFieldResponse> recursiveFetchChildCustomFields(CustomFieldResponse parentCustomField) {
		List<CustomFieldResponse> customFields = this.customFieldRepository
				.findActiveCustomFieldByParentFieldIdOrderByDisplayOrder(parentCustomField.getId(), 1L);
		if (customFields.size() != 0) {
			for (CustomFieldResponse customField : customFields) {
				customField.setOptions(this.optionsRepository.findByCustomFieldId(customField.getId()));
				customField.setFieldValidations(this.fieldValidationsRepository
						.findByCustomFieldIdAndStatus(customField.getId(), 1L));
				customField.setChildCustomFields(this.recursiveFetchChildCustomFields(customField));
			}
		}
		return customFields;
	}

	@Override
	public Map<String, Object> getActiveFieldByModuleIdAndFieldType(CustomFieldByModuleAndFieldTypeRequestDTO dto) {
		if (dto.getFieldTypeIds() == null || dto.getFieldTypeIds().size() == 0) {
			dto.setFieldTypeIds(Collections.emptyList());
		}
		Map<String, Object> response = new HashedMap<String, Object>();
		List<FieldType> fieldTypes = this.fieldTypeRepository.findAllById(dto.getFieldTypeIds());

		if (fieldTypes.size() != 0 && dto.getModuleId() != null) {
			response.put("data", this.customFieldRepository
					.findActiveCutomFieldByModuleIdAndFieldType(dto.getModuleId(), fieldTypes, 1L));
			return response;
		} else if (dto.getModuleId() != null && fieldTypes.size() == 0) {
			response.put("data",
					this.customFieldRepository.findActiveCutomFieldByModuleId(dto.getModuleId(), 1L));
		} else if (dto.getModuleId() == null && fieldTypes.size() != 0) {
			response.put("data",
					this.customFieldRepository.findActiveCutomFieldByFieldTypes(fieldTypes, 1L));
		} else {
			response.put("data", this.customFieldRepository.findActiveCutomField(1L));

		}
		return response;
	}

	@Override
	public CustomField getCustomFieldById(int id) {
		// Iterable<CustomField> cus = this.findAllCustomFields();
		// List<CustomField> data = this.commonService.getAllData(CustomField.class);
		// Test<String, Integer> n = new Test<String, Integer>();
		Optional<CustomField> optional = this.customFieldRepository.findById(id);
		if (optional.isPresent()) {
			CustomField response = optional.get();
			response.setFieldType(response.getFieldTypeObject().getId());
			return response;
		}
		return null;
	}

	// Modifier - Ankit
	@Override
	public Map<String, Object> getCustomFieldList(int page, int limit, String search, String sort, String order,
			List<ScreenFilter> screenFilter, boolean filterRemove, int moduleId, String entryType) {
		UserEntity currentUser = this.appService.getCurrentUser();
		if (currentUser == null)
			throw new CustomException("Session has been timed out!");

		Map<String, Object> response = new HashMap<String, Object>();

		if ((sort == null) || (sort.isEmpty())) {
			sort = "id";
		}
		if ((order == null) || (order.isEmpty())) {
			order = Sort.Direction.ASC.toString();
		}
		int totalCount = Integer.parseInt(Long.valueOf(this.getCount()).toString());
		Page<CustomFieldResponse> customFieldResponse = null;
		if (totalCount == 0) {
			throw new ResourceNotFoundException("No Record Found");
		}
		if ((search == null) || (search.isEmpty())) {
			customFieldResponse = this.customFieldRepository.findCustom(entryType,
					this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
		} else {
			customFieldResponse = this.customFieldRepository.findCustomGlobalSearch(entryType, search,
					this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
		}

		customFieldResponse.forEach((customField) -> {
			customField.setFieldValidations(
					fieldValidationsRepository.findByCustomFieldIdAndStatus(customField.getId(), 1L));
			customField.setOptions(optionsRepository.findByCustomFieldId(customField.getId()));
		});

		response.put("totalCount", this.customFieldRepository.count());
		response.put("data", customFieldResponse);
		return response;
	}

	@Override
	public List<CustomField> getCustomFieldByModuleId(long moduleId) {
		return customFieldRepository.findByModule(moduleId);
	}

//	@SuppressWarnings("unchecked")
//    public Iterable<Block> findAllBlock() {
//        return em.createNamedStoredProcedureQuery("procedure-one").getResultList();
//    }
// 
//	@SuppressWarnings("unchecked")
//	public Iterable<CustomField> findAllCustomFields() {
////		int pageNumber = 1;
////		int pageSize = 10;
////		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
////
////		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
////		countQuery.select(criteriaBuilder.count(countQuery.from(CustomField.class)));
////		Long count = em.createQuery(countQuery).getSingleResult();
////
////		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(CustomField.class);
////		Root from = criteriaQuery.from(CustomField.class);
////		CriteriaQuery select = criteriaQuery.select(from);
////
////		TypedQuery typedQuery = em.createQuery(select);
////		while (pageNumber < count.intValue()) {
////			typedQuery.setFirstResult(pageNumber - 1);
////			typedQuery.setMaxResults(pageSize);
////			System.out.println("Current page: " + typedQuery.getResultList());
////			pageNumber += pageSize;
////		}
////		return null;
//
//		int fromValue = 0;
//		int pSize = 10;
//		final String search = "companyCode";
//		final String sort = "id";
//		final String filter = null;
//		final String order = "ACS";
//
//		final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//		final CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(CustomField.class);
//		final Root employeeRoot = criteriaQuery.from(CustomField.class);
//		Query query = em.createQuery("Select cus from CustomField cus where cus.id=1");
//		List<CustomField> a = query.getResultList();
//		List<Predicate> criteriaList = new ArrayList<Predicate>();
//
//		if (!org.apache.commons.lang3.StringUtils.isEmpty(search)) {
//			Predicate predicateSearch = criteriaBuilder
//					.like(criteriaBuilder.upper(employeeRoot.get("fieldHeader").as(String.class)), "%" + search + "%");
//			// Predicate predicateSearch1 = criteriaBuilder
//			// .like(criteriaBuilder.upper(employeeRoot.get("errorMessage").as(String.class)),
//			// "%" + search + "%");
//
//			criteriaList.add(predicateSearch);
//			// criteriaList.add(predicateSearch1);
//
//		}
//
//		if (!StringUtils.isEmpty(filter)) {
//
//			Predicate predicateFilter = criteriaBuilder
//					.equal(criteriaBuilder.upper(employeeRoot.get("status").as(String.class)), filter);
//			criteriaList.add(predicateFilter);
//
//		}
//
//		if (!StringUtils.isEmpty(sort)) {
//			if (!StringUtils.isEmpty(order) && order.equalsIgnoreCase("DESC"))
//				criteriaQuery.orderBy(criteriaBuilder.desc(employeeRoot.get(sort)));
//			else
//				criteriaQuery.orderBy(criteriaBuilder.asc(employeeRoot.get(sort)));
//		}
//
//		criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
//
////		final TypedQuery query = em.createQuery(criteriaQuery);
////
////		query.setFirstResult(fromValue);
////		query.setMaxResults(pSize);
////
////		return query.getResultList();
//		return null;
//	}

//	@SuppressWarnings("unchecked")
//	public Iterable<CustomField> findAllCustomFields() {
//		return em.createNamedStoredProcedureQuery("procedure-one").getResultList();
//	}

//    @SuppressWarnings("unchecked")
//    public Iterable<Block> findBlockByID(Integer input) {
//        return em.createNamedStoredProcedureQuery("procedure-two").setParameter("block_id", input).getResultList();
//    }
// 
//    public Integer findBlockCountByID(Integer input) {
//        return (Integer) em.createNamedStoredProcedureQuery("procedure-third").setParameter("block_id", input).getOutputParameterValue("block_count");
//    }
//	
}
