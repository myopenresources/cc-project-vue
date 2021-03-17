package com.cjhme.system.impl.base.config;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cjhme.system.impl.base.loader.DataDicLoader;


/**
 *
 * <p>
 * Title: DataDicLoaderConfig.java
 * </p>
 * Description: 数据字典加载器
 * <p>
 * Modify histoty:
 *
 * @author cjh
 * @version 1.0
 */
@Configuration
public class DataDicLoaderConfig {
	Log LOGGER = LogFactory.getLog(DataDicLoaderConfig.class);
	
	
	
    /**
     * 数据字典加载
     * @return
     * @throws Exception
     */
    @Bean(name="system.dataDicLoader")
    public DataDicLoader dataDicLoader() throws Exception {
    	LOGGER.info("初始化数据字典加载器！");
        DataDicLoader dataDicLoader=new DataDicLoader();
        dataDicLoader.initLoader();
        return dataDicLoader;
    }
}
