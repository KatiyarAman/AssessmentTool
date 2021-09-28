package com.sterp.multitenant.tenant.constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CacheConstants {
	@Autowired
	private static CacheProperties appProperties;
	public static final long CACHE_EXPIRATION_TIME = 60; // 60 Secs to Refresh the Cache
	public static final String CACHE_MEMORY_STORE_EVICTION_POLICY = "LRU";
	public static final long CACHE_MAX_ENTRIES_LOCAL_HEAP = 1000;

	public static long getCahceExpirationTime() {
		return appProperties.getCahceExpirationTime();
	}

}
