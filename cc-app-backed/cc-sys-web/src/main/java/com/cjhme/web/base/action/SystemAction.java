package com.cjhme.web.base.action;

import java.util.ArrayList;
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

import com.cjhme.common.annotation.Auth;
import com.cjhme.common.constant.CommonConstants;
import com.cjhme.common.model.base.ResultData;
import com.cjhme.common.model.button.ButtonBean;
import com.cjhme.common.model.datadic.DataDicItemBean;
import com.cjhme.common.model.notice.NoticeBean;
import com.cjhme.common.model.user.UserBean;
import com.cjhme.common.util.AesUtil;
import com.cjhme.common.util.ConfigUtil;
import com.cjhme.common.util.DigestUtil;
import com.cjhme.common.util.IPUtil;
import com.cjhme.common.util.JsonUtil;
import com.cjhme.common.util.RequestParamToObjectUtil;
import com.cjhme.common.util.UUIDUtil;
import com.cjhme.system.impl.service.fileupload.FileDataService;
import com.cjhme.system.impl.service.log.LoginLogService;
import com.cjhme.system.impl.service.menu.MenuService;
import com.cjhme.system.impl.service.notice.NoticeService;
import com.cjhme.system.impl.service.task.TaskService;
import com.cjhme.system.impl.service.user.UserService;


