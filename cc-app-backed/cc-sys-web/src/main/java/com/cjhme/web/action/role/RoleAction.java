package com.cjhme.web.action.role;

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
import com.cjhme.common.constant.DataDicConstants;
import com.cjhme.common.model.base.DataPaging;
import com.cjhme.common.model.base.ResultData;
import com.cjhme.common.model.base.TreeNodeBean;
import com.cjhme.common.model.button.ButtonBean;
import com.cjhme.common.model.role.RoleBean;
import com.cjhme.common.model.role.RoleMenuMappingBean;
import com.cjhme.common.model.user.UserBean;
import com.cjhme.common.util.CreateTreeUtil;
import com.cjhme.common.util.JsonUtil;
import com.cjhme.common.util.RequestParamToObjectUtil;
import com.cjhme.common.util.UUIDUtil;
import com.cjhme.system.impl.service.accessurl.AccessUrlService;
import com.cjhme.system.impl.service.menu.MenuService;
import com.cjhme.system.impl.service.role.RoleService;
import com.cjhme.system.impl.service.user.UserService;
import com.cjhme.web.base.action.SysBaseAction;

/**
 * 
 * <p>
 * Title: RoleAction.java
 * </p>
 * Description: 角色
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/role")
public class RoleAction extends SysBaseAction {

	Log LOGGER = LogFactory.getLog(RoleAction.class);

	private RoleService roleService;

	private UserService userService;

	private MenuService menuService;

	private AccessUrlService accessUrlService;

	/**
	 * 初始角色管理数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/initRoleManageData", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void initRoleManageData(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request,
					Map.class);
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			if (null != userBean && !"".equals(userBean) && !"null".equals(userBean) && null != parameter) {
				Map<String, Object> map = new HashMap<String, Object>();
				// 绑定数据字典
				this.bindDataDicParam(map, new String[] { DataDicConstants.QUERY_IS_SUPER_TYPE,
						DataDicConstants.IS_SUPER_TYPE, DataDicConstants.STATUS_TYPE });

				parameter.put("userId", userBean.getUserId());
				parameter.put("busniessMark", BusniessMarkConstants.ROLE_MANAGE);
				List<ButtonBean> buttonList = this.getBusniessBtn(parameter);
				map.put("buttonList", buttonList);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("初始角色管理数据成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始角色管理数据未成功！");
				LOGGER.error("初始角色管理数据未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始角色管理数据失败！");
			e.printStackTrace();
			LOGGER.error("初始角色管理数据失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}

	/**
	 * 根据条件分页查询角色
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryRoleByConditionPaging", method = RequestMethod.POST)
	public void queryRoleByConditionPaging(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			DataPaging<Object> result = this.roleService
					.queryRoleByConditionPaging(RequestParamToObjectUtil.requestParamParseToPageMap(request));
			if (null != result) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("rows", result.getRows());
				map.put("total", result.getTotal());

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("根据条件分页查询角色成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("根据条件分页查询角色未成功！");
				LOGGER.error("根据条件分页查询角色未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("根据条件分页查询角色失败！");
			e.printStackTrace();
			LOGGER.error("根据条件分页查询角色失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}

	/**
	 * 初始化角色数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/initRoleAddData", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void initRoleAddData(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request,
					Map.class);
			if (!StringUtils.isEmpty(parameter)) {
				Map<String, Object> map = new HashMap<String, Object>();

				// 绑定数据字典
				this.bindDataDicParam(map,
						new String[] { DataDicConstants.IS_SUPER_TYPE, DataDicConstants.STATUS_TYPE });

				if (!StringUtils.isEmpty(parameter.get("roleId"))) {
					RoleBean roleData = this.roleService.queryRoleById(parameter);
					map.put("roleData", roleData);
				}
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("初始化角色数据成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始化角色数据未成功！");
				LOGGER.error("初始化角色数据未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始化角色数据失败！");
			e.printStackTrace();
			LOGGER.error("初始化角色数据失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}

	/**
	 * 保存角色
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveRole", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveRole(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request,
					Map.class);

			// 检测是否存在
			int isExists = this.roleService.queryRoleIsExists(parameter);
			if (isExists > 0) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("编号或角色名称已存在！");
				LOGGER.info("编号或角色名称已存在！");
			} else {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);

				int result = 0;
				if (StringUtils.isEmpty(parameter.get("roleId"))) {
					parameter.put("createUserId", userBean.getUserId());
					parameter.put("roleId", UUIDUtil.createUUID32());
					result = this.roleService.saveRole(parameter);
				} else {
					parameter.put("updateUserId", userBean.getUserId());
					result = this.roleService.updateRoleById(parameter);
				}

				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("保存角色成功！");
					LOGGER.info("保存角色成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("保存角色未成功！");
					LOGGER.info("保存角色未成功！");
				}
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("保存角色失败！");
			e.printStackTrace();
			LOGGER.error("保存角色失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 根据ids删除角色
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteRoleByIds", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void deleteRoleByIds(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request,
					Map.class);
			String idsStr = parameter.get("ids").toString();
			if (!StringUtils.isEmpty(parameter) && !StringUtils.isEmpty(idsStr)) {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("updateUserId", userBean.getUserId());
				int result = this.roleService.deleteRoleByIds(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("删除角色成功！");
					LOGGER.info("根据ids删除角色成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("删除角色未成功！");
					LOGGER.info("根据ids删除角色未成功！");
				}
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("删除角色失败！");
			e.printStackTrace();
			LOGGER.error("根据ids删除角色失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 查询角色信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryRoleById", method = RequestMethod.GET)
	public void queryRoleById(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			String roleId = String.valueOf(request.getParameter("roleId"));
			if (!StringUtils.isEmpty(roleId)) {

				Map<String, Object> map = new HashMap<String, Object>();

				// 绑定数据字典
				this.bindDataDicParam(map,
						new String[] { DataDicConstants.IS_SUPER_TYPE, DataDicConstants.STATUS_TYPE });

				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("roleId", roleId);
				RoleBean roleData = this.roleService.queryRoleById(parameter);
				map.put("roleData", roleData);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("查询角色信息成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("查询角色信息未成功！");
				LOGGER.error("查询角色信息未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("查询角色信息失败！");
			e.printStackTrace();
			LOGGER.error("查询角色信息失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}

	/**
	 * 查询角色用户
	 * 
	 * @return
	 */

	@RequestMapping(value = "/queryRoleUsers", method = RequestMethod.GET)
	public void queryRoleUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResultData resultData = new ResultData();
		try {
			String roleId = String.valueOf(request.getParameter("roleId"));
			if (!StringUtils.isEmpty(roleId)) {
				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("roleId", roleId);
				List<UserBean> roleUser = this.userService.queryUserByRoleId(parameter);

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("roleUser", roleUser);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("查询角色用户成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("查询角色用户未成功！");
				LOGGER.error("查询角色用户未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("查询角色用户失败！");
			e.printStackTrace();
			LOGGER.error("查询角色用户失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}

	/**
	 * 查询角色权限分配
	 * 
	 * @return
	 */

	@RequestMapping(value = "/queryRolePermissionDistribute", method = RequestMethod.GET)
	public void queryRolePermissionDistribute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ResultData resultData = new ResultData();
		try {
			String roleId = String.valueOf(request.getParameter("roleId"));
			if (!StringUtils.isEmpty(roleId)) {
				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("roleId", roleId);
				parameter.put("status", CommonConstants.STATUS_NORMAL);
				parameter.put("dataPermissionsCategory", DataDicConstants.DATA_PERMIT_CATEGORY);
				parameter.put("dataPermissionsProcessor", DataDicConstants.DATA_PERMIT_PROCESSOR);

				Map<String, Object> map = new HashMap<>();
				// 按钮与访问地址
				List<TreeNodeBean> accessUrlAndButtonList = this.roleService.queryRoleAccessUrlAndButtonList(parameter);

				// 角色菜单
				String menuTreeData = this.menuService.queryMenuList(CreateTreeUtil.STATE_OPEN,
						CreateTreeUtil.STATE_CLOSED);

				// 数据权限
				List<TreeNodeBean> dataPermissionsList = this.roleService.queryRoleDataPermissionsList(parameter);

				List<RoleMenuMappingBean> roleMenuList = this.roleService.queryRoleMenuMappingById(parameter);
				List<String> roleMenuData = new ArrayList<String>();
				if (null != roleMenuList && !roleMenuList.isEmpty()) {
					for (RoleMenuMappingBean RoleMenu : roleMenuList) {
						roleMenuData.add(RoleMenu.getMenuId());
					}
				}
				map.put("accessUrlAndButtonData", accessUrlAndButtonList);
				map.put("menuTreeData", JsonUtil.jsonStrToList(menuTreeData, Map.class));
				map.put("roleMenuData", roleMenuData);
				map.put("dataPermissionsData", dataPermissionsList);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("查询角色权限分配成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("查询角色权限分配未成功！");
				LOGGER.error("查询角色权限分配未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("查询角色权限分配失败！");
			e.printStackTrace();
			LOGGER.error("查询角色权限分配失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}

	/**
	 * 保存角色权限分配
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveRolePermissionDistribute", method = RequestMethod.POST)
	public void saveRolePermissionDistribute(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request,
					Map.class);

			if (!StringUtils.isEmpty(parameter)) {
				int result = this.roleService.saveRolePermissionDistribute(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("保存角色权限分配成功！");
					LOGGER.info("保存角色权限分配成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("保存角色权限分配未成功！");
					LOGGER.info("保存角色权限分配未成功！");
				}
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("保存角色权限分配未成功！");
				LOGGER.info("保存角色权限分配未成功！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("保存角色权限分配失败！");
			e.printStackTrace();
			LOGGER.error("保存角色权限分配失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	public RoleService getRoleService() {
		return roleService;
	}

	@Resource(name = "system.roleService")
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Resource(name = "system.userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	@Resource(name = "system.menuService")
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public AccessUrlService getAccessUrlService() {
		return accessUrlService;
	}

	@Resource(name = "system.accessUrlService")
	public void setAccessUrlService(AccessUrlService accessUrlService) {
		this.accessUrlService = accessUrlService;
	}

}
