package com.cjhme.system.impl.base.mybatis.datapermissions.processor.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.cjhme.common.model.datapermissions.DataPermissionsBean;
import com.cjhme.system.impl.base.mybatis.datapermissions.processor.DataPermissionsProcessorInterface;

/**
 *
 * <p>
 * Title: QueryAllProcessorImpl.java
 * </p>
 * Description: 查询所有数据权限处理器
 * <p>
 * Modify histoty:
 *
 * @author cjh
 * @version 1.0
 */
@Component("system.queryAllProcessor")
public class QueryAllProcessorImpl  implements DataPermissionsProcessorInterface {
	
	/**
	 * 构建sql
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void buildSql(MetaObject metaObject, String sourceSql, DataPermissionsBean dataPermissionsBean,Map<String, Object> parameter) throws Exception {
		BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
		List<ParameterMapping> parameterMappings  = new ArrayList<>(boundSql.getParameterMappings());
		Map<String, Object> additionalParameters = (Map<String, Object>) metaObject.getValue("delegate.boundSql.additionalParameters");
		Object parameterObject = metaObject.getValue("delegate.boundSql.parameterObject");
		
		metaObject.setValue("delegate.boundSql.sql", sourceSql);
		metaObject.setValue("delegate.boundSql.parameterMappings", parameterMappings);
		metaObject.setValue("delegate.boundSql.additionalParameters", additionalParameters);
		metaObject.setValue("delegate.boundSql.parameterObject", parameterObject);
	}
}
