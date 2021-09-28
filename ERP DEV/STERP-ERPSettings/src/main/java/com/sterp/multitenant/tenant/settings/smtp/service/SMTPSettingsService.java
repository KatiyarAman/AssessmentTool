package com.sterp.multitenant.tenant.settings.smtp.service;

import javax.mail.Session;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sterp.multitenant.tenant.settings.smtp.dto.SMTPSettingsDto;
import com.sterp.multitenant.tenant.settings.smtp.entity.SMTPSettings;

public interface SMTPSettingsService {

	public Long getCount();

	public Session smtpConfig();

	public Session smtpConfig(int id);

	public Session smtpTestMailConfigProvider(SMTPSettingsDto smtpSettingsDto);

	public void addSmtpConfig(SMTPSettings smtpSetting);

	public Page<SMTPSettings> smtpConfigList(Pageable pageable, String search);
}
