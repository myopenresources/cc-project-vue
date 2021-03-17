package com.cjhme.system.impl.service.task;

import java.util.Map;

import com.cjhme.common.model.base.DataPaging;


/**
 * 
 * <p>  
 * Title: TaskService.java 
 * </p>  
 * Description: 待办
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
public interface TaskService {

	/**
	 * 根据用户id获得待办列表
	 * @return
	 */
	public Map<String,Object>  queryTaskByUserId(Map<String,Object> parameter);
	
	/**
	 * 根据条件分页查询待办 
	 * @param parameter
	 * @return
	 */
	public DataPaging<Object>  queryTaskByConditionPaging(DataPaging<Object> parameter);
	
	/**
	 * 根据id修改待办状态
	 * @param parameter
	 * @return
	 */
	public int updateTaskById(Map<String,Object> parameter);
}
