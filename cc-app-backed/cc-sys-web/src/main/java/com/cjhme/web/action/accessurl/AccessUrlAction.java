package com.cjhme.web.action.accessurl;

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
import com.cjhme.common.model.accessurl.AccessUrlBean;
import com.cjhme.common.model.base.DataPaging;
import com.cjhme.common.model.base.ResultData;
import com.cjhme.common.model.button.ButtonBean;
import com.cjhme.common.model.user.UserBean;
import com.cjhme.common.util.RequestParamToObjectUtil;
import com.cjhme.common.util.UUIDUtil;
import com.cjhme.system.impl.service.accessurl.AccessUrlService;
import com.cjhme.web.base.action.SysBaseAction;

/**
 * 
 * <p>  
 * Title: AccessUrlAction.java 
 * </p>  
 * Description: 访问地址
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/accessUrl")
public class AccessUrlAction extends SysBaseAction  {
	
	Log LOGGER = LogFactory.getLog(AccessUrlAction.class);

	private AccessUrlService accessUrlService;
	

	
	/**
	 * 初始访问地址管理数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/initAccessUrlManageData", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void initAccessUrlManageData(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			if (null != userBean && null != parameter) {
				Map<String, Object> map = new HashMap<String, Object>();
				// 绑定数据字典
				this.bindDataDicParam(map, new String[] {DataDicConstants.STATUS_TYPE,DataDicConstants.BUSNIESS_MARK_TYPE});

				parameter.put("userId", userBean.getUserId());
				parameter.put("busniessMark", BusniessMarkConstants.ACCESS_URL_MANAGE);
				List<ButtonBean> buttonList = this.getBusniessBtn(parameter);
				map.put("buttonList", buttonList);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("初始访问地址管理数据成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始访问地址管理数据未成功！");
				LOGGER.error("初始访问地址管理数据未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始访问地址管理数据失败！");
			e.printStackTrace();
			LOGGER.error("初始访问地址管理数据失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}
	
	/**
	 * 根据条件分页查询访问地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAccessUrlByConditionPaging", method = RequestMethod.POST)
	public void queryAccessUrlByConditionPaging(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			DataPaging<Object> result = this.accessUrlService.queryAccessUrlByConditionPaging(RequestParamToObjectUtil.requestParamParseToPageMap(request));
			if (null != result) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("rows", result.getRows());
				map.put("total", result.getTotal());

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("根据条件分页查询访问地址成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("根据条件分页查询访问地址未成功！");
				LOGGER.error("根据条件分页查询访问地址未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("根据条件分页查询访问地址失败！");
			e.printStackTrace();
			LOGGER.error("根据条件分页查询访问地址失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}
	
	

	
	
	/**
	 * 初始化访问地址数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/initAccessUrlAddData", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void initAccessUrlAddData(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if (!StringUtils.isEmpty(parameter)) {
				Map<String, Object> map = new HashMap<String, Object>();

				// 绑定数据字典
				this.bindDataDicParam(map, new String[] { DataDicConstants.STATUS_TYPE,DataDicConstants.BUSNIESS_MARK_TYPE});

				if (!StringUtils.isEmpty(parameter.get("accessUrlId"))) {
					AccessUrlBean accessUrlData = this.accessUrlService.queryAccessUrlById(parameter);
					map.put("accessUrlData", accessUrlData);
				}
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("初始化访问地址数据成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始化访问地址数据未成功！");
				LOGGER.error("初始化访问地址数据未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始化访问地址数据失败！");
			e.printStackTrace();
			LOGGER.error("初始化访问地址数据失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}
	

	/**
	 * 保存访问地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveAccessUrl", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveAccessUrl(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);

			// 检测是否存在
			int isExists = this.accessUrlService.queryAccessUrlIsExists(parameter);
			if (isExists > 0) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("访问地址已存在！");
				LOGGER.info("访问地址已存在！");
			} else {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);

				int result = 0;
				if (StringUtils.isEmpty(parameter.get("accessUrlId"))) {
					parameter.put("createUserId", userBean.getUserId());
					parameter.put("accessUrlId", UUIDUtil.createUUID32());
					result = this.accessUrlService.saveAccessUrl(parameter);
				} else {
					parameter.put("updateUserId", userBean.getUserId());
					result = this.accessUrlService.updateAccessUrlById(parameter);
				}

				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("保存访问地址成功！");
					LOGGER.info("保存访问地址成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("保存访问地址未成功！");
					LOGGER.info("保存访问地址未成功！");
				}
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("保存访问地址失败！");
			e.printStackTrace();
			LOGGER.error("保存访问地址失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}
	
	
	/**
	 * 根据ids删除访问地址
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteAccessUrlByIds", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void deleteAccessUrlByIds(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			String idsStr = parameter.get("ids").toString();
			if (!StringUtils.isEmpty(parameter) && !StringUtils.isEmpty(idsStr)) {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("updateUserId", userBean.getUserId());
				int result = this.accessUrlService.deleteAccessUrlByIds(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("删除访问地址成功！");
					LOGGER.info("根据ids删除访问地址成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("删除访问地址未成功！");
					LOGGER.info("根据ids删除访问地址未成功！");
				}
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("删除访问地址失败！");
			e.printStackTrace();
			LOGGER.error("根据ids删除访问地址失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}
	
	/**
	 * 查询访问地址信息
	 * 
	 * @param request
	 * @return
	 */
	// todo
	@RequestMapping(value = "/queryAccessUrlById", method = RequestMethod.GET)
	public void queryAccessUrlById(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			String accessUrlId = String.valueOf(request.getParameter("accessUrlId"));
			if (!StringUtils.isEmpty(accessUrlId)) {

				Map<String, Object> map = new HashMap<String, Object>();

				// 绑定数据字典
				this.bindDataDicParam(map, new String[] { DataDicConstants.STATUS_TYPE,DataDicConstants.BUSNIESS_MARK_TYPE});

				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("accessUrlId", accessUrlId);
				AccessUrlBean accessUrlData = this.accessUrlService.queryAccessUrlById(parameter);
				map.put("accessUrlData", accessUrlData);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("查询访问地址信息成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("查询访问地址信息未成功！");
				LOGGER.error("查询访问地址信息未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("查询访问地址信息失败！");
			e.printStackTrace();
			LOGGER.error("查询访问地址信息失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}


	public AccessUrlService getAccessUrlService() {
		return accessUrlService;
	}

	@Resource(name="system.accessUrlService")
	public void setAccessUrlService(AccessUrlService accessUrlService) {
		this.accessUrlService = accessUrlService;
	}

	
	
	
}
