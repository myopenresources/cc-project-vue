package com.cjhme.system.impl.base.mybatis.interceptor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.core.env.Environment;


/**
 *
 * <p>
 * Title: PrepareInterceptor.java
 * </p>
 * Description: mybatis prepare拦截器
 * <p>
 * Modify histoty:
 *
 * @author cjh
 * @version 1.0
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PrepareInterceptor implements Interceptor {

	Log LOGGER = LogFactory.getLog(PrepareInterceptor.class);

	private Environment env;

	List<PrepareInterceptorInterface> interceptorImpls = new ArrayList<>();

	public PrepareInterceptor(Environment env) {
		this.env = env;
		initPrepareInterceptorImpl();
	}

	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		for (PrepareInterceptorInterface obj : interceptorImpls) {
			obj.intercept(invocation);
		}

		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof StatementHandler) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	@Override
	public void setProperties(Properties properties) {
		for (PrepareInterceptorInterface obj : interceptorImpls) {
			obj.setProperties(properties);
		}

	}

	/**
	 * 初始化拦截器自定义实现
	 * 
	 * @return
	 */
	private void initPrepareInterceptorImpl() {
		LOGGER.info("初始化Prepare拦截器自定义实现！");
		try {
			String prepareInterceptors = this.env.getProperty("system.mybatis.prepareInterceptors");
			if (null != prepareInterceptors && prepareInterceptors.length() > 0) {
				String[] prepareInterceptorList = prepareInterceptors.split(",");
				for (String interceptor : prepareInterceptorList) {
					Class<?> clazz = Class.forName(interceptor);
					interceptorImpls.add((PrepareInterceptorInterface) clazz.newInstance());
				}
			}
		} catch (Exception e) {
			LOGGER.error("初始化Prepare拦截器自定义实现失败！");
			e.printStackTrace();
		}
	}

}
