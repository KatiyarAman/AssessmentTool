package com.sterp.multitenant.mastertenant.service;

import com.sterp.multitenant.mastertenant.entity.Clientage;

/**
 * 
 * @author Navinder Singh
 *
 */

import com.sterp.multitenant.mastertenant.entity.MasterTenant;

public interface MasterTenantService {
	MasterTenant findByClientId(Integer clientId);

	MasterTenant getById(int id);

	public MasterTenant loadMasterTenantByClientage(Clientage clientage);
}
