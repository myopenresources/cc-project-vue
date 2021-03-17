package com.cjhme.system.impl.service.menu.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjhme.common.model.base.DataPaging;
import com.cjhme.common.model.base.TreeNodeBean;
import com.cjhme.common.model.menu.MenuBean;
import com.cjhme.common.util.CreateTreeUtil;
import com.cjhme.system.impl.dao.menu.MenuDao;
import com.cjhme.system.impl.service.menu.MenuService;

/**
 * 
 * <p>  
 * Title: MenuServiceImpl.java 
 * </p>  
 * Description: 菜单
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
@Service("system.menuService")
public class MenuServiceImpl implements MenuService {

	private MenuDao menuDao;
	
	
	/**
	 * 初始化用户菜单
	 * @param parameter
	 * @return
	 */
	public String queryUserMenuByRoleId(Map<String,Object> parameter)throws Exception{
		List<TreeNodeBean> list = this.menuDao.queryUserMenuByRoleId(parameter);
		return CreateTreeUtil.getTreeJson(list,"",CreateTreeUtil.STATE_OPEN, CreateTreeUtil.STATE_CLOSED);
	}
	
	
	/**
	 * 查询菜单列表
	 * @return
	 */
	public String queryMenuList(String rootState, String childState)throws Exception{
		List<TreeNodeBean> list= this.menuDao.queryMenuList();
		return CreateTreeUtil.getTreeJson(list, "",rootState, childState);
	}
	
	/**
	 * 根据条件查询菜单
	 * @param parameter
	 * @return
	 */
	public String  queryMenuByCondition(Map<String, Object> parameter)throws Exception{
		List<TreeNodeBean> list= this.menuDao.queryMenuByCondition(parameter);
		return CreateTreeUtil.getTreeJson(list, "",CreateTreeUtil.STATE_OPEN, CreateTreeUtil.STATE_CLOSED);
	}

	/**
	 * 根据条件分页查询菜单
	 * @param pageParameter
	 * @return
	 */
	public DataPaging<Object> queryMenuByConditionPaging(DataPaging<Object> pageParameter){
		return this.menuDao.queryMenuByConditionPaging(pageParameter);
	}
	
	/**
	 * 判断菜单是否存在
	 * 
	 * @param parameter
	 * @return
	 */
	public int queryMenuIsExists(Map<String, Object> parameter){
		return this.menuDao.queryMenuIsExists(parameter);
	}


	/**
	 * 添加菜单
	 * 
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int saveMenu(Map<String, Object> parameter){
		int sortNum = this.menuDao.queryMenuMaxSortNumByPMId(parameter);
		parameter.put("sortNum", sortNum+1);
		return this.menuDao.saveMenu(parameter);
	}

	/**
	 * 根据id查询菜单
	 * 
	 * @param parameter
	 * @return
	 */
	public MenuBean queryMenuByMenuId(Map<String, Object> parameter){
		return this.menuDao.queryMenuByMenuId(parameter);
	}

	/**
	 * 根据id修改菜单
	 */
	@Transactional
	public int updateMenuByMenuId(Map<String, Object> parameter){
		return this.menuDao.updateMenuByMenuId(parameter);
	}

	/**
	 * 根据ids删除菜单
	 * 
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int deleteMenuByIds(Map<String, Object> parameter){
		//删除菜单角色映射
		this.menuDao.deleteMenuRoleMappingByIds(parameter);
		
		//删除菜单
		return this.menuDao.deleteMenuByIds(parameter);
	}
	
	
	/**
	 * 根据id移动菜单
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int updateMenuParentMenuIdById(Map<String, Object> parameter){
		String sortNum = this.menuDao.queryMaxSortNumByParentMenuId(parameter);
		parameter.put("sortNum", sortNum);
		return this.menuDao.updateMenuParentMenuIdById(parameter);
	}
	
	/**
	 * 根据id移动菜单序号
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int updateMenuSortNumById(Map<String, Object> parameter){
		String moveSign = String.valueOf(parameter.get("moveSign"));
		String menuId = String.valueOf(parameter.get("menuId"));
		String sortNum = String.valueOf(parameter.get("sortNum"));
		
		MenuBean menuBean=null;
		
		if(null!=moveSign && !"null".equals(moveSign) && "up".equals(moveSign)){
			 menuBean =this.menuDao.queryPreviousMenuById(parameter);
		}else if(null!=moveSign && !"null".equals(moveSign) && "down".equals(moveSign)){
			 menuBean =this.menuDao.queryNextMenuById(parameter);
			
		}else{
			return -1;
		}
		
		
		if(null!=menuBean){
			parameter.put("menuId", menuId);
			parameter.put("sortNum", menuBean.getSortNum());
			this.menuDao.updateMenuSortNumById(parameter);
			
			parameter.put("menuId", menuBean.getMenuId());
			parameter.put("sortNum", sortNum);
			this.menuDao.updateMenuSortNumById(parameter);
			return 1;
		}else{
			return -2;
		}
	}
	
	public MenuDao getMenuDao() {
		return menuDao;
	}

	@Resource(name="system.menuDao")
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}
	
	
	
}
