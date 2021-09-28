package com.sterp.multitenant.tenant.uploadutility.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.uploadutility.entity.DocumentFolder;

@Repository
public interface DocumentFolderRepository extends JpaRepository<DocumentFolder, Long> {

//	DocumentFolder findByFolderNameAndStatus(String folderName, Long status);

	Optional<DocumentFolder> findByFolderNameAndStatusAndModuleId(String folderName, Long status,long moduleId);

	Optional<DocumentFolder> findByfolderName(String folderName);
	
	Optional<DocumentFolder> findByParentFolderIdAndStatusAndModuleId(Long parentFolderId, long status,long moduleId);

	List<DocumentFolder> findByParentFolderIdAndStatus(long parentFolderId, long status);

}
