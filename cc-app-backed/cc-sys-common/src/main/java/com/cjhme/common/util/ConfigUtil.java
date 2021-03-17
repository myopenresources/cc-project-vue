package com.cjhme.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 
 * <p>
 * Title: ConfigUtil.java
 * </p>
 * Description: 配置文件读取
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public class ConfigUtil {
	
	Log LOGGER = LogFactory.getLog(ConfigUtil.class);

	/**
	 * properties对象
	 */
	private Properties pop = null;

	/**
	 * 单例
	 */
	private static ConfigUtil CONFIGUTIL = new ConfigUtil();

	private ConfigUtil() {
		InputStream inputStream = null;
		try {
			LOGGER.info("初始化system/config.properties配置文件！");
			inputStream = ConfigUtil.class.getResourceAsStream("/system/config.properties");
			pop = new Properties();
			pop.load(inputStream);
		} catch (Exception e) {
			LOGGER.info("初始化system/config.properties配置文件失败！");
			e.printStackTrace();
		}
	}

	/**
	 * 获得实例
	 * 
	 * @return
	 */
	public static ConfigUtil getInstance() {
		if (null != CONFIGUTIL) {
			return CONFIGUTIL;
		} else {
			return null;
		}
	}

	/**
	 * 根据key获得value
	 * 
	 * @return
	 */
	public String getPopString(String key) {
		if (null != key && !"".equals(key) && null != pop) {
			LOGGER.info("根据"+key+"获得"+pop.getProperty(key));
			return pop.getProperty(key);
		} else {
			return null;
		}
	}
	
	/**
	 * 根据key设置value
	 * @param key
	 * @param value
	 */
	public void setPopString(String key,String value){
		if(null!=value && !"".equals(value)){
			LOGGER.info("根据"+key+"设置"+value);
			pop.setProperty(key, value);
			FileOutputStream fos=null;
			try {
				URL url = ConfigUtil.class.getResource("/system/config.properties");
				File file = new File(url.toURI());  
				if(file.exists()){
					fos= new FileOutputStream(file); 
				}else{
					pop.store(fos, "找不到系统配置文件/system/config.properties");
				}
				 
				pop.store(fos, "系统配置文件");
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(null!=fos){
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}else{
			LOGGER.info("根据key设置value失败！");
		}
	}

	public static void main(String[] args) {
		ConfigUtil.getInstance().getPopString("systemLogPath");
	}

}
