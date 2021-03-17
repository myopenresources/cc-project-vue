package com.cjhme.common.model.datadic;

import com.cjhme.common.model.base.BaseBean;

/**
 * 
 * <p>  
 * Title: DataDicItemRoleMappingBean.java 
 * </p>  
 * Description: 数据字典明细角色映射
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
public class DataDicItemRoleMappingBean extends BaseBean {
	
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String dirmId;
	
	/**
	 * 数据字典明细id
	 */
	private String dicItemId;
	
	/**
	 * 角色id
	 */
	private String roleId;
	
	/**
	 * 角色编号
	 */
	private String roleCode;
	
	/**
	 * 角色名称
	 */
	private String roleName;

	public String getDirmId() {
		return dirmId;
	}

	public void setDirmId(String dirmId) {
		this.dirmId = dirmId;
	}

	public String getDicItemId() {
		return dicItemId;
	}

	public void setDicItemId(String dicItemId) {
		this.dicItemId = dicItemId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
		this.setValue(roleId);
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
		this.setLabel(roleName);
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	
	
}
