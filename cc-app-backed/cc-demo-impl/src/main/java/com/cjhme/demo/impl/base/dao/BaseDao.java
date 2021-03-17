package com.cjhme.demo.impl.base.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import com.cjhme.system.impl.base.mybatis.dao.DaoPageExtend;

/**
 * 
 * <p>
 * Title: BaseDao.java
 * </p>
 * Description: 基础BaseDao，所有dao继承BaseDao
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public abstract class BaseDao extends DaoPageExtend {

	@Resource(name = "demo.sqlSessionTemplate")
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

}
