package com.cjhme.web.action.datapermissions;

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
import com.cjhme.common.model.datapermissions.DataPermissionsBean;
import com.cjhme.common.model.user.UserBean;
import com.cjhme.common.util.RequestParamToObjectUtil;
import com.cjhme.common.util.UUIDUtil;
import com.cjhme.system.impl.service.datapermissions.DataPermissionsService;
import com.cjhme.web.base.action.SysBaseAction;

/**
 * 
 * <p>  
 * Title: DataPermissionsAction.java 
 * </p>  
 * Description: 数据权限
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/dataPermissions")
public class DataPermissionsAction extends SysBaseAction  {

	Log LOGGER = LogFactory.getLog(DataPermissionsAction.class);

	private DataPermissionsService dataPermissionsService;
	

	
	/**
	 * 初始数据权限管理数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/initDataPermissionsManageData", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void initDataPermissionsManageData(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			if (null != userBean && null != parameter) {
				Map<String, Object> map = new HashMap<String, Object>();
				// 绑定数据字典
				this.bindDataDicParam(map, new String[] {DataDicConstants.STATUS_TYPE,DataDicConstants.DATA_PERMIT_CATEGORY,DataDicConstants.DATA_PERMIT_PROCESSOR});

				parameter.put("userId", userBean.getUserId());
				parameter.put("busniessMark", BusniessMarkConstants.DATA_PERMISSIONS_MANAGE);
				List<ButtonBean> buttonList = this.getBusniessBtn(parameter);
				map.put("buttonList", buttonList);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("初始数据权限管理数据成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始数据权限管理数据未成功！");
				LOGGER.error("初始数据权限管理数据未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始数据权限管理数据失败！");
			e.printStackTrace();
			LOGGER.error("初始数据权限管理数据失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}
	
	/**
	 * 根据条件分页查询数据权限
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryDataPermissionsByConditionPaging", method = RequestMethod.POST)
	public void queryDataPermissionsByConditionPaging(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			DataPaging<Object> result = this.dataPermissionsService.queryDataPermissionsByConditionPaging(RequestParamToObjectUtil.requestParamParseToPageMap(request));
			if (null != result) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("rows", result.getRows());
				map.put("total", result.getTotal());

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("根据条件分页查询数据权限成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("根据条件分页查询数据权限未成功！");
				LOGGER.error("根据条件分页查询数据权限未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("根据条件分页查询数据权限失败！");
			e.printStackTrace();
			LOGGER.error("根据条件分页查询数据权限失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}
	
	

	
	
	/**
	 * 初始化数据权限数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/initDataPermissionsAddData", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void initDataPermissionsAddData(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if (!StringUtils.isEmpty(parameter)) {
				Map<String, Object> map = new HashMap<String, Object>();

				// 绑定数据字典
				this.bindDataDicParam(map, new String[] { DataDicConstants.STATUS_TYPE,DataDicConstants.DATA_PERMIT_CATEGORY,DataDicConstants.DATA_PERMIT_PROCESSOR});

				if (!StringUtils.isEmpty(parameter.get("dataPermissionsId"))) {
					DataPermissionsBean dataPermissionsData = this.dataPermissionsService.queryDataPermissionsById(parameter);
					map.put("dataPermissionsData", dataPermissionsData);
				}
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("初始化数据权限数据成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始化数据权限数据未成功！");
				LOGGER.error("初始化数据权限数据未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始化数据权限数据失败！");
			e.printStackTrace();
			LOGGER.error("初始化数据权限数据失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}
	

	/**
	 * 保存数据权限
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveDataPermissions", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveDataPermissions(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);

			// 检测是否存在
			int isExists = this.dataPermissionsService.queryDataPermissionsIsExists(parameter);
			if (isExists > 0) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("数据权限已存在！");
				LOGGER.info("数据权限已存在！");
			} else {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);

				int result = 0;
				if (StringUtils.isEmpty(parameter.get("dataPermissionsId"))) {
					parameter.put("createUserId", userBean.getUserId());
					parameter.put("dataPermissionsId", UUIDUtil.createUUID32());
					result = this.dataPermissionsService.saveDataPermissions(parameter);
				} else {
					parameter.put("updateUserId", userBean.getUserId());
					result = this.dataPermissionsService.updateDataPermissionsById(parameter);
				}

				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("保存数据权限成功！");
					LOGGER.info("保存数据权限成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("保存数据权限未成功！");
					LOGGER.info("保存数据权限未成功！");
				}
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("保存数据权限失败！");
			e.printStackTrace();
			LOGGER.error("保存数据权限失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}
	
	
	/**
	 * 根据ids删除数据权限
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteDataPermissionsByIds", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void deleteDataPermissionsByIds(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			String idsStr = parameter.get("ids").toString();
			if (!StringUtils.isEmpty(parameter) && !StringUtils.isEmpty(idsStr)) {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("updateUserId", userBean.getUserId());
				int result = this.dataPermissionsService.deleteDataPermissionsByIds(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("删除数据权限成功！");
					LOGGER.info("根据ids删除数据权限成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("删除数据权限未成功！");
					LOGGER.info("根据ids删除数据权限未成功！");
				}
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("删除数据权限失败！");
			e.printStackTrace();
			LOGGER.error("根据ids删除数据权限失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}
	
	/**
	 * 查询数据权限信息
	 * 
	 * @param request
	 * @return
	 */
	// todo
	@RequestMapping(value = "/queryDataPermissionsById", method = RequestMethod.GET)
	public void queryDataPermissionsById(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			String dataPermissionsId = String.valueOf(request.getParameter("dataPermissionsId"));
			if (!StringUtils.isEmpty(dataPermissionsId)) {

				Map<String, Object> map = new HashMap<String, Object>();

				// 绑定数据字典
				this.bindDataDicParam(map, new String[] { DataDicConstants.STATUS_TYPE,DataDicConstants.DATA_PERMIT_CATEGORY,DataDicConstants.DATA_PERMIT_PROCESSOR});

				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("dataPermissionsId", dataPermissionsId);
				DataPermissionsBean dataPermissionsData = this.dataPermissionsService.queryDataPermissionsById(parameter);
				map.put("dataPermissionsData", dataPermissionsData);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("查询数据权限信息成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("查询数据权限信息未成功！");
				LOGGER.error("查询数据权限信息未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("查询数据权限信息失败！");
			e.printStackTrace();
			LOGGER.error("查询数据权限信息失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}

	public DataPermissionsService getDataPermissionsService() {
		return dataPermissionsService;
	}

	@Resource(name="system.dataPermissionsService")
	public void setDataPermissionsService(DataPermissionsService dataPermissionsService) {
		this.dataPermissionsService = dataPermissionsService;
	}


	

}
