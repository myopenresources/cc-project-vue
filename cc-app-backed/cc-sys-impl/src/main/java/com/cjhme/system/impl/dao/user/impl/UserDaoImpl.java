package com.cjhme.system.impl.dao.user.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cjhme.common.model.base.DataPaging;
import com.cjhme.common.model.user.UserBean;
import com.cjhme.common.model.user.UserDeptMappingBean;
import com.cjhme.common.model.user.UserRoleMappingBean;
import com.cjhme.system.impl.base.dao.BaseDao;
import com.cjhme.system.impl.dao.user.UserDao;

/**
 * 用户
 * <p>  
 * Title: UserDaoImpl.java 
 * </p>  
 * Description: 
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
@Repository("system.userDao")
public class UserDaoImpl extends BaseDao implements UserDao {

	/**
	 * 用户登录
	 * @param parameter
	 * @return
	 */
	public UserBean login(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.core.dao.user.UserDao.login", parameter);
	}
	
	/**
	 * 判断用户是否存在 
	 * @param parameter
	 * @return
	 */
	public int queryUserIsExists(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.core.dao.user.UserDao.queryUserIsExists",parameter);
	}
	
    /**
     * 添加用户 
     * @param parameter
     * @return
     */
	public int saveUser(Map<String,Object> parameter){
		 return  this.getSqlSessionTemplate().insert("com.cjhme.system.core.dao.user.UserDao.saveUser",parameter);
	}
	
	/**
	 * 根据条件分页查询用户
	 * @param pageParameter
	 * @return
	 */
	public DataPaging<Object> queryUserByConditionPaging(DataPaging<Object> pageParameter){
		return this.selectPaging("com.cjhme.system.core.dao.user.UserDao.queryUserByConditionPaging", pageParameter);
	}
	
	/**
	 * 根据id查询用户
	 * @param parameter
	 * @return
	 */
	public UserBean queryUserById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.core.dao.user.UserDao.queryUserById",parameter);
	}
	
	/**
	 * 根据id修改用户 
	 * @param parameter
	 * @return
	 */
	public int updateUserById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.core.dao.user.UserDao.updateUserById",parameter);
	}
	
	/**
	 * 根据id修改密码 
	 * @param parameter
	 * @return
	 */
	public int updateUserPasswordById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.core.dao.user.UserDao.updateUserPasswordById",parameter);
	}
	
	/**
	 * 根据ids重置用户密码
	 * @param parameter
	 * @return
	 */
	public int updateUserPwdByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.core.dao.user.UserDao.updateUserPwdByIds",parameter);
	}
	
	/**
	 * 根据ids删除用户
	 * @param parameter
	 * @return
	 */
	public int deleteUserByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.core.dao.user.UserDao.deleteUserByIds",parameter);
	}
	
	/**
	 * 根据用户id查询用户角色映射
	 * @param parameter
	 * @return
	 */
	public List<UserRoleMappingBean> queryUserRoleMappingById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.core.dao.user.UserDao.queryUserRoleMappingById",parameter);
	}
	
	/**
	 * 根据用户id删除用户角色映射
	 * @param parameter
	 * @return
	 */
	public int deleteUserRoleMappingById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.core.dao.user.UserDao.deleteUserRoleMappingById",parameter);
	}
	
	/**
	 * 根据用户ids删除用户角色映射
	 * @param parameter
	 * @return
	 */
	public int deleteUserRoleMappingByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.core.dao.user.UserDao.deleteUserRoleMappingByIds",parameter);
	}
	
	/**
	 * 保存用户角色映射
	 * @param parameter
	 * @return
	 */
	public int saveUserRoleMapping(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().insert("com.cjhme.system.core.dao.user.UserDao.saveUserRoleMapping",parameter);
	}
	
	/**
	 * 根据角色id查询用户
	 * @param parameter
	 * @return
	 */
	public List<UserBean> queryUserByRoleId(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.core.dao.user.UserDao.queryUserByRoleId",parameter);
	}
	
	/**
	 * 根据用户id查询用户部门映射
	 * @param parameter
	 * @return
	 */
	public List<UserDeptMappingBean> queryUserDeptMappingById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.core.dao.user.UserDao.queryUserDeptMappingById", parameter);
	}
	
	/**
	 * 根据用户id删除用户部门映射
	 * @param parameter
	 * @return
	 */
	public int deleteUserDeptMappingById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.core.dao.user.UserDao.deleteUserDeptMappingById",parameter);
	}
	
	/**
	 * 根据用户ids删除用户部门映射
	 * @param parameter
	 * @return
	 */
	public int deleteUserDeptMappingByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.core.dao.user.UserDao.deleteUserDeptMappingByIds",parameter);
	}
	
	/**
	 * 保存用户部门映射 
	 * @param parameter
	 * @return
	 */
	public int saveUserDeptMapping(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().insert("com.cjhme.system.core.dao.user.UserDao.saveUserDeptMapping",parameter);
	}
	
	/**
	 * 根据部门id查询用户
	 * @param parameter
	 * @return
	 */
	public List<UserBean> queryUserByDeptId(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.core.dao.user.UserDao.queryUserByDeptId",parameter);
	}
	
	
	/**
	 * 查询用户列表
	 * @param pageParameter
	 * @return
	 */
	public List<UserBean> queryUserList(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.core.dao.user.UserDao.queryUserList",parameter);
	}
	
	
}
