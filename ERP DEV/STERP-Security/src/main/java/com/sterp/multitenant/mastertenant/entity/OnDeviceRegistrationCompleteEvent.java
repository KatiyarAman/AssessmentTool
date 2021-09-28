package com.sterp.multitenant.mastertenant.entity;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.sterp.multitenant.tenant.entity.DeviceMetadata;

public class OnDeviceRegistrationCompleteEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1567936329596338153L;
	private String appUrl;
	private Locale locale;
	private DeviceMetadata deviceMetaData;
	private String number;

	public OnDeviceRegistrationCompleteEvent(DeviceMetadata deviceMetaData, Locale locale, String appUrl,
			String number) {
		super(deviceMetaData);
		this.deviceMetaData = deviceMetaData;
		this.locale = locale;
		this.appUrl = appUrl;
		this.number = number;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public DeviceMetadata getDeviceMetaData() {
		return deviceMetaData;
	}

	public void setDeviceMetaData(DeviceMetadata deviceMetaData) {
		this.deviceMetaData = deviceMetaData;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}