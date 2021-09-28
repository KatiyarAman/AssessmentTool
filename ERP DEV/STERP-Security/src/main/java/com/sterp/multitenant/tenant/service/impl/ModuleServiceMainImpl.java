package com.sterp.multitenant.tenant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterp.multitenant.tenant.modules.entity.Modules;
import com.sterp.multitenant.tenant.repository.ModuleMainRepository;
import com.sterp.multitenant.tenant.service.ModuleServiceMain;

@Service 
public class ModuleServiceMainImpl implements ModuleServiceMain {
	
	@Autowired
	ModuleMainRepository moduleMainRepo;
	
	@Override
	public Modules getModuleById(long moduleId, boolean iscacheable) {
		return this.moduleMainRepo.findById(moduleId).get();
	}
	
	@Override
	public Modules getModuleByTitle(String string, boolean b) {
		return this.moduleMainRepo.findByTitle(string);
	}

}
