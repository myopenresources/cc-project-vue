package com.cjhme.web.action.dept;

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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cjhme.common.constant.BusniessMarkConstants;
import com.cjhme.common.constant.CommonConstants;
import com.cjhme.common.model.base.ResultData;
import com.cjhme.common.model.base.TreeNodeBean;
import com.cjhme.common.model.button.ButtonBean;
import com.cjhme.common.model.dept.DeptBean;
import com.cjhme.common.model.user.UserBean;
import com.cjhme.common.util.CreateTreeUtil;
import com.cjhme.common.util.JsonUtil;
import com.cjhme.common.util.RequestParamToObjectUtil;
import com.cjhme.common.util.UUIDUtil;
import com.cjhme.system.impl.service.dept.DeptService;
import com.cjhme.system.impl.service.user.UserService;
import com.cjhme.web.base.action.SysBaseAction;

/**
 * 
 * <p>
 * Title: DeptAction.java
 * </p>
 * Description: 部门
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/dept")
public class DeptAction extends SysBaseAction {

	Log LOGGER = LogFactory.getLog(DeptAction.class);

	private DeptService deptService;

	private UserService userService;

	/**
	 * 初始部门管理数据
	 * 
	 * @param request
	 * @return
	 */
	// todo
	@RequestMapping(value = "/initDeptManageData", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void initDeptManageData(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request,
					Map.class);
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			if (null != userBean && null != parameter) {
				Map<String, Object> map = new HashMap<String, Object>();

				parameter.put("userId", userBean.getUserId());
				parameter.put("busniessMark", BusniessMarkConstants.DEPT_MANAGE);
				List<ButtonBean> buttonList = this.getBusniessBtn(parameter);
				map.put("buttonList", buttonList);

				String depts = this.deptService.queryDeptList(CreateTreeUtil.STATE_OPEN, CreateTreeUtil.STATE_OPEN);
				map.put("deptList", JsonUtil.jsonStrToList(depts, Map.class));

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("初始部门管理数据成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始部门管理数据未成功！");
				LOGGER.error("初始部门管理数据未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始部门管理数据失败！");
			e.printStackTrace();
			LOGGER.error("初始部门管理数据失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}

	/**
	 * 查询部门信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/queryDeptByDeptId", method = RequestMethod.GET)
	public void queryDeptByDeptId(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			String deptId = String.valueOf(request.getParameter("deptId"));
			if (!StringUtils.isEmpty(deptId)) {

				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("deptId", deptId);
				DeptBean deptBean = this.deptService.queryDeptByDeptId(parameter);

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("deptBean", deptBean);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("查询部门信息成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("查询部门信息未成功！");
				LOGGER.error("查询部门信息未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("查询部门信息失败！");
			e.printStackTrace();
			LOGGER.error("查询部门信息失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}

	/**
	 * 根据条件查询部门
	 * 
	 * @param orgCode
	 * @param orgName
	 * @param orgSortName
	 * @return
	 */
	@RequestMapping(value = "/queryDeptByCondition", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void queryDeptByCondition(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request,
					Map.class);
			if (null != parameter && !"".equals(parameter)) {
				Map<String, Object> map = new HashMap<String, Object>();

				List<TreeNodeBean> deptList = this.deptService.queryDeptByCondition(parameter);
				map.put("deptList", deptList);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				resultData.setResultMsg("根据条件查询部门成功");
				LOGGER.info("根据条件查询部门成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("根据条件查询部门未成功！");
				LOGGER.error("根据条件查询部门未成功！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("根据条件查询部门失败！");
			e.printStackTrace();
			LOGGER.error("根据条件查询部门失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 初始化部门添加数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/initDeptAddData", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void initDeptAddData(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request,
					Map.class);
			if (!StringUtils.isEmpty(parameter)) {
				Map<String, Object> map = new HashMap<String, Object>();

				String depts = this.deptService.queryDeptList(CreateTreeUtil.STATE_CLOSED, CreateTreeUtil.STATE_CLOSED);
				map.put("deptList", JsonUtil.jsonStrToList(depts, Map.class));

				Map<String, Object> userParameter = new HashMap<String, Object>();
				userParameter.put("status", CommonConstants.STATUS_NE_DEL);
				List<UserBean> userList = this.userService.queryUserList(userParameter);
				map.put("userList", userList);

				if (!StringUtils.isEmpty(parameter.get("deptId"))) {
					DeptBean deptBean = this.deptService.queryDeptByDeptId(parameter);
					map.put("deptData", deptBean);
				}

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("初始化部门添加数据成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始化用户添加数据未成功！");
				LOGGER.error("初始化用户添加数据未成功");
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始化部门添加数据失败！");
			e.printStackTrace();
			LOGGER.error("初始化部门添加数据失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}

	/**
	 * 保存部门
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveDept", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveDept(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request,
					Map.class);

			// 检测是否存在
			int isExists = this.deptService.queryDeptIsExists(parameter);
			if (isExists > 0) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("编号或部门名称已存在！");
				LOGGER.info("编号或部门名称已存在！");
			} else {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);

				int result = 0;
				if (StringUtils.isEmpty(parameter.get("deptId"))) {
					parameter.put("createUserId", userBean.getUserId());
					parameter.put("deptId", UUIDUtil.createUUID32());
					result = this.deptService.saveDept(parameter);
				} else {
					parameter.put("updateUserId", userBean.getUserId());
					result = this.deptService.updateDeptByDeptId(parameter);
				}

				if (result > 0) {
					String depts = this.deptService.queryDeptList(CreateTreeUtil.STATE_CLOSED,
							CreateTreeUtil.STATE_CLOSED);
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultData(JsonUtil.jsonStrToList(depts, Map.class));
					resultData.setResultMsg("保存部门成功！");
					LOGGER.info("保存部门成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("保存部门未成功！");
					LOGGER.info("保存部门未成功！");
				}
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("保存部门失败！");
			e.printStackTrace();
			LOGGER.error("保存部门失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}

	/**
	 * 根据ids删除部门
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteDeptByIds", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void deleteDeptByIds(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request,
					Map.class);
			if (!StringUtils.isEmpty(parameter)) {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("updateUserId", userBean.getUserId());

				int result = this.deptService.deleteDeptByIds(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("删除部门成功！");
					LOGGER.info("根据ids删除部门成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("删除部门未成功！");
					LOGGER.info("根据ids删除部门未成功！");
				}
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("删除部门失败！");
			e.printStackTrace();
			LOGGER.error("根据ids删除部门失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 查询部门用户
	 * 
	 * @return
	 */

	@RequestMapping(value = "/queryDeptUsers", method = RequestMethod.GET)
	public void queryDeptUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResultData resultData = new ResultData();
		try {
			String deptId = String.valueOf(request.getParameter("deptId"));
			if (!StringUtils.isEmpty(deptId)) {
				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("deptId", deptId);
				List<UserBean> deptUser = this.userService.queryUserByDeptId(parameter);

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("deptUser", deptUser);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("查询部门用户成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("查询部门用户未成功！");
				LOGGER.error("查询部门用户未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("查询部门用户失败！");
			e.printStackTrace();
			LOGGER.error("查询部门用户失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}

	/**
	 * 查询所有部门结构
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryDeptList", method = RequestMethod.POST)
	public void queryDeptList(HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			String depts = this.deptService.queryDeptList(CreateTreeUtil.STATE_OPEN, CreateTreeUtil.STATE_OPEN);
			resultData.setResultType(CommonConstants.RESULT_SUCCESS);
			resultData.setJsonArrayResultData(depts);
			resultData.setResultMsg("查询所有部门结构成功");
			LOGGER.info("查询所有部门结构成功！");
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("查询所有部门结构失败！");
			e.printStackTrace();
			LOGGER.error("查询所有部门结构失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	public DeptService getDeptService() {
		return deptService;
	}

	@Resource(name = "system.deptService")
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Resource(name = "system.userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
