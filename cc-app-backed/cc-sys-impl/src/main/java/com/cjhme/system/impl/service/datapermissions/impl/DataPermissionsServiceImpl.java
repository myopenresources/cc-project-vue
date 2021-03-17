package com.cjhme.system.impl.service.datapermissions.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjhme.common.model.base.DataPaging;
import com.cjhme.common.model.datapermissions.DataPermissionsBean;
import com.cjhme.system.impl.dao.datapermissions.DataPermissionsDao;
import com.cjhme.system.impl.service.datapermissions.DataPermissionsService;


/**
 * 
 * <p>  
 * Title: DataPermissionsServiceImpl.java 
 * </p>  
 * Description: 数据权限
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
@Service("system.dataPermissionsService")
public class DataPermissionsServiceImpl implements DataPermissionsService {
	
	private DataPermissionsDao dataPermissionsDao;

	/**
	 * 根据条件分页查询数据权限
	 * @param pageParameter
	 * @return
	 */
	public DataPaging<Object> queryDataPermissionsByConditionPaging(DataPaging<Object> pageParameter){
		return this.dataPermissionsDao.queryDataPermissionsByConditionPaging(pageParameter);
	}
	
	/**
	 * 判断数据权限是否存在
	 * @param parameter
	 * @return
	 */
	public int queryDataPermissionsIsExists(Map<String,Object> parameter){
		return this.dataPermissionsDao.queryDataPermissionsIsExists(parameter);
	}
	
	/**
	 * 添加数据权限
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int saveDataPermissions(Map<String,Object> parameter){
		return this.dataPermissionsDao.saveDataPermissions(parameter);
	}
	
	/**
	 * 根据id查询数据权限
	 * @param parameter
	 * @return
	 */
	public DataPermissionsBean queryDataPermissionsById(Map<String,Object> parameter){
		return this.dataPermissionsDao.queryDataPermissionsById(parameter);
	}
	
	/**
	 *  根据id修改数据权限
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int updateDataPermissionsById(Map<String,Object> parameter){
		return this.dataPermissionsDao.updateDataPermissionsById(parameter);
	}
	
	/**
	 * 根据ids删除数据权限
	 */
	@Transactional
	public int deleteDataPermissionsByIds(Map<String,Object> parameter){
		//删除数据权限角色映射
		this.dataPermissionsDao.deleteDataPermissionsRoleMappingByIds(parameter);
		
		//删除数据权限
		return this.dataPermissionsDao.deleteDataPermissionsByIds(parameter);
	}
	
	/**
	 * 查询所有数据权限列表
	 * @return
	 */
	public List<DataPermissionsBean> queryAllDataPermissionsList(){
		return this.dataPermissionsDao.queryAllDataPermissionsList();
	}
	

	public DataPermissionsDao getDataPermissionsDao() {
		return dataPermissionsDao;
	}

	@Resource(name="system.dataPermissionsDao")
	public void setDataPermissionsDao(DataPermissionsDao dataPermissionsDao) {
		this.dataPermissionsDao = dataPermissionsDao;
	}
	
	
	
}
