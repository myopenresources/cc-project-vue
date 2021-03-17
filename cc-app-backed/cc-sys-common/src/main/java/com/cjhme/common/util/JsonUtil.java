package com.cjhme.common.util;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * <p>
 * Title: JsonUtil.java
 * </p>
 * Description: json工具类
 * <p>
 * Modify histoty:
 *
 * @author cjh
 * @version 1.0
 */
public class JsonUtil {

	/**
	 * 对象转换成json字符串
	 * 
	 * @param jsonStr
	 * @return
	 * @throws Exception
	 */
	public final static String objToJsonStr(Object obj) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		return om.writeValueAsString(obj);
	}

	/**
	 * json字符转换成串对象
	 * 
	 * @param jsonStr
	 * @return
	 * @throws Exception
	 */
	public final static Object jsonStrToObj(String jsonStr, Class<?> objClass) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		return om.readValue(jsonStr, objClass);
	}
	
	/**
	 * json字符转换成串集合
	 * 
	 * @param jsonStr
	 * @return
	 * @throws Exception
	 */
	public final static Object jsonStrToList(String jsonStr, Class<?> objClass) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		JavaType javaType = om.getTypeFactory().constructCollectionType(List.class, objClass);
		return om.readValue(jsonStr, javaType);
	}
}
