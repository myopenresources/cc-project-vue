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
import com.cjhme.system.impl.base.mybatis.datapermissions.processor.DataPermissionsProcessorInterface;
import com.cjhme.system.impl.base.mybatis.util.ParameterMappingUtil;
import com.cjhme.system.impl.base.mybatis.util.ParameterObjectUtil;

/**
 *
 * <p>
 * Title: DesignatedUserProcessorImpl.java
 * </p>
 * Description: 指定用户数据权限处理器
 * <p>
 * Modify histoty:
 *
 * @author cjh
 * @version 1.0
 */
@Component("system.designatedUserProcessor")
public class DesignatedUserProcessorImpl implements DataPermissionsProcessorInterface {

	@SuppressWarnings("unchecked")
	@Override
	public void buildSql(MetaObject metaObject, String sourceSql, DataPermissionsBean dataPermissionsBean,Map<String, Object> parameter) throws Exception {
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
		List<ParameterMapping> parameterMappings  = new ArrayList<>(boundSql.getParameterMappings());
		Map<String, Object> additionalParameters = (Map<String, Object>) metaObject.getValue("delegate.boundSql.additionalParameters");
		Object parameterObject = metaObject.getValue("delegate.boundSql.parameterObject");

		List<String> designatedUserList = (List<String>) ((Map<String, Object>) parameter.get("parameter")).get(dataPermissionsBean.getParamKey());

		StringBuffer sqlBuffer = new StringBuffer(
				" SELECT SOURCE_TABLE.*  FROM   (" + sourceSql + ") AS SOURCE_TABLE WHERE ");

		Object param = null;
		for (int i = 0; i < designatedUserList.size(); i++) {
			String userId = designatedUserList.get(i);
			String key = "designatedUserId" + i;

			ParameterMappingUtil.parameterMappingAdd(parameterMappings, additionalParameters, mappedStatement, key, String.class, userId);
			param = ParameterObjectUtil.processSingle(parameterObject, parameterMappings, key, userId);
			
			sqlBuffer.append(" SOURCE_TABLE." + dataPermissionsBean.getMatchColumn() + " = ? ");
			if (i < designatedUserList.size() - 1) {
				sqlBuffer.append(" OR ");
			}
		}
		

		metaObject.setValue("delegate.boundSql.sql", sqlBuffer.toString());
		metaObject.setValue("delegate.boundSql.parameterMappings", parameterMappings);
		metaObject.setValue("delegate.boundSql.additionalParameters", additionalParameters);
		metaObject.setValue("delegate.boundSql.parameterObject", param);
	}

}