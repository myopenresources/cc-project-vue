package com.cjhme.system.impl.base.mybatis.datapermissions.interactive;

import java.util.HashMap;
import java.util.Map;

import com.cjhme.common.model.dept.DeptBean;
import com.cjhme.common.model.user.UserBean;

/**
 * 
 * <p>
 * Title: DataPermissionsInteractive.java
 * </p>
 * Description: 数据权限交互
 * <p>
 * Modify histoty:
 * 
 * @author
 * @version 1.0
 */
public class DataPermissionsInteractive {

	private static final ThreadLocal<Map<String, Object>> DATA_PERMISSIONS = new ThreadLocal<Map<String, Object>>() {
		@Override
		protected Map<String, Object> initialValue() {
			return new HashMap<String, Object>();
		}
	};



	/**
	 * 设置访问地址参数
	 * 
	 * @param value
	 */
	public static void setDataPermissionsAccessUrl(String accessUrl) {
		Map<String, Object> params = DATA_PERMISSIONS.get();
		if (null == params) {
			params = new HashMap<String, Object>();
		}
		params.put("accessUrl", accessUrl);
	}

	/**
	 * 设置用户信息
	 * 
	 * @param value
	 */
	public static void setDataPermissionsUserInfo(UserBean UserBean) {
		Map<String, Object> params = DATA_PERMISSIONS.get();
		if (null == params) {
			params = new HashMap<String, Object>();
		}
		params.put("userInfo", UserBean);
	}
	
	/**
	 * 设置部门数据
	 * 
	 * @param value
	 */
	public static void setDataPermissionsDeptInfo(DeptBean deptBean) {
		Map<String, Object> params = DATA_PERMISSIONS.get();
		if (null == params) {
			params = new HashMap<String, Object>();
		}
		params.put("deptInfo", deptBean);
	}

	/**
	 * 设置自定参数
	 * 
	 * @param value
	 */
	public static void setDataPermissionsParameter(Map<String,Object> parameter) {
		Map<String, Object> params = DATA_PERMISSIONS.get();
		if (null == params) {
			params = new HashMap<String, Object>();
		}
		params.put("parameter", parameter);
	}

	/**
	 * 使用基本数据权限处理器
	 * 
	 * @param value
	 */
	public static void usePrimaryDataPermissionsProcessor() {
		Map<String, Object> params = DATA_PERMISSIONS.get();
		if (null == params) {
			params = new HashMap<String, Object>();
		}
		params.put("primaryProcessor", true);
	}
	
	/**
	 * 使用辅助数据权限处理器
	 * 
	 * @param value
	 */
	public static void useSecondaryDataPermissionsProcessor() {
		Map<String, Object> params = DATA_PERMISSIONS.get();
		if (null == params) {
			params = new HashMap<String, Object>();
		}
		params.put("secondaryProcessor", true);
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public static Map<String, Object> getDataPermissionsParams() {
		return DATA_PERMISSIONS.get();
	}

	/**
	 * 清除
	 */
	public static void cleantDataPermissionsParams() {
		DATA_PERMISSIONS.remove();
	}
}
