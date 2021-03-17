package com.cjhme.web.action.button;

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
import com.cjhme.common.model.button.ButtonBean;
import com.cjhme.common.model.user.UserBean;
import com.cjhme.common.util.RequestParamToObjectUtil;
import com.cjhme.common.util.UUIDUtil;
import com.cjhme.system.impl.service.button.ButtonService;
import com.cjhme.web.base.action.SysBaseAction;

/**
 * 
 * <p>
 * Title: ButtonAction.java
 * </p>
 * Description: 按钮
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/button")
public class ButtonAction extends SysBaseAction {

	Log LOGGER = LogFactory.getLog(ButtonAction.class);

	private ButtonService buttonService;
	
	
	/**
	 * 初始按钮管理数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/initButtonManageData", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void initButtonManageData(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			if (null != userBean && !"".equals(userBean) && !"null".equals(userBean) && null != parameter) {
				Map<String, Object> map = new HashMap<String, Object>();
				// 绑定数据字典
				this.bindDataDicParam(map, new String[] {DataDicConstants.QUERY_STATUS_TYPE, DataDicConstants.STATUS_TYPE, DataDicConstants.BUSNIESS_MARK_TYPE, DataDicConstants.BUTTON_LAYOUT_MARK_TYPE });

				parameter.put("userId", userBean.getUserId());
				parameter.put("busniessMark", BusniessMarkConstants.BTN_MANAGE);
				List<ButtonBean> buttonList = this.getBusniessBtn(parameter);
				map.put("buttonList", buttonList);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("初始按钮管理数据成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始按钮管理数据未成功！");
				LOGGER.error("初始按钮管理数据未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始按钮管理数据失败！");
			e.printStackTrace();
			LOGGER.error("初始按钮管理数据失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}

	/**
	 * 根据条件分页查询按钮
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryButtonByConditionPaging", method = RequestMethod.POST)
	public void queryButtonByConditionPaging(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			DataPaging<Object> result = this.buttonService.queryButtonByConditionPaging(RequestParamToObjectUtil.requestParamParseToPageMap(request));
			if (null != result) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("rows", result.getRows());
				map.put("total", result.getTotal());

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("根据条件分页查询按钮成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("根据条件分页查询按钮未成功！");
				LOGGER.error("根据条件分页查询按钮未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("根据条件分页查询按钮失败！");
			e.printStackTrace();
			LOGGER.error("根据条件分页查询按钮失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}
	


	/**
	 * 初始化按钮数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/initButtonAddData", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void initButtonAddData(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if (!StringUtils.isEmpty(parameter)) {
				Map<String, Object> map = new HashMap<String, Object>();

				// 绑定数据字典
				this.bindDataDicParam(map, new String[] { DataDicConstants.BUTTON_CLS_TYPE,DataDicConstants.STATUS_TYPE,DataDicConstants.BUSNIESS_MARK_TYPE, DataDicConstants.BUTTON_LAYOUT_MARK_TYPE});

				if (!StringUtils.isEmpty(parameter.get("buttonId"))) {
					ButtonBean buttonData = this.buttonService.queryButtonById(parameter);
					map.put("buttonData", buttonData);
				}
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("初始化按钮数据成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始化按钮数据未成功！");
				LOGGER.error("初始化按钮数据未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始化按钮数据失败！");
			e.printStackTrace();
			LOGGER.error("初始化按钮数据失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}
	
	
	/**
	 * 保存按钮
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveButton", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveButton(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);

			// 检测是否存在
			int isExists = this.buttonService.queryButtonIsExists(parameter);
			if (isExists > 0) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("按钮已存在！");
				LOGGER.info("按钮已存在！");
			} else {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);

				int result = 0;
				if (StringUtils.isEmpty(parameter.get("buttonId"))) {
					parameter.put("createUserId", userBean.getUserId());
					parameter.put("buttonId", UUIDUtil.createUUID32());
					result = this.buttonService.saveButton(parameter);
				} else {
					parameter.put("updateUserId", userBean.getUserId());
					result = this.buttonService.updateButtonById(parameter);
				}

				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("保存按钮成功！");
					LOGGER.info("保存按钮成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("保存按钮未成功！");
					LOGGER.info("保存按钮未成功！");
				}
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("保存按钮失败！");
			e.printStackTrace();
			LOGGER.error("保存按钮失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}
	


	/**
	 * 根据ids删除按钮
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteButtonByIds", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void deleteButtonByIds(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			String idsStr = parameter.get("ids").toString();
			if (!StringUtils.isEmpty(parameter) && !StringUtils.isEmpty(idsStr)) {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("updateUserId", userBean.getUserId());
				int result = this.buttonService.deleteButtonByIds(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("删除按钮成功！");
					LOGGER.info("根据ids删除按钮成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("删除按钮未成功！");
					LOGGER.info("根据ids删除按钮未成功！");
				}
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("删除按钮失败！");
			e.printStackTrace();
			LOGGER.error("根据ids删除按钮失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}
	
	
	/**
	 * 查询按钮信息
	 * 
	 * @param request
	 * @return
	 */
	// todo
	@RequestMapping(value = "/queryButtonById", method = RequestMethod.GET)
	public void queryButtonById(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			String buttonId = String.valueOf(request.getParameter("buttonId"));
			if (!StringUtils.isEmpty(buttonId)) {

				Map<String, Object> map = new HashMap<String, Object>();

				// 绑定数据字典
				this.bindDataDicParam(map, new String[] {DataDicConstants.BUTTON_CLS_TYPE, DataDicConstants.STATUS_TYPE,DataDicConstants.BUSNIESS_MARK_TYPE, DataDicConstants.BUTTON_LAYOUT_MARK_TYPE});

				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("buttonId", buttonId);
				ButtonBean buttonData = this.buttonService.queryButtonById(parameter);
				map.put("buttonData", buttonData);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("查询按钮信息成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("查询按钮信息未成功！");
				LOGGER.error("查询按钮信息未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("查询按钮信息失败！");
			e.printStackTrace();
			LOGGER.error("查询按钮信息失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}
	

	

	public ButtonService getButtonService() {
		return buttonService;
	}

	@Resource(name="system.buttonService")
	public void setButtonService(ButtonService buttonService) {
		this.buttonService = buttonService;
	}



}
