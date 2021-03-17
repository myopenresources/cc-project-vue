package com.cjhme.web.action.menu;

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
import com.cjhme.common.model.button.ButtonBean;
import com.cjhme.common.model.menu.MenuBean;
import com.cjhme.common.model.user.UserBean;
import com.cjhme.common.util.CreateTreeUtil;
import com.cjhme.common.util.JsonUtil;
import com.cjhme.common.util.RequestParamToObjectUtil;
import com.cjhme.common.util.UUIDUtil;
import com.cjhme.system.impl.service.menu.MenuService;
import com.cjhme.web.base.action.SysBaseAction;


/**
 * 
 * <p>
 * Title: MenuAction.java
 * </p>
 * Description: 菜单
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/menu")
public class MenuAction extends SysBaseAction {

	Log LOGGER = LogFactory.getLog(MenuAction.class);

	private MenuService menuService;

	
	/**
	 * 初始菜单管理数据
	 * 
	 * @param request
	 * @return
	 */
	// todo
	@RequestMapping(value = "/initMenuManageData", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void initMenuManageData(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			if (null != userBean && !"".equals(userBean) && !"null".equals(userBean) && null != parameter) {
				Map<String, Object> map = new HashMap<String, Object>();

				parameter.put("userId", userBean.getUserId());
				parameter.put("busniessMark", BusniessMarkConstants.MENU_MANAGE);
				List<ButtonBean> buttonList = this.getBusniessBtn(parameter);
				map.put("buttonList", buttonList);

				String menus = this.menuService.queryMenuList(CreateTreeUtil.STATE_OPEN, CreateTreeUtil.STATE_OPEN);
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				
				map.put("menuList", JsonUtil.jsonStrToList(menus, Map.class));

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("初始菜单管理数据成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始菜单管理数据未成功！");
				LOGGER.error("初始菜单管理数据未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始菜单管理数据失败！");
			e.printStackTrace();
			LOGGER.error("初始菜单管理数据失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}

	
	/**
	 * 查询菜单信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/queryMenuByMenuId", method = RequestMethod.GET)
	public void queryMenuByMenuId(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			String menuId = String.valueOf(request.getParameter("menuId"));
			if (!StringUtils.isEmpty(menuId)) {

				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("menuId", menuId);
				MenuBean menuBean = this.menuService.queryMenuByMenuId(parameter);

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("menuBean", menuBean);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("查询菜单信息成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("查询菜单信息未成功！");
				LOGGER.error("查询菜单信息未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("查询菜单信息失败！");
			e.printStackTrace();
			LOGGER.error("查询菜单信息失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}
	
	
	

	/**
	 * 查询菜单列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryMenuList", method = RequestMethod.POST)
	public void queryMenuList(HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			 String menus=this.menuService.queryMenuList(CreateTreeUtil.STATE_OPEN, CreateTreeUtil.STATE_OPEN);
			 resultData.setResultType(CommonConstants.RESULT_SUCCESS);
			 resultData.setJsonArrayResultData(menus);
			 resultData.setResultMsg("查询菜单列表成功");
			 LOGGER.info("查询菜单列表成功！");
		}catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("查询菜单列表失败！");
			e.printStackTrace();
			LOGGER.error("查询菜单列表失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	
	
	/**
	 * 根据条件查询菜单
	 * 
	 * @param orgCode
	 * @param orgName
	 * @param orgSortName
	 * @return
	 */
	@RequestMapping(value = "/queryMenuByCondition", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void queryMenuByCondition(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if (null != parameter && !"".equals(parameter)) {
				Map<String, Object> map = new HashMap<String, Object>();

				String menus = this.menuService.queryMenuByCondition(parameter);
				map.put("menuList", JsonUtil.jsonStrToList(menus, Map.class));

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				resultData.setResultMsg("根据条件查询菜单成功");
				LOGGER.info("根据条件查询菜单成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("根据条件查询菜单未成功！");
				LOGGER.error("根据条件查询菜单未成功！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("根据条件查询菜单失败！");
			e.printStackTrace();
			LOGGER.error("根据条件查询菜单失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}
	
	/**
	 * 初始化菜单添加数据
	 * 
	 * @return
	 */
	// todo
	@RequestMapping(value = "/initMenuAddData", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void initMenuAddData(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if (!StringUtils.isEmpty(parameter)) {
				Map<String, Object> map = new HashMap<String, Object>();

				String menus = this.menuService.queryMenuList(CreateTreeUtil.STATE_CLOSED, CreateTreeUtil.STATE_CLOSED);
				map.put("menuList", JsonUtil.jsonStrToList(menus, Map.class));
				
				if(!StringUtils.isEmpty(parameter.get("menuId"))){
					MenuBean menuData = this.menuService.queryMenuByMenuId(parameter);
					map.put("menuData", menuData);
                }

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("初始化菜单添加数据成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始化菜单添加数据未成功！");
				LOGGER.error("初始化菜单添加数据未成功");
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始化菜单添加数据失败！");
			e.printStackTrace();
			LOGGER.error("初始化菜单添加数据失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}
	
	/**
	 * 保存菜单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveMenu", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveMenu(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);

			// 检测是否存在
			int isExists = this.menuService.queryMenuIsExists(parameter);
			if (isExists > 0) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("编号或菜单名称已存在！");
				LOGGER.info("编号或菜单名称已存在！");
			} else {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				
				int result=0;
				if(StringUtils.isEmpty(parameter.get("menuId"))){
					parameter.put("createUserId", userBean.getUserId());
					parameter.put("menuId", UUIDUtil.createUUID32());
					result = this.menuService.saveMenu(parameter);
				}else{
					parameter.put("updateUserId", userBean.getUserId());
					result = this.menuService.updateMenuByMenuId(parameter);
				}
				
				
				if (result > 0) {
					String menus = this.menuService.queryMenuList(CreateTreeUtil.STATE_CLOSED, CreateTreeUtil.STATE_CLOSED);
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultData(JsonUtil.jsonStrToList(menus, Map.class));
					resultData.setResultMsg("保存菜单成功！");
					LOGGER.info("保存菜单成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("保存菜单未成功！");
					LOGGER.info("保存菜单未成功！");
				}
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("保存菜单失败！");
			e.printStackTrace();
			LOGGER.error("保存菜单失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}
	
	
	
	
	/**
	 * 根据ids删除菜单
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteMenuByIds", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void deleteMenuByIds(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if (!StringUtils.isEmpty(parameter)) {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("updateUserId", userBean.getUserId());

				int result = this.menuService.deleteMenuByIds(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("删除菜单成功！");
					LOGGER.info("根据ids删除菜单成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("删除菜单未成功！");
					LOGGER.info("根据ids删除菜单未成功！");
				}
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("删除菜单失败！");
			e.printStackTrace();
			LOGGER.error("根据ids删除菜单失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}
	

	
	/**
	 * 根据id移动菜单序号(-1失败，1成功，-2已到顶、已到底)
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/updateMenuSortNumById", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void updateMenuSortNumById(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			parameter.put("updateUserId", userBean.getUserId());
			LOGGER.info(" 根据id移动菜单序号成功！");
			int result = this.menuService.updateMenuSortNumById(parameter);
			if (result == 1) {
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultMsg("移动菜单序号成功！");
				LOGGER.info("根据id移动菜单序号成功！");
			} else if (result == -2) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("移动菜单序号已到顶或已到底！");
				LOGGER.info("根据id移动菜单序号已到顶或已到底！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_ERROR);
				resultData.setResultMsg("移动菜单序号失败！");
				LOGGER.info("根据id移动菜单序号失败！");
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("移动菜单序号失败！");
			e.printStackTrace();
			LOGGER.error("根据id移动菜单序号失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	public MenuService getMenuService() {
		return menuService;
	}

	@Resource(name="system.menuService")
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

}
