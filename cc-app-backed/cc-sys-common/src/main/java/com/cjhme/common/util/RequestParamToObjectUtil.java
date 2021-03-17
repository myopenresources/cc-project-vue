package com.cjhme.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cjhme.common.constant.CommonConstants;
import com.cjhme.common.model.base.DataPaging;

/**
 * 
 * <p>
 * Title: RequestParamToObjectUtil.java
 * </p>
 * Description: 流转换成对象
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public class RequestParamToObjectUtil {

	static Log LOGGER = LogFactory.getLog(RequestParamToObjectUtil.class);

	/**
	 * 流转换成JSON String
	 * 
	 * @param request 请求
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static String jsonParseToJSONString(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		// 读取request 流
		BufferedReader reader;
		reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		StringBuilder tempStr = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			tempStr.append(line);
		}
		reader.close();

		Map<String,Object> tempMap =(Map<String, Object>) JsonUtil.jsonStrToObj(tempStr.toString(), Map.class);
		String aesParamStr = tempMap.get("data").toString();
		LOGGER.info("接收的请求参数：" + aesParamStr);
		Boolean convert = Boolean.valueOf(tempMap.get("convert").toString());

		String param = "";
		if (convert) {
			param = AesUtil.aesDecrypt(aesParamStr,
					ConfigUtil.getInstance().getPopString(CommonConstants.REQUEST_AES_KEY));
		} else {
			param = aesParamStr;
		}

		LOGGER.info("接收的请求参数：" + param);

		return param;
	}

	/**
	 * 将流转换成对象
	 * 
	 * @param request   请求
	 * @param beanClass 转换成的类的class对象
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static Object jsonParseToObject(HttpServletRequest request, Class<?> beanClass) throws Exception {
		request.setCharacterEncoding("utf-8");
		// 读取request
		BufferedReader reader;
		reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		StringBuilder tempStr = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			tempStr.append(line);
		}

		reader.close();

		Map<String,Object> tempMap =(Map<String, Object>) JsonUtil.jsonStrToObj(tempStr.toString(), Map.class);
		String aesParamStr = tempMap.get("data").toString();
		LOGGER.info("接收的请求参数：" + aesParamStr);
		Boolean convert = Boolean.valueOf(tempMap.get("convert").toString());

		String param = "";
		if (convert) {
			param = AesUtil.aesDecrypt(aesParamStr,
					ConfigUtil.getInstance().getPopString(CommonConstants.REQUEST_AES_KEY));
		} else {
			param = tempStr.toString();
		}

		LOGGER.info("转换后的请求参数：" + param);
		return JsonUtil.jsonStrToObj(param, beanClass);
	}

	/**
	 * 默认将流转换成对象
	 *
	 * @param request   请求
	 * @param beanClass 转换成的类的class对象
	 * @return
	 * @throws IOException
	 */
	/*
	 * public static Object defaultJsonParseToObject(HttpServletRequest request,
	 * Class<?> beanClass) throws Exception { request.setCharacterEncoding("utf-8");
	 * // 读取request BufferedReader reader; reader = new BufferedReader(new
	 * InputStreamReader(request.getInputStream(), "UTF-8")); StringBuilder tempStr
	 * = new StringBuilder(); String line = null; while ((line = reader.readLine())
	 * != null) { tempStr.append(line); }
	 * 
	 * reader.close();
	 * 
	 * LOGGER.info("默认请求参数："+tempStr.toString()); JSONObject jsonData =
	 * JSONObject.fromObject(tempStr.toString()); return JSONObject.toBean(jsonData,
	 * beanClass); }
	 */

	/**
	 * 将流转换成集合
	 * 
	 * @param request   请求
	 * @param beanClass 转换成的类的class对象
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static List<Object> jsonParseToList(HttpServletRequest request, Class<?> beanClass) throws Exception {
		request.setCharacterEncoding("utf-8");
		// 读取request
		BufferedReader reader;
		reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		StringBuilder tempStr = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			tempStr.append(line);
		}

		reader.close();
		
		Map<String,Object> tempMap =(Map<String, Object>) JsonUtil.jsonStrToObj(tempStr.toString(), Map.class);
		String aesParamStr = tempMap.get("data").toString();
		LOGGER.info("接收的请求参数：" + aesParamStr);
		Boolean convert = Boolean.valueOf(tempMap.get("convert").toString());

		String param = "";
		if (convert) {
			param = AesUtil.aesDecrypt(aesParamStr,
					ConfigUtil.getInstance().getPopString(CommonConstants.REQUEST_AES_KEY));
		} else {
			param = aesParamStr;
		}

		LOGGER.info("转换后的请求参数：" + param);

		// JSONArray jsonArray = JSONArray.fromObject(param);
		// return (List<Object>) JSONArray.toCollection(jsonArray,beanClass);

		LOGGER.info("转换后的请求参数：" + param);
		return  (List<Object>) JsonUtil.jsonStrToList(param, beanClass);
	}

	/**
	 * 将请求参数转换成Map
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static Map<String, Object> requestParamParseToMap(HttpServletRequest request) throws IOException {
		// 返回值Map
		Map<String, Object> returnMap = new HashMap<String, Object>();

		// 参数Map
		Map<?, ?> parameterMap = request.getParameterMap();

		Iterator<?> entries = parameterMap.entrySet().iterator();
		Map.Entry<?, ?> entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry<?, ?>) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}

		LOGGER.info("GET请求参数：" + returnMap.toString());

		return returnMap;
	}

	/**
	 * 将请求参数转换成Page Map
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static DataPaging<Object> requestParamParseToPageMap(HttpServletRequest request) throws Exception {
		// 参数Map
		Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request,
				Map.class);

		// 返回值Page Map
		DataPaging<Object> returnPageMap = DataPaging.pageUtil(String.valueOf(parameter.get("page")),
				String.valueOf(parameter.get("rows")));
		returnPageMap.setParameter(parameter);

		LOGGER.info("分页请求参数：" + returnPageMap.toString());

		return returnPageMap;
	}

}
