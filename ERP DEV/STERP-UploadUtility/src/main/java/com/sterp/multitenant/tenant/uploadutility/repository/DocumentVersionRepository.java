package com.sterp.multitenant.tenant.uploadutility.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.uploadutility.entity.DocumentVersion;

@Repository
public interface DocumentVersionRepository extends JpaRepository<DocumentVersion, Long>{
	DocumentVersion findByHashKeyAndStatus(String hashkey, Long status);
	
	DocumentVersion findByFileNameAndDocumentIdAndStatus(String fileName,long documentId, Long status);

	


}
