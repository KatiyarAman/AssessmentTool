package com.sterp.multitenant.tenant.uploadutility.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.uploadutility.entity.DocumentContent;

@Repository
public interface DocumentContentRepository extends JpaRepository<DocumentContent, Long> {

	DocumentContent findByDocumentVersionId(long documentVersionId);
}
