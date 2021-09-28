package com.sterp.multitenant.tenant.settings.approval.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.amazonaws.services.appstream.model.ResourceNotAvailableException;
import com.sterp.multitenant.tenant.core.repository.NotificationsRepository;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.core.services.NotificationService;
import com.sterp.multitenant.tenant.core.services.StatusDetailsService;
import com.sterp.multitenant.tenant.dto.Mail;
import com.sterp.multitenant.tenant.dto.UserDTO;
import com.sterp.multitenant.tenant.dto.UserResponse;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.exceptionhandler.CustomException;
import com.sterp.multitenant.tenant.exceptionhandler.ResourceNotFoundException;
import com.sterp.multitenant.tenant.model.Notifications;
import com.sterp.multitenant.tenant.modules.entity.Actions;
import com.sterp.multitenant.tenant.modules.entity.Modules;
import com.sterp.multitenant.tenant.modules.service.ModuleService;
import com.sterp.multitenant.tenant.repository.BranchUserRoleRepository;
import com.sterp.multitenant.tenant.service.UserService;
import com.sterp.multitenant.tenant.settings.approval.dto.ApprovalSettingsResponseDTO;
import com.sterp.multitenant.tenant.settings.approval.dto.PendingApprovalDTO;
import com.sterp.multitenant.tenant.settings.approval.dto.PendingApproversDetails;
import com.sterp.multitenant.tenant.settings.approval.entity.ApprovalDetails;
import com.sterp.multitenant.tenant.settings.approval.entity.ApprovalGroup;
import com.sterp.multitenant.tenant.settings.approval.entity.ApprovalGroupUsers;
import com.sterp.multitenant.tenant.settings.approval.entity.ApprovalSettings;
import com.sterp.multitenant.tenant.settings.approval.entity.ApproverLevel;
import com.sterp.multitenant.tenant.settings.approval.entity.Approvers;
import com.sterp.multitenant.tenant.settings.approval.enumtype.ApprovalBased;
import com.sterp.multitenant.tenant.settings.approval.enumtype.ApprovalSystem;
import com.sterp.multitenant.tenant.settings.approval.repository.ApprovalAssignRepository;
import com.sterp.multitenant.tenant.settings.approval.repository.ApprovalDetailsRepository;
import com.sterp.multitenant.tenant.settings.approval.repository.ApprovalGroupRepository;
import com.sterp.multitenant.tenant.settings.approval.repository.ApprovalSettingsRepository;
import com.sterp.multitenant.tenant.settings.approval.service.ApprovalSettingsService;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;

@Service
public class ApprovalSettigsServiceImpl implements ApprovalSettingsService {

	@Autowired
	ApprovalSettingsRepository approvalSettingsRepository;

	@Autowired
	AppService appService;

	@Autowired
	NotificationService notificationService;

	@Autowired
	UserService userService;

	@Autowired
	JavaMailSender mailSenderService;

	@Autowired
	ApprovalDetailsRepository approvalDetailsRepository;

	@Autowired
	NotificationsRepository notificationsRepository;

	@Autowired
	ApprovalAssignRepository approvalAssignRepository;

	@Autowired
	Environment environment;

	@Autowired
	BranchUserRoleRepository branchUserRoleRepository;

	@Autowired
	StatusDetailsService statusDetailsService;

	@Autowired
	ModuleService moduleService;

	@Autowired
	ApprovalGroupRepository approvalGroupRepository;

