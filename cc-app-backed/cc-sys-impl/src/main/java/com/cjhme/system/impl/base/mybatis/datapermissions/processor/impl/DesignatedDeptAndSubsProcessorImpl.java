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
import com.cjhme.common.model.dept.DeptBean;
import com.cjhme.system.impl.base.mybatis.datapermissions.processor.DataPermissionsProcessorInterface;
import com.cjhme.system.impl.base.mybatis.util.ParameterMappingUtil;
import com.cjhme.system.impl.base.mybatis.util.ParameterObjectUtil;

/**
*
* <p>
* Title: DesignatedDeptSubsProcessorImpl.java
* </p>
* Description: 指定部门及其下级部门	数据权限处理器
* <p>
* Modify histoty:
*
* @author cjh
* @version 1.0
*/
@Component("system.designatedDeptAndSubsProcessor")
public class DesignatedDeptAndSubsProcessorImpl implements DataPermissionsProcessorInterface  {

	@SuppressWarnings("unchecked")
	@Override
	public void buildSql(MetaObject metaObject, String sourceSql, DataPermissionsBean dataPermissionsBean,Map<String, Object> parameter) throws Exception {
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
		List<ParameterMapping> parameterMappings  = new ArrayList<>(boundSql.getParameterMappings());
		Map<String, Object> additionalParameters = (Map<String, Object>) metaObject.getValue("delegate.boundSql.additionalParameters");
		Object parameterObject = metaObject.getValue("delegate.boundSql.parameterObject");
		
		List<DeptBean> designatedDeptList = (List<DeptBean>) ((Map<String,Object>) parameter.get("parameter")).get(dataPermissionsBean.getParamKey());
		
		StringBuffer sqlBuffer=new StringBuffer(" SELECT SOURCE_TABLE.*  FROM   (" + sourceSql + ") AS SOURCE_TABLE LEFT JOIN SYS_DB.T_DEPT AS SYS_DB_DEPT ");
		sqlBuffer.append("ON( SYS_DB_DEPT.DEPT_ID = SOURCE_TABLE."+ dataPermissionsBean.getMatchColumn()+" ) WHERE ");
		
		Object param = null;
		for(int i=0;i<designatedDeptList.size();i++) {
			DeptBean dept = designatedDeptList.get(i);
			
			String leftVaKey = "designatedDeptAndSubsLeft"+ i;
			String rightVaKey = "designatedDeptAndSubsRight"+ i;
			
			ParameterMappingUtil.parameterMappingAdd(parameterMappings, additionalParameters, mappedStatement, leftVaKey, Integer.class, dept.getLeftValue());
			ParameterMappingUtil.parameterMappingAdd(parameterMappings, additionalParameters, mappedStatement, rightVaKey, Integer.class, dept.getRightValue());
			
			param = ParameterObjectUtil.processSingle(parameterObject, parameterMappings, leftVaKey, dept.getLeftValue());
			param = ParameterObjectUtil.processSingle(parameterObject, parameterMappings, rightVaKey, dept.getRightValue());
			
			sqlBuffer.append(" ( SYS_DB_DEPT.LEFT_VALUE >= ? AND SYS_DB_DEPT.RIGHT_VALUE <= ? ) ");
			if(i<designatedDeptList.size()-1) {
				sqlBuffer.append(" OR ");
			}	
		}
		
		metaObject.setValue("delegate.boundSql.sql", sqlBuffer.toString());
		metaObject.setValue("delegate.boundSql.parameterMappings", parameterMappings);
		metaObject.setValue("delegate.boundSql.additionalParameters", additionalParameters);
		metaObject.setValue("delegate.boundSql.parameterObject", param);
	}

}
