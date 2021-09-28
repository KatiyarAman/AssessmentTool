package com.sterp.multitenant.tenant.zone.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.sterp.multitenant.tenant.zone.dto.ZoneRequestDTO;
import com.sterp.multitenant.tenant.zone.entity.Zone;

public interface ZoneService {

	public Zone getZoneById(Long zoneId, boolean iscacheable);

	public Map<String, Object> addOrUpdateZone(HttpServletRequest request ,ZoneRequestDTO zoneDTO, MultipartFile[] files);

	public Map<String, Object> getSelectionList(boolean iscacheable);

	public Map<String, Object> getZoneList(Map<String, Object> params);
}
