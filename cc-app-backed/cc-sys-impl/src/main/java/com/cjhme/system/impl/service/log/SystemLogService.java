package com.cjhme.system.impl.service.log;

import java.util.List;

import com.cjhme.common.model.log.SystemLogFileBean;

/**
 * 
 * <p>
 * Title: SystemLogService.java
 * </p>
 * Description: 系统日志
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public interface SystemLogService {

	/**
	 * 获取日志文件
	 * 
	 * @return
	 */
	public List<SystemLogFileBean> querySystemLogFile();

}
