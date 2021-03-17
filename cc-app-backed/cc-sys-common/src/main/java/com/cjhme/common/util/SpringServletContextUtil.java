package com.cjhme.common.util;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;


/**
 * 
 * <p>
 * Title: SpringContextUtil.java
 * </p>
 * Description: 获得Spring、Servlet上下文
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public class SpringServletContextUtil implements ApplicationContextAware, ServletContextAware {
	
	Log LOGGER = LogFactory.getLog(SpringServletContextUtil.class);

	/**
	 * applicationContext对象
	 */
	private static ApplicationContext applicationContext;

	/**
	 * servletContext对象
	 */
	public static ServletContext servletContext;

	/**
	 * 实现ApplicationContextAware接口的context注入
	 */
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		SpringServletContextUtil.applicationContext = ac;
		LOGGER.info("初始化applicationContextd成功！");
	}

	/**
	 * 获得applicationContext对象
	 * 
	 * @return
	 */
	public static ApplicationContext getApplicationContext() throws Exception {
		return SpringServletContextUtil.applicationContext;
	}

	/**
	 * 根据bean名称获得bean对象
	 * 
	 * @param name
	 * @return
	 * @throws BeansException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBeanByName(String name) throws BeansException {
		return (T) applicationContext.getBean(name);
	}

	/**
	 * 根据bean类型获得bean对象
	 * 
	 * @param <T>
	 * @param requiredType
	 * @return
	 * @throws BeansException
	 */
	public static <T> T getBeanByType(Class<T> requiredType) throws BeansException {
		return (T) applicationContext.getBean(requiredType);
	}

	/**
	 * 实现ServletContextAware接口的context注入
	 */
	public void setServletContext(ServletContext sc) {
		SpringServletContextUtil.servletContext = sc;
		LOGGER.info("初始化servletContextd成功！");
	}


	/**
	 * 获得servletContext对象
	 * 
	 * @return
	 */
	public static ServletContext getServletContext() {
		return SpringServletContextUtil.servletContext;
	}

}
