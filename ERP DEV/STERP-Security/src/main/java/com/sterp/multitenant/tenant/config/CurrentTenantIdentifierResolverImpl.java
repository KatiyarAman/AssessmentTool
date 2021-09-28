package com.sterp.multitenant.tenant.config;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

import com.sterp.multitenant.mastertenant.config.DBContextHolder;

/**
 * @author Navinder Singh
 */
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

	private static final String DEFAULT_TENANT_ID = "client_tenant_1";

	@Override
	public String resolveCurrentTenantIdentifier() {
		String tenant = DBContextHolder.getCurrentDb();
		return StringUtils.isNotBlank(tenant) ? tenant : DEFAULT_TENANT_ID;
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}

}
