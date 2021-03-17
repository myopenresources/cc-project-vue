package com.cjhme.system.impl.service.role.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cjhme.common.constant.RoleConstant;
import com.cjhme.common.model.base.DataPaging;
import com.cjhme.common.model.base.TreeNodeBean;
import com.cjhme.common.model.role.RoleAccessUrlMappingBean;
import com.cjhme.common.model.role.RoleBean;
import com.cjhme.common.model.role.RoleButtonMappingBean;
import com.cjhme.common.model.role.RoleMenuMappingBean;
import com.cjhme.common.util.UUIDUtil;
import com.cjhme.system.impl.dao.role.RoleDao;
import com.cjhme.system.impl.service.role.RoleService;

/**
 * 
 * <p>
 * Title: RoleService.java
 * </p>
 * Description: 角色
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
@Service("system.roleService")
public class RoleServiceImpl implements RoleService {

	private RoleDao roleDao;

	/**
	 * 根据条件分页查询角色
	 * 
	 * @param parameter
	 * @return
	 */
	public DataPaging<Object> queryRoleByConditionPaging(DataPaging<Object> parameter) {
		return this.roleDao.queryRoleByConditionPaging(parameter);
	}

	/**
	 * 判断角色是否存在
	 * 
	 * @param parameter
	 * @return
	 */
	public int queryRoleIsExists(Map<String, Object> parameter) {
		return this.roleDao.queryRoleIsExists(parameter);
	}

	/**
	 * 添加角色
	 * 
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int saveRole(Map<String, Object> parameter) {
		return this.roleDao.saveRole(parameter);
	}

	/**
	 * 根据id查询角色
	 * 
	 * @param parameter
	 * @return
	 */
	public RoleBean queryRoleById(Map<String, Object> parameter) {
		return this.roleDao.queryRoleById(parameter);
	}

	/**
	 * 根据id修改角色
	 * 
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int updateRoleById(Map<String, Object> parameter) {
		return this.roleDao.updateRoleById(parameter);
	}

	/**
	 * 根据ids删除角色
	 * 
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int deleteRoleByIds(Map<String, Object> parameter) {
		// 删除角色菜单映射
		this.roleDao.deleteRoleMenuMappingByIds(parameter);

		// 删除角色按钮映射
		this.roleDao.deleteRoleButtonMappingByIds(parameter);

		// 删除角色用户映射
		this.roleDao.deleteRoleUserMappingByIds(parameter);

		// 删除数据字典明细角色映射
		this.roleDao.deleteDataDicItemRoleMappingByIds(parameter);

		// 删除角色访问地址映射
		this.roleDao.deleteRoleAccessUrlMappingByIds(parameter);

		// 删除角色数据权限映射
		this.roleDao.deleteRoleDataPermissionsMappingByIds(parameter);

		// 删除角色
		return this.roleDao.deleteRoleByIds(parameter);
	}

	/**
	 * 查询所有角色列表
	 * 
	 * @return
	 */
	public List<RoleBean> queryAllRoleList() {
		return this.roleDao.queryAllRoleList();
	}

	/**
	 * 根据角色id查询角色菜单映射
	 * 
	 * @param parameter
	 * @return
	 */
	public List<RoleMenuMappingBean> queryRoleMenuMappingById(Map<String, Object> parameter) {
		return this.roleDao.queryRoleMenuMappingById(parameter);
	}

	/**
	 * 保存角色菜单映射
	 * 
	 * @param parameter
	 * @return
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public int saveRoleMenuMapping(Map<String, Object> parameter) {
		this.roleDao.deleteRoleMenuMappingById(parameter);
		List<String> menuIds = (List<String>) parameter.get("menuIds");
		for (String menuId : menuIds) {
			parameter.put("menuId", menuId);
			parameter.put("rmmId", UUIDUtil.createUUID32());
			this.roleDao.saveRoleMenuMapping(parameter);
		}
		return 1;
	}

	/**
	 * 根据角色查询角色按钮映射
	 * 
	 * @param parameter
	 * @return
	 */
	public List<RoleButtonMappingBean> queryRoleButtonMappingById(Map<String, Object> parameter) {
		return this.roleDao.queryRoleButtonMappingById(parameter);
	}

	/**
	 * 保存角色按钮映射
	 * 
	 * @param parameter
	 * @return
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public int saveRoleButtonMapping(Map<String, Object> parameter) {
		this.roleDao.deleteRoleButtonMappingById(parameter);
		List<String> buttonIds = (List<String>) parameter.get("buttonIds");
		for (String buttonId : buttonIds) {
			parameter.put("buttonId", buttonId);
			parameter.put("rbmId", UUIDUtil.createUUID32());
			this.roleDao.saveRoleButtonMapping(parameter);
		}
		return 1;
	}

	/**
	 * 根据角色查询角色访问地址映射
	 * 
	 * @param parameter
	 * @return
	 */
	public List<RoleAccessUrlMappingBean> queryRoleAccessUrlMappingById(Map<String, Object> parameter) {
		return this.roleDao.queryRoleAccessUrlMappingById(parameter);
	}

	/**
	 * 保存角色访问地址映射
	 * 
	 * @param parameter
	 * @return
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public int saveRoleAccessUrlMapping(Map<String, Object> parameter) {
		this.roleDao.deleteRoleAccessUrlMappingById(parameter);
		List<String> accessUrlIds = (List<String>) parameter.get("accessUrlIds");
		for (String accessUrlId : accessUrlIds) {
			parameter.put("accessUrlId", accessUrlId);
			parameter.put("rumId", UUIDUtil.createUUID32());
			this.roleDao.saveRoleAccessUrlMapping(parameter);
		}
		return 1;
	}

	/**
	 * 根据条件查询角色访问地址与按钮列表
	 * 
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	public List<TreeNodeBean> queryRoleAccessUrlAndButtonList(Map<String, Object> parameter) throws Exception {
		List<TreeNodeBean> accessUrlButtonList = this.roleDao.queryRoleAccessUrlAndButtonList(parameter);

		List<TreeNodeBean> resultList = new ArrayList<>();

		// 匹配业务标识名称
		if (!StringUtils.isEmpty(accessUrlButtonList)) {
			Map<String, TreeNodeBean> busniessMarkMap = new HashMap<>();
			for (TreeNodeBean treeNodeBean : accessUrlButtonList) {
				treeNodeBean.setText("(" + treeNodeBean.getAttributes().get("attr2") + ")" + treeNodeBean.getText());

				// 分组业务标识
				String key = String.valueOf(treeNodeBean.getAttributes().get("attr3"));
				if (!busniessMarkMap.containsKey(key)) {
					TreeNodeBean newTreeNodeBean = new TreeNodeBean();
					newTreeNodeBean.setId(key);
					newTreeNodeBean.setText(String.valueOf(treeNodeBean.getAttributes().get("attr4")));
					busniessMarkMap.put(key, newTreeNodeBean);
				}

			}

			resultList = new ArrayList<>(busniessMarkMap.values());

			for (TreeNodeBean treeNodeBean : accessUrlButtonList) {

				for (TreeNodeBean busniessMark : resultList) {
					if (busniessMark.getId().equals(treeNodeBean.getAttributes().get("attr3"))) {
						busniessMark.getChildren().add(treeNodeBean);
						break;
					}
				}
			}
		}

		return resultList;

	}

	/**
	 * 根据条件查询角色数据权限列表
	 * 
	 * @param parameter
	 * @return
	 */
	public List<TreeNodeBean> queryRoleDataPermissionsList(Map<String, Object> parameter) throws Exception {
		List<TreeNodeBean> list = this.roleDao.queryRoleDataPermissionsList(parameter);

		List<TreeNodeBean> resultList = new ArrayList<>();

		if (!StringUtils.isEmpty(list)) {
			Map<String, TreeNodeBean> group = new HashMap<>();
			for (TreeNodeBean treeNodeBean : list) {
				// 分组业务标识
				String key = String.valueOf(treeNodeBean.getParentId());
				if (!group.containsKey(key)) {
					TreeNodeBean newTreeNodeBean = new TreeNodeBean();
					newTreeNodeBean.setId(key);
					newTreeNodeBean.setText(String.valueOf(treeNodeBean.getAttributes().get("attr1")));
					group.put(key, newTreeNodeBean);
				}

			}

			resultList = new ArrayList<>(group.values());

			for (TreeNodeBean treeNodeBean : list) {

				for (TreeNodeBean category : resultList) {
					if (category.getId().equals(treeNodeBean.getParentId())) {
						category.getChildren().add(treeNodeBean);
						break;
					}
				}
			}
		}

		return resultList;
	}

	/**
	 * 保存角色权限分配
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int saveRolePermissionDistribute(Map<String, Object> parameter) {
		// 菜单保存
		this.roleDao.deleteRoleMenuMappingById(parameter);
		List<String> menuIds = (List<String>) parameter.get("menuIds");
		for (String menuId : menuIds) {
			parameter.put("menuId", menuId);
			parameter.put("rmmId", UUIDUtil.createUUID32());
			this.roleDao.saveRoleMenuMapping(parameter);
		}

		// 按钮与访问地址
		this.roleDao.deleteRoleButtonMappingById(parameter);
		this.roleDao.deleteRoleAccessUrlMappingById(parameter);

		// 数据权限
		this.roleDao.deleteRoleDataPermissionsMappingById(parameter);

		List<Map<String, String>> btnAndAccessUrlIds = (List<Map<String, String>>) parameter.get("btnAndAccessUrlIds");

		for (Map<String, String> item : btnAndAccessUrlIds) {
			if (item.get("type").equals(RoleConstant.TYPE_BTN)) {
				parameter.put("buttonId", item.get("id"));
				parameter.put("rbmId", UUIDUtil.createUUID32());
				this.roleDao.saveRoleButtonMapping(parameter);
			} else if (item.get("type").equals(RoleConstant.TYPE_ACCESS_URL)) {
				parameter.put("accessUrlId", item.get("id"));
				parameter.put("rumId", UUIDUtil.createUUID32());
				this.roleDao.saveRoleAccessUrlMapping(parameter);
			}
		}

		List<Map<String, String>> dataPermissionsIds = (List<Map<String, String>>) parameter.get("dataPermissionsIds");
		for (Map<String, String> item : dataPermissionsIds) {
			parameter.put("dataPermissionsId", item.get("id"));
			parameter.put("rdpmId", UUIDUtil.createUUID32());
			this.roleDao.saveRoleDataPermissionsMapping(parameter);
		}

		return 1;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	@Resource(name = "system.roleDao")
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

}