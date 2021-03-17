package com.cjhme.common.model.accessurl;

import com.cjhme.common.model.base.BaseBean;

/**
 * 
 * <p>
 * Title: AccessUrlBean.java
 * </p>
 * Description: 访问地址
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public class AccessUrlBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	/**
	 * 访问地址id
	 */
	private String accessUrlId;

	/**
	 * 访问地址
	 */
	private String accessUrl;

	/**
	 * 访问地址名称
	 */
	private String accessUrlName;
	
	/**
	 * 业务标识
	 */
	private String busniessMark;
	
	/**
	 * 业务标识描述
	 */
	private String busniessMarkDesc;


	public String getAccessUrlId() {
		return accessUrlId;
	}

	public void setAccessUrlId(String accessUrlId) {
		this.accessUrlId = accessUrlId;
	}

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}


	public String getAccessUrlName() {
		return accessUrlName;
	}

	public void setAccessUrlName(String accessUrlName) {
		this.accessUrlName = accessUrlName;
	}

	public String getBusniessMark() {
		return busniessMark;
	}

	public void setBusniessMark(String busniessMark) {
		this.busniessMark = busniessMark;
	}

	public String getBusniessMarkDesc() {
		return busniessMarkDesc;
	}

	public void setBusniessMarkDesc(String busniessMarkDesc) {
		this.busniessMarkDesc = busniessMarkDesc;
	}
	
	

}