	@Override
	public synchronized Long getApprovalSetting(boolean approvalLine, long moduleId, Long docId, String transactionNo,
			float value, Date time, int volume, Long createrId, Long layerId, Long status) {
		Long approvalStatus = 0L;
		Long revisedState = 0L;
		UserEntity currentUser = this.appService.getCurrentUser();
		UserDTO creater = this.userService.getUserById(createrId);
//
		Long empId = currentUser.getEmployeeId();
		Long deptIdDoc = this.approvalSettingsRepository.findCustom(empId != null ? empId : 0);
		Date current = new Date(System.currentTimeMillis());
		Modules childModule = this.moduleService.getModuleById(moduleId, true);

		Set<Actions> approvalAction = childModule.getActions().stream()
				.filter(a -> a.getActions().equalsIgnoreCase("approve")).collect(Collectors.toSet());

		if (approvalAction.size() != 0) {
			ApprovalSettings approvalSettings = this.approvalSettingsRepository.findByModuleIdAndStatus(moduleId, 2L);

			if (approvalSettings != null) {
				ApprovalBased approvalBased = approvalSettings.getApprovalBased();
				/*
				 * LOGIC FOR CHECKING CODITIONALLY LEVEL OF APPROVAL NEEDED ON BASIS OF APPROVAL
				 * SYSTEM PARALLEL OR SEQUENCIAL
				 */
				ApprovalSystem approvalSystem = approvalSettings.getApprovalSystem();
				if (approvalSystem == ApprovalSystem.sequential) {

					List<ApproverLevel> approverLevelList = approvalSettings.getApproverAssignList();
					List<Notifications> notifications = new ArrayList<Notifications>();
					List<String> mailingList = new ArrayList<String>();
					// String labelName = "";
					List<Long> approverIdsPresent = new ArrayList<Long>();
					for (ApproverLevel approverLevel : approverLevelList) {
						if (approverLevel.getId() <= layerId) {
							continue;
						}
						// labelName = approverLevel.getLevelName();
						double min = approverLevel.getMinRange();
						double max = approverLevel.getMaxRange();
						List<Approvers> approvers = Collections.emptyList();
						switch (approverLevel.getApprovalType()) {
						case "AG":
							ApprovalGroup approvalGroupOption = this.approvalGroupRepository
									.findById(approverLevel.getApprovalGroupId())
									.orElseThrow(() -> new ResourceNotAvailableException("No Approvers Found"));
							for (ApprovalGroupUsers approvalusers : approvalGroupOption.getApprovalGroupUsers()) {
								Approvers approver = new Approvers();
								approver.setUserId(approvalusers.getUserId());
								approvers.add(approver);
							}
							break;
						case "RM":
							Long reportingUserId = this.approvalSettingsRepository.findReportingUserId(creater.getId());
							if (reportingUserId == null) {
								new ResourceNotAvailableException("No Reporting User Found");
							}
							Approvers approver = new Approvers();
							approver.setUserId(reportingUserId);
							approvers.add(approver);
							break;
						case "IU":
							approvers = approverLevel.getApprovers();
							break;
						default:
							new ResourceNotAvailableException(
									"Approver Type missmatch. Define AG as Approval Group, RM as Reporting Manager and IU as Individual Users in your database configuration");
							break;
						}

						Notifications notification = null;
						if (approverLevel.getDepartmentWise() == 1) {
							for (Approvers approverDetail : approvers) {
								if (createrId == approverDetail.getUserId()) {
									continue;
								}
								UserDTO user = this.userService.getUserById(approverDetail.getUserId());
								Long deptIdUser = this.approvalSettingsRepository
										.findCustom(user.getEmployeeId() != null ? user.getEmployeeId() : 0);
								if (deptIdDoc == deptIdUser) {
									/*
									 * CHECK IF SUBMITION WAS AGAINST THE REVERT CASE AND APPROVAL STATUS WAS 37, IF
									 * YES MAKE ITS STATUS TO 0
									 */
									ApprovalDetails currentUserRevertedDocument = this.approvalDetailsRepository
											.findByModuleIdAndRefDocIdAndParentIdAndUserIdAndApprovalStatusAndStatus(
													moduleId, docId, approverLevel.getId(), user.getId(), 11L, 2L);
									if (currentUserRevertedDocument != null) {
										/* IT DEFINES THIS USER HAS REVERTED BACK THIS DOCUMENT */
										/*
										 * FUTURE IMPLEMENTATION IF WANT TO RESEND THIS DOCUMENT TO THE USER WHO
										 * REVERTED IT BACK
										 */
										currentUserRevertedDocument.setStatus(0L);
										this.approvalDetailsRepository.saveAndFlush(currentUserRevertedDocument);
									}

									if (this.checkRange(approvalBased, min, max, value, time, volume)) {
										notification = new Notifications();
										notification.setNotificationDate(current);
										notification.setRefDocId(docId);
										notification.setModuleId(moduleId);
										notification.setUserId(user.getId());
										notification.setParentId(approverLevel.getId());
										notification.setStatus(2L);
										notification.setRefType("Approval");

										approverIdsPresent.add(user.getId());
										notifications.add(notification);
										mailingList.add(user.getEmail());
									}
								}
							}
						} else {
							for (Approvers approverDetail : approvers) {
								if (createrId == approverDetail.getUserId()) {
									continue;
								}
								UserDTO user = this.userService.getUserById(approverDetail.getUserId());
								if (this.checkRange(approvalBased, min, max, value, time, volume)) {
									notification = new Notifications();
									notification.setNotificationDate(current);
									notification.setRefDocId(docId);
									notification.setModuleId(moduleId);
									notification.setUserId(user.getId());
									notification.setParentId(approverLevel.getId());
									notification.setStatus(2L);
									notification.setRefType("Approval");
									approverIdsPresent.add(user.getId());
									notifications.add(notification);
									mailingList.add(user.getEmail());
								}
							}
						}
						if (notifications.size() != 0) {
							break;
						}
					}

					/*
					 * Check If Recheck was done and set it to 0 status
					 */
					Notifications previousNotification = this.notificationService.getNotication(moduleId, "Returned",
							docId, 2L, createrId);
					if (previousNotification != null && approvalLine == false) {
						revisedState = 12L;// revised status from StatusDetails Table
						previousNotification.setStatus(1L);
						this.notificationsRepository.save(previousNotification);
					}

					if (notifications.size() != 0) {
						if (this.notificationsRepository.findCustom(moduleId, docId, approverIdsPresent, 2L)
								.isEmpty()) {
							this.notificationService.saveNotifications(notifications);
						}
						approvalStatus = 23L;
						// approvalStatus = 8L; // 8 - approved status from StatusDetails Table
					} else {
						List<Notifications> checkNoti = this.notificationsRepository
								.findByRefTypeAndModuleIdAndRefDocIdAndStatus("Approval", moduleId, docId, 2L);
						if (checkNoti.size() != 0) {
							approvalStatus = 18L; // Hold status from StatusDetails Table for Line Item Approvals
						} else {
							approvalStatus = 0L;
						}
						approvalStatus = 8L; // 8 - approved status from StatusDetails Table
					}
					if (mailingList.size() != 0) {
						// log.info("Sending Email to Approvers :" + mailingList.toString());
						Mail mail = new Mail();
						mail.setFrom(creater.getEmail());
						mail.setTos(mailingList);
						mail.setSubject("Approval Request");
						mail.setDocumentNo(transactionNo);
						Map<String, Object> model = new HashMap<String, Object>();
						model.put("name", "User");
						model.put("location", "Noida");
						model.put("documentNo", transactionNo);
						model.put("signature", "test");
						model.put("accessUrl", environment.getProperty("server.servlet.context-path") + "/" + moduleId
								+ "/edit/" + docId + "/1");
						mail.setModel(model);
//						try {
//							emailService.sendHTMLMail(mail);
//						} catch (MessagingException e) {
//							e.printStackTrace();
//						} catch (IOException e) {
//							e.printStackTrace();
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
					}
				} else {
					/* Parallel Logic Goes Here */
				}
			} else {
				approvalStatus = 19L; // 19 -self approved status from StatusDetails Table ( If document is
										// self-approved)
			}
		} else {
			approvalStatus = status; // 19 -self approved status from StatusDetails Table ( If document is
			// self-approved)
		}
		return revisedState == 0 ? approvalStatus : revisedState;
	}

