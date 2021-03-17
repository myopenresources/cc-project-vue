package com.cjhme.system.impl.dao.task.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cjhme.common.model.base.DataPaging;
import com.cjhme.common.model.task.TaskBean;
import com.cjhme.system.impl.base.dao.BaseDao;
import com.cjhme.system.impl.dao.task.TaskDao;

/**
 * 
 * <p>  
 * Title: TaskDaoImpl.java 
 * </p>  
 * Description: 待办
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
@Repository("system.taskDao")
public class TaskDaoImpl extends BaseDao implements TaskDao {

	/**
	 * 根据用户id获得待办列表
	 * @return
	 */
	public List<TaskBean> queryTaskByUserId(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.core.dao.task.TaskDao.queryTaskByUserId", parameter);
	}
	
	/**
	 * 根据用户id获得待办列表条数
	 * @param parameter
	 * @return
	 */
	public int queryTaskCountByUserId(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.core.dao.task.TaskDao.queryTaskCountByUserId",parameter);
	}
	
	/**
	 * 根据条件分页查询待办 
	 * @param parameter
	 * @return
	 */
	public DataPaging<Object>  queryTaskByConditionPaging(DataPaging<Object> parameter){
		return this.selectPaging("com.cjhme.system.core.dao.task.TaskDao.queryTaskByConditionPaging", parameter);
	}
	
	/**
	 * 根据id修改待办状态
	 * @param parameter
	 * @return
	 */
	public int updateTaskById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.core.dao.task.TaskDao.updateTaskById",parameter);
	}
	
}
