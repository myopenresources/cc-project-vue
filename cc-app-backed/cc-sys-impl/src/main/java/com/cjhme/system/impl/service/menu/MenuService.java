package com.cjhme.system.impl.service.menu;

import java.util.Map;

import com.cjhme.common.model.base.DataPaging;
import com.cjhme.common.model.menu.MenuBean;


/**
 * 
 * <p>  
 * Title: MenuService.java 
 * </p>  
 * Description: 菜单
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
public interface MenuService {

	/**
	 * 初始化用户菜单
	 * @param parameter
	 * @return
	 */
	public String queryUserMenuByRoleId(Map<String,Object> parameter)throws Exception;
	
	
	/**
	 * 查询菜单列表
	 * @return
	 */
	public String queryMenuList(String rootState, String childState)throws Exception;
	
	/**
	 * 根据条件查询菜单
	 * @param parameter
	 * @return
	 */
	public String queryMenuByCondition(Map<String, Object> parameter)throws Exception;
	
	/**
	 * 根据条件分页查询菜单
	 * @param parameter
	 * @return
	 */
	public DataPaging<Object> queryMenuByConditionPaging(DataPaging<Object> pageParameter);
	
	/**
	 * 判断菜单是否存在
	 * 
	 * @param parameter
	 * @return
	 */
	public int queryMenuIsExists(Map<String, Object> parameter);


	/**
	 * 添加菜单
	 * 
	 * @param parameter
	 * @return
	 */
	public int saveMenu(Map<String, Object> parameter);

	/**
	 * 根据id查询菜单
	 * 
	 * @param parameter
	 * @return
	 */
	public MenuBean queryMenuByMenuId(Map<String, Object> parameter);

	/**
	 * 根据id修改菜单
	 */
	public int updateMenuByMenuId(Map<String, Object> parameter);

	/**
	 * 根据ids删除菜单
	 * 
	 * @param parameter
	 * @return
	 */
	public int deleteMenuByIds(Map<String, Object> parameter);
	
	
	/**
	 * 根据id移动菜单
	 * @param parameter
	 * @return
	 */
	public int updateMenuParentMenuIdById(Map<String, Object> parameter);
	
	
	/**
	 * 根据id移动菜单序号
	 * @param parameter
	 * @return
	 */
	public int updateMenuSortNumById(Map<String, Object> parameter);
}
