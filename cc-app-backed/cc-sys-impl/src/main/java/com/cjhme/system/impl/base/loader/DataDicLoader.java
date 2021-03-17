package com.cjhme.system.impl.base.loader;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;

import com.cjhme.common.interfaced.ICacheManagerNames;
import com.cjhme.common.util.SpringServletContextUtil;
import com.cjhme.system.impl.service.datadic.DataDicService;

/**
 * 
 * <p>
 * Title: DataDicLoader.java
 * </p>
 * Description: 数据字典加载器
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public class DataDicLoader {

	Log LOGGER = LogFactory.getLog(DataDicLoader.class);
	
	private static String SYS_DATA_DIC_CACHE = "sysDataDicCache";
	private static String SYS_DATA_DIC_MAP = "SYS_DATA_DIC_MAP";


	/**
	 * 初始化加载器
	 * 
	 * @throws Exception
	 */
	public void initLoader() throws Exception {
		LOGGER.info("初始化数据字典开始！");
		DataDicService dataDicService = SpringServletContextUtil.getBeanByName("system.dataDicService");
		Map<String, Object> dataDicMap =  dataDicService.queryInitDataDicType();
		this.setDataDicMap(dataDicMap);
		LOGGER.info("初始化数据字典结束,加载数据字典数量：" + dataDicMap.size());
	}
	
	/**
	 * 获取缓存
	 * @return
	 */
	public Cache getSysDataDicCache() {
		EhCacheCacheManager cacheManager = SpringServletContextUtil.getBeanByName(ICacheManagerNames.EHCACHE_CACHE_MANAGER);
		return cacheManager.getCache(SYS_DATA_DIC_CACHE);
	}

	/**
	 * 获取数据字典信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getDataDicMap() {
		return (Map<String, Object>) this.getSysDataDicCache().get(SYS_DATA_DIC_MAP).get();
	}
	
	/**
	 * 设置数据字典信息
	 * 
	 * @return
	 */
	public void setDataDicMap(Map<String, Object> dataDicMap){
		Cache cache = this.getSysDataDicCache();
		cache.clear();
		cache.put(SYS_DATA_DIC_MAP,dataDicMap);
	}

}
