package com.sterp.multitenant.tenant.settings.smtp.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.sterp.multitenant.tenant.settings.smtp.dto.MailBody;
import com.sterp.multitenant.tenant.settings.smtp.dto.SMTPSettingsDto;

public interface EmailService {
	public Map<String, Object> composeMail(MailBody mailBody, MultipartFile[] attachments);

	public Map<String, Object> sendConfigurationTestMail(SMTPSettingsDto smtpSettingsDto);

	public Map<String, Object> getSMTPConfigList(int page, int limit, String search, String sort, String order,
			boolean filterRemove, int screenId);
}