	public boolean checkRange(ApprovalBased approvalBased, double min, double max, float value, Date time, int volume) {
		if (approvalBased == ApprovalBased.Value) {
			if (value >= min && value <= max) {
				return true;
			}
		} else if (approvalBased == ApprovalBased.Time) {
			Date current = new Date(System.currentTimeMillis());
			long timeDifference = this.appService.hoursDifference(current, time);
			if (timeDifference >= min && timeDifference <= max) {
				return true;
			}
		} else if (approvalBased == ApprovalBased.Volume) {
			if (volume >= min && volume <= max) {
				return true;
			}
		}
		return false;
	}

	@Override
	public synchronized Long updateApproval(boolean approvalLine, long moduleId, String refType, Long docId,
			Long userId, String transactionNo, float value, Date time, int volume, Long status, Long createrId,
			String remarks, Long docStatus) {
		// StatusDetails statusDetails =
		// this.statusDetailsService.getStatusDetailsById(status);
		List<String> sendTo = new ArrayList<String>();
		sendTo.add(this.userService.getUserById(createrId).getEmail());
		Date current = new Date(System.currentTimeMillis());
		Notifications notification = this.notificationsRepository
				.findByRefTypeAndModuleIdAndRefDocIdAndUserIdAndStatus(refType, moduleId, docId, userId, 2L);
		if (notification == null) {
//			throw new org.springframework.security.access.AccessDeniedException("This is an unauthorized request.");
			return status;
		}
		ApprovalDetails approval = new ApprovalDetails();
		approval.setFromUserId(createrId);
		approval.setApprovalDate(current);
		approval.setApprovalStatus(status);
		approval.setRemarks(remarks);
		approval.setModuleId(moduleId);
		approval.setParentId(notification.getParentId());
		approval.setUserId(userId);
		approval.setStatus(2L);
		approval.setRefDocId(docId);
		ApprovalDetails approvalDetails = this.approvalDetailsRepository.saveAndFlush(approval);

		if (approvalDetails.getId() != null && approvalLine == false) {
			List<Notifications> notifications = this.notificationsRepository
					.findByRefTypeAndModuleIdAndRefDocIdAndStatus(refType, moduleId, docId, 2L);
			for (Notifications notifi : notifications) {
				if (notifi.getParentId() == notification.getParentId()) {
					notifi.setStatus(0L);
				}
			}
			this.notificationsRepository.saveAll(notifications);
		}
		if (status == 9) {
			/*
			 * this.mailSenderService.sendTextMail(sendTo, "Document " + refType +
			 * " reject by the Approver.", "This is to inform you document no. " +
			 * transactionNo + " has been rejected by the Approver.");
			 */
			return status;
		}
		if (status == 11) {
			Notifications revertBack = new Notifications();
			revertBack.setNotificationDate(current);
			revertBack.setParentId(notification.getParentId());
			revertBack.setStatus(2L);
			revertBack.setModuleId(moduleId);
			revertBack.setUserId(createrId);
			revertBack.setRefType("Returned");
			revertBack.setRefDocId(docId);
			this.notificationsRepository.save(revertBack);
//			this.mailSenderService.sendTextMail(sendTo, "Document " + refType + " send for Recheck.",
//					"This is to inform you document no. " + transactionNo + " has been reverted back for recheck.");
			return status;
		}
		return this.getApprovalSetting(approvalLine, moduleId, docId, transactionNo, value, time, volume, createrId,
				notification.getParentId(), docStatus);
	}

