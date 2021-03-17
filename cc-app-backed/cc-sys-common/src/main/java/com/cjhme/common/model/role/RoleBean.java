package com.cjhme.common.model.role;

import java.util.List;

import com.cjhme.common.model.accessurl.AccessUrlBean;
import com.cjhme.common.model.base.BaseBean;

/**
 * 
 * <p>  
 * Title: RoleBean.java 
 * </p>  
 * Description: 角色
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
public class RoleBean extends BaseBean {


	private static final long serialVersionUID = 1L;
	
	/**
	 * id
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
	
	/**
	 * 角色描述
	 */
	private String roleDescribe;
	
	/**
	 * 是否超级管理员(1否，2是)
	 */
	private String isSuper;
	
	
	/**
	 * 访问地址列表
	 */
	private List<AccessUrlBean> accessUrls;
	
	

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

	public String getRoleDescribe() {
		return roleDescribe;
	}

	public void setRoleDescribe(String roleDescribe) {
		this.roleDescribe = roleDescribe;
	}

	public String getIsSuper() {
		return isSuper;
	}

	public void setIsSuper(String isSuper) {
		this.isSuper = isSuper;
	}


	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}


	public List<AccessUrlBean> getAccessUrls() {
		return accessUrls;
	}

	public void setAccessUrls(List<AccessUrlBean> accessUrls) {
		this.accessUrls = accessUrls;
	}


	

}
