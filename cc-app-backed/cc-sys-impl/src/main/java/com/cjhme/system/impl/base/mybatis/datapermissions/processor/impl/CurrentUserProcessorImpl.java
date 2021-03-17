package com.cjhme.system.impl.base.mybatis.datapermissions.processor.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.cjhme.common.model.datapermissions.DataPermissionsBean;
import com.cjhme.common.model.user.UserBean;
import com.cjhme.system.impl.base.mybatis.datapermissions.processor.DataPermissionsProcessorInterface;
import com.cjhme.system.impl.base.mybatis.util.ParameterMappingUtil;
import com.cjhme.system.impl.base.mybatis.util.ParameterObjectUtil;

/**
 *
 * <p>
 * Title: CurrentUserProcessorImpl.java
 * </p>
 * Description: 当前用户数据权限处理器
 * <p>
 * Modify histoty:
 *
 * @author cjh
 * @version 1.0
 */
@Component("system.currentUserProcessor")
public class CurrentUserProcessorImpl implements DataPermissionsProcessorInterface  {

	@SuppressWarnings("unchecked")
	@Override
	public void buildSql(MetaObject metaObject, String sourceSql, DataPermissionsBean dataPermissionsBean,Map<String, Object> parameter) throws Exception {
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
		List<ParameterMapping> parameterMappings  = new ArrayList<>(boundSql.getParameterMappings());
		Map<String, Object> additionalParameters = (Map<String, Object>) metaObject.getValue("delegate.boundSql.additionalParameters");
		Object parameterObject = metaObject.getValue("delegate.boundSql.parameterObject");
		
		String userId = ((UserBean) parameter.get("userInfo")).getUserId();
		
		String sql = " SELECT SOURCE_TABLE.*  FROM   (" + sourceSql + ") AS SOURCE_TABLE WHERE SOURCE_TABLE."
				+ dataPermissionsBean.getMatchColumn() + " = ? ";
		
        String key = "currentUserId";
		
		ParameterMappingUtil.parameterMappingAdd(parameterMappings, additionalParameters, mappedStatement, key, String.class, userId);
		
		Object param = ParameterObjectUtil.processSingle(parameterObject, parameterMappings, key, userId);
		
		metaObject.setValue("delegate.boundSql.sql", sql);
		metaObject.setValue("delegate.boundSql.parameterMappings", parameterMappings);
		metaObject.setValue("delegate.boundSql.additionalParameters", additionalParameters);
		metaObject.setValue("delegate.boundSql.parameterObject", param);
	}

}
