package com.cjhme.web.action.sysinfo;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cjhme.common.constant.CommonConstants;
import com.cjhme.common.model.base.ResultData;
import com.cjhme.system.impl.service.sysinfo.SysInfoService;

/**
 * 
 * <p>
 * Title: SysInfoAction.java
 * </p>
 * Description: 系统信息
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/sysInfo")
public class SysInfoAction {

	Log LOGGER = LogFactory.getLog(SysInfoAction.class);

	private SysInfoService sysInfoService;

	/**
	 * 初始系统信息数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/initSysInfoData", method = RequestMethod.POST)
	public void initSysInfoData(HttpServletResponse response) throws Exception {
		ResultData resultData = new ResultData();
		try {
			resultData.setResultType(CommonConstants.RESULT_SUCCESS);
			resultData.setResultData(this.sysInfoService.querySysInfo());
			LOGGER.info("初始系统信息数据成功！");

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始系统信息数据失败！");
			e.printStackTrace();
			LOGGER.error("初始系统信息数据失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}

	public SysInfoService getSysInfoService() {
		return sysInfoService;
	}

	@Resource(name = "system.sysInfoService")
	public void setSysInfoService(SysInfoService sysInfoService) {
		this.sysInfoService = sysInfoService;
	}

}
