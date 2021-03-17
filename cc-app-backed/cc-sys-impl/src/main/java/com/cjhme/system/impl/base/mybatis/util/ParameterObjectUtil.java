package com.cjhme.system.impl.base.mybatis.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.binding.MapperMethod.MapperParamMap;
import org.apache.ibatis.mapping.ParameterMapping;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;

/**
*
* <p>
* Title: ParameterObjectUtil.java
* </p>
* Description: 查询参数工具类
* <p>
* Modify histoty:
*
* @author cjh
* @version 1.0
*/
public class ParameterObjectUtil {

	public static  boolean hasParamKey(List<String> paramNames, List<ParameterMapping> parameterMappings,String key) {
		boolean hasKey = false;
		for (ParameterMapping parameterMapping : parameterMappings) {
			if (StringUtils.equals(parameterMapping.getProperty(), key)) {
				hasKey = true;
			} else {
				paramNames.add(parameterMapping.getProperty());
			}
		}
		return hasKey;
	}

	public static Object processSingle(Object paramObj,List<ParameterMapping> parameterMappings,String key,Object val) throws Exception {
		List<String> paramNames  = new ArrayList<>();
		hasParamKey(paramNames,parameterMappings,key);
		
		Map<String, Object> paramMap = new MapperParamMap<>();
		if (paramObj == null) {
			paramMap.put(key, val);
			paramObj = paramMap;
			// 单参数 将 参数转为 map
		} else if (ClassUtils.isPrimitiveOrWrapper(paramObj.getClass())
				|| String.class.isAssignableFrom(paramObj.getClass())
				|| Number.class.isAssignableFrom(paramObj.getClass())) {
			if (paramNames.size() == 1) {
				paramMap.put(paramNames.iterator().next(), paramObj);
				paramMap.put(key, val);
				paramObj = paramMap;
			}
		} else {
			processParam(paramObj,key,val);
		}

		return paramObj;
	}

	@SuppressWarnings("unchecked")
	public static void processParam(Object parameterObject,String key,Object val) throws IllegalAccessException, InvocationTargetException {
		// 处理参数对象 如果是 map 且map的key，添加到参数map中
		// 如果参数是bean，反射设置值
		if (parameterObject instanceof Map) {
			((Map<String,Object>) parameterObject).putIfAbsent(key, val);
		} else {
			PropertyDescriptor ps = BeanUtils.getPropertyDescriptor(parameterObject.getClass(), key);
			if (ps != null && ps.getReadMethod() != null && ps.getWriteMethod() != null) {
				Object value = ps.getReadMethod().invoke(parameterObject);
				if (value == null) {
					ps.getWriteMethod().invoke(parameterObject, val);
				}
			}
		}
	}
}
