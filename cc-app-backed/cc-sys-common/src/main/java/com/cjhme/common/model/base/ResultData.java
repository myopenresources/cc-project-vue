package com.cjhme.common.model.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cjhme.common.constant.CommonConstants;
import com.cjhme.common.util.AesUtil;
import com.cjhme.common.util.ConfigUtil;
import com.cjhme.common.util.JsonUtil;
import com.cjhme.common.util.PrintData;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * <p>
 * Title: ResultData.java
 * </p>
 * Description: 返回参数
 * <p>
 * Modify histoty:
 *
 * @author cjh
 * @version 1.0
 */
public class ResultData {

	Log LOGGER = LogFactory.getLog(ResultData.class);

	/**
	 * 返回Map
	 */
	Map<String, Object> resultMap = null;

	/**
	 * 初始化
	 */
	public ResultData() {
		resultMap = new HashMap<String, Object>();
	}

	/**
	 * 返回json数据
	 *
	 * @return
	 */
	public String toJsonData() {
		String result = null;
		try {
			result = JsonUtil.objToJsonStr(resultMap);
			LOGGER.info("返回数据：" + result);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 输出json数据
	 *
	 * @param response
	 */
	public void printJsonData(HttpServletResponse response) {
		this.printJsonData(response, "application/json");
	}

	/**
	 * 输出json数据
	 *
	 * @param response
	 */
	public void printJsonData(HttpServletResponse response, String contentType) {
		try {
			String result = JsonUtil.objToJsonStr(resultMap);

			LOGGER.info("返回数据加密前：" + result);
			String aesData = AesUtil.aesEncrypt(result,
					ConfigUtil.getInstance().getPopString(CommonConstants.REQUEST_AES_KEY));

			Map<String, Object> json = new HashMap<>();
			json.put("data", aesData);
			json.put("convert", true);

			String resultJson = JsonUtil.objToJsonStr(json);

			LOGGER.info("返回数据加密后：" + resultJson);
			PrintData.printData(response, contentType, resultJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 登录输出json数据
	 *
	 * @param response
	 */
	public void loginPrintJsonData(HttpServletResponse response) {
		try {
			String result = JsonUtil.objToJsonStr(resultMap);
			LOGGER.info("返回数据加密前：" + result);
			String aesData = AesUtil.aesEncrypt(result,
					ConfigUtil.getInstance().getPopString(CommonConstants.LOGIN_AES_KEY));

			Map<String, Object> json = new HashMap<>();
			json.put("data", aesData);
			json.put("convert", false);

			String resultJson = JsonUtil.objToJsonStr(json);
			LOGGER.info("返回数据加密后：" + resultJson);
			PrintData.printData(response, "application/json", resultJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置返回类型
	 *
	 * @param value
	 */
	public void setResultType(String value) {
		resultMap.put("resultType", value);
	}

	/**
	 * 设置返回消息
	 *
	 * @param value
	 */
	public void setResultMsg(String value) {
		resultMap.put("resultMsg", value);
	}

	/**
	 * 设置返回数据
	 *
	 * @param value
	 */
	public void setResultData(Object value) {
		resultMap.put("resultData", value);
	}

	/**
	 * 设置json数组返回值
	 *
	 * @param json
	 */
	public void setJsonArrayResultData(String json) {
		try {
			resultMap.put("resultData", JsonUtil.jsonStrToList(json, Map.class));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置json对象返回值
	 *
	 * @param json
	 */
	public void setJsonObjResultData(String json) {
		try {
			resultMap.put("resultData",JsonUtil.jsonStrToObj(json, Map.class));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
