package com.sterp.multitenant.tenant.settings.codegeneration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sterp.multitenant.tenant.settings.codegeneration.entity.CodeGeneration;

public interface CodeGenerationRepository extends JpaRepository<CodeGeneration, Long>{

	@Query("from CodeGeneration where moduleId =:moduleId and field.id =:customFieldId and modulePrefix=:modulePrefix and status=:status")
	CodeGeneration getByModuleIdCustomFieldIdModuleRefFieldId(long moduleId, int customFieldId, long modulePrefix, Long status);

}
