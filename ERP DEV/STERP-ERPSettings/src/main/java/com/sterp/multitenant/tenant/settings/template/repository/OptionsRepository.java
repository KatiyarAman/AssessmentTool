package com.sterp.multitenant.tenant.settings.template.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.settings.template.entity.Options;

@Repository
public interface OptionsRepository extends JpaRepository<Options, Integer> {
	Collection<Options> findByCustomFieldId(Integer customFieldId);
}
