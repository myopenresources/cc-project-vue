package com.cjhme.system.impl.base.mybatis.util;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;

/**
*
* <p>
* Title: ParameterMappingUtil.java
* </p>
* Description: 参数映射工具
* <p>
* Modify histoty:
*
* @author cjh
* @version 1.0
*/
public final class ParameterMappingUtil {

	/**
	  *  参数添加
	 * @param parameterMappings
	 * @param additionalParameters
	 * @param mappedStatement
	 * @param key
	 * @param javaType
	 * @param val
	 */
	public static void parameterMappingAdd(List<ParameterMapping> parameterMappings,
			Map<String, Object> additionalParameters, MappedStatement mappedStatement, String key, Class<?> javaType,Object val) {
		parameterMappings.add(new ParameterMapping.Builder(mappedStatement.getConfiguration(), key, javaType).build());
		additionalParameters.put(key, val);
	}
}
