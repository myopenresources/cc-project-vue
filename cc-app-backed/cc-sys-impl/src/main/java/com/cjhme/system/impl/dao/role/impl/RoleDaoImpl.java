package com.cjhme.system.impl.dao.role.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cjhme.common.model.base.DataPaging;
import com.cjhme.common.model.base.TreeNodeBean;
import com.cjhme.common.model.role.RoleAccessUrlMappingBean;
import com.cjhme.common.model.role.RoleBean;
import com.cjhme.common.model.role.RoleButtonMappingBean;
import com.cjhme.common.model.role.RoleMenuMappingBean;
import com.cjhme.system.impl.base.dao.BaseDao;
import com.cjhme.system.impl.dao.role.RoleDao;

/**
 * 
 * <p>  
 * Title: RoleDao.java 
 * </p>  
 * Description: 角色
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
@Repository("system.roleDao")
public class RoleDaoImpl extends BaseDao implements RoleDao {

	/**
	 * 根据条件分页查询角色
	 * @param parameter
	 * @return
	 */
	public DataPaging<Object>  queryRoleByConditionPaging(DataPaging<Object> parameter){
		return this.selectPaging("com.cjhme.system.core.dao.role.RoleDao.queryRoleByConditionPaging", parameter);
	}
	
	/**
	 * 判断角色是否存在
	 * @param parameter
	 * @return
	 */
	public int queryRoleIsExists(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.core.dao.role.RoleDao.queryRoleIsExists",parameter);
	}
	
	/**
	 * 添加角色 
	 * @param parameter
	 * @return
	 */
	public int saveRole(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().insert("com.cjhme.system.core.dao.role.RoleDao.saveRole",parameter);
	}
	
	/**
	 * 根据id查询角色
	 * @param parameter
	 * @return
	 */
	public RoleBean queryRoleById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.core.dao.role.RoleDao.queryRoleById",parameter);
	}
	
	/**
	 * 根据id修改角色
	 * @param parameter
	 * @return
	 */
	public int updateRoleById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.core.dao.role.RoleDao.updateRoleById",parameter);
	}
	
	/**
	 *  根据ids删除角色
	 * @param parameter
	 * @return
	 */
	public int deleteRoleByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.core.dao.role.RoleDao.deleteRoleByIds",parameter);
	}
	
	/**
	 * 查询所有角色列表 
	 * @return
	 */
	public List<RoleBean> queryAllRoleList(){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.core.dao.role.RoleDao.queryAllRoleList");
	}
	
	/**
	 * 根据角色查询角色菜单映射
	 * @param parameter
	 * @return
	 */
	public List<RoleMenuMappingBean> queryRoleMenuMappingById(Map<String,Object> parameter){
		return  this.getSqlSessionTemplate().selectList("com.cjhme.system.core.dao.role.RoleDao.queryRoleMenuMappingById",parameter);
	}
	
	
	/**
	 * 根据角色id删除角色菜单映射
	 * @param parameter
	 * @return
	 */
	public int deleteRoleMenuMappingById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.core.dao.role.RoleDao.deleteRoleMenuMappingById",parameter);
	}
	
	/**
	 * 根据角色ids删除角色菜单映射
	 * @param parameter
	 * @return
	 */
	public int deleteRoleMenuMappingByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.core.dao.role.RoleDao.deleteRoleMenuMappingByIds",parameter);
	}
	
	/**
	 * 保存角色菜单映射
	 * @param parameter
	 * @return
	 */
	public int saveRoleMenuMapping(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().insert("com.cjhme.system.core.dao.role.RoleDao.saveRoleMenuMapping",parameter);
	}
	
	/**
	 * 根据角色查询角色按钮映射
	 * @param parameter
	 * @return
	 */
	public List<RoleButtonMappingBean> queryRoleButtonMappingById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.core.dao.role.RoleDao.queryRoleButtonMappingById",parameter);
	}
	
	/**
	 * 根据角色删除角色按钮映射 
	 * @param parameter
	 * @return
	 */
	public int deleteRoleButtonMappingById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.core.dao.role.RoleDao.deleteRoleButtonMappingById",parameter);
	}
	
	/**
	 * 根据角色ids删除角色按钮映射
	 * @param parameter
	 * @return
	 */
	public int deleteRoleButtonMappingByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.core.dao.role.RoleDao.deleteRoleButtonMappingByIds",parameter);
	}
	
	/**
	 * 保存角色按钮映射
	 * @param parameter
	 * @return
	 */
	public int  saveRoleButtonMapping(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().insert("com.cjhme.system.core.dao.role.RoleDao.saveRoleButtonMapping",parameter);
	}
	
	/**
	 * 根据角色ids删除角色用户映射
	 * @param parameter
	 * @return
	 */
	public int deleteRoleUserMappingByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.core.dao.role.RoleDao.deleteRoleUserMappingByIds",parameter);
	}
	
	/**
	 * 根据角色ids删除数据字典明细角色映射 
	 * @param parameter
	 * @return
	 */
	public int deleteDataDicItemRoleMappingByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.core.dao.role.RoleDao.deleteDataDicItemRoleMappingByIds",parameter);
	}
	
	/**
	 * 根据角色查询角色访问地址映射
	 * @param parameter
	 * @return
	 */
	public List<RoleAccessUrlMappingBean> queryRoleAccessUrlMappingById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.core.dao.role.RoleDao.queryRoleAccessUrlMappingById",parameter);
	}
	
	/**
	 * 保存角色访问地址映射
	 * @param parameter
	 * @return
	 */
	public int saveRoleAccessUrlMapping(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().insert("com.cjhme.system.core.dao.role.RoleDao.saveRoleAccessUrlMapping",parameter);
	}
	
	/**
	 * 根据角色id删除角色访问地址映射
	 * @param parameter
	 * @return
	 */
	public int  deleteRoleAccessUrlMappingById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.core.dao.role.RoleDao.deleteRoleAccessUrlMappingById",parameter);
	}
	
	/**
	 * 根据角色ids删除角色访问地址映射
	 * @param parameter
	 * @return
	 */
	public int deleteRoleAccessUrlMappingByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.core.dao.role.RoleDao.deleteRoleAccessUrlMappingByIds",parameter);
	}
	

	 /**
     * 根据条件查询角色访问地址与按钮列表
     * @param parameter
     * @return
     */
	public List<TreeNodeBean> queryRoleAccessUrlAndButtonList(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.core.dao.role.RoleDao.queryRoleAccessUrlAndButtonList",parameter);
	}
	
	/**
     * 根据条件查询角色数据权限列表
     * @param parameter
     * @return
     */
	public List<TreeNodeBean> queryRoleDataPermissionsList(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.core.dao.role.RoleDao.queryRoleDataPermissionsList",parameter);
	}
	
	/**
	 * 根据角色删除角色数据权限映射
	 * @param parameter
	 * @return
	 */
	public int deleteRoleDataPermissionsMappingById(Map<String,Object> parameter) {
		return this.getSqlSessionTemplate().delete("com.cjhme.system.core.dao.role.RoleDao.deleteRoleDataPermissionsMappingById",parameter);
	}
	
	/**
	 * 根据角色ids删除角色数据权限映射
	 * @param parameter
	 * @return
	 */
	public int deleteRoleDataPermissionsMappingByIds(Map<String,Object> parameter) {
		return this.getSqlSessionTemplate().delete("com.cjhme.system.core.dao.role.RoleDao.deleteRoleDataPermissionsMappingByIds",parameter);
	}
	
	/**
	 * 保存角色数据权限映射
	 * @param parameter
	 * @return
	 */
	public int saveRoleDataPermissionsMapping(Map<String,Object> parameter) {
		return this.getSqlSessionTemplate().insert("com.cjhme.system.core.dao.role.RoleDao.saveRoleDataPermissionsMapping",parameter);
	}
}