/**
 * <p>
 * Title: SystemAction.java
 * </p>
 * Description: 系统action
 * <p>
 * Modify histoty:
 *
 * @author cjh
 * @version 1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/system")
public class SystemAction extends SysBaseAction {

	Log LOGGER = LogFactory.getLog(SystemAction.class);

	private MenuService menuService;

	private UserService userService;

	private TaskService taskService;

	private LoginLogService loginLogService;

	private FileDataService fileDataService;

	private NoticeService noticeService;

	/**
	 * 用户登录(-1：系统错误，-2：用户名和密码为空，-3：用户名或密码为空，-4：用户名或密码有误，-5：用户被禁用，1登录成功)
	 *
	 * @param request
	 * @return
	 */
	@Auth(verifyLogin = false, verifyUrl = false)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void login(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request,
					Map.class);

			if (null != parameter) {

				// 解密
				String param = AesUtil.aesDecrypt(String.valueOf(parameter.get("data")),
						ConfigUtil.getInstance().getPopString(CommonConstants.LOGIN_AES_KEY));
				
				Map<String, Object> data = (Map<String, Object>) JsonUtil.jsonStrToObj(param, Map.class);

				String userName = String.valueOf(data.get("userName"));
				String password = String.valueOf(data.get("password"));

				if (null != userName && !"".equals(userName) && null != password && !"".equals(password)) {
					parameter.put("userName", userName);
					parameter.put("password", DigestUtil.md5(password));
					UserBean userBean = this.userService.login(parameter);

					if (null != userBean) {
						String status = userBean.getStatus();
						if ("1".equals(status)) {
							// 添加session
							session.setAttribute(CommonConstants.MANAGE_USER_SESSION, userBean);

							// 添加登录日志
							Map<String, Object> params = new HashMap<String, Object>();
							String loginIP = IPUtil.getIpAddr(request);
							params.put("createUserId", userBean.getUserId());
							params.put("loginIP", loginIP);
							params.put("loginId", UUIDUtil.createUUID32());
							this.loginLogService.saveLoginLog(params);

							// 传密钥
							userBean.setKey(AesUtil.aesEncrypt(
									ConfigUtil.getInstance().getPopString(CommonConstants.REQUEST_AES_KEY),
									ConfigUtil.getInstance().getPopString(CommonConstants.LOGIN_AES_KEY)));

							resultData.setResultType(CommonConstants.RESULT_SUCCESS);
							resultData.setResultData(userBean);
							resultData.setResultMsg("登录成功！");
							LOGGER.info("登录成功！");
						} else {
							resultData.setResultType(CommonConstants.RESULT_FAILURE);
							resultData.setResultMsg("用户被禁用！");
							LOGGER.info("用户被禁用!");
						}

					} else {
						resultData.setResultType(CommonConstants.RESULT_FAILURE);
						resultData.setResultMsg("用户名或密码有误！");
						LOGGER.info("用户名或密码有误!");
					}

				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("用户名或密码为空！");
					LOGGER.info("用户名或密码为空!");
				}

			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("用户名和密码为空！");
				LOGGER.info("用户名和密码为空!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("登录失败！");
			LOGGER.error("登录失败,错误——>" + e);
		}

		resultData.loginPrintJsonData(response);
	}

	/**
	 * 退出登录
	 *
	 * @param request
	 * @param session
	 * @return
	 */
	@Auth(verifyLogin = false, verifyUrl = false)
	@RequestMapping(value = "/exitLogin", method = RequestMethod.POST)
	public void exitLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ResultData resultData = new ResultData();
		try {
			// 从session中清除用户
			if (session.getAttribute(CommonConstants.MANAGE_USER_SESSION) != null
					&& !"".equals(session.getAttribute(CommonConstants.MANAGE_USER_SESSION))) {
				session.removeAttribute(CommonConstants.MANAGE_USER_SESSION);
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "no-store");
				response.setDateHeader("Expires", 0);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultMsg("退出登录成功！");
				LOGGER.info("退出登录成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("退出登录未成功，请刷新页面！");
				LOGGER.info("退出登录未成功!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("退出登录失败！");
			LOGGER.info("退出登录失败！");
		}

		resultData.printJsonData(response);
	}

	/**
	 * 初始化首页
	 *
	 * @param request
	 * @param session
	 * @return
	 */
	// todo
	@RequestMapping(value = "/initMainData", method = RequestMethod.GET)
	public void initMainData(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			if (null != userBean) {
				Map<String, Object> parameter = new HashMap<String, Object>();
				Map<String, Object> initMainData = new HashMap<String, Object>();

				// 菜单
				parameter.put("userId", userBean.getUserId());
				String menus = menuService.queryUserMenuByRoleId(parameter);

				initMainData.put("userData", userBean);// 用户信息
				initMainData.put("menuData", JsonUtil.jsonStrToList(menus, Map.class));// 菜单

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(initMainData);
				LOGGER.info("初始化首页成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始化首页未成功！");
				LOGGER.error("初始化首页未成功");
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始化首页失败！");
			LOGGER.error("初始化首页,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 初始化主页
	 *
	 * @param request
	 * @param session
	 * @return
	 */
	// todo
	@RequestMapping(value = "/initHomeData", method = RequestMethod.GET)
	public void initHomeData(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ResultData resultData = new ResultData();
		try {
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			if (null != userBean) {
				Map<String, Object> parameter = new HashMap<String, Object>();
				Map<String, Object> initHomeData = new HashMap<String, Object>();

				// 待办
				parameter.put("processUserId", userBean.getUserId());
				Map<String, Object> task = this.taskService.queryTaskByUserId(parameter);

				// 公告
				List<NoticeBean> notices = this.noticeService.queryNoticeByUserId(parameter);

				initHomeData.put("task", task);// 待办
				initHomeData.put("notices", notices);// 公告
				initHomeData.put("userData", userBean);// 用户信息

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(initHomeData);
				LOGGER.info("初始化首页成功！");

			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始化主页未成功！");
				LOGGER.error("初始化主页未成功");
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始化主页失败！");
			LOGGER.error("初始化主页,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 查询业务按钮
	 */
	@RequestMapping(value = "/queryBusniessBtn", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void queryBusniessBtn(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request,
					Map.class);
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			if (null != userBean && null != parameter) {
				// 查询按钮
				parameter.put("userId", userBean.getUserId());
				List<ButtonBean> buttonList = this.getBusniessBtn(parameter);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(buttonList);
				LOGGER.info("查询业务按钮成功！");

			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("查询业务按钮未成功！");
				LOGGER.error("查询业务按钮未成功");
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("查询业务按钮失败！");
			LOGGER.error("查询业务按钮,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 根据typeCode查询数据字典明细
	 */
	@RequestMapping(value = "/queryDataDicItemByDicTypeCode", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void queryDataDicItemByDicTypeCode(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request,
					Map.class);
			if (null != parameter) {
				List<DataDicItemBean> list = this
						.getDataDicItemByDicTypeCode(String.valueOf(parameter.get("typeCode")));
				if (null == list) {
					list = new ArrayList<DataDicItemBean>();
				}

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(list);
				LOGGER.info("根据typeCode获得数据字典明细成功！");

			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("查看数据字典明细未成功！");
				LOGGER.error("查看数据字典明细未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("查看数据字典明细失败！");
			LOGGER.error("查看数据字典明细,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	public MenuService getMenuService() {
		return menuService;
	}

	@Resource(name = "system.menuService")
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Resource(name = "system.userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	@Resource(name = "system.taskService")
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public LoginLogService getLoginLogService() {
		return loginLogService;
	}

	@Resource(name = "system.loginLogService")
	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public FileDataService getFileDataService() {
		return fileDataService;
	}

	@Resource(name = "system.fileDataService")
	public void setFileDataService(FileDataService fileDataService) {
		this.fileDataService = fileDataService;
	}

	public NoticeService getNoticeService() {
		return noticeService;
	}

	@Resource(name = "system.noticeService")
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

}
