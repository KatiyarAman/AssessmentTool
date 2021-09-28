package com.sterp.multitenant.tenant.settings.template.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.settings.template.entity.FieldValidations;

@Repository
public interface FieldValidationsRepository extends JpaRepository<FieldValidations, Integer> {
	List<FieldValidations> findByCustomFieldIdAndStatus(int moduleId, Long status);
}
