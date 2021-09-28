package com.sterp.multitenant.tenant.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.sterp.multitenant.tenant.entity.DeviceMetadata;
import com.sterp.multitenant.tenant.entity.UserEntity;

public interface DeviceService {
	public boolean verifyDevice(UserEntity user, HttpServletRequest request) throws IOException, GeoIp2Exception;
	
	
	public void createDeviceVerificationOTP(DeviceMetadata deviceMetadata, String otp);
	
	public void verifyOTP(UserEntity user, String otp, HttpServletRequest request) throws IOException, GeoIp2Exception ;
	
	public void resendOTP(UserEntity user, HttpServletRequest request) throws IOException, GeoIp2Exception;
}
