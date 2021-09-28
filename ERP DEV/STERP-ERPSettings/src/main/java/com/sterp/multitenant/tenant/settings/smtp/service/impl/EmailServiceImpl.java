package com.sterp.multitenant.tenant.settings.smtp.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.exceptionhandler.CustomException;
import com.sterp.multitenant.tenant.settings.smtp.dto.MailBody;
import com.sterp.multitenant.tenant.settings.smtp.dto.SMTPSettingsDto;
import com.sterp.multitenant.tenant.settings.smtp.entity.SMTPSettings;
import com.sterp.multitenant.tenant.settings.smtp.service.EmailService;
import com.sterp.multitenant.tenant.settings.smtp.service.SMTPSettingsService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	SMTPSettingsService SMTPSettingsService;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	AppService appService;

	@Override
	public Map<String, Object> composeMail(MailBody mailBody, MultipartFile[] attachments) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			MimeMessage message = new MimeMessage(SMTPSettingsService.smtpConfig());
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			// helper.addAttachment("logo.png", new
			// ClassPathResource("memorynotfound-logo.png"));
//			Context context = new Context();
//			context.setVariables(mail.getModel());
			String html = mailBody.getBody();// templateEngine.process("mailbody/invoiceBody", context);
			String[] tos = { mailBody.getRecipient() };// mail.getTos().stream().toArray(String[]::new);
			helper.setTo(tos);
			helper.setText(html, true);
			helper.setSubject(mailBody.getSubject());
			helper.setFrom("navinder@shivit.in");
			for (MultipartFile attachment : attachments) {
				helper.addAttachment(attachment.getOriginalFilename(), new ByteArrayResource(attachment.getBytes()));
			}
			Transport.send(message);
			response.put("message", "Mail Send Successfully!");
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
		return response;
	}

	@Override
	public Map<String, Object> sendConfigurationTestMail(SMTPSettingsDto smtpSettingsDto) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (smtpSettingsDto.isTest()) {
			Session session = this.SMTPSettingsService.smtpTestMailConfigProvider(smtpSettingsDto);
			MimeMessage message = new MimeMessage(session);
			try {
				message.setFrom(new InternetAddress(smtpSettingsDto.getUsername()));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(smtpSettingsDto.getUsername()));
				message.setSubject("Testing Email SMTP Configuration.");
				message.setText("Dear Mail Crawler," + "\n\n Please do not spam my email!");
				Transport.send(message);
				// System.out.println(this.sendSms());
				response.put("message", "Test mail sent successfully. You are good to go!");
			} catch (Exception e) {
				e.printStackTrace();
				throw new CustomException(e.getMessage());
			}
		} else {
			this.SMTPSettingsService.addSmtpConfig(this.modelMapper.map(smtpSettingsDto, SMTPSettings.class));
			response.put("message", "Mail configuration saved successfully.");
		}
		return response;
	}

	@Override
	public Map<String, Object> getSMTPConfigList(int page, int limit, String search, String sort, String order,
			boolean filterRemove, int screenId) {
		UserEntity currentUser = this.appService.getCurrentUser();
		if (currentUser == null)
			throw new CustomException("Session has been timed out!");

		Map<String, Object> response = new HashMap<String, Object>();
		if ((sort == null) || (sort.isEmpty())) {
			sort = "id";
		}
		if ((order == null) || (order.isEmpty())) {
			order = Sort.Direction.ASC.toString();
		}
		int totalCount = this.SMTPSettingsService.getCount().intValue();
		Page<SMTPSettings> smtpSettings = null;

		if (totalCount == 0) {
			throw new CustomException("No Record Found");
		}

		smtpSettings = this.SMTPSettingsService.smtpConfigList(
				this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort), search);

		if (!smtpSettings.isEmpty()) {
			response.put("data", smtpSettings);
			response.put("filter", Collections.emptyList());
			response.put("total", this.SMTPSettingsService.getCount());
			return response;
		} else {
			response.put("error", "No Record Found!");
			response.put("filter", Collections.emptyList());
			return response;
		}
	}

//	public String sendSms() {
//		try {
//			// Construct data
//			String apiKey = "apikey=" + "0/S4JqkPwMg-xsSWyVCzTsW02DdyNpbiRNnaKi93ER";
//			String message = "&message=" + "Hello Sanjay";
//			String sender = "&sender=" + "TXTLCL";
//			String numbers = "&numbers=" + "7827230454";
//
//			// Send data
//			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
//			String data = apiKey + numbers + message + sender;
//			conn.setDoOutput(true);
//			conn.setRequestMethod("POST");
//			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
//			conn.getOutputStream().write(data.getBytes("UTF-8"));
//			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			final StringBuffer stringBuffer = new StringBuffer();
//			String line;
//			while ((line = rd.readLine()) != null) {
//				stringBuffer.append(line);
//			}
//			rd.close();
//
//			return stringBuffer.toString();
//		} catch (Exception e) {
//			System.out.println("Error SMS " + e);
//			return "Error " + e;
//		}
//	}

}
