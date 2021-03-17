package com.cjhme.common.model.user;

import com.cjhme.common.model.base.BaseBean;


/**
 * 
 * <p>  
 * Title: UserRoleMappingBean.java 
 * </p>  
 * Description: 用户角色映射
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
public class UserRoleMappingBean extends BaseBean {
	

	private static final long serialVersionUID = 1L;

	/**
	 * 用户角色映射id
	 */
	private String urmId;
	
	/**
	 * 用户id
	 */
	private String userId;
	
	/**
	 * 角色id
	 */
	private String roleId;
	
	/**
	 * 角色编码
	 */
	private String roleCode;
	
	/**
	 * 角色名称
	 */
	private String roleName;

	public String getUrmId() {
		return urmId;
	}

	public void setUrmId(String urmId) {
		this.urmId = urmId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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


