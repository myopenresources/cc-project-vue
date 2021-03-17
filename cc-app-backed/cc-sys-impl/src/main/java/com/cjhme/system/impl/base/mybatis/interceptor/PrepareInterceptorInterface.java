package com.cjhme.system.impl.base.mybatis.interceptor;

import java.util.Properties;

import org.apache.ibatis.plugin.Invocation;



/**
*
* <p>
* Title: PrepareInterceptorInterface.java
* </p>
* Description: PrepareInterceptor接口
* <p>
* Modify histoty:
*
* @author cjh
* @version 1.0
*/
public interface PrepareInterceptorInterface {

	/**
	 * 处理
	 * @param invocation
	 * @param statementHandler
	 * @param metaStatementHandler
	 * @param rowBounds
	 * @param mappedStatement
	 * @throws Throwable
	 */
	public void intercept(Invocation invocation) throws Throwable;
	
	/**
	 * 设置属性
	 * @param properties
	 */
	public void setProperties(Properties properties);
}