	@Override
	public List<ApprovalDetails> getApprovalList(long moduleId, Long docId, Long status) {
		List<ApprovalDetails> approvalDetails = new ArrayList<ApprovalDetails>();
				approvalDetails = this.approvalDetailsRepository.findByModuleIdAndRefDocIdAndStatus(moduleId, docId, status);
		for (ApprovalDetails approvalDetail : approvalDetails) {
			Optional<ApproverLevel> apAssing = this.approvalAssignRepository.findById(approvalDetail.getParentId());
			if (apAssing.isPresent()) {
				approvalDetail.setLabelName(apAssing.get().getLevelName());
			}
		}
		return approvalDetails;
	}

	public ApprovalSettings saveApproval(ApprovalSettings approval) {
		for (ApproverLevel ap : approval.getApproverAssignList()) {
			for (int i = 0; i < ap.getApprovers().size(); i++) {
				Approvers p = (Approvers) ap.getApprovers().get(i);
				if (p.getUserId() == null) {
					ap.getApprovers().remove(p);
					i--;// decrease the counter by one
				}
			}
		}
		UserEntity currentUser = this.appService.getCurrentUser();
		Date current = new Date(System.currentTimeMillis());
		if (approval.getId() == null || approval.getId() == 0) {
			approval.setCreatedDate(current);
		}
		approval.setModifiedDate(current);
		approval.setCreatedBy(currentUser.getId());
		approval.setModifiedBy(currentUser.getId());
		return this.approvalSettingsRepository.save(approval);
	}

	public List<UserEntity> getUserList() {
		return this.userService.getAllUsers();
	}

	@Override
	public int getApprovalSetting(long moduleId) {
		ApprovalSettings approvalSettings = this.approvalSettingsRepository.findByModuleIdAndStatus(moduleId, 2L);
		if (approvalSettings != null) {
			return approvalSettings.getBulkApproval().getVal();
		}
		return 0;
	}

	@Transactional
	@Override
	public void deleteApprovalAsign(long approvalAsignId) {
		this.approvalAssignRepository.deleteById(approvalAsignId);
	}

	@Override
	public List<Object> getApprovers(long moduleId) {
		Set<Long> userWithApprovalPermissionModuleWiseList = this.branchUserRoleRepository
				.findModuleWiseApprovalPermissionUserList(moduleId);
		List<Object> userResponseList = new ArrayList<Object>();
		for (Long userId : userWithApprovalPermissionModuleWiseList) {
			UserDTO user = this.userService.getUserById(userId);
			UserResponse userReturn = new UserResponse(user.getId(), user.getUsername());
			userResponseList.add(userReturn);
		}
		return userResponseList;
	}

	@Override
	public List<Long> getApproverSettingIds() {
		List<ApprovalSettings> apSt = this.approvalSettingsRepository.findAll();
		List<Long> apSettingIds = new ArrayList<Long>();
		for (ApprovalSettings approvalSettings : apSt) {
			apSettingIds.add(approvalSettings.getModuleId());
		}
		return apSettingIds;
	}

