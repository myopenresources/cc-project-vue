package com.cjhme.system.impl.base.mybatis.datapermissions.processor;

import java.util.Map;

import org.apache.ibatis.reflection.MetaObject;

import com.cjhme.common.model.datapermissions.DataPermissionsBean;

/**
 * 
 * <p>
 * Title: DataPermissionsProcessorInterface.java
 * </p>
 * Description: mybatis数据权限处理器实现接口
 * <p>
 * Modify histoty:
 * 
 * @author
 * @version 1.0
 */
public interface DataPermissionsProcessorInterface {

	/**
	 * 构建sql
	 * 
	 * @param sourceSql
	 * @return
	 */
	public void buildSql(MetaObject metaObject,String sourceSql, DataPermissionsBean dataPermissionsBean, Map<String, Object> parameter) throws Exception;
}
