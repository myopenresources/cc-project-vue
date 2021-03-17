package com.cjhme.common.model.role;

/**
 * 
 * <p>
 * Title: RoleAccessUrlMappingBean.java
 * </p>
 * Description: 角色访问地址映射
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public class RoleAccessUrlMappingBean {

	/**
	 * id
	 */
	private String rumId;

	/**
	 * 角色id
	 */
	private String roleId;

	/**
	 * 访问地址id
	 */
	private String accessUrlId;

	/**
	 * 访问地址名称
	 */
	private String accessUrlName;

	public String getRumId() {
		return rumId;
	}

	public void setRumId(String rumId) {
		this.rumId = rumId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getAccessUrlId() {
		return accessUrlId;
	}

	public void setAccessUrlId(String accessUrlId) {
		this.accessUrlId = accessUrlId;
	}

	public String getAccessUrlName() {
		return accessUrlName;
	}

	public void setAccessUrlName(String accessUrlName) {
		this.accessUrlName = accessUrlName;
	}

}
