package com.sterp.multitenant.tenant.uploadutility.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.dto.UploadDocumentVersionResponseDTO;
import com.sterp.multitenant.tenant.uploadutility.entity.Documents;

@Repository
public interface DocumentsRepository extends JpaRepository<Documents, Long> {
	
	@Query(nativeQuery = true,value="WITH RECURSIVE tree AS ( "
			+" SELECT id, module_id,parent_folder_id,id AS top_level_id FROM document_folder UNION ALL "
			+" SELECT c.id,c.module_id,c.parent_folder_id, COALESCE(t.top_level_id) AS top_level_id "
			+" FROM document_folder c JOIN tree t ON c.parent_folder_id = t.id) "
			+" SELECT REPLACE(group_concat(folder_name,'/'),',','') as folder_path, d.document_name as document_name,d.id as document_id "
			+" FROM document_folder df"
			+ " LEFT JOIN tree ON tree.top_level_id = df.id "
			+ "LEFT JOIN documents d ON d.document_folder_id = tree.id"
		    +" WHERE tree.module_id =:moduleId and d.ref_doc =:refDocId group by tree.id, d.id")
			public Object[][] getFolderPath(Long moduleId,Long refDocId);
	
	
	@Query(value="SELECT new com.sterp.multitenant.tenant.dto.UploadDocumentVersionResponseDTO "
			+ " (udv.id,udv.revisionNumber, udv.originalFileName,CONCAT(:folderPath,udv.originalFileName)) From com.sterp.multitenant.tenant.uploadutility.entity.DocumentVersion udv "
			+ " WHERE udv.documentId=:documentId")
	List<UploadDocumentVersionResponseDTO> getVersions(Long documentId, String folderPath);

}
