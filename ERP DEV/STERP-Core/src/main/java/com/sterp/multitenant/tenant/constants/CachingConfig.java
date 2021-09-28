package com.sterp.multitenant.tenant.constants;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sf.ehcache.config.CacheConfiguration;

@Configuration
@EnableCaching
public class CachingConfig extends CachingConfigurerSupport {

	@Bean
	public net.sf.ehcache.CacheManager ehCacheManager() {
		CacheConfiguration twentySecondDirectoriesCache = new CacheConfiguration();
		twentySecondDirectoriesCache.setName("core-list-cache");
		twentySecondDirectoriesCache.setMemoryStoreEvictionPolicy(CacheConstants.CACHE_MEMORY_STORE_EVICTION_POLICY);
		twentySecondDirectoriesCache.setMaxEntriesLocalHeap(CacheConstants.CACHE_MAX_ENTRIES_LOCAL_HEAP);
		twentySecondDirectoriesCache.setTimeToLiveSeconds(CacheConstants.CACHE_EXPIRATION_TIME);
		// System.out.println(CacheConstants.getCahceExpirationTime());

		CacheConfiguration threeDaysStatusCache = new CacheConfiguration();
		threeDaysStatusCache.setName("status-cache");
		threeDaysStatusCache.setMemoryStoreEvictionPolicy(CacheConstants.CACHE_MEMORY_STORE_EVICTION_POLICY);
		threeDaysStatusCache.setMaxEntriesLocalHeap(CacheConstants.CACHE_MAX_ENTRIES_LOCAL_HEAP);
		threeDaysStatusCache.setTimeToLiveSeconds(864000 * 3);
		// System.out.println(CacheConstants.getCahceExpirationTime());

		CacheConfiguration threeDaysMenuCache = new CacheConfiguration();
		threeDaysMenuCache.setName("menu-cache");
		threeDaysMenuCache.setMemoryStoreEvictionPolicy(CacheConstants.CACHE_MEMORY_STORE_EVICTION_POLICY);
		threeDaysMenuCache.setMaxEntriesLocalHeap(CacheConstants.CACHE_MAX_ENTRIES_LOCAL_HEAP);
		threeDaysMenuCache.setTimeToLiveSeconds(864000 * 3);
		// System.out.println(CacheConstants.getCahceExpirationTime());

		CacheConfiguration oneDayTemplateCache = new CacheConfiguration();
		oneDayTemplateCache.setName("custom-field-cache");
		oneDayTemplateCache.setMemoryStoreEvictionPolicy(CacheConstants.CACHE_MEMORY_STORE_EVICTION_POLICY);
		oneDayTemplateCache.setMaxEntriesLocalHeap(CacheConstants.CACHE_MAX_ENTRIES_LOCAL_HEAP);
		oneDayTemplateCache.setTimeToLiveSeconds(864000);
		// System.out.println(CacheConstants.getCahceExpirationTime());
		
//		CacheConfiguration twentySecondsFolderCache = new CacheConfiguration();
//		twentySecondDirectoriesCache.setName("directory-cache");
//		twentySecondDirectoriesCache.setMemoryStoreEvictionPolicy(CacheConstants.CACHE_MEMORY_STORE_EVICTION_POLICY);
//		twentySecondDirectoriesCache.setMaxEntriesLocalHeap(CacheConstants.CACHE_MAX_ENTRIES_LOCAL_HEAP);
//		twentySecondDirectoriesCache.setTimeToLiveSeconds(CacheConstants.CACHE_EXPIRATION_TIME);
//		
//		CacheConfiguration oneDayFolderCache = new CacheConfiguration();
//		oneDayTemplateCache.setName("template-data-cache");
//		oneDayTemplateCache.setMemoryStoreEvictionPolicy(CacheConstants.CACHE_MEMORY_STORE_EVICTION_POLICY);
//		oneDayTemplateCache.setMaxEntriesLocalHeap(CacheConstants.CACHE_MAX_ENTRIES_LOCAL_HEAP);
//		oneDayTemplateCache.setTimeToLiveSeconds(864000);
		// System.out.println(CacheConstants.getCahceExpirationTime());

		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
		config.addCache(twentySecondDirectoriesCache);
//		config.addCache(twentySecondsFolderCache);
//		config.addCache(oneDayFolderCache);
		config.addCache(threeDaysStatusCache);
		config.addCache(threeDaysMenuCache);
		config.addCache(oneDayTemplateCache);
		return net.sf.ehcache.CacheManager.newInstance(config);
	}

	@Bean
	@Override
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheManager());
	}
}