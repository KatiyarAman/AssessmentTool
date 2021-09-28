package com.sterp.multitenant.tenant.core.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterp.multitenant.tenant.core.repository.NotificationsRepository;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.core.services.NotificationService;
import com.sterp.multitenant.tenant.dto.NotificationResponseBean;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.model.Notifications;
import com.sterp.multitenant.tenant.service.ModuleServiceMain;

@Service
public class NoticationsServiceImpl implements NotificationService {

	@Autowired
	NotificationsRepository notificationsRepository;

	@Autowired
	AppService appService;

	@Autowired
	ModuleServiceMain moduleService;

	@Override
	public List<Notifications> saveNotifications(List<Notifications> notifications) {
		return this.notificationsRepository.saveAll(notifications);
	}

	@Override
	public boolean checkIfApprovalPresent(String refType, long moduleId, Long docId, Long userId) {
		Notifications notifications = this.notificationsRepository
				.findByRefTypeAndModuleIdAndRefDocIdAndUserIdAndStatus(refType, moduleId, docId, userId, 1L);
		if (notifications != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkIfEditAllowed(String refType, long moduleId, Long docId, Long userId) {
		UserEntity dto = this.appService.getCurrentUser();
		Notifications notifications = this.notificationsRepository
				.findByRefTypeAndModuleIdAndRefDocIdAndUserIdAndStatus(refType, moduleId, docId, userId, 1L);
		if (notifications != null && notifications.getUserId() == dto.getId()) {
			return true;
		}
		return false;
	}

	@Override
	public Notifications getNotication(long moduleId, String refType, Long refDocId, Long status, Long userId) {
		return this.notificationsRepository.findByModuleIdAndRefTypeAndRefDocIdAndUserIdAndStatus(moduleId, refType,
				refDocId, userId, status);
	}

	@Override
	public Notifications saveNotification(Notifications notification) {
		return this.notificationsRepository.save(notification);
	}

	@Override
	public boolean checkForNotificationOther(long moduleId, String refType, Long docId, Long userId) {
		Notifications notifications = this.notificationsRepository
				.findByRefTypeAndModuleIdAndRefDocIdAndUserIdAndStatus(refType, moduleId, docId, userId, 1L);
		if (notifications == null) {
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Object> getAllNotifications() {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		UserEntity currentUser = this.appService.getCurrentUser();
		List<NotificationResponseBean> beans = new ArrayList<NotificationResponseBean>();
		NotificationResponseBean responseBean = null;
		List<Notifications> notifications = this.notificationsRepository.findByUserIdAndStatus(currentUser.getId(), 1);
		for (Notifications notification : notifications) {
			Long time = this.appService.hoursDifference(new Date(System.currentTimeMillis()),
					notification.getNotificationDate());
			String message = "You have a notification request for "
					+ this.moduleService.getModuleById(notification.getModuleId(), true);
			String userName = currentUser.getUsername();
			responseBean = new NotificationResponseBean();
			responseBean.setTime(time);
			responseBean.setMessage(message);
			responseBean.setUserName(userName);
			beans.add(responseBean);
		}
		responseMap.put("notifications", beans);
		return responseMap;
	}

	@Override
	public long getNotication(long moduleId, Long docId, Long userId) {
		Notifications notification = this.notificationsRepository.findByModuleIdAndRefDocIdAndUserIdAndStatus(moduleId,
				docId, userId, 1L);

		if (notification != null) {
			return notification.getParentId();
		}
		return 0l;
	}

	@Override
	public List<Notifications> getNotications(long documentId, long moduleId) {
		return this.notificationsRepository.findByModuleIdAndRefDocIdAndStatus(moduleId, documentId, 1L);
	}

	@Override
	public boolean checkIfEditAllowed(long moduleId, Long documentId, Date notificationDate) {
		List<Notifications> notifications = this.notificationsRepository.findByModuleIdAndRefDocIdAndDateAndStatus(moduleId, documentId, notificationDate, 1L);
		if(notifications != null && notifications.size() > 0)
			return true;
		return false;
	}

}
