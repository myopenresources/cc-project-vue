package com.cjhme.system.impl.base.config;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;

import com.cjhme.common.interfaced.ICacheManagerNames;

/**
*
* <p>
* Title: CacheManagerConfig.java
* </p>
* Description: 缓存的配置，可支持多个缓存
* <p>
* Modify histoty:
*
* @author cjh
* @version 1.0
*/
@Configuration
@EnableCaching
@EnableConfigurationProperties(CacheProperties.class)
public class CacheManagerConfig {

	private final CacheProperties cacheProperties;

	public CacheManagerConfig(CacheProperties cacheProperties) {
		this.cacheProperties = cacheProperties;
	}


	@Bean(name = ICacheManagerNames.EHCACHE_CACHE_MANAGER)
	@Primary
	public EhCacheCacheManager ehCacheManager() {
		Resource resource = this.cacheProperties.getEhcache().getConfig();
		resource = this.cacheProperties.resolveConfigLocation(resource);
		EhCacheCacheManager ehCacheManager = new EhCacheCacheManager(EhCacheManagerUtils.buildCacheManager(resource));
		ehCacheManager.afterPropertiesSet();
		return ehCacheManager;
	}

}
