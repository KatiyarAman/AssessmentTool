package com.sterp.multitenant.tenant.service;

import com.sterp.multitenant.tenant.modules.entity.Modules;

public interface ModuleServiceMain {

	
	public Modules getModuleById(long moduleId, boolean iscacheable);

	public Modules getModuleByTitle(String string, boolean b);
	
}
