package com.sterp.multitenant.tenant.service.impl;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.sterp.multitenant.mastertenant.entity.OnDeviceRegistrationCompleteEvent;
import com.sterp.multitenant.tenant.entity.DeviceMetadata;
import com.sterp.multitenant.tenant.entity.DeviceVerificationOTP;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.repository.DeviceMetadataRepository;
import com.sterp.multitenant.tenant.repository.DeviceVerificationOTPRepository;
import com.sterp.multitenant.tenant.service.DeviceService;
import com.sterp.multitenant.tenant.service.SecurityAppService;
import com.sterp.multitenant.utils.sendSMS;

import ua_parser.Client;
import ua_parser.Parser;

@Service
public class DeviceServiceImpl implements DeviceService {

	private static final String UNKNOWN = "UNKNOWN";
	private String from = "navinder.singh@shivit.com";
	
	@Autowired
	sendSMS sendSMS;

	@Autowired
	private DeviceMetadataRepository deviceMetadataRepository;

//	@Autowiredz
//	private DatabaseReader databaseReader;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private MessageSource messages;
	@Autowired
	private SecurityAppService securityAppService;

	@Autowired
	ApplicationEventPublisher eventPublisher;

	@Autowired
	DeviceVerificationOTPRepository deviceVerificationOTPRepository;

	@Override
	public boolean verifyDevice(UserEntity user, HttpServletRequest request) throws IOException, GeoIp2Exception {

		String ip = extractIp(request);
		String deviceDetails = getDeviceDetails(request.getHeader("user-agent"));
		String location = getIpLocation("18.218.95.179");// getIpLocation(ip);

		DeviceMetadata existingDevice = findExistingDevice(user.getId(), deviceDetails, location);

		if (Objects.isNull(existingDevice)) {
			// unknownDeviceNotification(deviceDetails, location, ip, user.getEmail(),
			// request.getLocale());

			DeviceMetadata deviceMetadata = new DeviceMetadata();
			deviceMetadata.setUserId(user.getId());
			deviceMetadata.setLocation(location);
			deviceMetadata.setSystemIp("18.218.95.179");
			deviceMetadata.setEnabled(false);
			deviceMetadata.setDeviceDetails(deviceDetails);
			deviceMetadata.setLastLoggedIn(new Date(System.currentTimeMillis()));
			DeviceMetadata storedDeviceMetadata = deviceMetadataRepository.save(deviceMetadata);
			String appUrl = request.getContextPath();
			eventPublisher.publishEvent(new OnDeviceRegistrationCompleteEvent(storedDeviceMetadata, request.getLocale(),
					appUrl, user.getMobile().toString()));

			return true;
			// new sendSMS().sendSms();

		} else {

			if (!existingDevice.isEnabled()) {
				try {
					String otp =  this.deviceVerificationOTPRepository.findByDeviceMetadata(existingDevice).getOtp();
					sendSMS.sendSms("Your one time verification code for shivit miniERP is : "+otp, user.getMobile().toString());
					return true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				existingDevice.setLastLoggedIn(new Date(System.currentTimeMillis()));
				deviceMetadataRepository.save(existingDevice);
				return false;
			}
		}
		return false;
	}
	
	

	@Override
	public void createDeviceVerificationOTP(DeviceMetadata deviceMetadata, String otp) {
		DeviceVerificationOTP myOTP = new DeviceVerificationOTP(otp, deviceMetadata);
		myOTP.setExpiryDate(60 * 24);
		deviceVerificationOTPRepository.save(myOTP);
	}
	
	@Override
	public void verifyOTP(UserEntity user, String otp, HttpServletRequest request) throws IOException, GeoIp2Exception {
		String ip = extractIp(request);
		String deviceDetails = getDeviceDetails(request.getHeader("user-agent"));
		String location = getIpLocation("18.218.95.179");// getIpLocation(ip);
		
		DeviceMetadata existingDevice = findExistingDevice(user.getId(), deviceDetails, location);
		
		String storedOTP =  this.deviceVerificationOTPRepository.findByDeviceMetadata(existingDevice).getOtp();
		
		if(!storedOTP.equalsIgnoreCase(otp)) {
			throw new RuntimeException("Oops! You have entered invalid OTP.");
		}
		else {
			existingDevice.setEnabled(true);
			deviceMetadataRepository.save(existingDevice);
		}
		
	}

	private String extractIp(HttpServletRequest request) {
		String clientIp;
		String clientXForwardedForIp = request.getHeader("x-forwarded-for");
		if (Objects.nonNull(clientXForwardedForIp)) {
			clientIp = parseXForwardedHeader(clientXForwardedForIp);
		} else {
			clientIp = request.getRemoteAddr();
		}

		return clientIp;
	}

	private String parseXForwardedHeader(String header) {
		return header.split(" *, *")[0];
	}

	private String getDeviceDetails(String userAgent) throws IOException {
		String deviceDetails = UNKNOWN;
		Parser parser = new Parser();
		Client client = parser.parse(userAgent);
		if (Objects.nonNull(client)) {
			deviceDetails = client.userAgent.family + " " + client.userAgent.major + "." + client.userAgent.minor
					+ " - " + client.os.family + " " + client.os.major + "." + client.os.minor;
		}

		return deviceDetails;
	}

	private String getIpLocation(String ip) throws IOException, GeoIp2Exception {
		return this.securityAppService.getLoginLocation(ip);
	}

	private DeviceMetadata findExistingDevice(Long userId, String deviceDetails, String location) {

		List<DeviceMetadata> knownDevices = deviceMetadataRepository.findByUserId(userId);

		for (DeviceMetadata existingDevice : knownDevices) {
			if (existingDevice.getDeviceDetails().equals(deviceDetails)
					&& existingDevice.getLocation().equals(location)) {
				return existingDevice;
			}
		}

		return null;
	}

	private void unknownDeviceNotification(String deviceDetails, String location, String ip, String email,
			Locale locale) {
		final String subject = "New Login Notification";
		final SimpleMailMessage notification = new SimpleMailMessage();
		notification.setTo(email);
		notification.setSubject(subject);

		String text = getMessage("message.login.notification.deviceDetails", locale) + " " + deviceDetails + "\n"
				+ getMessage("message.login.notification.location", locale) + " " + location + "\n"
				+ getMessage("message.login.notification.ip", locale) + " " + ip;

		notification.setText(text);
		notification.setFrom(from);

		mailSender.send(notification);
	}

	private String getMessage(String code, Locale locale) {
		try {
			return messages.getMessage(code, null, locale);
		} catch (NoSuchMessageException ex) {
			return messages.getMessage(code, null, Locale.ENGLISH);
		}
	}



	@Override
	public void resendOTP(UserEntity user, HttpServletRequest request) throws IOException, GeoIp2Exception {
		String ip = extractIp(request);
		String deviceDetails = getDeviceDetails(request.getHeader("user-agent"));
		String location = getIpLocation("18.218.95.179");// getIpLocation(ip);
		DeviceMetadata existingDevice = findExistingDevice(user.getId(), deviceDetails, location);
		String otp =  this.deviceVerificationOTPRepository.findByDeviceMetadata(existingDevice).getOtp();
		sendSMS.sendSms("Your one time verification code for shivit miniERP is : "+otp, user.getMobile().toString());
	}

	

}
