package com.sterp.multitenant.tenant.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.sterp.multitenant.tenant.entity.UserEntity;

public interface SecurityAppService {
	public UserEntity getCurrentUser();

	public HttpHeaders getXCountHeader(long count);

	public Pageable createPageRequestSortedSingleColumn(int firstResult, int maxResults, String sortType,
			String sortField);

	public Pageable createPageRequestSortedMultiColumn(int firstResult, int maxResults, String sortType,
			String[] sortFields);

	public Pageable createPageRequestUnsorted(int firstResult, int maxResults);

	public String getClientIPAddress(HttpServletRequest request);

	public String getClientMACAddress(String ip) throws Exception;

	public String getSystemName();

	public void printClientInfo(HttpServletRequest request);

	public String getLoginLocation(String ip) throws IOException, GeoIp2Exception;

	public int getTenantId(HttpServletRequest httpServletRequest);
}
