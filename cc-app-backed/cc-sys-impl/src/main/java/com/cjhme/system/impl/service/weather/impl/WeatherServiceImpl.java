package com.cjhme.system.impl.service.weather.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.cjhme.common.util.JsonUtil;
import com.cjhme.system.impl.service.weather.WeatherService;

/**
 * 
 * <p>
 * Title: WeatherServiceImpl.java
 * </p>
 * Description: 天气
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
@Service("system.weatherService")
public class WeatherServiceImpl implements WeatherService {
	@Autowired
	private Environment env;

	/**
	 * 查询天气
	 * 
	 * @param parameter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> queryWeather(Map<String, Object> parameter) {
		Map<String, Object> result = new HashMap<>();
		HttpGet httpGet = new HttpGet(env.getProperty("weather.url") + "&location=" + parameter.get("cityName"));
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();) {

			Map<String, Object> content = (Map<String, Object>) JsonUtil.jsonStrToObj(
					EntityUtils.toString(httpClient.execute(httpGet).getEntity()),
					new HashMap<String, Object>().getClass());
			result.put("success", true);
			result.put("content", content);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		return result;
	}
}
