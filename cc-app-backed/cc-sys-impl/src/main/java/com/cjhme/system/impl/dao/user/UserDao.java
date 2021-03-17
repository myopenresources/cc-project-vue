package com.cjhme.system.impl.dao.user;

import java.util.List;
import java.util.Map;

import com.cjhme.common.model.base.DataPaging;
import com.cjhme.common.model.user.UserBean;
import com.cjhme.common.model.user.UserDeptMappingBean;
import com.cjhme.common.model.user.UserRoleMappingBean;

/**
 * 
 * <p>  
 * Title: UserDao.java 
 * </p>  
 * Description: 用户
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
public interface UserDao {
	
	/**
	 * 用户登录
	 * @param parameter
	 * @return
	 */
	public UserBean login(Map<String,Object> parameter);
	
	/**
	 * 判断用户是否存在 
	 * @param parameter
	 * @return
	 */
	public int queryUserIsExists(Map<String,Object> parameter);
	
    /**
     * 添加用户 
     * @param parameter
     * @return
     */
	public int saveUser(Map<String,Object> parameter);
	
	/**
	 * 根据条件分页查询用户
	 * @param pageParameter
	 * @return
	 */
	public DataPaging<Object> queryUserByConditionPaging(DataPaging<Object> pageParameter);
	
	/**
	 * 根据id查询用户
	 * @param parameter
	 * @return
	 */
	public UserBean queryUserById(Map<String,Object> parameter);
	
	/**
	 * 根据id修改用户 
	 * @param parameter
	 * @return
	 */
	public int updateUserById(Map<String,Object> parameter);
	
	/**
	 * 根据id修改密码 
	 * @param parameter
	 * @return
	 */
	public int updateUserPasswordById(Map<String,Object> parameter);
	
	/**
	 * 根据ids重置用户密码
	 * @param parameter
	 * @return
	 */
	public int updateUserPwdByIds(Map<String,Object> parameter);
	
	/**
	 * 根据ids删除用户
	 * @param parameter
	 * @return
	 */
	public int deleteUserByIds(Map<String,Object> parameter);
	
	/**
	 * 根据用户id查询用户角色映射
	 * @param parameter
	 * @return
	 */
	public List<UserRoleMappingBean> queryUserRoleMappingById(Map<String,Object> parameter);
	
	/**
	 * 根据用户id删除用户角色映射
	 * @param parameter
	 * @return
	 */
	public int deleteUserRoleMappingById(Map<String,Object> parameter);
	
	/**
	 * 根据用户ids删除用户角色映射
	 * @param parameter
	 * @return
	 */
	public int deleteUserRoleMappingByIds(Map<String,Object> parameter);
	
	
	
	/**
	 * 保存用户角色映射
	 * @param parameter
	 * @return
	 */
	public int saveUserRoleMapping(Map<String,Object> parameter);
	
	/**
	 * 根据角色id查询用户
	 * @param parameter
	 * @return
	 */
	public List<UserBean> queryUserByRoleId(Map<String,Object> parameter);
	
	/**
	 * 根据用户id查询用户部门映射
	 * @param parameter
	 * @return
	 */
	public List<UserDeptMappingBean> queryUserDeptMappingById(Map<String,Object> parameter);
	
	/**
	 * 根据用户id删除用户部门映射
	 * @param parameter
	 * @return
	 */
	public int deleteUserDeptMappingById(Map<String,Object> parameter);
	
	/**
	 * 根据用户ids删除用户部门映射
	 * @param parameter
	 * @return
	 */
	public int deleteUserDeptMappingByIds(Map<String,Object> parameter);
	
	/**
	 * 保存用户部门映射 
	 * @param parameter
	 * @return
	 */
	public int saveUserDeptMapping(Map<String,Object> parameter);
	
	/**
	 * 根据部门id查询用户
	 * @param parameter
	 * @return
	 */
	public List<UserBean> queryUserByDeptId(Map<String,Object> parameter);
	
	/**
	 * 查询用户列表
	 * @param pageParameter
	 * @return
	 */
	public List<UserBean> queryUserList(Map<String,Object> parameter);
	
	
	
}
