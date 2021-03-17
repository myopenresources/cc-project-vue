package com.cjhme.web.action.log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cjhme.common.constant.CommonConstants;
import com.cjhme.common.model.base.DataPaging;
import com.cjhme.common.model.base.ResultData;
import com.cjhme.common.model.user.UserBean;
import com.cjhme.common.util.RequestParamToObjectUtil;
import com.cjhme.system.impl.service.log.LoginLogService;
import com.cjhme.system.impl.service.user.UserService;
import com.cjhme.web.base.action.SysBaseAction;

/**
 * 
 * <p>
 * Title: LoginLogAction.java
 * </p>
 * Description: 登录日志
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/loginLog")
public class LoginLogAction extends SysBaseAction {

	Log LOGGER = LogFactory.getLog(LoginLogAction.class);

	private LoginLogService loginLogService;
	private UserService userService;
	
	
	/**
	 * 初始登录日志管理数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/initLoginLogManageData", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void initLoginLogManageData(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if (null != parameter) {
				Map<String, Object> map = new HashMap<String, Object>();
				
				parameter.put("status", CommonConstants.STATUS_NE_DEL);
				List<UserBean>  userList=this.userService.queryUserList(parameter);
				map.put("userList", userList);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("初始登录日志管理数据成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始登录日志管理数据未成功！");
				LOGGER.error("初始登录日志管理数据未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始登录日志管理数据失败！");
			e.printStackTrace();
			LOGGER.error("初始登录日志管理数据失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}
	
	
	/**
	 * 根据条件分页查询登录日志
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryLoginLogByConditionPaging", method = RequestMethod.POST)
	public void queryLoginLogByConditionPaging(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			DataPaging<Object> result = this.loginLogService.queryLoginLogByConditionPaging(RequestParamToObjectUtil.requestParamParseToPageMap(request));
			if (null != result) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("rows", result.getRows());
				map.put("total", result.getTotal());

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("根据条件分页查询登录日志成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("根据条件分页查询登录日志未成功！");
				LOGGER.error("根据条件分页查询登录日志未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("根据条件分页查询登录日志失败！");
			e.printStackTrace();
			LOGGER.error("根据条件分页查询登录日志失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}



	public LoginLogService getLoginLogService() {
		return loginLogService;
	}

	@Resource(name="system.loginLogService")
	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}


	public UserService getUserService() {
		return userService;
	}


	@Resource(name="system.userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
}
