package com.cjhme.system.impl.base.mybatis.datapermissions.interceptor;

import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.reflection.MetaObject;

import com.cjhme.common.model.datapermissions.DataPermissionsBean;
import com.cjhme.common.util.SpringServletContextUtil;
import com.cjhme.system.impl.base.mybatis.datapermissions.helper.SqlHelper;
import com.cjhme.system.impl.base.mybatis.datapermissions.interactive.DataPermissionsInteractive;
import com.cjhme.system.impl.base.mybatis.datapermissions.processor.DataPermissionsProcessorInterface;
import com.cjhme.system.impl.base.mybatis.interceptor.PrepareInterceptorInterface;
import com.cjhme.system.impl.base.mybatis.util.PluginUtil;

/**
 * 
 * <p>
 * Title: DataPermissionsInterceptorImpl.java
 * </p>
 * Description: mybatis数据权限拦截器实现
 * <p>
 * Modify histoty:
 * 
 * @author
 * @version 1.0
 */
public class DataPermissionsPrepareInterceptorImpl implements PrepareInterceptorInterface {

	/**
	 * 拦截器处理
	 */
	public void intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = PluginUtil.realTarget(invocation.getTarget());
		MetaObject metaObject = MetaObject.forObject(statementHandler);
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		Object sqlCommandType =  metaObject.getValue("delegate.mappedStatement.sqlCommandType");
        String sql =  (String) metaObject.getValue("delegate.boundSql.sql");
		// 判断是否需要使用数据权限
		Map<String, Object> dataPermissionsInteractive = DataPermissionsInteractive.getDataPermissionsParams();
		if (SqlCommandType.SELECT.equals(sqlCommandType) && null != dataPermissionsInteractive
				&& (Boolean.valueOf(String.valueOf(dataPermissionsInteractive.get("primaryProcessor")))
						|| Boolean.valueOf(String.valueOf(dataPermissionsInteractive.get("secondaryProcessor"))))) {
			Connection connection = (Connection) invocation.getArgs()[0];

			// 查询配置
			Map<String, Object> params = DataPermissionsInteractive.getDataPermissionsParams();
			params.put("sqlId", mappedStatement.getId());
			DataPermissionsBean dataPermissionsBean = SqlHelper.getDataPermissions(connection, params);

			
			if (null != dataPermissionsBean) {
				String processorName = Boolean
						.valueOf(String.valueOf(dataPermissionsInteractive.get("primaryProcessor")))
								? dataPermissionsBean.getPrimaryProcessor()
								: dataPermissionsBean.getSecondaryProcessor();

				DataPermissionsProcessorInterface processor = SpringServletContextUtil.getBeanByName(processorName);
				processor.buildSql(metaObject,sql, dataPermissionsBean, params);
			}
		}
	}

	/**
	 * 设置属性
	 */
	public void setProperties(Properties properties) {

	}
}
