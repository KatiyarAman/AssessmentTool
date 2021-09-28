package com.sterp.multitenant.tenant.settings.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.settings.template.entity.FieldType;

@Repository
public interface FieldTypeRepository extends JpaRepository<FieldType, Integer> {

}
