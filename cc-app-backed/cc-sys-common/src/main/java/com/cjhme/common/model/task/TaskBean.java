package com.cjhme.common.model.task;

import com.cjhme.common.model.base.BaseBean;

/**
 * 
 * <p>
 * Title: TaskBean.java
 * </p>
 * Description: 待办
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public class TaskBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String taskId;

	/**
	 * 业务标识
	 */
	private String busniessMark;

	/**
	 * 业务id
	 */
	private String busniessId;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * url
	 */
	private String taskUrl;

	/**
	 * 待办参数
	 */
	private String taskParameter;

	/**
	 * 处理人id
	 */
	private String processUserId;

	/**
	 * 处理人
	 */
	private String processUser;

	/**
	 * 待办条数
	 */
	private String taskCount;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}


	public String getBusniessId() {
		return busniessId;
	}

	public void setBusniessId(String busniessId) {
		this.busniessId = busniessId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTaskUrl() {
		return taskUrl;
	}

	public void setTaskUrl(String taskUrl) {
		this.taskUrl = taskUrl;
	}

	public String getTaskParameter() {
		return taskParameter;
	}

	public void setTaskParameter(String taskParameter) {
		this.taskParameter = taskParameter;
	}

	public String getProcessUserId() {
		return processUserId;
	}

	public void setProcessUserId(String processUserId) {
		this.processUserId = processUserId;
	}

	public String getProcessUser() {
		return processUser;
	}

	public void setProcessUser(String processUser) {
		this.processUser = processUser;
	}

	public String getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(String taskCount) {
		this.taskCount = taskCount;
	}

	public String getBusniessMark() {
		return busniessMark;
	}

	public void setBusniessMark(String busniessMark) {
		this.busniessMark = busniessMark;
	}

	
}
