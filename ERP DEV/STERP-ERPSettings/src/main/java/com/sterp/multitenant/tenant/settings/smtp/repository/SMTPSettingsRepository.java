package com.sterp.multitenant.tenant.settings.smtp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.settings.smtp.entity.SMTPSettings;

@Repository
public interface SMTPSettingsRepository extends JpaRepository<SMTPSettings, Integer> {

	@Query(value = "SELECT smtp from com.sterp.multitenant.tenant.settings.smtp.entity.SMTPSettings smtp where "
			+ "smtp.host like concat('%',:wildCard,'%') or cast(smtp.port as string) like concat('%',:wildCard,'%') or "
			+ "cast(smtp.auth as string) like concat('%',:wildCard,'%') or smtp.username like concat('%',:wildCard,'%')")
	Page<SMTPSettings> findCustom(Pageable pageable, String wildCard);
}
