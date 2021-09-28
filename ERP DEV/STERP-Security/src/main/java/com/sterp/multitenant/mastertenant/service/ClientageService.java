package com.sterp.multitenant.mastertenant.service;

import com.sterp.multitenant.mastertenant.entity.Clientage;

public interface ClientageService {
	public Long getCount();

	public Clientage getClientageById(Integer clientId);

	public Clientage getClientageByEmail(String email);

	Clientage getClient(String verificationToken);

	public int getTenantIdBySubDomain(String subDomain);

	public Clientage getClientageByTenantId(Integer tenantClientId);

}
