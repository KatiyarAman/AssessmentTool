package com.sterp.multitenant.mastertenant.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sterp.multitenant.tenant.service.DeviceService;
import com.sterp.multitenant.utils.CommonUtils;
import com.sterp.multitenant.utils.sendSMS;


@Component
public class DeviceRegistrationListener implements ApplicationListener<OnDeviceRegistrationCompleteEvent>{

	@Autowired
	CommonUtils commonUtils; 
	
	@Autowired
	sendSMS sendSMS;
	
	@Autowired
	DeviceService deviceService;
	
	@Override
	public void onApplicationEvent(OnDeviceRegistrationCompleteEvent event) {
		this.sendOTPForDeviceVerification(event);
	}
	
	private void sendOTPForDeviceVerification(OnDeviceRegistrationCompleteEvent event) {
		String otp = commonUtils.generateRandomOTPString(4);
		System.out.println("OTP is: "+otp);
		deviceService.createDeviceVerificationOTP(event.getDeviceMetaData(), otp);
		try {
			sendSMS.sendSms("Your one time verification code for shivit miniERP is : "+otp, event.getNumber());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
