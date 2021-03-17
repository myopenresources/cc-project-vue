package com.cjhme.web.action.notice;

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
import com.cjhme.common.constant.NoticeConstant;
import com.cjhme.common.model.base.DataPaging;
import com.cjhme.common.model.base.ResultData;
import com.cjhme.common.model.button.ButtonBean;
import com.cjhme.common.model.notice.NoticeBean;
import com.cjhme.common.model.user.UserBean;
import com.cjhme.common.util.RequestParamToObjectUtil;
import com.cjhme.common.util.UUIDUtil;
import com.cjhme.system.impl.service.notice.NoticeService;
import com.cjhme.web.base.action.SysBaseAction;

/**
 * 
 * <p>
 * Title: NoticeAction.java
 * </p>
 * Description: 公告
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/notice")
public class NoticeAction extends SysBaseAction {

	Log LOGGER = LogFactory.getLog(NoticeAction.class);

	private NoticeService noticeService;

	/**
	 * 初始系统公告管理数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/initSysNoticeManageData", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void initSysNoticeManageData(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			if (null != userBean && !"".equals(userBean) && !"null".equals(userBean) && null != parameter) {
				Map<String, Object> map = new HashMap<String, Object>();
				// 绑定数据字典
				this.bindDataDicParam(map, new String[] { DataDicConstants.QUERY_FORM_STATUS_TYPE, DataDicConstants.FORM_STATUS_TYPE });

				parameter.put("userId", userBean.getUserId());
				parameter.put("busniessMark", BusniessMarkConstants.SYS_NOTICE_MANAGE);
				List<ButtonBean> buttonList = this.getBusniessBtn(parameter);
				map.put("buttonList", buttonList);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("初始系统公告管理数据成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始系统公告管理数据未成功！");
				LOGGER.error("初始系统公告管理数据未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始系统公告管理数据失败！");
			e.printStackTrace();
			LOGGER.error("初始系统公告管理数据失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}

	/**
	 * 根据条件分页查询系统公告
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/querySysNoticeByConditionPaging", method = RequestMethod.POST)
	public void querySysNoticeByConditionPaging(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			DataPaging<Object> result = this.noticeService.querySysNoticeByConditionPaging(RequestParamToObjectUtil.requestParamParseToPageMap(request));
			if (null != result) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("rows", result.getRows());
				map.put("total", result.getTotal());

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("根据条件分页查询系统公告成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("根据条件分页查询系统公告未成功！");
				LOGGER.error("根据条件分页查询系统公告未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("根据条件分页查询系统公告失败！");
			e.printStackTrace();
			LOGGER.error("根据条件分页查询系统公告失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}

	/**
	 * 初始化系统公告数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/initSysNoticeAddData", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void initSysNoticeAddData(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if (!StringUtils.isEmpty(parameter)) {
				Map<String, Object> map = new HashMap<String, Object>();

				// 绑定数据字典
				this.bindDataDicParam(map, new String[] { DataDicConstants.FORM_STATUS_TYPE });

				if (!StringUtils.isEmpty(parameter.get("noticeId"))) {
					NoticeBean noticeData = this.noticeService.querySysNoticeById(parameter);
					map.put("noticeData", noticeData);
				}
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("初始化系统公告数据成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始化系统公告数据未成功！");
				LOGGER.error("初始化系统公告数据未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始化系统公告数据失败！");
			e.printStackTrace();
			LOGGER.error("初始化系统公告数据失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}

	/**
	 * 保存系统公告
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveSysNotice", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveSysNotice(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);

			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);

			int result = 0;
			if (StringUtils.isEmpty(parameter.get("noticeId"))) {
				parameter.put("createUserId", userBean.getUserId());
				parameter.put("noticeType", NoticeConstant.SYS_NOTICE_TYPE);
				parameter.put("noticeId", UUIDUtil.createUUID32());

				result = this.noticeService.saveSysNotice(parameter);
			} else {
				parameter.put("updateUserId", userBean.getUserId());
				result = this.noticeService.updateSysNoticeById(parameter);
			}

			if (result > 0) {
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultMsg("保存系统公告成功！");
				LOGGER.info("保存系统公告成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("保存系统公告未成功！");
				LOGGER.info("保存系统公告未成功！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("保存系统公告失败！");
			e.printStackTrace();
			LOGGER.error("保存系统公告失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}
	
	/**
	 * 根据ids删除系统公告
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteSysNoticeByIds", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void deleteSysNoticeByIds(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			String idsStr = parameter.get("ids").toString();
			if (!StringUtils.isEmpty(parameter) && !StringUtils.isEmpty(idsStr)) {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("updateUserId", userBean.getUserId());
				int result = this.noticeService.deleteSysNoticeByIds(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("删除系统公告成功！");
					LOGGER.info("根据ids删除系统公告成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("删除系统公告未成功！");
					LOGGER.info("根据ids删除系统公告未成功！");
				}
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("删除系统公告失败！");
			e.printStackTrace();
			LOGGER.error("根据ids删除系统公告失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}
	
	/**
	 * 查询系统公告信息
	 * 
	 * @param request
	 * @return
	 */
	// todo
	@RequestMapping(value = "/querySysNoticeById", method = RequestMethod.GET)
	public void querySysNoticeById(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			String noticeId = String.valueOf(request.getParameter("noticeId"));
			if (!StringUtils.isEmpty(noticeId)) {

				Map<String, Object> map = new HashMap<String, Object>();

				// 绑定数据字典
				this.bindDataDicParam(map, new String[] { DataDicConstants.FORM_STATUS_TYPE });

				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("noticeId", noticeId);
				NoticeBean noticeData = this.noticeService.querySysNoticeById(parameter);
				map.put("noticeData", noticeData);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("查询系统公告信息成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("查询系统公告信息未成功！");
				LOGGER.error("查询系统公告信息未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("查询系统公告信息失败！");
			e.printStackTrace();
			LOGGER.error("查询系统公告信息失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}
	
	/**
	 * 查询用户系统公告信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryUserSysNoticeById", method = RequestMethod.GET)
	public void queryUserSysNoticeById(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			String noticeId = String.valueOf(request.getParameter("noticeId"));
			if (!StringUtils.isEmpty(noticeId)) {

				Map<String, Object> map = new HashMap<String, Object>();

				// 绑定数据字典
				this.bindDataDicParam(map, new String[] { DataDicConstants.FORM_STATUS_TYPE });

				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("noticeId", noticeId);
				NoticeBean noticeData = this.noticeService.querySysNoticeById(parameter);
				map.put("noticeData", noticeData);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("查询系统公告信息成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("查询系统公告信息未成功！");
				LOGGER.error("查询系统公告信息未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("查询系统公告信息失败！");
			e.printStackTrace();
			LOGGER.error("查询系统公告信息失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}
	
	/**
	 * 初始用户系统公告管理数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/initUserSysNoticeManageData", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void initUserSysNoticeManageData(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			if (null != userBean && !"".equals(userBean) && !"null".equals(userBean) && null != parameter) {
				Map<String, Object> map = new HashMap<String, Object>();
				// 绑定数据字典
				this.bindDataDicParam(map, new String[] {DataDicConstants.FORM_STATUS_TYPE });

				parameter.put("userId", userBean.getUserId());
				parameter.put("busniessMark", BusniessMarkConstants.USER_NOTICE_MANAGE);
				List<ButtonBean> buttonList = this.getBusniessBtn(parameter);
				map.put("buttonList", buttonList);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("初始用户系统公告管理数据成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始用户系统公告管理数据未成功！");
				LOGGER.error("初始用户系统公告管理数据未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始用户系统公告管理数据失败！");
			e.printStackTrace();
			LOGGER.error("初始用户系统公告管理数据失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}

	/**
	 * 根据条件分页查询用户系统公告
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryUserSysNoticeByConditionPaging", method = RequestMethod.POST)
	public void queryUserSysNoticeByConditionPaging(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			DataPaging<Object> result = this.noticeService.queryUserNoticeByConditionPaging(RequestParamToObjectUtil.requestParamParseToPageMap(request));
			if (null != result) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("rows", result.getRows());
				map.put("total", result.getTotal());

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("根据条件分页查询用户系统公告成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("根据条件分页查询用户系统公告未成功！");
				LOGGER.error("根据条件分页查询用户系统公告未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("根据条件分页查询用户系统公告失败！");
			e.printStackTrace();
			LOGGER.error("根据条件分页查询用户系统公告失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}


	public NoticeService getNoticeService() {
		return noticeService;
	}

	@Resource(name="system.noticeService")
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

}
