package com.sterp.multitenant.tenant.common.dao.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.collections4.map.HashedMap;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.sterp.multitenant.tenant.common.dao.CommonDao;
import com.sterp.multitenant.tenant.core.services.NotificationService;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDto;
import com.sterp.multitenant.tenant.dto.CommonPagingResponse;
import com.sterp.multitenant.tenant.settings.approval.dto.PendingApprovalDTO;
import com.sterp.multitenant.tenant.settings.approval.dto.PendingApproversDetails;
import com.sterp.multitenant.tenant.settings.approval.entity.ApprovalDetails;
import com.sterp.multitenant.tenant.settings.approval.service.ApprovalSettingsService;
import com.sterp.multitenant.tenant.settings.template.entity.dto.DefaultSortDto;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;

@Component
@Transactional(value = "tenantTransactionManager")
public class CommonDaoImpl implements CommonDao {

	@Autowired
	@Qualifier(value = "tenantEntityManagerFactory")
	EntityManager entityManager;

	@Autowired
	ApprovalSettingsService approvalSettingsService;
	
	@Autowired
	NotificationService notificationService;
	
	@SuppressWarnings("deprecation")
	@Override
	public Object add(Object obj) {
		Map<String, Object> response = new HashedMap<String, Object>();
		Session hbSession = entityManager.unwrap(Session.class).getSession();
		hbSession.save(obj);
		hbSession.close();
		response.put("data", obj);
		response.put("message", obj.getClass().getSimpleName() + " Add Successfully");
		return obj;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Object update(Object obj) {
		Map<String, Object> response = new HashedMap<String, Object>();
		Session hbSession = entityManager.unwrap(Session.class).getSession();
		hbSession.update(obj);
		hbSession.close();
		response.put("data", obj);
		response.put("message", obj.getClass().getSimpleName() + " Updated Successfully");
		return obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> CommonPagingResponse<T> getCommonFilteredList(Class<T> T, int page, int limit, String search,
			Map<String, Object> advanceSearch, Map<String, Object> advanceSearchAnd, String sort, String order,
			List<ScreenFilterDTO> screenFilterDTO, long moduleId, Map<String, Object> groupSort,
			DefaultSortDto defaultSort, long userId) {
		/*
		 * Map for Multiple Column Sorting 'In map Key contain column name and value
		 * contain order of sorting'
		 */
		groupSort.put(sort, order); // Merging data for single col sort.

		Map<String, Object> indexingField = getAllShowablwFieldByModuleId(moduleId, true);
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(T);
		Root<T> root = criteriaQuery.from(T);
		List<Predicate> orPredicates = new ArrayList<Predicate>();
		List<Predicate> andPredicates = new ArrayList<Predicate>();

		/* Dynamic Mapping --------- join reference hold in map value */
		Map<String, Object> joinMap = getDynamicMapping(indexingField, root);

		/* Global Search ... */
		if (search != null && !search.equals("")) {
			Field[] fields = T.getDeclaredFields();
			indexingField.forEach((key, value) -> {
				for (Field field : fields) {
					if (key.contains(".") && key.substring(0, key.lastIndexOf(".")).equals(field.getName())) {
						orPredicates.add(criteriaBuilder
								.like(((Join<Object, Object>) joinMap.get(key.substring(0, key.lastIndexOf("."))))
										.get(key.substring(key.lastIndexOf(".") + 1)), "%" + search + "%"));
					} else if (key.equals(field.getName())) {
						orPredicates.add(criteriaBuilder.like(
								((Root<T>) joinMap.get("root")).get(field.getName()).as(String.class),
								"%" + search + "%"));
					}
				}
			});
		}

		/* Advance Search Or condition with like operator ... */

		if (advanceSearch.size() > 0) {
			advanceSearch.forEach((key, value) -> {
				if (key.contains(".")) {
					orPredicates.add(criteriaBuilder
							.like(((Join<Object, Object>) joinMap.get(key.substring(0, key.lastIndexOf("."))))
									.get(key.substring(key.lastIndexOf(".") + 1)), "%" + value + "%"));
				} else {
					orPredicates.add(criteriaBuilder.like(((Root<T>) joinMap.get("root")).get(key).as(String.class),
							"%" + value + "%"));
				}
			});
		}

		/* Advance Search with equal Condition ... */
		if (advanceSearchAnd.size() > 0) {
			advanceSearchAnd.forEach((key, value) -> {
				if (key.contains(".")) {
					andPredicates.add(criteriaBuilder
							.like(((Join<Object, Object>) joinMap.get(key.substring(0, key.lastIndexOf("."))))
									.get(key.substring(key.lastIndexOf(".") + 1)), "%" + value + "%"));
				} else {
					andPredicates.add(criteriaBuilder.like(((Root<T>) joinMap.get("root")).get(key).as(String.class),
							"%" + value + "%"));
				}
			});
		}

		/* Screen filters for searching and sorting */
		List<Order> screenFilterOrderList = new ArrayList<Order>();
		if (screenFilterDTO != null && screenFilterDTO.size() != 0) {
			screenFilterDTO.forEach((screenFilter) -> {
				/* Saved Search */
				if (screenFilter.getFilterRange() != null || screenFilter.getFilterRange() != "") {

					switch (screenFilter.getFilterRange()) {
					case "eq":
						andPredicates.add(criteriaBuilder.equal(
								((Root<T>) joinMap.get("root")).get(screenFilter.getFieldIndexing().getDataFieldName()),
								screenFilter.getFilterValue()));
						break;
					case "in":
						break;
					default:
					}
				}
				/* Saved sorting */
				if ((screenFilter.getSortOrder() != null || screenFilter.getSortOrder().toString() != "")
						&& (screenFilter.isSortFlag())) {
					if (screenFilter.getFieldIndexing().getDataFieldName().contains(".")) {
						screenFilterOrderList
								.add(screenFilter.getSortOrder().toString().equals("asc")
										? criteriaBuilder.asc(((Join<Object, Object>) joinMap
												.get(screenFilter.getFieldIndexing().getDataFieldName().substring(0,
														screenFilter.getFieldIndexing().getDataFieldName()
																.lastIndexOf(".")))).get(screenFilter
																		.getFieldIndexing().getDataFieldName()
																		.substring(screenFilter.getFieldIndexing()
																				.getDataFieldName().lastIndexOf(".")
																				+ 1)))
										: criteriaBuilder.desc(((Join<Object, Object>) joinMap
												.get(screenFilter.getFieldIndexing().getDataFieldName().substring(0,
														screenFilter.getFieldIndexing().getDataFieldName()
																.lastIndexOf(".")))).get(screenFilter.getFieldIndexing()
																		.getDataFieldName()
																		.substring(screenFilter.getFieldIndexing()
																				.getDataFieldName().lastIndexOf(".")
																				+ 1))));
					} else {
						screenFilterOrderList.add(screenFilter.getSortOrder().toString().equals("asc")
								? criteriaBuilder.asc(((Root<?>) joinMap.get("root"))
										.get(screenFilter.getFieldIndexing().getDataFieldName()))
								: criteriaBuilder.desc(((Root<?>) joinMap.get("root"))
										.get(screenFilter.getFieldIndexing().getDataFieldName())));
					}

				}
			});
		}

		/* Default Sort */
		List<Order> defaultSortOrderList = new ArrayList<Order>();
		if (defaultSort != null) {
			if (defaultSort.getFieldIndexing().getDataFieldName().contains(".")) {
				defaultSortOrderList.add(defaultSort.getSortOrder().toString().equals("asc")
						? criteriaBuilder.asc(((Join<Object, Object>) joinMap
								.get(defaultSort.getFieldIndexing().getDataFieldName().substring(0,
										defaultSort.getFieldIndexing().getDataFieldName().lastIndexOf("."))))
												.get(defaultSort.getFieldIndexing().getDataFieldName()
														.substring(defaultSort.getFieldIndexing().getDataFieldName()
																.lastIndexOf(".") + 1)))
						: criteriaBuilder.desc(((Join<Object, Object>) joinMap
								.get(defaultSort.getFieldIndexing().getDataFieldName().substring(0,
										defaultSort.getFieldIndexing().getDataFieldName().lastIndexOf(".")))).get(
												defaultSort.getFieldIndexing().getDataFieldName().substring(defaultSort
														.getFieldIndexing().getDataFieldName().lastIndexOf(".") + 1))));
			} else {
				defaultSortOrderList.add(defaultSort.getSortOrder().toString().equals("asc")
						? criteriaBuilder.asc(
								((Root<?>) joinMap.get("root")).get(defaultSort.getFieldIndexing().getDataFieldName()))
						: criteriaBuilder.desc(((Root<?>) joinMap.get("root"))
								.get(defaultSort.getFieldIndexing().getDataFieldName())));
			}
		}

		/* Add restrictions in query by orPredicate and andPredicate */
		if (orPredicates.size() > 0 && andPredicates.size() > 0) {
			criteriaQuery.where(
					criteriaBuilder.and(criteriaBuilder.or(orPredicates.toArray(new Predicate[orPredicates.size()])),
							criteriaBuilder.and(andPredicates.toArray(new Predicate[andPredicates.size()]))));
		} else if (orPredicates.size() > 0) {
			criteriaQuery.where(criteriaBuilder.or(orPredicates.toArray(new Predicate[orPredicates.size()])));
		} else if (andPredicates.size() > 0) {
			criteriaQuery.where(criteriaBuilder.or(andPredicates.toArray(new Predicate[andPredicates.size()])));
		}
		orPredicates.clear();
		andPredicates.clear();

		/* Sorting */
		List<Order> orderList = getSortOrder(groupSort, joinMap, criteriaBuilder, moduleId);
		orderList.addAll(screenFilterOrderList);
		orderList.addAll(defaultSortOrderList);
		criteriaQuery.orderBy(orderList);

		final TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery.select(root).distinct(true));
		long totalElements = typedQuery.getResultList().size();
		long totalPage = totalElements % limit == 0 ? totalElements / limit : (totalElements / limit) + 1;
		List<T> response = typedQuery.setFirstResult(page).setMaxResults(limit).getResultList();
		boolean approvalSetting = this.approvalSettingsService.getApprovalSettings(moduleId);
		
		for (T t2 : response) {
			Class<? extends Object> ftClass = t2.getClass();
			Field field;
			Long id = null;
			Long status = 0L;
			Date modifiedDate = null;
			try {
				Map<String, Object> appprovalInfo = new HashMap<String, Object>();
				
				if(approvalSetting) {
					field = ftClass.getDeclaredField("id");								field.setAccessible(true);
					id = Long.valueOf(field.get(t2).toString());
					
					field = ftClass.getSuperclass().getDeclaredField("modifiedDate");	field.setAccessible(true);
					modifiedDate = (Date)field.get(t2);
					
					field = ftClass.getSuperclass().getDeclaredField("status");			field.setAccessible(true);
					status = Long.valueOf(field.get(t2).toString());
					
					
					
					appprovalInfo.put("approvalDetails", this.approvalSettingsService.getApprovalList(moduleId, id, status));
					appprovalInfo.put("pendingApprovalLevelWise", approvalSettingsService.getPendingApprovalLevelWise(moduleId, id));
					appprovalInfo.put("pendingApproversDetails", this.approvalSettingsService.getApprovers(id, moduleId));
					appprovalInfo.put("approval", this.notificationService.checkIfApprovalPresent("Approval", moduleId, id, userId));
					appprovalInfo.put("edit", this.notificationService.checkIfEditAllowed(moduleId, id, modifiedDate));
				}else {
					appprovalInfo.put("approvalDetails", new ArrayList<ApprovalDetails>());
					appprovalInfo.put("pendingApprovalLevelWise", new ArrayList<PendingApprovalDTO>());
					appprovalInfo.put("pendingApproversDetails", new ArrayList<PendingApproversDetails>());
					appprovalInfo.put("approval", false);
					appprovalInfo.put("edit", true);
				}
				
				 if(!ftClass.getSuperclass().isPrimitive()) {
					   field=ftClass.getSuperclass().getDeclaredField("infoApproval");
						field.setAccessible(true);
						field.set(t2, appprovalInfo);
				 }else {
					    field=ftClass.getField("infoApproval");
						field.setAccessible(true);
						field.set(t2, appprovalInfo);
				 }
			
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return new CommonPagingResponse<T>(response, response.size(), limit, totalElements, totalPage, screenFilterDTO,
				getDefaultSorting(moduleId));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CommonDropdownResponseDataTravelObject> getSelectionList(String queryString) {
		return entityManager.createQuery(queryString).getResultList();
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> getAllShowablwFieldByModuleId(long moduleId, boolean isIndexing) {
		List<String> fieldsList = entityManager
				.createNativeQuery("select fi.data_field_name as fieldName from custom_fields cf "
						+ "inner join fields_indexing fi on fi.id = cf.field_indexing_id " + "where cf.is_indexing = "
						+ isIndexing + " and cf.module_id = " + moduleId + " and fi.data_field_name IS NOT NULL")
				.getResultList();
		Map<String, Object> fieldsMap = new HashMap<String, Object>();
		fieldsList.forEach((fieldName) -> fieldsMap.put(fieldName, fieldName));
		return fieldsMap;
	}

	@SuppressWarnings("unchecked")
	private List<DefaultSortDto> getDefaultSorting(long moduleId) {
		return entityManager.createQuery("SELECT new com.sterp.multitenant.tenant.settings.template.entity.dto."
				+ "DefaultSortDto(srt.id, srt.moduleId, srt.sortOrder, fI, srt.sortingName, srt.defaultFlag, fI.id)"
				+ " from com.sterp.multitenant.tenant.settings.template.entity.DefaultSort srt"
				+ " left outer join srt.fieldIndexing fI where srt.moduleId =" + moduleId).getResultList();

	}

	@SuppressWarnings("unchecked")
	private List<Order> getSortOrder(Map<String, Object> groupSort, Map<String, Object> joinMap,
			CriteriaBuilder criteriaBuilder, Long moduleId) {
		List<Order> orderList = new ArrayList<Order>();
		/* Group Sorting (Sorting By Multiple Column) */
		if (groupSort.size() > 0) {
			groupSort.forEach((sorting, ordering) -> {
				if (sorting.contains("."))
					orderList.add(ordering.equals("asc")
							? criteriaBuilder.asc(
									((Join<Object, Object>) joinMap.get(sorting.substring(0, sorting.lastIndexOf("."))))
											.get(sorting.substring(sorting.lastIndexOf(".") + 1)))
							: criteriaBuilder.desc(
									((Join<Object, Object>) joinMap.get(sorting.substring(0, sorting.lastIndexOf("."))))
											.get(sorting.substring(sorting.lastIndexOf(".") + 1))));
				
				  else { System.out.println(joinMap.get("root")); orderList.add(
				  ordering.equals("asc") ? criteriaBuilder.asc(((Root<?>)
				  joinMap.get("root")).get(sorting)) : criteriaBuilder.desc(((Root<?>)
				  joinMap.get("root")).get(sorting))); }
				 

			});
		}
		return orderList;
	}

	private <T> Map<String, Object> getDynamicMapping(Map<String, Object> indexingField, Root<T> root) {
		Map<String, Object> joinMap = new HashMap<String, Object>();
		// indexingField.put("bankAccounts.bankName", "bankAccounts.bankName");
		indexingField.forEach((key, value) -> {
			if (key.contains(".")) {
				if (!joinMap.containsKey(key.substring(0, key.lastIndexOf("."))))
					joinMap.put(key.substring(0, key.lastIndexOf(".")),
							root.join(key.substring(0, key.lastIndexOf(".")), JoinType.LEFT));
			} else if (!joinMap.containsKey("root"))
				joinMap.put("root", root);
		});
		return joinMap;
	}

	@Override
	public <T> T getById(Class<T> T, Long id) {
		return entityManager.find(T, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CommonDropdownResponseDto> getAutogeneratedFields(String T, long moduleId) {
		String query = "select new com.sterp.multitenant.tenant.dto." + T
				+ "(cf.id, fi.dataFieldName) from CustomField cf inner join FieldIndexing fi on cf.fieldIndexing.id = fi.id"
				+ " where cf.type = 'AutoGenerate' and cf.module = " + moduleId;
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public <T> String getLastGeneratedCode(Class<T> T, Long branchId, Long companyId, String field) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
		Root<T> root = criteriaQuery.from(T);
		List<Predicate> andPredicates = new ArrayList<Predicate>();
		criteriaQuery.multiselect(root.get(field));
		andPredicates.add(criteriaBuilder.isNotNull(root.get(field)));
		if (companyId > 0)
			andPredicates.add(criteriaBuilder.equal(root.get("companyId"), companyId));
		else if (branchId > 0)
			andPredicates.add(criteriaBuilder.equal(root.get("branchId"), branchId));
		criteriaQuery.where(criteriaBuilder.and(andPredicates.toArray(new Predicate[andPredicates.size()])));
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createdDate")));
		final TypedQuery<String> typedQuery = entityManager.createQuery(criteriaQuery);
		List<String> codeList = typedQuery.getResultList();
		return codeList.isEmpty() ? null : codeList.get(0);
	}

	@Override
	public <R> String getLastGeneratedCode(Class<R> R, long refDocId, String field) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
		Root<R> root = criteriaQuery.from(R);
		criteriaQuery.multiselect(root.get(field));
		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), refDocId));
		final TypedQuery<String> typedQuery = entityManager.createQuery(criteriaQuery);
		List<String> codeList = typedQuery.getResultList();
		return codeList.isEmpty() ? null : codeList.get(0);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getCommonListByNativeQuery() {
		String query = "select * from plan1_46_v25.company_year cm where cm.id>0 ";
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public <T> List<T> getCommonList(Class<T> T) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(T);
		Root<T> root = criteriaQuery.from(T);
		final TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery.select(root));
		return typedQuery.getResultList();
	}

	@Override
	public <T> List<T> getCustomFieldByModuleId(Class<T> T, Long moduleId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(T);
		Root<T> root = criteriaQuery.from(T);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("module"), moduleId));
		final TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	@Override
	public <T> List<T> getByFilters(Class<T> T, Map<String, Object> filters) {
		System.out.println("********"+filters.toString()+" Class  "+T.getName());
		List<Predicate> andPredicates = new ArrayList<Predicate>();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(T);
		Root<T> root = criteriaQuery.from(T);
		criteriaQuery.select(root);
		filters.forEach((field, value) -> {
			System.out.println("****column name: "+field);
			andPredicates.add(value == null ? criteriaBuilder.isNull(root.get(field)) : criteriaBuilder.equal(root.get(field), value));
		});
		if(andPredicates.size() > 0) criteriaQuery.where(criteriaBuilder.and(andPredicates.toArray(new Predicate[andPredicates.size()])));
		final TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

//	@Override
//	public <T> CommonDropdownResponseDataTravelObject getLastGeneratedCode(Class<T> T, Long branchId, Long companyId, String field) {
//		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//		CriteriaQuery<CommonDropdownResponseDataTravelObject> criteriaQuery = criteriaBuilder.createQuery(CommonDropdownResponseDataTravelObject.class);
//		Root<T> root = criteriaQuery.from(T);
//		List<Predicate> andPredicates = new ArrayList<Predicate>();
//		criteriaQuery.multiselect(root.get("id") ,root.get(field));
//		andPredicates.add(criteriaBuilder.isNotNull(root.get(field)));
//		if(companyId > 0)
//			andPredicates.add(criteriaBuilder.equal(root.get("companyId"), companyId));
//		else if(branchId > 0)
//			andPredicates.add(criteriaBuilder.equal(root.get("branchId"), branchId));
//		criteriaQuery.where(criteriaBuilder.and(andPredicates.toArray(new Predicate[andPredicates.size()])));
//		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createdDate")));  
//		final TypedQuery<CommonDropdownResponseDataTravelObject> typedQuery = entityManager.createQuery(criteriaQuery);
//		List<CommonDropdownResponseDataTravelObject> codeList = typedQuery.getResultList();
//		return codeList.isEmpty() ? null : codeList.get(0);
//	}
	
	@Override
	public String getFolderFields(long moduleId) {
		String query = "Select fi.dataFieldName FROM CustomField cf INNER JOIN FieldIndexing fi ON cf.fieldIndexing.id = fi.id"
				+ " WHERE cf.folderFlag = 1 and cf.module = "+ moduleId;
		List<Object> fieldName = entityManager.createQuery(query).getResultList();
		return fieldName.isEmpty() ? null : fieldName.get(0).toString();
	}

}
