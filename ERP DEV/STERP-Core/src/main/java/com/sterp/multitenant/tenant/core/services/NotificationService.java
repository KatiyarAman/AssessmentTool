package com.sterp.multitenant.tenant.core.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sterp.multitenant.tenant.model.Notifications;

public interface NotificationService {
	public List<Notifications> saveNotifications(List<Notifications> notifications);

	public Map<String, Object> getAllNotifications();

	public Notifications saveNotification(Notifications notifications);

	public boolean checkIfApprovalPresent(String refType, long moduleId, Long docId, Long userId);

	public boolean checkIfEditAllowed(String refType, long moduleId, Long docId, Long userId);

	public Notifications getNotication(long moduleId, String refType, Long refDocId, Long status, Long userId);

	public boolean checkForNotificationOther(long moduleId, String refType, Long docId, Long userId);

	public long getNotication(long moduleId, Long docId, Long userId);

	public List<Notifications> getNotications(long documentId, long moduleId);

	public boolean checkIfEditAllowed(long moduleId, Long documentId, Date notificationDate);

}
