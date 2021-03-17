package com.cjhme.system.impl.dao.datapermissions.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cjhme.common.model.base.DataPaging;
import com.cjhme.common.model.datapermissions.DataPermissionsBean;
import com.cjhme.system.impl.base.dao.BaseDao;
import com.cjhme.system.impl.dao.datapermissions.DataPermissionsDao;

/**
 * 
 * <p>  
 * Title: DataPermissionsDaoImpl.java 
 * </p>  
 * Description: 数据权限
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
@Repository("system.dataPermissionsDao")
public class DataPermissionsDaoImpl extends BaseDao implements DataPermissionsDao {

	/**
	 * 根据条件分页查询数据权限
	 * @param pageParameter
	 * @return
	 */
	public DataPaging<Object> queryDataPermissionsByConditionPaging(DataPaging<Object> pageParameter){
		return this.selectPaging("com.cjhme.system.impl.dao.datapermissions.DataPermissionsDao.queryDataPermissionsByConditionPaging", pageParameter);
	}
	
	/**
	 * 判断数据权限是否存在
	 * @param parameter
	 * @return
	 */
	public int queryDataPermissionsIsExists(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.impl.dao.datapermissions.DataPermissionsDao.queryDataPermissionsIsExists",parameter);
	}
	
	/**
	 * 添加数据权限
	 * @param parameter
	 * @return
	 */
	public int saveDataPermissions(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().insert("com.cjhme.system.impl.dao.datapermissions.DataPermissionsDao.saveDataPermissions",parameter);
	}
	
	/**
	 * 根据id查询数据权限
	 * @param parameter
	 * @return
	 */
	public DataPermissionsBean queryDataPermissionsById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.impl.dao.datapermissions.DataPermissionsDao.queryDataPermissionsById",parameter);
	}
	
	/**
	 *  根据id修改数据权限
	 * @param parameter
	 * @return
	 */
	public int updateDataPermissionsById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.impl.dao.datapermissions.DataPermissionsDao.updateDataPermissionsById",parameter);
	}
	
	/**
	 * 根据ids删除数据权限
	 */
	public int deleteDataPermissionsByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.impl.dao.datapermissions.DataPermissionsDao.deleteDataPermissionsByIds",parameter);
	}
	
	/**
	 * 查询所有数据权限列表
	 * @return
	 */
	public List<DataPermissionsBean> queryAllDataPermissionsList(){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.impl.dao.datapermissions.DataPermissionsDao.queryAllDataPermissionsList");
	}
	
	/**
	 * 根据数据权限ids删除数据权限角色映射
	 * @param parameter
	 * @return
	 */
	public int deleteDataPermissionsRoleMappingByIds(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.impl.dao.datapermissions.DataPermissionsDao.deleteDataPermissionsRoleMappingByIds", parameter);
	}
}
