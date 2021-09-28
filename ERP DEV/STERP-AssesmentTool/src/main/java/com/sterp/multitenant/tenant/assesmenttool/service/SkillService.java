package com.sterp.multitenant.tenant.assesmenttool.service;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sterp.multitenant.tenant.assesmenttool.entity.Skill;


public interface SkillService {
	Map<String, Object> addOrUpdate(Skill skill);
	Skill getSkillById(Long id);
	Map<String, Object> getSkillsList(Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException;
	Map<String, Object> getSelectionList();
	Map<String, Object> getSkillList(Map<String, Object> params);
}
