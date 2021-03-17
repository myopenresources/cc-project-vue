package com.cjhme.system.impl.dao.log.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cjhme.common.model.base.DataPaging;
import com.cjhme.system.impl.base.dao.BaseDao;
import com.cjhme.system.impl.dao.log.LoginLogDao;

/**
 * 
 * <p>
 * Title: LoginLogDaoImpl.java
 * </p>
 * Description: 登录日志
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
@Repository("system.loginLogDao")
public class LoginLogDaoImpl extends BaseDao implements LoginLogDao {


	/**
	 * 添加登录日志
	 * 
	 * @param parameter
	 * @return
	 */
	public int saveLoginLog(Map<String, Object> parameter) {
		return this.getSqlSessionTemplate().insert("com.cjhme.system.core.dao.log.LoginLogDao.saveLoginLog", parameter);
	}

	/**
	 * 根据条件分页查询登录日志
	 * 
	 * @param parameter
	 * @return
	 */
	public DataPaging<Object> queryLoginLogByConditionPaging(DataPaging<Object> pageParameter) {
		return this.selectPaging("com.cjhme.system.core.dao.log.LoginLogDao.queryLoginLogByConditionPaging", pageParameter);
	}

}
