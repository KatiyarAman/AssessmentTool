package com.sterp.multitenant.tenant.core.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.model.Notifications;

@Repository
public interface NotificationsRepository extends JpaRepository<Notifications, Long> {
	public Notifications findByRefTypeAndModuleIdAndRefDocIdAndUserIdAndStatus(String refType, long moduleId,
			Long docId, Long userId, Long status);

	public List<Notifications> findByRefTypeAndModuleIdAndRefDocIdAndStatus(String refType, long moduleId, Long docId,
			Long status);

	public Notifications findByModuleIdAndRefTypeAndRefDocIdAndUserIdAndStatus(long moduleId, String refType,
			Long refDocId, Long userId, Long status);

	public List<Notifications> findByUserIdAndStatus(Long userId, int i);

	public Notifications findByModuleIdAndRefDocIdAndUserIdAndStatus(long moduleId, Long docId, Long userId,
			Long status);

	@Query("select ns from notifications ns where ns.moduleId=:moduleId and ns.refDocId=:docId and ns.userId in (:users) and ns.status=:status")
	public List<Notifications> findCustom(@Param("moduleId") long moduleId, @Param("docId") Long docId,
			@Param("users") List<Long> users, @Param("status") Long status);

	public List<Notifications> findByModuleIdAndRefDocIdAndStatus(long moduleId, long refDocId, Long status);

	@Query("select ns from notifications ns where ns.moduleId=:moduleId and ns.refDocId=:documentId and ns.notificationDate >:notificationDate and ns.status=:status")
	public List<Notifications> findByModuleIdAndRefDocIdAndDateAndStatus(long moduleId, Long documentId, Date notificationDate, Long status);

}
