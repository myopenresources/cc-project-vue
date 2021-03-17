package com.cjhme.system.impl.service.weather;

import java.util.Map;

/**
 * 
 * <p>
 * Title: WeatherService.java
 * </p>
 * Description: 天气
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public interface WeatherService {

	/**
	 * 查询天气
	 * @param parameter
	 * @return
	 */
	public Map<String,Object> queryWeather(Map<String, Object> parameter);
}
