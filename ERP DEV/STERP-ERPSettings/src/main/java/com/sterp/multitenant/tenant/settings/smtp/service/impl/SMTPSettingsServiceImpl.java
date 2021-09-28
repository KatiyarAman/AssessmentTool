package com.sterp.multitenant.tenant.settings.smtp.service.impl;

import java.util.List;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sterp.multitenant.tenant.exceptionhandler.CustomException;
import com.sterp.multitenant.tenant.settings.smtp.dto.SMTPSettingsDto;
import com.sterp.multitenant.tenant.settings.smtp.entity.SMTPSettings;
import com.sterp.multitenant.tenant.settings.smtp.repository.SMTPSettingsRepository;
import com.sterp.multitenant.tenant.settings.smtp.service.SMTPSettingsService;

@Service
public class SMTPSettingsServiceImpl implements SMTPSettingsService {

	@Autowired
	SMTPSettingsRepository SMTPSettingsRepository;

	@Override
	public Long getCount() {
		return this.SMTPSettingsRepository.count();
	}

	@Override
	public Session smtpConfig() {
		List<SMTPSettings> smtpSettings = SMTPSettingsRepository.findAll();
		if (smtpSettings.size() != 0) {
			SMTPSettings smtpSetting = smtpSettings.get(0);
			final String username = smtpSetting.getUsername();
			final String password = smtpSetting.getPassword();
			Properties prop = new Properties();
			prop.put("mail.smtp.host", smtpSetting.getHost());
			prop.put("mail.smtp.port", smtpSetting.getPort());// "587"
			prop.put("mail.smtp.auth", smtpSetting.isAuth());
			prop.put("mail.smtp.connectiontimeout", 5000);
			prop.put("mail.smtp.timeout", 5000);
			prop.put("mail.smtp.writetimeout", 5000);
			prop.put("mail.smtp.socketFactory.port", smtpSetting.getSocketFactoryPort());// "465"
			prop.put("mail.smtp.socketFactory.class", smtpSetting.getSocketFactoryClass());// "javax.net.ssl.SSLSocketFactory"
			prop.put("mail.smtp.ssl.enable", smtpSetting.isSsl());
			Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			return session;
		} else {
			throw new CustomException("SMTP Configuration not found.");
		}
	}

	@Override
	public Session smtpConfig(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Session smtpTestMailConfigProvider(SMTPSettingsDto smtpSettingsDto) {
//		#spring.mail.properties.mail.smtp.auth=true
//				#spring.mail.properties.mail.smtp.connectiontimeout=5000
//				#spring.mail.properties.mail.smtp.timeout=5000
//				#spring.mail.properties.mail.smtp.writetimeout=5000
		try {
			final String username = smtpSettingsDto.getUsername();
			final String password = smtpSettingsDto.getPassword();
			Properties prop = new Properties();
			prop.put("mail.smtp.host", smtpSettingsDto.getHost());
			prop.put("mail.smtp.port", smtpSettingsDto.getPort());// "587"
			prop.put("mail.smtp.auth", smtpSettingsDto.isAuth());
			prop.put("mail.smtp.connectiontimeout", 5000);
			prop.put("mail.smtp.timeout", 5000);
			prop.put("mail.smtp.writetimeout", 5000);
			prop.put("mail.smtp.socketFactory.port", smtpSettingsDto.getSocketFactoryPort());// "465"
			prop.put("mail.smtp.socketFactory.class", smtpSettingsDto.getSocketFactoryClass());// "javax.net.ssl.SSLSocketFactory"
			prop.put("mail.smtp.ssl.enable", smtpSettingsDto.isSsl());
			Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			return session;
		} catch (Exception e) {
			throw new CustomException("SMTP Configuration not found.");
		}
	}

	@Override
	public void addSmtpConfig(SMTPSettings smtpSetting) {
		this.SMTPSettingsRepository.save(smtpSetting);
	}

	@Override
	public Page<SMTPSettings> smtpConfigList(Pageable pageable, String search) {
		if (!search.isEmpty()) {
			return this.SMTPSettingsRepository.findCustom(pageable, search);
		} else {
			return this.SMTPSettingsRepository.findAll(pageable);
		}
	}

}
