package com.cjhme.system.impl.service.sysinfo;

import java.util.Map;

/**
 * 
 * <p>  
 * Title: SysInfoService.java 
 * </p>  
 * Description: 系统信息
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
public interface SysInfoService {

	public Map<String,Object>  querySysInfo()throws Exception;
}
