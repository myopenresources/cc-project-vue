package com.cjhme.common.model.datapermissions;

import com.cjhme.common.model.base.BaseBean;

/**
 * 
 * <p>  
 * Title: DataPermissionsBean.java 
 * </p>  
 * Description: 数据权限
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
public class DataPermissionsBean extends BaseBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	private String dataPermissionsId;
	
	/**
	 * 数据权限名称
	 */
	private String dataPermissionsName;
	
	/**
	 * 访问地址
	 */
	private String accessUrl;
	
	/**
	 * sql语句ID
	 */
	private String sqlId;
	
	/**
	 * 处理器优先级
	 */
	private int processorPriority;
	

	/**
	 * 基本处理器
	 */
	private String primaryProcessor;
	
	/**
	 * 辅助处理器
	 */
	private String secondaryProcessor;
	
	/**
	 * 匹配列名
	 */
	private String matchColumn;
	

	/**
	 * 数据权限分组
	 */
    private String dataPermissionsCategory;
    
    /**
	 * 参数关键字
	 */
	private String paramKey;
	
	

	public String getParamKey() {
		return paramKey;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	public String getDataPermissionsId() {
		return dataPermissionsId;
	}

	public void setDataPermissionsId(String dataPermissionsId) {
		this.dataPermissionsId = dataPermissionsId;
	}

	public String getDataPermissionsName() {
		return dataPermissionsName;
	}

	public void setDataPermissionsName(String dataPermissionsName) {
		this.dataPermissionsName = dataPermissionsName;
	}

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}

	public String getSqlId() {
		return sqlId;
	}

	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}

	public int getProcessorPriority() {
		return processorPriority;
	}

	public void setProcessorPriority(int processorPriority) {
		this.processorPriority = processorPriority;
	}

	public String getPrimaryProcessor() {
		return primaryProcessor;
	}

	public void setPrimaryProcessor(String primaryProcessor) {
		this.primaryProcessor = primaryProcessor;
	}

	public String getSecondaryProcessor() {
		return secondaryProcessor;
	}

	public void setSecondaryProcessor(String secondaryProcessor) {
		this.secondaryProcessor = secondaryProcessor;
	}

	public String getMatchColumn() {
		return matchColumn;
	}

	public void setMatchColumn(String matchColumn) {
		this.matchColumn = matchColumn;
	}

	public String getDataPermissionsCategory() {
		return dataPermissionsCategory;
	}

	public void setDataPermissionsCategory(String dataPermissionsCategory) {
		this.dataPermissionsCategory = dataPermissionsCategory;
	}
    
    
}
