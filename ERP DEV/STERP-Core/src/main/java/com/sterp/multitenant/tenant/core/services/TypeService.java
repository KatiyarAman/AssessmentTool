package com.sterp.multitenant.tenant.core.services;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sterp.multitenant.tenant.dto.TypeRequestDTO;

public interface TypeService {

	Map<String, Object> saveOrUpdateType(TypeRequestDTO typeDTO);

	TypeRequestDTO getTypeById(Long typeId, boolean iscacheable);

	Map<String, Object> getSelectionList(boolean iscacheable, Long moduleId);

	Map<String, Object> getTypeList(Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException;

}
