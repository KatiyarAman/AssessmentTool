package com.sterp.multitenant.tenant.constants;

import org.springframework.stereotype.Component;

@Component
public interface CacheNames {
	
	public static final String GRIDNAME = "sterp-grid";
	public final static String IDGEN_CACHE_CONFIG = "IDGEN_CACHE_CONFIG";
	public static final String VENDORCACHE = "VENDOR_CACHE_CONFIG";
	public static final String ADDRESSDETAILSCACHE = "ADDRESSDETAILS_CACHE_CONFIG";
}
