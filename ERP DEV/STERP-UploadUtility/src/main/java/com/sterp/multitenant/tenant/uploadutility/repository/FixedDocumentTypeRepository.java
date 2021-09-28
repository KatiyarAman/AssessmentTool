package com.sterp.multitenant.tenant.uploadutility.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.uploadutility.entity.FixedDocumentType;

@Repository
public interface FixedDocumentTypeRepository extends JpaRepository<FixedDocumentType, Long> {

}
