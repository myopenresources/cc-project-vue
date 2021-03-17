package com.cjhme.system.impl.service.datapermissions;

import java.util.List;
import java.util.Map;

import com.cjhme.common.model.base.DataPaging;
import com.cjhme.common.model.datapermissions.DataPermissionsBean;

/**
 * 
 * <p>  
 * Title: DataPermissionsService.java 
 * </p>  
 * Description: 数据权限
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
public interface DataPermissionsService {
	/**
	 * 根据条件分页查询数据权限
	 * @param pageParameter
	 * @return
	 */
	public DataPaging<Object> queryDataPermissionsByConditionPaging(DataPaging<Object> pageParameter);
	
	/**
	 * 判断数据权限是否存在
	 * @param parameter
	 * @return
	 */
	public int queryDataPermissionsIsExists(Map<String,Object> parameter);
	
	/**
	 * 添加数据权限
	 * @param parameter
	 * @return
	 */
	public int saveDataPermissions(Map<String,Object> parameter);
	
	/**
	 * 根据id查询数据权限
	 * @param parameter
	 * @return
	 */
	public DataPermissionsBean queryDataPermissionsById(Map<String,Object> parameter);
	
	/**
	 *  根据id修改数据权限
	 * @param parameter
	 * @return
	 */
	public int updateDataPermissionsById(Map<String,Object> parameter);
	
	/**
	 * 根据ids删除数据权限
	 */
	public int deleteDataPermissionsByIds(Map<String,Object> parameter);
	
	/**
	 * 查询所有数据权限列表
	 * @return
	 */
	public List<DataPermissionsBean> queryAllDataPermissionsList();
}
