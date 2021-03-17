package com.cjhme.web.action.weather;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjhme.common.annotation.Auth;
import com.cjhme.common.constant.CommonConstants;
import com.cjhme.common.model.base.ResultData;
import com.cjhme.common.util.RequestParamToObjectUtil;
import com.cjhme.system.impl.service.weather.WeatherService;

/**
 * 
 * <p>
 * Title: WeatherAction.java
 * </p>
 * Description: 天气
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/weather")
public class WeatherAction {

	Log LOGGER = LogFactory.getLog(WeatherAction.class);
	
	private WeatherService weatherService;
	
	

	/**
	 * 查询天气
	 * @param request
	 * @param response
	 * @return
	 */
	@Auth(verifyUrl = false)
	@PostMapping("/queryWeather")
	@SuppressWarnings("unchecked")
	public void queryWeather(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request,Map.class);

			if (null != parameter) {
				Map<String, Object> result = this.weatherService.queryWeather(parameter);
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
                resultData.setResultData(result);
                resultData.setResultMsg("查询天气成功！");
				LOGGER.info("查询天气成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("查询天气未成功！");
				LOGGER.error("查询天气未成功");
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("查询天气失败！");
			LOGGER.error("查询天气,错误——>" + e);
		}
		resultData.printJsonData(response);
	}
	
	public WeatherService getWeatherService() {
		return weatherService;
	}

	@Resource(name="system.weatherService")
	public void setWeatherService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}
}
