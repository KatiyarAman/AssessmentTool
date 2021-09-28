package com.sterp.multitenant.tenant.constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class CacheProperties {
	@Autowired
	private Environment environment;

	public long getCahceExpirationTime() {
		return Long.parseLong(environment.getProperty("tokenSecret"));
	}

	public String getCahceMemoryStoreConvictionPolicy() {
		return environment.getProperty("CACHEMEMORYSTOREEVICTIONPOLICY");
	}

	public long getCacheMaxEntriesLocalHeap() {
		return Long.parseLong(environment.getProperty("CACHEMAXENTRIESLOCALHEAP"));
	}
}
