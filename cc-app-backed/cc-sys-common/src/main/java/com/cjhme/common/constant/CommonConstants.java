package com.cjhme.common.constant;

/**
 * <p>
 * Title: CommonConstants.java
 * </p>
 * Description: 公共常量
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public class CommonConstants {

	// ==================系统信息==================

	/**
	 * 后台用户登录session
	 */
	public static String MANAGE_USER_SESSION = "manageUserSession";

	/**
	 * 登录过期或未登录返回json
	 */
	public static String LOING_EXPIRED_OR_NOT_LOGIN_JSON = "{\"sysErrorResult\":\"SESSION_TIME_OUT\"}";
	
	/**
	 * 权限验证未分配角色json
	 */
	public static String AUTHORITY_UNDISTRIBUTED_ROLE = "{\"sysErrorResult\":\"UNDISTRIBUTED_ROLE\"}";
	
	/**
	 * 权限验证未验证通过json
	 */
	public static String AUTHORITY_NO_AUTHORITY = "{\"sysErrorResult\":\"NO_AUTHORITY\"}";
	
	
	/**
	 * 默认密码
	 */
	public static String DEFAULT_PWD="111111";

	// ==================返回类型==================

	/**
	 * 成功
	 */
	public static String RESULT_SUCCESS = "success";

	/**
	 * 失败
	 */
	public static String RESULT_FAILURE = "failure";

	/**
	 * 错误
	 */
	public static String RESULT_ERROR = "error";
	
	// ==================数据状态==================
	

	/**
	 * 正常
	 */
	public static String STATUS_NORMAL = "1";
	
	/**
	 * 锁定
	 */
	public static String STATUS_LOCK = "2";
	
	/**
	 * 删除
	 */
	public static String STATUS_DEL = "3";
	
	/**
	 * 不等于删除
	 */
	public static String STATUS_NE_DEL = "-1";
	
	
	// ==================图片后缀==================
	
	/**
	 * png
	 */
	public static String IMG_FILE_SUFFIX_PNG = ".png";
	
	/**
	 * jpeg
	 */
	public static String IMG_FILE_SUFFIX_JPEG = ".jpeg";


	// ==================密钥==================
	public static String REQUEST_AES_KEY="requestAesKey";
	public static String LOGIN_AES_KEY="loginAesKey";

}
