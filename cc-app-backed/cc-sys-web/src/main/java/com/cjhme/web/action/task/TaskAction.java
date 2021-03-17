package com.cjhme.web.action.task;

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

import com.cjhme.common.constant.BusniessMarkConstants;
import com.cjhme.common.constant.CommonConstants;
import com.cjhme.common.constant.DataDicConstants;
import com.cjhme.common.model.base.DataPaging;
import com.cjhme.common.model.base.ResultData;
import com.cjhme.common.model.button.ButtonBean;
import com.cjhme.common.model.user.UserBean;
import com.cjhme.common.util.RequestParamToObjectUtil;
import com.cjhme.system.impl.service.task.TaskService;
import com.cjhme.web.base.action.SysBaseAction;

/**
 * 
 * <p>
 * Title: TaskAction.java
 * </p>
 * Description: 待办
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/task")
public class TaskAction extends SysBaseAction {

	Log LOGGER = LogFactory.getLog(TaskAction.class);

	private TaskService taskService;

	
	/**
	 * 初始待办管理数据
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/initTaskManageData", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void initTaskManageData(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			if (null != userBean && !"".equals(userBean) && !"null".equals(userBean) && null != parameter) {
				Map<String, Object> map = new HashMap<String, Object>();
				// 绑定数据字典
				this.bindDataDicParam(map, new String[] {DataDicConstants.QUERY_TASK_STATUS_TYPE });

				parameter.put("userId", userBean.getUserId());
				parameter.put("busniessMark", BusniessMarkConstants.USER_TASK_MANAGE);
				List<ButtonBean> buttonList = this.getBusniessBtn(parameter);
				map.put("buttonList", buttonList);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("初始待办管理数据成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始待办管理数据未成功！");
				LOGGER.error("初始待办管理数据未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始待办管理数据失败！");
			e.printStackTrace();
			LOGGER.error("初始待办管理数据失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}
	
	/**
	 * 根据条件分页查询待办
	 * 
	 * @param request
	 * @return
	 */
	// todo
	@RequestMapping(value = "/queryTaskByConditionPaging", method = RequestMethod.POST)
	public void queryTaskByConditionPaging(HttpSession session,HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			DataPaging<Object> parameter = RequestParamToObjectUtil.requestParamParseToPageMap(request);
			parameter.getParameter().put("createUserId", userBean.getUserId());
			
			DataPaging<Object> result = this.taskService.queryTaskByConditionPaging(parameter);
			if (null != result) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("rows", result.getRows());
				map.put("total", result.getTotal());

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("根据条件分页查询待办成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("根据条件分页查询用户未成功！");
				LOGGER.error("根据条件分页查询待办未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("根据条件分页查询待办失败！");
			e.printStackTrace();
			LOGGER.error("根据条件分页查询待办失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}
	

	
	/**
	 * 根据id修改待办状态
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateTaskById", method = RequestMethod.GET)
	public void updateTaskById(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			String taskId = request.getParameter("taskId");
			if (null != taskId && !"".equals(taskId)) {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);

				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("taskId", taskId);
				parameter.put("updateUserId", userBean.getUserId());
				int result= this.taskService.updateTaskById(parameter);
				if(result>0){
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("修改待办状态成功！");
					LOGGER.info("根据id修改待办状态成功！");
				}else{
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("修改待办状态未成功！");
					LOGGER.info("根据id修改待办状态未成功！");
				}
			}else{
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("修改待办状态失败！");
			e.printStackTrace();
			LOGGER.error("根据id修改待办状态失败,错误——>"+e);
		}
		resultData.printJsonData(response);
	}

	public TaskService getTaskService() {
		return taskService;
	}

	@Resource(name="system.taskService")
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}


}
