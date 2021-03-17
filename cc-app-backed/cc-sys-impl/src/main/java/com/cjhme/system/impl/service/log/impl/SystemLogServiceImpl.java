package com.cjhme.system.impl.service.log.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.cjhme.common.model.log.SystemLogFileBean;
import com.cjhme.common.util.ConfigUtil;
import com.cjhme.common.util.SystemLogFileDescComparator;
import com.cjhme.system.impl.service.log.SystemLogService;

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
@Service("system.systemLogService")
public class SystemLogServiceImpl implements SystemLogService {

	Log LOGGER = LogFactory.getLog(SystemLogServiceImpl.class);

	/**
	 * 获取日志文件
	 * 
	 * @return
	 */
	public List<SystemLogFileBean> querySystemLogFile() {
		String systemLogPath = ConfigUtil.getInstance().getPopString("systemLogPath");
		if (null != systemLogPath && !"".equals(systemLogPath)) {
			File file = new File(systemLogPath);
			if (file.exists()) {
				File[] files = file.listFiles();
				if (null != files && files.length > 0) {
					return SystemLogFileDescComparator.asList(files);
				} else {
					LOGGER.info(systemLogPath + "目录中无日志文件...");
				}
			} else {
				LOGGER.info(systemLogPath + "目录不存在...");
			}
		} else {
			LOGGER.info("无法从配置文件中获取systemLogPath...");
		}

		return new ArrayList<SystemLogFileBean>();
	}

}
