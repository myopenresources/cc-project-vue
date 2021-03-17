package com.cjhme.common.model.user;

/**
 * 
 * <p>
 * Title: UserDeptMappingBean.java
 * </p>
 * Description: 用户部门映射
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public class UserDeptMappingBean {

	/**
	 * id
	 */
	private String udmId;

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 部门id
	 */
	private String deptId;
	
	/**
	 * 部门名称
	 */
	private String deptName;

	public String getUdmId() {
		return udmId;
	}

	public void setUdmId(String udmId) {
		this.udmId = udmId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	
	
	
}
