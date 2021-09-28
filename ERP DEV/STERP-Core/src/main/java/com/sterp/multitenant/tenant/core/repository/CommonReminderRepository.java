package com.sterp.multitenant.tenant.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sterp.multitenant.tenant.model.CommonReminder;
import com.sterp.multitenant.tenant.modules.dto.CommonReminderDto;

public interface CommonReminderRepository extends JpaRepository<CommonReminder, Long> {
	
	@Query("SELECT NEW com.sterp.multitenant.tenant.modules.dto.CommonReminderDto(cr.id, cr.moduleId, cr.docRefId, cr.remarks, cr.postedTimestamp, "
			+ " cr.reminderTimestamp, ue.username) FROM "
			+ " com.sterp.multitenant.tenant.model.CommonReminder cr INNER JOIN com.sterp.multitenant.tenant.entity.UserEntity ue"
			+ " ON cr.createdBy = ue.id WHERE cr.moduleId = :moduleId AND cr.docRefId = :docRefId ORDER BY cr.postedTimestamp DESC")
	List<CommonReminderDto> findAllByModuleIdAndDocRefIdOrderByPostedTimestampDesc(Long moduleId, Long docRefId);
}
