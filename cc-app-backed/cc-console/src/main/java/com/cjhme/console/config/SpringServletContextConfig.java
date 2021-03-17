package com.cjhme.console.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.cjhme.common.util.SpringServletContextUtil;

/**
*
* <p>
* Title: SpringServletContextConfig.java
* </p>
* Description: springServletContextUtil工具配置
* <p>
* Modify histoty:
*
* @author cjh
* @version 1.0
*/
@Configuration
public class SpringServletContextConfig {

	Log LOGGER = LogFactory.getLog(SpringServletContextConfig.class);
	
	/**
     * springServletContextUtil工具
     * @return
     */
    @Bean(name="springServletContextUtil")
    public SpringServletContextUtil springServletContextUtil() {
    	LOGGER.info("初始化springServletContextUtil工具！");
        SpringServletContextUtil springServletContextUtil =new SpringServletContextUtil();
        return springServletContextUtil;
    }
	
    
}
