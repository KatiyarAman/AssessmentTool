package com.sterp.multitenant.tenant.exceptionhandler.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.sterp.multitenant.tenant.exceptionhandler.ExceptionResponse;
import com.sterp.multitenant.tenant.exceptionhandler.repository.ExceptionResponseRepository;
import com.sterp.multitenant.tenant.exceptionhandler.service.ExceptionService;

@Service
public class ExceptionServiceImpl implements ExceptionService {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionServiceImpl.class);
	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
	ExceptionResponseRepository exceptionResponseRepository;

	public static int noOfQuickServiceThreads = 20;

	private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(noOfQuickServiceThreads);

	@Override
	public void storeExceptionDeveloperZone(ExceptionResponse exceptionResponse, String tenantInfo) {
		this.exceptionResponseRepository.save(exceptionResponse);
		sendASynchronousMail(exceptionResponse, tenantInfo);
	}

	public void sendASynchronousMail(ExceptionResponse exceptionResponse, String tenantInfo)
			throws MailException, RuntimeException {
		logger.debug("inside sendASynchronousMail method");
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			String html = "<h2>Hey, developer i need help! Please look into the following error being generated in Client Application: </h2>"
					+ "<h5>Tenant Id: " + tenantInfo + "</h5>" + "<h5>API Path: " + exceptionResponse.getPath()
					+ "</h5>" + "<h5>Error Code: " + exceptionResponse.getStatus() + "</h5>" + "<h5>Message: "
					+ exceptionResponse.getMessage() + "</h5>" + "<h5>Error Cause: " + exceptionResponse.getError()
					+ "</h5>";
			helper.setTo("navinder.singh@shivit.com");
			helper.setSubject("Tenant_" + tenantInfo + "_Error_" + exceptionResponse.getStatus());
			helper.setText(html, true);
			quickService.submit(new Runnable() {
				@Override
				public void run() {
					try {
						javaMailSender.send(message);
					} catch (Exception e) {
						logger.error("Exception occur while send a mail : ", e);
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
