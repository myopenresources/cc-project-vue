package com.cjhme.common.model.user;

import java.util.List;

import com.cjhme.common.model.base.BaseBean;
import com.cjhme.common.model.dept.DeptBean;
import com.cjhme.common.model.role.RoleBean;



/**
 * 
 * <p>  
 * Title: UserBean.java 
 * </p>  
 * Description: 用户
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
public class UserBean extends BaseBean  {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private String userId;
	
	/**
	 * 用户头像
	 */
	private String headImg;
	
	/**
	 * 用户编号
	 */
	private String userCode;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 真实姓名
	 */
	private String realName;
	
	/**
	 * 性别：1男，2女，3保密
	 */
	private String sex;

	/**
	 * 电话
	 */
	private String telephone;
	
	/**
	 * 邮箱
	 */
	private String email;
	

	/**
	 * 描述
	 */
	private String userDescribe;
	
	/**
	 * 部门列表
	 */
	private List<DeptBean>  depts;
	

	/**
	 * 角色列表
	 */
	private List<RoleBean> roles;
	
	
	/**
	 * 邮件开关(1开启，2关闭)
	 */
	private String emailSwitch;
	
	/**
	 * 短信开关(1开启，2关闭)
	 */
	private String smsSwitch;
	
	/**
	 * 身份证
	 */
	private String idCard;

	/**
	 * 密钥
	 */
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
		this.setValue(userId);
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRealName() {
		return realName;
	}


	public void setRealName(String realName) {
		this.realName = realName;
		this.setLabel(realName);
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getUserDescribe() {
		return userDescribe;
	}


	public void setUserDescribe(String userDescribe) {
		this.userDescribe = userDescribe;
	}


	public String getUserCode() {
		return userCode;
	}


	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}




	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}





	public List<DeptBean> getDepts() {
		return depts;
	}


	public void setDepts(List<DeptBean> depts) {
		this.depts = depts;
	}


	public List<RoleBean> getRoles() {
		return roles;
	}


	public void setRoles(List<RoleBean> roles) {
		this.roles = roles;
	}


	public String getHeadImg() {
		return headImg;
	}


	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}



	public String getEmailSwitch() {
		return emailSwitch;
	}


	public void setEmailSwitch(String emailSwitch) {
		this.emailSwitch = emailSwitch;
	}


	public String getSmsSwitch() {
		return smsSwitch;
	}


	public void setSmsSwitch(String smsSwitch) {
		this.smsSwitch = smsSwitch;
	}


	public String getIdCard() {
		return idCard;
	}


	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	


	
	

}