	@Override
	public String getApproverAssign(long moduleId, Long docId, Long userId) {
		long approverAssignId = this.notificationService.getNotication(moduleId, docId, userId);
		if (this.approvalAssignRepository.findById(approverAssignId).isPresent()) {
			return this.approvalAssignRepository.findById(approverAssignId).get().getLevelName();
		}
		return "";
	}

	@Override
	public List<PendingApproversDetails> getApprovers(long documentId, long moduleId) {
		List<Notifications> notifications = this.notificationService.getNotications(documentId, moduleId);
		List<PendingApproversDetails> penApprovers = new ArrayList<PendingApproversDetails>();
		if (notifications.size() != 0) {
			for (Notifications notify : notifications) {
				Optional<ApproverLevel> oLevel = this.approvalAssignRepository.findById(notify.getParentId());
				if (oLevel.isPresent()) {
					ApproverLevel level = oLevel.get();
					UserDTO user = this.userService.getUserById(notify.getUserId());
					PendingApproversDetails penAppr = new PendingApproversDetails();
					penAppr.setApproverName(user.getUsername());
					penAppr.setUserId(user.getId());
					penApprovers.add(penAppr);
				}
			}
			return penApprovers;
		}
		return penApprovers;
	}

	@Override
	public ApprovalSettings getApprovalSettingById(Integer approvalSettingId) {
		return this.approvalSettingsRepository.findById(approvalSettingId).get();
	}

	public Long getCount() {
		return this.approvalSettingsRepository.count();
	}

	@Override
	public Map<String, Object> getApprovarSettingsList(int page, int limit, String search, String sort, String order,
			List<ScreenFilterDTO> screenFilterDTO, boolean filterRemove, int moduleId) {
		UserEntity currentUser = this.appService.getCurrentUser();
		if (currentUser == null)
			throw new CustomException("Session has been timed out!");

		Map<String, Object> response = new HashMap<String, Object>();
		// List<ScreenFilter> screenFilter = new ArrayList<ScreenFilter>();

		if ((sort == null) || (sort.isEmpty())) {
			sort = "id";
		}
		if ((order == null) || (order.isEmpty())) {
			order = Sort.Direction.ASC.toString();
		}
		int totalCount = Integer.parseInt(Long.valueOf(this.getCount()).toString());
		Page<ApprovalSettingsResponseDTO> approvalSettingList = null;
		if (totalCount == 0) {
			throw new ResourceNotFoundException("No Record Found");
		}
		if ((search == null) || (search.isEmpty())) {
			approvalSettingList = this.approvalSettingsRepository
					.findCustom(this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
		} else {
			approvalSettingList = this.approvalSettingsRepository.findCustomSearch(search,
					this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
		}
		// customQuery.findCustom("ac");
		response.put("totalCount", this.approvalSettingsRepository.count());
		response.put("data", approvalSettingList);
		return response;
	}

	@Override
	public List<PendingApprovalDTO> getPendingApprovalLevelWise(long moduleId, Long docId) {
		List<PendingApprovalDTO> levelWise = new ArrayList<PendingApprovalDTO>();
		Map<String, List<String>> pendingApprovalLevelWise = new HashMap<String, List<String>>();
		List<Notifications> noteList = notificationService.getNotications(docId, moduleId);
		for (Notifications note : noteList) {
			List<String> userGroup = new ArrayList<String>();
			ApproverLevel appproverLevel = this.approvalAssignRepository.findById(note.getParentId()).get();
			UserDTO user = this.userService.getUserById(note.getUserId());
			if(pendingApprovalLevelWise.containsKey(appproverLevel.getLevelName())){
				userGroup = pendingApprovalLevelWise.get(appproverLevel.getLevelName());
				userGroup.add(user.getUsername());
				pendingApprovalLevelWise.put(appproverLevel.getLevelName(), userGroup);
			}else {
				userGroup.add(user.getUsername());
				pendingApprovalLevelWise.put(appproverLevel.getLevelName(), userGroup);
			}
		}
		
		pendingApprovalLevelWise.forEach((level,users) -> {
			PendingApprovalDTO dto = new PendingApprovalDTO();
			dto.setLevel(level);
			dto.setUserGroup(users);
			levelWise.add(dto);
		});
		return levelWise;
	}

	@Override
	public boolean getApprovalSettings(long moduleId) {
		ApprovalSettings approvalSettings = this.approvalSettingsRepository.findByModuleIdAndStatus(moduleId, 2L);
		if (approvalSettings != null) {
			return true;
		}
		return false;
	}
	
	
}
