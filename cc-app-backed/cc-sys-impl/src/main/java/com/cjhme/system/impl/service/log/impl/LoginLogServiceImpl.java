package com.cjhme.system.impl.service.log.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjhme.common.model.base.DataPaging;
import com.cjhme.system.impl.dao.log.LoginLogDao;
import com.cjhme.system.impl.service.log.LoginLogService;

/**
 * 
 * <p>  
 * Title: LoginLogServiceImpl.java 
 * </p>  
 * Description: 登录日志
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
@Service("system.loginLogService")
public class LoginLogServiceImpl implements LoginLogService {

	private LoginLogDao loginLogDao;


	/**
	 * 添加登录日志 
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int saveLoginLog(Map<String, Object> parameter){
		return this.loginLogDao.saveLoginLog(parameter);
	}
	
	/**
	 * 根据条件分页查询登录日志
	 * 
	 * @param parameter
	 * @return
	 */
	public DataPaging<Object> queryLoginLogByConditionPaging(DataPaging<Object> pageParameter){
		return this.loginLogDao.queryLoginLogByConditionPaging(pageParameter);
	}
	
	public LoginLogDao getLoginLogDao() {
		return loginLogDao;
	}

	@Resource(name="system.loginLogDao")
	public void setLoginLogDao(LoginLogDao loginLogDao) {
		this.loginLogDao = loginLogDao;
	}
	
	

}
