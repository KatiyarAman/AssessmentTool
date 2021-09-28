package com.sterp.multitenant.tenant.settings.template.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.modules.dto.ButtonDto;
import com.sterp.multitenant.tenant.settings.template.entity.Buttons;

@Repository
public interface ButtonsRepository extends JpaRepository<Buttons, Integer> {
	Buttons findByActionId(long actionId);
	
	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject(btn.id,btn.buttonName)"
			+ " from com.sterp.multitenant.tenant.settings.template.entity.Buttons btn where btn.status= 1")
	List<CommonDropdownResponseDataTravelObject> getSelectionList();


	@Query(value = "SELECT new com.sterp.multitenant.tenant.modules.dto.ButtonDto(btn.id,btn.buttonName, btn.buttonClass, btn.icon, btn.purpose, btn.status, btn.type) from "
			+ "com.sterp.multitenant.tenant.settings.template.entity.Buttons btn "
			+ "where btn.id like concat('%',:wildCard,'%') or btn.buttonName like concat('%',:wildCard,'%') or "
			+ "btn.buttonClass like concat('%',:wildCard,'%') ")
	Page<ButtonDto> findCustomSearch(String wildCard, Pageable pageable);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.modules.dto.ButtonDto(btn.id,btn.buttonName, btn.buttonClass, btn.icon, btn.purpose, btn.status, btn.type) from "
			+ "com.sterp.multitenant.tenant.settings.template.entity.Buttons btn")
	Page<ButtonDto> findCustom(Pageable pageable);

}
